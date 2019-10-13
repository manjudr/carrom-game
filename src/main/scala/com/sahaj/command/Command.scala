package com.sahaj.command

import com.sahaj.executors.Player
import com.sahaj.mediator.PlayerStatus

/**
  * Command Interface
  */
trait Command {
  def execute(player: Player): PlayerStatus
}
