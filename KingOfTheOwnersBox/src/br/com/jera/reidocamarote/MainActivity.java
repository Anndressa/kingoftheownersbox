package br.com.jera.reidocamarote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ScrollView;

import com.crashlytics.android.Crashlytics;
import com.google.analytics.tracking.android.EasyTracker;

public class MainActivity extends Activity implements OnItemClickListener {
	CommandmentsAdapter commandmentsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Crashlytics.start(this);
		setContentView(R.layout.activity_main);
		commandmentsAdapter = new CommandmentsAdapter();
		ListView listView = (ListView) findViewById(R.id.commandments_list);
		listView.setAdapter(commandmentsAdapter);
		listView.setOnItemClickListener(this);

		((ScrollView) findViewById(R.id.scrollView)).smoothScrollTo(0, 0);

		Utils.applyTypeface(getWindow().getDecorView());
	}

	public void share(View view) {
		Intent intent = new Intent(this, CameraActivity.class);
		intent.putExtra(PictureActivity.COMMANDMENTS_KEY,
				commandmentsAdapter.commandmentsCheckedStatus);
		startActivity(intent);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long id) {
		CheckBox checkBox = (CheckBox) view
				.findViewById(R.id.commandment_check);
		checkBox.setChecked(!checkBox.isChecked());
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
