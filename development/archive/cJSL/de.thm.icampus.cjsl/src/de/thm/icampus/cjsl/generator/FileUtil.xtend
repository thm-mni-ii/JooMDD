package de.thm.icampus.cjsl.generator

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.BufferedOutputStream
import java.io.BufferedInputStream
import java.io.FileReader
import java.io.BufferedReader
import java.util.zip.ZipFile
import java.util.zip.ZipEntry
import java.util.Enumeration

class FileUtil {
	/**
 * copy the content of a directory to a destination 
 * 
 * @param File source
 * @param File destination
 * 
 */
 	public def void copyDir(File source, File destination) throws FileNotFoundException, IOException {
		
		var File[] files = source.listFiles()
		var File newFile = null; 
		destination.mkdirs()	
		    
		if (files != null) {
			for ( File file: files) {
					newFile = new File(destination.getAbsolutePath() + System.getProperty("file.separator") + file.getName());
				if (file.isDirectory()) {
					copyDir(file, newFile)
				}
				else {
					copyFile(file, newFile)
				}
			}
		}
	}
	
	 /**
 * copy the content of a file to a destination
 * 
 * @param File source
 * @param File destination
 * 
 */
	public def void copyFile(File source, File destination) throws FileNotFoundException, IOException {
		
		var BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))
		var BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destination, true))
		var int bytes = 0
		while ((bytes = in.read()) != -1) { 
			out.write(bytes)
		}
		in.close()
		out.close()
	}
	
	/**
 * read a File and return the content in a String
 * 
 * @param String	path
 * 
 * @return String result
 */
def String readFiletoString(String path){
	var FileReader fr = new FileReader(path);
    var BufferedReader br = new BufferedReader(fr);
    
    var StringBuffer result = new StringBuffer()
    var String line = br.readLine
    while(line != null){
    	result.append(line +"\n")
    	line = br.readLine
    }

    br.close();
    
    return result.toString
	}
	
		/**
 *  to extract a Archive to a destination
 * 
 * @param File archive
 * @param File destDir
 * 
 */
 public	def   extractArchive(File archive, File destDir) throws Exception {
	        if (!destDir.exists()) {
	            destDir.mkdir();
	        }
	 
	       var ZipFile zipFile = new ZipFile(archive);
	       var Enumeration<? extends ZipEntry> entries = zipFile.entries();
	 
	       var byte[] buffer = newByteArrayOfSize(16384);
	       var  int len;
	        while (entries.hasMoreElements()) {
	           var ZipEntry entry = entries.nextElement() as ZipEntry;
	 
	           var String entryFileName = entry.getName();
	 
	           var File dir  = buildDirectoryHierarchyFor(entryFileName, destDir);
	            if (!dir.exists()) {
	                dir.mkdirs();
	            }
	 
	            if (!entry.isDirectory()) {
	              var  BufferedOutputStream bos = new BufferedOutputStream(
	                        new FileOutputStream(new File(destDir, entryFileName)));
	 
	               var BufferedInputStream bis = new BufferedInputStream(zipFile
	                        .getInputStream(entry));
	 
	                while ((len = bis.read(buffer)) > 0) {
	                    bos.write(buffer, 0, len);
	                }
	 
	                bos.flush();
	                bos.close();
	                bis.close();
	            }
	        }
	                zipFile.close();
	 
	    }
	    
	    /**
 *  build a new File name
 * 
 * @param String entryName
 * @param File destDir
 * return File
 */
	    def File buildDirectoryHierarchyFor(String entryName, File destDir) {
	      var  int lastIndex = entryName.lastIndexOf('/');
	      var  String entryFileName = entryName.substring(lastIndex + 1);
	      var  String internalPathToEntry = entryName.substring(0, lastIndex + 1);
	        return new File(destDir, internalPathToEntry);
	    }
	    
}