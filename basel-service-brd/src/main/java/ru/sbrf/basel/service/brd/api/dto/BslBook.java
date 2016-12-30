package ru.sbrf.basel.service.brd.api.dto;

public class BslBook
{

    private String bookCode;
    private String sourceSystem;
    private String bookType;

    public String getBookCode()
    {
        return bookCode;
    }

    public void setBookCode(String code)
    {
        this.bookCode = code;
    }

    public String getSourceSystem()
    {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem)
    {
        this.sourceSystem = sourceSystem;
    }

    public String getBookType()
    {
        return bookType;
    }

    public void setBookType(String type)
    {
        this.bookType = type;
    }

}
