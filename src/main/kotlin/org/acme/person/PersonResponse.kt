package org.acme.person

data class PersonResponse(
    var name: String? = null,
    var age: Int? = null,
    var companyName: String? = null,
    var companyAddress: String? = null
)