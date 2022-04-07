# MasterDetail

The app is a simple master-detail app that fetches most popular articles from <a href = "https://developer.nytimes.com"> developer.nytimes.com</a>

It follows MVVM architecture, hilt for dependency injection, uses paging library to display the articles and navigation components for navigating between destinations.

## Building the App

First, clone the repo:

`git clone git@github.com:DikenMaharjan/masterDetail.git`

### Android Studio (Recommended)

* Open Android Studio and select `File->Open...` or from the Android Launcher select `Import project` and navigate to the root directory of your project.
* Select the directory and select the file `build.gradle` in the cloned repo.
* Click 'OK' to open the project in Android Studio.
* A Gradle sync should start, but you can force a sync and build the 'app' module as needed.

### Gradle (command line)

* Build the APK: `./gradlew build`

## Running the Sample App

Connect an Android device to your development machine.

### Android Studio

* Select `Run -> Run 'app'` from the menu bar
* Select the device you wish to run the app on and click 'OK'

### Gradle

* Install the debug APK on your device `./gradlew installDebug`

## Generate Code Coverage

### Android Studio

* Right click on the test -> java package
* select `Run Tests in Java with Coverage`


