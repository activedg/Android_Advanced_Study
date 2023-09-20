package com.example.naversearchcompose.common.base

interface BiMapper<LEFT, RIGHT> {
    fun mapToRight(from: LEFT): RIGHT
    fun mapToLeft(from: RIGHT): LEFT
}