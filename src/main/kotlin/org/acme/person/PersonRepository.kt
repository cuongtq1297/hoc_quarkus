package org.acme.person

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PersonRepository : PanacheRepository<Person> {
    fun findByCompany(companyName: String): List<Person> {
        return find("#Person.findByCompany", mapOf("companyName" to companyName)).list()
    }

    fun findByAgeGreaterThan(age: Int): List<Person> {
        return find("#Person.findByAgeGreaterThan", mapOf("age" to age)).list()
    }
}