package com.pictoaster.www.retouch.edit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pictoaster.www.R;
import com.pictoaster.www.Setting;
import com.pictoaster.www.home;
import com.pictoaster.www.retouch.Retouch;
 
 public class Crop extends Activity {
 
	 private Bitmap cropImage;
     private static int SELECT_PICTURE;//返回标志位 filed
     private File tempFile;
     private ImageView cropImg;
     String extStorage = Environment.getExternalStorageDirectory().toString();
     private Bitmap modified;
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
 		setContentView(R.layout.retouch_edit_crop);
 		
 		cropImg = (ImageView) findViewById(R.id.imgCrop);
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
	    cropImg.setImageBitmap(mySourceBmp);
	    modified = mySourceBmp;
	    
 	     File file = new File(path);
 	     this.tempFile = new File(extStorage,"Crop_tem.JPG");
         final Uri uri = Uri.fromFile(file);
         Button btnStart = (Button) findViewById(R.id.btn_start_crop);
 	    	btnStart.setOnClickListener(new Button.OnClickListener() {

 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				Intent intent = new Intent("com.android.camera.action.CROP"); 
 		        intent.setClassName("com.android.camera","com.android.camera.CropImage");
 		        intent.setDataAndType(uri, "image/*");
 		        intent.putExtra("crop", "true");
 		        intent.putExtra("output", Uri.fromFile(tempFile));
 		        intent.putExtra("outputFormat", "JPEG");
 		        startActivityForResult(Intent.createChooser(intent, "选择图片"), SELECT_PICTURE);			
 			}
         });
         
 	    	ImageView ok = (ImageView) findViewById(R.id.crop_check);
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
 				     Toast.makeText(Crop.this, 
 				          e.toString(), 
 				          Toast.LENGTH_LONG).show();
 				    } catch (IOException e) {
 				     // TODO Auto-generated catch block
 				     e.printStackTrace();
 				     Toast.makeText(Crop.this, 
 				          e.toString(), 
 				          Toast.LENGTH_LONG).show();
 				    }
 				    
 				    Intent intent = new Intent(Crop.this, Retouch.class);
 				    Bundle bundle = Crop.this.getIntent().getExtras();
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
 			
 			ImageView cancel = (ImageView) findViewById(R.id.crop_error);
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
 					
 					Intent intent = new Intent(Crop.this, Retouch.class);
 					Bundle bundle = Crop.this.getIntent().getExtras();
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
 
    /**
     * 裁剪完图片后系统调用的方法:onActivityResult
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
            if (requestCode == SELECT_PICTURE)
            {
                modified = BitmapFactory.decodeFile(tempFile.getAbsolutePath());
                cropImg.setImageBitmap(modified);
                
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
    		     Toast.makeText(Crop.this, 
    		          e.toString(), 
    		          Toast.LENGTH_LONG).show();
    		    } catch (IOException e) {
    		     // TODO Auto-generated catch block
    		     e.printStackTrace();
    		     Toast.makeText(Crop.this, 
    		          e.toString(), 
    		          Toast.LENGTH_LONG).show();
    		    }
            	//button.setBackgroundDrawable(Drawable.createFromPath(tempFile.getAbsolutePath()));
            }
    }
}