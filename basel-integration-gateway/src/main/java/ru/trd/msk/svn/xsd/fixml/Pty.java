package ru.trd.msk.svn.xsd.fixml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Pty", propOrder = { "value" })
public class Pty
{

    @XmlValue
    protected String value;
    @XmlAttribute(name = "ID")
    protected String id;
    @XmlAttribute(name = "R")
    protected Byte r;

    /**
     * Gets the value of the value property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getValue()
    {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getID()
    {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setID(String value)
    {
        this.id = value;
    }

    /**
     * Gets the value of the r property.
     * 
     * @return possible object is {@link Byte }
     * 
     */
    public Byte getR()
    {
        return r;
    }

    /**
     * Sets the value of the r property.
     * 
     * @param value
     *            allowed object is {@link Byte }
     * 
     */
    public void setR(Byte value)
    {
        this.r = value;
    }

}