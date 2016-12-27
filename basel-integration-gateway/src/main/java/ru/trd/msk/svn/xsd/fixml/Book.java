package ru.trd.msk.svn.xsd.fixml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Book", namespace = "http://svn.msk.trd.ru/xsd/fixml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Book", propOrder = { "aid", "pty" }, namespace = "http://svn.msk.trd.ru/xsd/fixml")
public class Book
{

    @XmlElement(name = "AID", required = true)
    protected AID aid;
    @XmlElement(name = "Pty")
    protected List<Pty> pty;
    @XmlAttribute(name = "ID")
    protected Short id;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "SrcName")
    protected String srcName;
    @XmlAttribute(name = "Typ")
    protected String typ;
    @XmlAttribute(name = "Mode")
    protected String mode;
    @XmlAttribute(name = "OpenFlag")
    protected Byte openFlag;
    @XmlAttribute(name = "ActiveFlag")
    protected Byte activeFlag;
    @XmlAttribute(name = "LegalEntity")
    protected String legalEntity;
    @XmlAttribute(name = "TraderName")
    protected String traderName;
    @XmlAttribute(name = "Created")
    protected String created;
    @XmlAttribute(name = "Modified")
    protected String modified;
    @XmlAttribute(name = "SrcID")
    protected Short srcID;

    /**
     * Gets the value of the aid property.
     * 
     * @return possible object is {@link FIXML.Batch.BookDef.Book.AID }
     * 
     */
    public AID getAID()
    {
        return aid;
    }

    /**
     * Sets the value of the aid property.
     * 
     * @param value
     *            allowed object is {@link FIXML.Batch.BookDef.Book.AID }
     * 
     */
    public void setAID(AID value)
    {
        this.aid = value;
    }

    /**
     * Gets the value of the pty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the pty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getPty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link FIXML.Batch.BookDef.Book.Pty }
     * 
     * 
     */
    public List<Pty> getPty()
    {
        if (pty == null)
        {
            pty = new ArrayList<Pty>();
        }
        return this.pty;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return possible object is {@link Short }
     * 
     */
    public Short getID()
    {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *            allowed object is {@link Short }
     * 
     */
    public void setID(Short value)
    {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setName(String value)
    {
        this.name = value;
    }

    /**
     * Gets the value of the srcName property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getSrcName()
    {
        return srcName;
    }

    /**
     * Sets the value of the srcName property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setSrcName(String value)
    {
        this.srcName = value;
    }

    /**
     * Gets the value of the typ property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTyp()
    {
        return typ;
    }

    /**
     * Sets the value of the typ property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setTyp(String value)
    {
        this.typ = value;
    }

    /**
     * Gets the value of the mode property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getMode()
    {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setMode(String value)
    {
        this.mode = value;
    }

    /**
     * Gets the value of the openFlag property.
     * 
     * @return possible object is {@link Byte }
     * 
     */
    public Byte getOpenFlag()
    {
        return openFlag;
    }

    /**
     * Sets the value of the openFlag property.
     * 
     * @param value
     *            allowed object is {@link Byte }
     * 
     */
    public void setOpenFlag(Byte value)
    {
        this.openFlag = value;
    }

    /**
     * Gets the value of the activeFlag property.
     * 
     * @return possible object is {@link Byte }
     * 
     */
    public Byte getActiveFlag()
    {
        return activeFlag;
    }

    /**
     * Sets the value of the activeFlag property.
     * 
     * @param value
     *            allowed object is {@link Byte }
     * 
     */
    public void setActiveFlag(Byte value)
    {
        this.activeFlag = value;
    }

    /**
     * Gets the value of the legalEntity property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getLegalEntity()
    {
        return legalEntity;
    }

    /**
     * Sets the value of the legalEntity property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setLegalEntity(String value)
    {
        this.legalEntity = value;
    }

    /**
     * Gets the value of the traderName property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTraderName()
    {
        return traderName;
    }

    /**
     * Sets the value of the traderName property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setTraderName(String value)
    {
        this.traderName = value;
    }

    /**
     * Gets the value of the created property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCreated()
    {
        return created;
    }

    /**
     * Sets the value of the created property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setCreated(String value)
    {
        this.created = value;
    }

    /**
     * Gets the value of the modified property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getModified()
    {
        return modified;
    }

    /**
     * Sets the value of the modified property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setModified(String value)
    {
        this.modified = value;
    }

    /**
     * Gets the value of the srcID property.
     * 
     * @return possible object is {@link Short }
     * 
     */
    public Short getSrcID()
    {
        return srcID;
    }

    /**
     * Sets the value of the srcID property.
     * 
     * @param value
     *            allowed object is {@link Short }
     * 
     */
    public void setSrcID(Short value)
    {
        this.srcID = value;
    }

}
