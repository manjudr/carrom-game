package com.sahaj.command

import com.sahaj.executors.Player

/**
  * Command Interface
  */
trait Command {
  def execute(player: Player): Unit
}
