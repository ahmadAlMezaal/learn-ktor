package com.example.routes

import com.example.models.Customer
import com.example.models.customerStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRoutes() {
    route("/customer") {
        get{
            if(customerStorage.isNotEmpty()){
                call.respond(customerStorage)
            }else{
                call.respondText("No customers found", status = HttpStatusCode.OK)
            }
        }
        get("{id?}"){
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val customer = customerStorage.find{it.id ==id } ?: return@get call.respondText(
                "Customer not found",
                status = HttpStatusCode.NotFound
            )
            call.respond(customer)
        }
        post{
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
            call.respondText("Customer added", status = HttpStatusCode.Created)
        }
        delete("{id?}"){
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            if(customerStorage.removeIf { it.id == id }){
                call.respondText("Customer removed correctly", status = HttpStatusCode.OK)
            }else{
                call.respondText("Not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}