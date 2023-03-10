# MyLittleFoodQuiz

A tiny quiz about food (with JSON) **with dark and light theme**.

# Focus

I focused primarily on :

* The use of the latest tools and best practices from Google
* An architecture allowing an easy evolution of the software

# About

* Due to json file language, the app is in french only

# Tools

### Global Architecture

[Android app architecture](https://developer.android.com/topic/architecture)

### Language

[Kotlin](https://developer.android.com/kotlin)

### Data Stream

[Flow](https://developer.android.com/kotlin/flow)

### Dependency Injection

[Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

### UI

[Compose](https://developer.android.com/jetpack/compose)

### Tests

* Simulate device for local tests: [Robolectric](http://robolectric.org/)
* Assert tooling: [Google Truth](https://github.com/google/truth)

# About Tests
It's more a technical demonstration that can be greatly improved.
Better tests, especially UI ones, can be seen in my personal project:
https://github.com/BenayounP/AndroidMovieDataBase


# Possible improvements

* Do UI Tests
* finish tests on repository with "end to end" one that check if the json is well read and the list
  is well sorted
* Simulate error responses
* Simulate network response when posting
* Set to `internal` all classes where possible
