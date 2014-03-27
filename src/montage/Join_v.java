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
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pictoaster.www.R;
import com.pictoaster.www.retouch.Retouch;
import com.pictoaster.www.retouch.effect.Lomo;

public class Join_v extends Activity {
	
	private Intent intent;
	private Bundle bundle;
	
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.montage_join_v);
        ImageView image1 = (ImageView) findViewById(R.id.image1);
        ImageView image2 = (ImageView) findViewById(R.id.image2);
        ImageView image3 = (ImageView) findViewById(R.id.image3);
        
        bundle = this.getIntent().getExtras();
        
	    String path1 = bundle.getString("bm1");
	    String path2 = bundle.getString("bm2");
	    String path3 = bundle.getString("bm3");
	    	    
	    int scale;
	    
	    BitmapFactory.Options options1 = new BitmapFactory.Options();   
        options1.inJustDecodeBounds = true;
        Bitmap bmp1 = BitmapFactory.decodeFile(path1, options1);                
	    for(scale = 2; options1.outWidth*options1.outHeight > 300000; scale = scale * 2)
	    {
	    	options1.inSampleSize = scale;
	    	bmp1 = BitmapFactory.decodeFile(path1, options1);
	    }
	    options1.inJustDecodeBounds = false;
	    bmp1 = BitmapFactory.decodeFile(path1, options1);
	    
	    
	    BitmapFactory.Options options2 = new BitmapFactory.Options();   
        options2.inJustDecodeBounds = true;
        Bitmap bmp2 = BitmapFactory.decodeFile(path2, options2);                
	    for(scale = 2; options2.outWidth*options2.outHeight > 300000; scale = scale * 2)
	    {
	    	options2.inSampleSize = scale;
	    	bmp2 = BitmapFactory.decodeFile(path2, options2);
	    }
	    options2.inJustDecodeBounds = false;
	    bmp2 = BitmapFactory.decodeFile(path2, options2);
	    
	    BitmapFactory.Options options3 = new BitmapFactory.Options();   
        options3.inJustDecodeBounds = true;
        Bitmap bmp3 = BitmapFactory.decodeFile(path3, options3);                
	    for(scale = 2; options3.outWidth*options3.outHeight > 300000; scale = scale * 2)
	    {
	    	options3.inSampleSize = scale;
	    	bmp3 = BitmapFactory.decodeFile(path3, options3);
	    }
	    options3.inJustDecodeBounds = false;
	    bmp3 = BitmapFactory.decodeFile(path3, options3);
	        
        int w1 = bmp1.getWidth();
        int w2 = bmp2.getWidth();
        int w3 = bmp3.getWidth();
        
	    int h1 = bmp1.getHeight();
	    int h2 = bmp2.getHeight();
	    int h3 = bmp3.getHeight();
	    
	    int w = 300;
	    
	    float t = 1F*w/w1*h1 + 1F*w/w2*h2 + 1F*w/w3*h3;
	    if((int)t > 900)
            w = (int)(900/t*w);
	    
        Matrix matrix1 = new Matrix(); 	    
        Matrix matrix2 = new Matrix();
        Matrix matrix3 = new Matrix();
        
        float sizeWidth = 1F*w/w1;      
        matrix1.postScale(sizeWidth, sizeWidth);
        bmp1 = Bitmap.createBitmap(bmp1, 0, 0, w1 ,h1 , matrix1, true);
        
        sizeWidth = 1F*w/w2;   
        matrix2.postScale(sizeWidth, sizeWidth);
        bmp2 = Bitmap.createBitmap(bmp2, 0, 0, w2 ,h2 , matrix2, true);
        
        sizeWidth = 1F*w/w3;   
        matrix3.postScale(sizeWidth, sizeWidth);
        bmp3 = Bitmap.createBitmap(bmp3, 0, 0, w3 ,h3 , matrix3, true);
        
        
        image1.setImageBitmap(bmp1);
        image2.setImageBitmap(bmp2);
        image3.setImageBitmap(bmp3);
        
        
		ImageView ok = (ImageView) findViewById(R.id.check);
		ok.setOnClickListener(new View.OnClickListener() {
			
		
			@Override
			public void onClick(View v) {
			
				LinearLayout view = (LinearLayout)findViewById(R.id.result);
				view.setDrawingCacheEnabled(true);
				view.buildDrawingCache();
				
				Bitmap bm = view.getDrawingCache();		     
			
				// TODO Auto-generated method stub
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
			     Toast.makeText(Join_v.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Join_v.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    }
			    
			    intent = new Intent(Join_v.this, Montage.class);
			    intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});
		
		ImageView cancel = (ImageView) findViewById(R.id.error);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(Join_v.this, Montage.class);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});
    }

}
