package com.sahaj.mediator

import com.sahaj.executors.Player


case class StrikeRules(var ruleType: String,
                       var score: Int,
                       var onBlockCoins: Int,
                       var onRedCoins: Int,
                       var isValidStrike: Boolean,
                       var playingStatus: Boolean,
                       var maxAttempts: Option[Int]
                      )

case class Rules(strikeRules: List[StrikeRules],
                 onFouls: Int,
                 maxFouls: Int,
                 minPointsToWon: Int,
                 opponMinDiff: Int
                )


case class PlayerStatus(identifier: String, score: Int, isPlaying: Boolean, redCoins: Int, blockCoins: Int, isWon: Boolean, status:Option[String])


trait Mediator {
  def getStrikeRules(name: String): StrikeRules

  def getRules: Rules

  def validate(player: Player, ruleType: String, blockCoins: Option[Int], redCoins: Option[Int]): PlayerStatus

}

