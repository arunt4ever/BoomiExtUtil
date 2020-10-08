package com.arunzlair.boomiextutil

import org.everit.json.schema.Schema
import org.everit.json.schema.ValidationException
import org.everit.json.schema.loader.SchemaLoader
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class JSONValidation {

     private var validationMsg: List<String> = emptyList()
     private var validationMsgJSON: String = ""

     fun isValid(jsonSchema: String, jsonData: String): Boolean {
          try {
               val jsonSchemaObj = JSONObject(JSONTokener(jsonSchema))
               val schema: Schema = SchemaLoader.load(jsonSchemaObj)
               schema.validate(JSONObject(jsonData))
          }catch (e: ValidationException){
               validationMsg = e.causingExceptions.map { ve -> ve.allMessages }.flatten()
               validationMsgJSON  = e.toJSON().toString(4)
               return false
          }catch (e: FileNotFoundException){
               e.printStackTrace()
          }catch (e: IOException){
               e.printStackTrace()
          }
          validationMsg = emptyList()
          validationMsgJSON = ""
          return true
     }

     @JvmOverloads
     fun getValidationMsg(delimiter: String = "; "): String {
          return validationMsg.joinToString(separator = delimiter)
     }

     fun getValidationMsgJSON(): String {
          return validationMsgJSON
     }
}

//fun main(){
//     var jsonData = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/poc.json").readText()
//     val jsonSchema = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/poc.xsd").readText()
//     val jsonVal = JSONValidation()
//     println(jsonVal.getValidationMsg())
//
//     // inValid Case
//     println(jsonVal.isValid(jsonSchema,jsonData))
//     println(jsonVal.getValidationMsg("\n"))
//     println(jsonVal.getValidationMsgJSON())
//
//     // Valid Case
//     jsonData = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/poc_good.json").readText()
//     println(jsonVal.isValid(jsonSchema,jsonData))
//     println(jsonVal.getValidationMsg("\n"))
//     println(jsonVal.getValidationMsgJSON())
//}