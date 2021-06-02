package ch.swissre.taskmanager.service

import ch.swissre.taskmanager.entity.Process
import ch.swissre.taskmanager.entity.Process.Priority.MEDIUM
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class FifoTaskManagerTest {

    private lateinit var fifoTaskManager: FifoTaskManager

    @BeforeEach
    internal fun setUp() {
        fifoTaskManager = FifoTaskManager()
    }

    @Test
    internal fun `should add new process`() {
        // arrange
        val process = Process(randomUUID(), MEDIUM)

        // act
        fifoTaskManager.add(process)

        // assert
        fifoTaskManager.list()
    }

    @Test
    internal fun `should remove first element when tm is full`() {

        // arrange
        repeat(TaskManager.CAPACITY) {
            val process = Process(randomUUID(), MEDIUM)
            if (it == 0) println("Add ${process} to TM")
            fifoTaskManager.add(process)
        }

        // act & assert
        val process = Process(randomUUID(), MEDIUM)
        fifoTaskManager.add(process)
    }


    @Test
    internal fun `should kill all processes`() {
        // arrange
        fifoTaskManager.add(Process(randomUUID(), MEDIUM))

        // act
        fifoTaskManager.killAll()

        // assert
        fifoTaskManager.list()
    }
}