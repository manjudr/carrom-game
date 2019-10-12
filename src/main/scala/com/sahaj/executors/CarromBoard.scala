package com.sahaj.executors

import com.sahaj.mediator.RuleManager
import com.sahaj.services.AppConfig

class CarromBoard {

  private var redCoins: Int = 1
  private var bloackCoins: Int = 9
  private var isMatchWon: String = "NOT_WON"

  def strike(player: Player): Unit = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.strike"), Option(this.redCoins), Option(this.bloackCoins))
    val status = if (response.isWon) "WON" else "NOT_WON"
    this.updateCarromStatus(response.redCoins, response.blockCoins, status)
    DashBoard.show(Some(player), None)

  }

  def multiStrike(player: Player): Unit = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.multiStrike"), Option(this.redCoins), Option(this.bloackCoins))
    val status = if (response.isWon) "WON" else "NOT_WON"
    this.updateCarromStatus(response.redCoins, response.blockCoins, status)
    DashBoard.show(Some(player), None)
  }

  def redStrike(player: Player): Unit = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.redStrike"), Option(this.redCoins), Option(this.bloackCoins))
    val status = if (response.isWon) "WON" else "NOT_WON"
    this.updateCarromStatus(response.redCoins, response.blockCoins, status)
    DashBoard.show(Some(player), None)
  }

  def strikerStrike(player: Player): Unit = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.strikerStrike"), Option(this.redCoins), Option(this.bloackCoins))
    val status = if (response.isWon) "WON" else "NOT_WON"
    this.updateCarromStatus(response.redCoins, response.blockCoins, status)
    DashBoard.show(Some(player), None)
  }

  def defunctCoin(player: Player): Unit = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.defunctCoin"), Option(this.redCoins), Option(this.bloackCoins))
    val status = if (response.isWon) "WON" else "NOT_WON"
    this.updateCarromStatus(response.redCoins, response.blockCoins, status)
    DashBoard.show(Some(player), None)
  }

  def failedHit(player: Player): Unit = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.failedHit"), Option(this.redCoins), Option(this.bloackCoins))
    val status = if (response.isWon) "WON" else "NOT_WON"
    this.updateCarromStatus(response.redCoins, response.blockCoins, status)
    DashBoard.show(Some(player), None)
  }

  def setBloackCoins(coins: Int): Unit = {
    this.bloackCoins = bloackCoins
  }

  def setRedCoins(coins: Int): Unit = {
    this.redCoins = coins
  }

  def getBloackCoins: Int = {
    this.bloackCoins
  }

  def getRedCoins: Int = {
    this.redCoins
  }

  def getMatchWonStatus: String = {
    this.isMatchWon
  }

  def setMatchWonStatus(status: String): Unit = {
    this.isMatchWon = status
  }

  private def updateCarromStatus(redCoin: Int, blockCoin: Int, status: String): Unit = {
    this.setRedCoins(this.getRedCoins - redCoin)
    this.setBloackCoins(this.getBloackCoins - blockCoin)
    this.setMatchWonStatus(status)
  }

}
