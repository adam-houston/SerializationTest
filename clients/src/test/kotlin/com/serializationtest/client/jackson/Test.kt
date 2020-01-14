package com.serializationtest.client.jackson

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import junit.framework.Assert.assertEquals
import net.corda.client.jackson.internal.CordaModule
import net.corda.core.serialization.CordaSerializable
import org.junit.Test

class CordaSerializableTest {
    private companion object {
        private val jsonMapper: ObjectMapper = ObjectMapper()
                .registerModule(CordaModule())
                .registerModule(KotlinModule())
    }

    @Test
    fun `should serialize - when class is annotated with @CordaSerializable and attribute has @JsonProperty modifying attributes name`() {
        val expectedString = """{"Type":"test value"}"""
        val someClass = SomeClass("test value")

        val serialized = jsonMapper.writeValueAsString(someClass)

        assertEquals(expectedString, serialized)
        // test passes if you remove @CordaSerializable from SomeClass
    }

    @Test
    fun `should serialize - when class attribute has JsonProperty modifying name and class is not marked with @CordaSerializable`() {
        val expectedString = """{"Type":"test value"}"""
        val someClass = AnotherClass("test value")

        val serialized = jsonMapper.writeValueAsString(someClass)

        assertEquals(expectedString, serialized)
    }

    @Test
    fun `should serialize - when class attribute has JsonProperty not modifying name attributes`() {
        val expectedString = """{"type":"test value"}"""
        val someClass = AnotherClass2("test value")

        val serialized = jsonMapper.writeValueAsString(someClass)

        assertEquals(expectedString, serialized)
    }

    @Test
    fun `should serialize - when class attribute is missing JsonProperty`() {
        val expectedString = """{"type":"test value"}"""
        val someClass = AnotherClass3("test value")

        val serialized = jsonMapper.writeValueAsString(someClass)

        assertEquals(expectedString, serialized)
    }

    @CordaSerializable
    data class SomeClass(@JsonProperty("Type") val type: String)

    data class AnotherClass(@JsonProperty("Type") val type: String)

    @CordaSerializable
    data class AnotherClass2(@JsonProperty("type") val type: String)

    @CordaSerializable
    data class AnotherClass3(val type: String)
}
