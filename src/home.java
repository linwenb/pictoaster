package com.pictoaster.www;

import java.io.File;
import java.io.FileOutputStream;

import com.pictoaster.www.download.ConvBubble;
import com.pictoaster.www.download.Delete;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;


@SuppressWarnings("deprecation")
public class home extends TabActivity {
   
	private TabHost mTabHost;// for tabs function
	
	/** Called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        String FILENAME = "down_file.txt";
		String content = "0000000000000000";
		File writeFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
		
		if(!writeFile.exists())
		{
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {

			try {
				FileOutputStream fos = new FileOutputStream(writeFile);
				fos.write(content.getBytes());
				fos.close();
				/*Toast.makeText(home.this, content,
                       Toast.LENGTH_LONG).show();*/
			} catch (Exception e) {
                Toast.makeText(home.this, "Write Failed",
                       Toast.LENGTH_SHORT).show();
            }
			} else {
        	//sdcard doesn't exist or can't write it right now
            Toast.makeText(home.this,
                    "Can't Write", Toast.LENGTH_SHORT).show();
			}
		}
		
        //build the about button
        Button aboutBTN = (Button) findViewById(R.id.btn_about);
        aboutBTN.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(0);// it calls onCreateDialog function bellow, 0 is argument for about
			}
		});
      
        //build the setting button
        Button settingBTN = (Button) findViewById(R.id.btn_setting);
        settingBTN.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//showDialog(1);//1 is the argument for setting
				Intent intent = new Intent(home.this, Setting.class);
				Bundle bundle1 = home.this.getIntent().getExtras();
			    int tem = bundle1.getInt("download", 0);
			    Bundle bundle = new Bundle();
				bundle.putInt("download", tem);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
        
        
        /*
         * bellow are all for building tabs
         * I cannot move them to a new function and I dont know why
         * */
        Resources res = getResources(); // Resource object to get Drawables
        
        mTabHost = getTabHost();  // The activity TabHost
        
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
		
	    intent = new Intent().setClass(home.this, home_retouch.class);
	    
	    Bundle bundle1 = this.getIntent().getExtras();
	    int tem = bundle1.getInt("download", 0);
	    //int tem = bundle1.getInt("download", 0);
	    Bundle bundle = new Bundle();
		bundle.putInt("download", tem);
		intent.putExtras(bundle);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = mTabHost.newTabSpec("retouch")
	    		.setIndicator("Retouch", res.getDrawable(R.layout.tab_home_retouch))
	            .setContent(intent);
	    mTabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(home.this, home_montage.class);
	    spec = mTabHost.newTabSpec("montage")
	    		.setIndicator("Montage", res.getDrawable(R.layout.tab_home_montage))
	    		.setContent(intent);
	    mTabHost.addTab(spec);

	    intent = new Intent().setClass(home.this, home_download.class);
	    spec = mTabHost.newTabSpec("download")
	    		.setIndicator("Download", res.getDrawable(R.layout.tab_home_download))
	    		.setContent(intent);
	    mTabHost.addTab(spec);
	    //done for building tabs
	    
    
	    //it will display the first tab content by default
	    mTabHost.setCurrentTab(0);
    }
    
    protected Dialog onCreateDialog(int id) {    
        switch(id) {
        case 0: // for about button content
        	return new AlertDialog.Builder(this)
        	.setTitle("PicsToaster")
        	.setMessage("this is a powerful pictures editor")
        	.setNegativeButton("Cancel", null)
        	.show();
        case 1: // for settings button content
        	return  new AlertDialog.Builder(this)
        	.setTitle("Settings")
        	.setCancelable(false)  // this is friendly
        	.setMessage("here is for building settings")
        	.setPositiveButton("Ok", null)
        	.setNeutralButton("Clean records", new DialogInterface.OnClickListener() { 
                public void onClick(DialogInterface dialog, int id) { 
                	String FILENAME = "down_file.txt";
                	File delete = new File(Environment.getExternalStorageDirectory(), FILENAME);
            	    if(delete.exists())
            	    {
            	    	boolean deleted = delete.delete();
            	    }
                } 
        	}) 
        	.setNegativeButton("Cancel", null)
        	.show();
        }
        return null;
    }
}