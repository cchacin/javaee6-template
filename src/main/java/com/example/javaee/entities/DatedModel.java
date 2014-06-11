package com.example.javaee.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
public class DatedModel extends Model {

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
