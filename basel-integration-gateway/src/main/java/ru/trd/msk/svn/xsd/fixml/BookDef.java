package ru.trd.msk.svn.xsd.fixml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookDef", propOrder = { "book" })
public class BookDef
{

    @XmlElement(name = "Book", required = true)
    protected Book book;

    /**
     * Gets the value of the book property.
     * 
     * @return possible object is {@link FIXML.Batch.BookDef.Book }
     * 
     */
    public Book getBook()
    {
        return book;
    }

    /**
     * Sets the value of the book property.
     * 
     * @param value
     *            allowed object is {@link FIXML.Batch.BookDef.Book }
     * 
     */
    public void setBook(Book value)
    {
        this.book = value;
    }

}
