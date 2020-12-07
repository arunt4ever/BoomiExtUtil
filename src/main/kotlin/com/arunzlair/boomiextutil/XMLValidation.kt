package com.arunzlair.boomiextutil

import org.json.JSONObject
import org.xml.sax.ErrorHandler
import org.xml.sax.SAXException
import org.xml.sax.SAXParseException
import java.io.StringReader
import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory


class XMLValidation {

    private var validationMsg: MutableList<String> = mutableListOf()
    private var validationMsgJSON: String = ""
    private var validationResult: Boolean = false

    fun isValid(xmlSchema: String, xmlData: String): Boolean {
        validationMsg = mutableListOf()
        validationMsgJSON = ""
        validationResult = false

        val factory: SchemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        try {
            val schema = factory.newSchema(StreamSource(StringReader(xmlSchema)))
            val validator = schema.newValidator()
            val xmlSource = StreamSource(StringReader(xmlData))
            validator.errorHandler = object : ErrorHandler {
                @Throws(SAXException::class)
                override fun warning(exception: SAXParseException) {
                    validationMsg.add(exception.message!!)
                }

                @Throws(SAXException::class)
                override fun error(exception: SAXParseException) {
                    validationMsg.add(exception.message!!)
                }

                @Throws(SAXException::class)
                override fun fatalError(exception: SAXParseException) {
                    validationMsg.add(exception.message!!)
                }
            }
            validator.validate(xmlSource)
            if (validationMsg.isNotEmpty()) {
                return validationResult
            }
        } catch (e: SAXException) {
            e.printStackTrace()
        }
        validationResult = true
        return validationResult
    }

    @JvmOverloads
    fun getValidationMsg(delimiter: String = "; "): String {
        return validationMsg.joinToString(separator = delimiter)
    }

    fun getValidationMsgJSON(): String {
        val valMsg = JSONObject()
        valMsg.put("validationResult", validationResult)
        valMsg.put("validationErrors", validationMsg)
        validationMsgJSON = valMsg.toString()
        return validationMsgJSON
    }
}

//fun main() {
//    var xmlData = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/myxml-bad.xml").readText()
//    val xmlSchema = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/myxml-schema.xsd").readText()
//    val xmlVal = XMLValidation()
//    println(xmlVal.getValidationMsg())
//
//    // inValid Case
//    println(xmlVal.isValid(xmlSchema, xmlData))
//    println(xmlVal.getValidationMsg("\n"))
//    println(xmlVal.getValidationMsgJSON())
//
//    // Valid Case
//    xmlData = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/myxml-good.xml").readText()
//    println(xmlVal.isValid(xmlSchema, xmlData))
//    println(xmlVal.getValidationMsg("\n"))
//    println(xmlVal.getValidationMsgJSON())
//}