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

public  class Join_h extends Activity {
	
	private Intent intent;
	private Bundle bundle;
	private Bitmap bm;
	
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.montage_join_h);
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

	    int h = 300;
	    float t = 1F*h/h1*w1 + 1F*h/h2*w2 + 1F*h/h3*w3;
	    if((int)t > 900)
            h = (int)(900/t*h);
            
        Matrix matrix1 = new Matrix(); 	    
        Matrix matrix2 = new Matrix();
        Matrix matrix3 = new Matrix();
        
        float sizeHeight = 1F*h/h1;        
        matrix1.postScale(sizeHeight, sizeHeight);
        bmp1 = Bitmap.createBitmap(bmp1, 0, 0, w1 ,h1 , matrix1, true);
        
        sizeHeight = 1F*h/h2;        
        matrix2.postScale(sizeHeight, sizeHeight);
        bmp2 = Bitmap.createBitmap(bmp2, 0, 0, w2 ,h2 , matrix2, true);
        
        sizeHeight = 1F*h/h3;           
        matrix3.postScale(sizeHeight, sizeHeight);
        bmp3 = Bitmap.createBitmap(bmp3, 0, 0, w3 ,h3 , matrix3, true);
         
        
        image1.setImageBitmap(bmp1);
        image2.setImageBitmap(bmp2);
        image3.setImageBitmap(bmp3);
        
		ImageView ok = (ImageView) findViewById(R.id.check);
		ok.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				LinearLayout view = (LinearLayout)findViewById(R.id.result);
				view.setDrawingCacheEnabled(true);
				view.buildDrawingCache();
				
				bm = view.getDrawingCache();				
		        				
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
			     Toast.makeText(Join_h.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Join_h.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    }
			    
			    intent = new Intent(Join_h.this, Montage.class);
			    intent.putExtras(bundle);
				
				startActivity(intent);
				System.exit(0);
			}
		});
		
		ImageView cancel = (ImageView) findViewById(R.id.error);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(Join_h.this, Montage.class);
				intent.putExtras(bundle);
	
				startActivity(intent);
				System.exit(0);
			}
		});		
    }

}
