package com.sahaj.command

import com.sahaj.executors.{CarromBoard, Player}

class DefunctCoin(game: CarromBoard) extends Command {

  private val carrom: CarromBoard = game

  override def execute(player: Player): Unit = {
    this.carrom.defunctCoin(player)
  }
}
