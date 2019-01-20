package com.skynet.mumgo.ui.views;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by Huy on 6/9/2017.
 */

public class FontCache {
    private static HashMap<String, Typeface> fontcache = new HashMap<>();

    public static Typeface getTypeface(Context context, String fontName) {
        Typeface typeface = fontcache.get(fontName);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            fontcache.put(fontName, typeface);
        }
        return typeface;
    }
}
