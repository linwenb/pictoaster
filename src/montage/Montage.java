package com.pictoaster.www.montage;

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
import com.pictoaster.www.retouch.Edit;
import com.pictoaster.www.retouch.Retouch;

public class Montage extends Activity implements View.OnClickListener {
	
	private ImageView myImageView;
	private ImageView view1; 
	private ImageView view2; 
	private ImageView view3; 
	private Bitmap bm,bm1,bm2,bm3;
	private Intent intent;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.montage);

        String extStorage = Environment.getExternalStorageDirectory().toString();
        //String path;
        
        File tem_file = new File(extStorage, "Montage_tem.JPEG");
	    if(tem_file.exists())
	    {
	    	myImageView = (ImageView) findViewById(R.id.imageView);
	    	String path = extStorage + "/Montage_tem.JPEG";
	    	bm = BitmapFactory.decodeFile(path);	
	    	myImageView.setImageBitmap(bm);
	    }
	    else
	    {
	    	Bundle bundle = this.getIntent().getExtras();
	    	
	    	view1 = (ImageView) findViewById(R.id.img1);
	    	view2 = (ImageView) findViewById(R.id.img2);
	    	view3 = (ImageView) findViewById(R.id.img3);
	    	
	    	String path1 = bundle.getString("bm1");
	    	String path2 = bundle.getString("bm2"); 
	    	String path3 = bundle.getString("bm3"); 
	    	
	    	int scale;
		    
		    BitmapFactory.Options options1 = new BitmapFactory.Options();   
	        options1.inJustDecodeBounds = true;
	        bm1 = BitmapFactory.decodeFile(path1, options1);                
		    for(scale = 2; options1.outWidth*options1.outHeight > 300000; scale = scale * 2)
		    {
		    	options1.inSampleSize = scale;
		    	bm1 = BitmapFactory.decodeFile(path1, options1);
		    }
		    options1.inJustDecodeBounds = false;
		    bm1 = BitmapFactory.decodeFile(path1, options1);
		    
		    
		    BitmapFactory.Options options2 = new BitmapFactory.Options();   
	        options2.inJustDecodeBounds = true;
	        bm2 = BitmapFactory.decodeFile(path2, options2);                
		    for(scale = 2; options2.outWidth*options2.outHeight > 300000; scale = scale * 2)
		    {
		    	options2.inSampleSize = scale;
		    	bm2 = BitmapFactory.decodeFile(path2, options2);
		    }
		    options2.inJustDecodeBounds = false;
		    bm2 = BitmapFactory.decodeFile(path2, options2);
		    
		    BitmapFactory.Options options3 = new BitmapFactory.Options();   
	        options3.inJustDecodeBounds = true;
	        bm3 = BitmapFactory.decodeFile(path3, options3);                
		    for(scale = 2; options3.outWidth*options3.outHeight > 300000; scale = scale * 2)
		    {
		    	options3.inSampleSize = scale;
		    	bm3 = BitmapFactory.decodeFile(path3, options3);
		    }
		    options3.inJustDecodeBounds = false;
		    bm3 = BitmapFactory.decodeFile(path3, options3);
		    		    
		    view2.setImageBitmap(bm2);
		    view1.setImageBitmap(bm1);
		    view3.setImageBitmap(bm3);
	    }
        
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
   
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
    }
    
    protected Dialog onCreateDialog(int id) {    
        switch(id) {
        case 0: // for return button content
        	return new AlertDialog.Builder(this)
        	.setMessage("Exit without save?")
        	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					String extStorage = Environment.getExternalStorageDirectory().toString();
					File tem_file = new File(extStorage, "Montage_tem.JPEG");
				    if(tem_file.exists())
				    {
				    	boolean deleted = tem_file.delete();
				    }
				    
					intent = new Intent(Montage.this, home.class);
					Bundle bundle = new Bundle();
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
					
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
					File tem_file = new File(extStorage, "Montage_tem.JPEG");
				    if(tem_file.exists())
				    {
				    	boolean deleted = tem_file.delete();
				    
				    				    
						//String extStorage = Environment.getExternalStorageDirectory().toString();
							
					    Bundle fieldresults = Montage.this.getIntent().getExtras();
					    String name = fieldresults.getString("bm1");
					    name = name.replace(extStorage, "");
					    name = name + "_MontageCopy";
					    
					    File test_file = new File(extStorage, name+"0");
					    
					    if(!test_file.exists())
					    {
					    	name = name + "0";
					    }
					    else
					    {
					    	int i = 1;
	
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
						      	  Toast.makeText(Montage.this, 
						          extStorage+name,
						          Toast.LENGTH_LONG).show();
						    } catch (FileNotFoundException e) {
						     // TODO Auto-generated catch block
						     e.printStackTrace();
						     Toast.makeText(Montage.this, 
						          e.toString(), 
						          Toast.LENGTH_LONG).show();
						    } catch (IOException e) {
						     // TODO Auto-generated catch block
						     e.printStackTrace();
						     Toast.makeText(Montage.this, 
						          e.toString(), 
						          Toast.LENGTH_LONG).show();
						    }
					    	
					    intent = new Intent(Montage.this, home.class);
					    Bundle bundle = new Bundle();
						intent.putExtras(bundle);
						startActivity(intent);
						finish();
				    }
				    else{
				    	Toast.makeText(Montage.this, 
						          "Cannot save without any progress", 
						          Toast.LENGTH_LONG).show();
				    }
				}
			})
        	.setNegativeButton("Cancel", null)
        	.show();
        }
        return null;
    }
    
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Bundle bundle = Montage.this.getIntent().getExtras();
		
		switch(v.getId())
		{
		case R.id.image1:
			intent = new Intent(Montage.this, Template.class);
			intent.putExtras(bundle);
			if(bm!=null&&!bm.isRecycled()){
		        bm.recycle(); //回收图片所占的内存
		         System.gc(); //提醒系统及时回收
		     }
			if(bm1!=null&&!bm1.isRecycled()){
		        bm1.recycle(); //回收图片所占的内存
		         System.gc(); //提醒系统及时回收
		     }
			if(bm2!=null&&!bm2.isRecycled()){
		        bm2.recycle(); //回收图片所占的内存
		         System.gc(); //提醒系统及时回收
		     }
			if(bm3!=null&&!bm3.isRecycled()){
		        bm3.recycle(); //回收图片所占的内存
		         System.gc(); //提醒系统及时回收
		     }
			startActivity(intent);
			System.exit(0);
			
			break;
		case R.id.image2:
			intent = new Intent(Montage.this, Free.class);
			intent.putExtras(bundle);
			if(bm!=null&&!bm.isRecycled()){
		        bm.recycle(); //回收图片所占的内存
		         System.gc(); //提醒系统及时回收
		     }
			if(bm1!=null&&!bm1.isRecycled()){
		        bm1.recycle(); //回收图片所占的内存
		         System.gc(); //提醒系统及时回收
		     }
			if(bm2!=null&&!bm2.isRecycled()){
		        bm2.recycle(); //回收图片所占的内存
		         System.gc(); //提醒系统及时回收
		     }
			if(bm3!=null&&!bm3.isRecycled()){
		        bm3.recycle(); //回收图片所占的内存
		         System.gc(); //提醒系统及时回收
		     }
			startActivity(intent);
			System.exit(0);
			break;
		case R.id.image3:
			intent = new Intent(Montage.this, Join.class);
			intent.putExtras(bundle);
			if(bm!=null&&!bm.isRecycled()){
		        bm.recycle(); //回收图片所占的内存
		         System.gc(); //提醒系统及时回收
		     }
			if(bm1!=null&&!bm1.isRecycled()){
		        bm1.recycle(); //回收图片所占的内存
		         System.gc(); //提醒系统及时回收
		     }
			if(bm2!=null&&!bm2.isRecycled()){
		        bm2.recycle(); //回收图片所占的内存
		         System.gc(); //提醒系统及时回收
		     }
			if(bm3!=null&&!bm3.isRecycled()){
		        bm3.recycle(); //回收图片所占的内存
		         System.gc(); //提醒系统及时回收
		     }
			startActivity(intent);
			System.exit(0);
			break;

		}
	}
}
