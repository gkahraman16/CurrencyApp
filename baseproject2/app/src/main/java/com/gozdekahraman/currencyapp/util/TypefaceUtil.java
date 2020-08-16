package com.gozdekahraman.currencyapp.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.gozdekahraman.currencyapp.App;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public final class TypefaceUtil {

    private static final HashMap<IsbasiTypeface, Typeface> TYPEFACE_MAP = new LinkedHashMap<>();

    public enum IsbasiTypeface {
        AVENIR("Avenir.ttf"),
        AVENIR_BOLD("AvenirBold.ttf");

        private String fileName;

        IsbasiTypeface(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return this.fileName;
        }
    }

    private TypefaceUtil() {
    }

    private static Typeface getTypeface(Context context, IsbasiTypeface typeface) {
        if (TYPEFACE_MAP.get(typeface) == null) {
            TYPEFACE_MAP.put(typeface, Typeface.createFromAsset(context.getAssets(), "fonts/" + typeface.getFileName()));
        }
        return TYPEFACE_MAP.get(typeface);
    }

    public static void setTypeface(Context context, IsbasiTypeface IsbasiTypeface, TextView... textViews) {
        if (context == null) {
            context = App.getInstance().getBaseContext();
        }
        Typeface typeface = getTypeface(context, IsbasiTypeface);
        for (TextView textView : textViews) {
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    public static void setTypeface(Context context, IsbasiTypeface IsbasiTypeface, List<TextView> textViews) {
        Typeface typeface = getTypeface(context, IsbasiTypeface);
        for (TextView textView : textViews) {
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }
}
