package de.thm.mni.ii.data;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "value"
})
public  class File {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "line")
    protected int line;
    @XmlAttribute(name = "path")
    protected String path;

    /**
     * Ruft den Wert der value-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getValue() {
        return value;
    }

    /**
     * Legt den Wert der value-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Ruft den Wert der line-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link int }
     *
     */
    public int getLine() {
        return line;
    }

    /**
     * Legt den Wert der line-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link int }
     *
     */
    public void setLine(int value) {
        this.line = value;
    }

    /**
     * Ruft den Wert der path-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPath() {
        return path;
    }

    /**
     * Legt den Wert der path-Eigenschaft fest.
     * @since 1.6
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPath(String value)  {

        this.path = value;
    }

    @Override
    public String toString() {
        return "File{" +
                "value='" + value + '\'' +
                ", line=" + line +
                ", path='" + path + '\'' +
                "}\n";
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return path.equalsIgnoreCase(file.path) && file.line == this.line;
    }


}
