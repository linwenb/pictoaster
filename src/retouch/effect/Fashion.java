package com.pictoaster.www.retouch.effect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.pictoaster.www.R;
import com.pictoaster.www.retouch.Retouch;
import com.pictoaster.www.retouch.edit.Sharpen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Fashion extends Activity{
	private ProgressBar ProgressBar_Fashion;
	private Bitmap mBitmap;
	private ImageView mImageView;
	private Bitmap modified;
	private boolean operated = false;
	Thread threadFashion;
	Handler handlerFashion = new Handler();
	String extStorage = Environment.getExternalStorageDirectory().toString();
	
	 @Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
		}


		 

		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
		setContentView(R.layout.retouch_effect_fashion);
		
    
        Bundle fieldresults = this.getIntent().getExtras();
		String path;
        
        File tem_file = new File(extStorage, "Effect_tem.JPEG");
	    if(tem_file.exists())
	    {
	    	path = extStorage + "/Effect_tem.JPEG";
	    }
	    else
	    {
	    	path = fieldresults.getString("bm");
	    }
	    mBitmap= BitmapFactory.decodeFile(path);
	    mImageView = (ImageView) findViewById(R.id.display);
        mImageView.setImageBitmap(mBitmap);
        modified = mBitmap;
        ProgressBar_Fashion = (ProgressBar) findViewById(R.id.fashionBar);
        
        //get the resourse
	    path = fieldresults.getString("bm");
        mBitmap= BitmapFactory.decodeFile(path);
		
		
		//set the button to get the film effect
		ImageView bw = (ImageView) findViewById(R.id.black_white);
		bw.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProgressBar_Fashion.setVisibility(View.VISIBLE);
				threadFashion = new Thread(new Runnable(){
					 public void run(){
						    operated = true;
							modified = getBlackWhite(mBitmap);
						 
							 handlerFashion.post(new Runnable(){
								 public void run(){
									 
									 File tem_file1 = new File(extStorage, "Effect_tem.JPEG");
										if(tem_file1.exists())
										{
											boolean deleted = tem_file1.delete();
										}
									    
										File file = new File(extStorage, "Effect_tem.JPEG");
								    
										try {
											OutputStream outStream = new FileOutputStream(file);
											modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
											outStream.flush();
											outStream.close();
											/*Toast.makeText(Fashion.this, 
								          	extStorage+"/Effect_tem.JPEG", 
								          	Toast.LENGTH_LONG).show();*/
										} catch (FileNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											Toast.makeText(Fashion.this, 
													e.toString(), 
													Toast.LENGTH_LONG).show();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											Toast.makeText(Fashion.this, 
													e.toString(), 
													Toast.LENGTH_LONG).show();
										}
                                     mImageView.setImageBitmap(modified);
									 ProgressBar_Fashion.setVisibility(View.GONE);
								 }
				 });
				 }
			});
		    threadFashion.start();
				
				
			}
		});
		
		ImageView film = (ImageView) findViewById(R.id.film);
		film.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProgressBar_Fashion.setVisibility(View.VISIBLE);
				threadFashion = new Thread(new Runnable(){
					 public void run(){
						    operated = true;
							modified = getFilm(mBitmap);
						 
							 handlerFashion.post(new Runnable(){
								 public void run(){
									 File tem_file1 = new File(extStorage, "Effect_tem.JPEG");
										if(tem_file1.exists())
										{
											boolean deleted = tem_file1.delete();
										}
									    
										File file = new File(extStorage, "Effect_tem.JPEG");
								    
										try {
											OutputStream outStream = new FileOutputStream(file);
											modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
											outStream.flush();
											outStream.close();
											/*Toast.makeText(Fashion.this, 
								          	extStorage+"/Effect_tem.JPEG", 
								          	Toast.LENGTH_LONG).show();*/
										} catch (FileNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											Toast.makeText(Fashion.this, 
													e.toString(), 
													Toast.LENGTH_LONG).show();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											Toast.makeText(Fashion.this, 
													e.toString(), 
													Toast.LENGTH_LONG).show();
										}
									 
                                     mImageView.setImageBitmap(modified);
									 ProgressBar_Fashion.setVisibility(View.GONE);
								 }
				 });
				 }
			});
		    threadFashion.start();
				
				
			}
		});
		
		ImageView reminiscence = (ImageView) findViewById(R.id.reminiscence);
		reminiscence.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ProgressBar_Fashion.setVisibility(View.VISIBLE);
				threadFashion = new Thread(new Runnable(){
					 public void run(){
						    operated = true;
						    modified = getReminiscence(mBitmap);
						 
							 handlerFashion.post(new Runnable(){
								 public void run(){
									 File tem_file1 = new File(extStorage, "Effect_tem.JPEG");
										if(tem_file1.exists())
										{
											boolean deleted = tem_file1.delete();
										}
									    
										File file = new File(extStorage, "Effect_tem.JPEG");
								    
										try {
											OutputStream outStream = new FileOutputStream(file);
											modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
											outStream.flush();
											outStream.close();
											/*Toast.makeText(Fashion.this, 
								          	extStorage+"/Effect_tem.JPEG", 
								          	Toast.LENGTH_LONG).show();*/
										} catch (FileNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											Toast.makeText(Fashion.this, 
													e.toString(), 
													Toast.LENGTH_LONG).show();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											Toast.makeText(Fashion.this, 
													e.toString(), 
													Toast.LENGTH_LONG).show();
										}
                                     mImageView.setImageBitmap(modified);
									 ProgressBar_Fashion.setVisibility(View.GONE);
								 }
				 });
				 }
			});
		    threadFashion.start();
				
				
				
			}
		});
		
			
		
		ImageView ok = (ImageView) findViewById(R.id.fashion_check);
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
			        /*Toast.makeText(Fashion.this, 
			          extStorage+"/Retouch_tem.JPEG", 
			          Toast.LENGTH_LONG).show();*/
			    } catch (FileNotFoundException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Fashion.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Fashion.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    }
			    
			    Intent intent = new Intent(Fashion.this, Retouch.class);
			    Bundle bundle = Fashion.this.getIntent().getExtras();
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
		
		ImageView cancel = (ImageView) findViewById(R.id.fashion_error);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File tem_file1 = new File(extStorage, "Effect_tem.JPEG");
				if(tem_file1.exists())
			    {
					boolean deleted = tem_file1.delete();
				}
				
				Intent intent = new Intent(Fashion.this, Retouch.class);
				Bundle bundle = Fashion.this.getIntent().getExtras();
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
	
	private Bitmap getBlackWhite(Bitmap bmp)
	{
		//get the width and height of the image, and initialize a new bitmap of same size
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		
		//initialize the R G B pixel as integer and pixel matrix as an int array
		//get the pixel array and store it in the int array which we just initialize
		int pixelR = 0;
		int pixelG = 0;
		int pixelB = 0;
		
		int [] pixels = new int[width*height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		int pos = 0;
		final int MAX_VALUE = 255;
		
		for(int i = 0; i < height; i ++)
		{
			for(int j = 0; j < width; j ++)
			{
				pos = i*width + j;
				
				//get the value of RGB in the original pixel
				pixelR = Color.red(pixels[pos]);
				pixelG = Color.green(pixels[pos]);
				pixelB = Color.blue(pixels[pos]);
				
				// Gray = R*0.299 + G*0.587 + B*0.114
				//set the value in RGB
				pixelR = (int)(0.299 * pixelR + 0.587 * pixelG + 0.114 * pixelB);
				pixelG = pixelB = pixelR;
				
				//assure the value in RGB >= 255 and <= 0
				pixelR = Math.min(MAX_VALUE, Math.max(0, pixelR));
				pixelG = Math.min(MAX_VALUE, Math.max(0, pixelG));
				pixelB = Math.min(MAX_VALUE, Math.max(0, pixelB));
				
				//set the pixel back to the array
				pixels[pos] = Color.argb(MAX_VALUE, pixelR, pixelG, pixelB);
			}
		}
		
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}
	
	private Bitmap getFilm(Bitmap bmp)
	{
		//get the width and height of the image, and initialize a new bitmap of same size
		final int MAX_VALUE = 255;
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		
		//initialize the R G B pixel as integer and pixel matrix as an int array
		//get the pixel array and store it in the int array which we just initialize
		int pixelR = 0;
		int pixelG = 0;
		int pixelB = 0;
		int [] pixels = new int[width*height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		int pos = 0;
		
		for(int i = 0; i < height; i ++)
		{
			for(int j = 0; j < width; j ++)
			{
				pos = i*width + j;
				
				//get the value of RGB in the original pixel
				pixelR = Color.red(pixels[pos]);
				pixelG = Color.green(pixels[pos]);
				pixelB = Color.blue(pixels[pos]);
				
				//reverse the value in RGB
				pixelR = MAX_VALUE - pixelR;
				pixelG = MAX_VALUE - pixelG;
				pixelB = MAX_VALUE - pixelB;
				
				//assure the value in RGB >= 255 and <= 0
				pixelR = Math.min(MAX_VALUE, Math.max(0, pixelR));
				pixelG = Math.min(MAX_VALUE, Math.max(0, pixelG));
				pixelB = Math.min(MAX_VALUE, Math.max(0, pixelB));
				
				//set the pixel back to the array
				pixels[pos] = Color.argb(MAX_VALUE, pixelR, pixelG, pixelB);
			}
		}
		
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}
	
	private Bitmap getReminiscence(Bitmap bmp)
	{
		//get the width and height of the image, and initialize a new bitmap of same size
		final int MAX_VALUE = 255;
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		
		//initialize the R G B pixel as integer and pixel matrix as an int array
		//get the pixel array and store it in the int array which we just initialize
		int pixelR = 0;
		int pixelG = 0;
		int pixelB = 0;
		
		int newR = 0;
		int newG = 0;
		int newB = 0;
		
		int [] pixels = new int[width*height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		int pos = 0;
		
		for(int i = 0; i < height; i ++)
		{
			for(int j = 0; j < width; j ++)
			{
				pos = i*width + j;
				
				//get the value of RGB in the original pixel
				pixelR = Color.red(pixels[pos]);
				pixelG = Color.green(pixels[pos]);
				pixelB = Color.blue(pixels[pos]);
				
				//reverse the value in RGB
				newR = (int)(0.393 * pixelR + 0.769 * pixelG + 0.189 * pixelB);
				newG = (int)(0.349 * pixelR + 0.686 * pixelG + 0.168 * pixelB);
				newB = (int)(0.272 * pixelR + 0.534 * pixelG + 0.131 * pixelB);
				
				//assure the value in RGB >= 255 and <= 0
				newR = Math.min(MAX_VALUE, Math.max(0, newR));
				newG = Math.min(MAX_VALUE, Math.max(0, newG));
				newB = Math.min(MAX_VALUE, Math.max(0, newB));
				
				//set the pixel back to the array
				pixels[pos] = Color.argb(MAX_VALUE, newR, newG, newB);
			}
		}
		
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}



}