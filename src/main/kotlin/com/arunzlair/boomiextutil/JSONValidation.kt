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

     private var errorMsgs: List<String> = emptyList()

     fun isValid(jsonSchema: String, jsonData: String): Boolean {
          try {
               val jsonSchemaObj = JSONObject(JSONTokener(jsonSchema))
               val schema: Schema = SchemaLoader.load(jsonSchemaObj)
               schema.validate(JSONObject(jsonData))
          }catch (e: ValidationException){
               errorMsgs = e.causingExceptions.map { ve -> ve.allMessages }.flatten()
               e.causingExceptions.map { ve -> ve.allMessages }.forEach{println("$it")}
               return false
          }catch (e: FileNotFoundException){
               e.printStackTrace()
          }catch (e: IOException){
               e.printStackTrace()
          }
          return true
     }

     fun getValidationMsgs(): String {
          return errorMsgs.joinToString(separator = "; ")
     }
}

//fun main(){
//     val jsonData = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/poc.json").readText()
//     val jsonSchema = File("/Users/arunthangavel/IdeaProjects/BoomiExtUtil/src/main/resources/poc.xsd").readText()
//     val jsonVal = JSONValidation()
//     println(jsonVal.getValidationMsgs())
//     println(jsonVal.isValid(jsonSchema,jsonData))
//     println(jsonVal.getValidationMsgs())
//}