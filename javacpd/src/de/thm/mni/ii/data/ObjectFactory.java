
package de.thm.mni.ii.data;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: data
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PmdCpd }
     *
     */
    public PmdCpd createPmdCpd() {
        return new PmdCpd();
    }

    /**
     * Create an instance of {@link Duplication }
     * 
     */
    public Duplication createPmdCpdDuplication() {
        return new Duplication();
    }

    /**
     * Create an instance of {@link File }
     * 
     */
    public File createPmdCpdDuplicationFile() {
        return new File();
    }

}
