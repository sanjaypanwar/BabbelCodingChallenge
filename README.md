# CodingChallenge
Challenge Description:
A small language game. The player will see a word in language ONE on the screen. While
this word is displayed, a word in language TWO will fall down on the screen. The player will have to choose if
the falling word is the correct translation or a wrong translation. The player needs to answer, before the word
reaches the bottom of the screen.

# Architecture:
## MVVM


![MVVM](https://user-images.githubusercontent.com/10473282/145411515-527646c2-ea86-4ea9-8347-43c30d913a26.png)

# Libraries added
## KOIN
> Used for Dependency Injection

## Coroutines
> For acync calls

## DataBinding
>To ease the management of UI xml views in activity code

## Parcelize
>  To provide easy implementation of Parcelable in data classes.

## Android Navigation Component
> To manage fragments transactions , navigation and data passing 


# Screens Created:
- **Entry Screen** : To give details about the game and entry point for the game
- **Game Screen** : Main screen for game , where user interacts to play the word game
- **Final Screen** : To give breif about the score after game finished.

# Other class added:
- **WordRescueModule** class to provide dependencies for repository and localdataprovider objects
- **CheckoutVIewModelModule** to provide the viewmodel dependencies.
- **CustomApplication** : Application class to initialize and start KOIN di.

