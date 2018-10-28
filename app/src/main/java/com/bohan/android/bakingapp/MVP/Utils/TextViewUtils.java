package com.bohan.android.bakingapp.MVP.Utils;

/**
 * Created by Bo Han.
 */

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class TextViewUtils {

    public static void setTextWithSpan(TextView textView, String fullText, String styledText, StyleSpan style) {
        SpannableStringBuilder sb = new SpannableStringBuilder(fullText);
        int start = fullText.indexOf(styledText);
        int end = start + styledText.length();
        sb.setSpan(style, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //This is for make title text size large
        sb.setSpan(new RelativeSizeSpan(2.0f), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(sb);
    }
}
