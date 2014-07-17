package org.superbiz.javaee.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class DatedModel extends Model {

	@Column(nullable = false)
	private Date created;

	@Column(nullable = false)
	private Date modified;

	@PrePersist
	public void create() {
		this.created = new Date();
	}

	@PreUpdate
	public void modify() {
		this.modified = new Date();
	}
}
