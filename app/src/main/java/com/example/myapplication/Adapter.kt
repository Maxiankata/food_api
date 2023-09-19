package com.example.myapplication

interface Adapter<T, K> {
    fun adapt(t:T): K?
}