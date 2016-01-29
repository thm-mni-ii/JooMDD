import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by Leon on 21.01.16.
 */
public class InitalSettings implements ProjectComponent{

    private Project project;

    public InitalSettings(Project project){
        this.project = project;
    }

    @Override
    public void projectOpened() {

    }



    @Override
    public void projectClosed() {
        File projectfile = new File(project.getBasePath()+"/"+project.getName()+ ".iml");

        if (projectfile.length() < 300){
            try {
                projectfile.createNewFile();
                FileWriter fw = new FileWriter(project.getBasePath() + "/" +project.getName() + ".iml" );
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(getProjectConfig());
                bw.close();
            }catch (Exception e){

            }
        }
    }

    @Override
    public void initComponent() {
        File src = new File(project.getBasePath() + "/src");
        File src_gen = new File(project.getBasePath() + "/src-gen");
        File model = new File(project.getBasePath() + "/src/model.eJSL");
        try{
            src.mkdir();
            src_gen.mkdir();

           if (!model.exists()) {
                try {
                    model.createNewFile();
                    FileWriter fw = new FileWriter(project.getBasePath() + "/src/model.eJSL");
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(geteJSLExample(eJSLWizardStep.getOption()));
                    bw.close();
                } catch (Exception e) {

                }
            }

        }catch (Exception e){

        }

    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "InitalSettings";
    }


    public String getProjectConfig(){
        return
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<module type=\"eJSLModuleType\" version=\"4\">\n" +
                "  <component name=\"FacetManager\">\n" +
                "    <facet type=\"de.thm.icampus.ejsl.EJSL\" name=\"EJSL\">\n" +
                "      <configuration>\n" +
                "        <option name=\"activated\" value=\"true\" />\n" +
                "        <option name=\"createDirectory\" value=\"true\" />\n" +
                "        <option name=\"outputDirectory\" value=\"src-gen\" />\n" +
                "        <option name=\"overwriteExisting\" value=\"true\" />\n" +
                "        <option name=\"testOutputDirectory\" value=\"src-gen\" />\n" +
                "      </configuration>\n" +
                "    </facet>\n" +
                "  </component>\n" +
                "  <component name=\"NewModuleRootManager\" inherit-compiler-output=\"true\">\n" +
                "    <exclude-output />\n" +
                "    <content url=\"file://$MODULE_DIR$\">\n" +
                "      <sourceFolder url=\"file://$MODULE_DIR$/src\" isTestSource=\"false\" />\n" +
                "      <sourceFolder url=\"file://$MODULE_DIR$/src-gen\" isTestSource=\"false\" generated=\"true\" />\n" +
                "    </content>\n" +
                "    <orderEntry type=\"sourceFolder\" forTests=\"false\" />\n" +
                "  </component>\n" +
                "</module>";
    }



