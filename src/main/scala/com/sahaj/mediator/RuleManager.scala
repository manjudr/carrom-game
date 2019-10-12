package com.sahaj.mediator

import com.sahaj.executors.Player
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.sahaj.services.AppConfig

object RuleManager extends Mediator {
  override def getStrikeRules(ruleType: String): StrikeRules = {
    val rules = this.getRules
    rules.strikeRules.filter(r => r.ruleType == ruleType).head
  }

  override def getRules: Rules = {
    val mapper = new ObjectMapper
    mapper.registerModule(DefaultScalaModule)
    mapper.readValue(AppConfig.getConfig("com.sahaj.rules"), classOf[Rules])
  }

  override def validate(player: Player, ruleType: String, redCoins: Option[Int], blockCoins: Option[Int]): GameStatus = {
    val rules: StrikeRules = this.getStrikeRules(ruleType)
    if (ruleType == AppConfig.getConfig("com.sahaj.command.redStrike")) {
      if (redCoins.get == 0) {
        GameStatus(player.getIdentifier, 0, player.getPlayingStatus, 0, player.getBlackCoins, player.getWonStatus)
      } else {
        this.updateStatus(player, rules, false)
      }
    } else {
      if (ruleType == AppConfig.getConfig("com.sahaj.command.failedHit")) {
        this.failedHit(player, rules)
      } else {
        this.updateStatus(player, rules, false)
      }
    }
  }

  def updateStatus(player: Player, value: StrikeRules, isWon: Boolean): GameStatus = {
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
    GameStatus(player.getIdentifier, player.getScore, player.getPlayingStatus, player.getRedCoins, player.getBlackCoins, player.getWonStatus)
  }

  def failedHit(player: Player, value: StrikeRules): GameStatus = {
    var score = 0
    var isPlaying = true
    if (player.getAttempts == value.maxAttempts.getOrElse(3)) {
      score += value.score
      isPlaying = value.playingStatus
      player.setAttempts(0)
    } else {
      player.setAttempts(player.getAttempts + 1)
    }
    updateStatus(player, StrikeRules("FAILED_HIT", score, value.onBlockCoins, value.onRedCoins, false, isPlaying, None), false)
  }

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
