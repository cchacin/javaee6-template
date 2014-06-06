package com.example.javaee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "users")
@Entity
@XmlRootElement
public class User extends AbstractDomainObject {
    private static final long serialVersionUID = 3810638653455000233L;

    @Column(nullable = false, length = 9, unique = true)
    private String username;

    @Size(min = 2, max = 20, message = "invalid first name")
    @NotNull
    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String password;
}
