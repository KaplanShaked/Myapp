package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;

public class SimpleAdapter extends ArrayAdapter<Task>{
	 private final Context context;
	  private final Task[] values;
	  
	  public SimpleAdapter(Context context, Task[] values) {
		    super(context, R.layout.rowlayout, values);
		    this.context = context;
		    this.values = values;
		  }
	  static class ViewHolder {
		    protected EditText text;
		    protected CheckBox checkbox;
		  }
	  
	  
	  @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		    LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		     EditText textView = (EditText) rowView.findViewById(R.id.label);
		    CheckBox ck = (CheckBox) rowView.findViewById(R.id.chk);
		    final ViewHolder viewh = new ViewHolder();
		    viewh.text=textView;
		    viewh.checkbox=ck;
		    viewh.checkbox.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(viewh.checkbox.isSelected()){
					viewh.text.setClickable(false);
					viewh.text.setFocusable(false);
					viewh.text.setFocusableInTouchMode(false);
					//viewh.text.setBackgroundColor(255);
					}
					else{
						viewh.text.setClickable(true);
						viewh.text.setFocusable(true);
						viewh.text.setFocusableInTouchMode(true);
					}
				}
			});
		    /*
		    viewh.checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					
					viewh.text.setEnabled(viewh.checkbox.isSelected());
					}
			});
			*/
		    textView.setText(values[position].getToDo());
		    // Change the icon for Windows and iPhone
		    boolean s = values[position].getDone();
		     ck.setChecked(s);
		     textView.setClickable(!s);
		     viewh.text.setFocusable(!s);
				viewh.text.setFocusableInTouchMode(!s);
		    return rowView;
		  }
}
