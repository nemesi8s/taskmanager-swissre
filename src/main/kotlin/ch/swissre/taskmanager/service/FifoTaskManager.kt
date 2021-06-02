package ch.swissre.taskmanager.service

import ch.swissre.taskmanager.entity.Process
import ch.swissre.taskmanager.service.TaskManager.Companion.CAPACITY
import java.util.*

class FifoTaskManager : TaskManager {
    override val maximumCapacity = CAPACITY
    override val tasks: Queue<Process> = LinkedList()

    override fun add(process: Process) {
        if (maximumCapacity == tasks.size) {
            val removedProcess = tasks.poll()
            removedProcess.kill()
        }
        tasks.add(process)
    }
}