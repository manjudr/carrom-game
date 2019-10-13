package com.sahaj.command

import com.sahaj.executors.{CarromBoard, Player}
import com.sahaj.mediator.PlayerStatus

class DefunctCoin(game: CarromBoard) extends Command {

  private val carrom: CarromBoard = game

  override def execute(player: Player): PlayerStatus = {
    this.carrom.defunctCoin(player)
  }
}
