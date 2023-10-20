package com.example.balticbytebuccaneers.util

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainScope

fun ConstrainScope.linkToParentVertical(margin: Dp = 0.dp) {
    linkToParentVertical(margin, margin)
}

fun ConstrainScope.linkToParentVertical(marginTop: Dp = 0.dp, marginBottom: Dp = 0.dp) {
    top.linkTo(parent.top, marginTop)
    bottom.linkTo(parent.bottom, marginBottom)
}

fun ConstrainScope.linkToParentHorizontal(margin: Dp = 0.dp) {
    linkToParentHorizontal(margin, margin)
}

fun ConstrainScope.linkToParentHorizontal(marginTop: Dp = 0.dp, marginBottom: Dp = 0.dp) {
    start.linkTo(parent.start, marginTop)
    end.linkTo(parent.end, marginBottom)
}

fun ConstrainScope.linkToParentTop(margin: Dp = 0.dp) {
    top.linkTo(parent.top, margin)
}

fun ConstrainScope.linkToParentBottom(margin: Dp = 0.dp) {
    bottom.linkTo(parent.bottom, margin)
}

fun ConstrainScope.linkToParentStart(margin: Dp = 0.dp) {
    start.linkTo(parent.start, margin)
}

fun ConstrainScope.linkToParentEnd(margin: Dp = 0.dp) {
    end.linkTo(parent.end, margin)
}