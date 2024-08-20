package com.github.katorly.obfusplayer

import org.bukkit.plugin.java.JavaPlugin

class ObfusPlayer: JavaPlugin() {
    companion object { // skipcq: KT-W1047
        lateinit var INSTANCE: ObfusPlayer
            private set
    }

    override fun onEnable() {
        INSTANCE = this
        CommandHandler.registerCommands()
        logger.info("[ObfusPlayer] Repo: https://github.com/katorlys/ObfusPlayer")
        logger.info("[ObfusPlayer] Author: Katorly")
        logger.info("[ObfusPlayer] ObfusPlayer enabled!")
    }

    override fun onDisable() {
        logger.info("[ObfusPlayer] ObfusPlayer disabled!")
    }
}