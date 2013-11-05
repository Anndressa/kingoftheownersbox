package br.com.jera.reidocamarote;

import android.app.Activity;
import android.view.View;

public class OverlayActivity extends Activity {
	public static final String COMMANDMENTS_KEY = "commandments";

	public void loadOverlayData() {
		boolean[] commandmentsCheckedStatus = (boolean[]) getIntent()
				.getSerializableExtra(COMMANDMENTS_KEY);
		for (int i = 0; i < commandmentsCheckedStatus.length; i++) {
			int id = getResources().getIdentifier("commandment_" + i, "id",
					getPackageName());
			findViewById(id).setVisibility(
					commandmentsCheckedStatus[i] ? View.VISIBLE : View.GONE);
		}
	}
}
