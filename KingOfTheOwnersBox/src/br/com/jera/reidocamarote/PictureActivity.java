package br.com.jera.reidocamarote;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class PictureActivity extends OverlayActivity implements OnClickListener {

	public static final String PICTURE_KEY = "picture";
	public static final String PICTURE_TYPE_KEY = "picture_type";
	public static final int PICTURE_BYTES = 1;
	public static final int PICTURE_PATH = 2;

	private ImageView pictureView;
	private View pictureMask;
	private Uri imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture);

		pictureView = (ImageView) findViewById(R.id.picture);
		pictureMask = findViewById(R.id.picture_mask);
		getPicture(getIntent());

		findViewById(R.id.button_share).setOnClickListener(this);

		loadOverlayData();
		Utils.applyTypeface(getWindow().getDecorView());
	}

	public void getPicture(Intent intent) {
		if (intent != null && intent.getExtras() != null) {

			switch (intent.getExtras().getInt(PICTURE_TYPE_KEY)) {
			case PICTURE_BYTES:
				getPictureFromBytes(intent.getExtras());
				break;
			case PICTURE_PATH:
				getPictureFromPath(intent.getExtras());
				break;
			}
		}
	}

	private void getPictureFromPath(Bundle extras) {
		String path = extras.getString(PICTURE_KEY);
		if (!TextUtils.isEmpty(path)) {
			Drawable drawable = BitmapDrawable.createFromPath(path);
			pictureView.setImageDrawable(drawable);
			imageUri = Uri.parse(path);
		}
	}

	private void getPictureFromBytes(Bundle extras) {
		byte[] bytes = extras.getByteArray(PICTURE_KEY);
		if (bytes != null && bytes.length > 0) {
			InputStream is = new ByteArrayInputStream(bytes);
			Bitmap bmp = BitmapFactory.decodeStream(is);
			if (bmp != null) {

				int sideSize = Math.min(bmp.getHeight(), bmp.getWidth());

				// Setting post rotate to 90
				Matrix mtx = new Matrix();
				mtx.postRotate(90);

				// Rotating Bitmap
				Bitmap picture = Bitmap.createBitmap(bmp, 0, 0, sideSize,
						sideSize, mtx, true);
				pictureView.setImageBitmap(picture);
			} else {
				// TODO Mostrar erro e finalizar activity
				return;
			}
		}
	}

	@Override
	public void onClick(View v) {
		int viewId = v.getId();
		switch (viewId) {
		case R.id.button_share:
			drawImage();
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			imageUri = data.getData();
			pictureView.setImageURI(imageUri);
		}
	}

	public void updateImageUri() {
		if (imageUri != null)
			return;

		Bitmap bitmap = ((BitmapDrawable) pictureView.getDrawable())
				.getBitmap();
		imageUri = getImageUri(bitmap, "psstmpimg");
	}

	public void drawImage() {
		Bitmap image = Bitmap.createBitmap(pictureView.getWidth(),
				pictureView.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(image);
		pictureView.draw(c);
		pictureMask.draw(c);
		sharePicture(getImageUri(image, "pss_sharepic"));

	}

	public void sharePicture(Uri uri) {
		Intent share = new Intent(Intent.ACTION_SEND);
		share.putExtra(Intent.EXTRA_STREAM, uri);
		share.setType("image/*");
		share.putExtra(Intent.EXTRA_TEXT, "#SouOReiDoCamarote http://bit.ly/1hOeaNG");
		share.putExtra(Intent.EXTRA_TITLE, "#SouOReiDoCamarote http://bit.ly/1hOeaNG");
		share.putExtra(Intent.EXTRA_SUBJECT, "#SouOReiDoCamarote http://bit.ly/1hOeaNG");
		startActivity(Intent.createChooser(share, "Compartilhar"));
	}

	public Uri getImageUri(Bitmap bitmap, String pictureName) {
		return Uri.parse(save(bitmap, pictureName));
	}

	public String save(Bitmap bitmap, String pictureName) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
		String path = Images.Media.insertImage(this.getContentResolver(),
				bitmap, pictureName, null);
		return path;
	}

}
