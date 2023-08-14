package com.example.cleanmvvm.presentation

interface MainEvent

class SaveEvent(val text: String) : MainEvent

class LoadEvent : MainEvent