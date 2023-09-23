package com.example.myapplication.adapters

interface Adapter<T, K> {
    fun adapt(t: T): K?
}