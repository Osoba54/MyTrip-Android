package com.example.mytrip.repositories

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import javax.inject.Inject

class TripRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : TripRepository {

    override suspend fun importTripFromZip(zipUri: Uri): Boolean = withContext(Dispatchers.IO) {
        try {
            val targetDir = context.filesDir

            val inputStream = context.contentResolver.openInputStream(zipUri)
                ?: run {
                    return@withContext false
                }


            inputStream.use {
                ZipInputStream(it).use { zipInputStream ->
                    var entry: ZipEntry?
                    while (zipInputStream.nextEntry.also { entry = it } != null) {

                        val currentEntry = entry!!
                        val entryName = currentEntry.name ?: continue
                        if (currentEntry.isDirectory) continue

                        val targetFile = File(targetDir, entryName)
                        targetFile.parentFile?.mkdirs()

                        FileOutputStream(targetFile).use { outputStream ->
                            zipInputStream.copyTo(outputStream)
                        }
                        zipInputStream.closeEntry()
                    }
                }
            }
            return@withContext true

        } catch (e: Exception) {
            return@withContext false
        }
    }
}