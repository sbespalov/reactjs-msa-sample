package ru.sbrf.basel.service.brd.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ru.sbrf.basel.service.generic.domain.BslEntityBase;

@Entity
@AttributeOverrides({
                      @AttributeOverride(name = "entityId", column = @Column(name = "BookId", insertable = false, updatable = false))
})
@Table(name = "`cadBook`", schema = "bsl_brd")
public class Book extends BslEntityBase
{

    private String bookCode;
    private String bookType;
    private String sourceSystem;

    @Column(name="BookCode")
    public String getBookCode()
    {
        return bookCode;
    }

    public void setBookCode(String code)
    {
        this.bookCode = code;
    }

    @Column(name="SourceSystem")
    public String getSourceSystem()
    {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem)
    {
        this.sourceSystem = sourceSystem;
    }

    @Column(name="BookType")
    public String getBookType()
    {
        return bookType;
    }

    public void setBookType(String type)
    {
        this.bookType = type;
    }

}
