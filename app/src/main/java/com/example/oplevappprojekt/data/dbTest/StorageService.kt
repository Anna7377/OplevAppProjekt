package com.example.oplevappprojekt.data.dbTest

interface StorageService {
    fun addListener(
        userId: String,
        onDocumentEvent: (Boolean, Task) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun removeListener()
    fun getTask(taskId: String, onError: (Throwable) -> Unit, onSuccess: (Task) -> Unit)
    fun saveTask(task: Task, onResult: (Throwable?) -> Unit)
    fun updateTask(task: Task, onResult: (Throwable?) -> Unit)
    fun deleteTask(taskId: String, onResult: (Throwable?) -> Unit)
}