package com.sahaj.command

import com.sahaj.executors.Player

trait Command {
  def execute(player: Player): Unit
}
