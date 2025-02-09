package org.acme.company

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Company: PanacheEntity() {
    @Column(length = 40, unique = true)
    lateinit var name: String
    @Column
    var address: String? = null
}