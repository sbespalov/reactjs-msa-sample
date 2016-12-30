package ru.sbrf.basel.service.generic.domain;

import java.util.Date;

public interface BslEntity
{

    public Long getEntityId();

    public Date getCreationDate();

    public void setModificationDate(Date modificationDate);

    public Date getModificationDate();

}
