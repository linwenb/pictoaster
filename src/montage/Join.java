package com.pictoaster.www.montage;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import com.pictoaster.www.R;

public class Join extends TabActivity {

	private TabHost mTabHost;// for tabs function
	
	@SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.retouch_tab);
        
        Bundle bundle = this.getIntent().getExtras();
        
        Resources res = getResources(); // Resource object to get Drawables
        
        mTabHost = getTabHost();  // The activity TabHost
        
	    TabHost.TabSpec spec;  // Reusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, Join_v.class);
	    intent.putExtras(bundle);
	    spec = mTabHost.newTabSpec("vertical")
	    		.setIndicator("Vertical", res.getDrawable(R.drawable.ic_tab_join_v))
	            .setContent(intent);
	    mTabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, Join_h.class);
	    intent.putExtras(bundle); 
	    spec = mTabHost.newTabSpec("horizontal")
	    		.setIndicator("Horizontal", res.getDrawable(R.drawable.ic_tab_join_h))
	    		.setContent(intent);
	    mTabHost.addTab(spec);

	    //done for building tabs
	    
	    //it will display the first tab content by default
	    mTabHost.setCurrentTab(0);
	}

}