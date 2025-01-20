package com.elclcd.device.calendar.app.common.utils

import android.nfc.Tag
import android.util.Log
import com.elvishew.xlog.XLog

private const val TAG = "MOVIE_INFO"
private var isDebug = true

fun String.logd(tag: String = TAG){
    if (isDebug) XLog.enableBorder().tag(tag).d(this)
//    if (isDebug) Log.d(tag, this)
}

fun String.loge(tag: String = TAG){
    if (isDebug) XLog.enableBorder().tag(tag).e(this)
//    if (isDebug) Log.e(tag, this)
}