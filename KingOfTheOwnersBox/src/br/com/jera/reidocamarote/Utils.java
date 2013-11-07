package br.com.jera.reidocamarote;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Utils {
	public static final String FONT = "Fonts/Roboto-Light.ttf";
	public static final String FONT_BOLD = "Fonts/Roboto-Regular.ttf";
	public static final String FONT_ITALIC = "Fonts/Roboto-LightItalic.ttf";
	public static final String FONT_BOLD_ITALIC = "Fonts/Roboto-BoldItalic.ttf";

	public static void setFont(android.widget.TextView txt) {
		Typeface tf = txt.getTypeface();
		String font = FONT;
		if (tf != null)
			switch (tf.getStyle()) {
			case Typeface.BOLD:
				font = FONT_BOLD;
				break;
			case Typeface.ITALIC:
				font = FONT_ITALIC;
				break;
			case Typeface.BOLD_ITALIC:
				font = FONT_BOLD_ITALIC;
				break;
			default:
				font = FONT;
				break;
			}
		txt.setTypeface(Typeface.createFromAsset(txt.getContext().getAssets(),
				font));
	}

	public static <T extends View> T applyTypeface(T view) {
		if (view instanceof TextView) {
			setFont((TextView) view);
		} else if (view instanceof ViewGroup) {
			ViewGroup group = (ViewGroup) view;
			final int childCount = group.getChildCount();
			for (int i = 0; i < childCount; i++) {
				applyTypeface(group.getChildAt(i));
			}
		}
		return view;
	}
}
