package com.pictoaster.www.retouch;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import com.pictoaster.www.R;
import com.pictoaster.www.retouch.effect.Fashion;
import com.pictoaster.www.retouch.effect.Lomo;

public class Effects extends TabActivity {

	private TabHost mTabHost;// for tabs function
	
	@SuppressWarnings("deprecation")
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
	    intent = new Intent().setClass(this, Lomo.class);
	    intent.putExtras(bundle);
	    spec = mTabHost.newTabSpec("lomo")
	    		.setIndicator("LOMO", res.getDrawable(R.drawable.ic_tab_edit))
	            .setContent(intent);
	    mTabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, Fashion.class);
	    intent.putExtras(bundle); 
	    spec = mTabHost.newTabSpec("fashion")
	    		.setIndicator("Fashion", res.getDrawable(R.drawable.tab_frame_special))
	    		.setContent(intent);
	    mTabHost.addTab(spec);

	    //done for building tabs
	    
	    //it will display the first tab content by default
	    mTabHost.setCurrentTab(0);
	}

}