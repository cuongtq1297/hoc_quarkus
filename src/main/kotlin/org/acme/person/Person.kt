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
    ),
    NamedQuery(
        name = "Person.findByOptionalParams",
        query = """
            FROM Person p WHERE 1=1 
            AND (:name IS NULL OR p.name = :name) 
            AND (:age IS NULL OR p.age = :age)
            AND (:companyName IS NULL OR p.company.name = :companyName)
            AND (:companyAddress IS NULL OR p.company.address = :companyAddress)
            ORDER BY p.name ASC
        """
    ),
    NamedQuery(
        name = "Person.findAllWithDTO",
        query = """
        SELECT NEW org.acme.person.PersonResponse(
            p.name, 
            p.age, 
            p.company.name, 
            p.company.address
        ) 
        FROM Person p
    """
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