package com.sahaj.executors

class Player(id: String, wonToss: Boolean) {
  private var identifier: String = id
  private var redCoins: Int = 0
  private var blockCoins: Int = 0
  private var score: Int = 0
  private var isWon: Option[Boolean] = Some(false)
  private var isPlaying: Boolean = wonToss
  private var attempts: Int = 0
  private var foulsCount: Int = 0

  def getIdentifier: String = {
    this.identifier
  }

  def getScore: Int = {
    this.score
  }

  def setScore(score: Int): Unit = {
    this.score = score
  }

  def getAttempts: Int = {
    this.attempts
  }

  def setAttempts(attempt: Int): Unit = {
    this.attempts = attempt
  }

  def getWonStatus: Boolean = {
    this.isWon.getOrElse(false)
  }

  def setWonStatus(status: Option[Boolean]): Unit = {
    this.isWon = status
  }

  def getRedCoins: Int = {
    this.redCoins
  }

  def setRedCoins(redCoins: Int): Unit = {
    this.redCoins = redCoins
  }

  def getBlackCoins: Int = {
    this.blockCoins
  }

  def setBlackCoins(blackCoins: Int): Unit = {
    this.blockCoins = blackCoins
  }

  def setPlayingStatus(isPlaying: Boolean): Unit = {
    this.isPlaying = isPlaying
  }

  def getPlayingStatus: Boolean = {
    this.isPlaying
  }

  def setFoulsCount(count:Int):Unit={
    this.foulsCount = count
  }

  def getFoulsCount:Int={
    this.foulsCount
  }

  def reset(): Unit = {
    this.redCoins = 0
    this.blockCoins = 0
    this.score = 0
    this.isWon = Some(false)
    this.attempts = 0
    this.isPlaying = false
    this.foulsCount = 0
  }


}
