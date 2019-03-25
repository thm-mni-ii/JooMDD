
package de.thm.mni.ii.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "duplication"
})
@XmlRootElement(name = "pmd-cpd")
public class PmdCpd {

    protected List<Duplication> duplication;

    /**
     * Gets the value of the duplication property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the duplication property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDuplication().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Duplication }
     *
     *
     */
    public List<Duplication> getDuplication() {
        if (duplication == null) {
            duplication = new ArrayList<Duplication>();
        }
        return this.duplication;
    }

    public void  filterNumberOfFile(int numberOfFiles){
        List<Duplication> dup = new ArrayList<Duplication>();

        for(Duplication dp : duplication){
            if(dp.getFillesPath().size() >= numberOfFiles){
                dup.add(dp);
            }
        }
        this.duplication = dup;

    }
  public void filterCluster(List<String> paths ){
      List<Duplication> dup = new ArrayList<Duplication>();
      for(Duplication dp : duplication){
          if(dp.contailsAllFile(paths) == true){
              dup.add(dp);
          }
      }
      this.duplication = dup;
  }
  public void deleteDoubleDuplication(){
        for(Duplication d: duplication){
            d.mergeFile();
        }
  }

    public void setDuplication(List<Duplication> duplication) {
        this.duplication = duplication;
    }

    public void mergeDuplication(){
        List<Duplication> dupList = new ArrayList<Duplication>();
        for(Duplication d: duplication){
            if(dupList.contains(d)){
                int index = duplication.indexOf(d);
                Duplication du = duplication.get(index);
                if(du.contailsAllFile(d.getFillesPath()))
                    du.mergeTo(d);
            }else{
                dupList.add(d);
            }
        }
        this.duplication = dupList;
    }

    public boolean existFile(String pt) {

        for(Duplication dp: this.getDuplication()){
            if(dp.getFillesPath().contains(pt)){
                return true;
            }
        }
        return false;
    }

    public float maxDuplicate() {
        float procent =0;
        for(Duplication d : this.duplication){
            if(d.getPercentage() > procent)
                procent = d.getPercentage();
        }
        return procent;
    }
}
