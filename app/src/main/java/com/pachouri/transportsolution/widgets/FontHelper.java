package com.pachouri.transportsolution.widgets;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by ankit on 12/24/16.
 */
public class FontHelper {

    public static final int FONT_OPENSANS = 2;
    public static final int FONT_ROBOTO = 3;

    private static final int LIGHT = 0;
    private static final int REGULAR = 1;
    private static final int BOLD = 2;
    private static final int SEMI_BOLD = 3;

    private static final String OPENSANS_PATH = "fonts/OpenSans/";
    private static final String ROBOTO_PATH = "fonts/Roboto/";

    private static final String OPENSANS_BOLD = OPENSANS_PATH + "OpenSans-Bold.ttf";
    private static final String OPENSANS_LIGHT = OPENSANS_PATH + "OpenSans-Light.ttf";
    private static final String OPENSANS_REGULAR = OPENSANS_PATH + "OpenSans-Regular.ttf";
    private static final String OPENSANS_SEMIBOLD = OPENSANS_PATH + "OpenSans-Semibold.ttf";

    private static final String ROBOTO_BOLD = ROBOTO_PATH + "roboto-bold.ttf";
    private static final String ROBOTO_LIGHT = ROBOTO_PATH + "roboto-light.ttf";
    private static final String ROBOTO_REGULAR = ROBOTO_PATH + "roboto-regular.ttf";
    private static final String ROBOTO_SEMIBOLD = ROBOTO_PATH + "roboto-medium.ttf";

    private String currentFontBold, currentFontRegular, currentFontLight, currentFontSemiBold;

    public FontHelper(int currentFont) {
        switch (currentFont) {
            case FONT_OPENSANS:
                currentFontRegular = OPENSANS_REGULAR;
                currentFontBold = OPENSANS_BOLD;
                currentFontLight = OPENSANS_LIGHT;
                currentFontSemiBold = OPENSANS_SEMIBOLD;
                break;
            case FONT_ROBOTO:
                currentFontRegular = ROBOTO_REGULAR;
                currentFontBold = ROBOTO_BOLD;
                currentFontLight = ROBOTO_LIGHT;
                currentFontSemiBold = ROBOTO_SEMIBOLD;
                break;
        }
    }

    public Typeface getFont(Context context, int fontIndex) {
        Typeface typeface;
        switch (fontIndex) {
            case BOLD:
                typeface = FontCache.getFont(context, currentFontBold);
                break;
            case LIGHT:
                typeface = FontCache.getFont(context, currentFontLight);
                break;
            case REGULAR:
                typeface = FontCache.getFont(context, currentFontRegular);
                break;
            case SEMI_BOLD:
                typeface = FontCache.getFont(context, currentFontSemiBold);
                break;
            default:
                typeface = FontCache.getFont(context, currentFontRegular);
                break;
        }
        return typeface;
    }
}
