package org.superbiz.javaee.entities

import javax.persistence.*
import java.util.*
import org.superbiz.javaee.entities.Model

MappedSuperclass
data public abstract class DatedModel : Model() {

    Column
    public var created: Date = Date()
    Column
    public var modified: Date = Date()

    PrePersist
    public fun create() {
        this.created = Date()
    }

    PreUpdate
    public fun modify() {
        this.modified = Date()
    }
}