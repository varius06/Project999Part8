package com.example.course999part8.core

interface Module<T : Representative<*>> {
    fun representative(): T
}