package io.holixon.axon.eclipsestore.root

import org.apache.commons.io.FilenameUtils
import org.eclipse.store.integrations.spring.boot.types.configuration.EclipseStoreProperties
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream


object FileSystemHelper {

  /**
   *
   */
  fun isStorageInitialized(eclipseStoreProperties: EclipseStoreProperties): Boolean {
    return Files.exists(Path.of(eclipseStoreProperties.storageDirectory))
      && Files.exists(Path.of(eclipseStoreProperties.storageDirectory + "/" + eclipseStoreProperties.typeDictionaryFileName))
  }

  fun backupExists(eclipseStoreProperties: EclipseStoreProperties): Boolean {
    return Files.exists(Path.of(eclipseStoreProperties.backupDirectory))
  }

  /**
   * Inspired by from https://gist.github.com/mike10004/d15478e3a14f0f31c276ff2d13e1d9da
   */
  class DeepZippingFileVisitor(
    private val enclosure: Path,
    private val zipOutputStream: ZipOutputStream
  ) : FileVisitor<Path> {

    @Throws(IOException::class)
    override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
      if (!dir.toFile().absoluteFile.equals(enclosure.toFile().absoluteFile)) {
        val relativeDirName = normalize(dir) + "/"
        zipOutputStream.putNextEntry(ZipEntry(relativeDirName))
        zipOutputStream.closeEntry()
      }
      return FileVisitResult.CONTINUE
    }

    @Throws(IOException::class)
    override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
      val fileEntryName = normalize(file)
      val entry = ZipEntry(fileEntryName)
      zipOutputStream.putNextEntry(entry)
      val bytes = Files.readAllBytes(file)
      zipOutputStream.write(bytes, 0, bytes.size)
      zipOutputStream.closeEntry()
      return FileVisitResult.CONTINUE
    }

    private fun normalize(path: Path): String {
      val relativeDir = enclosure.relativize(path)
      return FilenameUtils.normalizeNoEndSeparator(relativeDir.toString(), true)
    }

    @Throws(IOException::class)
    override fun visitFileFailed(file: Path, exc: IOException): FileVisitResult {
      throw exc
    }

    @Throws(IOException::class)
    override fun postVisitDirectory(dir: Path, exc: IOException?): FileVisitResult {
      if (exc != null) {
        throw exc
      }
      return FileVisitResult.CONTINUE
    }
  }

  class ShallowZippingDirectoryVisitor(
    private val enclosure: Path,
    private val zipOutputStream: ZipOutputStream
  ) : SimpleFileVisitor<Path>() {
    override fun visitFile(file: Path, attributes: BasicFileAttributes): FileVisitResult {
      val targetFile = enclosure.relativize(file)
      zipOutputStream.putNextEntry(ZipEntry(targetFile.toString()))
      val bytes = Files.readAllBytes(file)
      zipOutputStream.write(bytes, 0, bytes.size)
      zipOutputStream.closeEntry()
      return FileVisitResult.CONTINUE
    }
  }

  @Throws(IOException::class)
  fun createBackupSnapshot(eclipseStoreProperties: EclipseStoreProperties): File {
    return compress(Path.of(eclipseStoreProperties.backupDirectory), eclipseStoreProperties.backupDirectory + ".zip")
  }

  @Throws(IOException::class)
  fun compress(enclosure: Path, target: String): File {
    ZipOutputStream(FileOutputStream(target)).use { zipOutputStream ->
      Files.walkFileTree(enclosure, DeepZippingFileVisitor(enclosure, zipOutputStream))
    }
    return File(target)
  }

  @Throws(IOException::class)
  fun compressWithoutSubDirs(enclosure: Path, target: String): File {
    ZipOutputStream(FileOutputStream(target)).use { zipOutputStream ->
      Files.walkFileTree(enclosure, ShallowZippingDirectoryVisitor(enclosure, zipOutputStream))
    }
    return File(target)
  }


  @Throws(IOException::class)
  fun uncompress(backupZipBytes: ByteArray, targetDir: String) {
    val destinationDir = File(targetDir)
    val buffer = ByteArray(1024)
    val zis = ZipInputStream(ByteArrayInputStream(backupZipBytes))
    var zipEntry = zis.nextEntry
    while (zipEntry != null) {
      val newFile: File = newFile(destinationDir, zipEntry)
      if (zipEntry.isDirectory) {
        if (!newFile.isDirectory && !newFile.mkdirs()) {
          throw IOException("Failed to create directory $newFile")
        }
      } else {
        // fix for Windows-created archives
        val parent = newFile.parentFile
        if (!parent.isDirectory && !parent.mkdirs()) {
          throw IOException("Failed to create directory $parent")
        }
        // write file content
        val fos = FileOutputStream(newFile)
        var len: Int
        while ((zis.read(buffer).also { len = it }) > 0) {
          fos.write(buffer, 0, len)
        }
        fos.close()
      }
      zipEntry = zis.nextEntry
    }
    zis.closeEntry();
    zis.close();
  }
}

@Throws(IOException::class)
private fun newFile(destinationDir: File, zipEntry: ZipEntry): File {
  val destFile = File(destinationDir, zipEntry.name)
  val destDirPath = destinationDir.canonicalPath
  val destFilePath = destFile.canonicalPath
  if (!destFilePath.startsWith(destDirPath + File.separator)) {
    throw IOException("Entry is outside of the target dir: " + zipEntry.name)
  }
  return destFile
}
