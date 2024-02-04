package org.example

import io.vertx.core.Vertx
import io.vertx.ext.web.Router

suspend fun main() {
    val vertx = Vertx.vertx()

    val router = Router.router(vertx)
    router.get("/").handler { ctx ->
        ctx.response().end("Hello, World!")
    }

    vertx.createHttpServer()
        .requestHandler(router)
        .listen()
        .onSuccess {
            println("Server started at http://0.0.0.0:${it.actualPort()}")
        }
}