package com.example.naversearchcompose.common.base

interface Mapper<LEFT, RIGHT> {
    fun mapToRight(from: LEFT): RIGHT
}