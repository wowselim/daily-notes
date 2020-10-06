package co.selim

import co.selim.ext.standardWorkingDirectory
import io.quarkus.picocli.runtime.annotations.TopCommand
import picocli.CommandLine
import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.asSequence

@TopCommand
@CommandLine.Command(
    name = "list",
    subcommands = [StoreCommand::class, ArchiveCommand::class]
)
class ListCommand(
    private val workingDirectory: Path
) : Runnable {
  @CommandLine.Option(
      names = ["-a", "--archive"],
      description = ["archive notes after listing them"],
      required = false
  )
  var archive: Boolean = false

  constructor() : this(standardWorkingDirectory)

  @ExperimentalStdlibApi
  override fun run() {
    Files.list(workingDirectory)
        .asSequence()
        .filter { Files.isRegularFile(it) }
        .filter { it.fileName.toString().endsWith(".daily") }
        .sorted()
        .forEach { file -> println(Files.readString(file)) }

    if (archive) {
      ArchiveCommand().run()
    }
  }
}