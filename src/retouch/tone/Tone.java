package com.pictoaster.www.retouch.tone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.pictoaster.www.R;
import com.pictoaster.www.retouch.Retouch;

public class Tone extends Activity implements OnSeekBarChangeListener{
	
	private ToneMethod mToneLayer;
	private Bitmap mBitmap;
	private ImageView mImageView;
	private Bitmap modified;
	//private SeekBar tone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.retouch_tone);
		
		init();
	}
	
	private void init()
	{
		mToneLayer = new ToneMethod(this);
		
		Bundle fieldresults = this.getIntent().getExtras();
	    String path = fieldresults.getString("bm");
	
	    mBitmap = BitmapFactory.decodeFile(path);
	    modified = mBitmap;
	    
		//mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.im);
		mImageView = (ImageView) findViewById(R.id.img_view);
		mImageView.setImageBitmap(mBitmap);
		((LinearLayout) findViewById(R.id.tone_view)).addView(mToneLayer.getParentView());
		
		ArrayList<SeekBar> seekBars = mToneLayer.getSeekBar();
		for(int i = 0; i< seekBars.size(); i ++)
		{
			seekBars.get(i).setOnSeekBarChangeListener(this);
		}
		
		ImageView ok = (ImageView) findViewById(R.id.tone_check);
		ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String extStorage = Environment.getExternalStorageDirectory().toString();
				
				File tem_file = new File(extStorage, "Retouch_tem.JPEG");
			    if(tem_file.exists())
			    {
			    	boolean deleted = tem_file.delete();
			    }
			    
			    File file = new File(extStorage, "Retouch_tem.JPEG");
			     
			    try {
			     OutputStream outStream = new FileOutputStream(file);
			     modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
			     outStream.flush();
			        outStream.close();
			        /*Toast.makeText(Tone.this, 
			          extStorage+"/Retouch_tem.JPEG", 
			          Toast.LENGTH_LONG).show();*/
			    } catch (FileNotFoundException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Tone.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Tone.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    }
			    
			    Intent intent = new Intent(Tone.this, Retouch.class);
			    Bundle bundle = Tone.this.getIntent().getExtras();
			    intent.putExtras(bundle);
			    
			    if(!modified.isRecycled() ){
			        modified.recycle(); //回收图片所占的内存
			         System.gc(); //提醒系统及时回收
			     }
			    
				startActivity(intent);
				System.gc();
				System.exit(0);
				//finish();
			}
		});
		
		ImageView cancel = (ImageView) findViewById(R.id.tone_error);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Tone.this, Retouch.class);
				Bundle bundle = Tone.this.getIntent().getExtras();
			    intent.putExtras(bundle);
			    
			    if(!modified.isRecycled() ){
			        modified.recycle(); //回收图片所占的内存
			         System.gc(); //提醒系统及时回收
			     }
			    
				startActivity(intent);
				System.gc();
				System.exit(0);
				//finish();
			}
		});
	}
	
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		int flag = (Integer) seekBar.getTag();
		switch(flag)
		{
		case ToneMethod.FLAG_SATURATION:
			mToneLayer.setSaturation(progress);
			break;
		case ToneMethod.FLAG_LIGHTNESS:
			mToneLayer.setLightness(progress);
			break;
		case ToneMethod.FLAG_HUE:
			mToneLayer.setHue(progress);
			break;
		}
		
		modified = mToneLayer.handleImage(mBitmap, flag);
		mImageView.setImageBitmap(modified);
		
		
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
}
