package de.thm.icampus.mdd

import java.io.{File, PrintWriter}
import java.nio.file.{Path, Paths}
import javax.swing.{JFileChooser, JOptionPane}
import javax.swing.filechooser.FileNameExtensionFilter

import de.thm.icampus.mdd.Builder.EJSLModelFunctionWrapper

import scala.util.{Failure, Success, Try}

object Main extends App {

  val arguments = resolveArguments(args.toList)
  validateArgumentDependencies(arguments)

  val noGui = arguments.contains(NoGUI)

  try {

    val manifestPath : Path = if(noGui) {
      arguments.filter(_.isInstanceOf[ManifestPath]).last.asInstanceOf[ManifestPath].path
    } else {
      chooseXMLManifestWithGUI.get
      //TODO is Option
    }

    println(manifestPath)

    val eJSLModel = Builder.build(manifestPath)

    val outputPath : Path = if(arguments.exists(_.isInstanceOf[OutputPath])) {
      arguments.filter(_.isInstanceOf[OutputPath]).last.asInstanceOf[OutputPath].path
    } else {
      Paths.get(manifestPath.getParent.toAbsolutePath.toString + File.separator + eJSLModel.name + ".eJSL")
    }

    new PrintWriter(outputPath.toString) {
      write(eJSLModel.toTemplate); close()
    }

    if(!noGui){
      JOptionPane.showMessageDialog(null, "Model Saved in: " + outputPath)
    }

    println("Model Saved in: " + outputPath)

  } catch {
    case e: Exception ⇒ {
      e.printStackTrace()
      if(!noGui) {
        JOptionPane.showMessageDialog(null, e.getMessage, "Error", JOptionPane.ERROR_MESSAGE)
      }
    }
  }


  /**
   * Dialog for choosing a File
   *
   * @return The path to the choosen file. None if Dialog was aborted.
   */
  def chooseXMLManifestWithGUI: Option[Path] = {
    val fc = new JFileChooser()
    fc.addChoosableFileFilter(new FileNameExtensionFilter("XML File", "xml"))

    val action = fc.showOpenDialog(null)

    if (action == JFileChooser.APPROVE_OPTION)
      Option(fc.getSelectedFile.toPath)
    else
      None
  }

  sealed abstract class ConsoleArgument
  case object NoGUI extends ConsoleArgument
  case class OutputPath(path: Path) extends ConsoleArgument
  case class ManifestPath(path: Path) extends ConsoleArgument


  /**
    * This method arguments from a list of strings
    *
    * @param input list of strings to parse
    * @return all parsed arguments from the given list
    */
  def resolveArguments(input: List[String], arguments: List[ConsoleArgument] = List()) : List[ConsoleArgument] = input match {
    case Nil => arguments
    case ("-o" | "-output") :: tail => if(tail.isEmpty) {
      println("Undefined output path")
      sys.exit(1)
    } else {
      val pathString = tail(0)
      Try(Paths.get(pathString)) match {
        case Success(path) => resolveArguments(tail.drop(1), arguments :+ OutputPath(path))
        case Failure(ex) =>
          println("Invalid output path")
          sys.exit(1)
      }
    }
    case ("-m" | "-manifest") :: tail => if(tail.isEmpty) {
      println("Undefined manifest path")
      sys.exit(1)
    } else {
      val pathString = tail(0)
      Try(Paths.get(pathString)) match {
        case Success(path) =>
          val manifestFile = new File(pathString)
          if(manifestFile.exists() && (manifestFile.isDirectory | !manifestFile.canRead)) {
            println("Invalid manifest file")
            sys.exit(1)
          }
          resolveArguments(tail.drop(1), arguments :+ ManifestPath(path))
        case Failure(ex) =>
          println("Invalid manifest path")
          sys.exit(1)
      }
    }
    case ("-ng" | "-no-gui") :: tail => resolveArguments(tail, arguments :+ NoGUI)
    case argument :: tail =>
      println("Unknown argument " + argument)
      sys.exit(1)
  }


  /**
    * Validates the dependencies between arguments
    *
    * @param arguments all arguments to validate
    */
  def validateArgumentDependencies(arguments: List[ConsoleArgument]) = {
    if(arguments.contains(NoGUI) && !arguments.exists(_.isInstanceOf[ManifestPath])) {
      println("Undefined manifest")
      sys.exit(1)
    }
  }

}
