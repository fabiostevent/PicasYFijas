package com.android.picasfijas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UrlActivity extends Activity {

	EditText url;
	Button continuar;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.url_activity);
		url=(EditText) findViewById(R.id.etUrl);
		continuar=(Button) findViewById(R.id.continuarUrl);
		tv=(TextView) findViewById(R.id.txtUrl);
		
		Typeface fuente= Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf");
		url.setTypeface(fuente);
		continuar.setTypeface(fuente);
		tv.setTypeface(fuente);
		
		continuar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String direccion=url.getText().toString();
				if(direccion.length()==0) {
					Intent i=new Intent(getApplicationContext(), PicasFijasActivity.class);
					i.putExtra("url", "http://192.167.1.36/PruebaPicasFijas/pic2.php");
					startActivity(i);
					finish();
				}
				else {
					Intent i=new Intent(getApplicationContext(), PicasFijasActivity.class);
					i.putExtra("url", direccion);
					startActivity(i);
					finish();
				}
			}
		});
	}
	
}
