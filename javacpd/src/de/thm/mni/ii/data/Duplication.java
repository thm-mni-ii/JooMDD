package de.thm.mni.ii.data;

import com.google.common.io.Files;

import javax.xml.bind.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "file",
        "codefragment"
})
public  class Duplication {

    protected List<File> file;
    @XmlElement(required = true)
    protected String codefragment;
    @XmlAttribute(name = "lines")
    protected int lines;
    @XmlAttribute(name = "tokens")
    protected int tokens;


    @Override
    public String toString() {
        return "Duplication[ lines=" + lines +", tokens=" + tokens + " All file Size= "+ this.getAllFileSize() + " Procent: " + this.getPercentage() +"]{\n" +
                "file=" + file +"}\n" ;
                //"codefragment='" + codefragment + "\'}\n##ENDDUPLICATION\n";
    }

    public List<File> getFile() {
        if (file == null) {
            file = new ArrayList<File>();
        }
        return this.file;
    }

    /**
     * Ruft den Wert der codefragment-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodefragment() {
        return codefragment;
    }

    /**
     * Legt den Wert der codefragment-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodefragment(String value) {
        this.codefragment = value;
    }

    /**
     * Ruft den Wert der lines-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link Byte }
     *
     */
    public int getLines() {
        return lines;
    }

    /**
     * Legt den Wert der lines-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link Byte }
     *
     */
    public void setLines(int value) {
        this.lines = value;
    }

    /**
     * Ruft den Wert der tokens-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link Byte }
     *
     */
    public int getTokens() {
        return tokens;
    }

    /**
     * Legt den Wert der tokens-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link Byte }
     *
     */
    public void setTokens(Byte value) {
        this.tokens = value;
    }

    public float getAllFileSize(){
        float allLines = 0;
        List<String> paths = this.getFillesPath();
        try{
            for(String f: paths){
                allLines = this.getSize(f) + allLines;
            }
        }catch ( IOException e ){

        }
        return allLines/paths.size();
    }
  public float getPercentage()  {


      float result =  (float)this.lines / (this.getAllFileSize()) ;
        return result * 100;
  }

    public int getSize(String path) throws  IOException{
        BufferedReader buff = Files.newReader(new java.io.File(path), Charset.forName("UTF-8"));
        String s = buff.readLine();
        int allLineCode =1;
        while (buff.ready()){
            if(s.length() > 150){
                int lg = (s.length()/150)+1;
                allLineCode = allLineCode + lg ;
            }else{
                allLineCode++;
            }

            s = buff.readLine();
        }
        buff.close();
        return allLineCode;
    }
  public boolean contailsAllFile (List<String> allFiles){

        if(allFiles.size() > file.size())
            return false;
        for(String fn: allFiles ){
            if(!this.getFillesPath().contains(fn))
                return false;
        }
        return true;
  }
 public void mergeFile(){
     List<File> newfiles = new ArrayList<File>();

     for (File f : file){
         if(!newfiles.contains(f))
             newfiles.add(f);
     }
     this.file = newfiles;
 }

 public List<String> getFillesPath(){
        if(file == null){
            file = new ArrayList<File>();
        }
        List<String> result = new ArrayList<String>();
        for(File f : file){
            if(!result.contains(f.path))
            result.add(f.path);
        }
        return result;
 }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duplication that = (Duplication) o;
        List<String> thatAllPath = that.getFillesPath();
        List<String> thisAllPath = this.getFillesPath();
        if(thisAllPath.size() == thatAllPath.size() && this.contailsAllFile(thatAllPath) )
            return true;
        return false;
    }


    public void  mergeTo(Duplication c){



       this.codefragment = this.codefragment + c.codefragment;

       this.tokens = this.tokens + c.tokens;
       for(File l: c.file){
           if(!this.file.contains(l)){
               this.file.addAll(c.file);
               this.lines = this.lines + 1;
           }

       }






    }

    public void addFiles(List<String> fillesPath) {

        if(this.file == null){
            this.file = new ArrayList<File>();
        }

        for (String file: fillesPath){
            if(!this.getFillesPath().contains(file)){
                File df = (new ObjectFactory()).createPmdCpdDuplicationFile();
                df.setPath(file);
                this.file.add(df);
            }
        }
    }
    public void addFile(String fillesPath) {

        if(this.file == null){
            this.file = new ArrayList<File>();
        }

            if(!this.getFillesPath().contains(fillesPath)){
                File df = (new ObjectFactory()).createPmdCpdDuplicationFile();
                df.setPath(fillesPath);
                this.file.add(df);
            }

    }

    public void deletePath(String s) {
        List<File> result = new ArrayList<File>();
        for (File k: this.file){
            if(!k.path.equals(s)){
                result.add(k);
            }
        }
        this.file = result;
    }

    public boolean containFile(String pt) {
        return this.getFillesPath().contains(pt);
    }
}
