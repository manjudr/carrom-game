package com.sahaj.services

import com.sahaj.command._
import com.sahaj.executors.{CarromBoard, DashBoard, Player}
import com.sahaj.mediator.{PlayerStatus, RuleManager}


object CarromBoardService {
  private val carrom = this.getCarromBoard

  def registerPlayer(identifier: String, wonToss: Boolean): Player = {
    new Player(identifier, wonToss)
  }

  def getCarromBoard: CarromBoard = {
    new CarromBoard()
  }

  def init(): Unit = {
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.strike"), new Strike(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.multiStrike"), new MultiStrike(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.strikerStrike"), new StrikerStrike(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.redStrike"), new RedStrike(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.defunctCoin"), new DefunctCoin(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.failedHit"), new FailedStrike(carrom))
  }

  def autoPlay(player1: Player, player2: Player): Unit = {
    while (!player1.getWonStatus || !player2.getWonStatus) {
      val activePlayer = RuleManager.getPlayer(player1, player2)
      val command = DashBoard.promptOptions(activePlayer)
      val playerStatus = CommandManager.execute(command, activePlayer)
      DashBoard.show(None, None, Option(playerStatus))
      val gameStatus = this.getMatchStatus(player1, player2)
      if (gameStatus != null) {
        DashBoard.show(None, None, Option(gameStatus))
      }
    }
  }

  def getMatchStatus(player1: Player, player2: Player): PlayerStatus = {
    RuleManager.getMatchStatus(player1, player2, carrom)
  }

  def play(player: Player, command: String): PlayerStatus = {
    CommandManager.execute(command, player)
  }
}
