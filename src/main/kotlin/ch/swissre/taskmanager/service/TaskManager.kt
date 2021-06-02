package ch.swissre.taskmanager.service

import ch.swissre.taskmanager.entity.Process

interface TaskManager {
    val maximumCapacity: Int
    val tasks: MutableCollection<Process>

    fun add(process: Process)

    fun killAll() {
        val tasksIterator = tasks.iterator()
        for (tasks in tasksIterator) {
            tasks.kill()
            tasksIterator.remove()
        }
    }

    fun list() = if (tasks.isEmpty()) println("No tasks in TM!") else tasks.iterator().forEach { println(it) }

    companion object {
        const val CAPACITY = 5
    }
}