package ch.swissre.taskmanager.comparator

import ch.swissre.taskmanager.entity.Process

class ProcessComparatorByPriority {

    companion object : Comparator<Process> {
        override fun compare(a: Process, b: Process): Int = a.priority.rank.compareTo(b.priority.rank)
    }
}