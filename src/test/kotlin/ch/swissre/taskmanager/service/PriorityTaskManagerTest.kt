package ch.swissre.taskmanager.service

import ch.swissre.taskmanager.entity.Process
import ch.swissre.taskmanager.entity.Process.Priority.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class PriorityTaskManagerTest {

    private lateinit var priorityTaskManager: PriorityTaskManager

    @BeforeEach
    internal fun setUp() {
        priorityTaskManager = PriorityTaskManager()
    }

    @Test
    internal fun `should add new process`() {
        // arrange
        val process = Process(randomUUID(), MEDIUM)

        // act
        priorityTaskManager.add(process)

        // assert
        priorityTaskManager.list()
    }

    @Test
    internal fun `should remove lowest priority when tm is full`() {

        // arrange
        repeat(TaskManager.CAPACITY) {
            val process = Process(randomUUID(), Process.Priority.values().random())
            println("Add ${process} to TM")
            priorityTaskManager.add(process)
        }

        // act & assert
        val process = Process(randomUUID(), HIGH)
        priorityTaskManager.add(process)
    }

    @Test
    internal fun `should not remove lowest priority when tm is full`() {

        // arrange
        repeat(TaskManager.CAPACITY) {
            val process = Process(randomUUID(), Process.Priority.values().random())
            println("Add ${process} to TM")
            priorityTaskManager.add(process)
        }

        // act & assert
        val process = Process(randomUUID(), LOW)
        priorityTaskManager.add(process)
    }


    @Test
    internal fun `should kill all processes`() {
        // arrange
        priorityTaskManager.add(Process(randomUUID(), MEDIUM))

        // act
        priorityTaskManager.killAll()

        // assert
        priorityTaskManager.list()
    }
}