package com.example.javaee.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
