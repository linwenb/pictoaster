package com.pictoaster.www.retouch.frame;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TabHost;
import com.pictoaster.www.R;

public class Frame extends TabActivity {
    /** Called when the activity is first created. */

	private TabHost mTabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.retouch_tab);

    	Bundle bundle = this.getIntent().getExtras();
        Resources res = getResources(); // Resource object to get Drawables
        
        mTabHost = getTabHost();  // The activity TabHost
        
	    TabHost.TabSpec spec;  // Reusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    intent = new Intent().setClass(this, SimpleFrame.class);
	    intent.putExtras(bundle);
	    spec = mTabHost.newTabSpec("simple")
	    		.setIndicator("Simple", res.getDrawable(R.drawable.tab_frame_simple))
	            .setContent(intent);
	    mTabHost.addTab(spec);
	    
	    
	    intent = new Intent().setClass(this, SpecialFrame.class);
	    intent.putExtras(bundle); 
	    spec = mTabHost.newTabSpec("special")
	    		.setIndicator("Special", res.getDrawable(R.drawable.tab_frame_special))
	    		.setContent(intent);
	    mTabHost.addTab(spec);

	    mTabHost.setCurrentTab(0);
    }

}
