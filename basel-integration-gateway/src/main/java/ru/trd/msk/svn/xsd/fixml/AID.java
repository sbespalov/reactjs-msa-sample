package ru.trd.msk.svn.xsd.fixml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AID", propOrder = { "value" })
public class AID
{

    @XmlValue
    protected String value;
    @XmlAttribute(name = "AltID")
    protected String altID;
    @XmlAttribute(name = "AltIDSrc")
    protected String altIDSrc;

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
     * Gets the value of the altID property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getAltID()
    {
        return altID;
    }

    /**
     * Sets the value of the altID property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setAltID(String value)
    {
        this.altID = value;
    }

    /**
     * Gets the value of the altIDSrc property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getAltIDSrc()
    {
        return altIDSrc;
    }

    /**
     * Sets the value of the altIDSrc property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setAltIDSrc(String value)
    {
        this.altIDSrc = value;
    }

}
