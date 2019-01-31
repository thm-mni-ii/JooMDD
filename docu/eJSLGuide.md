# eJSL Guide #

Contents:

- [eJSL Guide](#ejsl-guide)
  - [Main concepts](#main-concepts)
  - [Use the language](#use-the-language)
    - [General conventions](#general-conventions)
    - [Data modelling](#data-modelling)
      - [Entities](#entities)
      - [Attributes](#attributes)
      - [Types](#types)
      - [References](#references)
    - [Interaction modelling](#interaction-modelling)
    - [Extension modelling](#extension-modelling)
  - [Example models](#example-models)
    - [Conference](#conference)
    - [Shop](#shop)
    - [Generic example model](#generic-example-model)

##  Main concepts ##

The eJSL language is a textual modelling language which is based on three main modelling parts - data, interaction, and extension modelling. Within the next sections, we will illustrate the language features by using the conference eJSL model. This model can be used as example model in the IDE plugins and the web editor to generate a conference component on the fly without any manual refinements. 

**(Please visit the getting started guides for [IDE](GettingStarted.md) and [Web editor](WebEditorGettingStarted.md) to find information regarding the use of the model editors.)

The generated conference component consists of a set of frontend and backend views for participants, talks, rooms, and the conference programme. Once installed, the views allow CRUD operations on the attributes which are illustrated within list tables and details views.

<img src="img/conference_J3.png" alt="J3 Conference Component" style="max-width:100%">

To understand the main concept of the language we take a partial look at the conference model.

```
eJSLModel "Conference" {
    eJSL part: CMS Extension {
        entities {...}    // data modelling
        pages {...}       // interaction modelling
        extensions {...}  //extension modelling 
    }
}

```

In order to generate a deployable Joomla extension, all model parts have to be defined. The entity section contains the whole data modelling of your project. It allows the definition of data entites with typed attributes and references to attributes of other entities.

The entities part of the conference model consits of data entities which are required for a simple conference:

```
entities {
    Entity Participant {                 // Entity definition with identifier
        attributes {
            Attribute name {             // Attribute definition with identifier
                type = Short_Text        // Attribute type
            }
            Attribute affiliation {
                type = Text
            }
            Attribute nationality {
                type = Text
            }
        }
    }
    Entity Talk {
        attributes {
            Attribute title {
                type = Short_Text
            }
            Attribute ^description              // Some identifiers need the '^' prefix, since they are also used as language keywords
                type = Text
            }
            Attribute speaker {
                type = Text
            }
        }
        references {
            Reference {                                          // Reference definition
                EntityAttribute = speaker                        // There ference attribute of the entity
                ReferencedEntity = Participant                   // Identifier of the referenced entity
                ReferencedEntityAttribute = Participant.name     // Referenced attribute
                min = 1                                          // Reference multiplicity (lower)
                max = 1                                         // multiplicity (upper)
            }
        }  
    }
    Entity Room {...}
    Entity Programme {...}
}
```

All **entities** must have unique identifiers to allow cross-references within the model file. For each entitiy, **attributes** can be added. These attributes must be defined with a **type**. The language provides a set of type abstractions like Text, Short_Text, Boolean, Integer, Date, or File. For these types, a sophisticated type mapping is processed during code generation. However, it is also possible to define own types, which can be assigned during an attribute definition. For further reading, see the [type documentation](#types) below.

 All entities must at least contain one attribute. For defined attributes, references can be defined within an entity definition. These references allow relationships between different entities. In the example above, a reference is defined for the `speaker` attribute of the `talk` entity. It references the `name` attribute of the `Participant` entity. It is also to define reference multiplicities (`min`, `max`), whereby min can be '0' or '1' and max can be defines as '1' or '-1' (corresp. to 'n' or '*' ). For a better understanding, see the following class diagram which illustrates the attributes of and references between the conference entities.
        
<img src="img/conferenceModel.png" alt="Conference Model" style="max-width:100%">

The **pages** section represents an abstraction layer for the MVC structure of Joomla components. I.e. an explicit definition of models, views, and controllers is not required.

```
pages {
    IndexPage Participants {
        *Entities Participant
        representation columns = Participant.name, Participant.address, Participant.affiliation
        filters = Participant.name, Participant.affiliation 
        links {
            InternalcontextLink Details {
                target = Participant 
                linked attribute = Participant.name
                linkparameters {
                    Parameter name = *Attribute  "Participant.name"
                }
            }
        }
    }
    DetailsPage Participant {
        *Entities Participant 
        links {
            InternalLink Index {
                target = Participants
                linked attribute = name
            }
        }
    }
    IndexPage Talks {...}
    DetailsPage Talk {...}
    IndexPage Rooms {...}
    DetailsPage Room {...}
    IndexPage Programme {...}
    DetailsPage Session {...}
}
```

```
extensions {
    Component MyConference {
        Manifestation {
            authors {
                Author "John Doe" {
                    authoremail = "john.doe@example.org"
                }
            }
            copyright = "Copyright (C) 2019 All right reserved."
            license = "GNU General Public License"
            version = "1.0.1"
        }
        languages {
            Language de-DE {
            }
            Language en-GB {
            }
        }
        sections {
            Frontend section {
                *Pages {
                    *Page : Participants
                    *Page : Talks
                    *Page : Rooms
                    *Page : Programme
                }
            }
            Backend section {
                *Pages {
                    *Page : Participants
                    *Page : Participant
                    *Page : Talks
                    *Page : Talk
                    *Page : Rooms
                    *Page : Room
                    *Page : Programme
                    *Page : Session
                }
            }
        }
    }
}   
```


## Use the language ##
### General conventions ###
- **Some keywords are reserved in the language**. In this case, the validator throws an validation error, if they are used as identifier. However, you can **use the caret operator (^) as prefix** to allow their usage. The generator then removes the prefix during code compilation to ensure clean identifiers in the generated extension code. <br><br>
  `Attribute template` -> Validation Error <br>
  `Attribute ^template` -> Valid model
- 
### Data modelling ###
#### Entities ####
#### Attributes ####
#### Types ####
#### References ####
### Interaction modelling ###
### Extension modelling ###

## Example models ##
### Conference ###
### Shop ###
### Generic example model ###