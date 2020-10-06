package co.selim

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files


class StoreCommandTest : DailyTest() {
  @Test
  fun `storing a note creates the working directory`() = test { fs ->
    val workingDirectory = fs.getPath("daily")
    val storeCommand = StoreCommand(workingDirectory, fileNameProvider)
    storeCommand.message = ""

    storeCommand.run()

    assertTrue(Files.exists(workingDirectory))
    assertTrue(Files.isDirectory(workingDirectory))
  }

  @Test
  fun `storing a note stores its contents`() = test { fs ->
    val workingDirectory = fs.getPath("daily")
    val storeCommand = StoreCommand(workingDirectory, fileNameProvider)
    storeCommand.message = "hello 123"

    storeCommand.run()

    val noteFile = workingDirectory.resolve("$fileName.daily")
    assertTrue(Files.exists(noteFile))
    assertTrue(Files.isRegularFile(noteFile))
    assertEquals("hello 123", Files.readString(noteFile))
  }
}