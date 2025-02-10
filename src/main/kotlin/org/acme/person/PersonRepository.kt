package org.acme.person

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PersonRepository : PanacheRepository<Person> {

    fun findByCompany(companyName: String): List<Person> {
        return find("#Person.findByCompany", mapOf("companyName" to companyName)).list()
    }

    fun findByOptionalParams(name: String?, age: Int?, companyName: String?, companyAddress: String?): List<Person> {
        return find(
            "#Person.findByOptionalParams",
            mapOf("name" to name, "age" to age, "companyName" to companyName, "companyAddress" to companyAddress)
        ).list()
    }

    fun findAllWithDTO(): List<PersonResponse> {
        return getEntityManager().createNamedQuery("Person.findAllWithDTO", PersonResponse::class.java)
            .resultList
    }
}