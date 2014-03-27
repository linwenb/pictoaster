package com.pictoaster.www.montage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.pictoaster.www.R;
import com.pictoaster.www.retouch.frame.SimpleFrame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Template extends Activity implements View.OnClickListener {
	
	private RelativeLayout view;
	
	private Bitmap bmp1;	
	private Bitmap bmp2;	
	private Bitmap bmp3;
	
	private Bitmap bm1;
	private Bitmap bm2;
	private Bitmap bm3;
	
    private int width1;
    private int width2;
    private int width3;
     
    private int height1;
    private int height2;
    private int height3;

	private ImageView img1;
	private ImageView img2;
	private ImageView img3;
	
	private Intent intent;
	private Bundle bundle;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.montage_template);
        setupViews(); 
        setupButton();
        t1();
    }
    
    private void setupButton(){
		view = (RelativeLayout)findViewById(R.id.result);
				
		ImageView cancel = (ImageView) findViewById(R.id.error);
		ImageView ok = (ImageView) findViewById(R.id.check);
        
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        
        ImageView image1 = (ImageView) findViewById(R.id.image1);
        ImageView image2 = (ImageView) findViewById(R.id.image2);
        ImageView image3 = (ImageView) findViewById(R.id.image3);
        ImageView image4 = (ImageView) findViewById(R.id.image4);
        ImageView image5 = (ImageView) findViewById(R.id.image5);
        
        String FILENAME = "down_file.txt";
		String content = "";
		int NUMBER_SIMPLE = 5;
		int NUMBER_SPECIAL = 3;
		int NUMBER_TEMPLATE = 3;
		int NUMBER_DIALOG = 5;
		int NUMBER_TOTAL = 16;

		File readFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
		
		if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                FileInputStream inputStream = new FileInputStream(readFile);
                byte[] b = new byte[inputStream.available()];
                inputStream.read(b);
                content += new String(b);
                /*Toast.makeText(Template.this, "Read Success",
                        Toast.LENGTH_LONG).show();*/
            } catch (Exception e) {
                Toast.makeText(Template.this, "Read Failed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            //sdcard doesn't exist or can't read it right now
            Toast.makeText(Template.this,
                    "Can't Read", Toast.LENGTH_SHORT).show();
        }
		
		if(content.isEmpty())
		{
			content = "0000000000000000";
		}
        
        if(content.charAt(NUMBER_SIMPLE+NUMBER_SPECIAL) == '1'){ 
        	image3.setVisibility(View.VISIBLE);	        
        }
        if(content.charAt(NUMBER_SIMPLE+NUMBER_SPECIAL + 1) == '1'){	        
        	image4.setVisibility(View.VISIBLE);	        
        }
        if(content.charAt(NUMBER_SIMPLE+NUMBER_SPECIAL + 2) == '1'){
        	image5.setVisibility(View.VISIBLE);        	
        }
        
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
    }
    
    private void setupViews() 
    {
        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);
        
        bundle = this.getIntent().getExtras();
        
	    String path1 = bundle.getString("bm1");
	    String path2 = bundle.getString("bm2");
	    String path3 = bundle.getString("bm3");
	    
	    int scale;
	    
	    BitmapFactory.Options options1 = new BitmapFactory.Options();   
        options1.inJustDecodeBounds = true;
        bmp1 = BitmapFactory.decodeFile(path1, options1);                
	    for(scale = 2; options1.outWidth*options1.outHeight > 300000; scale = scale * 2)
	    {
	    	options1.inSampleSize = scale;
	    	bmp1 = BitmapFactory.decodeFile(path1, options1);
	    }
	    options1.inJustDecodeBounds = false;
	    bmp1 = BitmapFactory.decodeFile(path1, options1);
	    
	    
	    BitmapFactory.Options options2 = new BitmapFactory.Options();   
        options2.inJustDecodeBounds = true;
        bmp2 = BitmapFactory.decodeFile(path2, options2);                
	    for(scale = 2; options2.outWidth*options2.outHeight > 300000; scale = scale * 2)
	    {
	    	options2.inSampleSize = scale;
	    	bmp2 = BitmapFactory.decodeFile(path2, options2);
	    }
	    options2.inJustDecodeBounds = false;
	    bmp2 = BitmapFactory.decodeFile(path2, options2);
	    
	    BitmapFactory.Options options3 = new BitmapFactory.Options();   
        options3.inJustDecodeBounds = true;
        bmp3 = BitmapFactory.decodeFile(path3, options3);                
	    for(scale = 2; options3.outWidth*options3.outHeight > 300000; scale = scale * 2)
	    {
	    	options3.inSampleSize = scale;
	    	bmp3 = BitmapFactory.decodeFile(path3, options3);
	    }
	    options3.inJustDecodeBounds = false;
	    bmp3 = BitmapFactory.decodeFile(path3, options3);
	    
        width1 = bmp1.getWidth(); 
        width2 = bmp2.getWidth(); 
        width3 = bmp3.getWidth();
        
	    height1 = bmp1.getHeight();
	    height2 = bmp2.getHeight();
	    height3 = bmp3.getHeight();
    }
    
    private void t1(){
    	view.setBackgroundResource(R.drawable.t1);
    	
        Matrix matrix1 = new Matrix(); 	    
        Matrix matrix2 = new Matrix();
        Matrix matrix3 = new Matrix();	    
        
        float sizeW1 = 1F*268/width1;
        float sizeW2 = 1F*84/width2; 
        float sizeW3 = 1F*84/width3;   
        
        float sizeH1 = 1F*205/height1;
        float sizeH2 = 1F*150/height2;
        float sizeH3 = 1F*150/height3;

        matrix1.postScale(sizeW1, sizeH1);
        matrix2.postScale(sizeW2, sizeH2);
        matrix3.postScale(sizeW3, sizeH3);
        
        bm1 = Bitmap.createBitmap(bmp1, 0, 0, width1 ,height1 , matrix1, true);
        bm2 = Bitmap.createBitmap(bmp2, 0, 0, width2 ,height2 , matrix2, true);
        bm3 = Bitmap.createBitmap(bmp3, 0, 0, width3 ,height3 , matrix3, true);

	    RelativeLayout.LayoutParams par1 = (RelativeLayout.LayoutParams)img1.getLayoutParams();
	    RelativeLayout.LayoutParams par2 = (RelativeLayout.LayoutParams)img2.getLayoutParams();
	    RelativeLayout.LayoutParams par3 = (RelativeLayout.LayoutParams)img3.getLayoutParams();
	    
	    par1.topMargin = 75;
	    par2.topMargin = 300;
	    par3.topMargin = 300;	
	    
	    par1.leftMargin = 136;
	    par2.leftMargin = 62;
	    par3.leftMargin = 158;
	    
	    img1.setLayoutParams(par1);
	    img2.setLayoutParams(par2);
	    img3.setLayoutParams(par3);
        
        img1.setRotation((float) -5.3);
        img2.setRotation(0);
        img3.setRotation(0);
        
        img1.setImageBitmap(bm1);
        img2.setImageBitmap(bm2);
        img3.setImageBitmap(bm3);	 
    }
    
    private void t2(){
    	view.setBackgroundResource(R.drawable.t2);
    	
        Matrix matrix1 = new Matrix(); 	    
        Matrix matrix2 = new Matrix();
        Matrix matrix3 = new Matrix();	    
        
        float sizeW1 = 1F*177/width1;
        float sizeW2 = 1F*145/width2; 
        float sizeW3 = 1F*147/width3;   
        
        float sizeH1 = 1F*274/height1;
        float sizeH2 = 1F*139/height2;
        float sizeH3 = 1F*139/height3;

        matrix1.postScale(sizeW1, sizeH1);
        matrix2.postScale(sizeW2, sizeH2);
        matrix3.postScale(sizeW3, sizeH3);
        
        bm1 = Bitmap.createBitmap(bmp1, 0, 0, width1 ,height1 , matrix1, true);
        bm2 = Bitmap.createBitmap(bmp2, 0, 0, width2 ,height2 , matrix2, true);
        bm3 = Bitmap.createBitmap(bmp3, 0, 0, width3 ,height3 , matrix3, true);

	    RelativeLayout.LayoutParams par1 = (RelativeLayout.LayoutParams)img1.getLayoutParams();
	    RelativeLayout.LayoutParams par2 = (RelativeLayout.LayoutParams)img2.getLayoutParams();
	    RelativeLayout.LayoutParams par3 = (RelativeLayout.LayoutParams)img3.getLayoutParams();
	    
	    par1.topMargin = 125;
	    par2.topMargin = 107;
	    par3.topMargin = 257;	
	    
	    par1.leftMargin = 27;
	    par2.leftMargin = 282;
	    par3.leftMargin = 268;
	    
	    img1.setLayoutParams(par1);
	    img2.setLayoutParams(par2);
	    img3.setLayoutParams(par3);
        
        img1.setRotation((float) -4.3);
        img2.setRotation((float) 5.3);
        img3.setRotation((float) 5.3);
        
        img1.setImageBitmap(bm1);
        img2.setImageBitmap(bm2);
        img3.setImageBitmap(bm3);	 
    }
    
    private void t3(){
    	view.setBackgroundResource(R.drawable.t3);
    	
        Matrix matrix1 = new Matrix(); 	    
        Matrix matrix2 = new Matrix();
        Matrix matrix3 = new Matrix();	    
        
        float sizeW1 = 1F*345/width1;
        float sizeW2 = 1F*155/width2; 
        float sizeW3 = 1F*155/width3;   
        
        float sizeH1 = 1F*240/height1;
        float sizeH2 = 1F*102/height2;
        float sizeH3 = 1F*102/height3;

        matrix1.postScale(sizeW1, sizeH1);
        matrix2.postScale(sizeW2, sizeH2);
        matrix3.postScale(sizeW3, sizeH3);
        
        bm1 = Bitmap.createBitmap(bmp1, 0, 0, width1 ,height1 , matrix1, true);
        bm2 = Bitmap.createBitmap(bmp2, 0, 0, width2 ,height2 , matrix2, true);
        bm3 = Bitmap.createBitmap(bmp3, 0, 0, width3 ,height3 , matrix3, true);

	    RelativeLayout.LayoutParams par1 = (RelativeLayout.LayoutParams)img1.getLayoutParams();
	    RelativeLayout.LayoutParams par2 = (RelativeLayout.LayoutParams)img2.getLayoutParams();
	    RelativeLayout.LayoutParams par3 = (RelativeLayout.LayoutParams)img3.getLayoutParams();
	    
	    par1.topMargin = 63;
	    par2.topMargin = 357;
	    par3.topMargin = 377;	
	    
	    par1.leftMargin = 60;
	    par2.leftMargin = 110;
	    par3.leftMargin = 307;
	    
	    img1.setLayoutParams(par1);
	    img2.setLayoutParams(par2);
	    img3.setLayoutParams(par3);
        
        img1.setRotation(0);
        img2.setRotation(0);
        img3.setRotation(0);
        
        img1.setImageBitmap(bm1);
        img2.setImageBitmap(bm2);
        img3.setImageBitmap(bm3);	 
    }
    
    private void t4(){
    	view.setBackgroundResource(R.drawable.t4);
    	
        Matrix matrix1 = new Matrix(); 	    
        Matrix matrix2 = new Matrix();
        Matrix matrix3 = new Matrix();	    
        
        float sizeW1 = 1F*277/width1;
        float sizeW2 = 1F*81/width2; 
        float sizeW3 = 1F*81/width3;   
        
        float sizeH1 = 1F*304/height1;
        float sizeH2 = 1F*165/height2;
        float sizeH3 = 1F*165/height3;

        matrix1.postScale(sizeW1, sizeH1);
        matrix2.postScale(sizeW2, sizeH2);
        matrix3.postScale(sizeW3, sizeH3);
        
        bm1 = Bitmap.createBitmap(bmp1, 0, 0, width1 ,height1 , matrix1, true);
        bm2 = Bitmap.createBitmap(bmp2, 0, 0, width2 ,height2 , matrix2, true);
        bm3 = Bitmap.createBitmap(bmp3, 0, 0, width3 ,height3 , matrix3, true);

	    RelativeLayout.LayoutParams par1 = (RelativeLayout.LayoutParams)img1.getLayoutParams();
	    RelativeLayout.LayoutParams par2 = (RelativeLayout.LayoutParams)img2.getLayoutParams();
	    RelativeLayout.LayoutParams par3 = (RelativeLayout.LayoutParams)img3.getLayoutParams();
	    
	    par1.topMargin = 90;
	    par2.topMargin = 56;
	    par3.topMargin = 287;	
	    
	    par1.leftMargin = 26;
	    par2.leftMargin = 359;
	    par3.leftMargin = 359;
	    
	    img1.setLayoutParams(par1);
	    img2.setLayoutParams(par2);
	    img3.setLayoutParams(par3);
        
        img1.setRotation(0);
        img2.setRotation(0);
        img3.setRotation(0);
        
        img1.setImageBitmap(bm1);
        img2.setImageBitmap(bm2);
        img3.setImageBitmap(bm3);	 
    }
    
    private void t5(){
    	view.setBackgroundResource(R.drawable.t5);
    	
        Matrix matrix1 = new Matrix(); 	    
        Matrix matrix2 = new Matrix();
        Matrix matrix3 = new Matrix();	    
        
        float sizeW1 = 1F*115/width1;
        float sizeW2 = 1F*115/width2; 
        float sizeW3 = 1F*415/width3;   
        
        float sizeH1 = 1F*162/height1;
        float sizeH2 = 1F*162/height2;
        float sizeH3 = 1F*375/height3;

        matrix1.postScale(sizeW1, sizeH1);
        matrix2.postScale(sizeW2, sizeH2);
        matrix3.postScale(sizeW3, sizeH3);
        
        bm1 = Bitmap.createBitmap(bmp1, 0, 0, width1 ,height1 , matrix1, true);
        bm2 = Bitmap.createBitmap(bmp2, 0, 0, width2 ,height2 , matrix2, true);
        bm3 = Bitmap.createBitmap(bmp3, 0, 0, width3 ,height3 , matrix3, true);

	    RelativeLayout.LayoutParams par1 = (RelativeLayout.LayoutParams)img1.getLayoutParams();
	    RelativeLayout.LayoutParams par2 = (RelativeLayout.LayoutParams)img2.getLayoutParams();
	    RelativeLayout.LayoutParams par3 = (RelativeLayout.LayoutParams)img3.getLayoutParams();
	    
	    par1.topMargin = 115;
	    par2.topMargin = 80;
	    par3.topMargin = 318;	
	    
	    par1.leftMargin = 75;
	    par2.leftMargin = 219;
	    par3.leftMargin = 0;
	    
	    img1.setLayoutParams(par1);
	    img2.setLayoutParams(par2);
	    img3.setLayoutParams(par3);
        
        img1.setRotation((float) -12.9);
        img2.setRotation((float) -12.9);
        img3.setRotation((float) -14.8);
        
        img1.setImageBitmap(bm1);
        img2.setImageBitmap(bm2);
        img3.setImageBitmap(bm3);	 
    }
    
    private void clear()
    {
	    bmp1.recycle();		    
	    bmp2.recycle();
	    bmp3.recycle();
	    
	    bmp1 = null;
	    bmp2 = null;
	    bmp3 = null;
		
	    bm1.recycle();		    
	    bm2.recycle();
	    bm3.recycle();
	    
	    bm1 = null;
	    bm2 = null;
	    bm3 = null;
	    System.gc();
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.image1:t1();break;
		case R.id.image2:t2();break;
		case R.id.image3:t3();break;
		case R.id.image4:t4();break;
		case R.id.image5:t5();break;
		
		case R.id.check:
			
			view.setDrawingCacheEnabled(true);
			view.buildDrawingCache();
			
			Bitmap bm = view.getDrawingCache();
			
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
		     Toast.makeText(Template.this, 
		          e.toString(), 
		          Toast.LENGTH_LONG).show();
		    } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		     Toast.makeText(Template.this, 
		          e.toString(), 
		          Toast.LENGTH_LONG).show();
		    }
		    
		    clear();
			
		    intent = new Intent(Template.this, Montage.class);
		    intent.putExtras(bundle);
			startActivity(intent);
			System.exit(0);
			
			break;
		case R.id.error:
			
			clear();
			
			intent = new Intent(Template.this, Montage.class);
			intent.putExtras(bundle);
			startActivity(intent);
			System.exit(0);
			
			break;
		}
	}
}