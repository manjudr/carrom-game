/**
  * Author: Manjunath Davanam <manjunathdavanam@gamil.com>
  */


package com.sahaj.mediator

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.sahaj.executors.{CarromBoard, Player}
import com.sahaj.models.{PlayerStatus, Rules, StrikeRules}
import com.sahaj.services.AppConfig

/**
  * RuleManager is like Umpire - Which validate the result, updates the score and status
  */
object RuleManager extends Mediator {

  /**
    * Method to get the strike rule
    *
    * @param ruleType - rule name
    * @return - StrikeRules - Particular strike rule
    */
  override def getStrikeRules(ruleType: String): StrikeRules = {
    val rules = this.getRules
    rules.strikeRules.filter(r => r.ruleType == ruleType).head
  }

  /**
    * Method to get the rules - Which overrides the mediator trait
    *
    * @return - Rules
    */
  override def getRules: Rules = {
    val mapper = new ObjectMapper
    mapper.registerModule(DefaultScalaModule)
    mapper.readValue(AppConfig.getConfig("com.sahaj.rules"), classOf[Rules])
  }

  /**
    * Method to validate the game, for every strike
    *
    * @param player     - Player
    * @param ruleType   - name of the strike rule
    * @param redCoins   - Number of red coins are present in the carrom instance
    * @param blockCoins - Number of block coins are present in the carrom instance
    * @return - Player status
    */
  override def validate(player: Player, ruleType: String, redCoins: Option[Int], blockCoins: Option[Int]): PlayerStatus = {
    val rules: StrikeRules = this.getStrikeRules(ruleType)
    if (ruleType == AppConfig.getConfig("com.sahaj.command.redStrike")) {
      if (redCoins.get == 0) {
        PlayerStatus(player.getIdentifier, 0, player.getPlayingStatus, 0, player.getBlackCoins, player.getWonStatus, None)
      } else {
        this.updateStatus(player, rules, None)
      }
    } else {
      if (ruleType == AppConfig.getConfig("com.sahaj.command.failedHit")) {
        this.failedHit(player, rules)
      } else {
        this.updateStatus(player, rules, None)
      }
    }
  }

  /**
    * Method to update the status of the player - it can score, wonstatus etc..,
    *
    * @param player - Player
    * @param value  - StrikeRule
    * @param isWon  - isPlayer won
    * @return - Player status
    */
  private def updateStatus(player: Player, value: StrikeRules, isWon: Option[Boolean]): PlayerStatus = {
    if (!value.isValidStrike) player.setFoulsCount(player.getFoulsCount + 1)
    if (player.getFoulsCount == this.getRules.maxFouls) {
      value.score = value.score + this.getRules.onFouls
      player.setFoulsCount(0)
    }
    player.setScore(player.getScore + value.score)
    player.setRedCoins(value.onRedCoins)
    player.setBlackCoins(value.onBlockCoins)
    player.setPlayingStatus(value.playingStatus)
    player.setWonStatus(isWon)
    PlayerStatus(player.getIdentifier, player.getScore, player.getPlayingStatus, player.getRedCoins, player.getBlackCoins, player.getWonStatus, None)
  }

  /**
    * To check wheather game is won or not. - Game status
    *
    * @param player1 - Player instnace
    * @param player2 - Player instance
    * @param carrom  - CarromBoard instance
    * @return - Game status
    */
  def getMatchStatus(player1: Player, player2: Player, carrom: CarromBoard): PlayerStatus = {
    val minPointsToWon = this.getRules.minPointsToWon
    val minDiffToWon = this.getRules.opponMinDiff

    if (player1.getScore >= minPointsToWon && ((player1.getScore - player2.getScore) >= minDiffToWon)) {
      player1.setWonStatus(Some(true))
      PlayerStatus(player1.getIdentifier, player1.getScore, player1.getPlayingStatus, player1.getRedCoins, player1.getBlackCoins, player1.getWonStatus, Option("WON"))
    }
    else if (player2.getScore >= minPointsToWon && ((player2.getScore - player1.getScore) >= minDiffToWon)) {
      player2.setWonStatus(Some(true))
      PlayerStatus(player2.getIdentifier, player2.getScore, player2.getPlayingStatus, player2.getRedCoins, player2.getBlackCoins, player2.getWonStatus, Option("WON"))
    } else {
      null
    }

  }

  /**
    * Method to handle the failed hit logic
    *
    * @param player - Player instance
    * @param value  - strike rule
    * @return - Player status
    */
  private def failedHit(player: Player, value: StrikeRules): PlayerStatus = {
    var score = 0
    var isPlaying = true
    if (player.getAttempts == value.maxAttempts.getOrElse(3)) {
      score += value.score
      isPlaying = value.playingStatus
      player.setAttempts(0)
    } else {
      player.setAttempts(player.getAttempts + 1)
    }
    updateStatus(player, StrikeRules("FAILED_HIT", score, value.onBlockCoins, value.onRedCoins, false, isPlaying, None), None)
  }

  /**
    * Method to get the active palyer - Who has to play the game on every strike
    *
    * @param player1 - Player
    * @param player2 - Player
    * @return - Player
    */
  def getPlayer(player1: Player, player2: Player): Player = {
    if (player1.getPlayingStatus) {
      player2.setPlayingStatus(true)
      player1
    } else {
      player1.setPlayingStatus(true)
      player2
    }
  }

}
