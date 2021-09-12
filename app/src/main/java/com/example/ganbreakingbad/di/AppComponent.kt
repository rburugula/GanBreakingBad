package com.example.ganbreakingbad.di

import com.example.ganbreakingbad.CharactersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface AppComponent {
    fun inject(charactersFragment: CharactersFragment)
}