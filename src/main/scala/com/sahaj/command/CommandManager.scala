/**
  * Author: Manjunath Davanam <manjunathdavanam@gamil.com>
  */


package com.sahaj.command

import com.sahaj.executors.Player
import com.sahaj.models.PlayerStatus

import scala.collection.mutable

/**
  * Manager which handles all the commands.
  */
object CommandManager {

  private val commandMap = new mutable.HashMap[String, Command]()

  /**
    * Method to register the command
    *
    * @param commandName - Name of the command
    * @param command     - Command instance
    */
  def register(commandName: String, command: Command): Unit = {
    commandMap.put(commandName, command)
  }

  /**
    * Method to execute the particular command instance (strike, multiStrike, redStrike.. etc.,)
    *
    * @param commandName - Name of the command
    * @param player      - Player
    * @return - PlayerStatus of the execution
    */
  def execute(commandName: String, player: Player): PlayerStatus = {
    val command: Command = commandMap(commandName)
    if (command == null) throw new IllegalStateException("no command registered for " + commandName)
    command.execute(player)
  }
}
