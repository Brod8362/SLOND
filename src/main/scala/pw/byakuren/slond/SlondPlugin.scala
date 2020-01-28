package pw.byakuren.slond

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class SlondPlugin extends JavaPlugin {

  override def onLoad(): Unit = super.onLoad()

  override def onDisable(): Unit = super.onDisable()

  override def onEnable(): Unit = {
    Bukkit.getScheduler.scheduleSyncRepeatingTask(this, new SlondAlerter, 20, 10)
  }
}
