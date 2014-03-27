package com.pictoaster.www.retouch.edit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.pictoaster.www.R;
import com.pictoaster.www.retouch.Retouch;
import com.pictoaster.www.retouch.blur.Blur;
import com.pictoaster.www.retouch.effect.Fashion;

public class Mirror extends Activity {
	  private ImageButton mirrorH;
	  private ImageButton mirrorV;
	  private ImageView imageView;
	  private Bitmap modified;
	  private Bitmap mySourceBmp;
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
		
		setContentView(R.layout.retouch_edit_mirror);
        
        mirrorH =(ImageButton) findViewById(R.id.button1);
        mirrorV =(ImageButton) findViewById(R.id.button2);
	
	    imageView = (ImageView) findViewById(R.id.imgResult);
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
	    
	    final Bitmap mySourceBmp = BitmapFactory.decodeFile(path);
	    modified = mySourceBmp;
	    
  
	    //final Bitmap mySourceBmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
	    final int widthOrig = mySourceBmp.getWidth(); 
	    final int heightOrig = mySourceBmp.getHeight();
	    
	    imageView.setImageBitmap(mySourceBmp);
	              
	    mirrorH.setOnClickListener(new Button.OnClickListener()
	    {
	      public void onClick(View v)
	      {
	    	  Bundle fieldresults = Mirror.this.getIntent().getExtras();
	  	    
	          String path;
	          
	          File tem_file1 = new File(extStorage, "Edit_tem.JPEG");
	  	    if(tem_file1.exists())
	  	    {
	  	    	path = extStorage + "/Edit_tem.JPEG";
	  	    }
	  	    else
	  	    {
	  	    	path = fieldresults.getString("bm");
	  	    }
	  	    
	  	    final Bitmap mySourceBmp = BitmapFactory.decodeFile(path);
	  	    
	    	Matrix matrix = new Matrix(); 
	        float[] floats = null;  
	        floats = new float[] { -1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f }; 
	        if (floats != null) {         
                  matrix.setValues(floats);  
	        } 
	    	
	        Bitmap resizedBitmap = Bitmap.createBitmap(mySourceBmp, 0, 0, widthOrig, heightOrig, matrix, true);
	        BitmapDrawable myNewBitmapDrawable =new BitmapDrawable(resizedBitmap);
	        //imageView.setImageDrawable(myNewBitmapDrawable);
	        modified = myNewBitmapDrawable.getBitmap();
	        imageView.setImageBitmap(modified);
	        
	        File tem_file = new File(extStorage, "Edit_tem.JPEG");
			if(tem_file.exists())
		    {
				boolean deleted = tem_file.delete();
			}
			    
		    File file = new File(extStorage, "Edit_tem.JPEG");
		    
		    try {
		     OutputStream outStream = new FileOutputStream(file);
		     modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
		     outStream.flush();
		        outStream.close();
		        /*Toast.makeText(Mirror.this, 
		          extStorage+"/Edit_tem.JPEG", 
		          Toast.LENGTH_LONG).show();*/
		    } catch (FileNotFoundException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		     Toast.makeText(Mirror.this, 
		          e.toString(), 
		          Toast.LENGTH_LONG).show();
		    } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		     Toast.makeText(Mirror.this, 
		          e.toString(), 
		          Toast.LENGTH_LONG).show();
		    }
		    
		    //finish();
	      }
	    });
	    

	    mirrorV.setOnClickListener(new Button.OnClickListener()
	    {
	      public void onClick(View v)
	      {
	    	  Bundle fieldresults = Mirror.this.getIntent().getExtras();
	  	    
	          String path;
	          
	          File tem_file1 = new File(extStorage, "Edit_tem.JPEG");
	  	    if(tem_file1.exists())
	  	    {
	  	    	path = extStorage + "/Edit_tem.JPEG";
	  	    }
	  	    else
	  	    {
	  	    	path = fieldresults.getString("bm");
	  	    }
	  	    
	  	    final Bitmap mySourceBmp = BitmapFactory.decodeFile(path);
	    	Matrix matrix = new Matrix(); 
	        float[] floats = null;  
	        floats = new float[] { 1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f };
	        if (floats != null) {         
                  matrix.setValues(floats);  
	        } 
	    	
	        Bitmap resizedBitmap = Bitmap.createBitmap(mySourceBmp, 0, 0, widthOrig, heightOrig, matrix, true);
	        BitmapDrawable myNewBitmapDrawable =new BitmapDrawable(resizedBitmap);
	        //imageView.setImageDrawable(myNewBitmapDrawable);
	        modified = myNewBitmapDrawable.getBitmap();
	        imageView.setImageBitmap(modified);
	        
			File tem_file = new File(extStorage, "Edit_tem.JPEG");
			if(tem_file.exists())
		    {
				boolean deleted = tem_file.delete();
			}
			    
		    File file = new File(extStorage, "Edit_tem.JPEG");
		    
		    try {
		     OutputStream outStream = new FileOutputStream(file);
		     modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
		     outStream.flush();
		        outStream.close();
		        /*Toast.makeText(Mirror.this, 
		          extStorage+"/Edit_tem.JPEG", 
		          Toast.LENGTH_LONG).show();*/
		    } catch (FileNotFoundException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		     Toast.makeText(Mirror.this, 
		          e.toString(), 
		          Toast.LENGTH_LONG).show();
		    } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		     Toast.makeText(Mirror.this, 
		          e.toString(), 
		          Toast.LENGTH_LONG).show();
		    }
		    //finish();
	      }
	    });
	   
	    
		ImageView ok = (ImageView) findViewById(R.id.mirror_check);
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
			     modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
			     outStream.flush();
			        outStream.close();
			        /*Toast.makeText(Mirror.this, 
			          extStorage+"/Retouch_tem.JPEG", 
			          Toast.LENGTH_LONG).show();*/
			    } catch (FileNotFoundException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Mirror.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Mirror.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    }
			    
			    Intent intent = new Intent(Mirror.this, Retouch.class);
			    Bundle bundle = Mirror.this.getIntent().getExtras();
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
		
		ImageView cancel = (ImageView) findViewById(R.id.mirror_error);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File tem_file1 = new File(extStorage, "Edit_tem.JPEG");
				if(tem_file1.exists())
			    {
					boolean deleted = tem_file1.delete();
				}
				
				Intent intent = new Intent(Mirror.this, Retouch.class);
				Bundle bundle = Mirror.this.getIntent().getExtras();
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
}