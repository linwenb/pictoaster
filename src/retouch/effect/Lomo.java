package com.pictoaster.www.retouch.effect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.pictoaster.www.R;
import com.pictoaster.www.retouch.Retouch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Lomo extends Activity implements View.OnClickListener{
	private ProgressBar ProgressBar_Lomo;
	private Bitmap originalView;
	private int which;
	private ImageView display;
	private Bitmap modified;
	private boolean operated = false;
	Thread threadLomo;
	Handler handlerLomo = new Handler();
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
			
			// get the view picture
			//Bitmap view;
			setContentView(R.layout.retouch_effect_lomo);
			Bundle fieldresults = this.getIntent().getExtras();
			String path;
			//path = fieldresults.getString("bm");
	        
	        File tem_file = new File(extStorage, "Effect_tem.JPEG");
		    if(tem_file.exists())
		    {
		    	path = extStorage + "/Effect_tem.JPEG";
		    }
		    else
		    {
		    	path = fieldresults.getString("bm");
		    }
			originalView= BitmapFactory.decodeFile(path);
		    display = (ImageView) findViewById(R.id.display);
	        display.setImageBitmap(originalView);
	        modified = originalView;
	        
	        //get the resourse
	        path = fieldresults.getString("bm");
	        originalView= BitmapFactory.decodeFile(path);
        
        //dio = (ProgressBar) findViewById(R.id.pr);
		//Bundle fieldresults = this.getIntent().getExtras();
	    //originalView = BitmapFactory.decodeFile(path); 
	    
	        ProgressBar_Lomo = (ProgressBar) findViewById(R.id.lomoBar);
		
        ImageView image1 = (ImageView) findViewById(R.id.image1);
        ImageView image2 = (ImageView) findViewById(R.id.image2);
        ImageView image3 = (ImageView) findViewById(R.id.image3);
        ImageView image4 = (ImageView) findViewById(R.id.image4);
        ImageView image5 = (ImageView) findViewById(R.id.image5);
        ImageView image6 = (ImageView) findViewById(R.id.image6);
        
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);
        
        ImageView ok = (ImageView) findViewById(R.id.lomo_check);
		ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String extStorage = Environment.getExternalStorageDirectory().toString();
				
				File tem_file1 = new File(extStorage, "Effect_tem.JPEG");
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
			     modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
			     outStream.flush();
			        outStream.close();
			        /*Toast.makeText(Lomo.this, 
			          extStorage+"/Retouch_tem.JPEG", 
			          Toast.LENGTH_LONG).show();*/
			    } catch (FileNotFoundException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Lomo.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Lomo.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    }
			    
			    Intent intent = new Intent(Lomo.this, Retouch.class);
			    Bundle bundle = Lomo.this.getIntent().getExtras();
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
		
		ImageView cancel = (ImageView) findViewById(R.id.lomo_error);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File tem_file1 = new File(extStorage, "Effect_tem.JPEG");
				if(tem_file1.exists())
			    {
					boolean deleted = tem_file1.delete();
				}
				
				Intent intent = new Intent(Lomo.this, Retouch.class);
				Bundle bundle = Lomo.this.getIntent().getExtras();
			    intent.putExtras(bundle);
			    
			    if(!modified.isRecycled() ){
			        modified.recycle(); //回收图片所占的内存
			         System.gc(); //提醒系统及时回收
			     }
			    
				startActivity(intent);
				System.gc();
				System.exit(0);
			}
		});
        
    }
		
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final Bitmap mBitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.bw);
		//Bitmap mBitmap2;
		
		/*if(modified != null && !modified.isRecycled()){   
			modified.recycle(); 
			modified = null;
		}*/
		
		
		//ProgressBar_Lomo = (ProgressBar) findViewById(R.id.lomoBar);
		switch(v.getId())
		{		
		case R.id.image1:
			ProgressBar_Lomo.setVisibility(View.VISIBLE);
			//dio = ProgressDialog.show(this,"",  "loading" , true, true);
			threadLomo = new Thread(new Runnable(){
				 public void run(){
					 
						 which = 1;
					     //mBitmap2 = overlay(mBitmap1);
						 modified = overlay( overlay(mBitmap1));
						 operated = true;
						 
						 handlerLomo.post(new Runnable(){
							 public void run(){
								 operated = false;
									File tem_file = new File(extStorage, "Effect_tem.JPEG");
									if(tem_file.exists())
									{
										boolean deleted = tem_file.delete();
									}
								    
									File file = new File(extStorage, "Effect_tem.JPEG");
							    
									try {
										OutputStream outStream = new FileOutputStream(file);
										modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
										outStream.flush();
										outStream.close();
										/*Toast.makeText(Lomo.this, 
							          	extStorage+"/Effect_tem.JPEG", 
							          	Toast.LENGTH_LONG).show();*/
									} catch (FileNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										Toast.makeText(Lomo.this, 
												e.toString(), 
												Toast.LENGTH_LONG).show();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										Toast.makeText(Lomo.this, 
												e.toString(), 
												Toast.LENGTH_LONG).show();
									}
								 display.setImageBitmap(modified);	
								 ProgressBar_Lomo.setVisibility(View.GONE);
							 }
			 });
			 }
		});
	    threadLomo.start();
			break;
			
		case R.id.image2:
			ProgressBar_Lomo.setVisibility(View.VISIBLE);
			//dio = ProgressDialog.show(this,"",  "loading" , true, true);
			threadLomo = new Thread(new Runnable(){
				 public void run(){
					 
						 which = 2;
					     //mBitmap2 = overlay(mBitmap1);
						 modified = overlay( overlay(mBitmap1));
						 operated = true;
						 
						 handlerLomo.post(new Runnable(){
							 public void run(){
								 //operated = false;
									File tem_file = new File(extStorage, "Effect_tem.JPEG");
									if(tem_file.exists())
									{
										boolean deleted = tem_file.delete();
									}
								    
									File file = new File(extStorage, "Effect_tem.JPEG");
							    
									try {
										OutputStream outStream = new FileOutputStream(file);
										modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
										outStream.flush();
										outStream.close();
										/*Toast.makeText(Lomo.this, 
							          	extStorage+"/Effect_tem.JPEG", 
							          	Toast.LENGTH_LONG).show();*/
									} catch (FileNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										Toast.makeText(Lomo.this, 
												e.toString(), 
												Toast.LENGTH_LONG).show();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										Toast.makeText(Lomo.this, 
												e.toString(), 
												Toast.LENGTH_LONG).show();
									}
								 display.setImageBitmap(modified);	
								 ProgressBar_Lomo.setVisibility(View.GONE);
							 }
			 });
			 }
		});
	    threadLomo.start();
			break;
		case R.id.image3:
			ProgressBar_Lomo.setVisibility(View.VISIBLE);
			//dio = ProgressDialog.show(this,"",  "loading" , true, true);
			threadLomo = new Thread(new Runnable(){
				 public void run(){
					 
						 which = 3;
					     //mBitmap2 = overlay(mBitmap1);
						 modified = overlay( overlay(mBitmap1));
						 operated = true;
						 
						 handlerLomo.post(new Runnable(){
							 public void run(){
								 //operated = false;
									File tem_file = new File(extStorage, "Effect_tem.JPEG");
									if(tem_file.exists())
									{
										boolean deleted = tem_file.delete();
									}
								    
									File file = new File(extStorage, "Effect_tem.JPEG");
							    
									try {
										OutputStream outStream = new FileOutputStream(file);
										modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
										outStream.flush();
										outStream.close();
										/*Toast.makeText(Lomo.this, 
							          	extStorage+"/Effect_tem.JPEG", 
							          	Toast.LENGTH_LONG).show();*/
									} catch (FileNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										Toast.makeText(Lomo.this, 
												e.toString(), 
												Toast.LENGTH_LONG).show();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										Toast.makeText(Lomo.this, 
												e.toString(), 
												Toast.LENGTH_LONG).show();
									}
									
								 display.setImageBitmap(modified);	
								 ProgressBar_Lomo.setVisibility(View.GONE);
							 }
			 });
			 }
		});
	    threadLomo.start();		
			break;
		case R.id.image4:
			ProgressBar_Lomo.setVisibility(View.VISIBLE);
			//dio = ProgressDialog.show(this,"",  "loading" , true, true);
			threadLomo = new Thread(new Runnable(){
				 public void run(){
					 
						 which = 4;
					     //mBitmap2 = overlay(mBitmap1);
						 modified = overlay( overlay(mBitmap1));
						 operated = true;
						 
						 handlerLomo.post(new Runnable(){
							 public void run(){
								 //operated = false;
									File tem_file = new File(extStorage, "Effect_tem.JPEG");
									if(tem_file.exists())
									{
										boolean deleted = tem_file.delete();
									}
								    
									File file = new File(extStorage, "Effect_tem.JPEG");
							    
									try {
										OutputStream outStream = new FileOutputStream(file);
										modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
										outStream.flush();
										outStream.close();
										/*Toast.makeText(Lomo.this, 
							          	extStorage+"/Effect_tem.JPEG", 
							          	Toast.LENGTH_LONG).show();*/
									} catch (FileNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										Toast.makeText(Lomo.this, 
												e.toString(), 
												Toast.LENGTH_LONG).show();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										Toast.makeText(Lomo.this, 
												e.toString(), 
												Toast.LENGTH_LONG).show();
									}
								 display.setImageBitmap(modified);	
								 ProgressBar_Lomo.setVisibility(View.GONE);
							 }
			 });
			 }
		});
	    threadLomo.start();			
			break;
		case R.id.image5:
			ProgressBar_Lomo.setVisibility(View.VISIBLE);
			//dio = ProgressDialog.show(this,"",  "loading" , true, true);
			threadLomo = new Thread(new Runnable(){
				 public void run(){
					 
						 which = 5;
					     //mBitmap2 = overlay(mBitmap1);
						 modified = overlay( overlay(mBitmap1));
						 operated = true;
						 
						 handlerLomo.post(new Runnable(){
							 public void run(){
								 //operated = false;
									File tem_file = new File(extStorage, "Effect_tem.JPEG");
									if(tem_file.exists())
									{
										boolean deleted = tem_file.delete();
									}
								    
									File file = new File(extStorage, "Effect_tem.JPEG");
							    
									try {
										OutputStream outStream = new FileOutputStream(file);
										modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
										outStream.flush();
										outStream.close();
										/*Toast.makeText(Lomo.this, 
							          	extStorage+"/Effect_tem.JPEG", 
							          	Toast.LENGTH_LONG).show();*/
									} catch (FileNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										Toast.makeText(Lomo.this, 
												e.toString(), 
												Toast.LENGTH_LONG).show();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										Toast.makeText(Lomo.this, 
												e.toString(), 
												Toast.LENGTH_LONG).show();
									}
								 display.setImageBitmap(modified);	
								 ProgressBar_Lomo.setVisibility(View.GONE);
							 }
			 });
			 }
		});
	    threadLomo.start();				
			break;
		case R.id.image6:
			ProgressBar_Lomo.setVisibility(View.VISIBLE);
			//dio = ProgressDialog.show(this,"",  "loading" , true, true);
			threadLomo = new Thread(new Runnable(){
				 public void run(){
					 
						 which = 6;
					     //mBitmap2 = overlay(mBitmap1);
						 modified = overlay( overlay(mBitmap1));
						 operated = true;
						 
						 handlerLomo.post(new Runnable(){
							 public void run(){
									File tem_file = new File(extStorage, "Effect_tem.JPEG");
									if(tem_file.exists())
									{
										boolean deleted = tem_file.delete();
									}
								    
									File file = new File(extStorage, "Effect_tem.JPEG");
							    
									try {
										OutputStream outStream = new FileOutputStream(file);
										modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
										outStream.flush();
										outStream.close();
										/*Toast.makeText(Lomo.this, 
							          	extStorage+"/Effect_tem.JPEG", 
							          	Toast.LENGTH_LONG).show();*/
									} catch (FileNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										Toast.makeText(Lomo.this, 
												e.toString(), 
												Toast.LENGTH_LONG).show();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										Toast.makeText(Lomo.this, 
												e.toString(), 
												Toast.LENGTH_LONG).show();
									}
								 display.setImageBitmap(modified);	
								 ProgressBar_Lomo.setVisibility(View.GONE);
							 }
			 });
			 }
		});
	    threadLomo.start();				
			break;
		}
		
	}
	
	
	

	private Bitmap overlay(Bitmap bmp)
	{		
		
		int width = originalView.getWidth();
		int height = originalView.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

		int w = bmp.getWidth();
		int h = bmp.getHeight();
		float scaleX = width * 1F / w;
		float scaleY = height * 1F / h;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleX, scaleY);
		Bitmap bmpCopy = Bitmap.createBitmap(bmp, 0, 0, w, h, matrix, true);

		int pixColor = 0;
		int layColor = 0;
		
		int pixR = 0;
		int pixG = 0;
		int pixB = 0;
		int pixA = 0;
		
		int newR = 0;
		int newG = 0;
		int newB = 0;
		int newA = 0;
		
		int layR = 0;
		int layG = 0;
		int layB = 0;
		int layA = 0;
		
		//final float alpha = 0.7F;
		
		int[] srcPixels = new int[width * height];
		int[] layPixels = new int[width * height];
		originalView.getPixels(srcPixels, 0, width, 0, 0, width, height);
		bmpCopy.getPixels(layPixels, 0, width, 0, 0, width, height);
		
		int pos = 0;
		for (int i = 0; i < height; i++)
		{
			for (int k = 0; k < width; k++)
			{
				pos = i * width + k;
				pixColor = srcPixels[pos];
				layColor = layPixels[pos];
				
				pixR = Color.red(pixColor);
				pixG = Color.green(pixColor);
				pixB = Color.blue(pixColor);
				pixA = Color.alpha(pixColor);
				
				layR = Color.red(layColor);
				layG = Color.green(layColor);
				layB = Color.blue(layColor);
				layA = Color.alpha(layColor);
				
				double a=0, b=0, c=0;
				double ref = 0.5;
				switch(which)
				{	
				case 1:
					a = 1.4;
					b = 1;
					c = 1;
					break;
					
				case 2:
					a = 1;
					b = 1.4;
					c = 1;
					break;
					
				case 3:
					a = 1;
					b = 1;
					c = 1.4;
					break;
					
				case 4:
					a = 1.4;
				    b = 1.4;
				    c = 1;
					break;
					
				case 5:
					a = 1.4;
                    b = 1;
					c = 1.4;
					break;
					
				case 6:
					a = 1;
					b = 1.4;
				    c = 1.4;
					break;
				}
			    
			    newR = (int) ((pixR * ref + layR * (1-ref)) * a);
				newG = (int) ((pixG * ref + layG * (1-ref)) * b);
				newB = (int) ((pixB * ref + layB * (1-ref)) * c);
				newA = (int) (pixA * ref + layA * (1-ref)) ;
		
				newR = Math.min(255, Math.max(0, newR));
				newG = Math.min(255, Math.max(0, newG));
				newB = Math.min(255, Math.max(0, newB));
				newA = Math.min(255, Math.max(0, layA));
				
				srcPixels[pos] = Color.argb(newA, newR, newG, newB);
			}
		}
		
		bitmap.setPixels(srcPixels, 0, width, 0, 0, width, height);
		
		return bitmap;
	}
}