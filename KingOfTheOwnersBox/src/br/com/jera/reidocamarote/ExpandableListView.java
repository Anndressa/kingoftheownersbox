package br.com.jera.reidocamarote;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ExpandableListView extends ListView {

	public ExpandableListView(Context context) {
		super(context);
		if (Build.VERSION.SDK_INT >= 9)
			this.setOverScrollMode(View.OVER_SCROLL_NEVER);
	}

	public ExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ExpandableListView(Context context, AttributeSet attrs,
			int defaultStyle) {
		super(context, attrs, defaultStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);

		ViewGroup.LayoutParams params = getLayoutParams();
		params.height = getMeasuredHeight();

	}
}