    public String geteJSLExample(Integer option){
        // option 1 = Conference, 2 = model, 3 = Weblinks
        String example = "";
        if (option ==1 ){
            example =
            "eJSLModel \"Conference\" {\n" +
            "\tdatatypes {\n" +
            "\t\tDatatype \"varchar(255) NOT NULL\",\n" +
            "\t\tDatatype \"text NOT NULL\",\n" +
            "\t\tDatatype \"datetime NOT NULL DEFAULT '0000-00-00 00:00:00'\",\n" +
            "\t\tDatatype \"date\",\n" +
            "\t\tDatatype \"textarea\",\n" +
            "\t\tDatatype \"text\",\n" +
            "\t\tDatatype \"editor\"\n" +
            "\t}\n" +
            "\tentities {\n" +
            "\t\tEntity Participant {\n" +
            "\t\t\tattributes {\n" +
            "\t\t\t\tAttribute name {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\tPrimary attribute\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute affiliation {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute nationality {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute address {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"Conference.editor\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tEntity Talk {\n" +
            "\t\t\tattributes {\n" +
            "\t\t\t\tAttribute title {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\tPrimary attribute\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute ^description {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute speaker {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t\treferences {\n" +
            "\t\t\t\tReference {\n" +
            "\t\t\t\t\tAttribute = speaker\n" +
            "\t\t\t\t\t*Entity = Participant\n" +
            "\t\t\t\t\tReference = Participant.name\n" +
            "\t\t\t\t\tlower = 1\n" +
            "\t\t\t\t\tupper = 1\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tEntity Room {\n" +
            "\t\t\tattributes {\n" +
            "\t\t\t\tAttribute roomname {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\tPrimary attribute\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute position {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tEntity AgendaItem {\n" +
            "\t\t\tattributes {\n" +
            "\t\t\t\tAttribute title {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\tPrimary attribute\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute time {\n" +
            "\t\t\t\t\tdbtype = \"datetime NOT NULL DEFAULT \\'0000-00-00 00:00:00\\'\"\n" +
            "\t\t\t\t\thtmltype = \"date\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute talk {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute room {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t\treferences {\n" +
            "\t\t\t\tReference {\n" +
            "\t\t\t\t\tAttribute = talk\n" +
            "\t\t\t\t\t*Entity = Talk\n" +
            "\t\t\t\t\tReference = Talk.title\n" +
            "\t\t\t\t\tlower = 0\n" +
            "\t\t\t\t\tupper = 1\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tReference {\n" +
            "\t\t\t\t\tAttribute = room\n" +
            "\t\t\t\t\t*Entity = Room\n" +
            "\t\t\t\t\tReference = Room.roomname\n" +
            "\t\t\t\t\tlower = 1\n" +
            "\t\t\t\t\tupper = 1\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t}\n" +
            "\tpages {\n" +
            "\t\tIndexPage Participants {\n" +
            "\t\t\t*Entities Participant\n" +
            "\t\t\ttable columns = Participant.name, Participant.address, Participant.affiliation\n" +
            "\t\t\tfilters = Participant.name, Participant.affiliation links {\n" +
            "\t\t\t\tInternalcontextLink Details {\n" +
            "\t\t\t\t\ttarget = Participant linked attribute = Participant.name\n" +
            "\t\t\t\t\tlinkparameters {\n" +
            "\t\t\t\t\t\tParameter name = *Attribute  \"Participant.name\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tDetailsPage Participant {\n" +
            "\t\t\t*Entities Participant links {\n" +
            "\t\t\t\tInternalLink Index {\n" +
            "\t\t\t\t\ttarget = Participants linked attribute = name\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tIndexPage Talks {\n" +
            "\t\t\t*Entities Talk\n" +
            "\t\t\ttable columns = Talk.title, Talk.speaker\n" +
            "\t\t\tfilters = Talk.title, Talk.speaker links {\n" +
            "\t\t\t\tInternalcontextLink Details {\n" +
            "\t\t\t\t\ttarget = Talk linked attribute = Talk.title\n" +
            "\t\t\t\t\tlinkparameters {\n" +
            "\t\t\t\t\t\tParameter title = *Attribute  \"Talk.title\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tDetailsPage Talk {\n" +
            "\t\t\t*Entities Talk links {\n" +
            "\t\t\t\tInternalLink Index {\n" +
            "\t\t\t\t\ttarget = Talks linked attribute = title\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tIndexPage Rooms {\n" +
            "\t\t\t*Entities Room\n" +
            "\t\t\ttable columns = Room.roomname, Room.position\n" +
            "\t\t\tfilters = Room.roomname, Room.position links {\n" +
            "\t\t\t\tInternalcontextLink Details {\n" +
            "\t\t\t\t\ttarget = Room linked attribute = Room.roomname\n" +
            "\t\t\t\t\tlinkparameters {\n" +
            "\t\t\t\t\t\tParameter roomname = *Attribute  \"Room.roomname\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tDetailsPage Room {\n" +
            "\t\t\t*Entities Room links {\n" +
            "\t\t\t\tInternalLink Index {\n" +
            "\t\t\t\t\ttarget = Rooms linked attribute = roomname\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tIndexPage Agendaitems {\n" +
            "\t\t\t*Entities AgendaItem\n" +
            "\t\t\ttable columns = AgendaItem.room, AgendaItem.talk, AgendaItem.time, AgendaItem.title\n" +
            "\t\t\tfilters = AgendaItem.room, AgendaItem.talk, AgendaItem.time, AgendaItem.title links {\n" +
            "\t\t\t\tInternalcontextLink Details {\n" +
            "\t\t\t\t\ttarget = Agendaitem linked attribute = AgendaItem.title\n" +
            "\t\t\t\t\tlinkparameters {\n" +
            "\t\t\t\t\t\tParameter title = *Attribute  \"AgendaItem.title\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tDetailsPage Agendaitem {\n" +
            "\t\t\t*Entities AgendaItem links {\n" +
            "\t\t\t\tInternalLink Index {\n" +
            "\t\t\t\t\ttarget = Agendaitems linked attribute = AgendaItem.title\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t}\n" +
            "\textensions {\n" +
            "\t\tComponent JoomlaDays {\n" +
            "\t\t\tManifestation {\n" +
            "\t\t\t\tauthors {\n" +
            "\t\t\t\t\tAuthor \"Dennis Priefer\" {\n" +
            "\t\t\t\t\t\tauthoremail = \"dennis.priefer@mni.thm.de\"\n" +
            "\t\t\t\t\t\tauthorurl = \"https://www.priefer.net\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tcopyright = \"Copyright (C) 20053 - 2014 Open Source Matters, Inc. All right reserved.\"\n" +
            "\t\t\t\tlicense = \"GNU General x Public License version 22 orz later; see LICENSE.txt\"\n" +
            "\t\t\t}\n" +
            "\t\t\tlanguages {\n" +
            "\t\t\t\tLanguage de-DE {\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tLanguage en-GB {\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t\tsections {\n" +
            "\t\t\t\tFrontend section {\n" +
            "\t\t\t\t\t*Pages {\n" +
            "\t\t\t\t\t\t*Page: Participants\n" +
            "\t\t\t\t\t\t*Page: Talks\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tBackend section {\n" +
            "\t\t\t\t\t*Pages {\n" +
            "\t\t\t\t\t\t*Page: Participants\n" +
            "\t\t\t\t\t\t*Page: Participant\n" +
            "\t\t\t\t\t\t*Page: Talks\n" +
            "\t\t\t\t\t\t*Page: Talk\n" +
            "\t\t\t\t\t\t*Page: Rooms\n" +
            "\t\t\t\t\t\t*Page: Room\n" +
            "\t\t\t\t\t\t*Page: Agendaitems\n" +
            "\t\t\t\t\t\t*Page: Agendaitem\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";
        }else if (option ==2){
            example =
            "/*\n" +
            " * This is an example model with the needed model elements entities, pages, and extensions\n" +
            " * '..' are just placeholders for names and should be replaced in concrete models!\n" +
            " */\n" +
            "eJSLModel \"name of the model\" {\n" +
            "\t datatypes {\n" +
            "\t\tDatatype \"varchar(255) NOT NULL\",\n" +
            "\t\tDatatype \"text NOT NULL\",\n" +
            "\t\tDatatype \"datetime NOT NULL DEFAULT '0000-00-00 00:00:00'\",\n" +
            "\t\tDatatype \"date\",\n" +
            "\t\tDatatype \"textarea\",\n" +
            "\t\tDatatype \"text\",\n" +
            "\t\tDatatype \"editor\"\t\t\n" +
            "\t}\n" +
            "\tentities { \n" +
            "\t\tEntity Person {\n" +
            "\t\t\tattributes {\n" +
            "\t\t\t\tAttribute Vorname {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\tPrimary attribute\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute Name {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"editor\"\n" +
            "\t\t\t\t\tPrimary attribute\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tEntity entity2 {\n" +
            "\t\t\tattributes { \n" +
            "\t\t\t\tAttribute attribute1 {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\tPrimary attribute\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute attribute2 {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t\treferences {\n" +
            "\t\t\t\tReference {\n" +
            "\t\t\t\t\tAttribute = attribute2\n" +
            "\t\t\t\t\t*Entity = Person\n" +
            "\t\t\t\t\tReference = Person.Name\n" +
            "\t\t\t\t\tlower = 1\n" +
            "\t\t\t\t\tupper = 1\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t}\n" +
            "\n" +
            "\tpages {\n" +
            "\t\tIndexPage IndexPage1 {\n" +
            "\t\t\t*Entities Person\n" +
            "\t\t\ttable columns = Person.Vorname, \n" +
            "\t\t\t\tPerson.Name\n" +
            "\t\tfilters = Person.Name ,Person.Vorname\n" +
            "\t\tlinks {\n" +
            "\t\t\tInternalLink Test {\n" +
            "\t\t\t\ttarget = * DetailsPage1\n" +
            "\t\t\t\tlinked attribute = * Person.Name\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\t}\n" +
            "\t\tDetailsPage DetailsPage1 {\n" +
            "\t\t\t*Entities Person\n" +
            "\t\t\tlinks {\n" +
            "\t\t\t\tInternalLink forall {\n" +
            "\t\t\t\t\ttarget = * IndexPage1\n" +
            "\t\t\t\t\tlinked attribute = * Person.Name\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tIndexPage IndexPage2 {\n" +
            "\t\t\t*Entities Person\n" +
            "\t\t\ttable columns =  Person.Name, Person.Vorname\n" +
            "\t\tfilters = Person.Name , Person.Vorname\n" +
            "\t\t\t\tlinks {\n" +
            "\t\t\t\t\tInternalLink Link {\n" +
            "\t\t\t\t\t\ttarget = * DetailsPage1\n" +
            "\t\t\t\t\t\tlinked attribute = * Person.Name\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\t\n" +
            "\t\t}\n" +
            "\t\tDetailsPage DetailsPage2 {\n" +
            "\t\t\t*Entities entity2\n" +
            "\t\t\tlinks {\n" +
            "\t\t\t\tInternalLink forall {\n" +
            "\t\t\t\t\ttarget = * IndexPage2\n" +
            "\t\t\t\t\tlinked attribute = * entity2.attribute1\n" +
            "\t\t\t\t}\n" +
            "\t\t\t} \n" +
            "\t\t}\n" +
            "\t}\n" +
            "\textensions {\n" +
            "\t\tComponent Component1 {\n" +
            "\t\t\tManifestation {\n" +
            "\t\t\t\tauthors {\n" +
            "\t\t\t\t\tAuthor \"forename surname\" {\n" +
            "\t\t\t\t\t\tauthoremail = \"name@provider.domains\"\n" +
            "\t\t\t\t\t\tauthorurl = \"www.anyurl.com\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t} \n" +
            "\t\t\t\tcreationdate=20.04.1222\t\t\t\n" +
            "\t\t\t\tcopyright = \"Copyright\"\n" +
            "\t\t\t\tlicense = \"License\"\n" +
            "\t\t\t}\n" +
            "\t\t\tlanguages {\n" +
            "\t\t\t\tLanguage de-DE {\n" +
            "\t   \t\t\t}\n" +
            "\t\t\t\tLanguage en-GB {\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t\tsections {\n" +
            "\t\t\t\tFrontend section{ \n" +
            "\t\t\t\t   *Pages {\n" +
            "\t\t\t\t    *Page: DetailsPage1 from: Component1.backend\n" +
            "\t\t\t\t    *Page: IndexPage1\n" +
            "\t\t\t\t   }\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tBackend section  {\n" +
            "\t\t\t\t\t*Pages {\n" +
            "\t\t\t\t\t*Page: IndexPage1\n" +
            "\t\t\t\t   \t*Page:  DetailsPage1 \n" +
            "\t\t\t\t   \t*Page:  IndexPage2\n" +
            "\t\t\t\t   \t*Page:  DetailsPage2\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tModule mod1 {\n" +
            "\t\t\tManifestation {\n" +
            "\t\t\t\tauthors {\n" +
            "\t\t\t\t\tAuthor \"Tim H�user\"\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\tauthoremail = \"tim.haeuser@mni.thm.des\"\n" +
            "\t\t\t\t\t\tauthorurl = \"www.clox-fitnesss.de\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t\tAuthor \"Basti Felsing\"\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\tauthoremail = \"sebastian.felsing@mni.thm.de\"\n" +
            "\t\t\t\t\t\tauthorurl = \"www.google.de\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t\t*Page: IndexPage1 \n" +
            "\t\t}\n" +
            "\t\tExtension package Test{\n" +
            "\t\t\tManifestation {\n" +
            "\t\t\t\tauthors {\n" +
            "\t\t\t\t\tAuthor \"Klaus Merller\"\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\tauthoremail = \"Klaus.Maaa@mni.thm.de\"\n" +
            "\t\t\t\t\t\tauthorurl = \"www.clox-fitness.de\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t\tAuthor \"Max Mustermann\"\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\tauthoremail = \"Max.Mustermann@mni.thm.de\"\n" +
            "\t\t\t\t\t\tauthorurl = \"www.joomla.de\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\t}extensions {\n" +
            "\t\t\t\t\tModule mod1 {\n" +
            "\t\t\t\t\t\tManifestation {\n" +
            "\t\t\t\t\t\t\tauthors {\n" +
            "\t\t\t\t\t\t\t\tAuthor \"Klaus Muller\"\n" +
            "\t\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\tauthoremail = \"Klaus.Mullerr@mni.thm.de\"\n" +
            "\t\t\t\t\t\t\t\t\tauthorurl = \"www.clox-fitness.de\"\n" +
            "\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t\tAuthor \"Max Mustermann\"\n" +
            "\t\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\tauthoremail = \"Max.Mustermann@mni.thm.de\"\n" +
            "\t\t\t\t\t\t\t\t\tauthorurl = \"www.joomla.de\"\n" +
            "\t\t\t\t\t\t\t\t}\t\t\t\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\tcreationdate = 23.06.1337\n" +
            "\t\t\t\t\t\t\tcopyright=\"MDD SS15\"\n" +
            "\t\t\t\t\t\t\tdescription = \"Master\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t*Page: IndexPage2 from: Component1 .backend\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t\tComponent Component1 {\n" +
            "\t\t\tManifestation {\n" +
            "\t\t\t\tauthors {\n" +
            "\t\t\t\t\tAuthor \"forename surname\" {\n" +
            "\t\t\t\t\t\tauthoremail = \"name@provider.domaiss\"\n" +
            "\t\t\t\t\t\tauthorurl = \"www.anyurl.com\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t} \n" +
            "\t\t\t\tcreationdate=20.04.1222\t\t\t\n" +
            "\t\t\t\tcopyright = \"Copyright\"\n" +
            "\t\t\t\tlicense = \"License\"\n" +
            "\t\t\t}\n" +
            "\t\t\tlanguages {\n" +
            "\t\t\t\tLanguage de-DE {\n" +
            "\t   \t\t\t}\n" +
            "\t\t\t\tLanguage en-GB {\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t\t\tsections {\n" +
            "\t\t\t\tFrontend section {\n" +
            "\t\t\t\t   *Pages {\n" +
            "\t\t\t\t   \t*Page: IndexPage1\n" +
            "\t\t\t\t   \t*Page:  DetailsPage1 \n" +
            "\t\t\t\t   }\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tBackend section {\n" +
            "\t\t\t\t\t*Pages {\n" +
            "\t\t\t\t\t*Page: IndexPage1\n" +
            "\t\t\t\t   \t*Page:  DetailsPage1 \n" +
            "\t\t\t\t   \t*Page:  IndexPage2\n" +
            "\t\t\t\t   \t*Page: DetailsPage2\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t}\n" +
            "   } \n" +
            "  }\n" +
            "}\n";
        } else if (option == 3){
            example =
            "eJSLModel \"Weblinks\" {\n" +
            "\tdatatypes {\n" +
            "\t\tDatatype \"String\",\n" +
            "\t\tDatatype \"int(11) NOT NULL\",\n" +
            "\t\tDatatype \"checkbox\",\n" +
            "\t\tDatatype \"varchar(255) NOT NULL\",\n" +
            "\t\tDatatype \"text NOT NULL\",\n" +
            "\t\tDatatype \"boolean\",\n" +
            "\t\tDatatype \"datetime NOT NULL DEFAULT '0000-00-00 00:00:00'\",\n" +
            "\t\tDatatype \"date\",\n" +
            "\t\tDatatype \"tinyint(1) NOT NULL DEFAULT '0'\",\n" +
            "\t\tDatatype \"char(7) NOT NULL\",\n" +
            "\t\tDatatype \"category\",\n" +
            "\t\tDatatype \"list\",\n" +
            "\t\tDatatype \"textarea\",\n" +
            "\t\tDatatype \"spacer\",\n" +
            "\t\tDatatype \"radio\",\n" +
            "\t\tDatatype \"media\",\n" +
            "\t\tDatatype \"componentlayout\",\n" +
            "\t\tDatatype \"rules\",\n" +
            "\t\tDatatype \"text\",\n" +
            "\t\tDatatype \"modulelayout\",\n" +
            "\t\tDatatype \"hidden\",\n" +
            "\t\tDatatype \"editor\",\n" +
            "\t\tDatatype \"TEXTAREA\"\n" +
            "\t\t\n" +
            "\t}\n" +
            "\tglobalparameters {\n" +
            "\t// Weblink Configuration-------------------------------------------------------------------------\n" +
            "\t\tParameter _target {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tdefaultvalue = \"0\"\n" +
            "\t\t\tlabel = \"FIELD_TARGET_LABEL\"\n" +
            "\t\t\tdescription = \"FIELD_TARGET_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter save_history {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"0\"\n" +
            "\t\t\tlabel = \"JGLOBAL_SAVE_HISTORY_OPTIONS_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_SAVE_HISTORY_OPTIONS_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter history_limit {\n" +
            "\t\t\ttype = \"text\"\n" +
            "\t\t\tdefaultvalue = \"5\"\n" +
            "\t\t\tlabel = \"JGLOBAL_HISTORY_LIMIT_OPTIONS_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_HISTORY_LIMIT_OPTIONS_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter count_clicks {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"-1\"\n" +
            "\t\t\tlabel = \"FIELD_COUNTCLICKS_LABEL\"\n" +
            "\t\t\tdescription = \"FIELD_COUNTCLICKS_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter icons {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"FIELD_ICON_LABEL\"\n" +
            "\t\t\tdescription = \"FIELD_ICON_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter link_icons {\n" +
            "\t\t\ttype = \"media\"\n" +
            "\t\t\tlabel = \"FIELD_CONFIG_ICON_LABEL\"\n" +
            "\t\t\tdescription = \"FIELD_CONFIG_ICON_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter float_first {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tlabel = \"FLOAT_LABEL\"\n" +
            "\t\t\tdescription = \"FLOAT_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter float_second {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tlabel = \"FLOAT_LABEL\"\n" +
            "\t\t\tdescription = \"FLOAT_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter show_tags {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"FIELD_SHOW_TAGS_LABEL\"\n" +
            "\t\t\tdescription = \"FIELD_SHOW_TAGS_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\t//Category Configuration-----------------------------------------------------------------------------------\n" +
            "\t\tParameter category_layout {\n" +
            "\t\t\ttype = \"componentlayout\"\n" +
            "\t\t\tlabel = \"JGLOBAL_FIELD_LAYOUT_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_FIELD_LAYOUT_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter show_category_title {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"JGLOBAL_SHOW_CATEGORY_TITLE\"\n" +
            "\t\t\tdescription = \"JGLOBAL_SHOW_CATEGORY_TITLE_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter show_description {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"JGLOBAL_SHOW_CATEGORY_DESCRIPTION_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_SHOW_CATEGORY_DESCRIPTION_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter show_description_image {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"JGLOBAL_SHOW_CATEGORY_IMAGE_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_SHOW_CATEGORY_IMAGE_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter maxLevel {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tdefaultvalue = \"-1\"\n" +
            "\t\t\tlabel = \"JGLOBAL_MAXIMUM_CATEGORY_LEVELS_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_MAXIMUM_CATEGORY_LEVELS_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\tParameter show_empty_categories {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"0\"\n" +
            "\t\t\tlabel = \"JGLOBAL_SHOW_EMPTY_CATEGORIES_LABEL\"\n" +
            "\t\t\tdescription = \"SHOW_EMPTY_CATEGORIES_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\tParameter show_subcat_desc {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"JGLOBAL_SHOW_SUBCATEGORIES_DESCRIPTION_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_SHOW_SUBCATEGORIES_DESCRIPTION_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter show_cat_num_links {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"FIELD_CONFIG_CAT_SHOWNUMBERS_LABEL\"\n" +
            "\t\t\tdescription = \"FIELD_CONFIG_CAT_SHOWNUMBERS_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\tParameter show_cat_tags {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"FIELD_SHOW_CAT_TAGS_LABEL\"\n" +
            "\t\t\tdescription = \"FIELD_SHOW_CAT_TAGS_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\t//Categories Configuration-------------------------------------------------------------------------------\t\n" +
            "\t\tParameter show_base_description {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tlabel = \"JGLOBAL_FIELD_SHOW_BASE_DESCRIPTION_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_FIELD_SHOW_BASE_DESCRIPTION_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\tParameter categories_description {\n" +
            "\t\t\ttype = \"textarea\"\n" +
            "\t\t\tlabel = \"JGLOBAL_FIELD_CATEGORIES_DESC_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_FIELD_CATEGORIES_DESC_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\tParameter maxLevelcat {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tdefaultvalue = \"-1\"\n" +
            "\t\t\tlabel = \"JGLOBAL_MAXIMUM_CATEGORY_LEVELS_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_MAXIMUM_CATEGORY_LEVELS_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter show_empty_categories_cat {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tlabel = \"JGLOBAL_SHOW_EMPTY_CATEGORIES_LABEL\"\n" +
            "\t\t\tdescription = \"SHOW_EMPTY_CATEGORIES_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\tParameter show_subcat_desc_cat {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tlabel = \"JGLOBAL_SHOW_SUBCATEGORIES_DESCRIPTION_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_SHOW_SUBCATEGORIES_DESCRIPTION_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter show_cat_num_links_cat {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tlabel = \"FIELD_CONFIG_CAT_SHOWNUMBERS_LABEL\"\n" +
            "\t\t\tdescription = \"FIELD_CONFIG_CAT_SHOWNUMBERS_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\t//List Layout Configuration------------------------------------------------------------------------\n" +
            "\t\tParameter filter_field {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"JGLOBAL_FILTER_FIELD_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_FILTER_FIELD_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\tParameter show_pagination_limit {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"JGLOBAL_DISPLAY_SELECT_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_DISPLAY_SELECT_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\tParameter show_headings {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"JGLOBAL_SHOW_HEADINGS_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_SHOW_HEADINGS_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\tParameter show_link_description {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"FIELD_CONFIG_LINKDESCRIPTION_LABEL\"\n" +
            "\t\t\tdescription = \"FIELD_CONFIG_LINKDESCRIPTION_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\tParameter show_link_hits {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"JGLOBAL_HITS\"\n" +
            "\t\t\tdescription = \"FIELD_CONFIG_HITS_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter show_pagination {\n" +
            "\t\t\ttype = \"list\"\n" +
            "\t\t\tdefaultvalue = \"2\"\n" +
            "\t\t\tlabel = \"JGLOBAL_PAGINATION_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_PAGINATION_DESC\"\n" +
            "\t\t}\n" +
            "\t\tParameter show_pagination_results {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"JGLOBAL_PAGINATION_RESULTS_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_PAGINATION_RESULTS_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\t//Integration Configuration------------------------------------------------------------------------------\n" +
            "\t\tParameter show_feed_link {\n" +
            "\t\t\ttype = \"radio\"\n" +
            "\t\t\tdefaultvalue = \"1\"\n" +
            "\t\t\tlabel = \"JGLOBAL_SHOW_FEED_LINK_LABEL\"\n" +
            "\t\t\tdescription = \"JGLOBAL_SHOW_FEED_LINK_DESC\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t\t//Permission Configuration----------------------------------------------------------------------------------\n" +
            "\t\tParameter rules {\n" +
            "\t\t\ttype = \"rules\"\n" +
            "\t\t\tlabel = \"JCONFIG_PERMISSIONS_LABEL\"\n" +
            "\t\t}\n" +
            "\n" +
            "\t}\n" +
            "\tparametergroups { \n" +
            "\t\tParameterGroup Weblink{\n" +
            "\t\t\tParameters {\n" +
            "\t\t\t\t _target save_history history_limit count_clicks icons link_icons float_first float_second show_tags \n" +
            "\t\t\t\t\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tParameterGroup Category{\n" +
            "\t\t\tParameters {\n" +
            "category_layout show_category_title show_description show_description_image maxLevel  show_empty_categories show_subcat_desc show_cat_num_links show_cat_tags \n" +
            "\t\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tParameterGroup Categories{\n" +
            "\t\t\tParameters {\n" +
            "   show_base_description categories_description maxLevelcat show_empty_categories_cat show_subcat_desc_cat show_cat_num_links_cat \n" +
            "\t\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tParameterGroup List_Layout{\n" +
            "\t\t\tParameters {\n" +
            "   filter_field show_pagination_limit show_headings show_link_description show_link_hits show_pagination show_pagination_results\n" +
            "   \t\t\t}\n" +
            "\t\t}\n" +
            "\t\tParameterGroup Integration_Configuration{\n" +
            "\t\t\tParameters {\n" +
            "\t\t\t\tshow_feed_link \n" +
            "   \t\t\t}\n" +
            "\t\t}\n" +
            "\t\tParameterGroup Permission_Configuration{\n" +
            "\t\t\tParameters {\n" +
            "\t\t\t\trules\n" +
            "   \t\t\t}\n" +
            "\t\t}\n" +
            "\t\t\n" +
            "\t}\n" +
            "\tentities {\n" +
            "\t\tEntity Rettred222 {\n" +
            "\t\t\tattributes {\n" +
            "\t\t\t\t\n" +
            "\t\t\t\t\tAttribute Title {\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute alias{\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute url{\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute hits{\n" +
            "\t\t\t\t\tdbtype = \"int(11) NOT NULL\"\n" +
            "\t\t\t\t\t htmltype = int\n" +
            "\t\t\t\t\t }\n" +
            "\t\t\t\tAttribute catid{\n" +
            "\t\t\t\t\t dbtype = \"int(11) NOT NULL\"\n" +
            "\t\t\t\t\t htmltype = int\n" +
            "\t\t\t\t\t }\n" +
            "\t\t\t\tAttribute sid{\n" +
            "\t\t\t\t\tdbtype = \"int(11) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = int\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute descriptions{\n" +
            "\t\t\t\t\tdbtype = \"text NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute date{\n" +
            "\t\t\t\t\tdbtype = \"datetime NOT NULL DEFAULT \\'0000-00-00 00:00:00\\'\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t\n" +
            "\t\t\t\tAttribute archived{\n" +
            "\t\t\t\t\tdbtype = \"tinyint(1) NOT NULL DEFAULT '0'\"\n" +
            "\t\t\t\t\thtmltype = int\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute approved{\n" +
            "\t\t\t\t\tdbtype = \"tinyint(1) NOT NULL DEFAULT '0'\"\n" +
            "\t\t\t\t\thtmltype = int\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute access{\n" +
            "\t\t\t\t\tdbtype = \"int(11) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = int\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute params{\n" +
            "\t\t\t\t\tdbtype = \"text NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute language{\n" +
            "\t\t\t\t\tdbtype = \"char(7) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute created{\n" +
            "\t\t\t\t\tdbtype = \"datetime NOT NULL DEFAULT \\'0000-00-00 00:00:00\\'\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t\n" +
            "\t\t\t\tAttribute created_by_alias{\n" +
            "\t\t\t\t\tdbtype = \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute modified{\n" +
            "\t\t\t\t\tdbtype = \"datetime NOT NULL DEFAULT \\'0000-00-00 00:00:00\\'\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute modified_by{\n" +
            "\t\t\t\t\tdbtype = \"int(11) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = int\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute metakey{\n" +
            "\t\t\t\t\tdbtype = \"text NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute metadesc{\n" +
            "\t\t\t\t\t dbtype = \"text NOT NULL\"\n" +
            "\t\t\t\t\t htmltype = \"text\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tAttribute metadata{\n" +
            "\t\t\t\t\tdbtype = \"text NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute featured{\n" +
            "\t\t\t\t\tdbtype = \"tinyint(1) NOT NULL DEFAULT '0'\"\n" +
            "\t\t\t\t\thtmltype = int\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute xreference{\n" +
            "\t\t\t\t\tdbtype= \"varchar(255) NOT NULL\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute publish_up{\n" +
            "\t\t\t\t\tdbtype = \"datetime NOT NULL DEFAULT \\'0000-00-00 00:00:00\\'\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\tAttribute publish_down{\n" +
            "\t\t\t\t\tdbtype = \"datetime NOT NULL DEFAULT \\'0000-00-00 00:00:00\\'\"\n" +
            "\t\t\t\t\thtmltype = \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\t\n" +
            "\t}\n" +
            "\n" +
            "\tpages {\n" +
            "\t\tIndexPage Weblinks {\n" +
            "\t\t\t*Entities \"Rettred222\" \n" +
            "\t\t\tfilters Rettred222.Title ,Rettred222.alias ,Rettred222.created \n" +
            "\t\t}\n" +
            "\t\tDetailsPage Weblink {\n" +
            "\t\t\t*Entities \"Rettred222\" \n" +
            "\t\t}\n" +
            "\t\tIndexPage Categories {\n" +
            "\t\t\t*Entities \"Rettred222\"\n" +
            "\t\t\t*ParameterGroups Categories , Category ,Weblink ,List_Layout \n" +
            "\t\t\tlocalparameters {\n" +
            "\t\t\t\tParameter id {\n" +
            "\t\t\t\t\ttype = \"category\"\n" +
            "\t\t\t\t\tlabel = \"JGLOBAL_FIELD_CATEGORIES_CHOOSE_CATEGORY_LABEL\"\n" +
            "\t\t\t\t\tdescription = \"JGLOBAL_FIELD_CATEGORIES_CHOOSE_CATEGORY_DESC\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t\tParameter spacer1 {\n" +
            "\t\t\t\t\ttype = \"spacer\"\n" +
            "\t\t\t\t\tlabel = \"JGLOBAL_SUBSLIDER_DRILL_CATEGORIES_LABEL\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tParameter spacer2 {\n" +
            "\t\t\t\t\ttype = \"spacer\"\n" +
            "\t\t\t\t\tlabel = \"JGLOBAL_SUBSLIDER_DRILL_CATEGORIES_LABEL\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\t\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t}\n" +
            "\t\tIndexPage Category {\n" +
            "\t\t\t*Entities \"Rettred222\"\n" +
            "\t\t\t*ParameterGroups Category , Weblink\n" +
            "\t\t\t\n" +
            "\t\t\tlocalparameters {\n" +
            "\t\t\t\tParameter id {\n" +
            "\t\t\t\t\ttype = \"category\"\n" +
            "\t\t\t\t\tlabel = \"JGLOBAL_FIELD_CATEGORIES_CHOOSE_CATEGORY_LABEL\"\n" +
            "\t\t\t\t\tdescription = \"JGLOBAL_FIELD_CATEGORIES_CHOOSE_CATEGORY_DESC\"\n" +
            "\t\t\t\t}\n" +
            "\n" +
            "\t\t\t\tParameter spacer1 {\n" +
            "\t\t\t\t\ttype = \"spacer\"\n" +
            "\t\t\t\t\tlabel = \"JGLOBAL_SUBSLIDER_DRILL_CATEGORIES_LABEL\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tParameter spacer2 {\n" +
            "\t\t\t\t\ttype = \"spacer\"\n" +
            "\t\t\t\t\tlabel = \"JGLOBAL_SUBSLIDER_DRILL_CATEGORIES_LABEL\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t\t\n" +
            "\t\t}\n" +
            "\t\tDetailsPage Form {\n" +
            "\t\t\t*Entities \"Rettred222\"\n" +
            "\t\t\t*ParameterGroups Weblink\n" +
            "\t\t\t\n" +
            "\t\t}\n" +
            "\t\tIndexPage ModulPage {\n" +
            "\t\t\t*Entities \"Rettred222\"\n" +
            "\t\t\tlocalparameters{\n" +
            "\t\t\t\tParameter catid {\n" +
            "\t\t\t\t\ttype=\"category\"\n" +
            "\t\t\t\t\tlabel=\"JCATEGORY\"\n" +
            "\t\t\t\t\tdescription=\"MOD_WEBLINKS_FIELD_CATEGORY_DESCsss\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t  Parameter count {\n" +
            "\t\t\t  \t\ttype=\"text\"\n" +
            "\t\t\t  \t\tdefaultvalue=\"5\"\n" +
            "\t\t\t\t\tlabel=\"MOD_WEBLINKS_FIELD_COUNT_LABEL\"\n" +
            "\t\t\t\t\tdescription=\"MOD_WEBLINKS_FIELD_COUNT_DESC\"\n" +
            "\t\t\t  }\n" +
            "\t\t\t Parameter ordering {\n" +
            "\t\t\t \t\ttype=\"list\"\n" +
            "\t\t\t\t\tdefaultvalue=\"title\"\n" +
            "\t\t\t\t\tlabel=\"MOD_WEBLINKS_FIELD_ORDERING_LABEL\"\n" +
            "\t\t\t\t\tdescription=\"MOD_WEBLINKS_FIELD_ORDERING_DESC\"\n" +
            "\t\t\t }\n" +
            "\t\t\t Parameter direction {\n" +
            "\t\t\t \t\ttype=\"list\"\n" +
            "\t\t\t\t\tdefaultvalue=\"asc\"\n" +
            "\t\t\t\t\tlabel=\"MOD_WEBLINKS_FIELD_ORDERDIRECTION_LABEL\"\n" +
            "\t\t\t\t\tdescription=\"MOD_WEBLINKS_FIELD_ORDERDIRECTION_DESC\"\n" +
            "\t\t\t }\n" +
            "\t\t\t Parameter _target {\n" +
            "\t\t\t\t\ttype=\"list\"\n" +
            "\t\t\t\t\tdefaultvalue=\"3\"\n" +
            "\t\t\t\t\tlabel=\"MOD_WEBLINKS_FIELD_TARGET_LABEL\"\n" +
            "\t\t\t\t\tdescription=\"MOD_WEBLINKS_FIELD_TARGET_DESC\"\n" +
            "\t\t\t }\n" +
            "\t\t\t Parameter follow {\n" +
            "\t\t\t \t\ttype=\"list\"\n" +
            "\t\t\t\t\tdefaultvalue=\"0\"\n" +
            "\t\t\t\t\tlabel=\"MOD_WEBLINKS_FIELD_FOLLOW_LABEL\"\n" +
            "\t\t\t\t\tdescription=\"MOD_WEBLINKS_FIELD_FOLLOW_DESC\"\n" +
            "\t\t\t }\n" +
            "\t\t\t Parameter _description {\n" +
            "\t\t\t \t\ttype=\"radio\"\n" +
            "\t\t\t\t\tdefaultvalue=\"0\"\n" +
            "\t\t\t\t\tlabel=\"MOD_WEBLINKS_FIELD_DESCRIPTION_LABEL\"\n" +
            "\t\t\t\t\tdescription=\"MOD_WEBLINKS_FIELD_DESCRIPTION_DESC\"\n" +
            "\t\t\t } \n" +
            "\t\t\t Parameter hits {\n" +
            "\t\t\t \t\ttype=\"radio\"\n" +
            "\t\t\t\t\tdefaultvalue=\"0\"\n" +
            "\t\t\t\t\tlabel=\"MOD_WEBLINKS_FIELD_HITS_LABEL\"\n" +
            "\t\t\t\t\tdescription=\"MOD_WEBLINKS_FIELD_HITS_DESC\"\n" +
            "\t\t\t }\n" +
            "\t\t\t Parameter count_clicks {\n" +
            "\t\t\t \t\ttype=\"list\"\n" +
            "\t\t\t\t\tdefaultvalue=\"0\"\n" +
            "\t\t\t\t\tlabel=\"MOD_WEBLINKS_FIELD_COUNTCLICKS_LABEL\"\n" +
            "\t\t\t\t\tdescription=\"MOD_WEBLINKS_FIELD_COUNTCLICKS_DESC\"\n" +
            "\t\t\t }\n" +
            "\t\t\t Parameter layout {\n" +
            "\t\t\t \t\ttype=\"modulelayout\"\n" +
            "\t\t\t\t\tlabel=\"JFIELD_ALT_LAYOUT_LABEL\"\n" +
            "\t\t\t\t\tdescription=\"JFIELD_ALT_MODULE_LAYOUT_DESC\"\n" +
            "\t\t\t }\n" +
            "\t\t\t Parameter moduleclass_sfx {\n" +
            "\t\t\t \t\ttype=\"textarea\" \n" +
            "\t\t\t\t\tlabel=\"COM_MODULES_FIELD_MODULECLASS_SFX_LABEL\"\n" +
            "\t\t\t\t\tdescription=\"COM_MODULES_FIELD_MODULECLASS_SFX_DESC\"\n" +
            "\t\t\t }\n" +
            "\t\t\t Parameter cache {\n" +
            "\t\t\t \t\ttype=\"list\"\n" +
            "\t\t\t\t\tdefaultvalue=\"1\"\n" +
            "\t\t\t\t\tlabel=\"COM_MODULES_FIELD_CACHING_LABEL\"\n" +
            "\t\t\t\t\tdescription=\"COM_MODULES_FIELD_CACHING_DESC\"\n" +
            "\t\t\t }\n" +
            "\t\t\t Parameter cache_time {\n" +
            "\t\t\t \t\ttype=\"text\"\n" +
            "\t\t\t\t\tdefaultvalue=\"900\"\n" +
            "\t\t\t\t\tlabel=\"COM_MODULES_FIELD_CACHE_TIME_LABEL\"\n" +
            "\t\t\t\t\tdescription=\"COM_MODULES_FIELD_CACHE_TIME_DESC\"\n" +
            "\t\t\t }\n" +
            "\t\t\t Parameter cachemode {\n" +
            "\t\t\t \ttype=\"hidden\"\n" +
            "\t\t\t\tdefaultvalue=\"static\"\n" +
            "\t\t\t }\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\n" +
            "\t}\n" +
            "\n" +
            "\textensions {\n" +
            "\t\tComponent myexter222 {\n" +
            "\t\t\tManifestation {\n" +
            "\t\t\t\tauthors {\n" +
            "\t\t\t\t\tAuthor \"Joomla! Project\" {\n" +
            "\t\t\t\t\t\tauthoremail = \"admin@joomlaf.org\"\n" +
            "\t\t\t\t\t\tauthorurl = \"www.joomla.org\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tcopyright = \"Copyright (C) 20053 - 2014 Open Source Matters, Inc. All rights reserved.\"\n" +
            "\t\t\t\tlicense = \"GNU General x Publics License version 2 or later; see LICENSE.txt\"\n" +
            "\t\t\t}\n" +
            "\t\t\tlanguages {\n" +
            "\t\t\t\t\n" +
            "\t\t\t\tLanguage de-DE {\n" +
            "\t\t\t\t\tkeyvaluepairs {\n" +
            "\t\t\t\t\t\tKey COM_WEBLINKS=\"Weblinks\"\n" +
            "\t\t\t\t\t\tKey ACCESS_HEADING=\"Zugriff\"\n" +
            "\t\t\t\t\t\tKey BATCH_OPTIONS=\"Mehrerde ausgewähltedn Weblinks gleichzeitig bearbeiten (Stapelverarbeitung)\"\n" +
            "\t\t\t\t\t\tKey BATCH_TIP=\"Wenn ein Weblink kopiertd/verschoben wird, so werden auch alle zusätzlich ausgewählten Aktionen auf den kopierten/verschobenen Weblink angewendet. Ansonsten werden die Aktionen nur auf die ausgewählten Weblinks angewendet.\"\n" +
            "\t\t\t\t\t\tKey CATEGORIES_DESC=\"Diese Einstelslungen gelten für die Optionen der Weblinks-Kategorien, sofern sie nicht für einen spezifischen Menüpunkt geändert wurden.\"\n" +
            "\t\t\t\t\t\tKey CATEGORY_DESC=\"Diese Einstellungen gelten für die Optionen einer Weblinks-Kategorie, sofern sie nicht für einen spezifischen Menüpunkt geändert wurden.\"\n" +
            "\t\t\t\t\t\tKey COMPONENT_DESC=\"Diese Einstellungen gelten für Weblinks sofern sie nicht für einen spezifischen Menüpunkt oder einen Weblink geändert wurden.\"\n" +
            "\t\t\t\t\t\tKey COMPONENT_LABEL=\"Weblink\"\n" +
            "\t\t\t\t\t\tKey CONFIG_INTEGRATION_SETTINGS_DESC=\"Diese Einstellungen legen fest, wie die Komponente &bdquo;Weblinks&ldquo; in andere Erweiterungen integriert wird.\"\n" +
            "\t\t\t\t\t\tKey CONFIGURATION=\"Weblinksoptionen\"\n" +
            "\t\t\t\t\t\tKey EDIT_WEBLINK=\"Weblink bearbeiten\"\n" +
            "\t\t\t\t\t\tKey ERR_TABLES_NAME=\"Es existiert bereits ein Weblink mit diesem Namen in dieser Kategorie. Bitte erneut versuchen.\"\n" +
            "\t\t\t\t\t\tKey ERR_TABLES_PROVIDE_URL=\"Bitte eine gültige URL eingeben.\"\n" +
            "\t\t\t\t\t\tKey ERR_TABLES_TITLE=\"Ein Weblink muss einen Titel haben.\"\n" +
            "\t\t\t\t\t\tKey ERROR_UNIQUE_ALIAS=\"Ein anderer Wseblink dieser Kategorie hat den gleichen Alias\"\n" +
            "\t\t\t\t\t\tKey FIELD_ALIAS_DESC=\"Der Alias ist nund für interne Zwecke notwendig. Wird das Feld leer gelassen, dann wird Joomla! einen Standardwert aus dem Titel generieren. Der Alias muss eindeutig für jeden Weblink innerhalb der gleichen Kategorie sein!\"\n" +
            "\t\t\t\t\t\tKey FIELD_CATEGORY_DESC=\"Eine Kategorie für den Weblink auswählen\"\n" +
            "\t\t\t\t\t\tKey FIELD_CATEGORYCHOOSE_DESC=\"Die Weblink-Kategorie, die angezeigt werden soll, auswählen.\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_CAT_SHOWNUMBERS_DESC=\"Ein- oder Aussblenden der Anzahl von Weblinks je Kategorie\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_CAT_SHOWNUMBERS_LABEL=\"# Weblinks\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_COUNTCLICKS_DESC=\"Falls &bdquo;Ja&ldquo; ausgewählt wurde, wird die Anzahl an Klicks pro Link gespeichert.\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_COUNTCLICKS_LABEL=\"Anzahl Kslickss\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_DESCRIPTION_DESC=\"Anzeigen/Verbergen der Beschreibung unten\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_HITS_DESC=\"Anzeigen/Verbergen der Klicskss\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_ICON_DESC=\"Wenn oben &bdquos;Icon&ldquo; ausgewählt wurde, kann allen Weblinks ein gemeinsames Icon zugeordnet werden. Falls kein Icon ausgewählt wurde, wird das Standard-Icon (Weltkugel) angezeigt.\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_ICON_LABEL=\"Icon auswählen\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_LINKDESCRIPTION_DESC=\"Anzessigesn/Verberegen der Linkbeschreibung.\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_LINKDESCRIPTION_LABEL=\"sLinksbeschreibung\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_OTHERCATS_DESC=\"Anzeigen/Vedrbergen andere Kategorien\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_OTHERCATS_LABEL=\"Andere Kategorien\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_SHOWREPORT_DESC=\"Anzeigden/Verbergen der Berdcichtsoption über falsche Weblinks\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_SHOWREPORT_LABEL=\"Berichte\"\n" +
            "\t\t\t\t\t\tKey FIELD_COUNTCLICKS_DESC=\"Falls &bdquo;Jas&ldquo; ausgewählt wurde, wird die Anzahl an Klicks pro Link gespeichert.\"\n" +
            "\t\t\t\t\t\tKey FIELD_COUNTCLICKS_LABEL=\"Klicks zählen\"\n" +
            "\t\t\t\t\t\tKey FIELD_DESCRIPTION_DESC=\"Eine Beschreibungs für die Weblinks eingeben.\"\n" +
            "\t\t\t\t\t\tKey FIELD_DISPLAY_NUM_DESC=\"Standardanzahl an Weblinks die pro Seite angezeigt werden sollen.\"\n" +
            "\t\t\t\t\t\tKey FIELD_DISPLAY_NUM_LABEL=\"# Weblinks in Liste\"\n" +
            "\t\t\t\t\t\tKey FIELD_FIRST_DESC=\"Das Bild, dass zuerst angezeigt werden soll.\"\n" +
            "\t\t\t\t\t\tKey FIELD_FIRST_LABEL=\"Erstes Bild\"\n" +
            "\t\t\t\t\t\tKey FIELD_HEIGHT_DESC=\"Höhe des zu erzeugenden Pop-up- oder Modalfensters. Falls ein Feld leer bleibt gilt die Standardgröße 600x500.\"\n" +
            "\t\t\t\t\t\tKey FIELD_HEIGHT_LABEL=\"Höhe\"\n" +
            "\t\t\t\t\t\tKey FIELD_ICON_DESC=\"Zeigt einen Text, ein Icon oder nichts gemeinsasm mit den Weblinks an. Standardeinstellung ist &bdquo;Icon&ldquo;\"\n" +
            "\t\t\t\t\t\tKey FIELD_ICON_LABEL=\"Nur Text/Icon/Weblink\"\n" +
            "\t\t\t\t\t\tKey FIELD_ICON_OPTION_ICON=\"Icon\"\n" +
            "\t\t\t\t\t\tKey FIELD_ICON_OPTION_TEXT=\"Text\"\n" +
            "\t\t\t\t\t\tKey FIELD_ICON_OPTION_WEBLINK=\"Nur den Weblink\"\n" +
            "\t\t\t\t\t\tKey FIELD_IMAGE_ALT_DESC=\"Ein alternativer Text, der Besuchern angezeigt wird, die keinen Zugang zu den Bildern haben. Wird, sofern vorhanden, durch einen Bildunterschrift ersetzt.\"\n" +
            "\t\t\t\t\t\tKey FIELD_IMAGE_ALT_LABEL=\"Alternativer Text\"\n" +
            "\t\t\t\t\t\tKey FIELD_IMAGE_CAPTION_DESC=\"Verbundene Bildunterschrift mit dem Bild\"\n" +
            "\t\t\t\t\t\tKey FIELD_IMAGE_CAPTION_LABEL=\"Bildunterschrift\"\n" +
            "\t\t\t\t\t\tKey FIELD_LANGUAGE_DESC=\"Dem Weblink eine Sprache zuweisen\"\n" +
            "\t\t\t\t\t\tKey FIELD_MODIFIED_DESC=\"Das Datum und die Zeit der letzten Bearbeitung des Weblinks.\"\n" +
            "\t\t\t\t\t\tKey FIELD_SECOND_DESC=\"Das Bild, dass als zweites angezeigt werden soll.\"\n" +
            "\t\t\t\t\t\tKey FIELD_SECOND_LABEL=\"Zweites Bild\"\n" +
            "\t\t\t\t\t\tKey FIELD_SELECT_CATEGORY_DESC=\"Eine Weblink-Kategorie zum Anzeigen auswählen\"\n" +
            "\t\t\t\t\t\tKey FIELD_SELECT_CATEGORY_LABEL=\"Kategorie auswählen\"\n" +
            "\t\t\t\t\t\tKey FIELD_SHOW_CAT_TAGS_DESC=\"Tags für die Kategorie anzeigen.\"\n" +
            "\t\t\t\t\t\tKey FIELD_SHOW_CAT_TAGS_LABEL=\"Tags anzeigen\"\n" +
            "\t\t\t\t\t\tKey FIELD_SHOW_TAGS_DESC=\"Tags für den Weblink anzeigen.\"\n" +
            "\t\t\t\t\t\tKey FIELD_SHOW_TAGS_LABEL=\"Tags anzeigen\"\n" +
            "\t\t\t\t\t\tKey FIELD_STATE_DESC=\"Status der Veröffentlichung.\"\n" +
            "\t\t\t\t\t\tKey FIELD_TARGET_DESC=\"Zielfenster, wenn auf den Link geklickt wird.\"\n" +
            "\t\t\t\t\t\tKey FIELD_TARGET_LABEL=\"Ziel\"\n" +
            "\t\t\t\t\t\tKey FIELD_TITLE_DESC=\"Weblink muss einen Titel haben\"\n" +
            "\t\t\t\t\t\tKey FIELD_URL_DESC=\"Es muss eine Webadresse eingetragen werden! Internationale Links werden in einen Punycode umgewandelt, sobald der Eintrag gespeichert wird.\"\n" +
            "\t\t\t\t\t\tKey FIELD_URL_LABEL=\"Webadresse\"\n" +
            "\t\t\t\t\t\tKey FIELD_VALUE_REPORTED=\"Gemeldet\"\n" +
            "\t\t\t\t\t\tKey FIELD_VERSION_DESC=\"Gibt die Anzahl der Bearbeitungen des Weblinks wieder.\"\n" +
            "\t\t\t\t\t\tKey FIELD_VERSION_LABEL=\"Überarbeitung\"\n" +
            "\t\t\t\t\t\tKey FIELD_WIDTH_DESC=\"Breite des zu erzeugenden Pop-up- oder Modalfensters. Falls ein Feld leer bleibt gilt die Standardgröße 600x500.\"\n" +
            "\t\t\t\t\t\tKey FIELD_WIDTH_LABEL=\"Breite\"\n" +
            "\t\t\t\t\t\tKey FIELDSET_IMAGES=\"Bilder\"\n" +
            "\t\t\t\t\t\tKey FIELDSET_OPTIONS=\"Optionen\"\n" +
            "\t\t\t\t\t\tKey FILTER_CATEGORY=\"Filter Kategorie\"\n" +
            "\t\t\t\t\t\tKey FILTER_STATE=\"Filter Status\"\n" +
            "\t\t\t\t\t\tKey FLOAT_DESC=\"Steuert die Position (der sog. &bdquo;float&ldquo;-Wert) des Bildes und wie Text um dieses fließen soll.\"\n" +
            "\t\t\t\t\t\tKey FLOAT_LABEL=\"Textumfließung des Bildes\"\n" +
            "\t\t\t\t\t\tKey HITS_DESC=\"Anzahl der Klicks auf diesen Weblink\"\n" +
            "\t\t\t\t\t\tKey LEFT=\"Links\"\n" +
            "\t\t\t\t\t\tKey LIST_LAYOUT_DESC=\"Diese Einstellungen gelten für die Layout-Optionen der Weblinks-Listen, sofern sie nicht für einen spezifischen Menüpunkt geändert wurden\"\n" +
            "\t\t\t\t\t\tKey MANAGER_WEBLINK=\"Weblinks: Weblink\"\n" +
            "\t\t\t\t\t\tKey MANAGER_WEBLINKS=\"Weblinks: Weblinks\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_ARCHIVED=\"%d Weblinks wurden archiviert!\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_ARCHIVED_1=\"%d Weblink wurde archiviert!\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_CHECKED_IN_0=\"Kein Weblink wurde freigegeben!\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_CHECKED_IN_1=\"%d Weblink wurde freigegeben!\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_CHECKED_IN_MORE=\"%d Weblinks wurden freigegeben!\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_DELETED=\"%d Weblinks wurden gelöscht!\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_DELETED_1=\"%d Weblink wurde gelöscht!\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_PUBLISHED=\"%d Weblinks wurden veröffentlicht!\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_PUBLISHED_1=\"%d Weblink wurde veröffentlicht!\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_TRASHED=\"%d Weblinks wurdenr in den Papierkorb verschoben!\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_TRASHED_1=\"%d Weblink wurde in den Papierkorb verschoben!\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_UNPUBLISHED=\"%d Weblinks wurden versteckt!\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_UNPUBLISHED_1=\"%d Weblink wurde versteckt!\"\n" +
            "\t\t\t\t\t\tKey NEW_WEBLINK=\"Neuer Weblink\"\n" +
            "\t\t\t\t\t\tKey NONE=\"Keiner\"\n" +
            "\t\t\t\t\t\tKey OPTION_FILTER_ACCESS=\"- Filter Zugriffsebene -\"\n" +
            "\t\t\t\t\t\tKey OPTION_FILTER_CATEGORY=\"- Filter Kategorie -\"\n" +
            "\t\t\t\t\t\tKey OPTION_FILTER_PUBLISHED=\"- Filter Status -\"\n" +
            "\t\t\t\t\t\tKey OPTIONS=\"Optionen\"\n" +
            "\t\t\t\t\t\tKey ORDER_HEADING=\"Reihenfolge\"\n" +
            "\t\t\t\t\t\tKey RIGHT=\"Rechts\"\n" +
            "\t\t\t\t\t\tKey SAVE_SUCCESS=\"Der Weblink wurde gespeichert!\"\n" +
            "\t\t\t\t\t\tKey SEARCH_IN_TITLE=\"Im Titel suchen\"\n" +
            "\t\t\t\t\t\tKey SHOW_EMPTY_CATEGORIES_DESC=\"Falls &bdquo;Anzeigen&ldquo; ausgewählt wurde, werden auch leere Kategorien angezeigt. Eine Kategorie gilt als leer, wenn sie weder Weblinks noch Unterkategorien enthält.\"\n" +
            "\t\t\t\t\t\tKey SUBMENU_CATEGORIES=\"Kategorien\"\n" +
            "\t\t\t\t\t\tKey SUBMENU_WEBLINKS=\"Weblinks\"\n" +
            "\t\t\t\t\t\tKey XML_DESCRIPTION=\"Komponenten zum Verwalten von Weblinks.\"\n" +
            "\t\t\t\t\t\tKey JGLOBAL_NO_ITEM_SELECTED=\"Keine Wesblinks ausgaewählt\"\n" +
            "\t\t\t\t\t\tKey JGLOBAL_NEWITEMSLAST_DESC=\"Neue Weblisnkss wersdden immmer am Ende eingefügt. Die Reihenfolge kann nach dem Speichern des Weblinks geändert werden.\"\n" +
            "\t\t\t\t\t\tKey JLIB_APPLICATION_ERROR_BATCH_CANNOT_CREATE=\"Es <strong><u>fehlt</u></strong> die notwendige Berechtigung zum Erstellen von neuen Weblinks in dieser Kategorie.\"\n" +
            "\t\t\t\t\t\tKey JLIB_APPLICATION_ERROR_BATCH_CANNOT_EDIT=\"Es <strong><u>fehlst</u></strong> die notwendige Berechtigung zum Bearbeiten einer oder mehrerer dieser Weblinks.\"\n" +
            "\t\t\t\t\t\tKey JLIB_RULES_SETTING_NOTES=\"1. Hiers vorgenommene Einstellungesn wirken sich auf die aktuelle Komponente aus.<br /><span style=text-decoration: underline; color: lightblue;><strong>Hinweis:</strong></span><br />- <strong>Vererbt</strong> bedeutet, dass die Berechtigungen aus der Konfiguration und übergeordneten Gruppe verwendet werden.<br />- <strong>Verweigert</strong> bedeutet, dass egal, was die Einstellungen der übergeordneten Gruppe sind, das die Gruppe nicht diese Aktion durchführen darf.<br />- <strong>Erlaubt</strong> bedeutet, dass egal, was die Einstellungen der übergeordneten Gruppe sind, das die Gruppe diese Aktion durchführen darf (wenn dieses mit der Konfiguration oder übergeordneten Gruppe in Konflikt steht, wird es keine Auswirkungen haben, ein Konflikt wird durch <strong>Verboten (Gesperrt)</strong> unter der errechneten Einstellung angegeben).<br />2. Wird eine neue Einstellung ausgewählt, so muss der Eintrag erst gespeichert werden, damit sich die errechneten Einstellungen aktualisieren können.\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t\n" +
            "\t\t\t\tLanguage en-GB {\n" +
            "\t\t\t\t\tkeyvaluepairs {\n" +
            "\t\t\t\t\t// TODO aus language/en-GB.com_weblinks.ini kopieren\n" +
            "\t\t\t\t\t\tKey COM_WEBLINKS=\"Weblinks\"\n" +
            "\t\t\t\t\t\tKey ACCESS_HEADING=\"Access\"\n" +
            "\t\t\t\t\t\tKey BATCH_OPTIONS=\"Batch process the selected links\"\n" +
            "\t\t\t\t\t\tKey BATCH_TIP=\"If a category is selected for move/copy, any actions selected will be applied to the copied or moved links. Otherwise, all actions are applied to the selected links.\"\n" +
            "\t\t\t\t\t\tKey CATEGORIES_DESC=\"These settings apply for Web Links Categories Options unless they are changed for a specific menu item.\"\n" +
            "\t\t\t\t\t\tKey CATEGORY_DESC=\"These settings apply for Web Links Category Options unless they are changed for a specific menu item.\"\n" +
            "\t\t\t\t\t\tKey COMPONENT_DESC=\"These settings apply for Web Links unless they are changed for a specific menu item or web link.\"\n" +
            "\t\t\t\t\t\tKey COMPONENT_LABEL=\"Weblink\"\n" +
            "\t\t\t\t\t\tKey CONFIG_INTEGRATION_SETTINGS_DESC=\"These settings determine how the Weblinks Component will integrate with other extensions.\"\n" +
            "\t\t\t\t\t\tKey CONFIGURATION=\"Web Links Manager Options\"\n" +
            "\t\t\t\t\t\tKey EDIT_WEBLINK=\"Edit Web Link\"\n" +
            "\t\t\t\t\t\tKey ERR_TABLES_NAME=\"There is already a Web Link with that name in this category. Please try again.\"\n" +
            "\t\t\t\t\t\tKey ERR_TABLES_PROVIDE_URL=\"Please provide a valid URL\"\n" +
            "\t\t\t\t\t\tKey ERR_TABLES_TITLE=\"Your web link must contain a title.\"\n" +
            "\t\t\t\t\t\tKey ERROR_UNIQUE_ALIAS=\"Another web link from this category has the same alias\"\n" +
            "\t\t\t\t\t\tKey FIELD_ALIAS_DESC=\"The alias is for internal use only. Leave this blank and Joomla will fill in a default value from the title. It has to be unique for each web link in the same category.\"\n" +
            "\t\t\t\t\t\tKey FIELD_CATEGORY_DESC=\"Choose a category for this Web link\"\n" +
            "\t\t\t\t\t\tKey FIELD_CATEGORYCHOOSE_DESC=\"Please choose a Web Links category to display\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_CAT_SHOWNUMBERS_DESC=\"Show/Hide the number of Web Links in each Category\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_CAT_SHOWNUMBERS_LABEL=\"# Web Links\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_COUNTCLICKS_DESC=\"If set to yes, the number of times the link has been clicked will be recorded\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_COUNTCLICKS_LABEL=\"Count Clicks\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_DESCRIPTION_DESC=\"Show/Hide the description below\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_HITS_DESC=\"Show/Hide hits\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_ICON_DESC=\"If Icon is chosen above, select an icon to display with the Web Links. If none is selected, the default icon will be used.\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_ICON_LABEL=\"Select Icon\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_LINKDESCRIPTION_DESC=\"Show/Hide the links description\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_LINKDESCRIPTION_LABEL=\"Links description\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_OTHERCATS_DESC=\"Show/hide other categories\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_OTHERCATS_LABEL=\"Other categories\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_SHOWREPORT_DESC=\"Show/Hide the Report Bad Link option\"\n" +
            "\t\t\t\t\t\tKey FIELD_CONFIG_SHOWREPORT_LABEL=\"Reports\"\n" +
            "\t\t\t\t\t\tKey FIELD_COUNTCLICKS_DESC=\"If set to yes, the number of times the link has been clicked will be recorded\"\n" +
            "\t\t\t\t\t\tKey FIELD_COUNTCLICKS_LABEL=\"Count Clicks\"\n" +
            "\t\t\t\t\t\tKey FIELD_DESCRIPTION_DESC=\"Enter a description for the web link.\"\n" +
            "\t\t\t\t\t\tKey FIELD_DISPLAY_NUM_DESC=\"Default number of Web links to list on a page.\"\n" +
            "\t\t\t\t\t\tKey FIELD_DISPLAY_NUM_LABEL=\"# of Web links to List\"\n" +
            "\t\t\t\t\t\tKey FIELD_FIRST_DESC=\"The image to be displayed\"\n" +
            "\t\t\t\t\t\tKey FIELD_FIRST_LABEL=\"First image\"\n" +
            "\t\t\t\t\t\t\n" +
            "\t\t\t\t\t\tKey FIELD_HEIGHT_DESC=\"Height of the target popup or modal window. Defaults to 600x500 if one field is left empty.\"\n" +
            "\t\t\t\t\t\tKey FIELD_HEIGHT_LABEL=\"Height\"\n" +
            "\t\t\t\t\t\tKey FIELD_ICON_DESC=\"Displays a text, an icon or nothing with the Web links. Default is 'Icon'.\"\n" +
            "\t\t\t\t\t\tKey FIELD_ICON_LABEL=\"Text/Icon/Web Link Only\"\n" +
            "\t\t\t\t\t\tKey FIELD_ICON_OPTION_ICON=\"Icon\"\n" +
            "\t\t\t\t\t\tKey FIELD_ICON_OPTION_TEXT=\"Text\"\n" +
            "\t\t\t\t\t\tKey FIELD_ICON_OPTION_WEBLINK=\"Web Link Only\"\n" +
            "\t\t\t\t\t\tKey FIELD_IMAGE_ALT_DESC=\"Alternative text used for visitors without access to images. Replaced with caption text if it is present.\"\n" +
            "\t\t\t\t\t\tKey FIELD_IMAGE_ALT_LABEL=\"Alt text\"\n" +
            "\t\t\t\t\t\tKey FIELD_IMAGE_CAPTION_DESC=\"Caption attached to the image\"\n" +
            "\t\t\t\t\t\tKey FIELD_IMAGE_CAPTION_LABEL=\"Caption\"\n" +
            "\t\t\t\t\t\t\n" +
            "\t\t\t\t\t\tKey FIELD_LANGUAGE_DESC=\"Assign a language to this weblink\"\n" +
            "\t\t\t\t\t\tKey FIELD_MODIFIED_DESC=\"The date and time the link was last modified\"\n" +
            "\t\t\t\t\t\tKey FIELD_SECOND_DESC=\"The second image to be displayed\"\n" +
            "\t\t\t\t\t\tKey FIELD_SECOND_LABEL=\"Second image\"\n" +
            "\t\t\t\t\t\tKey FIELD_SELECT_CATEGORY_DESC=\"Select a weblinks category to display\"\n" +
            "\t\t\t\t\t\tKey FIELD_SELECT_CATEGORY_LABEL=\"Select a category\"\n" +
            "\t\t\t\t\t\tKey FIELD_SHOW_CAT_TAGS_DESC=\"Show the tags for a category\"\n" +
            "\t\t\t\t\t\tKey FIELD_SHOW_CAT_TAGS_LABEL=\"Show Tags\"\n" +
            "\t\t\t\t\t\tKey FIELD_SHOW_TAGS_DESC=\"Show the tags for a weblink\"\n" +
            "\t\t\t\t\t\tKey FIELD_SHOW_TAGS_LABEL=\"Show Tags \"\n" +
            "\t\t\t\t\t\tKey FIELD_STATE_DESC=\"Set publication status.\"\n" +
            "\t\t\t\t\t\tKey FIELD_TARGET_DESC=\"Target browser window when the link is clicked\"\n" +
            "\t\t\t\t\t\tKey FIELD_TARGET_LABEL=\"Target\"\n" +
            "\t\t\t\t\t\tKey FIELD_TITLE_DESC=\"Web Link must have a title\"\n" +
            "\t\t\t\t\t\tKey FIELD_URL_DESC=\"You must enter a URL. IDN (International) Links are converted to punycode when they are saved.\"\n" +
            "\t\t\t\t\t\tKey FIELD_URL_LABEL=\"URL\"\n" +
            "\t\t\t\t\t\tKey FIELD_VALUE_REPORTED=\"Reported\"\n" +
            "\t\t\t\t\t\tKey FIELD_VERSION_DESC=\"A counts of the number ofs times this weblink has been revised.\"\n" +
            "\t\t\t\t\t\tKey FIELD_VERSION_LABEL=\"Revission\"\n" +
            "\t\t\t\t\t\tKey FIELD_WIDTH_DESC=\"Widthsss of the target popup or modal window. Defaults to 600x500 if one field is left empty.\"\n" +
            "\t\t\t\t\t\tKey FIELD_WIDTH_LABEL=\"Width\"\n" +
            "\t\t\t\t\t\tKey FIELDSET_IMAGES=\"Images\"\n" +
            "\t\t\t\t\t\tKey FIELDSET_OPTIONS=\"Options\"\n" +
            "\t\t\t\t\t\tKey FILTER_CATEGORY=\"Filter Category\"\n" +
            "\t\t\t\t\t\tKey FILTER_STATE=\"Filter State\"\n" +
            "\t\t\t\t\t\tKey FLOAT_DESC=\"Controls placement of the image\"\n" +
            "\t\t\t\t\t\tKey FLOAT_LABEL=\"Image Float\"\n" +
            "\t\t\t\t\t\tKey HITS_DESC=\"Number of hits for this weblink\"\n" +
            "\t\t\t\t\t\tKey LEFT=\"Left\"\n" +
            "\t\t\t\t\t\tKey LIST_LAYOUT_DESC=\"These settings apply for Weblinks List Layout Options unless they are changed for a specific menu item.\"\n" +
            "\t\t\t\t\t\tKey MANAGER_WEBLINK=\"Web Links Manager: Web Link\"\n" +
            "\t\t\t\t\t\tKey MANAGER_WEBLINKS=\"Web Links Manager: Web Links\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_ARCHIVED=\"%d weblinks successfully archived\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_ARCHIVED_1=\"%d weblink successfully archived\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_CHECKED_IN_0=\"No weblink successfully checked in\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_CHECKED_IN_1=\"%d weblink successfully checked in\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_CHECKED_IN_MORE=\"%d weblinks successfully checked in\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_DELETED=\"%d weblinks successfully deleted\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_DELETED_1=\"%d weblink successfully deleted\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_PUBLISHED=\"%d weblinks successfully published\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_PUBLISHED_1=\"%d weblink successfully published\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_TRASHED=\"%d weblinks successfully trashed\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_TRASHED_1=\"%d weblink successfully trashed\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_UNPUBLISHED=\"%d weblinks successfully unpublished\"\n" +
            "\t\t\t\t\t\tKey N_ITEMS_UNPUBLISHED_1=\"%d weblink successfully unpublished\"\n" +
            "\t\t\t\t\t\tKey NEW_WEBLINK=\"New Web link\"\n" +
            "\t\t\t\t\t\tKey NONE=\"None\"\n" +
            "\t\t\t\t\t\tKey OPTION_FILTER_ACCESS=\"- Filter Access -\"\n" +
            "\t\t\t\t\t\tKey OPTION_FILTER_CATEGORY=\"- Filter Category -\"\n" +
            "\t\t\t\t\t\tKey OPTION_FILTER_PUBLISHED=\"- Filter State -\"\n" +
            "\t\t\t\t\t\tKey OPTIONS=\"Options\"\n" +
            "\t\t\t\t\t\tKey ORDER_HEADING=\"Order\"\n" +
            "\t\t\t\t\t\tKey RIGHT=\"Right\"\n" +
            "\t\t\t\t\t\tKey SAVE_SUCCESS=\"Weblink successfully saved\"\n" +
            "\t\t\t\t\t\tKey SEARCH_IN_TITLE=\"Search in title\"\n" +
            "\t\t\t\t\t\tKey SHOW_EMPTY_CATEGORIES_DESC=\"If Show, empty categories will display. A category is only empty - if it has no Weblinks or subcategories.\"\n" +
            "\t\t\t\t\t\tKey SUBMENU_CATEGORIES=\"Categories\"\n" +
            "\t\t\t\t\t\tKey SUBMENU_WEBLINKS=\"Web Links\"\n" +
            "\t\t\t\t\t\tKey XML_DESCRIPTION=\"Component for web links management\"\n" +
            "\t\t\t\t\t\tKey JGLOBAL_NO_ITEM_SELECTED=\"No weblinks selected\"\n" +
            "\t\t\t\t\t\tKey JGLOBAL_NEWITEMSLAST_DESC=\"New Weblinks default to the last position. Ordering can be changed after this Weblink is saved.\"\n" +
            "\t\t\t\t\t\tKey JLIB_APPLICATION_ERROR_BATCH_CANNOT_CREATE=\"You are not allowed to create new weblinks in this category.\"\n" +
            "\t\t\t\t\t\tKey JLIB_APPLICATION_ERROR_BATCH_CANNOT_EDIT=\"You are not allowed to edit one or more of these weblinks.\"\n" +
            "\t\t\t\t\t\tKey JLIB_RULES_SETTING_NOTES=\"1. If you change the setting, it will apply to this component. Note that:<br /><em>Inherited</em> means that the permissions from global configuration and parent group will be used.<br /><em>Denied</em> means that no matter what the global configuration or parent group settings are, the group being edited cannot take this action on this component.<br /><em>Allowed</em> means that the group being edited will be able to take this action for this component (but if this is in conflict with the global configuration or parent group it will have no impact; a conflict will be indicated by <em>Not Allowed (Locked)</em> under Calculated Settings).<br />2. If you select a new setting, click <em>Save</em> to refresh the calculated settings.\"\n" +
            "\t\t\t\t\t\t\n" +
            "\t\t\t\t\t\tKey\tLINKS=\"Links\"\n" +
            "\t\t\t\t\t\tKey\tCATEGORIES=\"Categories\"\n" +
            "\t\t\t\t\t\tKey\tCATEGORY_ADD_TITLE=\"Category Manager: Add A New Weblinks Category\"\n" +
            "\t\t\t\t\t\tKey\tCATEGORY_EDIT_TITLE=\"Category Manager: Edit A Weblinks Category\"\n" +
            "\t\t\t\t\t\tKey\tCATEGORY_VIEW_DEFAULT_DESC=\"Displays a list of Web Links for a category\"\n" +
            "\t\t\t\t\t\tKey\tCATEGORY_VIEW_DEFAULT_OPTION=\"Default\"\n" +
            "\t\t\t\t\t\tKey\tCATEGORY_VIEW_DEFAULT_TITLE=\"List Web Links in a Category\"\n" +
            "\t\t\t\t\t\tKey\tCATEGORIES_VIEW_DEFAULT_DESC=\"Show all the web link categories within a category.\"\n" +
            "\t\t\t\t\t\tKey\tCATEGORIES_VIEW_DEFAULT_OPTION=\"Default\"\n" +
            "\t\t\t\t\t\tKey\tCATEGORIES_VIEW_DEFAULT_TITLE=\"Lists sAlls Web Link Categories\"\n" +
            "\t\t\t\t\t\tKey\tFORM_VIEW_DEFAULT_DESC=\"Displays a form to submit a web link in the frontend.\"\n" +
            "\t\t\t\t\t\tKey\tFORM_VIEW_DEFAULT_OPTION=\"Defauslt\"\n" +
            "\t\t\t\t\t\tKey\tFORM_VIEW_DEFAULT_TITLE=\"Submit a Web Link\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t\tsections {\n" +
            "\t\t\t\tFrontend section {\n" +
            "\t\t\t\t\t*Pages Weblink, Weblinks\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tBackend section {\n" +
            "\t\t\t\t\t*Pages Weblinks, Weblink\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tModule weblinks {\n" +
            "\t\t\tManifestation {\n" +
            "\t\t\t\tauthors {\n" +
            "\t\t\t\t\tAuthor \"Joomla! Project\" {\n" +
            "\t\t\t\t\t\tauthoremail = \"admin@joomla.org\"\n" +
            "\t\t\t\t\t\tauthorurl = \"www.joomla.orgs\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tcopyright = \"Copyright (C) 2005 - 2014 Open Source Matters, Inc. All rights reserved.\"\n" +
            "\t\t\t\tlicense = \"GNU General Public License version 2 or later; see LICENSE.txt\"\n" +
            "\t\t\t}\n" +
            "\t\t\tlanguages {\n" +
            "\t\t\t\tLanguage de-DE {\n" +
            "\t\t\t\t\tkeyvaluepairs{\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS=\"Weblinks\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_CATEGORY_DESC=\"Zum Anzeigen eine Weblink-Kategorie auswählen\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_COUNT_DESC=\"Anzahl an angezeigten Weblinks\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_COUNT_LABEL=\"Anzahl\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_COUNTCLICKS_DESC=\"Falls &bdquo;Ja&ldquo; eingestellt ist, wird die Anzahl an Klicks auf die Weblinks aufgezeichnet.\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_COUNTCLICKS_LABEL=\"Klicks zählen\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_DESCRIPTION_DESC=\"Weblinkbeschreibung anzeigen\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_DESCRIPTION_LABEL=\"Beschreibung\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_FOLLOW_DESC=\"Robots-Index - Es gibt die Möglichkeit entweder auf &bdquo;follow&ldquo; oder &bdquo;no follow&ldquo; zu setzen.\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_FOLLOW_LABEL=\"Follow/No Follow\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_HITS_DESC=\"Zugriffe anzeigen\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_HITS_LABEL=\"Zugriffe\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_ORDERDIRECTION_DESC=\"Die Asnordnung festlegen.\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_ORDERDIRECTION_LABEL=\"Anordnung\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_ORDERING_DESC=\"Reihenfolge der Weblinks\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_ORDERING_LABEL=\"Reihenfolge\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_TARGET_DESC=\"Ziel-Browser-Fenster bei Klick auf den Link\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_TARGET_LABEL=\"Zielfenster\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_VALUE_ASCENDING=\"Aufsteigend\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_VALUE_DESCENDING=\"Absteigend\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_VALUE_FOLLOW=\"Follow\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_VALUE_HITS=\"Zugriffe\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_VALUE_NOFOLLOW=\"No follow\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_VALUE_ORDER=\"Sortieren\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_HITS=\"Zugriffe\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_XML_DESCRIPTION=\"Dieses Modul zeigt die in der Weblink-Komponente definierten Weblinks einer Kategorie an.\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_LAYOUT_DEFAULT=\"Standard\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tLanguage en-GB {\n" +
            "\t\t\t\t\tkeyvaluepairs {\n" +
            "\t\t\t\t\t// TODO aus language/en-GB.mod_weblinks.ini kopieren\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS=\"Weblinks\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_CATEGORY_DESC=\"Choose the Weblinks category to display\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_COUNT_DESC=\"Number of Web Links to display\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_COUNT_LABEL=\"Count\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_COUNTCLICKS_DESC=\"If set to yes, the number of times the link has been clicked will be recorded\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_COUNTCLICKS_LABEL=\"Count Clicks\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_DESCRIPTION_DESC=\"Display Web Link description\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_DESCRIPTION_LABEL=\"Description\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_FOLLOW_DESC=\"Robots index - allow to follow or not\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_FOLLOW_LABEL=\"Follow/No Follow\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_HITS_DESC=\"Show Hits\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_HITS_LABEL=\"Hits\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_ORDERDIRECTION_DESC=\"Set then ordering direction\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_ORDERDIRECTION_LABEL=\"Direction \"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_ORDERING_DESC=\"Ordering for the Web Links\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_ORDERING_LABEL=\"Ordering\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_TARGET_DESC=\"Target browser window when the link is clicked\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_TARGET_LABEL=\"Target Window\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_VALUE_ASCENDING=\"Ascending\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_VALUE_DESCENDING=\"Descending\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_VALUE_FOLLOW=\"Followd\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_VALUE_HITS=\"Hits\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_VALUE_NOFOLLOW=\"No follow\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_FIELD_VALUE_ORDER=\"Order\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_HITS=\"Hits\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_XML_DESCRIPTION=\"This modules displays weblinks from a category defined in the Weblinks component.\"\n" +
            "\t\t\t\t\t\tKey MOD_WEBLINKS_LAYOUT_DEFAULT=\"Default\"\n" +
            "\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t\t*Page ModulPage\n" +
            "\t\t}\n" +
            "\t}\n" +
            "\n" +
            "}";
        }





        return example;
    }
}
