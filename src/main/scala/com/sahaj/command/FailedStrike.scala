package com.sahaj.command

import com.sahaj.executors.{CarromBoard, Player}

class FailedStrike(game:CarromBoard) extends Command {

  private val carrom: CarromBoard = game


  override def execute(player: Player): Unit = {
    this.carrom.failedHit(player)
  }
}
