# JooMDD Development Guides#

## eJSL ##

### Language Development ###
The eJSL Language consists of three main parts: ***entities***, ***pages***, and ***extensions***.
### Editor Development ###

#### Textual Editor ###
##### Formatter #####
##### Validator #####
##### Project Wizard #####
The project Wizard is available for IntelliJ and soon for PhpStorm.
You can easy create new eJSL projects with this wizard and you can choose examples

##### Developer information #####
*	If you want to add an example just copy it into "resources/eJSLexamples/". The Wizard shows all files in this folder.
*	To add a preview just add it to "resources/previews/ with the same name as the example.

*	If you want to change the description, version ... go to the plugin.xml
*	If you want to export the plugin go to Path: C:\Users\"user"\ .IdeaIC15\system\plugins-sandbox\plugins\ .
Compress the ProjectWizard-folder into a zip file. Now you can install the .zip via install plugin from disk...

*   Merge Plugin with eJSL:
you need both plugins as zip files (EJSL2.9.1.idea-1.0.0-SNAPSHOT.zip and ProjectWizard.zip).  ("EJSL2.9.1" is just the project name)
copy ProjectWizard.zip\ProjectWizard\classes to EJSL2.9.1.idea-1.0.0-SNAPSHOT.zip\EJSL2.9.1.idea
copy the part (<extensions ... until < /idea-plugin>) of the wizard plugin.xml file to the eJSL plugin.xml file. Past it between .../depends>  (here)  <xi:include...


*	for PhpStorm compatibility the "projectfile".iml is in the folder .idea and not visible in the ide.

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
Installation (user):
you need to install Xtext IDEA Core Plugin via Plugin manager and the eJSL Plugin from ...

##### Developer information #####
*	To create an Xtext Project you need the 3 Plugins (Xtend Support, Xtext and Xtext IDEA Core).

*	You can create eJSL plugins for IntelliJ (Gradle: Path= *.parent/*.idea/Task/build/ideaZip) 
The created *.zip is in *.idea/build/distributions/
*	For PhpStorm you must change the plugin.xml (Add <depends>com.intellij.modules.lang</depends>)
Infomation:
in the plugin.xml you can change all Meta-Inf like: description, Plugin version, Plugin name ...
#### PHPStorm ####
##### Prepare PHPStorm (only first time): #####
*	Copy IntelliJdepencies.jar into path: PHPStorm installation\lib\  .
*	Now install the two downloaded plugins (Xtext and EJSL) via "Install plugin from disk ..." (Menu: File/Settings/Plugins/).

##### Add the language to your Project: #####
*	Now you must configure the plugin via the project file (the project file is in the work directory of the project in the folder .idea\"ProjectName".iml).

Example for adding the configuration in the project file:

    <component name="FacetManager">
      <facet type="de.thm.icampus.ejsl.EJSL" name="EJSL">
        <configuration>
          <option name="activated" value="true" />
          <option name="createDirectory" value="true" />
          <option name="outputDirectory" value="src-gen" />
          <option name="overwriteExisting" value="true" />
          <option name="testOutputDirectory" value="src-gen" />
        </configuration>
      </facet>
    </component>

*    To use the language you must create a folder in the project and mark it as "Sources Root".

##### Developer information #####
*	IntelliJdepencies.jar contains idea.jar and some files from openapi.jar. (IntelliJ Version 15.0.2)
Care that cou do not overwrite files that exists in the current openapi.jar. Doubled classes cause a startup error in PHPStorm
It is possible that an update crash the Xtext plugins.


*	The Xtext plugin (Version 2.9.1) has a changed plugin.xml the dependency "JUnit" is changed to "com.intellij.modules.lang"

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