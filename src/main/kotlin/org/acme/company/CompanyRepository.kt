package org.acme.company

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class CompanyRepository : PanacheRepository<Company> {
}