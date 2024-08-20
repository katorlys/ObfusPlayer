package com.github.katorly.obfusplayer.commands

import com.github.katorly.obfusplayer.playername.obfuscatePlayerName
import com.github.katorly.obfusplayer.playername.toggleObfuscation
import com.github.katorly.obfusplayer.playername.unobfuscatePlayerName
import com.github.katorly.obfusplayer.utils.sm
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

/**
 * The /hide command.
 *
 */
class Chide : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (command.name.equals("hide", ignoreCase = true)) {
            if (sender is Player) {
                if (sender.isOp || sender.hasPermission("obfusplayer.hide")) {
                    if (args.isEmpty()) {
                        toggleObfuscation(sender)
                    } else if (args.size == 1) {
                        if (args[0] == "on") {
                            obfuscatePlayerName(sender)
                        } else if (args[0] == "off") {
                            unobfuscatePlayerName(sender)
                        }
                    } else {
                        sender.sm("&e&lObfusPlayer &r&8>> &7Usage: &f/hide [on|off].")
                    }
                } else {
                    sender.sm("&e&lObfusPlayer &r&8>> &7Insufficient permission!")
                }
            } else {
                sender.sm("&e&lObfusPlayer &r&8>> &7Only players can use this command!")
            }
        }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>,
    ): List<String>? {
        if (sender !is Player) {
            return null
        } else if (sender.isOp || sender.hasPermission("obfusplayer.hide")) {
            val sub: MutableList<String> = ArrayList()
            sub.add("on")
            sub.add("off")
            return sub
        }
        return null
    }
}
