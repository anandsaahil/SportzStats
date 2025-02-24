package com.example.sportzstats.data.typeAdapter

import com.google.gson.*
import java.lang.reflect.Type
import com.example.sportzstats.domain.model.Teams
import com.example.sportzstats.domain.model.Team

class TeamsTypeAdapter : JsonDeserializer<Teams> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Teams? {
        val jsonObject = json?.asJsonObject ?: return null

        val indiaTeam = jsonObject.getAsJsonObject("4") ?: jsonObject.getAsJsonObject("6")
        val newZealandTeam = jsonObject.getAsJsonObject("5") ?: jsonObject.getAsJsonObject("7")

        val india = context?.deserialize<Team>(indiaTeam, Team::class.java)
        val newZealand = context?.deserialize<Team>(newZealandTeam, Team::class.java)

        return Teams(teamOne = india, teamTwo = newZealand)
    }
}