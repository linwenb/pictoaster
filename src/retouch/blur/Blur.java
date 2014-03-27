package com.pictoaster.www.retouch.blur;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import com.pictoaster.www.R;


public class Blur extends TabActivity {

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

	    intent = new Intent().setClass(this, Blur_l.class);
	    intent.putExtras(bundle);
	    spec = mTabHost.newTabSpec("linear")
	    		.setIndicator("Linear", res.getDrawable(R.drawable.tab_blur_l))
	            .setContent(intent);
	    mTabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, Blur_c.class);
	    intent.putExtras(bundle); 
	    spec = mTabHost.newTabSpec("circular")
	    		.setIndicator("Circular", res.getDrawable(R.drawable.tab_blur_c))
	    		.setContent(intent);
	    mTabHost.addTab(spec);

	    mTabHost.setCurrentTab(0);
    }

}
