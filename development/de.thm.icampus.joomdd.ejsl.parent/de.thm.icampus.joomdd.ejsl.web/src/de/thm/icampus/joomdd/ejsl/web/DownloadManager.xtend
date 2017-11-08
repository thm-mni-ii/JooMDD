package de.thm.icampus.joomdd.ejsl.web

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.eclipse.xtext.resource.IResourceServiceProvider
import java.util.Map
import javax.servlet.http.Cookie

@WebServlet(name='DownLoadManager', urlPatterns='/download-manager/*')
class DownloadManager extends HttpServlet {

	override protected doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var String name = req.getParameter("name") as String
		var String[] fileTozip = req.getParameterValues("files[]")
		println(fileTozip.toString)

		val gson = new Gson
		if (name != null && (req.session.getAttribute("joomddusername") as String).equals(name) && !fileTozip.empty) {
			resp.setHeader("Content-Type", "application/zip")
			resp.setHeader("Accept-Ranges", "bytes")
			resp.setHeader("Content-Disposition", '''attachment; filename="«name».zip"''');
			resp.status = HttpServletResponse.SC_OK
			var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
			val byte[] buffer = newByteArrayOfSize(4096000)
			var String serverPath = resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String

			// var FileOutputStream fileOut = new FileOutputStream(filoutName)			       
			var ZipOutputStream zipOut = new ZipOutputStream(resp.outputStream)
			var long length = 0;
			for (String tempFilename : fileTozip) {
				var File tempfile = new File(tempFilename.replace("download-manager", serverPath))
				println(tempfile.path)
				if (tempfile.exists) {
					if (tempfile.file) {
						putFile(zipOut, tempfile, buffer, "");
						length += tempfile.length

					} else {
						length += scanDirectorie(zipOut, buffer, tempfile.getPath(), "")

					}

				} else {
					println(tempFilename)
				}

			}

			resp.setHeader("Content-Lengt", length + "");
			println(length + " test 2")
			zipOut.close
			resp.outputStream.flush
			resp.outputStream.close

		} else {
			resp.status = HttpServletResponse.SC_OK
			resp.contentType = 'text/x-json'
			gson.toJson(false, resp.writer)
		}
	}

	private def long putFile(ZipOutputStream zos, File file, byte[] buffer, String root) {
		var long fileLenght = 0;
		try {
			var FileInputStream fis = new FileInputStream(file);

			// begin writing a new ZIP entry, positions the stream to the start of the entry data
			zos.putNextEntry(new ZipEntry(root + file.getName()));
			var int linelength;

			while ((linelength = fis.read(buffer)) > 0) {
				zos.write(buffer, 0, linelength);
				fileLenght += linelength
			}
			zos.closeEntry();
			// close the InputStream
			fis.close();
		} catch (IOException ioe) {

			println("Error creating zip file" + ioe);

		}
		return fileLenght
	}

	private def long scanDirectorie(ZipOutputStream zos, byte[] buffer, String src, String path) {
		var File dir = new File(src);

		var File[] files = dir.listFiles();
		var long length = 0;

		for (File f : files) {
			if (! f.isDirectory()) {
				if (path.isEmpty) {
					length += putFile(zos, f, buffer, "");
				} else {

					length += putFile(zos, f, buffer, path);
				}
			} else {
				if (path.isEmpty) {
					length += scanDirectorie(zos, buffer, f.getPath(), f.getName() + "/");

				} else {

					length += scanDirectorie(zos, buffer, f.getPath(), path + f.getName() + "/");
				}

			}
		}
		return length
	}

	override protected doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
		var String uri = req.requestURI;
		var String name = req.session.id
		var String serverPath = resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String
		var String userWorkspacePath = serverPath + "/" + name + uri.replace("/download-manager", "");

		if (name != null) {
			var File out = new File(userWorkspacePath)
			if (out.file) {
				resp.status = HttpServletResponse.SC_OK
				resp.setHeader('Cache-Control', 'no-cache')
				resp.contentType = 'application/x-' + out.name.split("\\.").get(1)
				var FileReader read = new FileReader(out)
				var BufferedReader readBuff = new BufferedReader(read)
				var StringBuffer buff = new StringBuffer()
				while (readBuff.ready) {
					buff.append(readBuff.readLine)
					buff.append("\n")
				}
				read.close
				readBuff.close
				resp.outputStream.print(buff.toString)

			} else {
				var zipName = userWorkspacePath.split("/").last;
				
				if(zipName.equals(name))
				{
					zipName = "workspace";
				}
				resp.setHeader("Content-Type", "application/zip")
				resp.setHeader("Accept-Ranges", "bytes")
				resp.setHeader("Content-Disposition", '''attachment; filename="«zipName».zip"''');
				resp.status = HttpServletResponse.SC_OK
				val byte[] buffer = newByteArrayOfSize(32768)

				// var FileOutputStream fileOut = new FileOutputStream(filoutName)			       
				var ZipOutputStream zipOut = new ZipOutputStream(resp.outputStream)
				var long length = 0;
				println(out.path)
				if (out.exists) {
					if (out.file) {
						putFile(zipOut, out, buffer, "");
						length += out.length

					} else {
						length += scanDirectorie(zipOut, buffer, out.getPath(), "")

					}

				} else {
				}

				resp.setHeader("Content-Lengt", length + "");
				zipOut.close
				resp.outputStream.flush
			}

		} else {
			resp.status = HttpServletResponse.SC_OK
			resp.setHeader('Cache-Control', 'no-cache')
			resp.contentType = 'text/html'
			resp.sendError(404, "Source not Found")

		}

	}

	override protected doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var resourcesProvider = IResourceServiceProvider.Registry.INSTANCE
		var String server = resourcesProvider.contentTypeToFactoryMap.get("serverpath") as String

		var Map<String, Object> users = resourcesProvider.contentTypeToFactoryMap.get(
			"mddsessions") as Map<String, Object>
		if (!checkCookies(req.cookies) || !users.containsKey(req.session.getAttribute("joomddusername"))) {
			resp.sendError(404, "User not im System")
			return
		}
		resp.status = HttpServletResponse.SC_OK
		resp.setHeader('Cache-Control', 'no-cache')
		resp.contentType = 'text/x-json'
		val gson = new Gson
		var String uri = req.requestURI;
		var String name = uri.split("/").get(2)
		var String replaceuri = uri.replace("/download-manager", server)
		var File out = new File(replaceuri)
		println("Delete " + replaceuri)
		if (out.exists) {
			var boolean delete = false
			if(out.file){
				delete = out.delete
			}else{
				delete = deleteDirectory(out);
			}
			if(delete){
			gson.toJson(true, resp.writer)
			}else{
				gson.toJson(false, resp.writer)
			}
			
		}else{
			gson.toJson(false, resp.writer)
		}
	}
    public def boolean deleteDirectory(File path) {
    if (path.exists()) {
        var File[] files = path.listFiles();
        for (File f: files) {
            if (f.isDirectory()) {
                deleteDirectory(f);
            } else {
                f.delete();
            }
        }
    }
    return (path.delete());
}
	def boolean checkCookies(Cookie[] cookies) {
		var boolean havename = false;
		var boolean haveemail = false
		for (Cookie cook : cookies) {
			if (cook.name == "joomddusername" && cook.value != null)
				havename = true
			if (cook.name == "joomddemail" && cook.value != null)
				haveemail = true
		}
		return havename && haveemail
	}
}
