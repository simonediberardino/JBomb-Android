package com.diberardino.jbomb.network.serializing

import com.google.common.base.Splitter
import com.diberardino.jbomb.network.models.HttpMessage

class HttpParserSerializer private constructor() {
    fun parse(httpData: String): Map<String, String> {
        var httpData_: String = httpData

        if (httpData.startsWith("{"))
            httpData_ = httpData.drop(1)

        if (httpData.endsWith("}"))
            httpData_ = httpData_.dropLast(1)

        return Splitter.on(",")
                .withKeyValueSeparator("=")
                .split(httpData_)
    }

    fun serialize(httpMessage: HttpMessage, private: Boolean, actorId: Long): String {
        val map = httpMessage.serialize()
        map["private"] = private.toString()
        map["actorId"] = actorId.toString()
        return map.toString()
    }

    companion object {
        val instance: HttpParserSerializer by lazy { HttpParserSerializer() }
    }
}