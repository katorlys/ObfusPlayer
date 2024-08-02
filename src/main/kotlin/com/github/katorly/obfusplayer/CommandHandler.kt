package com.github.katorly.obfusplayer

import com.github.katorly.obfusplayer.commands.Chide
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.command.TabCompleter

class CommandHandler {
    companion object {
        /**
         * Register all the commands of the plugin.
         * Please make sure every argument is corresponding.
         *
         */
        fun registerCommands() {
            val commands = arrayOf(
                "hide"
            )
            val excutors = arrayOf<CommandExecutor>(
                Chide()
            )
            val completers = arrayOf<TabCompleter>(
                Chide()
            )
            for (i in commands.indices) {
                Bukkit.getPluginCommand(commands[i])!!.setExecutor(excutors[i])
                Bukkit.getPluginCommand(commands[i])!!.tabCompleter = completers[i]
            }
        }
    }
}