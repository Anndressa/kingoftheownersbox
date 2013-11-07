package br.com.jera.reidocamarote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {
	CommandmentsAdapter commandmentsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		commandmentsAdapter = new CommandmentsAdapter();
		((ListView) findViewById(R.id.commandments_list))
				.setAdapter(commandmentsAdapter);

	}

	public void share(View view) {
		Intent intent = new Intent(this, CameraActivity.class);
		intent.putExtra(PictureActivity.COMMANDMENTS_KEY, commandmentsAdapter.commandmentsCheckedStatus);
		startActivity(intent);
	}

}
