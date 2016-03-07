package de.thm.icampus.mdd

import java.io.PrintWriter
import java.nio.file.Path
import javax.swing.{JOptionPane, JFileChooser}
import javax.swing.filechooser.FileNameExtensionFilter


import de.thm.icampus.mdd.Builder.EJSLModelFunctionWrapper

object Main extends App {
  try {
    val manifestPath = chooseXMLManifest
    if (manifestPath.isDefined) {
      val eJSLModel = Builder.build(manifestPath.get)

      val fileName = s"${manifestPath.get.getParent}/${eJSLModel.name}.eJSL"

      new PrintWriter(fileName.toString) {
        write(eJSLModel.asText); close()
      }

      JOptionPane.showMessageDialog(null, "Model Saved in: " + fileName)
    }
  } catch {
    case e: Exception ⇒ JOptionPane.showMessageDialog(null, e.getMessage, "Error", JOptionPane.ERROR_MESSAGE)
  }


  /**
   * Dialog for choosing a File
   *
   * @return The path to the choosen file. None if Dialog was aborted.
   */
  def chooseXMLManifest: Option[Path] = {
    val fc = new JFileChooser()
    fc.addChoosableFileFilter(new FileNameExtensionFilter("XML File", "xml"))

    val action = fc.showOpenDialog(null)

    if (action == JFileChooser.APPROVE_OPTION)
      Option(fc.getSelectedFile.toPath)
    else
      None
  }

}
