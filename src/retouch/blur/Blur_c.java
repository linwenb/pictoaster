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
import com.pictoaster.www.retouch.effect.Lomo;


public class Blur_c extends Activity {
    /** Called when the activity is first created. */
	private ProgressBar ProgressBar_Blurc;
	private ImageView imgView1;
	private SeekBar seekbar3;
	private Bitmap bmp1 = null;
	private Bitmap bmp2 = null;
	private Bitmap bmp3 = null;
	private Bitmap modified;
	private Canvas canvas;
	private Canvas canvas1;
	private double maxR;
	private int midX;
	private int midY;
	private int iniR;
	private int progressTemp;
	Thread threadBlurc;
	Handler handlerBlurc = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retouch_blur);

        Bundle fieldresults = this.getIntent().getExtras();
	    String path = fieldresults.getString("bm");
	    bmp1 = BitmapFactory.decodeFile(path);
	    modified= bmp1.copy(Bitmap.Config.ARGB_8888, true);
	    midX = bmp1.getWidth() / 2;
    	midY = bmp1.getHeight() / 2;
    	if (bmp1.getWidth() >= bmp1.getHeight()) {
        	iniR = bmp1.getHeight() / 4;
    	}
    	else {
    		iniR = bmp1.getWidth() / 4;
    	}
	    
    	canvas1 = new Canvas(modified);
		canvas1.save(); 
		
		
		Paint p = new Paint(); //  实例化paint 类   
	    p.setAntiAlias(true);     // 设置去锯齿   
        p.setColor(Color.BLACK);   // 设置paint 的颜色是红色的   
		p.setStyle(Paint.Style.STROKE);   // 设置为空心的 
		canvas1.drawCircle(midX,midY,iniR,p);  
		
		imgView1 = (ImageView)findViewById(R.id.imgView1);
    	imgView1.setImageBitmap(modified);
		
    	canvas1.restore();
        
    	ProgressBar_Blurc = (ProgressBar) findViewById(R.id.blurcBar);
    	
    	seekbar3 = (SeekBar)findViewById(R.id.seekbar3);
    	
    	//maxR =  Math.sqrt(Math.pow(bmp1.getWidth(),2) + Math.pow(bmp1.getHeight(), 2)) / 2;
    	//seekbar3.setMax((int) maxR);
    	if (bmp1.getWidth() >= bmp1.getHeight()) {
        	seekbar3.setProgress(bmp1.getHeight() / 4);
        	seekbar3.setMax(bmp1.getHeight());
    	}
    	else {
    		seekbar3.setProgress(bmp1.getWidth() / 4);
    		seekbar3.setMax(bmp1.getWidth());
    	}
    	
    	seekbar3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {   
    		@Override
    		public void onStartTrackingTouch(SeekBar seekbar) {}
    		@Override
    		public void onStopTrackingTouch(SeekBar seekbar) {}
    		@Override
			public void onProgressChanged(SeekBar seekbar, final int progress, boolean fromUser) {
                   
    			ProgressBar_Blurc.setVisibility(View.VISIBLE);
    			threadBlurc = new Thread(new Runnable(){
    				 public void run(){
    					 progressTemp = progress;
    					 operateCircle(progressTemp, midX, midY);
    						 handlerBlurc.post(new Runnable(){
    							 public void run(){
    								 imgView1.setImageBitmap(bmp3);
    								 ProgressBar_Blurc.setVisibility(View.GONE);
    							 }
    			 });
    			 }
    		});
    	    threadBlurc.start();		   			
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
			     Toast.makeText(Blur_c.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Blur_c.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    }
			    
			    Intent intent = new Intent(Blur_c.this, Retouch.class);
			    Bundle bundle = Blur_c.this.getIntent().getExtras();
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
		
		ImageView cancel = (ImageView) findViewById(R.id.blur_error);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Blur_c.this, Retouch.class);
				Bundle bundle = Blur_c.this.getIntent().getExtras();
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

    //final
    private void operateCircle(int R,  int X, int Y) {
    	
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
		
		modified = blur_circle(bmp1, R, X, Y);
		bmp3 = modified.copy(Bitmap.Config.ARGB_8888, true);
		canvas = new Canvas(bmp3);
		canvas.save(); 
		
		
		Paint p = new Paint(); //  实例化paint 类   
	    p.setAntiAlias(true);     // 设置去锯齿   
        p.setColor(Color.BLACK);   // 设置paint 的颜色是红色的   
		p.setStyle(Paint.Style.STROKE);   // 设置为空心的 
		canvas.drawCircle(X,Y,R,p);  
		
    }

    //final
    private Bitmap blur_circle (Bitmap bmp,int R, int X, int Y) {
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
    	
    	//up
    	for(int i=1; i<Y-R-1; i++) {
    		for (int j=1; j<width-1; j++) {
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
    	
    	//left
    	for(int i=Y-R+1; i<Y+R-1; i++) {
    		for (int j=1; j<X-R-1; j++) {
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
    	
    	//right
    	for(int i=Y-R+1; i<Y+R-1; i++) {
    		for (int j=X+R+1; j<width-1; j++) {
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
    	
    	//down
    	for(int i=Y+R+1; i<height-1; i++) {
    		for (int j=1; j<width-1; j++) {
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
    	
    	int r = (int) (Math.cos(45*Math.PI/180) * R);
    	
    	//middle up
    	for (int i=Y-R+1; i<Y-r-1; i++) {
    		for (int j=X-R+1; j<X+R-1; j++) {
    			if (Math.pow(Math.abs(j-X), 2) + Math.pow(Math.abs(i-Y), 2) > Math.pow(R, 2)) {
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
    	}
    	
    	//middle down
    	for (int i=Y+r+1; i<Y+R-1; i++) {
    		for (int j=X-R+1; j<X+R-1; j++) {
    			if (Math.pow(Math.abs(j-X), 2) + Math.pow(Math.abs(i-Y), 2) > Math.pow(R, 2)) {
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
    	}
    	
    	//middle left
    	for (int i=Y-r+1; i<Y+r-1; i++) {
    		for (int j=X-R+1; j<X-r-1; j++) {
    			if (Math.pow(Math.abs(j-X), 2) + Math.pow(Math.abs(i-Y), 2) > Math.pow(R, 2)) {
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
    	}
    	
    	//middle right
    	for (int i=Y-r+1; i<Y+r-1; i++) {
    		for (int j=X+r+1; j<X+R-1; j++) {
    			if (Math.pow(Math.abs(j-X), 2) + Math.pow(Math.abs(i-Y), 2) > Math.pow(R, 2)) {
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
    	}
    	/*for (int i=1; i<height-1; i++) {
    		for (int j=1; j<width-1; j++) {
    			if (Math.pow(Math.abs(j-X), 2) + Math.pow(Math.abs(i-Y), 2) > Math.pow(R, 2)) {
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
    	}*/
    	
    	bitmap.setPixels(pixels, 0, width, 0, 0, width, height);  
    	return bitmap;
    }
    
}
