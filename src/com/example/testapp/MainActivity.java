package com.example.testapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends ListActivity{

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.listv);
		String[] values = {"assinment1","assinment1","assinment3","assinment5","assinment1"};
		//ArrayAdapter<String> ad = new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.label,values);
		//setListAdapter(ad);
		Task A[]={new Task(true, "HomeWork"),new Task(false,"classWotk"),new Task(true, "blaaa"),new Task(false,"laundry")};
		SimpleAdapter Adapter = new SimpleAdapter(this, A);
		setListAdapter(Adapter);
	}

}
