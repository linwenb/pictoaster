package com.pictoaster.www.download;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import com.pictoaster.www.R;

public class DownloadHome extends TabActivity  {
	private TabHost mTabHost;
	
	@SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.retouch_tab);
        
        Resources res = getResources();        
        mTabHost = getTabHost();
        
	    TabHost.TabSpec spec;
	    Intent intent;

	    intent = new Intent().setClass(this, Download.class);
	    spec = mTabHost.newTabSpec("download")
	    		.setIndicator("Download", res.getDrawable(R.layout.tab_home_download))
	            .setContent(intent);
	    mTabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, Delete.class);
	    spec = mTabHost.newTabSpec("delete")
	    		.setIndicator("Delete", res.getDrawable(R.drawable.tab_download_delete))
	    		.setContent(intent);
	    mTabHost.addTab(spec); 

	    mTabHost.setCurrentTab(0);
	}
}
