package co.selim

import com.github.marschall.memoryfilesystem.MemoryFileSystemBuilder
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.fail
import java.nio.file.FileSystem

abstract class DailyTest {
  private var inMemoryFileSystem: FileSystem? = null
  protected val fileName = "note"
  protected val fileNameProvider: () -> String = { fileName }

  @BeforeEach
  fun setup() {
    inMemoryFileSystem = MemoryFileSystemBuilder.newEmpty().build()
  }

  @AfterEach
  fun cleanup() {
    inMemoryFileSystem?.close()
  }

  protected fun test(block: (FileSystem) -> Unit) {
    val fs = inMemoryFileSystem
    if (fs != null) {
      block(fs)
    } else {
      fail("in memory fs not initialized")
    }
  }
}