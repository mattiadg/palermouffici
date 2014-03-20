/**
 * @author mattiadigan
 */

package it.bigteam;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UfficioDetailActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		setContentView(R.layout.detail_view_activity);
		TextView[] tv = new TextView[10];
		Bundle bundle = getIntent().getExtras();
		//Creazione delle textView
		tv[0] = (TextView)findViewById(R.id.textView2);
		tv[0].setText(bundle.getString("area"));
		tv[1] = (TextView)findViewById(R.id.textView4);
		tv[1].setText(bundle.getString("area"));
		tv[2] = (TextView)findViewById(R.id.textView6);
		tv[2].setText(bundle.getString("area"));
		tv[3] = (TextView)findViewById(R.id.textView8);
		tv[3].setText(bundle.getString("area"));
		tv[4] = (TextView)findViewById(R.id.textView10);
		tv[4].setText(bundle.getString("area"));
		tv[5] = (TextView)findViewById(R.id.textView12);
		tv[5].setText(bundle.getString("area"));
		tv[6] = (TextView)findViewById(R.id.textView14);
		tv[6].setText(bundle.getString("area"));
		tv[7] = (TextView)findViewById(R.id.textView16);
		tv[7].setText(bundle.getString("area"));
		tv[8] = (TextView)findViewById(R.id.textView18);
		tv[8].setText(bundle.getString("area"));
		tv[9] = (TextView)findViewById(R.id.textView20);
		tv[9].setText(bundle.getString("area"));
		
	}
}
