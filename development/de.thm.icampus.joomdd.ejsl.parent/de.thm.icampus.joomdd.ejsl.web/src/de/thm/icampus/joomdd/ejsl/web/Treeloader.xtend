package de.thm.icampus.joomdd.ejsl.web

import com.google.gson.Gson
import java.io.File
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.web.util.Helper

@WebServlet(name = 'Treeloader', urlPatterns = '/tree-loader/*')
class Treeloader extends HttpServlet {
	
	override protected doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var String sessionID = req.session.id;
		resp.status = HttpServletResponse.SC_OK
		resp.setHeader('Cache-Control', 'no-cache')
		
		resp.contentType = 'text/x-json'
		val gson = new Gson
		try
		{
			if(sessionID !== null)
			{
				var workspaceUserPath = Helper.getWorkspaceUserPath(sessionID)
				
				var File temp = new File(workspaceUserPath)
				if(temp.exists)
				{
					var Treeitem workspace = new Treeitem(workspaceUserPath, "download-manager")
					workspace.text = "My Workspace"
					workspace.setState("opened", true)
					var EList <Treeitem> result = new BasicEList<Treeitem>
					result.add(workspace)
					gson.toJson(result, resp.writer)
				}
				else
				{
					gson.toJson(false, resp.writer)
				}
			}
			else
			{
				gson.toJson("No sessionID found.", resp.writer)
			}
		}
		catch(Exception e)
		{
			println(e.message);
		}
	}
	
	override protected doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	  
	}
	

}