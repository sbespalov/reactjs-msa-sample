package ru.sbrf.basel.service.generic.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BslEntityBase implements BslEntity
{

    private Long entityId;
    private Date creationDate = new Date();
    private Date modificationDate = new Date();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EntityId", insertable = false, updatable = false)
    public Long getEntityId()
    {
        return entityId;
    }

    public void setEntityId(Long entityId)
    {
        this.entityId = entityId;
    }

    @Column(name = "CreateAt", insertable = false, updatable = false)
    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    @Column(name = "ChangeAt", insertable = false, updatable = false)
    public Date getModificationDate()
    {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate)
    {
        this.modificationDate = modificationDate;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        BslEntity other = (BslEntity) obj;
        return this.getEntityId() != null && other.getEntityId() != null
                && this.getEntityId().equals(other.getEntityId());
    }

}
