package ch.swissre.taskmanager.service

import ch.swissre.taskmanager.comparator.ProcessComparatorByPriority
import ch.swissre.taskmanager.entity.Process
import ch.swissre.taskmanager.service.TaskManager.Companion.CAPACITY
import java.util.*

class PriorityTaskManager : TaskManager {
    override val maximumCapacity = CAPACITY
    override val tasks: PriorityQueue<Process> = PriorityQueue(ProcessComparatorByPriority)

    override fun add(process: Process) {

        // Peeking (read the top element heap in the heap) it looks at the smallest element.
        // If this element has lower priority then we remove it.
        if (maximumCapacity == tasks.size && tasks.peek().priority.compareTo(process.priority) < 0) {
            val removedProcess = tasks.poll()
            removedProcess.kill()
        }
        tasks.add(process)
    }

    override fun killAll() {
        val processesToRemove: MutableList<Process> = arrayListOf()
        tasks.forEach {
            it.kill()
            processesToRemove.add(it)
        }
        tasks.removeAll(processesToRemove)
    }
}