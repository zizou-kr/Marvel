package com.zizou.marvel.presentation

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showMessage(message: String) {
    Toast.makeText(requireActivity().applicationContext, message, Toast.LENGTH_SHORT).show()
}