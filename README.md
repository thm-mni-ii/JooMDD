<img src="docu/img/logo.svg" alt="JooMDDLogo" style="max-width:100%;float:right;">

[![Build Status](https://travis-ci.com/priefyou/JooMDD.svg?token=e6pz6A4yhPBsVY7Ye4rR&branch=master)](https://travis-ci.com/priefyou/JooMDD)
[![Licence](https://img.shields.io/badge/license-GPL-blue.svg)](https://github.com/priefyou/JooMDD/blob/master/LICENSE)

### What is it?
**JooMDD** provides a set of plugins for model-driven development of Joomla! extension 
packages. 
The current version of JooMDD can be used as plugins within ***Eclipse***, 
***IntelliJ IDEA***, and ***PhpStorm***. Additionally we provide the  [***JooMDD web editor***](https://tinyurl.com/joomdd-web).

### How does it work?
Use the straightforward and text-based eJSL language for abstract definitions of Joomla components, modules, plugins, and templates. To this end, you can use the integrated model editors which come with features like auto completion, syntax highlighting, and live validation.

Based on these models the JooMDD code generator creates installable extension code for Joomla-based web sites. JooMDD supports Joomla versions 3 and 4.  

### What do I get?
Installable extension packages for Joomla 3.x and Joomla 4.

# Quick Start
The easiest and quickest way to try out JooMDD is to use our [***JooMDD web editor***](https://tinyurl.com/joomdd-web).

If you want to use JooMDD within your IDE (Eclipse, PhpStorm, IntelliJ IDEA), you caan use one of the following update sites to install the respective IDE plugins:

- **Eclipse:**  <https://raw.githubusercontent.com/thm-mni-ii/JooMDD/master/updateSites/Eclipse/site.xml>
- **PhpStorm:** 
<https://raw.githubusercontent.com/thm-mni-ii/JooMDD/master/updateSites/PhpStorm/ideaRepository/updatePlugins.xml>
- **IntelliJ IDEA:** 
<https://raw.githubusercontent.com/thm-mni-ii/JooMDD/master/updateSites/IntelliJIDEA/ideaRepository/updatePlugins.xml>


# Documentation
- [Installation Guide](docu/InstallationGuide.md)
- [IDE plugin - Gettting started](docu/GettingStarted.md)
- [Web editor - Getting Started](docu/WebEditorGettingStarted.md)
- [How to use the modelling language](docu/eJSLGuide.md) (in combination with the Joomla-specific generator)

# Contribution
Please visit our [contribution guide](docu/Contribute.md) and feel free to open [new issues]().

A hard requirement for contribution as infrastructure developer is the willingness to work with the Eclipse IDE and at least understand the [Xtend](https://www.eclipse.org/xtend/) language, which is a Java-based dialect. In addition, knowledge in grammar definitions with [Xtext](https://www.eclipse.org/Xtext/) and constraint implementation with the [Object Constraint Language (OCL)](https://www.omg.org/spec/OCL/) is very helpful for contributing to the project.

# Versioning
In this project, we follow the [Semantic Versioning Guidelines](https://semver.org/). I.e. we use major, minor, and patch increments in the form MAJOR.MINOR.PATCH for our plugin versions.

Latest versions of JooMDD plugins can be found in the [releases](https://github.com/priefyou/JooMDD/releases) section.

# Main Contributors
**Dennis Priefer**
- [Twitter](https://twitter.com/Priefyou)
- [Github](https://github.com/priefyou)

**Dieudonne Timma Meyatchie**
- [Github](https://github.com/dieudonnetimma)

**Wolf Rost**
- [Twitter](https://twitter.com/rost_wolf)
- [Github](https://github.com/Wolf-Rost)

# Acknowledgements
The following developers helped to improve the infrastrcuture by giving helpful advice and/or implemented helpful infrastructure parts: 

[@roland-d](https://github.com/roland-d), [@PeterTHM](https://github.com/PeterTHM), [@lpln07](https://github.com/lpln07), [@Schmidie64](https://github.com/Schmidie64), [@eXsiLe95](https://github.com/eXsiLe95), [@andrej-sajenko](https://github.com/andrej-sajenko), [@Max-St](https://github.com/Max-St)

# License

This project is distributed under the GPL (GNU General Public License) version 3. For further information see 
the [License details](https://github.com/priefyou/JooMDD/blob/master/LICENSE).

***
[![II Logo](https://mni.thm.de/images/MNI_content/Forschung/Logos_Institute/ii-01.png)]((https://mni.thm.de/forschung/institute-a-gruppen/ii/ii-ueberblick))

This project is organized by the [Institute for Information Sciences](https://mni.thm.de/forschung/institute-a-gruppen/ii/ii-ueberblick).
