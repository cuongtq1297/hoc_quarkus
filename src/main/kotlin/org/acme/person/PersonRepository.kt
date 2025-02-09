package org.acme.person

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PersonRepository : PanacheRepository<Person> {
}