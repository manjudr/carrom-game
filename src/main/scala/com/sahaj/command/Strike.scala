package com.sahaj.command

import com.sahaj.executors.{CarromBoard, Player}

class Strike(game: CarromBoard) extends Command {

  private val carrom: CarromBoard = game

  override def execute(player: Player): Unit = {
    this.carrom.strike(player)
  }
}
