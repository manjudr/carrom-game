package com.sahaj.executors

import com.sahaj.mediator.PlayerStatus
import com.sahaj.services.AppConfig

trait MainDashBoard {
  def show(player: Option[Player], carrom: Option[CarromBoard], status: Option[PlayerStatus]): Unit

  def clear: Boolean

  def read: Int

}

object DashBoard extends MainDashBoard {
  override def clear: Boolean = ???

  override def show(player: Option[Player], carram: Option[CarromBoard], status: Option[PlayerStatus]): Unit = {
    if (player.isDefined) {
      println("==============PLAYER====================")

      println("IDENTIFIER: " + player.get.getIdentifier)
      println("SCORE: " + player.get.getScore)
      println("WON: " + player.get.getWonStatus)
      println("ATTEMPT: " + player.get.getAttempts)
      println("PLAYING STATUS: " + player.get.getPlayingStatus)
      println("FOULS COUNT:" + player.get.getFoulsCount)
      println("========================================")
    }
    else if (status.isDefined) {
      println("==================GAME STATUS  ===============")
      println("IDENTIFIER -> " + status.get.identifier)
      println("WON -> " + status.get.isWon)
      println("SCORE -> " + status.get.score)
      println("========================================")
    }
    else {
      println("==============CARROM====================")

      println("RED COINS" + carram.get.getRedCoins)
      println("BLOCK COINS" + carram.get.getBloackCoins)

      println("========================================")
    }
  }

  override def read: Int = {
    scala.io.StdIn.readInt()
  }

  def promptOptions(player: Player): String = {
    println("..........................................................")
    println(player.getIdentifier.toUpperCase() + ": IT'S YOUR TURN, PLEASE SELECT THE CHOICE NUMBER")
    println("1." + AppConfig.getConfig("com.sahaj.command.strike"))
    println("2." + AppConfig.getConfig("com.sahaj.command.multiStrike"))
    println("3." + AppConfig.getConfig("com.sahaj.command.redStrike"))
    println("4." + AppConfig.getConfig("com.sahaj.command.strikerStrike"))
    println("5." + AppConfig.getConfig("com.sahaj.command.defunctCoin"))
    println("6." + AppConfig.getConfig("com.sahaj.command.failedHit"))
    println("7. EXIT")
    println("..........................................................")
    val number = this.read
    number match {
      case 1 => AppConfig.getConfig("com.sahaj.command.strike")
      case 2 => AppConfig.getConfig("com.sahaj.command.multiStrike")
      case 3 => AppConfig.getConfig("com.sahaj.command.redStrike")
      case 4 => AppConfig.getConfig("com.sahaj.command.strikerStrike")
      case 5 => AppConfig.getConfig("com.sahaj.command.defunctCoin")
      case 6 => AppConfig.getConfig("com.sahaj.command.failedHit")
      case 7 =>
        println("System exit..")
        System.exit(0)
        "EXIT"
      case _ =>
        println("INVALID OPTION:" + number)
        "INVALID"
    }

  }
}



