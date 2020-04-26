package dev.gumil.talan.backend

import okhttp3.mockwebserver.MockResponse
import java.io.ByteArrayOutputStream

internal fun Any.readFromFile(file: String): String {
    val inputStream = this.javaClass.classLoader!!.getResourceAsStream(file)
    val result = ByteArrayOutputStream()
    val buffer = ByteArray(1024)
    var length = inputStream.read(buffer)
    while (length != -1) {
        result.write(buffer, 0, length)
        length = inputStream.read(buffer)
    }
    return result.toString("UTF-8")
}

internal fun createMockResponse(body: String): MockResponse {
    return MockResponse().apply {
        setResponseCode(200)
        setBody(body)
        addHeader("Content-type: application/json")
    }
}
