package com.blackbox.plog.pLogs.filter

import android.util.Log
import com.blackbox.plog.pLogs.PLog
import com.blackbox.plog.pLogs.exporter.ExportType
import com.blackbox.plog.pLogs.models.LogLevel
import com.blackbox.plog.utils.DateControl
import com.blackbox.plog.utils.Utils
import java.io.File
import java.util.zip.ZipFile

object FilterUtils {

    internal fun extractDay(name: String): Int {
        return Integer.parseInt(name.substring(0, 2))
    }

    internal fun extractHour(name: String): Int {
        return Integer.parseInt(name.substring(8, 10))
    }

    fun prepareOutputFile(outputPath: String) {

        if (PLog.getPLogger()?.autoClearExports!!)
            File(outputPath).deleteRecursively() //Delete all previous Exports

        //Create export directory if it doesn't already exists
        Utils.instance.createDirIfNotExists(outputPath)
    }

    internal fun filterFile(folderPath: String, files: Array<File>, lastHour: Int): Boolean {
        var found = false

        for (i in files.indices) {
            val fileHour = extractHour(files[i].name)

            if (PLog.getPLogger()?.isDebuggable!!)
                Log.i(FileFilter.TAG, "Last Hour: " + lastHour + " Check File Hour: " + fileHour + " " + files[i].name)

            if (fileHour == lastHour) {
                found = true

            }
        }

        return found
    }

    /*
     * Returns path of Logs based on selected Directory Structure.
     */
    internal fun getPathForType(requestType: ExportType): String {

        //Create Root folder
        val rootFolderName = "Logs"
        val rootFolderPath = PLog.logPath + rootFolderName + File.separator

        when (requestType) {

            ExportType.TODAY -> {
                return rootFolderPath + DateControl.instance.today
            }

            ExportType.LAST_24_HOURS -> {
                return rootFolderPath
            }

            ExportType.LAST_HOUR -> {
                return rootFolderPath + DateControl.instance.today
            }

            ExportType.WEEKS -> {
                return rootFolderPath
            }

            ExportType.ALL -> {
                return rootFolderPath
            }
        }
    }

    /*
     * Print all entries in zip file created.
     */
    internal fun readZipEntries(path: String) {
        val zipFile = ZipFile(path)

        //List all entries in a Zip file
        val entries = zipFile.entries()

        while (entries.hasMoreElements()) {
            val entry = entries.nextElement()

            if (PLog.getPLogger()?.isDebuggable!!)
                PLog.logThis(FileFilter.TAG, "readZip", entry.name, LogLevel.INFO)
        }
    }

    /*
     * After creating zip, delete all temp files copied.
     */
    internal fun deleteFilesExceptZip() {
        val path = File(PLog.exportTempPath)
        path.deleteRecursively()
    }


    /*
     * Will return list of all files with a directory & it's sub-directory.
     */
    fun listFiles(directoryName: String, files: ArrayList<File>): List<File> {

        val directory = File(directoryName)

        val fList = directory.listFiles()

        if (fList != null) {
            for (file in fList) {
                if (file.isFile) {
                    files.add(file)
                } else if (file.isDirectory) {
                    listFiles(file.absolutePath, files)
                }
            }
        }

        return files
    }
}

