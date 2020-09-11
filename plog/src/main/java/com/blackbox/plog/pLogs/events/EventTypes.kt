package com.blackbox.plog.pLogs.events

import androidx.annotation.Keep

@Keep
enum class EventTypes(var data: String) {

    PLOGS_EXPORTED("1"),
    DATA_LOGS_EXPORTED("2"),
    NEW_ERROR_REPORTED("4"),
    NEW_EVENT_DIRECTORY_CREATED("5"),
    DELETE_LOGS("7"),
    DELETE_EXPORTED_FILES("8"),
    AUTO_EXPORT_PERIOD_COMPLETED("9"),
    LOG_TYPE_EXPORTED("10"),
    SEVERE_ERROR_REPORTED("11"),
    NEW_ERROR_REPORTED_FORMATTED("12"),
    SEVERE_ERROR_REPORTED_FORMATTED("13"),
    NEW_EVENT_LOG_FILE_CREATED("14")

}

