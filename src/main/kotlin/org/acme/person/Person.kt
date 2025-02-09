package org.acme.person

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.acme.company.Company

@Entity
class Person: PanacheEntity() {
    @Column
    var name: String? = null
    var age: Int? = null
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    var company: Company? = null
}