# JooMDD Development Guides#

## eJSL ##

### Language Development ###
The eJSL Language consists of three main parts: ***entities***, ***pages***, and ***extensions***.
### Editor Development ###

#### Textual Editor ###
##### Formatter #####
##### Validator #####
##### Project Wizard #####

#### (De-)Serializer Handlers ####
The Eclipse editor provides an extension to (de-)serialize *eJSL-instances* to/from *xmi-files*. This is done by two 
handler classes (*DeserializationHandler.java* and *SerializationHandler.java*) and the following extension point in
the *plugin.xml* of the *de.thm.icampus.joomdd.ejsl.ui plugin*:

	  <!-- XMI Serialization -->  
	  <extension
	        point="org.eclipse.ui.handlers">
	     <handler
	           class="de.thm.icampus.joomdd.ejsl.ui.EJSLExecutableExtensionFactory:de.thm.icampus.joomdd.ejsl.ui.handler.SerializationHandler"
	           commandId="de.thm.icampus.joomdd.ejsl.ui.handler.SerializationCommand">
	     </handler>
	  </extension>
	       
	  <extension
	        point="org.eclipse.ui.commands">
	     <command
	           id="de.thm.icampus.joomdd.ejsl.ui.handler.SerializationCommand"
	           name="eJSL --&gt; XMI">
	     </command>
	  </extension>
	  
	   <!-- XMI Deserialization -->
	  <extension
	        point="org.eclipse.ui.handlers">
	     <handler
	           class="de.thm.icampus.joomdd.ejsl.ui.EJSLExecutableExtensionFactory:de.thm.icampus.joomdd.ejsl.ui.handler.DeserializationHandler"
	           commandId="de.thm.icampus.joomdd.ejsl.ui.handler.DeserializationCommand">
	     </handler>
	  </extension>
	       
	  <extension
	        point="org.eclipse.ui.commands">
	     <command
	           id="de.thm.icampus.joomdd.ejsl.ui.handler.DeserializationCommand"
	           name="eJSL &lt;-- XMI">
	     </command>
	  </extension>   
	
	<!-- (De-)Serialization Context Menu -->
	  <extension
	        point="org.eclipse.ui.menus">
	     <menuContribution
	           locationURI="popup:org.eclipse.jdt.ui.PackageExplorer">
	        <menu
	              label="eJSL Serialization">
	           <command
	                 commandId="de.thm.icampus.joomdd.ejsl.ui.handler.SerializationCommand"
	                 style="push">
	              <visibleWhen
	                    checkEnabled="false">
	                 <iterate>
	                    <adapt
	                          type="org.eclipse.core.resources.IResource">
	                       <test
	                             property="org.eclipse.core.resources.name"
	                             value="*.xmi">
	                       </test>
	                    </adapt>
	                 </iterate>
	              </visibleWhen>
	           </command>
	           <command
	                 commandId="de.thm.icampus.joomdd.ejsl.ui.handler.DeserializationCommand"
	                 style="push">
	              <visibleWhen
	                    checkEnabled="false">
	                 <iterate>
	                    <adapt
	                          type="org.eclipse.core.resources.IResource">
	                       <test
	                             property="org.eclipse.core.resources.name"
	                             value="*.xmi">
	                       </test>
	                    </adapt>
	                 </iterate>
	              </visibleWhen>
	           </command>
	           <command
	                 commandId="de.thm.icampus.joomdd.ejsl.ui.handler.SerializationCommand"
	                 style="push">
	              <visibleWhen
	                    checkEnabled="false">
	                 <iterate>
	                    <adapt
	                          type="org.eclipse.core.resources.IResource">
	                       <test
	                             property="org.eclipse.core.resources.name"
	                             value="*.eJSL">
	                       </test>
	                    </adapt>
	                 </iterate>
	              </visibleWhen>
	           </command>
	           <command
	                 commandId="de.thm.icampus.joomdd.ejsl.ui.handler.DeserializationCommand"
	                 style="push">
	              <visibleWhen
	                    checkEnabled="false">
	                 <iterate>
	                    <adapt
	                          type="org.eclipse.core.resources.IResource">
	                       <test
	                             property="org.eclipse.core.resources.name"
	                             value="*.eJSL">
	                       </test>
	                    </adapt>
	                 </iterate>
	              </visibleWhen>
	           </command>
	        </menu>
	     </menuContribution>
	  </extension>
	  
*Attention:* The context menu is only available within the ***Java perspective***.
### Generator Development ###
#### Monolithic vs dynamic generator structure ####
The generator structure consists of a platform-independent and a platform-specific part. The platform-independent part 
extends the generated API for the language by additional domain-specific functionality, whereas the platform-specific part 
contains the generator template for the platform-specific code generation.

<image of the package structure>

#### Ressource Transformator ####
The **Ressource Transformator** (RT) extends any eJSL instance by missing implicit options to ensure a full instance model. 
It uses an ressource (instance model) as input and returns a full instance model as ressource, which can e.g. be used by a
generator within Xtend generator templates.

The following example illustrates how the RT works:

<image of an RT example>

### Plugin Development ###
Within the structure of the JooMDD project, a set of installable plugins for the following IDEs should always be provided: 
*Eclipse*, *IntelliJ IDEA*, and *PHPStorm*. 
#### Eclipse ####
#### IntelliJ ####
#### PHPStorm ####
### Instances ###
#### Simple Default ####
#### Complex Default ####
#### Weblinks ####
#### Conference ####

***
## Copyright ##
Copyright (C) 2013 - 2016, [iCampus](http://icampus.thm.de) - [Technische Hochschule Mittelhessen](http://www.thm.de). 
All rights reserved.
This project is distributed under the GPL (GNU General Public License) version 2. For further information see 
the [License details](https://git.thm.de/JooMDD/joomdd_repo/blob/master/LICENSE).

***
Please feel free to [contact](icampu@lists.thm.de) us, if you find some bugs or if you like to contribute to the project.