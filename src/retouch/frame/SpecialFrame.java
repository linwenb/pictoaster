package com.pictoaster.www.retouch.frame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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

import com.pictoaster.www.R;
import com.pictoaster.www.retouch.Retouch;

public class SpecialFrame extends Activity {
	private ProgressBar ProgressBar_Special;
	private ImageView imgView1;
	private ImageView frame1;
	private ImageView frame2;
	private ImageView frame3;
	private ImageView frame4;
	private ImageView frame5;	
	private ImageView frame6;
	private Bitmap modified;
	private Bitmap bmp1;
	Thread threadSpecial;
	Handler handlerSpecial = new Handler();
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
        setContentView(R.layout.retouch_frame_special);
        
        Bundle fieldresults = this.getIntent().getExtras();
		String path;
        
        File tem_file = new File(extStorage, "Frame_tem.JPEG");
	    if(tem_file.exists())
	    {
	    	path = extStorage + "/Frame_tem.JPEG";
	    }
	    else
	    {
	    	path = fieldresults.getString("bm");
	    }

	    bmp1= BitmapFactory.decodeFile(path);
	    imgView1 = (ImageView) findViewById(R.id.imgView1);
        imgView1.setImageBitmap(bmp1);
        modified = bmp1;
        
        path = fieldresults.getString("bm");
        bmp1= BitmapFactory.decodeFile(path);
	    
