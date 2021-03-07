package com.example.bank

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Account(
        val id: String,
        val accountName: String,
        val amount: String,
        val iban: String,
        val currency: String
){
    class Deserializer: ResponseDeserializable<Array<Account>> {
        override fun deserialize(content: String): Array<Account>? = Gson().fromJson(content, Array<Account>::class.java)
    }
}