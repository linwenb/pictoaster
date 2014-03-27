package com.pictoaster.www.retouch.blur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.pictoaster.www.R;
import com.pictoaster.www.retouch.Retouch;


public class Blur_l extends Activity {
    /** Called when the activity is first created. */
	private ProgressBar ProgressBar_Blurl;
	private ImageView imgView1;
	private SeekBar seekbar1;
	private SeekBar seekbar2;
	private Bitmap bmp1 = null;
	private Bitmap bmp2 = null;
	private Bitmap bmp3 = null;
	private Bitmap modified;
	private int midline;
	private int span;
	private Canvas canvas;
	private Canvas canvas1;
	Thread threadBlurl;
	Handler handlerBlurl = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retouch_blur_l);

        Bundle fieldresults = this.getIntent().getExtras();
	    String path = fieldresults.getString("bm");
	    
	    bmp1 = BitmapFactory.decodeFile(path);
	    
	    midline = bmp1.getWidth() / 2;
    	span = bmp1.getWidth() / 4;
	    
	    modified = bmp1.copy(Bitmap.Config.ARGB_8888, true);
	    ProgressBar_Blurl = (ProgressBar) findViewById(R.id.blurlBar);
	    
	    canvas1 = new Canvas(modified);
		canvas1.save();
		
	    //draw leftline
		Paint paint4 = new Paint(); //  实例化paint 类   
	    paint4.setAntiAlias(true);     // 设置去锯齿   
        paint4.setColor(Color.GRAY);   // 设置paint 的颜色是红色的   
		paint4.setStyle(Paint.Style.STROKE);   // 设置为空心的  
		canvas1.drawLine(midline - span, 0, midline - span, modified.getHeight(), paint4);   //  划一条线段
		
		//draw rightline
		Paint paint5 = new Paint(); //  实例化paint 类   
	    paint5.setAntiAlias(true);     // 设置去锯齿   
        paint5.setColor(Color.GRAY);   // 设置paint 的颜色是红色的   
		paint5.setStyle(Paint.Style.STROKE);   // 设置为空心的  
		canvas1.drawLine(midline + span, 0, midline + span, modified.getHeight(), paint5);   //  划一条线段
	    
        imgView1 = (ImageView)findViewById(R.id.imgView1);
    	imgView1.setImageBitmap(modified);
    	canvas1.restore();
		
		
    	seekbar1 = (SeekBar)findViewById(R.id.seekbar1);
    	seekbar1.setMax(bmp1.getWidth());
    	seekbar1.setProgress(bmp1.getWidth() / 2);
    	seekbar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {   
    		@Override
    		public void onStartTrackingTouch(SeekBar seekbar) {}
    		@Override
    		public void onStopTrackingTouch(SeekBar seekbar) {}
    		@Override
			public void onProgressChanged(SeekBar seekbar, final int progress, boolean fromUser) {
    			ProgressBar_Blurl.setVisibility(View.VISIBLE);
    			threadBlurl = new Thread(new Runnable(){
    				 public void run(){			 
    					 midline = progress;
    	                 operateLine(midline, span, bmp1.getHeight());
    					 handlerBlurl.post(new Runnable() {
    						public void run(){
    							imgView1.setImageBitmap(bmp3);
    							ProgressBar_Blurl.setVisibility(View.GONE);
    						}
    					 });
    				 }
    			});
    			threadBlurl.start();
    			
    		}
    	} ) ;
    	seekbar2 = (SeekBar)findViewById(R.id.seekbar2);
    	seekbar2.setMax(bmp1.getWidth() / 2);
    	seekbar2.setProgress(bmp1.getWidth() / 4);
    	seekbar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {   
    		@Override
    		public void onStartTrackingTouch(SeekBar seekbar) {}
    		@Override
    		public void onStopTrackingTouch(SeekBar seekbar) {}
    		@Override
			public void onProgressChanged(SeekBar seekbar, final int progress, boolean fromUser) {
    			ProgressBar_Blurl.setVisibility(View.VISIBLE);
    			threadBlurl = new Thread(new Runnable(){
    				 public void run(){	
    					 span = progress;
    		    		 operateLine(midline, span, bmp1.getHeight());
    					 
    					 handlerBlurl.post(new Runnable() {
    						public void run(){
    							imgView1.setImageBitmap(bmp3);
    							ProgressBar_Blurl.setVisibility(View.GONE);
    						}
    					 });
    				 }
    			});
    			threadBlurl.start();		   			
    		}
    	} ) ;
        
		
    	ImageView ok = (ImageView) findViewById(R.id.blur_check);
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
			        /*Toast.makeText(Blur.this, 
			          extStorage+"/Retouch_tem.JPEG", 
			          Toast.LENGTH_LONG).show();*/
			    } catch (FileNotFoundException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Blur_l.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Blur_l.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    }
			    
			    Intent intent = new Intent(Blur_l.this, Retouch.class);
			    Bundle bundle = Blur_l.this.getIntent().getExtras();
			    intent.putExtras(bundle);
			    if(!modified.isRecycled() ){
			        modified.recycle(); //回收图片所占的内存
			         System.gc(); //提醒系统及时回收
			     }
				startActivity(intent);
				System.exit(0);
				//finish();
			}
		});
		
		ImageView cancel = (ImageView) findViewById(R.id.blur_error);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Blur_l.this, Retouch.class);
				Bundle bundle = Blur_l.this.getIntent().getExtras();
			    intent.putExtras(bundle);
			    if(!modified.isRecycled() ){
			        modified.recycle(); //回收图片所占的内存
			         System.gc(); //提醒系统及时回收
			     }
				startActivity(intent);
				System.exit(0);
				//finish();
			}
		});
    }

  //do operation with changed midline or changed span
    private void operateLine(int midline, int span, int height) {

    	/*if(modified != null && !modified.isRecycled()){   
    		modified.recycle(); 
    		modified = null;
		}
    	
    	if(bmp2 != null && !bmp2.isRecycled()){   
			 bmp2.recycle(); 
			 bmp2 = null;
		}
		
		if(bmp3 != null && !bmp3.isRecycled()){   
			 bmp3.recycle(); 
			 bmp3 = null;
		}*/
       
		if(midline - span > 3) {
			bmp2 = blur_left(bmp1, midline - span);
			
			if(midline + span < bmp1.getWidth()-3){
				bmp2 = blur_right(bmp2, midline + span);
				
				modified = bmp2;
				bmp3 = bmp2.copy(Bitmap.Config.ARGB_8888, true);
				canvas = new Canvas(bmp3);
				canvas.save();
				
				//draw midline
				/*
				Paint paint1 = new Paint(); //  实例化paint 类   
			    paint1.setAntiAlias(true);     // 设置去锯齿   
                paint1.setColor(Color.BLACK);   // 设置paint 的颜色是红色的   
				paint1.setStyle(Paint.Style.STROKE);   // 设置为空心的  
				canvas.drawLine(midline, 0, midline, height, paint1);   //  划一条线段
			    */
				
				//draw leftline
				Paint paint2 = new Paint(); //  实例化paint 类   
			    paint2.setAntiAlias(true);     // 设置去锯齿   
                paint2.setColor(Color.GRAY);   // 设置paint 的颜色是红色的   
				paint2.setStyle(Paint.Style.STROKE);   // 设置为空心的  
				canvas.drawLine(midline - span, 0, midline - span, height, paint2);   //  划一条线段
				
				//draw rightline
				Paint paint3 = new Paint(); //  实例化paint 类   
			    paint3.setAntiAlias(true);     // 设置去锯齿   
                paint3.setColor(Color.GRAY);   // 设置paint 的颜色是红色的   
				paint3.setStyle(Paint.Style.STROKE);   // 设置为空心的  
				canvas.drawLine(midline + span, 0, midline + span, height, paint3);   //  划一条线段
					       
				//canvas.restore(); 
			}	
			else {
				
				modified = bmp2;
				bmp3 = bmp2.copy(Bitmap.Config.ARGB_8888, true);
				canvas = new Canvas(bmp3);
				canvas.save();
				
				//draw midline
				/*
				Paint paint1 = new Paint(); //  实例化paint 类   
			    paint1.setAntiAlias(true);     // 设置去锯齿   
                paint1.setColor(Color.BLACK);   // 设置paint 的颜色是红色的   
				paint1.setStyle(Paint.Style.STROKE);   // 设置为空心的  
				canvas.drawLine(midline, 0, midline, height, paint1);   //  划一条线段
				*/
				
				//draw leftline
				Paint paint2 = new Paint(); //  实例化paint 类   
			    paint2.setAntiAlias(true);     // 设置去锯齿   
                paint2.setColor(Color.GRAY);   // 设置paint 的颜色是红色的   
				paint2.setStyle(Paint.Style.STROKE);   // 设置为空心的  
				canvas.drawLine(midline - span, 0, midline - span, height, paint2);   //  划一条线段

				//canvas.restore();
				
			}
		}
		else {
			if(midline + span < bmp1.getWidth()-3){
				bmp2 = blur_right(bmp1, midline + span);
				
				modified = bmp2;
				bmp3 = bmp2.copy(Bitmap.Config.ARGB_8888, true);
				
				canvas = new Canvas(bmp3);
				canvas.save(); 
				
				/*
				//draw midline
				Paint paint1 = new Paint(); //  实例化paint 类   
			    paint1.setAntiAlias(true);     // 设置去锯齿   
                paint1.setColor(Color.BLACK);   // 设置paint 的颜色是红色的   
				paint1.setStyle(Paint.Style.STROKE);   // 设置为空心的  
				canvas.drawLine(midline, 0, midline, height, paint1);   //  划一条线段
				*/
				
				//draw rightline
				Paint paint3 = new Paint(); //  实例化paint 类   
			    paint3.setAntiAlias(true);     // 设置去锯齿   
                paint3.setColor(Color.GRAY);   // 设置paint 的颜色是红色的   
				paint3.setStyle(Paint.Style.STROKE);   // 设置为空心的  
				canvas.drawLine(midline + span, 0, midline + span, height, paint3);   //  划一条线段
							
				//canvas.restore(); 
			}
			else {
				
				modified = bmp1;
				bmp3 = bmp1.copy(Bitmap.Config.ARGB_8888, true);
				
				canvas = new Canvas(bmp3);
				canvas.save(); 
				
				/*
				//draw midline
				Paint paint1 = new Paint(); //  实例化paint 类   
			    paint1.setAntiAlias(true);     // 设置去锯齿   
                paint1.setColor(Color.BLACK);   // 设置paint 的颜色是红色的   
				paint1.setStyle(Paint.Style.STROKE);   // 设置为空心的  
				canvas.drawLine(midline, 0, midline, height, paint1);   //  划一条线段
			    */
			
				//canvas.restore();
				
			}
		}
    }

	//blur left part
    private Bitmap blur_left (Bitmap bmp, int progress) {
    	int[] gaosi = new int[] {1,1,1,1,1,1,1,1,1};
    	
    	int width = bmp.getWidth();
    	int height = bmp.getHeight();
    	Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    	
    	int pixR = 0;
    	int pixG = 0;
    	int pixB = 0;
    	
    	int pixColor = 0;
    	
    	int newR = 0;
    	int newG = 0;
    	int newB = 0;
    	
    	//int delta = 16;
    	
    	int idx = 0;
    	int[] pixels = new int[width*height];
    	bmp.getPixels(pixels, 0, width, 0, 0, width, height);
    	
    	for (int i=1; i<height-1; i++) {
    		for (int j=1; j<progress; j++) {
    			idx=0;
    			for (int m = -1; m <= 1; m++)  
                {  
                    for (int n = -1; n <= 1; n++)  
                    {  
                        pixColor = pixels[(i + m) * width + j + n];  
                        pixR = Color.red(pixColor);  
                        pixG = Color.green(pixColor);  
                        pixB = Color.blue(pixColor);  
                          
                        newR = newR + (int) (pixR * gaosi[idx]);  
                        newG = newG + (int) (pixG * gaosi[idx]);  
                        newB = newB + (int) (pixB * gaosi[idx]);  
                        idx++;  
                    }  
                }  	
    			
    			newR /= 9;  
                newG /= 9;  
                newB /= 9;  
                  
                newR = Math.min(255, Math.max(0, newR));  
                newG = Math.min(255, Math.max(0, newG));  
                newB = Math.min(255, Math.max(0, newB));  
                  
                pixels[i * width + j] = Color.argb(255, newR, newG, newB);  
                  
                newR = 0;  
                newG = 0;  
                newB = 0;  

    		}
    	}
    	
    	bitmap.setPixels(pixels, 0, width, 0, 0, width, height);  
    	return bitmap;
    }
    
    //blur right part
    private Bitmap blur_right (Bitmap bmp, int progress) {
    	int[] gaosi = new int[] {1,1,1,1,1,1,1,1,1};
    	
    	int width = bmp.getWidth();
    	int height = bmp.getHeight();
    	Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    	
    	int pixR = 0;
    	int pixG = 0;
    	int pixB = 0;
    	
    	int pixColor = 0;
    	
    	int newR = 0;
    	int newG = 0;
    	int newB = 0;
    	
    	//int delta = 16;
    	
    	int idx = 0;
    	int[] pixels = new int[width*height];
    	bmp.getPixels(pixels, 0, width, 0, 0, width, height);
    	
    	for (int i=1; i<height-1; i++) {
    		for (int j=progress; j<width-1; j++) {
    			idx=0;
    			for (int m = -1; m <= 1; m++)  
                {  
                    for (int n = -1; n <= 1; n++)  
                    {  
                        pixColor = pixels[(i + m) * width + j + n];  
                        pixR = Color.red(pixColor);  
                        pixG = Color.green(pixColor);  
                        pixB = Color.blue(pixColor);  
                          
                        newR = newR + (int) (pixR * gaosi[idx]);  
                        newG = newG + (int) (pixG * gaosi[idx]);  
                        newB = newB + (int) (pixB * gaosi[idx]);  
                        idx++;  
                    }  
                }  	
    			
    			newR /= 9;  
                newG /= 9;  
                newB /= 9;  
                  
                newR = Math.min(255, Math.max(0, newR));  
                newG = Math.min(255, Math.max(0, newG));  
                newB = Math.min(255, Math.max(0, newB));  
                  
                pixels[i * width + j] = Color.argb(255, newR, newG, newB);  
                  
                newR = 0;  
                newG = 0;  
                newB = 0;  

    		}
    	}
    	
    	bitmap.setPixels(pixels, 0, width, 0, 0, width, height);  
    	return bitmap;
    }
    
}
