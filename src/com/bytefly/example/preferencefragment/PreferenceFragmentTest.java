package com.bytefly.example.preferencefragment;

import com.bytefly.example.preferencefragment.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PreferenceFragmentTest extends Activity {
	private Button prefBtn;
	private Preferences frag;
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            frag=new Preferences();
            prefBtn = (Button) findViewById(R.id.prefBtn);
            prefBtn.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                            getFragmentManager().beginTransaction().add(android.R.id.content,frag,"PREFS").commit();
                            v.setVisibility(View.INVISIBLE);
                    }
            });
    }
	boolean CheckboxPreference;
	String ListPreference;
	String editTextPreference;
	String ringtonePreference;
	String secondEditTextPreference;
	String customPref;

	private void getPrefs() {
		// Get the xml/preferences.xml preferences
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		CheckboxPreference = prefs.getBoolean("checkboxPref", true);
		ListPreference = prefs.getString("listPref", "nr1");
		editTextPreference = prefs.getString("editTextPref",
				"Nothing has been entered");
		ringtonePreference = prefs.getString("ringtonePref",
				"DEFAULT_RINGTONE_URI");
		secondEditTextPreference = prefs.getString("SecondEditTextPref",
				"Nothing has been entered");
		// Get the custom preference
		SharedPreferences mySharedPreferences = getSharedPreferences(
				"myCustomSharedPrefs", Activity.MODE_PRIVATE);
		customPref = mySharedPreferences.getString("myCusomPref", "");
		
		Toast.makeText(getBaseContext(),
				"CheckboxPreference "+CheckboxPreference,
				Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		getPrefs();
	}
	
	@Override
	public void onBackPressed() {
		if(frag!=null && frag.isAdded()){
			prefBtn.setVisibility(View.VISIBLE);
			getFragmentManager().beginTransaction().remove(frag).commit();
		}else{
			super.onBackPressed();
		}
		
	}
}