# JooMDD <img src="http://icampus.thm.de/images/JooMDD_Logo_transp.png" alt="JooMDDLogo" height="100" style="max-width:100%;float:right;">#

**JooMDD** provides an MDD infrastructure for the model-driven development of Joomla extension packages and Joomla application packages with concrete data. 
The current version of the infrastructure can be used as plugins within the Eclipse IDE. We are currently working on support for IntelliJ-based IDEs. We are also working on a web-based solution for a simple and independent use of our approach.

***
## Installation of JooMDD ##
### Installation in Eclipse ###
Install the JooMDD tools through the use of the following update site within the update manager in Eclipse. Please use the plugins within Eclipse Kepler, Luna, or Mars.

#### JooMDD update site: <http://icampus.thm.de/JooMDDUpdateSite> ####
### Installation in PHPStorm ###
#### Precondition: ####
IntelliJ IDEA 15, PHPStorm 10, Xtext IDEA Core 2.9 (available here: <http://download.eclipse.org/modeling/tmf/xtext/idea/2.9.0/org.eclipse.xtext.idea-2.9.0.zip>)
#### Prepare PHPStorm (only first time): ###
*	Merge the IntellijIDEA\lib\openpi.jar into the PHPStorm\lib\openapi.jar (copy all missing content to PHPStorm)
*	Copy the IntellijIDEA\lib\idea.jar to PHPStorm\lib\.
*	Edit the downloaded Plugin org.eclipse.xtext.idea\META-INF\plugin.xml. 
    * Change the "JUnit" dependency to "com.intellij.modules.lang" (Line 9)
    * Now you add the Plugin in PHPStorm via the Plugin manager (Install plugin from disk…)

#### Add the language to PHPStorm: ####
*	Add the update site in the Plugin Manager and install the Plugin.
    * 	Update Site: ...
*	Now you must configure the Plugin via the Project file (the Project file is in the work directory of the Project in the folder .idea\....iml

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

***
## Getting Started ##
### eJSL <img src="http://icampus.thm.de/images/eJSL_Logo_trans.png" alt="eJSLLogo" height="50" style="max-width:100%;float:right;">###
The eJSL part of JooMDD can be used to create extensions for the Joomla CMS in a model-driven way. Through the creation of eJSL-specific models a tremendous amount of code becomes generated automatically. eJSL supports the definition of several Joomla extension types like components, modules, plugins, and libraries. The generated code can be used within web pages, running on [Joomla 3.x](https://www.joomla.org/3). 

Please make sure, that you've installed the eJSL part of JooMDD to follow the next steps.
#### 1. Create a new eJSL project ####
There are two ways to create an eJSL project:

##### Manual project creation: #####
1. Create a new project of any type (e.g. a general, Java, or PHP project)
2. Create a new file of any name with the ending .eJSL (e.g. *model.eJSL*)
3. Start creating your model containing entities, pages, and extensions

##### Using the eJSL Project Wizard: #####
Instead of creating an eJSL project manually, you can get started easier, using the eJSL project wizard. For this, create a new project and within the "new Project" dialogue open the folder Xtext. Within this folder you should see *"EJSL Project"*. Through a double-click on this project type, the required project structure becomes generated containing source folders for your models (*src*) and for the code generated based on your models (*src-gen*). In addition, an example model is created within the src folder which can be used for a straightforward introduction.

#### 2. Create a model ####
eJSL allows you the definition of different parts of a Joomla extension. Starting with the definition of a data structure (***entities***) on to its presentation (***pages***) up to the specification of Joomla-specific ***extensions***.

#### 3. Code generation ####
When you save your model, the code generator creates your modelled Joomla extensions within the project's src-gen folder. The extensions are installable within Joomla 3 web sites and don't need any additional line of code. However, if you knwo what you do, you can extend the generated code through individual features. But beware: All the code within the src-gen folder becomes COMPLETELY overwritten, when you change your model and save it. Therefore we recommend to copy generated extensions to another folder within your project, where you can extend them without loosing them after a new code generation. Another and cleaner option is using a versioning tool like git to store your individual added code.

### cJSL <img src="http://icampus.thm.de/images/cJSL_Logo_trans.png" alt="cJSLLogo" height="50" style="max-width:100%;float:right;">###
With cJSL you have the possibility to create Joomla-based websites through the definition of so called cJSL files.
***
## Sources of JooMDD ##
Get the latest version of the eJSL and cJSL source code directly from our Git repository at the SCM at the THM (Technische Hochschule Mittelhessen). But be prepared, the repository will move to GitHub within the next weeks.

### URL to git repository: <https://git.thm.de/JooMDD/joomdd_repo> ###
***
## Copyright ##
Copyright (C) 2013 - 2015, [iCampus](http://icampus.thm.de) - [Technische Hochschule Mittelhessen](http://www.thm.de). All rights reserved.
This project is distributed under the GPL (GNU General Public License) version 2. For further information see the [License details](https://git.thm.de/JooMDD/joomdd_repo/blob/master/LICENSE).

***
Please feel free to [contact](icampu@lists.thm.de) us, if you find some bugs or if you like to contribute to the project.