# Getting Started #
### The eJSL language ###
 <img src="img/eJSL_Logo_trans.png" alt="eJSLLogo" height="100" style="max-width:100%;float:right;">
 
The **eJSL** plugin can be used to create extensions for the Joomla CMS in a model-driven way. 
Through the creation of eJSL-specific models a tremendous amount of code becomes generated automatically. 
eJSL supports the definition of several Joomla extension types like components, modules, plugins, and 
libraries. The generated code can be used within web pages, running on [Joomla 3.x](https://www.joomla.org/3). 

Please make sure, that you've installed the eJSL part of JooMDD to follow the next steps.
### 1. Create a new eJSL project ###
There are two ways to create an eJSL project:

#### Manual project creation (works for Eclipse, IntelliJ IDEA, and PhpStorm): ####
1. Create a new project of any type (e.g. a general, Java, or PHP project)
2. Create a new file of any name with the ending .eJSL (e.g. *model.eJSL*)
3. Start creating your model containing entities, pages, and extensions

#### Using the eJSL Project Wizard: ####
Instead of creating an eJSL project manually, you can get started easier, using the eJSL project wizard. 

##### Eclipse #####
Create a new project and within the "new Project" dialogue open the folder eJSL Wizard. 

<img src="img/eclipse_pw_1.png" alt="Eclipse Project Wizard" height="300" style="max-width:100%;float:right;">
<img src="img/eclipse_pw_2.png" alt="Eclipse Project Wizard 2" height="300" style="max-width:100%;float:right;">
<img src="img/eclipse_pw_3.png" alt="Eclipse Project Wizard 3" height="300" style="max-width:100%;float:right;">

Within this folder you should see *"EJSL Project"*. Give your project a name and select a model example template.
Through a click on the Finish-Button the required project structure becomes generated containing source folders 
for your models (*src*) and for the code generated based on your models (*src-gen*). The chosen example model 
is created within the src folder which can be used for a straightforward introduction.

<img src="img/eclipse_pw_4.png" alt="Eclipse Project Wizard 4" height="300" style="max-width:100%;float:right;">

##### IntelliJ #####
Create a new project and within the "new Project" dialogue click on the *eJSL* section. 

<img src="img/ij_pw_1.png" alt="IntelliJ IDEA Project Wizard" height="300" style="max-width:100%;float:right;">
<img src="img/ij_pw_2.png" alt="IntelliJ IDEA Project Wizard 2" height="300" style="max-width:100%;float:right;">

Select a model example template and subsequently give you project a name.
Through a click on the Finish-Button the required project structure becomes generated containing source folders 
for your models (*src*) and for the code generated based on your models (*src-gen*). The chosen example model 
is created within the src folder which can be used for a straightforward introduction.

<img src="img/ij_pw_3.png" alt="IntelliJ IDEA Wizard 3" height="300" style="max-width:100%;float:right;">

##### PhpStorm #####
Create a new project and within the "new Project" dialogue click on the *eJSL* section.

<img src="img/php_pw_1.png" alt="PhpStorm Project Wizard" height="300" style="max-width:100%;float:right;">

Select a model example template and subsequently give you project a name.
Through a click on the create-Button the required project structure becomes generated containing source folders 
for your models (*src*) and for the code generated based on your models (*src-gen*). The chosen example model 
is created within the src folder which can be used for a straightforward introduction.

<img src="img/php_pw_2.png" alt="PhpStorm Project Wizard 2" height="300" style="max-width:100%;float:right;">


### 2. Create a model ###
eJSL allows you the definition of different parts of a Joomla extension. Starting with the definition 
of a data structure (***entities***) on to its presentation (***pages***) up to the specification of 
Joomla-specific ***extensions***.

While using the text-based editor you get support by the code completion typing ***Ctrl + Space***.

For an easier start we recommend the use of the example instances, provided by the project wizards.  

### 3. Code generation ###
When you save your model, the code generator creates your modelled Joomla extensions within the project's 
src-gen folder. The extensions are installable within Joomla 3.x web sites and don't need any additional 
line of code. However, if you know what you do, you can extend the generated code through individual features. 
But beware: All the code within the src-gen folder becomes COMPLETELY overwritten, when you change your model 
and save it. Therefore we recommend to copy generated extensions to another folder within your project, where 
you can extend them without loosing them after a new code generation. Another and cleaner option is using a 
versioning tool like git to store your individual added code.

## Reverse Engineering ##
In addition, we provide **jext2eJSL** to create eJSL-based models based on existing Joomla 3.x extension packages.
We are currently working on the documentation of jext2eJSL. If you are interested in using the tool, see the current (german) 
documentation [here](https://wiki.thm.de/Reverse-Engineering_(Joomla-Code_zu_eJSL-Instanzmodell)).
Instead of executing the .jar file and using the GUI it is also possible to execute the application via CLI using the following arguments: 
**-m &lt;path to manifest file&gt; -o &lt;output path&gt; -no-gui**