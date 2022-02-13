package com.allana.suitapp_challengechapter5.utils

import android.app.Activity
import android.widget.Toast

fun Activity.toast(msg: String) {
    Toast.makeText(window.context, msg, Toast.LENGTH_SHORT).show()
}


