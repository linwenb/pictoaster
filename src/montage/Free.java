package com.pictoaster.www.montage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.pictoaster.www.R;

public class Free extends Activity implements View.OnClickListener{

	@SuppressWarnings("deprecation")
	private AbsoluteLayout view;
	
	private Bitmap bmp1;	
	private Bitmap bm1;
	private ImageView img1;
	private SeekBar rotation1;
	private SeekBar scale1;
	private int width1;
	private int height1;
	private int sca1 = 50;
	private int rot1 = 50;
	private int l1 = 0;
	private int t1 = 0;
	
	private Bitmap bmp2;	
	private Bitmap bm2;
	private ImageView img2;
	private SeekBar rotation2;
	private SeekBar scale2;
	private int width2;
	private int height2;
	private int sca2 = 50;
	private int rot2 = 50;
	private int l2 = 60;
	private int t2 = 0;
	
	private Bitmap bmp3;	
	private Bitmap bm3;
	private ImageView img3;
	private SeekBar rotation3;
	private SeekBar scale3;
	private int width3;
	private int height3;
	private int sca3 = 50;
	private int rot3 = 50;
	private int l3 = 60;
	private int t3 = 60;
	
	private int bg;
	
	private Intent intent;
	private Bundle bundle;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.montage_free);
        Bundle bundle = getIntent().getExtras();
        
        bg = bundle.getInt("bg");
        
        setupButton();
        setupViews(); 
        
        switch(bg)
		{
		case 1:view.setBackgroundResource(R.drawable.bg1);break;
		case 2:view.setBackgroundResource(R.drawable.bg2);break;
		case 3:view.setBackgroundResource(R.drawable.bg3);break;
		case 4:view.setBackgroundResource(R.drawable.bg4);break;
		case 5:view.setBackgroundResource(R.drawable.bg5);break;
		case 6:view.setBackgroundResource(R.drawable.bg6);break;
		}
  
    }
    
    private void setupButton(){
		view = (AbsoluteLayout)findViewById(R.id.result);
		view.setBackgroundColor(Color.WHITE);
		
		ImageView cancel = (ImageView) findViewById(R.id.error);
		ImageView ok = (ImageView) findViewById(R.id.check);
		Button btnbg = (Button) findViewById(R.id.btn_change_bg);
        
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        btnbg.setOnClickListener(this);
    }
    
    public void onClick(View v) {
		// TODO Auto-generated method stub
    	
		switch(v.getId())
		{
		case R.id.btn_change_bg:
			
			clear();
			
			intent = new Intent(Free.this, Background.class);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		
		case R.id.check:

			view.setDrawingCacheEnabled(true);
			view.buildDrawingCache();
			
			Bitmap bm = view.getDrawingCache();
			
			String extStorage = Environment.getExternalStorageDirectory().toString();
			File tem_file = new File(extStorage, "Montage_tem.JPEG");
		    if(tem_file.exists())
		    {
		    	boolean deleted = tem_file.delete();
		    }
		    
		    File file = new File(extStorage, "Montage_tem.JPEG");
		    
		    try {
		     OutputStream outStream = new FileOutputStream(file);
		     bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
		     outStream.flush();
		     outStream.close();

		    } catch (FileNotFoundException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		     Toast.makeText(Free.this, 
		          e.toString(), 
		          Toast.LENGTH_LONG).show();
		    } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		     Toast.makeText(Free.this, 
		          e.toString(), 
		          Toast.LENGTH_LONG).show();
		    }
		    
		    clear();
		    
		    intent = new Intent(Free.this, Montage.class);
		    intent.putExtras(bundle);
			startActivity(intent);
			finish();
			
			break;
		case R.id.error:
			
			clear();
			intent = new Intent(Free.this, Montage.class);
			intent.putExtras(bundle);
			startActivity(intent);
			finish();
			break;
		}
	}
    
    private void clear()
    {
	    bmp1.recycle();		    
	    bmp2.recycle();
	    bmp3.recycle();
	    
	    bmp1 = null;
	    bmp2 = null;
	    bmp3 = null;
		
	    if(bm1 != null){
		    bm1.recycle();		    	    
		    bm1 = null;
		    System.gc();
	    }
	    
	    if(bm2 != null){
	    	bm2.recycle();
	    	bm2 = null;
	    	System.gc();
		} 
	    if(bm3 != null){
	    	bm3.recycle();
		    bm3 = null;
		    System.gc();
	    }
    }

    private void setupViews() 
    {
        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);

        bundle = this.getIntent().getExtras();
        
	    String path1 = bundle.getString("bm1");
	    String path2 = bundle.getString("bm2");
	    String path3 = bundle.getString("bm3");
	    
	    int scale;
	    
	    BitmapFactory.Options options1 = new BitmapFactory.Options();   
        options1.inJustDecodeBounds = true;
        bmp1 = BitmapFactory.decodeFile(path1, options1);                
	    for(scale = 2; options1.outWidth*options1.outHeight > 300000; scale = scale * 2)
	    {
	    	options1.inSampleSize = scale;
	    	bmp1 = BitmapFactory.decodeFile(path1, options1);
	    }
	    options1.inJustDecodeBounds = false;
	    bmp1 = BitmapFactory.decodeFile(path1, options1);
	    
	    
	    BitmapFactory.Options options2 = new BitmapFactory.Options();   
        options2.inJustDecodeBounds = true;
        bmp2 = BitmapFactory.decodeFile(path2, options2);                
	    for(scale = 2; options2.outWidth*options2.outHeight > 300000; scale = scale * 2)
	    {
	    	options2.inSampleSize = scale;
	    	bmp2 = BitmapFactory.decodeFile(path2, options2);
	    }
	    options2.inJustDecodeBounds = false;
	    bmp2 = BitmapFactory.decodeFile(path2, options2);
	    
	    BitmapFactory.Options options3 = new BitmapFactory.Options();   
        options3.inJustDecodeBounds = true;
        bmp3 = BitmapFactory.decodeFile(path3, options3);                
	    for(scale = 2; options3.outWidth*options3.outHeight > 300000; scale = scale * 2)
	    {
	    	options3.inSampleSize = scale;
	    	bmp3 = BitmapFactory.decodeFile(path3, options3);
	    }
	    options3.inJustDecodeBounds = false;
	    bmp3 = BitmapFactory.decodeFile(path3, options3);
         
	    width1 = bmp1.getWidth(); 
        width2 = bmp2.getWidth(); 
        width3 = bmp3.getWidth(); 
        
	    height1 = bmp1.getHeight();
	    height2 = bmp2.getHeight();
	    height3 = bmp3.getHeight();
	        
	    setScale();
        			    
		img1.setImageBitmap(bmp1);
		img2.setImageBitmap(bmp2);
		img3.setImageBitmap(bmp3);

        img1.setOnTouchListener(touchListener1);
        img2.setOnTouchListener(touchListener2);
        img3.setOnTouchListener(touchListener3);
        
	    rotation1 = (SeekBar)findViewById(R.id.rotation1);
	    rotation2 = (SeekBar)findViewById(R.id.rotation2);
	    rotation3 = (SeekBar)findViewById(R.id.rotation3);
	    
	    scale1 = (SeekBar)findViewById(R.id.scale1);
	    scale2 = (SeekBar)findViewById(R.id.scale2);
	    scale3 = (SeekBar)findViewById(R.id.scale3);
	    
	    rotation1.setProgress(50);
	    rotation2.setProgress(50);
	    rotation3.setProgress(50);
	    
	    scale1.setProgress(50);
	    scale2.setProgress(50);
	    scale3.setProgress(50);
	    
        rotation1.setOnSeekBarChangeListener(setBar1R);
        rotation2.setOnSeekBarChangeListener(setBar2R);
        rotation3.setOnSeekBarChangeListener(setBar3R);
        
        scale1.setOnSeekBarChangeListener(setBar1S);
        scale2.setOnSeekBarChangeListener(setBar2S);
        scale3.setOnSeekBarChangeListener(setBar3S);
    }
    
    private void setScale() {

        Matrix matrix1 = new Matrix(); 	    
        Matrix matrix2 = new Matrix();
        Matrix matrix3 = new Matrix();	    
        
        float size = 1F*240/width1;   
        matrix1.postScale(size, size);
        bmp1 = Bitmap.createBitmap(bmp1, 0, 0, width1 ,height1 , matrix1, true);
        
        size = 1F*240/width2;    
        matrix2.postScale(size, size);
        bmp2 = Bitmap.createBitmap(bmp2, 0, 0, width2 ,height2 , matrix2, true);
        
        size = 1F*240/width3;        
        matrix3.postScale(size, size);
        bmp3 = Bitmap.createBitmap(bmp3, 0, 0, width3 ,height3 , matrix3, true);
        
	    width1 = bmp1.getWidth(); 
        width2 = bmp2.getWidth(); 
        width3 = bmp3.getWidth(); 
        
	    height1 = bmp1.getHeight();
	    height2 = bmp2.getHeight();
	    height3 = bmp3.getHeight();
	}

	OnSeekBarChangeListener setBar1R = new OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
            	f1(sca1,progress);
            }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
    };
    OnSeekBarChangeListener setBar1S = new OnSeekBarChangeListener(){
	        @Override
	        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	            // TODO Auto-generated method stub
	        	f1(progress,rot1);
	        }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
    };
    
	OnSeekBarChangeListener setBar2R = new OnSeekBarChangeListener(){
	        @Override
	        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	            // TODO Auto-generated method stub
	        	f2(sca2,progress);
	        }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
	};
	OnSeekBarChangeListener setBar2S = new OnSeekBarChangeListener(){
	        @Override
	        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	            // TODO Auto-generated method stub
	        	f2(progress,rot2);
	        }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
	};

	OnSeekBarChangeListener setBar3R = new OnSeekBarChangeListener(){
		    @Override
		    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		        // TODO Auto-generated method stub
		    	f3(sca3,progress);
		    }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
	};
	OnSeekBarChangeListener setBar3S = new OnSeekBarChangeListener(){
		    @Override
		    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		        // TODO Auto-generated method stub
		    	f3(progress,rot3);
		    }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
	};
	
    OnTouchListener touchListener1 = new OnTouchListener()
    {
    	private int mx, my;  
    	
		@Override
		public boolean onTouch(View arg0, MotionEvent event){
			// TODO Auto-generated method stub
			
			int x = (int)event.getRawX();
			int y = (int)event.getRawY();
			
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mx = (int)event.getX();
				my = (int)(y-arg0.getTop());
				arg0.bringToFront();
				arg0.postInvalidate();
				break;
				
            case MotionEvent.ACTION_MOVE:
                int left = x - mx;
                int top = y - my;
                
                l1=left;
                t1=top;

                int right = left + arg0.getWidth();
                int bottom = top + arg0.getHeight();  
                    
                arg0.layout(left, top, right, bottom); 
                arg0.postInvalidate();
                break;
            }
			return true;
		}
    };
    OnTouchListener touchListener2 = new OnTouchListener()
    {
    	private int mx, my;  
    	
		@Override
		public boolean onTouch(View arg0, MotionEvent event){
			// TODO Auto-generated method stub
			
			int x = (int)event.getRawX();
			int y = (int)event.getRawY();
			
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mx = (int)event.getX();
				my = (int)(y-arg0.getTop());
				arg0.bringToFront();
				arg0.postInvalidate();
				break;
				
            case MotionEvent.ACTION_MOVE:
                int left = x - mx;
                int top = y - my;

                l2=left;
                t2=top;

                int right = left + arg0.getWidth();
                int bottom = top + arg0.getHeight();  
                    
                arg0.layout(left, top, right, bottom); 
                arg0.postInvalidate();
                break;
            }
			return true;
		}
    };
    OnTouchListener touchListener3 = new OnTouchListener()
    {
    	private int mx, my;  
    	
		@Override
		public boolean onTouch(View arg0, MotionEvent event){
			// TODO Auto-generated method stub
			
			int x = (int)event.getRawX();
			int y = (int)event.getRawY();
			
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mx = (int)event.getX();
				my = (int)(y-arg0.getTop());
				arg0.bringToFront();
				arg0.postInvalidate();
				break;
				
            case MotionEvent.ACTION_MOVE:
                int left = x - mx;
                int top = y - my;

                l3=left;
                t3=top;
                
                int right = left + arg0.getWidth();
                int bottom = top + arg0.getHeight();  
                    
                arg0.layout(left, top, right, bottom); 
                arg0.postInvalidate();
                break;
            }
			return true;
		}
    };
    
    public void f1(int scale, int angle){
    	 
    	sca1 = scale;
        rot1 = angle;
        
        int defaultScale = scale - 50;
        float sizeWidth = (1+(float)defaultScale/100);
        float sizeHeight = (1+(float)defaultScale/100);
            
        Matrix matrix1 = new Matrix(); 
        matrix1.postScale(sizeWidth, sizeHeight);   
        bm1 = Bitmap.createBitmap(bmp1, 0, 0, width1 ,height1 , matrix1, true);

        int w = bm1.getWidth();
        int h = bm1.getHeight();
                
    	Matrix matrix2 = new Matrix(); 
        matrix2.postRotate((angle-50)*3.6f);        
        bm1 = Bitmap.createBitmap(bm1, 0, 0, w ,h , matrix2, true);
        
	    img1.setImageBitmap(bm1);
	    /*
        int right = l1 + w;
        int bottom = t1 + h;  
            
        img1.layout(l1, t1, w, h); 
        img1.postInvalidate();
        */
        
    }

    public void f2(int scale, int angle){
   	 
    	sca2 = scale;
        rot2 = angle;
        
        int defaultScale = scale - 50;
        float sizeWidth = (1+(float)defaultScale/100);
        float sizeHeight = (1+(float)defaultScale/100);
            
        Matrix matrix1 = new Matrix(); 
        matrix1.postScale(sizeWidth, sizeHeight);   
        bm2 = Bitmap.createBitmap(bmp2, 0, 0, width2 ,height2 , matrix1, true);

        int w = bm2.getWidth();
        int h = bm2.getHeight();
                
    	Matrix matrix2 = new Matrix(); 
        matrix2.postRotate((angle-50)*3.6f);        
        bm2 = Bitmap.createBitmap(bm2, 0, 0, w ,h , matrix2, true);
        
	    img2.setImageBitmap(bm2);
	    /*
	    int right = l2 + img2.getWidth();
        int bottom = t2 + img2.getHeight();  
            
        img2.layout(l2, t2, right, bottom); 
        img2.postInvalidate();
        */
    }
    
    public void f3(int scale, int angle){
   	 
    	sca3 = scale;
        rot3 = angle;
        
        int defaultScale = scale - 50;
        float sizeWidth = (1+(float)defaultScale/100);
        float sizeHeight = (1+(float)defaultScale/100);
            
        Matrix matrix1 = new Matrix(); 
        matrix1.postScale(sizeWidth, sizeHeight);   
        bm3 = Bitmap.createBitmap(bmp3, 0, 0, width3 ,height3 , matrix1, true);

        int w = bm3.getWidth();
        int h = bm3.getHeight();
                
    	Matrix matrix2 = new Matrix(); 
        matrix2.postRotate((angle-50)*3.6f);        
        bm3 = Bitmap.createBitmap(bm3, 0, 0, w ,h , matrix2, true);
        
	    img3.setImageBitmap(bm3);
	    /*
	    int right = l3 + img3.getWidth();
        int bottom = t3 + img3.getHeight();  
            
        img3.layout(l3, t3, right, bottom); 
        img3.postInvalidate();
        */
    }

}