	    int download = fieldresults.getInt("download");
	    
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
                /*Toast.makeText(SpecialFrame.this, "Read Success",
                        Toast.LENGTH_LONG).show();*/
            } catch (Exception e) {
                Toast.makeText(SpecialFrame.this, "Read Failed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            //sdcard doesn't exist or can't read it right now
            Toast.makeText(SpecialFrame.this,
                    "Can't Read", Toast.LENGTH_SHORT).show();
        }
		
		if(content.isEmpty())
		{
			content = "0000000000000000";
		}
	    
	    ProgressBar_Special = (ProgressBar) findViewById(R.id.specialBar);

	    frame1 = (ImageView)findViewById(R.id.frame1);
    	frame1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				ProgressBar_Special.setVisibility(View.VISIBLE);
				threadSpecial = new Thread(new Runnable(){
					 public void run(){
						 modified = specialFrame1(bmp1);
						 handlerSpecial.post(new Runnable(){
							public void run(){
							File tem_file1 = new File(extStorage, "Frame_tem.JPEG");
							if(tem_file1.exists())
							{
								boolean deleted = tem_file1.delete();
							}
									    
							File file = new File(extStorage, "Frame_tem.JPEG");
								    
							try {
								OutputStream outStream = new FileOutputStream(file);
								modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
								outStream.flush();
								outStream.close();
								/*Toast.makeText(Fashion.this, 
								extStorage+"/Effect_tem.JPEG", 
								Toast.LENGTH_LONG).show();*/
							} 
							catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Toast.makeText(SpecialFrame.this, 
								e.toString(), 
								Toast.LENGTH_LONG).show();
							} 
							catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Toast.makeText(SpecialFrame.this, 
								e.toString(), 
								Toast.LENGTH_LONG).show();
							}
									 
							imgView1.setImageBitmap(modified);
							ProgressBar_Special.setVisibility(View.GONE);
						 }
					});
				 }
			});
		    threadSpecial.start();
			}
		});
    	
    	frame2 = (ImageView)findViewById(R.id.frame2);
    	frame2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				ProgressBar_Special.setVisibility(View.VISIBLE);
				threadSpecial = new Thread(new Runnable(){
					 public void run(){
						 modified = specialFrame2(bmp1);
						 handlerSpecial.post(new Runnable(){
							public void run(){
							File tem_file1 = new File(extStorage, "Frame_tem.JPEG");
							if(tem_file1.exists())
							{
								boolean deleted = tem_file1.delete();
							}
									    
							File file = new File(extStorage, "Frame_tem.JPEG");
								    
							try {
								OutputStream outStream = new FileOutputStream(file);
								modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
								outStream.flush();
								outStream.close();
								/*Toast.makeText(Fashion.this, 
								extStorage+"/Effect_tem.JPEG", 
								Toast.LENGTH_LONG).show();*/
							} 
							catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Toast.makeText(SpecialFrame.this, 
								e.toString(), 
								Toast.LENGTH_LONG).show();
							} 
							catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Toast.makeText(SpecialFrame.this, 
								e.toString(), 
								Toast.LENGTH_LONG).show();
							}
									 
							imgView1.setImageBitmap(modified);
							ProgressBar_Special.setVisibility(View.GONE);
						 }
					});
				 }
			});
		    threadSpecial.start();
			}
		});
	    
    	frame3 = (ImageView)findViewById(R.id.frame3);
    	frame3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				ProgressBar_Special.setVisibility(View.VISIBLE);
				threadSpecial = new Thread(new Runnable(){
					 public void run(){
						 modified = specialFrame3(bmp1);
						 handlerSpecial.post(new Runnable(){
							public void run(){
							File tem_file1 = new File(extStorage, "Frame_tem.JPEG");
							if(tem_file1.exists())
							{
								boolean deleted = tem_file1.delete();
							}
									    
							File file = new File(extStorage, "Frame_tem.JPEG");
								    
							try {
								OutputStream outStream = new FileOutputStream(file);
								modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
								outStream.flush();
								outStream.close();
								/*Toast.makeText(Fashion.this, 
								extStorage+"/Effect_tem.JPEG", 
								Toast.LENGTH_LONG).show();*/
							} 
							catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Toast.makeText(SpecialFrame.this, 
								e.toString(), 
								Toast.LENGTH_LONG).show();
							} 
							catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Toast.makeText(SpecialFrame.this, 
								e.toString(), 
								Toast.LENGTH_LONG).show();
							}
									 
							imgView1.setImageBitmap(modified);
							ProgressBar_Special.setVisibility(View.GONE);
						 }
					});
				 }
			});
		    threadSpecial.start();
			}
		});
    	
    	frame4 = (ImageView)findViewById(R.id.frame4);
    	
    	if(content.charAt(NUMBER_SIMPLE) == '1'){ 
    		frame4.setVisibility(View.VISIBLE);	        
        }
    	
    	frame4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				ProgressBar_Special.setVisibility(View.VISIBLE);
				threadSpecial = new Thread(new Runnable(){
					 public void run(){
						 modified = specialFrame4(bmp1);
						 handlerSpecial.post(new Runnable(){
								public void run(){
							    File tem_file1 = new File(extStorage, "Frame_tem.JPEG");
								if(tem_file1.exists())
								{
									boolean deleted = tem_file1.delete();
								}
									    
								File file = new File(extStorage, "Frame_tem.JPEG");
								    
								try {
									OutputStream outStream = new FileOutputStream(file);
									modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
									outStream.flush();
									outStream.close();
									/*Toast.makeText(Fashion.this, 
								    extStorage+"/Effect_tem.JPEG", 
								    Toast.LENGTH_LONG).show();*/
								} 
								catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SpecialFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								} 
								catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SpecialFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								}
										
								imgView1.setImageBitmap(modified);
								ProgressBar_Special.setVisibility(View.GONE);
							}
						 });
					 }
				});
				threadSpecial.start();
			}
		});
	    
    	frame5 = (ImageView)findViewById(R.id.frame5);
    	
    	if(content.charAt(NUMBER_SIMPLE + 1) == '1'){ 
    		frame5.setVisibility(View.VISIBLE);	        
        }
    	
    	frame5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				ProgressBar_Special.setVisibility(View.VISIBLE);
				threadSpecial = new Thread(new Runnable(){
					 public void run(){
						 modified = specialFrame5(bmp1);
						 handlerSpecial.post(new Runnable(){
							public void run(){
							File tem_file1 = new File(extStorage, "Frame_tem.JPEG");
							if(tem_file1.exists())
							{
								boolean deleted = tem_file1.delete();
							}
									    
							File file = new File(extStorage, "Frame_tem.JPEG");
								    
							try {
								OutputStream outStream = new FileOutputStream(file);
								modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
								outStream.flush();
								outStream.close();
								/*Toast.makeText(Fashion.this, 
								extStorage+"/Effect_tem.JPEG", 
								Toast.LENGTH_LONG).show();*/
							} 
							catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Toast.makeText(SpecialFrame.this, 
								e.toString(), 
								Toast.LENGTH_LONG).show();
							} 
							catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Toast.makeText(SpecialFrame.this, 
								e.toString(), 
								Toast.LENGTH_LONG).show();
							}
									 
							imgView1.setImageBitmap(modified);
							ProgressBar_Special.setVisibility(View.GONE);
						}
					});
				 }
			});
		    threadSpecial.start();
			}
    	});
    	
    	frame6 = (ImageView)findViewById(R.id.frame6);
    	
    	if(content.charAt(NUMBER_SIMPLE + 2) == '1'){ 
    		frame6.setVisibility(View.VISIBLE);	        
        }
    	
    	frame6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				ProgressBar_Special.setVisibility(View.VISIBLE);
				threadSpecial = new Thread(new Runnable(){
					 public void run(){
						 modified = specialFrame6(bmp1);
						 handlerSpecial.post(new Runnable(){
							public void run(){
								File tem_file1 = new File(extStorage, "Frame_tem.JPEG");
								if(tem_file1.exists())
								{
									boolean deleted = tem_file1.delete();
								}
									    
								File file = new File(extStorage, "Frame_tem.JPEG");
								    
								try {
									OutputStream outStream = new FileOutputStream(file);
									modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
									outStream.flush();
									outStream.close();
									/*Toast.makeText(Fashion.this, 
								    extStorage+"/Effect_tem.JPEG", 
								    Toast.LENGTH_LONG).show();*/
								} 
								catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SpecialFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								} 
								catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SpecialFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								}
										
								imgView1.setImageBitmap(modified);
								ProgressBar_Special.setVisibility(View.GONE);
							}
						 });
					 }
				});
				threadSpecial.start();		
			}
		});



    	ImageView ok = (ImageView) findViewById(R.id.frame_check);
		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String extStorage = Environment.getExternalStorageDirectory().toString();
				
				File tem_file1 = new File(extStorage, "Frame_tem.JPEG");
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
			        /*Toast.makeText(FrameActivity.this,
			          extStorage+"/Retouch_tem.JPEG",
			          Toast.LENGTH_LONG).show();*/
			    } catch (FileNotFoundException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(SpecialFrame.this,
			          e.toString(),
			          Toast.LENGTH_LONG).show();
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(SpecialFrame.this,
			          e.toString(),
			          Toast.LENGTH_LONG).show();
			    }

			    Intent intent = new Intent(SpecialFrame.this, Retouch.class);
			    Bundle bundle = SpecialFrame.this.getIntent().getExtras();
			    intent.putExtras(bundle);
				startActivity(intent);
				//finish();
			}
		});

		ImageView cancel = (ImageView) findViewById(R.id.frame_error);
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File tem_file1 = new File(extStorage, "Frame_tem.JPEG");
				if(tem_file1.exists())
			    {
					boolean deleted = tem_file1.delete();
				}
				
				Intent intent = new Intent(SpecialFrame.this, Retouch.class);
				Bundle bundle = SpecialFrame.this.getIntent().getExtras();
			    intent.putExtras(bundle);
				startActivity(intent);
				//finish();
			}
		});
    }

    public Bitmap resizeBitmap(Bitmap bm, float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
    }

    public Bitmap resizeBitmap(Bitmap bm, int w, int h) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) w) / width;
        float scaleHeight = ((float) h) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }

    
    //specialFrame1
    private Bitmap specialFrame1(Bitmap bm) {
    	int width = bm.getWidth();
        int height = bm.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // 边框图片
        Bitmap lay = BitmapFactory.decodeResource(getResources(), R.drawable.specialframe1);
        int w = lay.getWidth();
        int h = lay.getHeight();
        float scaleX = width * 1F / w;
        float scaleY = height * 1F / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);

        Bitmap newlay = Bitmap.createBitmap(lay, 0, 0, w, h, matrix, true);
        lay.recycle();
        lay = null;

        int pixColor = 0;
        int layColor = 0;
        int newColor = 0;

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

        float alpha= 0.3F;
        float alphaR = 0F;
        float alphaG = 0F;
        float alphaB = 0F;
        for (int i = 0; i < width; i++)
        {
            for (int k = 0; k < height; k++)
            {
                pixColor = bm.getPixel(i, k);
                layColor = newlay.getPixel(i, k);

                // 获取原图片的RGBA值
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                pixA = Color.alpha(pixColor);

                // 获取边框图片的RGBA值
                layR = Color.red(layColor);
                layG = Color.green(layColor);
                layB = Color.blue(layColor);
                layA = Color.alpha(layColor);

                // 颜色与纯黑色相近的点
                if (layR > 250 && layG > 250 && layB > 250)
                {
                    alpha = 1F;
                }
                else
                {
                    alpha = 0.3F;
                }

                alphaR = alpha;
                alphaG = alpha;
                alphaB = alpha;

                // 两种颜色叠加
                newR = (int) (pixR * alphaR + layR * (1 - alphaR));
                newG = (int) (pixG * alphaG + layG * (1 - alphaG));
                newB = (int) (pixB * alphaB + layB * (1 - alphaB));
                layA = (int) (pixA * alpha + layA * (1 - alpha));

                // 值在0~255之间
                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));
                newA = Math.min(255, Math.max(0, layA));

                newColor = Color.argb(newA, newR, newG, newB);
                newBitmap.setPixel(i, k, newColor);
            }
        }

        return newBitmap;
    }

    //specialFrame2
    private Bitmap specialFrame2(Bitmap bm) {
    	int width = bm.getWidth();
        int height = bm.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // 边框图片
        Bitmap lay = BitmapFactory.decodeResource(getResources(), R.drawable.specialframe2);
        int w = lay.getWidth();
        int h = lay.getHeight();
        float scaleX = width * 1F / w;
        float scaleY = height * 1F / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);

        Bitmap newlay = Bitmap.createBitmap(lay, 0, 0, w, h, matrix, true);
        lay.recycle();
        lay = null;

        int pixColor = 0;
        int layColor = 0;
        int newColor = 0;

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

        float alpha= 0.3F;
        float alphaR = 0F;
        float alphaG = 0F;
        float alphaB = 0F;
        for (int i = 0; i < width; i++)
        {
            for (int k = 0; k < height; k++)
            {
                pixColor = bm.getPixel(i, k);
                layColor = newlay.getPixel(i, k);

                // 获取原图片的RGBA值
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                pixA = Color.alpha(pixColor);

                // 获取边框图片的RGBA值
                layR = Color.red(layColor);
                layG = Color.green(layColor);
                layB = Color.blue(layColor);
                layA = Color.alpha(layColor);

                // 颜色与纯黑色相近的点
                if (layR < 5 && layG < 5 && layB < 5)
                {
                    alpha = 1F;
                }
                else
                {
                    alpha = 0.3F;
                }

                alphaR = alpha;
                alphaG = alpha;
                alphaB = alpha;

                // 两种颜色叠加
                newR = (int) (pixR * alphaR + layR * (1 - alphaR));
                newG = (int) (pixG * alphaG + layG * (1 - alphaG));
                newB = (int) (pixB * alphaB + layB * (1 - alphaB));
                layA = (int) (pixA * alpha + layA * (1 - alpha));

                // 值在0~255之间
                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));
                newA = Math.min(255, Math.max(0, layA));

                newColor = Color.argb(newA, newR, newG, newB);
                newBitmap.setPixel(i, k, newColor);
            }
        }

        return newBitmap;
    }
    
    //specialFrame3
    private Bitmap specialFrame3(Bitmap bm) {
    	int width = bm.getWidth();
        int height = bm.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // 边框图片
        Bitmap lay = BitmapFactory.decodeResource(getResources(), R.drawable.specialframe3);
        int w = lay.getWidth();
        int h = lay.getHeight();
        float scaleX = width * 1F / w;
        float scaleY = height * 1F / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);

        Bitmap newlay = Bitmap.createBitmap(lay, 0, 0, w, h, matrix, true);
        lay.recycle();
        lay = null;

        int pixColor = 0;
        int layColor = 0;
        int newColor = 0;

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

        float alpha= 0.3F;
        float alphaR = 0F;
        float alphaG = 0F;
        float alphaB = 0F;
        for (int i = 0; i < width; i++)
        {
            for (int k = 0; k < height; k++)
            {
                pixColor = bm.getPixel(i, k);
                layColor = newlay.getPixel(i, k);

                // 获取原图片的RGBA值
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                pixA = Color.alpha(pixColor);

                // 获取边框图片的RGBA值
                layR = Color.red(layColor);
                layG = Color.green(layColor);
                layB = Color.blue(layColor);
                layA = Color.alpha(layColor);

                // 颜色与纯黑色相近的点
                if (layR > 250 && layG > 250 && layB > 250)
                {
                    alpha = 1F;
                }
                else
                {
                    alpha = 0.3F;
                }

                alphaR = alpha;
                alphaG = alpha;
                alphaB = alpha;

                // 两种颜色叠加
                newR = (int) (pixR * alphaR + layR * (1 - alphaR));
                newG = (int) (pixG * alphaG + layG * (1 - alphaG));
                newB = (int) (pixB * alphaB + layB * (1 - alphaB));
                layA = (int) (pixA * alpha + layA * (1 - alpha));

                // 值在0~255之间
                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));
                newA = Math.min(255, Math.max(0, layA));

                newColor = Color.argb(newA, newR, newG, newB);
                newBitmap.setPixel(i, k, newColor);
            }
        }

        return newBitmap;
    }

    //specialFrame4
    private Bitmap specialFrame4(Bitmap bm) {
    	int width = bm.getWidth();
        int height = bm.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // 边框图片
        Bitmap lay = BitmapFactory.decodeResource(getResources(), R.drawable.specialframe4);
        int w = lay.getWidth();
        int h = lay.getHeight();
        float scaleX = width * 1F / w;
        float scaleY = height * 1F / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);

        Bitmap newlay = Bitmap.createBitmap(lay, 0, 0, w, h, matrix, true);
        lay.recycle();
        lay = null;

        int pixColor = 0;
        int layColor = 0;
        int newColor = 0;

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

        float alpha= 0.3F;
        float alphaR = 0F;
        float alphaG = 0F;
        float alphaB = 0F;
        for (int i = 0; i < width; i++)
        {
            for (int k = 0; k < height; k++)
            {
                pixColor = bm.getPixel(i, k);
                layColor = newlay.getPixel(i, k);

                // 获取原图片的RGBA值
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                pixA = Color.alpha(pixColor);

                // 获取边框图片的RGBA值
                layR = Color.red(layColor);
                layG = Color.green(layColor);
                layB = Color.blue(layColor);
                layA = Color.alpha(layColor);

                // 颜色与纯黑色相近的点
                if (layR < 5 && layG < 5 && layB < 5)
                {
                    alpha = 1F;
                }
                else
                {
                    alpha = 0.8F;
                }

                alphaR = alpha;
                alphaG = alpha;
                alphaB = alpha;

                // 两种颜色叠加
                newR = (int) (pixR * alphaR + layR * (1 - alphaR));
                newG = (int) (pixG * alphaG + layG * (1 - alphaG));
                newB = (int) (pixB * alphaB + layB * (1 - alphaB));
                layA = (int) (pixA * alpha + layA * (1 - alpha));

                // 值在0~255之间
                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));
                newA = Math.min(255, Math.max(0, layA));

                newColor = Color.argb(newA, newR, newG, newB);
                newBitmap.setPixel(i, k, newColor);
            }
        }

        return newBitmap;
    }
    
    //specialFrame5
    private Bitmap specialFrame5(Bitmap bm) {
    	int width = bm.getWidth();
        int height = bm.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // 边框图片
        Bitmap lay = BitmapFactory.decodeResource(getResources(), R.drawable.specialframe5);
        int w = lay.getWidth();
        int h = lay.getHeight();
        float scaleX = width * 1F / w;
        float scaleY = height * 1F / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);

        Bitmap newlay = Bitmap.createBitmap(lay, 0, 0, w, h, matrix, true);
        lay.recycle();
        lay = null;

        int pixColor = 0;
        int layColor = 0;
        int newColor = 0;

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

        float alpha= 0.3F;
        float alphaR = 0F;
        float alphaG = 0F;
        float alphaB = 0F;
        for (int i = 0; i < width; i++)
        {
            for (int k = 0; k < height; k++)
            {
                pixColor = bm.getPixel(i, k);
                layColor = newlay.getPixel(i, k);

                // 获取原图片的RGBA值
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                pixA = Color.alpha(pixColor);

                // 获取边框图片的RGBA值
                layR = Color.red(layColor);
                layG = Color.green(layColor);
                layB = Color.blue(layColor);
                layA = Color.alpha(layColor);

                // 颜色与纯黑色相近的点
                if (layR < 5 && layG < 5 && layB < 5)
                {
                    alpha = 1F;
                }
                else
                {
                    alpha = 0.8F;
                }

                alphaR = alpha;
                alphaG = alpha;
                alphaB = alpha;

                // 两种颜色叠加
                newR = (int) (pixR * alphaR + layR * (1 - alphaR));
                newG = (int) (pixG * alphaG + layG * (1 - alphaG));
                newB = (int) (pixB * alphaB + layB * (1 - alphaB));
                layA = (int) (pixA * alpha + layA * (1 - alpha));

                // 值在0~255之间
                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));
                newA = Math.min(255, Math.max(0, layA));

                newColor = Color.argb(newA, newR, newG, newB);
                newBitmap.setPixel(i, k, newColor);
            }
        }

        return newBitmap;
    }
    
    //specialFrame6
    private Bitmap specialFrame6(Bitmap bm) {
    	int width = bm.getWidth();
        int height = bm.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // 边框图片
        Bitmap lay = BitmapFactory.decodeResource(getResources(), R.drawable.specialframe6);
        int w = lay.getWidth();
        int h = lay.getHeight();
        float scaleX = width * 1F / w;
        float scaleY = height * 1F / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);
        
        Bitmap newlay = Bitmap.createBitmap(lay, 0, 0, w, h, matrix, true);
        lay.recycle();
        lay = null;


        int pixColor = 0;
        int layColor = 0;
        int newColor = 0;

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

        float alpha= 0.3F;
        float alphaR = 0F;
        float alphaG = 0F;
        float alphaB = 0F;
        for (int i = 0; i < width; i++)
        {
            for (int k = 0; k < height; k++)
            {
                pixColor = bm.getPixel(i, k);
                layColor = newlay.getPixel(i, k);

                // 获取原图片的RGBA值
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                pixA = Color.alpha(pixColor);

                // 获取边框图片的RGBA值
                layR = Color.red(layColor);
                layG = Color.green(layColor);
                layB = Color.blue(layColor);
                layA = Color.alpha(layColor);

                // 颜色与纯黑色相近的点
                if (layR < 5 && layG < 5 && layB < 5)
                {
                    alpha = 1F;
                }
                else
                {
                    alpha = 0.5F;
                }

                alphaR = alpha;
                alphaG = alpha;
                alphaB = alpha;

                // 两种颜色叠加
                newR = (int) (pixR * alphaR + layR * (1 - alphaR));
                newG = (int) (pixG * alphaG + layG * (1 - alphaG));
                newB = (int) (pixB * alphaB + layB * (1 - alphaB));
                layA = (int) (pixA * alpha + layA * (1 - alpha));

                // 值在0~255之间
                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));
                newA = Math.min(255, Math.max(0, layA));

                newColor = Color.argb(newA, newR, newG, newB);
                newBitmap.setPixel(i, k, newColor);
            }
        }

        return newBitmap;
    }
}


