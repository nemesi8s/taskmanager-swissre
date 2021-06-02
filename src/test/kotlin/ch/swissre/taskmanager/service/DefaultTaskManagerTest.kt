package ch.swissre.taskmanager.service

import ch.swissre.taskmanager.entity.Process
import ch.swissre.taskmanager.entity.Process.Priority.MEDIUM
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class DefaultTaskManagerTest {

    private lateinit var defaultTaskManager: DefaultTaskManager

    @BeforeEach
    internal fun setUp() {
        defaultTaskManager = DefaultTaskManager()
    }

    @Test
    internal fun `should add new process`() {
        // arrange
        val process = Process(randomUUID(), MEDIUM)

        // act
        defaultTaskManager.add(process)

        // assert
        defaultTaskManager.list()
    }

    @Test
    internal fun `should throw exception when tm is full`() {

        // arrange
        repeat(TaskManager.CAPACITY) {
            val process = Process(randomUUID(), MEDIUM)
            defaultTaskManager.add(process)
        }

        // act & assert
        assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy {
            val process = Process(randomUUID(), MEDIUM)
            defaultTaskManager.add(process)
        }
    }

    @Test
    internal fun `should kill all processes`() {
        // arrange
        defaultTaskManager.add(Process(randomUUID(), MEDIUM))

        // act
        defaultTaskManager.killAll()

        // assert
        defaultTaskManager.list()
    }
}