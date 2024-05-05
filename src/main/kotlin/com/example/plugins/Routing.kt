package com.example.plugins

import com.example.routes.customerRoutes
import com.example.routes.getOrderRoute
import com.example.routes.listOrderRoutes
import com.example.routes.totaliseOrderRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        customerRoutes()
        listOrderRoutes()
        getOrderRoute()
        totaliseOrderRoute()
    }
}
