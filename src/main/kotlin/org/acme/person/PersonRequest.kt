package org.acme.person

data class PersonRequest(
    var name: String,
    var age: Int?,
    var companyId: Long
)
