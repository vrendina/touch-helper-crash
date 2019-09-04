ItemTouchHelper Crash
========================

The Android `ItemTouchHelper` class can crash when overriding `clearView()` in the `ItemTouchHelper.Callback` because
the restore animations are not cancelled when detaching the the touch helper from the RecyclerView. When the restore animation
completes, it attempts to invoke `clearView()` on the callback and calls the method with a null RecyclerView. The RecyclerView parameter is annotated
as `@NonNull` and Kotlin expects a non-nullable value.

*ItemTouchHelper.java:2037*
```
public void clearView(@NonNull RecyclerView recyclerView, @NonNull ViewHolder viewHolder)
```

Stack Trace
-----------

```
    java.lang.IllegalArgumentException: Parameter specified as non-null is null: method kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull, parameter recyclerView
        at com.victorrendina.touchhelpercrash.SampleItemTouchHelperCallback.clearView(Unknown Source:2)
        at androidx.recyclerview.widget.ItemTouchHelper$3.onAnimationEnd(ItemTouchHelper.java:644)
        at android.animation.Animator$AnimatorListener.onAnimationEnd(Animator.java:554)
        ...
```

Steps to Reproduce
------------------

The crash will only happen if the `ItemTouchHelper.Callback` overrides `clearView` in a Kotlin file and the touch helper is removed
from the RecyclerView while the item is animating.

- Run the sample application
- Swipe an item and release it so the restore animation is running
- Press the `Detach Touch Helper` button before the animation completes
- The application will crash when the animation completes

This issue was discovered because with new gesture based navigation on Android Q is it easy to start swiping a list item
while attempting to swipe to go back. Our application detaches the touch helper from the RecyclerView whenever a fragment's
view is destroyed.

Proposed Resolution
--------------------

In the `destroyCallbacks()` method `ItemTouchHelper.java:493` the recovery animation should be cancelled. 