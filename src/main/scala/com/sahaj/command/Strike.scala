package com.sahaj.command

import com.sahaj.executors.{CarromBoard, Player}
import com.sahaj.mediator.PlayerStatus

class Strike(game: CarromBoard) extends Command {

  private val carrom: CarromBoard = game

  override def execute(player: Player): PlayerStatus = {
    this.carrom.strike(player)
  }
}
