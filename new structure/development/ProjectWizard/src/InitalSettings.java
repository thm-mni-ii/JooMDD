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
        try {
            FileWriter fw = new FileWriter(project.getBasePath() + "/" +project.getName() + ".iml" );
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(getProjectConfig());

            bw.close();
        }catch (Exception e){

        }
    }

    @Override
    public void initComponent() {

        File src = new File(project.getBasePath() + "/src");
        File src_gen = new File(project.getBasePath() + "/src-gen");
        File model = new File(project.getBasePath() + "/src/model.eJSL");

        File projectfile = new File(project.getBasePath()+"/"+project.getName()+ ".iml");

        try{
            src.mkdir();
            src_gen.mkdir();
            model.createNewFile();
            projectfile.createNewFile();

            FileWriter fw = new FileWriter(project.getBasePath() + "/src/model.eJSL");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(geteJSLExample());
            bw.close();
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



    public String geteJSLExample(){
        return
                "eJSLModel \"name of the model\" {\n" +
                "    datatypes {\n" +
                "        Datatype \"varchar(255) NOT NULL\",\n" +
                "        Datatype \"text NOT NULL\",\n" +
                "        Datatype \"datetime NOT NULL DEFAULT '0000-00-00 00:00:00'\",\n" +
                "        Datatype \"date\",\n" +
                "        Datatype \"textarea\",\n" +
                "        Datatype \"text\",\n" +
                "        Datatype \"editor\",\n" +
                "        Datatype \"text_I\",\n" +
                "        Datatype \"blog\",\n" +
                "        Datatype \"ediad_6\",\n" +
                "        Datatype \"assdf\",\n" +
                "        Datatype \"aasadfd\"\n" +
                "    }\n" +
                "    globalparameters {\n" +
                "        Parameter Name {\n" +
                "            type = bool\n" +
                "            size = 12\n" +
                "        }\n" +
                "        Parameter Name_ID_22 {\n" +
                "            type = float\n" +
                "        }\n" +
                "    }\n" +
                "    entities {\n" +
                "        Entity entity1  {\n" +
                "            attributes {\n" +
                "                Attribute attribute1 {\n" +
                "                    dbtype = \"varchar(255) NOT NULL\"\n" +
                "                    htmltype = \"text\"\n" +
                "                    Primary attribute\n" +
                "                }\n" +
                "                Attribute attribute2 {\n" +
                "                    dbtype = \"varchar(255) NOT NULL\"\n" +
                "                    htmltype = \"editor\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        Entity entity2 {\n" +
                "            attributes {\n" +
                "                Attribute attribute1 {\n" +
                "                    dbtype = \"varchar(255) NOT NULL\"\n" +
                "                    htmltype = \"text\"\n" +
                "                    Primary attribute\n" +
                "                }\n" +
                "                Attribute attribute2 {\n" +
                "                    dbtype = \"varchar(255) NOT NULL\"\n" +
                "                    htmltype = \"text\"\n" +
                "                }\n" +
                "                Attribute ida {\n" +
                "                    dbtype = \"varchar(255) NOT NULL\"\n" +
                "                    htmltype = \"text\"\n" +
                "                }\n" +
                "                Attribute ida_ID_X {\n" +
                "                    dbtype = \"datetime NOT NULL DEFAULT \\'0000-00-00 00:00:00\\'\"\n" +
                "                    htmltype = \"datetime NOT NULL DEFAULT \\'0000-00-00 00:00:00\\'\"\n" +
                "                }\n" +
                "            }\n" +
                "            references {\n" +
                "                Reference {\n" +
                "                    Attribute = attribute1\n" +
                "                    *Entity = entity1\n" +
                "                    Reference = entity1.attribute1\n" +
                "                    lower = 1\n" +
                "                    upper = 1\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "    datapackages {\n" +
                "        Datapackage Name {\n" +
                "            entities {\n" +
                "                Entity text {\n" +
                "                    attributes {\n" +
                "                        Attribute attribute1 {\n" +
                "                            dbtype = \"varchar(255) NOT NULL\"\n" +
                "                            htmltype = \"text\"\n" +
                "                            Primary attribute\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "    pages {\n" +
                "        IndexPage IndexPage1 {\n" +
                "            *Entities entity1, entity2\n" +
                "            localparameters {\n" +
                "                Parameter id {\n" +
                "                    type = \"date\"\n" +
                "                }\n" +
                "                Parameter Name {\n" +
                "                    type = \"date\"\n" +
                "                }\n" +
                "                Parameter Name_ID_104 {\n" +
                "                    type = \"date\"\n" +
                "                }\n" +
                "            }\n" +
                "            table columns = entity1.attribute1, entity1.attribute2\n" +
                "            filters = entity1.attribute2, entity1.attribute1\n" +
                "        }\n" +
                "        DetailsPage DetailsPage1 {\n" +
                "            *Entities entity1\n" +
                "        }\n" +
                "        IndexPage IndexPage2 {\n" +
                "            *Entities entity1, Name.text, entity2\n" +
                "            table columns = entity1.attribute1, entity1.attribute2\n" +
                "            filters = entity2.attribute2, entity1.attribute1\n" +
                "        }\n" +
                "        DetailsPage DetailsPage2 {\n" +
                "            *Entities entity2\n" +
                "        }\n" +
                "    }\n" +
                "    extensions {\n" +
                "        Library lib1 {\n" +
                "            Manifestation {\n" +
                "                authors {\n" +
                "                    Author \"surname\" {\n" +
                "                        authoremail = \"na12rovgfhi@der.domain\"\n" +
                "                        authorurl = \"http://www.anazuyaurl.com\"\n" +
                "                    }\n" +
                "                }\n" +
                "                copyright = \"Copyright\"\n" +
                "                license = \"License\"\n" +
                "            }\n" +
                "            classes {\n" +
                "                Class name {\n" +
                "                    methods {\n" +
                "                        Method asd {\n" +
                "                            Returnvalue ^Returnvalue: \"date\"\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "                Class name_ID_141 {\n" +
                "                    methods {\n" +
                "                        Method asd {\n" +
                "                            Returnvalue ^Returnvalue: \"date\"\n" +
                "                        }\n" +
                "                        Method asd_ID_148 {\n" +
                "                            Returnvalue ^Returnvalue: \"date\"\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        Library lib1hj  {\n" +
                "            Manifestation {\n" +
                "                authors {\n" +
                "                    Author \"surname\" {\n" +
                "                        authoremail = \"nasdsdi@der.domain\"\n" +
                "                        authorurl = \"http://www.anasdurl.com\"\n" +
                "                    }\n" +
                "                }\n" +
                "                copyright = \"Copyright\"\n" +
                "                license = \"License\"\n" +
                "            }\n" +
                "        }\n" +
                "        Library neuelib {\n" +
                "            Manifestation {\n" +
                "                authors {\n" +
                "                    Author \"\"  {\n" +
                "                        authoremail = \"\"\n" +
                "                        authorurl = \"\"\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        Library libsdjfvhbbvb2 {\n" +
                "            Manifestation {\n" +
                "                authors {\n" +
                "                    Author \"surname\" {\n" +
                "                        authoremail = \"naasdaasdsdvi@der.domain\"\n" +
                "                        authorurl = \"http://www.asaaqwasddurl.com\"\n" +
                "                    }\n" +
                "                }\n" +
                "                copyright = \"Copyright\"\n" +
                "                license = \"License\"\n" +
                "            }\n" +
                "        }\n" +
                "        Extension package package1 {\n" +
                "            Manifestation {\n" +
                "                authors {\n" +
                "                    Author \"saddname\" {\n" +
                "                        authoremail = \"na12rovi@der.domain\"\n" +
                "                        authorurl = \"http://www.aasdwqrl.com\"\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "            extensions {\n" +
                "                Component Component1 {\n" +
                "                    Manifestation {\n" +
                "                        authors {\n" +
                "                            Author \"surname\" {\n" +
                "                                authoremail = \"na12rovi@der.domain\"\n" +
                "                                authorurl = \"http://www.anyurl.com\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                        copyright = \"Copyright\"\n" +
                "                        license = \"License\"\n" +
                "                    }\n" +
                "                    languages {\n" +
                "                        Language de-DE {\n" +
                "                            keyvaluepairs {\n" +
                "                                Key Name = \"Value\"\n" +
                "                                Key x_182 = \"dValue\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                        Language en-UI {\n" +
                "                        }\n" +
                "                    }\n" +
                "                    sections {\n" +
                "                        Frontend section {\n" +
                "                            *Pages {\n" +
                "                            }\n" +
                "                        }\n" +
                "                        Backend section {\n" +
                "                            *Pages {\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }
}
