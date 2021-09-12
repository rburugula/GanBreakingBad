package com.example.ganbreakingbad.di

import com.example.ganbreakingbad.CharactersFragment
import dagger.Component

@Component(modules = [RetroModule::class])
interface AppComponent {
    fun inject(charactersFragment: CharactersFragment)
}