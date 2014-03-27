package com.pictoaster.www.retouch.edit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.pictoaster.www.R;
import com.pictoaster.www.retouch.Retouch;
import com.pictoaster.www.retouch.effect.Fashion;


public class Sharpen extends Activity {
	protected void onDestroy() {
		super.onDestroy();
		if(!bmp1.isRecycled()){
		bmp1.recycle();
		System.gc();
		}
		if(!bmp2.isRecycled()){
		bmp2.recycle();
		System.gc();
		}
	}

	private ProgressBar ProgressBar_Sharpen;
	private ImageView imgView1;
	private SeekBar seekbar;
	private Bitmap bmp1 = null;
	private Bitmap bmp2 = null;
	private Bitmap mask = null;
    private int temp_progress;
	private float[] f = {(float) 1, (float) 1.005, (float) 1.01, (float) 1.015, (float) 1.02};
	Thread threadSharpen;
	Handler handlerSharpen = new Handler();
	String extStorage = Environment.getExternalStorageDirectory().toString();

    /** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
        setContentView(R.layout.retouch_edit_sharpen);

        Bundle fieldresults = this.getIntent().getExtras();
        
        String path;
        
        File tem_file = new File(extStorage, "Edit_tem.JPEG");
	    if(tem_file.exists())
	    {
	    	path = extStorage + "/Edit_tem.JPEG";
	    }
	    else
	    {
	    	path = fieldresults.getString("bm");
	    }
	    
	    bmp1 = BitmapFactory.decodeFile(path);
	    
        imgView1 = (ImageView)findViewById(R.id.imgView1);
    	imgView1.setImageBitmap(bmp1);
    	
    	
    	ProgressBar_Sharpen = (ProgressBar) findViewById(R.id.sharpenBar);
    	seekbar = (SeekBar)findViewById(R.id.seekbar1);
    	seekbar.setMax(5);
    	seekbar.setProgress(0);
    	
    	//get mask of bmp1
	    //mask = getMask(bmp1);
	    
    	ImageView ok = (ImageView) findViewById(R.id.sharpen_check);
		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File tem_file2 = new File(extStorage, "Crop_tem.JPEG");
				if(tem_file2.exists())
				{
					boolean deleted = tem_file2.delete();
				}
				
				File tem_file1 = new File(extStorage, "Edit_tem.JPEG");
				if(tem_file1.exists())
			    {
					boolean deleted = tem_file1.delete();
				}

				File tem_file = new File(extStorage, "Retouch_tem.JPEG");
			    if(tem_file.exists())
			    {
			    	boolean deleted = tem_file.delete();
			    }

			    File file = new File(extStorage, "Retouch_tem.JPEG");

			    try {
			     OutputStream outStream = new FileOutputStream(file);
			     bmp2.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
			     outStream.flush();
			        outStream.close();
			        /*Toast.makeText(Sharpen.this,
			          extStorage+"/Retouch_tem.JPEG",
			          Toast.LENGTH_LONG).show();*/
			    } catch (FileNotFoundException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Sharpen.this,
			          e.toString(),
			          Toast.LENGTH_LONG).show();
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Sharpen.this,
			          e.toString(),
			          Toast.LENGTH_LONG).show();
			    }

			    Intent intent = new Intent(Sharpen.this, Retouch.class);
			    Bundle bundle = Sharpen.this.getIntent().getExtras();
			    intent.putExtras(bundle);
			    if(!bmp2.isRecycled() ){
			        bmp2.recycle(); //回收图片所占的内存
			         System.gc(); //提醒系统及时回收
			     }
				startActivity(intent);
			}
		});

		ImageView cancel = (ImageView) findViewById(R.id.sharpen_error);
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File tem_file2 = new File(extStorage, "Crop_tem.JPEG");
				if(tem_file2.exists())
				{
					boolean deleted = tem_file2.delete();
				}
				
				File tem_file1 = new File(extStorage, "Edit_tem.JPEG");
				if(tem_file1.exists())
			    {
					boolean deleted = tem_file1.delete();
				}
				
				Intent intent = new Intent(Sharpen.this, Retouch.class);
				Bundle bundle = Sharpen.this.getIntent().getExtras();
			    intent.putExtras(bundle);
			    if(!bmp2.isRecycled() ){
			        bmp2.recycle(); //回收图片所占的内存
			         System.gc(); //提醒系统及时回收
			     }
				startActivity(intent);
				//finish();
			}
		});

        temp_progress = 0;
    	bmp2 = bmp1;
    	seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
    		@Override
    		public void onStartTrackingTouch(SeekBar seekbar) {}
    		@Override
    		public void onStopTrackingTouch(SeekBar seekbar) {}
    		@Override
			public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
    			
                if (temp_progress == progress) {
    				imgView1.setImageBitmap(bmp2);
    			}
                else if(temp_progress < progress) {
    				for(int i=temp_progress+1; i<=progress; i++) {
    					switch(i) {
    					case 1:
    						ProgressBar_Sharpen.setVisibility(View.VISIBLE);
    						threadSharpen = new Thread(new Runnable(){
    							 public void run(){

    		    						bmp2 = sharpenOld(bmp2, (float) f[0]);
    									 
    									 handlerSharpen.post(new Runnable(){
    										 public void run(){
    											 File tem_file = new File(extStorage, "Edit_tem.JPEG");
    												if(tem_file.exists())
    											    {
    													boolean deleted = tem_file.delete();
    												}
    												    
    											    File file = new File(extStorage, "Edit_tem.JPEG");
    											    
    											    try {
    											     OutputStream outStream = new FileOutputStream(file);
    											     bmp2.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
    											     outStream.flush();
    											        outStream.close();
    											        /*Toast.makeText(Sharpen.this, 
    											          extStorage+"/Edit_tem.JPEG", 
    											          Toast.LENGTH_LONG).show();*/
    											    } catch (FileNotFoundException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    } catch (IOException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    }
    											 imgView1.setImageBitmap(bmp2); 
    											 ProgressBar_Sharpen.setVisibility(View.GONE);
    										 }
    						 });
    						 }
    					});
    				    threadSharpen.start();
    						break;
    					case 2:
    						ProgressBar_Sharpen.setVisibility(View.VISIBLE);
    						threadSharpen = new Thread(new Runnable(){
    							 public void run(){

    		    						bmp2 = sharpenOld(bmp2, (float) f[1]);
    									 
    									 handlerSharpen.post(new Runnable(){
    										 public void run(){
    											 File tem_file = new File(extStorage, "Edit_tem.JPEG");
    												if(tem_file.exists())
    											    {
    													boolean deleted = tem_file.delete();
    												}
    												    
    											    File file = new File(extStorage, "Edit_tem.JPEG");
    											    
    											    try {
    											     OutputStream outStream = new FileOutputStream(file);
    											     bmp2.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
    											     outStream.flush();
    											        outStream.close();
    											        /*Toast.makeText(Sharpen.this, 
    											          extStorage+"/Edit_tem.JPEG", 
    											          Toast.LENGTH_LONG).show();*/
    											    } catch (FileNotFoundException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    } catch (IOException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    }
    											 imgView1.setImageBitmap(bmp2); 
    											 ProgressBar_Sharpen.setVisibility(View.GONE);
    										 }
    						 });
    						 }
    					});
    				    threadSharpen.start();
    						break;
    					case 3:
    						ProgressBar_Sharpen.setVisibility(View.VISIBLE);
    						threadSharpen = new Thread(new Runnable(){
    							 public void run(){

    		    						bmp2 = sharpenOld(bmp2, (float) f[2]);
    									 
    									 handlerSharpen.post(new Runnable(){
    										 public void run(){
    											 File tem_file = new File(extStorage, "Edit_tem.JPEG");
    												if(tem_file.exists())
    											    {
    													boolean deleted = tem_file.delete();
    												}
    												    
    											    File file = new File(extStorage, "Edit_tem.JPEG");
    											    
    											    try {
    											     OutputStream outStream = new FileOutputStream(file);
    											     bmp2.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
    											     outStream.flush();
    											        outStream.close();
    											        /*Toast.makeText(Sharpen.this, 
    											          extStorage+"/Edit_tem.JPEG", 
    											          Toast.LENGTH_LONG).show();*/
    											    } catch (FileNotFoundException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    } catch (IOException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    }
    											 imgView1.setImageBitmap(bmp2); 
    											 ProgressBar_Sharpen.setVisibility(View.GONE);
    										 }
    						 });
    						 }
    					});
    				    threadSharpen.start();
    						break;
    					case 4:
    						ProgressBar_Sharpen.setVisibility(View.VISIBLE);
    						threadSharpen = new Thread(new Runnable(){
    							 public void run(){

    		    						bmp2 = sharpenOld(bmp2, (float) f[3]);
    									 
    									 handlerSharpen.post(new Runnable(){
    										 public void run(){
    											 File tem_file = new File(extStorage, "Edit_tem.JPEG");
    												if(tem_file.exists())
    											    {
    													boolean deleted = tem_file.delete();
    												}
    												    
    											    File file = new File(extStorage, "Edit_tem.JPEG");
    											    
    											    try {
    											     OutputStream outStream = new FileOutputStream(file);
    											     bmp2.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
    											     outStream.flush();
    											        outStream.close();
    											        /*Toast.makeText(Sharpen.this, 
    											          extStorage+"/Edit_tem.JPEG", 
    											          Toast.LENGTH_LONG).show();*/
    											    } catch (FileNotFoundException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    } catch (IOException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    }
    											 imgView1.setImageBitmap(bmp2); 
    											 ProgressBar_Sharpen.setVisibility(View.GONE);
    										 }
    						 });
    						 }
    					});
    				    threadSharpen.start();
    						break;
    					case 5:
    						ProgressBar_Sharpen.setVisibility(View.VISIBLE);
    						threadSharpen = new Thread(new Runnable(){
    							 public void run(){

    		    						bmp2 = sharpenOld(bmp2, (float) f[4]);
    									 
    									 handlerSharpen.post(new Runnable(){
    										 public void run(){
    											 File tem_file = new File(extStorage, "Edit_tem.JPEG");
    												if(tem_file.exists())
    											    {
    													boolean deleted = tem_file.delete();
    												}
    												    
    											    File file = new File(extStorage, "Edit_tem.JPEG");
    											    
    											    try {
    											     OutputStream outStream = new FileOutputStream(file);
    											     bmp2.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
    											     outStream.flush();
    											        outStream.close();
    											        /*Toast.makeText(Sharpen.this, 
    											          extStorage+"/Edit_tem.JPEG", 
    											          Toast.LENGTH_LONG).show();*/
    											    } catch (FileNotFoundException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    } catch (IOException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    }
    											 imgView1.setImageBitmap(bmp2); 
    											 ProgressBar_Sharpen.setVisibility(View.GONE);
    										 }
    						 });
    						 }
    					});
    				    threadSharpen.start();
    						break;
    					}
    				}
    				temp_progress = progress;
				}
    			else {
    				bmp2 = bmp1;
    				temp_progress = progress;
    				for(int i=1; i<=progress; i++) {
    					switch(i) {
    					case 1:
    						ProgressBar_Sharpen.setVisibility(View.VISIBLE);
    						threadSharpen = new Thread(new Runnable(){
    							 public void run(){

    		    						bmp2 = sharpenOld(bmp2, (float) f[0]);
    									 
    									 handlerSharpen.post(new Runnable(){
    										 public void run(){
    											 File tem_file = new File(extStorage, "Edit_tem.JPEG");
    												if(tem_file.exists())
    											    {
    													boolean deleted = tem_file.delete();
    												}
    												    
    											    File file = new File(extStorage, "Edit_tem.JPEG");
    											    
    											    try {
    											     OutputStream outStream = new FileOutputStream(file);
    											     bmp2.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
    											     outStream.flush();
    											        outStream.close();
    											        /*Toast.makeText(Sharpen.this, 
    											          extStorage+"/Edit_tem.JPEG", 
    											          Toast.LENGTH_LONG).show();*/
    											    } catch (FileNotFoundException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    } catch (IOException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    }
    											 imgView1.setImageBitmap(bmp2); 
    											 ProgressBar_Sharpen.setVisibility(View.GONE);
    										 }
    						 });
    						 }
    					});
    				    threadSharpen.start();
    						break;
    					case 2:
    						ProgressBar_Sharpen.setVisibility(View.VISIBLE);
    						threadSharpen = new Thread(new Runnable(){
    							 public void run(){

    		    						bmp2 = sharpenOld(bmp2, (float) f[1]);
    									 
    									 handlerSharpen.post(new Runnable(){
    										 public void run(){
    											 File tem_file = new File(extStorage, "Edit_tem.JPEG");
    												if(tem_file.exists())
    											    {
    													boolean deleted = tem_file.delete();
    												}
    												    
    											    File file = new File(extStorage, "Edit_tem.JPEG");
    											    
    											    try {
    											     OutputStream outStream = new FileOutputStream(file);
    											     bmp2.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
    											     outStream.flush();
    											        outStream.close();
    											        /*Toast.makeText(Sharpen.this, 
    											          extStorage+"/Edit_tem.JPEG", 
    											          Toast.LENGTH_LONG).show();*/
    											    } catch (FileNotFoundException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    } catch (IOException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    }
    											 imgView1.setImageBitmap(bmp2); 
    											 ProgressBar_Sharpen.setVisibility(View.GONE);
    										 }
    						 });
    						 }
    					});
    				    threadSharpen.start();
    						break;
    					case 3:
    						ProgressBar_Sharpen.setVisibility(View.VISIBLE);
    						threadSharpen = new Thread(new Runnable(){
    							 public void run(){

    		    						bmp2 = sharpenOld(bmp2, (float) f[2]);
    									 
    									 handlerSharpen.post(new Runnable(){
    										 public void run(){
    											 File tem_file = new File(extStorage, "Edit_tem.JPEG");
    												if(tem_file.exists())
    											    {
    													boolean deleted = tem_file.delete();
    												}
    												    
    											    File file = new File(extStorage, "Edit_tem.JPEG");
    											    
    											    try {
    											     OutputStream outStream = new FileOutputStream(file);
    											     bmp2.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
    											     outStream.flush();
    											        outStream.close();
    											        /*Toast.makeText(Sharpen.this, 
    											          extStorage+"/Edit_tem.JPEG", 
    											          Toast.LENGTH_LONG).show();*/
    											    } catch (FileNotFoundException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    } catch (IOException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    }
    											 imgView1.setImageBitmap(bmp2); 
    											 ProgressBar_Sharpen.setVisibility(View.GONE);
    										 }
    						 });
    						 }
    					});
    				    threadSharpen.start();
    						break;
    					case 4:
    						ProgressBar_Sharpen.setVisibility(View.VISIBLE);
    						threadSharpen = new Thread(new Runnable(){
    							 public void run(){

    		    						bmp2 = sharpenOld(bmp2, (float) f[3]);
    									 
    									 handlerSharpen.post(new Runnable(){
    										 public void run(){
    											 File tem_file = new File(extStorage, "Edit_tem.JPEG");
    												if(tem_file.exists())
    											    {
    													boolean deleted = tem_file.delete();
    												}
    												    
    											    File file = new File(extStorage, "Edit_tem.JPEG");
    											    
    											    try {
    											     OutputStream outStream = new FileOutputStream(file);
    											     bmp2.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
    											     outStream.flush();
    											        outStream.close();
    											        /*Toast.makeText(Sharpen.this, 
    											          extStorage+"/Edit_tem.JPEG", 
    											          Toast.LENGTH_LONG).show();*/
    											    } catch (FileNotFoundException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    } catch (IOException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    }
    											 imgView1.setImageBitmap(bmp2); 
    											 ProgressBar_Sharpen.setVisibility(View.GONE);
    										 }
    						 });
    						 }
    					});
    				    threadSharpen.start();
    						break;
    					case 5:
    						ProgressBar_Sharpen.setVisibility(View.VISIBLE);
    						threadSharpen = new Thread(new Runnable(){
    							 public void run(){

    		    						bmp2 = sharpenOld(bmp2, (float) f[4]);
    									 
    									 handlerSharpen.post(new Runnable(){
    										 public void run(){
    											 File tem_file = new File(extStorage, "Edit_tem.JPEG");
    												if(tem_file.exists())
    											    {
    													boolean deleted = tem_file.delete();
    												}
    												    
    											    File file = new File(extStorage, "Edit_tem.JPEG");
    											    
    											    try {
    											     OutputStream outStream = new FileOutputStream(file);
    											     bmp2.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
    											     outStream.flush();
    											        outStream.close();
    											        /*Toast.makeText(Sharpen.this, 
    											          extStorage+"/Edit_tem.JPEG", 
    											          Toast.LENGTH_LONG).show();*/
    											    } catch (FileNotFoundException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    } catch (IOException e) {
    											     // TODO Auto-generated catch block
    											     e.printStackTrace();
    											     Toast.makeText(Sharpen.this, 
    											          e.toString(), 
    											          Toast.LENGTH_LONG).show();
    											    }
    											 imgView1.setImageBitmap(bmp2); 
    											 ProgressBar_Sharpen.setVisibility(View.GONE);
    										 }
    						 });
    						 }
    					});
    				    threadSharpen.start();
    						break;
    					}
    				}
    			}
				
                
                /*
    			else if(temp_progress < progress) {
    				for(int i=temp_progress+1; i<=progress; i++) {
    					switch(i) {
    					case 1:
    						bmp2 = sharpen(bmp2, mask, (float) f[0]);
    						break;
    					case 2:
    						bmp2 = sharpen(bmp2, mask, (float) f[1]);
    						break;
    					case 3:
    						bmp2 = sharpen(bmp2, mask, (float) f[2]);
    						break;
    					case 4:
    						bmp2 = sharpen(bmp2, mask, (float) f[3]);
    						break;
    					case 5:
    						bmp2 = sharpen(bmp2, mask, (float) f[4]);
    						break;
    					}
    				}
    				temp_progress = progress;
				}
    			else {
    				bmp2 = bmp1;
    				temp_progress = progress;
    				for(int i=1; i<=progress; i++) {
    					switch(i) {
    					case 1:
    						bmp2 = sharpen(bmp2, mask, (float) f[0]);
    						break;
    					case 2:
    						bmp2 = sharpen(bmp2, mask, (float) f[1]);
    						break;
    					case 3:
    						bmp2 = sharpen(bmp2, mask, (float) f[2]);
    						break;
    					case 4:
    						bmp2 = sharpen(bmp2, mask, (float) f[3]);
    						break;
    					case 5:
    						bmp2 = sharpen(bmp2, mask, (float) f[4]);
    						break;
    					}
    				}
    			}
				imgView1.setImageBitmap(bmp2); 
				*/
			}
    		
    	});
    }

    
	private Bitmap sharpen (Bitmap bmp, Bitmap mask, float a) {
    	int width = bmp.getWidth();
    	int height = bmp.getHeight();
    	
    	Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    	
    	int bmpR = 0;
    	int bmpG = 0;
    	int bmpB = 0;
    	int maskR = 0;
    	int maskG = 0;
    	int maskB = 0;
    	int newR = 0;
    	int newG = 0;
    	int newB = 0;

    	int bmpPixColor = 0;
    	int maskPixColor = 0;

    	float alpha = a;
    	int[] bmpPixels = new int[width * height];
    	bmp.getPixels(bmpPixels, 0, width, 0, 0, width, height);
    	int[] maskPixels = new int[width * height];
    	mask.getPixels(maskPixels, 0, width, 0, 0, width, height);
    	int[] newPixels = new int[width * height];

    	for(int i=0; i < height; i++) {
    		for(int j=0; j< width; j++) {
    			bmpPixColor = bmpPixels[i * width + j];
    			bmpR = Color.red(bmpPixColor);
    			bmpG = Color.green(bmpPixColor);
    			bmpB = Color.blue(bmpPixColor);

    			maskPixColor = maskPixels[i * width + j];
    			maskR = Color.red(maskPixColor);
    			maskG = Color.green(maskPixColor);
    			maskB = Color.blue(maskPixColor);
    			
    			newR = (int) (bmpR * 0.9 + maskR * alpha);
    			newG = (int) (bmpG * 0.9 + maskG * alpha);
    			newB = (int) (bmpB * 0.9 + maskB * alpha);

    			newR = Math.min(255, Math.max(0, newR));
    			newG = Math.min(255, Math.max(0, newG));
    			newB = Math.min(255, Math.max(0, newB));

    			newPixels[i * width + j] = Color.argb(255, newR, newG, newB);
    			
    			bmpR = 0;
    			bmpG = 0;
    			bmpB = 0;
    			maskR = 0;
    			maskB = 0;
    			maskG = 0;
    			newR = 0;
    			newG = 0;
    			newB = 0;
    		}
    	}

    	newBitmap.setPixels(newPixels, 0, width, 0, 0, width, height);
    	return newBitmap;
    }

	private Bitmap getMask (Bitmap bmp) {
		int[] gaosi = new int[] {-1, -4, -7, -4, -1,
				-4, -16, -26, -16, -4,
				-7, -26, 232, -26, -7,
				-4, -16, -26, -16, -4,
				-1, -4, -7, -4, -1 };


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

		int idx = 0;
		float alpha = (float) 0.8;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);

		for(int i=2; i < height-2; i++) {
			for(int j=2; j< width-2; j++) {
				idx = 0;
				for(int m=i-2; m<i+3; m++) {
					for(int n=j-2; n<j+3; n++) {
						pixColor = pixels[m * width + n];
						pixR = Color.red(pixColor);
						pixG = Color.green(pixColor);
						pixB = Color.blue(pixColor);

						newR = newR + (int) (pixR * gaosi[idx] * alpha);
						newG = newG + (int) (pixG * gaosi[idx] * alpha);
						newB = newB + (int) (pixB * gaosi[idx] * alpha);
						idx++;
					}
				}

				newR /= 273;
				newG /= 273;
				newB /= 273;

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
	
    private Bitmap sharpenSmall (Bitmap bmp, float a) {
    	int[] gaosi = new int[] {-1, -1, -1,
    							 -1, 9, -1,
    							 -1, -1, -1};

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

    	int idx = 0;
    	float alpha = a;
    	int[] pixels = new int[width * height];
    	bmp.getPixels(pixels, 0, width, 0, 0, width, height);

    	for(int i=1; i < height-1; i++) {
    		for(int j=1; j< width-1; j++) {
    			idx = 0;
    			for(int m=i-1; m<i+2; m++) {
    				for(int n=j-1; n<j+2; n++) {
    					pixColor = pixels[m * width + n];
    					pixR = Color.red(pixColor);
    					pixG = Color.green(pixColor);
    					pixB = Color.blue(pixColor);

    					newR = newR + (int) (pixR * gaosi[idx] * alpha);
    					newG = newG + (int) (pixG * gaosi[idx] * alpha);
    					newB = newB + (int) (pixB * gaosi[idx] * alpha);
    					idx++;
    				}
    				}

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
    
    private Bitmap sharpenOld(Bitmap bmp, float a) {

    	int[] gaosi = new int[] {-1, -4, -7, -4, -1,
    							-4, -16, -26, -16, -4,
    							-7, -26, 505, -26, -7,
    							-4, -16, -26, -16, -4,
    							-1, -4, -7, -4, -1 };


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

    	int idx = 0;
    	float alpha = a;
    	int[] pixels = new int[width * height];
    	bmp.getPixels(pixels, 0, width, 0, 0, width, height);

    	for(int i=2; i < height-2; i++) {
    		for(int j=2; j< width-2; j++) {
    			idx = 0;
    			for(int m=i-2; m<i+3; m++) {
    				for(int n=j-2; n<j+3; n++) {
    					pixColor = pixels[m * width + n];
    					pixR = Color.red(pixColor);
    					pixG = Color.green(pixColor);
    					pixB = Color.blue(pixColor);

    					newR = newR + (int) (pixR * gaosi[idx] * alpha);
    					newG = newG + (int) (pixG * gaosi[idx] * alpha);
    					newB = newB + (int) (pixB * gaosi[idx] * alpha);
    					idx++;
    				}
    			}

    			newR /= 273;
    			newG /= 273;
    			newB /= 273;

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
