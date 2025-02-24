package com.example.sportzstats.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    @SerializedName("Matchdetail")
    val matchDetail: MatchDetail,

    @SerializedName("Nuggets")
    val nuggets: List<String>,

    @SerializedName("Innings")
    val innings: List<Inning>,

    @SerializedName("Teams")
    val teams: Teams,

    @SerializedName("Notes")
    val notes: Notes
) : Parcelable

@Parcelize
data class MatchDetail(
    @SerializedName("Team_Home")
    val teamHome: String,

    @SerializedName("Team_Away")
    val teamAway: String,

    @SerializedName("Match")
    val match: Match,

    @SerializedName("Series")
    val series: Series,

    @SerializedName("Venue")
    val venue: Venue,

    @SerializedName("Officials")
    val officials: Officials,

    @SerializedName("Weather")
    val weather: String,

    @SerializedName("Tosswonby")
    val tossWonBy: String,

    @SerializedName("Status")
    val status: String,

    @SerializedName("Status_Id")
    val statusId: String,

    @SerializedName("Player_Match")
    val playerMatch: String,

    @SerializedName("Result")
    val result: String,

    @SerializedName("Winningteam")
    val winningTeam: String,

    @SerializedName("Winmargin")
    val winMargin: String,

    @SerializedName("Equation")
    val equation: String,
) : Parcelable

@Parcelize
data class Match(
    @SerializedName("Livecoverage")
    val liveCoverage: String,

    @SerializedName("Id")
    val id: String,

    @SerializedName("Code")
    val code: String,

    @SerializedName("League")
    val league: String,

    @SerializedName("Number")
    val number: String,

    @SerializedName("Type")
    val type: String,

    @SerializedName("Date")
    val date: String,

    @SerializedName("Time")
    val time: String,

    @SerializedName("Offset")
    val offset: String,

    @SerializedName("Daynight")
    val dayNight: String
) : Parcelable

@Parcelize
data class Series(
    @SerializedName("Id")
    val id: String,

    @SerializedName("Name")
    val name: String,

    @SerializedName("Status")
    val status: String,

    @SerializedName("Tour")
    val tour: String,

    @SerializedName("Tour_Name")
    val tourName: String
) : Parcelable

@Parcelize
data class Notes(
    @SerializedName("1")
    val notesOne: List<String>,

    @SerializedName("2")
    val notesTwo: List<String>
) : Parcelable

@Parcelize
data class Venue(
    @SerializedName("Id")
    val id: String,

    @SerializedName("Name")
    val name: String
) : Parcelable

@Parcelize
data class Officials(
    @SerializedName("Umpires")
    val umpires: String,

    @SerializedName("Referee")
    val referee: String
) : Parcelable

@Parcelize
data class Teams(
    @SerializedName("4")
    val teamOne: Team?,

    @SerializedName("5")
    val teamTwo: Team?
) : Parcelable

@Parcelize
data class Team(
    @SerializedName("Name_Full")
    val nameFull: String,

    @SerializedName("Name_Short")
    val nameShort: String,

    @SerializedName("Players")
    val players: Map<String, Player>
) : Parcelable

@Parcelize
data class Player(
    @SerializedName("Position")
    val position: String,

    @SerializedName("Name_Full")
    val nameFull: String,

    @SerializedName("Iscaptain")
    val isCaptain: Boolean? = null,

    @SerializedName("Iskeeper")
    val isKeeper: Boolean? = null,

    @SerializedName("Batting")
    val batting: BattingStats,

    @SerializedName("Bowling")
    val bowling: BowlingStats
)  : Parcelable

@Parcelize
data class BattingStats(
    @SerializedName("Style")
    val style: String?,

    @SerializedName("Average")
    val average: String?,

    @SerializedName("Strikerate")
    val strikeRate: String?,

    @SerializedName("Runs")
    val runs: String?
) : Parcelable

@Parcelize
data class BowlingStats(
    @SerializedName("Style")
    val style: String?,

    @SerializedName("Average")
    val average: String?,

    @SerializedName("Economyrate")
    val economyRate: String?,

    @SerializedName("Wickets")
    val wickets: String?
): Parcelable

@Parcelize
data class Inning(
    @SerializedName("Number")
    val number: String,

    @SerializedName("Battingteam")
    val battingTeam: String,

    @SerializedName("Total")
    val total: String,

    @SerializedName("Wickets")
    val wickets: String,

    @SerializedName("Overs")
    val overs: String,

    @SerializedName("Runrate")
    val runRate: String,

    @SerializedName("Byes")
    val byes: String,

    @SerializedName("Legbyes")
    val legByes: String,

    @SerializedName("Wides")
    val wides: String,

    @SerializedName("Noballs")
    val noBalls: String,

    @SerializedName("Penalty")
    val penalty: String,

    @SerializedName("AllottedOvers")
    val allottedOvers: String,

    @SerializedName("Batsmen")
    val batsmen: List<Batsman>,

    @SerializedName("Partnership_Current")
    val partnershipCurrent: Partnership,

    @SerializedName("Bowlers")
    val bowlers: List<Bowler>,

    @SerializedName("FallofWickets")
    val fallOfWickets: List<FallOfWicket>,

    @SerializedName("PowerPlay")
    val powerPlay: PowerPlay
): Parcelable

@Parcelize
data class Batsman(
    @SerializedName("Batsman")
    val batsman: String,

    @SerializedName("Runs")
    val runs: String,

    @SerializedName("Balls")
    val balls: String,

    @SerializedName("Fours")
    val fours: String,

    @SerializedName("Sixes")
    val sixes: String,

    @SerializedName("Dots")
    val dots: String,

    @SerializedName("Strikerate")
    val strikeRate: String,

    @SerializedName("Dismissal")
    val dismissal: String,

    @SerializedName("Howout")
    val howOut: String,

    @SerializedName("Bowler")
    val bowler: String,

    @SerializedName("Fielder")
    val fielder: String
) : Parcelable

@Parcelize
data class Partnership(
    @SerializedName("Runs")
    val runs: String,

    @SerializedName("Balls")
    val balls: String,

    @SerializedName("Batsmen")
    val batsmen: List<BatsmanDetails>
) : Parcelable

@Parcelize
data class BatsmanDetails(
    @SerializedName("Batsman")
    val batsman: String,

    @SerializedName("Runs")
    val runs: String,

    @SerializedName("Balls")
    val balls: String
) : Parcelable

@Parcelize
data class Bowler(
    @SerializedName("Bowler")
    val bowler: String,

    @SerializedName("Overs")
    val overs: String,

    @SerializedName("Maidens")
    val maidens: String,

    @SerializedName("Runs")
    val runs: String,

    @SerializedName("Wickets")
    val wickets: String,

    @SerializedName("Economyrate")
    val economyRate: String,

    @SerializedName("Noballs")
    val noBalls: String,

    @SerializedName("Wides")
    val wides: String,

    @SerializedName("Dots")
    val dots: String
) : Parcelable

@Parcelize
data class FallOfWicket(
    @SerializedName("Batsman")
    val batsman: String,

    @SerializedName("Score")
    val score: String,

    @SerializedName("Overs")
    val overs: String
) : Parcelable

@Parcelize
data class PowerPlay(
    @SerializedName("PP1")
    val pp1: String,

    @SerializedName("PP2")
    val pp2: String
) : Parcelable