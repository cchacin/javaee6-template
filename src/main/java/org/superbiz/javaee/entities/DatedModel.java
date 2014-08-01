package org.superbiz.javaee.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.deltaspike.data.api.audit.CreatedOn;
import org.apache.deltaspike.data.api.audit.ModifiedOn;
import org.apache.deltaspike.data.impl.audit.AuditEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditEntityListener.class)
public abstract class DatedModel extends Model {

    @CreatedOn
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

	@ModifiedOn(onCreate = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
}
