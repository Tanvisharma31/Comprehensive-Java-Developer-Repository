# Android Activity Lifecycle

An Activity is a single, focused thing that the user can do. Almost all activities interact with the user, so the Activity class takes care of creating a window for you.

## Lifecycle Callbacks

1.  **onCreate()**: Called when the activity is first created. Setup UI (setContentView), init variables.
2.  **onStart()**: Called when the activity is becoming visible to the user.
3.  **onResume()**: Called when the activity will start interacting with the user.
4.  **onPause()**: Called when the system is about to start resuming a previous activity. Commit unsaved changes, stop animations.
5.  **onStop()**: Called when the activity is no longer visible.
6.  **onDestroy()**: The final call before the activity is destroyed.

## Diagram
`Activity Launched` -> `onCreate` -> `onStart` -> `onResume` -> **Running** -> `onPause` -> `onStop` -> `onDestroy` -> `Activity Shutdown`
