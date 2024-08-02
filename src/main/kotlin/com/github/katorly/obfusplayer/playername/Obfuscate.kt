package com.github.katorly.obfusplayer.playername

import com.github.katorly.obfusplayer.utils.color
import com.github.katorly.obfusplayer.utils.sm
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Team
import java.util.*


private val obfuscatedPlayers = HashMap<UUID, Boolean>()
private val scoreboard = Bukkit.getScoreboardManager()!!.mainScoreboard

fun toggleObfuscation(player: Player) {
    if (obfuscatedPlayers.containsKey(player.uniqueId) && obfuscatedPlayers[player.uniqueId] == true) {
        unobfuscatePlayerName(player)
    } else {
        obfuscatePlayerName(player)
    }
}

fun obfuscatePlayerName(player: Player) {
    if (obfuscatedPlayers.containsKey(player.uniqueId) && obfuscatedPlayers[player.uniqueId] == true) {
        player.sm("&e&lObfusPlayer &r&8>> &7Your name is already hidden!")
        return
    }

    obfuscatedPlayers[player.uniqueId] = true
    val obfuscatedName: String = "&k".color() + player.name

    // Add player to obfuscation team
    val team = if (scoreboard.getTeam("obfuscate") != null) scoreboard.getTeam("obfuscate") else scoreboard.registerNewTeam("obfuscate")
    team!!.color = ChatColor.MAGIC
    team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS)
    team.addEntry(player.name)

    player.setPlayerListName(obfuscatedName)
//    player.setDisplayName(obfuscatedName)
    player.customName = obfuscatedName

    player.sm("&e&lObfusPlayer &r&8>> &7Your name is now hidden.")
}

fun unobfuscatePlayerName(player: Player) {
    if (obfuscatedPlayers.containsKey(player.uniqueId) && obfuscatedPlayers[player.uniqueId] == false) {
        player.sm("&e&lObfusPlayer &r&8>> &7Your name is already visible!")
        return
    }

    obfuscatedPlayers[player.uniqueId] = false

    // Remove player from obfuscation team
    val team = scoreboard.getTeam("obfuscate")
    if (team != null && team.hasEntry(player.name)) team.removeEntry(player.name)

    // Set to null will display player's actual name
    player.setPlayerListName(null)
//    player.setDisplayName(player.name)
    player.customName = player.name

    player.sm("&e&lObfusPlayer &r&8>> &7Your name is now visible.")
}
