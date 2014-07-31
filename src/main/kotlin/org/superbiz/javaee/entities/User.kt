package org.superbiz.javaee.entities

import javax.persistence.*
import javax.xml.bind.annotation.XmlRootElement

Entity
Table(name = "users")
XmlRootElement
data public class User(val email: String, val password: String, val fullname: String) : DatedModel() {

}