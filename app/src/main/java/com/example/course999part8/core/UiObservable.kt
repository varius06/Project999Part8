package com.example.course999part8.core

interface UiObservable<T : Any> : UiUpdate<T>, UpdateObserver<T> {

    fun clear()

    abstract class Base<T : Any> : UiObservable<T> {
        private var cache: T? = null
        private var observer: UiObserver<T> = UiObserver.Empty()
        override fun updateObserver(uiObserver: UiObserver<T>) {
            observer = uiObserver

            if (!observer.isEmpty()) {
                cache?.let {
                    observer.update(it)
                }
            }
        }

        override fun update(data: T) {
            if (observer.isEmpty()) {
                cache = data
            } else {
                cache = data
                observer.update(data)
            }
        }

    }

    abstract class Single<T : Any>(
        private val empty: T
    ) : UiObservable<T> {

        protected var cache: T = empty

        private var observer: UiObserver<T> = UiObserver.Empty()

        override fun clear() {
            cache = empty
        }
        override fun updateObserver(uiObserver: UiObserver<T>) {
            observer = uiObserver
            if (!observer.isEmpty()) {
                observer.update(cache)
            }
        }

        override fun update(data: T) {
            cache = data
            if (!observer.isEmpty())
                observer.update(cache)
        }

    }
}

interface UiUpdate<T : Any> {
    fun update(data: T)
}

interface UpdateObserver<T : Any> {
    fun updateObserver(uiObserver: UiObserver<T> = UiObserver.Empty())
}

interface UiObserver<T : Any> : UiUpdate<T>, IsEmpty {
    class Empty<T : Any> : UiObserver<T> {
        override fun isEmpty() = true
        override fun update(data: T) = Unit
    }
}