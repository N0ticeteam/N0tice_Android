package com.example.n0tice.core.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.n0tice.core.ui.myiconpack.Risk
import com.example.n0tice.core.ui.myiconpack.Log
import com.example.n0tice.core.ui.myiconpack.Predict
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons = listOf(Risk, Log, Predict)
    return __AllIcons!!
  }
