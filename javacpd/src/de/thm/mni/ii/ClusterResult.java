package de.thm.mni.ii;

import de.thm.mni.ii.data.Duplication;
import de.thm.mni.ii.data.ObjectFactory;

import java.util.LinkedList;
import java.util.List;

public class ClusterResult {
    Duplication duplication;
    float procent;

    public Duplication getDuplication() {
        return duplication;
    }

    public float getProcent() {
        return procent;
    }

    public ClusterResult(Duplication duplication, float procent) {
        this.duplication = duplication;
        this.procent = procent;
    }
    public ClusterResult() {
        this.duplication = new ObjectFactory().createPmdCpdDuplication();
        this.procent = 0;
    }
    public  void addFiles(List<String> files){
        for (String f: files ){
            if(!this.duplication.getFillesPath().contains(f)){
                this.addfile(f);
            }
        }
    }

    public boolean addfile(String f) {
        if(this.duplication.getFillesPath().size() ==0){
            this.duplication.addFile(f);
            return true;
        }
        if(this.duplication.getFillesPath().contains(f)){
            return true;
        }
        else{
            List<String> tempath = new LinkedList<String>();
            tempath.addAll(this.duplication.getFillesPath());
            tempath.add(f);
            JooMDDCPD cpd = new JooMDDCPD();
            float temProcent = cpd.searchFromFilesDuplicationProcent(tempath,2,16);
            float diff = this.procent - temProcent;
            if(10 > diff && diff >-10){
                this.duplication.addFile(f);
                this.procent = temProcent;
                return true;
            }
        }
        return false;
    }

    public void setDuplication(Duplication duplication) {
        this.duplication = duplication;
    }

    public void setProcent(float procent) {
        this.procent = procent;
    }
}




