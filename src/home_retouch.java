package com.pictoaster.www;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pictoaster.www.retouch.Retouch;

public class home_retouch extends Activity implements OnClickListener {
	private ImageView myImageView;  
	private static final int SELECT_IMAGE = 1;
	private static final String TAG = "MainActivity";	
	private byte[] imageFile;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.home_retouch_view);
        
        String FILENAME = "down_file.txt";
		String content = "0000000000000000";
		File writeFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
		
		if(!writeFile.exists())
		{
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {

			try {
				FileOutputStream fos = new FileOutputStream(writeFile);
				fos.write(content.getBytes());
				fos.close();
				/*Toast.makeText(home.this, content,
                       Toast.LENGTH_LONG).show();*/
			} catch (Exception e) {
                Toast.makeText(home_retouch.this, "Write Failed",
                       Toast.LENGTH_SHORT).show();
            }
			} else {
        	//sdcard doesn't exist or can't write it right now
            Toast.makeText(home_retouch.this,
                    "Can't Write", Toast.LENGTH_SHORT).show();
			}
		}
        
        String extStorage = Environment.getExternalStorageDirectory().toString();
        File tem_file4 = new File(extStorage, "Crop_tem.JPEG");
		if(tem_file4.exists())
		{
			boolean deleted = tem_file4.delete();
		}
			
		File tem_file = new File(extStorage, "Retouch_tem.JPEG");
	    if(tem_file.exists())
	    {
	    	boolean deleted = tem_file.delete();
	    }
	    
	    File tem_file1 = new File(extStorage, "Edit_tem.JPEG");
		if(tem_file1.exists())
	    {
			boolean deleted = tem_file1.delete();
		}
		
		File tem_file2 = new File(extStorage, "Effect_tem.JPEG");
		if(tem_file2.exists())
	    {
			boolean deleted = tem_file2.delete();
		}
		
		File tem_file3 = new File(extStorage, "Frame_tem.JPEG");
		if(tem_file3.exists())
	    {
			boolean deleted = tem_file3.delete();
		}
       
        //ImageView image = (ImageView) findViewById(R.id.image);
        
        //image.setOnClickListener(this);
        setupButtonOnClickListeners();
    }
    
    private void setupButtonOnClickListeners(){

    	Button btn = (Button)findViewById(R.id.takeimage);
    	btn.setOnClickListener(this);
    	
    	 btn.setOnTouchListener(new Button.OnTouchListener(){
    		   @Override
    		   public boolean onTouch(View v, MotionEvent event) {
    		    if(event.getAction() == MotionEvent.ACTION_DOWN){   
    		                    v.setBackgroundResource(R.drawable.select2);   
    		                    Log.i("TestAndroid Button", "MotionEvent.ACTION_DOWN");
    		                }   
    		                else if(event.getAction() == MotionEvent.ACTION_UP){   
    		                    v.setBackgroundResource(R.drawable.select); 
    		                    Log.i("TestAndroid Button", "MotionEvent.ACTION_UP");
    		                } 
    		    return false;
    		   }
    		  });
    }
    
    private boolean readFromFile(String path)
    {
    	File file = new File(path);
    	try
    	{
    		FileInputStream fis = new FileInputStream(file);
    		Log.i(TAG, "fileSize: " + file.length());
    		imageFile = new byte[(int)file.length()];
    		fis.read(imageFile);
    		fis.close();
    		
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    		return false;
    	}
    	return true;
    }   
   
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    
    	if (resultCode != RESULT_OK) { 
    		Log.e(TAG,"ActivityResult resultCode error");
    		return;
       }
    
       Bitmap bm = null; 
       ContentResolver resolver = getContentResolver();

       if (requestCode == SELECT_IMAGE) {
           Uri imageUri = data.getData();
		   //bm = MediaStore.Images.Media.getBitmap(resolver, imageUri);      
		   //myImageView = (ImageView) findViewById(R.id.imageView);  
		   //myImageView.setImageBitmap(bm); 
		      
		   //next it get the image path by a complex method
		   String[] proj = {MediaStore.Images.Media.DATA};
		   Cursor cursor = managedQuery(imageUri, proj, null, null, null);  
		   int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);      
		   cursor.moveToFirst();
		   String path = cursor.getString(column_index);
		   cursor.close();

		   //initial two thing for calling the target activity
		   	Intent intent = new Intent(home_retouch.this, Retouch.class);
		   	
		   	Bundle bundle1 = this.getIntent().getExtras();
			int tem = bundle1.getInt("download");
			Bundle bundle = new Bundle();
			bundle.putInt("download", tem);
			//intent.putExtras(bundle);
		   //Bundle bundle = new Bundle();
		   
		   //take the path string as message
		   bundle.putString("bm", path);
		   intent.putExtras(bundle);
		   //call activity
		   startActivity(intent);    
       }   
   }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.takeimage:
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(Intent.createChooser(intent, "selectImage"), SELECT_IMAGE);
			break;
		}
	}
}

