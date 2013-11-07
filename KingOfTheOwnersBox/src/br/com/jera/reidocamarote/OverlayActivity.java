package br.com.jera.reidocamarote;

import java.io.ByteArrayInputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class OverlayActivity extends Activity {
	public static final String COMMANDMENTS_KEY = "commandments";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void loadOverlayData() {
		boolean[] commandmentsCheckedStatus = (boolean[]) getIntent().getSerializableExtra(COMMANDMENTS_KEY);
		for (int i = 0; i < commandmentsCheckedStatus.length; i++) {
			int id = getResources().getIdentifier("commandment_" + i, "id", getPackageName());
			TextView textView = (TextView) findViewById(id);
			textView.setVisibility(commandmentsCheckedStatus[i] ? View.VISIBLE : View.GONE);
			textView.setText(Commandments.values()[i].name);
		}
		// AdView adView = (AdView) this.findViewById(R.id.adView);
		// AdRequest adRequest = new AdRequest.Builder().build();
		// adView.loadAd(adRequest);
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}

	public static Bitmap decodeBitmapFromFile(String path, int reqWidth, int reqHeight) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		BitmapFactory.decodeFile(path, options);
		configSize(options, 640, 640);
		return BitmapFactory.decodeFile(path, options);
	}

	public static Bitmap decodeSampledBitmapFromResource(byte[] data) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		BitmapFactory.decodeStream(new ByteArrayInputStream(data), null, options);
		configSize(options, 640, 640);

		return BitmapFactory.decodeStream(new ByteArrayInputStream(data), new Rect(0, 0, 0, 0), options);
	}

	public static void configSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		int height = options.outHeight;
		int width = options.outWidth;
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		int inSize = 1;

		if (height > reqHeight) {
			inSize = Math.round((float) height / (float) reqHeight);
		}
		int expectedWidth = width / inSize;

		if (expectedWidth > reqWidth) {
			inSize = Math.round((float) width / (float) reqWidth);
		}

		options.inSampleSize = inSize;
		options.inJustDecodeBounds = false;

	}

}
