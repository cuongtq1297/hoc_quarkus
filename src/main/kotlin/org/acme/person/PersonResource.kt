package org.acme.person

import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.acme.company.Company
import org.acme.company.CompanyRepository

@Path("persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PersonResource(
    private val personRepository: PersonRepository, private val companyRepository: CompanyRepository
) {
    @GET
    fun getAllPersonInfo(): List<PersonResponse> {
//        val persons: List<Person> = personRepository.findAll().list()
//        val personResList = mutableListOf<PersonResponse>()
//        for (person in persons) {
//            val personResponse = PersonResponse()
//            personResponse.name = person.name
//            personResponse.age = person.age
//            personResponse.companyName = person.company?.name
//            personResponse.companyAddress = person.company?.address
//            personResList.add(personResponse)
//        }
//        return personResList
        return personRepository.findAll().list().map { person ->
            PersonResponse(
                name = person.name,
                age = person.age,
                companyName = person.company?.name,
                companyAddress = person.company?.address
            )
        }
    }

    @GET
    @Path("/v2")
    fun findAllWithDTO(): List<PersonResponse> {
        return personRepository.findAllWithDTO()
    }

    @GET
    @Path("/searchByCompanyName")
    fun searchByCompanyName(@QueryParam("companyName") companyName: String): List<PersonResponse> {
        return personRepository.findByCompany(companyName).map { person ->
            PersonResponse(
                name = person.name,
                age = person.age,
                companyName = person.company?.name,
                companyAddress = person.company?.address
            )
        }
    }

    @GET
    @Path("/search")
    fun search(
        @QueryParam("name") name: String?,
        @QueryParam("age") age: Int?,
        @QueryParam("companyName") companyName: String?,
        @QueryParam("companyAddress") companyAddress: String?
    ): List<PersonResponse> {
        return personRepository.findByOptionalParams(name, age, companyName, companyAddress).map { person ->
            PersonResponse(
                name = person.name,
                age = person.age,
                companyName = person.company?.name,
                companyAddress = person.company?.address
            )
        }
    }

    @POST
    @Transactional
    fun create(request: PersonRequest): String {
        val company = companyRepository.findById(request.companyId)
        company?.let {
            val person = Person().apply {
                name = request.name
                age = request.age
                this.company = company
            }
            personRepository.persist(person)
            return "Created Successfully"
        } ?: run {
            return "Company not found"
        }
//        if (company == null) {
//            return "Company not found"
//        } else {
//            val person = Person().apply {
//                name = request.name
//                age = request.age
//                this.company = company
//            }
////            person.name = request.name
////            person.age = request.age
////            person.company = company
//            personRepository.persist(person)
//            return "Created Successfully"
//        }
    }
}