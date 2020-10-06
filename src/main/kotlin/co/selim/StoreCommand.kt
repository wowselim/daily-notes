package co.selim

import co.selim.ext.standardWorkingDirectory
import co.selim.ext.timestampString
import picocli.CommandLine
import java.nio.file.Files
import java.nio.file.Path

@CommandLine.Command(name = "store")
class StoreCommand(
    private val workingDirectory: Path,
    private val fileNameProvider: () -> String
) : Runnable {
  @CommandLine.Option(
      names = ["-m", "--message"],
      description = ["store a message for the next daily"],
      required = true
  )
  lateinit var message: String

  constructor() : this(standardWorkingDirectory, ::timestampString)

  override fun run() {
    Files.createDirectories(workingDirectory)
    val fileName = "${fileNameProvider()}.daily"
    Files.writeString(workingDirectory.resolve(fileName), message)
  }
}