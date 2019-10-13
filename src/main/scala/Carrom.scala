import com.sahaj.executors.Player
import com.sahaj.services.CarromBoardService

object Carrom {
  def main(args: Array[String]): Unit = {
    CarromBoardService.init()
    val player1: Player = CarromBoardService.registerPlayer("Manju", true)
    val player2: Player = CarromBoardService.registerPlayer("Manoj", false)
    CarromBoardService.autoPlay(player1, player2)
  }
}
