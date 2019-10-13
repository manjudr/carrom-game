package com.sahaj.executors

import com.sahaj.mediator.{PlayerStatus, RuleManager}
import com.sahaj.services.AppConfig

class CarromBoard {

  private var redCoins: Int = 1
  private var bloackCoins: Int = 9

  def strike(player: Player): PlayerStatus = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.strike"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    response

  }

  def multiStrike(player: Player): PlayerStatus = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.multiStrike"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    response
  }

  def redStrike(player: Player): PlayerStatus = {
    println("red")
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.redStrike"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    response
  }

  def strikerStrike(player: Player): PlayerStatus = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.strikerStrike"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    response
  }

  def defunctCoin(player: Player): PlayerStatus = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.defunctCoin"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    response
  }

  def failedHit(player: Player): PlayerStatus = {
    val response = RuleManager.validate(player, AppConfig.getConfig("com.sahaj.command.failedHit"), Option(this.redCoins), Option(this.bloackCoins))
    this.updateCarromStatus(response.redCoins, response.blockCoins)
    response
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
