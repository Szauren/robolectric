package org.robolectric.shadows;

import android.os.IBinder;
import android.os.Looper;
import android.os.ResultReceiver;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.view.IInputMethodManager;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.HiddenApi;
import org.robolectric.internal.Shadow;

import java.lang.reflect.InvocationTargetException;

/**
 * Shadow for {@link android.view.inputmethod.InputMethodManager}.
 */
@Implements(value = InputMethodManager.class)
public class ShadowInputMethodManager {

  private boolean softInputVisible;

  @HiddenApi @Implementation
  static public InputMethodManager peekInstance() {
//    try {
//      return InputMethodManager.class
//          .getConstructor(IInputMethodManager.class, Looper.class)
//          .newInstance(null, Looper.getMainLooper());
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }

    return Shadow.newInstanceOf(InputMethodManager.class);
  }

  @Implementation
  public boolean showSoftInput(View view, int flags) {
    return showSoftInput(view, flags, null);
  }

  @Implementation
  public boolean showSoftInput(View view, int flags, ResultReceiver resultReceiver) {
    softInputVisible = true;
    return true;
  }

  @Implementation
  public boolean hideSoftInputFromWindow(IBinder windowToken, int flags) {
    return hideSoftInputFromWindow(windowToken, flags, null);
  }

  @Implementation
  public boolean hideSoftInputFromWindow(IBinder windowToken, int flags,
                       ResultReceiver resultReceiver) {
    softInputVisible = false;
    return true;
  }

  @Implementation
  public void toggleSoftInput(int showFlags, int hideFlags) {
    softInputVisible = !softInputVisible;
  }

  public boolean isSoftInputVisible() {
    return softInputVisible;
  }
}
