package com.example.javaee.entities;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractDomainObject implements Serializable {
	private static final long serialVersionUID = 2209366869487621842L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version;

	public boolean isTransient() {
		return this.version == null;
	}

	public Long getId() {
		return id;
	}
}
