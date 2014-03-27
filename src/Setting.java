package com.pictoaster.www;

import java.io.File;

import com.pictoaster.www.download.ConvBubble;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Setting extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//»•µÙ±ÍÃ‚¿∏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.setting);       
        
        Button img1 = (Button) findViewById(R.id.btn_clean);
        Button img2 = (Button) findViewById(R.id.btn_ok);
        Button img3 = (Button) findViewById(R.id.btn_cancel);
        
        img1.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Setting.this, home.class);
				String FILENAME = "down_file.txt";
            	File delete = new File(Environment.getExternalStorageDirectory(), FILENAME);
        	    if(delete.exists())
        	    {
        	    	boolean deleted = delete.delete();
        	    }
        	    Toast.makeText(Setting.this, "Download records have been cleaned!",
                        Toast.LENGTH_LONG).show();
        	    Bundle bundle1 = Setting.this.getIntent().getExtras();
        	    int tem = bundle1.getInt("download", 0);
        	    //int tem = bundle1.getInt("download", 0);
        	    Bundle bundle = new Bundle();
        		bundle.putInt("download", tem);
        		intent.putExtras(bundle);
				startActivity(intent);				
			}
        });
        
        img2.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Setting.this, home.class);
				startActivity(intent);				
			}
        });
        
        img3.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Setting.this, home.class);
				startActivity(intent);				
			}
        });
	}
}