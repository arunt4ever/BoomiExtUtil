package com.arunzlair.boomiextutil

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class XMLValidationTest {

    private val xmlVal = XMLValidation()

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun isValid() {
        var xmlData = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/myxml-bad.xml").readText()
        val xmlSchema = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/myxml-schema.xsd").readText()
        assertFalse(xmlVal.isValid(xmlSchema,xmlData))
    }

    @Test
    fun valid() {
        var xmlData = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/myxml-good.xml").readText()
        val xmlSchema = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/myxml-schema.xsd").readText()
        assertTrue(xmlVal.isValid(xmlSchema,xmlData))
    }

    @Test
    fun getValidationMsg() {
        assertNotNull(xmlVal.getValidationMsg())
    }

    @Test
    fun getValidationMsgJSON(){
        assertNotNull(xmlVal.getValidationMsgJSON())
    }
}