# Carrom Board

## Introduction:

Service which helps to play the carrom board game with multiple user.

## Architecture:

![alt text](https://github.com/manjudr/carrom-game/raw/master/CarromBoard-Architecture.png)


## Game Rules:

```js
 val rules = {
  "strikeRules": [
    {
      "ruleType": "STRIKE",
      "score": 1,
      "onBlockCoins": 1,
      "onRedCoins": 0,
      "playingStatus": true,
      "isValidStrike": true
    },
    {
      "ruleType": "MULTI_STRIKE",
      "score": 2,
      "onBlockCoins": -2,
      "onRedCoins": 0,
      "playingStatus": true,
      "isValidStrike": true
    },
    {
      "ruleType": "RED_STRIKE",
      "score": 3,
      "onBlockCoins": 0,
      "onRedCoins": 1,
      "playingStatus": true,
      "isValidStrike": true
    },
    {
      "ruleType": "STRIKER_STRIKE",
      "score": -1,
      "onBlockCoins": 1,
      "onRedCoins": 0,
      "playingStatus": false,
      "isValidStrike": false
    },
    {
      "ruleType": "DEFUNCT_COIN",
      "score": -2,
      "onBlockCoins": 0,
      "onRedCoins": 0,
      "playingStatus": false,
      "isValidStrike": false
    },
    {
      "ruleType": "FAILED_HIT",
      "score": -1,
      "onBlockCoins": 0,
      "onRedCoins": 0,
      "playingStatus": false,
      "isValidStrike": false,
      "maxAttempts": 3
    }
  ],
  "onFouls": -1,
  "maxFouls": 3,
  "minPointsToWon": 5,
  "opponMinDiff": 3
}
```

## Build / Run
 
 1. Bulid : `mvn clean install`
 2. Compile: `mvn compile`
 3. Run Test: `mvn test`

> Git link : https://github.com/manjudr/carrom-game
