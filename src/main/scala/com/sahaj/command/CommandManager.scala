package com.sahaj.command

import com.sahaj.executors.Player

import scala.collection.mutable

object CommandManager {

  private val commandMap = new mutable.HashMap[String, Command]()

  def register(commandName: String, command: Command): Unit = {
    commandMap.put(commandName, command)
  }


  def execute(commandName: String, player: Player): Unit = {
    val command: Command = commandMap(commandName)
    if (command == null) throw new IllegalStateException("no command registered for " + commandName)
    command.execute(player)
  }
}
