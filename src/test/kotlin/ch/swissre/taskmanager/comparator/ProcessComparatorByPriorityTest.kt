package ch.swissre.taskmanager.comparator

import ch.swissre.taskmanager.entity.Process
import ch.swissre.taskmanager.entity.Process.Priority.*
import org.junit.jupiter.api.Test
import java.util.*

class ProcessComparatorByPriorityTest {

    private val processComparatorByPriority = ProcessComparatorByPriority

    @Test
    internal fun `should High priority when compare High and Medium`() {
        val highProcess = Process(UUID.randomUUID(), HIGH)
        val mediumProcess = Process(UUID.randomUUID(), MEDIUM)

        assert(processComparatorByPriority.compare(highProcess, mediumProcess) > 0)
    }

    @Test
    internal fun `should Medium priority when compare Medium and Low`() {
        val mediumProcess = Process(UUID.randomUUID(), MEDIUM)
        val lowProcess = Process(UUID.randomUUID(), LOW)

        assert(processComparatorByPriority.compare(mediumProcess, lowProcess) > 0)
    }

    @Test
    internal fun `should be equal priority when compare Medium and Medium`() {
        val mediumProcess1 = Process(UUID.randomUUID(), MEDIUM)
        val mediumProcess2 = Process(UUID.randomUUID(), MEDIUM)

        assert(processComparatorByPriority.compare(mediumProcess1, mediumProcess2) == 0)
    }
}