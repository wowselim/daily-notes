package co.selim

import co.selim.ext.dateString
import co.selim.ext.standardWorkingDirectory
import picocli.CommandLine
import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.asSequence

@CommandLine.Command(name = "archive")
class ArchiveCommand(
    private val workingDirectory: Path,
    private val fileNameProvider: () -> String
) : Runnable {
  constructor() : this(standardWorkingDirectory, ::dateString)

  override fun run() {
    val archiveDirectory = workingDirectory.resolve(fileNameProvider())
    if (Files.notExists(archiveDirectory)) {
      Files.createDirectory(archiveDirectory)
    }

    Files.list(workingDirectory)
        .asSequence()
        .filter { Files.isRegularFile(it) }
        .filter { it.fileName.toString().endsWith(".daily") }
        .forEach { file -> Files.move(file, archiveDirectory.resolve(file.fileName)) }
  }
}