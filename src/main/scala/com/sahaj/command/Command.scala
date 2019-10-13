/**
  * Author: Manjunath Davanam <manjunathdavanam@gamil.com>
  */

package com.sahaj.command

import com.sahaj.executors.Player
import com.sahaj.models.PlayerStatus

/**
  * Command Interface
  */
trait Command {
  /**
    * Interface method to execute the commands
    *
    * @param player : Player
    * @return PlayerStatus - {identifier: String, score: Int, isPlaying: Boolean, redCoins: Int, blockCoins: Int, isWon: Boolean, status:Option[String]}
    */
  def execute(player: Player): PlayerStatus
}
