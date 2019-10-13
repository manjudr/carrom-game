package com.sahaj.command

import com.sahaj.executors.Player
import com.sahaj.mediator.PlayerStatus

import scala.collection.mutable

object CommandManager {

  private val commandMap = new mutable.HashMap[String, Command]()

  def register(commandName: String, command: Command): Unit = {
    commandMap.put(commandName, command)
  }


  def execute(commandName: String, player: Player): PlayerStatus = {
    val command: Command = commandMap(commandName)
    if (command == null) throw new IllegalStateException("no command registered for " + commandName)
    command.execute(player)
  }
}
