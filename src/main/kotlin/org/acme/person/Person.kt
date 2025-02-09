package org.acme.person

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.*
import org.acme.company.Company

@Entity
@NamedQueries(
    NamedQuery(
        name = "Person.findByCompany",
        query = "FROM Person p WHERE p.company.name = :companyName"
    ),
    NamedQuery(
        name = "Person.findByAgeGreaterThan",
        query = "FROM Person p WHERE p.age > :age"
    )
)

class Person : PanacheEntity() {
    @Column
    var name: String? = null
    var age: Int? = null

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    var company: Company? = null
}