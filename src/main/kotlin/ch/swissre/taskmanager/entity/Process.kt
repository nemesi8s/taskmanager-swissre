package ch.swissre.taskmanager.entity

import java.util.*

data class Process(
    val pid: UUID,
    val priority: Priority
) {
    enum class Priority(val rank: Int) { LOW(10), MEDIUM(100), HIGH(200) }

    fun kill() {
        println("The process ${pid} is killed")
    }
}