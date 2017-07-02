package com.lzjian.androidutils.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * @Description: SharedPreference链式结构
 */
public class SharedPrefsChain {

    public static EditorChain editor(Context context) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        return new EditorChain(editor);
    }

    public static EditorChain editor(Context context, String fileName) {
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        return new EditorChain(editor);
    }

    public static SharedPreferences viewer(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferences viewer(Context context, String fileName) {
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public static class EditorChain {
        final SharedPreferences.Editor editor;

        EditorChain(SharedPreferences.Editor editor) {
            this.editor = editor;
        }

        public EditorChain put(String key, String value) {
            this.editor.putString(key, value);
            return this;
        }

        public EditorChain put(String key, boolean value) {
            this.editor.putBoolean(key, value);
            return this;
        }

        public EditorChain put(String key, float value) {
            this.editor.putFloat(key, value);
            return this;
        }

        public EditorChain put(String key, int value) {
            this.editor.putInt(key, value);
            return this;
        }

        public EditorChain put(String key, long value) {
            this.editor.putLong(key, value);
            return this;
        }

        public EditorChain put(String key, Set<String> values) {
            this.editor.putStringSet(key, values);
            return this;
        }

        public EditorChain remove(String key) {
            this.editor.remove(key);
            return this;
        }

        public EditorChain clear() {
            this.editor.clear();
            return this;
        }

        public void apply() {
            this.editor.apply();
        }

        public boolean commit() {
            return this.editor.commit();
        }
    }
}
