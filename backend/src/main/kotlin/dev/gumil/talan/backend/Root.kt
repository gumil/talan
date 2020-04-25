package dev.gumil.talan.backend

import io.kotless.dsl.lang.http.Get
import kotlinx.html.*
import kotlinx.html.stream.createHTML

@Get("/")
fun gettingStartedPage() = html {
    body {
        +"Hello world!"
    }
}

fun html(body: TagConsumer<String>.() -> Unit): String {
    return createHTML().apply(body).finalize()
}
