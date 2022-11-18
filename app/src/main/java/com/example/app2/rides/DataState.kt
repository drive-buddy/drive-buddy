package com.example.app2.rides

import com.example.app2.helperfiles.Infos

sealed class DataState {
    class Success(val data: MutableList<Infos>): DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}