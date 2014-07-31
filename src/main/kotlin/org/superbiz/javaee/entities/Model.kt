package org.superbiz.javaee.entities;

import javax.persistence.*;
import java.io.Serializable;

MappedSuperclass
data public abstract class Model : Serializable {

	Id
	GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long = 0

	Version
	var version: Long = 0

}
