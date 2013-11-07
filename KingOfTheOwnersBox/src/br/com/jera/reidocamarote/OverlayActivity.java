package br.com.jera.reidocamarote;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

public class OverlayActivity extends Activity {
	public static final String COMMANDMENTS_KEY = "commandments";

	public void loadOverlayData() {
		boolean[] commandmentsCheckedStatus = (boolean[]) getIntent()
				.getSerializableExtra(COMMANDMENTS_KEY);
		for (int i = 0; i < commandmentsCheckedStatus.length; i++) {
			int id = getResources().getIdentifier("commandment_" + i, "id",
					getPackageName());
			TextView textView = (TextView) findViewById(id);
			textView.setVisibility(commandmentsCheckedStatus[i] ? View.VISIBLE
					: View.GONE);
			textView.setText(Commandments.values()[i].name);
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this); // Add this method.
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this); // Add this method.
	}
}
