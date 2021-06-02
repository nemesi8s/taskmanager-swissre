package ch.swissre.taskmanager.service

import ch.swissre.taskmanager.entity.Process
import ch.swissre.taskmanager.service.TaskManager.Companion.CAPACITY

class DefaultTaskManager : TaskManager {
    override val maximumCapacity = CAPACITY
    override val tasks: MutableList<Process> = arrayListOf()

    override fun add(process: Process) {
        if (maximumCapacity == tasks.size) {
            throw IllegalStateException("Process list has reached maximum capacity.")
        }
        tasks.add(process)
    }
}