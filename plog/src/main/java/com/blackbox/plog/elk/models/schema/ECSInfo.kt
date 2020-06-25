package com.blackbox.plog.elk.models.schema

import com.blackbox.plog.elk.models.fields.Geo
import com.blackbox.plog.elk.models.fields.Host
import com.blackbox.plog.elk.models.fields.Organization
import com.blackbox.plog.elk.models.fields.User
import com.google.gson.annotations.SerializedName

data class ECSInfo(
        @SerializedName("labels") val labels: String,
        @SerializedName("log.level") val log_level: String,
        @SerializedName("message") val message: String,
        @SerializedName("service.name") val service_name: String,
        @SerializedName("process.thread.name") val process_thread_name: String,
        @SerializedName("log.logger") val log_logger: String,
        @SerializedName("geo") val geo: Geo,
        @SerializedName("host") val host: Host,
        @SerializedName("organization") val organization: Organization,
        @SerializedName("user") val user: User
)