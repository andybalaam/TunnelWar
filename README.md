# TunnelWar

A retro up-to-8-player sideways-scrolling one-button-each Android action shooty
game.

## Development

Development should work nicely with Android Studio (log an issue if not).

If you prefer the command line, install the Android SDK v25+,
make sure adb is in your path, and connect your device or start an emulator,
and use the following commands.

### Build & run on your device

```bash
./gradlew installDebug
adb shell am start -n "net.artificialworlds.tunnelwar/net.artificialworlds.tunnelwar.TitleActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
```

### Run the unit tests

```bash
./gradlew test
```

### Run the system tests (on a device)

```bash
./gradlew connectedAndroidTest
```
