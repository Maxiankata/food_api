package com.example.myapplication.adapters

import com.example.myapplication.data.Food

interface Adapter<T, K> {
    fun adapt(t: T): K?
}