package pw.byakuren.slond

import org.bukkit.entity.Player
import org.bukkit.{Bukkit, ChatColor, Sound}

import scala.jdk.CollectionConverters._

class SlondAlerter extends Runnable {

  override def run(): Unit = {
    val allOnlinePlayers = Bukkit.getServer.getOnlinePlayers
    if (allOnlinePlayers.size()<2) return
    for ((_,players) <- allOnlinePlayers.asScala.groupBy(_.getWorld)) {
      val (playersInBed, playersNotInBed) = players.filter(!_.isDead).partition(_.isSleeping)
      if (playersInBed.nonEmpty) {
        for (player <- playersInBed) {
          if (playersNotInBed.nonEmpty) {
            alertSleepingPlayer(player, playersNotInBed)
          } else {
            player.sendTitle("", ChatColor.GREEN+"All clear!", 0, 20, 0)
          }
        }
        for (player <- playersNotInBed) {
          player.sendTitle("", ChatColor.RED+"SLOND!", 5, 20, 0)
          player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1f, 1f)
        }
      }
    }
  }

  def alertSleepingPlayer(p: Player, needToSleep: Iterable[Player]) {
    p.sendTitle("Waiting on...", ChatColor.RED+(needToSleep.map(_.getName)).mkString(
      s"${ChatColor.RESET},${ChatColor.RED}"), 0, 20, 0)
  }
}
