package ru.trd.msk.svn.xsd.fixml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Batch", propOrder = { "bookDef" })
@XmlRootElement
public class Batch
{

    @XmlElement(name = "BookDef")
    protected List<BookDef> bookDef;
    @XmlAttribute(name = "ID")
    protected String id;

    /**
     * Gets the value of the bookDef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the bookDef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getBookDef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link FIXML.Batch.BookDef }
     * 
     * 
     */
    public List<BookDef> getBookDef()
    {
        if (bookDef == null)
        {
            bookDef = new ArrayList<BookDef>();
        }
        return this.bookDef;
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
}