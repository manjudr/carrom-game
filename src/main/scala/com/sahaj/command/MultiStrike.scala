/**
  * Author: Manjunath Davanam <manjunathdavanam@gamil.com>
  */


package com.sahaj.command

import com.sahaj.executors.{CarromBoard, Player}
import com.sahaj.models.PlayerStatus

class MultiStrike(game: CarromBoard) extends Command {
  /**
    * CarromBoard Class instance
    */
  private val carrom: CarromBoard = game

  /**
    *
    * @param player : Player
    * @return PlayerStatus - {identifier: String, score: Int, isPlaying: Boolean, redCoins: Int, blockCoins: Int, isWon: Boolean, status:Option[String]}
    */
  override def execute(player: Player): PlayerStatus = {
    this.carrom.multiStrike(player)
  }
}
