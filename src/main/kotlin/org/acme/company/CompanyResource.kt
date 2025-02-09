package org.acme.company

import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("company")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class CompanyResource(
    private val companyRepository: CompanyRepository
) {
    @GET
    fun getAll(): List<Company> {
        return companyRepository.listAll();
    }

    @POST
    @Transactional
    fun create(company: Company): Company {
        companyRepository.persist(company);
        return company;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    fun update(companyRq: Company, @PathParam("id") id: Long): String {
        val company = companyRepository.findById(id);
        if (company == null) {
            return "Company not found";
        } else {
            company.name = companyRq.name;
            company.address = companyRq.address;
            companyRepository.persist(company);
            return "Company ${companyRq.name} updated";
        }
    }
}