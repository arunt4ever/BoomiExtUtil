package com.arunzlair.boomiextutil

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.io.File

internal class JSONValidationTest {

    private val jsonVal = JSONValidation()

    @Test
    fun isValid() {
        val jsonData = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/poc.json").readText()
        val jsonSchema = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/poc.xsd").readText()
        assertFalse(jsonVal.isValid(jsonSchema,jsonData))
    }

    @Test
    fun getValidationMsgs() {
        assertNotNull(jsonVal.getValidationMsgs())
    }

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }
}