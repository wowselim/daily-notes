package co.selim.ext

import java.nio.file.Path
import java.nio.file.Paths

internal val standardWorkingDirectory: Path = Paths.get(
    System.getProperty("user.home"),
    ".daily"
)