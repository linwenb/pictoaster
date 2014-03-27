package com.pictoaster.www.retouch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pictoaster.www.R;
import com.pictoaster.www.home;
import com.pictoaster.www.retouch.blur.Blur;
import com.pictoaster.www.retouch.frame.Frame;
import com.pictoaster.www.retouch.text.TextActivity;
import com.pictoaster.www.retouch.tone.Tone;

public class Retouch extends Activity implements View.OnClickListener{
   
	private Bitmap bm;
	private ImageView myImageView; 
	String path;
	int download;
	int scale=0;
	String extStorage = Environment.getExternalStorageDirectory().toString();
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retouch);
        
        //here get the path string from the parent activity
        Bundle fieldresults = this.getIntent().getExtras();
        download = fieldresults.getInt("download");
        
        //-------------------------------------------
        //big problem!!!!!!!
        String extStorage = Environment.getExternalStorageDirectory().toString();
        //String path;
        
        File tem_file = new File(extStorage, "Retouch_tem.JPEG");
	    if(tem_file.exists())
	    {
	    	path = extStorage + "/Retouch_tem.JPEG";
	 
	    }
	    else
	    {
	    	path = fieldresults.getString("bm");
	     }
	    
	    BitmapFactory.Options options = new BitmapFactory.Options();   
        options.inJustDecodeBounds = true;
        bm = BitmapFactory.decodeFile(path, options);
        
        int scale;
	    for(scale = 2; options.outWidth*options.outHeight > 800000; scale = scale * 2)
	    {
	    	//BitmapFactory.Options options1 = new BitmapFactory.Options();
	    	options.inSampleSize = scale;
	    	bm = BitmapFactory.decodeFile(path, options);
	    }
	    options.inJustDecodeBounds = false;
	    bm = BitmapFactory.decodeFile(path, options);
	    
	    File file = new File(extStorage, "Retouch_tem.JPEG");
	     
	    try {
	     OutputStream outStream = new FileOutputStream(file);
	     bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
	     outStream.flush();
	        outStream.close();
	        /*Toast.makeText(Tone.this, 
	          extStorage+"/Retouch_tem.JPEG", 
	          Toast.LENGTH_LONG).show();*/
	    } catch (FileNotFoundException e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	     Toast.makeText(Retouch.this, 
	          e.toString(), 
	          Toast.LENGTH_LONG).show();
	    } catch (IOException e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	     Toast.makeText(Retouch.this, 
	          e.toString(), 
	          Toast.LENGTH_LONG).show();
	    }
	    path = extStorage + "/Retouch_tem.JPEG";
	         
	    //show the path on screen
        myImageView = (ImageView) findViewById(R.id.imageView);  
        myImageView.setImageBitmap(bm); 
       
        
        //build the about button
        Button returnBTN = (Button) findViewById(R.id.btn_return);
        returnBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(0);// it calls onCreateDialog function bellow, 0 is argument for about
			}
		});
      
        //build the setting button
        Button saveBTN = (Button) findViewById(R.id.btn_save);
        saveBTN.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(1);//1 is the argument for setting
			}
		});
       
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
    }
    
    protected Dialog onCreateDialog(int id) {    
        switch(id) {
        case 0: // for return button content
        	return new AlertDialog.Builder(this)
        	.setMessage("Exit without save?")
        	.setCancelable(false)
        	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					String extStorage = Environment.getExternalStorageDirectory().toString();
					File tem_file = new File(extStorage, "Retouch_tem.JPEG");
				    if(tem_file.exists())
				    {
				    	boolean deleted = tem_file.delete();
				    }
				    
					Intent intent = new Intent(Retouch.this, home.class);
					Bundle bundle = new Bundle();
					intent.putExtras(bundle);
					startActivity(intent);
					
				}
			})
        	.setNegativeButton("Cancel", null)
        	.show();
        case 1: // for settings button content
        	return  new AlertDialog.Builder(this)
        	.setMessage("Save?")
        	.setCancelable(false)  // this is friendly
        	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					String extStorage = Environment.getExternalStorageDirectory().toString();
					File tem_file = new File(extStorage, "Retouch_tem.JPEG");
				    if(tem_file.exists())
				    {
				    	boolean deleted = tem_file.delete();
				    }
				    
				    Bundle fieldresults = Retouch.this.getIntent().getExtras();
				    String name = fieldresults.getString("bm");
				    name = name.replace(extStorage, "");
				    name = name + "_RetouchCopy";
				    
				    File test_file = new File(extStorage, name+"0");
				    
				    if(!test_file.exists())
				    {
				    	//name = name.replace(extStorage, "");
					    //name = name + "_RetouchCopy";
				    	name = name + "0";
				    }
				    else
				    {
				    	int i = 1;
				    	//name = name.replace(extStorage, "");
					    //name = name + "_RetouchCopy";
					    String tem = name;
				    	while(test_file.exists())
				    	{
				    		if(i > 9)
				    		{
				    			int tem1 = i / 10;
				    			int tem2 = i % 10;
				    			tem = name + ( "0" + tem1) + ("0"+ tem2)+".JPEG";
				    		}
				    		else
				    		{
				    			tem = name+("0"+i)+".JPEG";
				    		}
				    		
				    		test_file = new File(extStorage, tem);
				    		i++;
				    	}
				    	name = tem;
				    }
				    
				    File file = new File(extStorage, name);
				     
				    try {
				     OutputStream outStream = new FileOutputStream(file);
				     bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
				     outStream.flush();
				        outStream.close();
				        Toast.makeText(Retouch.this, 
				          extStorage+name,
				          Toast.LENGTH_LONG).show();
				    } catch (FileNotFoundException e) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
				     Toast.makeText(Retouch.this, 
				          e.toString(), 
				          Toast.LENGTH_LONG).show();
				    } catch (IOException e) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
				     Toast.makeText(Retouch.this, 
				          e.toString(), 
				          Toast.LENGTH_LONG).show();
				    }
				   
				    Intent intent = new Intent(Retouch.this, home.class);
				    Bundle bundle = new Bundle();
					intent.putExtras(bundle);
					startActivity(intent);
				}
			})
        	.setNegativeButton("Cancel", null)
        	.show();
        }
        return null;
    }
    
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putString("bm", path);
		bundle.putInt("download", download);
		//bundle.putInt("sc", scale);
		
		//Bundle bundle = Retouch.this.getIntent().getExtras();
		Intent intent;
		switch(v.getId())
		{
		case R.id.image1:
			intent = new Intent(Retouch.this, Edit.class);
			intent.putExtras(bundle);
			startActivity(intent);
			File tem_file1 = new File(extStorage, "Edit_tem.JPEG");
			if(tem_file1.exists())
		    {
				boolean deleted = tem_file1.delete();
			}
			break;
		case R.id.image2:
			intent = new Intent(Retouch.this, Tone.class);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case R.id.image3:
			intent = new Intent(Retouch.this, Blur.class);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case R.id.image4:
			intent = new Intent(Retouch.this, Effects.class);
			intent.putExtras(bundle);
			File tem_file2 = new File(extStorage, "Effect_tem.JPEG");
			if(tem_file2.exists())
		    {
				boolean deleted = tem_file2.delete();
			}
			startActivity(intent);
			break;
		case R.id.image5:
			intent = new Intent(Retouch.this, Frame.class);
			intent.putExtras(bundle);
			File tem_file3 = new File(extStorage, "Frame_tem.JPEG");
			if(tem_file3.exists())
		    {
				boolean deleted = tem_file3.delete();
			}
			startActivity(intent);
			break;
		case R.id.image6:
			intent = new Intent(Retouch.this, TextActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		}
	}
}