package br.com.jera.reidocamarote;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class CommandmentsAdapter extends BaseAdapter {

	boolean[] commandmentsCheckedStatus = new boolean[10];

	@Override
	public int getCount() {
		return Commandments.values().length;
	}

	@Override
	public Commandments getItem(int position) {
		return Commandments.values()[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Commandments commandment = getItem(position);
		if (convertView == null) {
			convertView = View.inflate(parent.getContext(),
					R.layout.commandment_item, null);
		}

		((TextView) convertView.findViewById(R.id.commandment_title))
				.setText(commandment.name);
		CheckBox checkBox = (CheckBox) convertView
				.findViewById(R.id.commandment_check);
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton compoundButton,
					boolean checked) {
				commandmentsCheckedStatus[position] = checked;
			}
		});
		checkBox.setChecked(commandmentsCheckedStatus[position]);

		return convertView;
	}

}
