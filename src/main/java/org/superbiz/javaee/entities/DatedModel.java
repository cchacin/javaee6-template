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

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedOn
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@ModifiedOn(onCreate = true)
	private Date modified;
}
