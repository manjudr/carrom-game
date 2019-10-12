package com.sahaj.services

import com.sahaj.command._
import com.sahaj.executors.{CarromBoard, DashBoard, Player}
import com.sahaj.mediator.{GameStatus, RuleManager}


object CarromBoardService {
  private val carrom = new CarromBoard()

  def registerPlayer(identifier: String, wonToss: Boolean): Player = {
    new Player(identifier, wonToss)
  }

  def init(): Unit = {
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.strike"), new Strike(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.multiStrike"), new MultiStrike(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.strikerStrike"), new StrikerStrike(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.redStrike"), new RedStrike(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.defunctCoin"), new DefunctCoin(carrom))
    CommandManager.register(AppConfig.getConfig("com.sahaj.command.failedHit"), new FailedStrike(carrom))
  }

  def play(player1: Player, player2: Player): Unit = {
    while (!player1.getWonStatus || !player2.getWonStatus) {
      val activePlayer = RuleManager.getPlayer(player1, player2)
      val command = DashBoard.promptOptions(activePlayer)
      CommandManager.execute(command, activePlayer)
      val gameStatus: GameStatus = RuleManager.getMatchStatus(player1, player2, carrom)
      if (gameStatus != null) {
        DashBoard.show(None, None, Option(gameStatus))
      }
    }
  }
}
