package de.thm.mni.ii;

import de.thm.mni.ii.data.Duplication;
import de.thm.mni.ii.data.PmdCpd;
import net.sourceforge.pmd.cpd.*;
import net.sourceforge.pmd.cpd.renderer.CPDRenderer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class JooMDDCPD {

    public JooMDDCPD() {
    }

    public static void main(String[] args) {
        JooMDDCPD cpd = new JooMDDCPD();

        String command = args[0];
        if(args[0].equalsIgnoreCase("sp")){
            System.out.println("Procent:" + "   --- " +  cpd.searFromOneFile(args[1]));
        }else{
            List<ClusterResult> gg = cpd.startSearchClustering(args[1]);

            for(ClusterResult k: gg){
                System.out.println(k.procent + "   --- " + k.duplication.getFillesPath().toString());
            }
        }




    }

    public List<ClusterResult> startSearchClustering(String path){
        List<ClusterResult> resuslt = new ArrayList<ClusterResult>();
        PmdCpd d = this.searFromOneFileCluster(path);
        if(d != null){
            d.filterNumberOfFile(2);
            d.deleteDoubleDuplication();
            d.mergeDuplication();
            return  this.searchCluster(d);
        }
        return resuslt;
    }
    public List<ClusterResult> searchCluster(PmdCpd cpd){
        ArrayList<ClusterResult> result = new ArrayList<ClusterResult>();

        for(Duplication dp: cpd.getDuplication()){
            searchPathToCluste(dp,result);

        }

        return result;
    }

    private void searchPathToCluste(Duplication dup, List<ClusterResult> existendCluster) {
        List<ClusterResult> result = new ArrayList<ClusterResult>() ;
        if(existendCluster.isEmpty()){
            float pc = this.searchFromFilesDuplicationProcent(dup.getFillesPath(),2,16);
            existendCluster.add(new ClusterResult(dup,pc));
            return ;
        }
        float pc = this.searchFromFilesDuplicationProcent(dup.getFillesPath(),2,16);
        ClusterResult akt = new ClusterResult(dup,pc);
        List<String> todeletePath = new ArrayList<String>();
        for (String pt: dup.getFillesPath()){
            boolean isfunsCluster = false;

            for(ClusterResult pmd: existendCluster) {
                if (!pmd.getDuplication().containFile(pt)) {
                    float diff = pmd.procent - akt.procent;
                    if (10 > diff && diff > -10) {
                        isfunsCluster = pmd.addfile(pt);
                        if (isfunsCluster) {
                            todeletePath.add(pt);
                        }
                    }

                }else{
                    todeletePath.add(pt);
                }
            }
        }
        if(todeletePath.size()>0){
            for(String s: todeletePath){
                akt.duplication.deletePath(s);
            }

        }
        if(akt.duplication.getFillesPath().size()>0)
        existendCluster.add(akt);
    }

    public PmdCpd searFromOneFileCluster(String file)  {
        List<File> all = this. searchFile(new File(file));
        PmdCpd s = null;
        try {
            s = this.copyPasteDetector(all, 2);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  s;
    }
    public float searFromOneFile(String file){
        List<File> all = this. searchFile(new File(file));
        List<String> allPath = new ArrayList<String>();
        for(File s: all){
            allPath.add(s.getPath());
        }
      return this.searchFromFilesDuplicationProcent(allPath, 2,16) ;
    }
    public float searchFromFilesDuplicationProcent(List<String> paths, int filter, int clonLenght){
        float result = 0;
        PmdCpd data =  null;
        try{
            List<File> all = new ArrayList<File>();
            for(String s: paths){
                all.add(new File(s));
            }
             data = copyPasteDetector(all, clonLenght);

            data.filterNumberOfFile(2);
             data.filterCluster(paths);
            data.mergeDuplication();

            for(Duplication fg : data.getDuplication()){
                result += fg.getPercentage() ;
            }
        }catch(JAXBException e ){

        }catch(IOException t){

        }
        return data.maxDuplicate();
    }

    public PmdCpd copyPasteDetector(List<File> filesList, int minimumTileSize) throws JAXBException
            , IOException{

        CPDConfiguration configuration = new CPDConfiguration();
        configuration.setMinimumTileSize(minimumTileSize);
        Properties p = new Properties();
        p.setProperty(Tokenizer.IGNORE_LITERALS, "true");
        p.setProperty(Tokenizer.IGNORE_IDENTIFIERS, "true");
        p.setProperty(Tokenizer.IGNORE_ANNOTATIONS, "true");
        p.setProperty(Tokenizer.IGNORE_USINGS, "true");
        p.setProperty(Tokenizer.OPTION_SKIP_BLOCKS, Boolean.toString(false));
        p.setProperty(Tokenizer.OPTION_SKIP_BLOCKS_PATTERN, Tokenizer.DEFAULT_SKIP_BLOCKS_PATTERN);
        configuration.setLanguage(LanguageFactory.createLanguage("php",p));
        configuration.setEncoding("UTF-8");
        configuration.setSkipDuplicates(false);
        configuration.setSkipLexicalErrors(false);
        CPD test =   new CPD(configuration);
        for(File df : filesList){
            test.add(df);
        }
        long start = System.currentTimeMillis();
        test.go();
        long stop = System.currentTimeMillis();
        long totalTime =  stop - start;
        CPDRenderer renderer = new XMLRenderer();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Writer w = new BufferedWriter(new OutputStreamWriter(os,"utf-8"));
        renderer.render(test.getMatches(),w);

        JAXBContext context = JAXBContext.newInstance(PmdCpd.class);
        PmdCpd data = (PmdCpd) context.createUnmarshaller().unmarshal(new ByteArrayInputStream(os.toByteArray()));

        return data;
    }
    public  List<File> searchFile(File file){
        List<File> result = new ArrayList<File>();

        if(file.isDirectory()){
            for(File f : file.listFiles()){
                if(f.isFile() && f.getPath().endsWith("php")){
                    result.add(f);
                }else{
                    List<File> temp = searchFile(f);
                    if(!temp.isEmpty())
                    result.addAll(temp);
                }
            }
        }else{
            if(file.isFile() && file.getPath().endsWith("php"))
            result.add(file);
            return result;
        }
        return  result;
    }
}
