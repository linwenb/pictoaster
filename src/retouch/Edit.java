package com.pictoaster.www.retouch;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import com.pictoaster.www.R;
import com.pictoaster.www.home_montage;
import com.pictoaster.www.retouch.edit.Crop;
import com.pictoaster.www.retouch.edit.Mirror;
import com.pictoaster.www.retouch.edit.Rotation;
import com.pictoaster.www.retouch.edit.Sharpen;

@SuppressWarnings("deprecation")
public class Edit extends TabActivity {
	
	private TabHost mTabHost;// for tabs function
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.retouch_tab);
        
        
        Bundle bundle = this.getIntent().getExtras();
        
        
        
        /*
         * bellow are all for building tabs
         * */
        Resources res = getResources(); // Resource object to get Drawables
        
        mTabHost = getTabHost();  // The activity TabHost
        
	    TabHost.TabSpec spec;  // Reusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, Rotation.class);
	    intent.putExtras(bundle);
	    spec = mTabHost.newTabSpec("rotate")
	    		.setIndicator("Rotate", res.getDrawable(R.drawable.tab_rorate))
	            .setContent(intent);
	    mTabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, Crop.class);
	    intent.putExtras(bundle);
	    spec = mTabHost.newTabSpec("crop")
	    		.setIndicator("Crop", res.getDrawable(R.drawable.tab_crop))
	    		.setContent(intent);
	    mTabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, Mirror.class);
	    intent.putExtras(bundle); 
	    spec = mTabHost.newTabSpec("mirror")
	    		.setIndicator("Mirror", res.getDrawable(R.drawable.tab_mirror))
	    		.setContent(intent);
	    mTabHost.addTab(spec);

	    intent = new Intent().setClass(this, Sharpen.class);
	    intent.putExtras(bundle); 
	    spec = mTabHost.newTabSpec("sharpen")
	    		.setIndicator("Sharpen", res.getDrawable(R.drawable.tab_sharpen))
	    		.setContent(intent);
	    mTabHost.addTab(spec);
	    //done for building tabs
	    
	    //it will display the first tab content by default
	    mTabHost.setCurrentTab(0);
	}
}