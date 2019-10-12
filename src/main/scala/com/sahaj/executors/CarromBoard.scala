package com.sahaj.executors

import com.sahaj.mediator.RuleManager
import com.sahaj.services.AppConfig

class CarromBoard {

  private var redCoins: Int = 1
  private var bloackCoins: Int = 9

  def strike(player: Player): Unit = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.strike"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    DashBoard.show(Some(player), None, None)

  }

  def multiStrike(player: Player): Unit = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.multiStrike"), Option(this.redCoins), Option(this.bloackCoins))
    val status = if (response.isWon) "WON" else "NOT_WON"
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    DashBoard.show(Some(player), None, None)
  }

  def redStrike(player: Player): Unit = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.redStrike"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    DashBoard.show(Some(player), None, None)
  }

  def strikerStrike(player: Player): Unit = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.strikerStrike"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    DashBoard.show(Some(player), None, None)
  }

  def defunctCoin(player: Player): Unit = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.defunctCoin"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    DashBoard.show(Some(player), None, None)
  }

  def failedHit(player: Player): Unit = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.failedHit"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    DashBoard.show(Some(player), None, None)
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

  private def updateCarromStatus(redCoin: Int, blockCoin: Int): Unit = {
    this.setRedCoins(this.getRedCoins - redCoin)
    this.setBloackCoins(this.getBloackCoins - blockCoin)
  }

}
