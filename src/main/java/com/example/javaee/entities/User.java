package com.example.javaee.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Entity
@XmlRootElement
public class User extends DatedModel {
	private static final long serialVersionUID = 3810638653455000233L;

	@NotNull
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String email;

	@NotNull
	@Size(min = 5, max = 15)
	private String password;

	@NotNull
	@Size(min = 3, max = 15)
	private String fullname;
}
