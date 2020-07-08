package com.manappuram.ogl.util;

import java.util.ArrayList;
import java.util.List;

public class KeyboardVisibilityListenerHandler {

    private static final KeyboardVisibilityListenerHandler handler = new KeyboardVisibilityListenerHandler();
    private List<KeyboardVisibilityListener> list = new ArrayList<>();

    public static KeyboardVisibilityListenerHandler getHandler() {
        return handler;
    }

    public void registerListener(KeyboardVisibilityListener listener) {
        list.add(listener);
    }

    public void unregisterListener(KeyboardVisibilityListener listener) {
        list.remove(listener);
    }

    public void onKeyboardVisible() {
        for (int i = 0; i < list.size(); i++)
            list.get(i).onKeyboardVisible();
    }

    public void onKeyboardHidden() {
        for (int i = 0; i < list.size(); i++)
            list.get(i).onKeyboardHide();
    }
}
