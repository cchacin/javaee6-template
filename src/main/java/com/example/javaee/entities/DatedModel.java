package com.example.javaee.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Data
@MappedSuperclass
public class DatedModel extends Model {

	@Column(nullable = false)
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =
	// "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private Date created;

	@Column(nullable = false)
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =
	// "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
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
