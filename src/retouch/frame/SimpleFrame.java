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
import android.graphics.Canvas;
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
import com.pictoaster.www.retouch.effect.Fashion;
import com.pictoaster.www.retouch.effect.Lomo;

public class SimpleFrame extends Activity {
	private ProgressBar ProgressBar_Simple;
	private ImageView imgView1;
	private ImageView frame1;
	private ImageView frame2;
	private ImageView frame3;
	private ImageView frame4;
	private ImageView frame5;
	private ImageView frame6;
	private ImageView frame7;
	private ImageView frame8;
	private ImageView frame9;
	private ImageView frame10;
	private ImageView frame11;
	
	private Bitmap bmp1;
	private Bitmap modified;
	Thread threadSimple;
	Handler handlerSimple = new Handler();
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
        setContentView(R.layout.retouch_frame);

        /*Bundle fieldresults = this.getIntent().getExtras();
	    String path = fieldresults.getString("bm");
	    final Bitmap bmp1 = BitmapFactory.decodeFile(path);
	    modified = bmp1.copy(Bitmap.Config.ARGB_8888, true);
	    imgView1 = (ImageView)findViewById(R.id.imgView1);
    	imgView1.setImageBitmap(bmp1);*/
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
        //modified = bmp1.copy(Bitmap.Config.ARGB_8888, true);
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
                /*Toast.makeText(SimpleFrame.this, "Read Success",
                        Toast.LENGTH_LONG).show();*/
            } catch (Exception e) {
                Toast.makeText(SimpleFrame.this, "Read Failed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            //sdcard doesn't exist or can't read it right now
            Toast.makeText(SimpleFrame.this,
                    "Can't Read", Toast.LENGTH_SHORT).show();
        }
		
		if(content.isEmpty())
		{
			content = "0000000000000000";
		}
	    
	    ProgressBar_Simple = (ProgressBar) findViewById(R.id.simpleBar);
    	
    	frame1 = (ImageView)findViewById(R.id.frame1);
    	frame1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()) {
					modified.recycle();
					modified = null;
				}*/
				ProgressBar_Simple.setVisibility(View.VISIBLE);
				threadSimple = new Thread(new Runnable(){
					 public void run(){
						 modified = outerFrame1(bmp1);
						 handlerSimple.post(new Runnable(){
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
											Toast.makeText(SimpleFrame.this, 
													e.toString(), 
													Toast.LENGTH_LONG).show();
										} 
								    catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											Toast.makeText(SimpleFrame.this, 
													e.toString(), 
													Toast.LENGTH_LONG).show();
										}
										
									imgView1.setImageBitmap(modified);
								    ProgressBar_Simple.setVisibility(View.GONE);
								 }
						 });
					 }
				});
				threadSimple.start();	
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
				ProgressBar_Simple.setVisibility(View.VISIBLE);
				threadSimple = new Thread(new Runnable(){
					 public void run(){
						 modified = outerFrame2(bmp1);
						 handlerSimple.post(new Runnable(){
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
										Toast.makeText(SimpleFrame.this, 
										e.toString(), 
										Toast.LENGTH_LONG).show();
								} 
								catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										Toast.makeText(SimpleFrame.this, 
										e.toString(), 
										Toast.LENGTH_LONG).show();
								}
								
								imgView1.setImageBitmap(modified);
								ProgressBar_Simple.setVisibility(View.GONE);
							}
						 });
					 }
				});
				threadSimple.start();		
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
				ProgressBar_Simple.setVisibility(View.VISIBLE);
				threadSimple = new Thread(new Runnable(){
					 public void run(){
						 modified = outerFrame3(bmp1);
						 handlerSimple.post(new Runnable(){
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
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								} 
								catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								}
										
									imgView1.setImageBitmap(modified);
									ProgressBar_Simple.setVisibility(View.GONE);
							}
						 });
					 }
				});
				threadSimple.start();	
			}
		});
    	
    	
    	frame4 = (ImageView)findViewById(R.id.frame4);
    	
    	if(content.charAt(0) == '1'){
    		frame4.setVisibility(View.VISIBLE); 
    	}
    	
    	frame4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				ProgressBar_Simple.setVisibility(View.VISIBLE);
				threadSimple = new Thread(new Runnable(){
					 public void run(){
						 modified = outerFrame4(bmp1);
						 handlerSimple.post(new Runnable(){
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
								Toast.makeText(SimpleFrame.this, 
								e.toString(), 
								Toast.LENGTH_LONG).show();
							} 
							catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Toast.makeText(SimpleFrame.this, 
								e.toString(), 
								Toast.LENGTH_LONG).show();
							}
										
								imgView1.setImageBitmap(modified);
								ProgressBar_Simple.setVisibility(View.GONE);
							}
						 });
					 }
				});
				threadSimple.start();
			}
		});
    	
    	frame5 = (ImageView)findViewById(R.id.frame5);
    	
    	if(content.charAt(1) == '1'){
    		frame5.setVisibility(View.VISIBLE); 
    	}
    	
    	frame5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				
				ProgressBar_Simple.setVisibility(View.VISIBLE);
				threadSimple = new Thread(new Runnable(){
					 public void run(){
						 modified = outerFrame5(bmp1);
						 handlerSimple.post(new Runnable(){
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
									//Toast.makeText(Fashion.this, 
								    //extStorage+"/Effect_tem.JPEG", 
								    //Toast.LENGTH_LONG).show();*/
								} 
								catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								} 
								catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								}
										
									imgView1.setImageBitmap(modified);
									ProgressBar_Simple.setVisibility(View.GONE);
							}
						 });
					 }
				});
				threadSimple.start();
			}
		});
    	
    	frame6 = (ImageView)findViewById(R.id.frame6);
    	frame6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				
				ProgressBar_Simple.setVisibility(View.VISIBLE);
				threadSimple = new Thread(new Runnable(){
					 public void run(){
						 modified = outerFrame6(bmp1);
						 handlerSimple.post(new Runnable(){
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
									//Toast.makeText(Fashion.this, 
								    //extStorage+"/Effect_tem.JPEG", 
								    //Toast.LENGTH_LONG).show();*/
								} 
								catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								} 
								catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								}
										
									imgView1.setImageBitmap(modified);
									ProgressBar_Simple.setVisibility(View.GONE);
							}
						 });
					 }
				});
				threadSimple.start();
			}
		});
    	
    	frame7 = (ImageView)findViewById(R.id.frame7);
    	frame7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				
				ProgressBar_Simple.setVisibility(View.VISIBLE);
				threadSimple = new Thread(new Runnable(){
					 public void run(){
						 modified = outerFrame7(bmp1);
						 handlerSimple.post(new Runnable(){
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
									//Toast.makeText(Fashion.this, 
								    //extStorage+"/Effect_tem.JPEG", 
								    //Toast.LENGTH_LONG).show();*/
								} 
								catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								} 
								catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								}
										
									imgView1.setImageBitmap(modified);
									ProgressBar_Simple.setVisibility(View.GONE);
							}
						 });
					 }
				});
				threadSimple.start();
			}
		});
    	
    	frame8 = (ImageView)findViewById(R.id.frame8);
    	if(content.charAt(2) == '1'){
    		frame8.setVisibility(View.VISIBLE); 
    	}
    	frame8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				
				ProgressBar_Simple.setVisibility(View.VISIBLE);
				threadSimple = new Thread(new Runnable(){
					 public void run(){
						 modified = outerFrame8(bmp1);
						 handlerSimple.post(new Runnable(){
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
									//Toast.makeText(Fashion.this, 
								    //extStorage+"/Effect_tem.JPEG", 
								    //Toast.LENGTH_LONG).show();*/
								} 
								catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								} 
								catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								}
										
									imgView1.setImageBitmap(modified);
									ProgressBar_Simple.setVisibility(View.GONE);
							}
						 });
					 }
				});
				threadSimple.start();
			}
		});
    	
    	frame9 = (ImageView)findViewById(R.id.frame9);
    	if(content.charAt(3) == '1'){
    		frame9.setVisibility(View.VISIBLE); 
    	}
    	frame9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				
				ProgressBar_Simple.setVisibility(View.VISIBLE);
				threadSimple = new Thread(new Runnable(){
					 public void run(){
						 modified = outerFrame9(bmp1);
						 handlerSimple.post(new Runnable(){
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
									//Toast.makeText(Fashion.this, 
								    //extStorage+"/Effect_tem.JPEG", 
								    //Toast.LENGTH_LONG).show();*/
								} 
								catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								} 
								catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								}
										
									imgView1.setImageBitmap(modified);
									ProgressBar_Simple.setVisibility(View.GONE);
							}
						 });
					 }
				});
				threadSimple.start();
			}
		});
    	
    	frame10 = (ImageView)findViewById(R.id.frame10);
    	if(content.charAt(4) == '1'){
    		frame10.setVisibility(View.VISIBLE); 
    	}
    	frame10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				
				ProgressBar_Simple.setVisibility(View.VISIBLE);
				threadSimple = new Thread(new Runnable(){
					 public void run(){
						 modified = outerFrame10(bmp1);
						 handlerSimple.post(new Runnable(){
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
									//Toast.makeText(Fashion.this, 
								    //extStorage+"/Effect_tem.JPEG", 
								    //Toast.LENGTH_LONG).show();*/
								} 
								catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								} 
								catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								}
										
									imgView1.setImageBitmap(modified);
									ProgressBar_Simple.setVisibility(View.GONE);
							}
						 });
					 }
				});
				threadSimple.start();
			}
		});
    	
    	frame11 = (ImageView)findViewById(R.id.frame11);
    	frame11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*if(modified != null && !modified.isRecycled()){
					modified.recycle();
					modified = null;
				}*/
				
				ProgressBar_Simple.setVisibility(View.VISIBLE);
				threadSimple = new Thread(new Runnable(){
					 public void run(){
						 modified = outerFrame11(bmp1);
						 handlerSimple.post(new Runnable(){
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
									//Toast.makeText(Fashion.this, 
								    //extStorage+"/Effect_tem.JPEG", 
								    //Toast.LENGTH_LONG).show();*/
								} 
								catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								} 
								catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									Toast.makeText(SimpleFrame.this, 
									e.toString(), 
									Toast.LENGTH_LONG).show();
								}
										
									imgView1.setImageBitmap(modified);
									ProgressBar_Simple.setVisibility(View.GONE);
							}
						 });
					 }
				});
				threadSimple.start();
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
			     Toast.makeText(SimpleFrame.this,
			          e.toString(),
			          Toast.LENGTH_LONG).show();
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(SimpleFrame.this,
			          e.toString(),
			          Toast.LENGTH_LONG).show();
			    }

			    Intent intent = new Intent(SimpleFrame.this, Retouch.class);
			    Bundle bundle = SimpleFrame.this.getIntent().getExtras();
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
				
				Intent intent = new Intent(SimpleFrame.this, Retouch.class);
				Bundle bundle = SimpleFrame.this.getIntent().getExtras();
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


    //outerFrame1
    private Bitmap outerFrame1(Bitmap obm)
    {
    	Bitmap newBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.outerframe1);
    	
    	int newWidth = obm.getWidth() * 500 / 390;
    	int newHeight = obm.getHeight() * 500 / 313;
        newBitmap = resizeBitmap(newBitmap, newWidth, newHeight);
        
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(obm, newWidth * 57 / 500, newHeight * 90 / 500, null);
        
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        
        return newBitmap;
    	
    }

    //outerFrame2
    private Bitmap outerFrame2(Bitmap obm)
    { 
    	Bitmap newBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.outerframe2);
    	
    	int newWidth = obm.getWidth() * 500 / 384;
    	int newHeight = obm.getHeight() * 497 / 345;
        newBitmap = resizeBitmap(newBitmap, newWidth, newHeight);
        
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(obm, newWidth * 54 / 500, newHeight * 74 / 497, null);
        
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        
        return newBitmap;
        
    }

    //outerFrame3
    private Bitmap outerFrame3(Bitmap obm)
    {
    	
    	Bitmap newBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.outerframe3);
    	
    	int newWidth = obm.getWidth() * 500 / 360;
    	int newHeight = obm.getHeight() * 500 / 301;
        newBitmap = resizeBitmap(newBitmap, newWidth, newHeight);
        
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(obm, newWidth * 67 / 500, newHeight * 96 / 500, null);
        
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        
        return newBitmap;
    }
    
    //outerFrame4
    private Bitmap outerFrame4(Bitmap obm)
    {
    	Bitmap newBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.outerframe4);
    	
    	int newWidth = obm.getWidth() * 500 / 349;
    	int newHeight = obm.getHeight() * 500 / 264;
        newBitmap = resizeBitmap(newBitmap, newWidth, newHeight);
        
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(obm, newWidth * 73 / 500, newHeight * 120 / 500, null);
        
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        
        return newBitmap;
    }
      
    //outerFrame5
    private Bitmap outerFrame5(Bitmap obm)
    {
    	Bitmap newBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.outerframe5);
    	
    	int newWidth = obm.getWidth() * 500 / 268;
    	int newHeight = obm.getHeight() * 500 / 272;
        newBitmap = resizeBitmap(newBitmap, newWidth, newHeight);
        
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(obm, newWidth * 118 / 500, newHeight * 115 / 500, null);
        
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        
        return newBitmap;
    }
    
    //outerFrame6
    private Bitmap outerFrame6(Bitmap obm) {
    	Bitmap newBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.outerframe6);
        
    	int newWidth = obm.getWidth() * 264 / 198;
    	int newHeight = obm.getHeight() * 379 / 316;
        newBitmap = resizeBitmap(newBitmap, newWidth, newHeight);
        
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(obm, newWidth * 34 / 264, newHeight * 32 / 379, null);
        
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        
        return newBitmap;
    }

    //outerFrame7
    private Bitmap outerFrame7(Bitmap obm) {
    	Bitmap newBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.outerframe7);
        
    	int newWidth = obm.getWidth() * 265 / 198;
    	int newHeight = obm.getHeight() * 379 / 316;
        newBitmap = resizeBitmap(newBitmap, newWidth, newHeight);
        
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(obm, newWidth * 34 / 265, newHeight * 32 / 379, null);
        
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        
        return newBitmap;
    }
    
    //outerFrame8
    private Bitmap outerFrame8(Bitmap obm) {
    	Bitmap newBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.outerframe8);
        
    	int newWidth = obm.getWidth() * 265 / 198;
    	int newHeight = obm.getHeight() * 379 / 316;
        newBitmap = resizeBitmap(newBitmap, newWidth, newHeight);
        
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(obm, newWidth * 34 / 265, newHeight * 32 / 379, null);
        
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        
        return newBitmap;
    }
    
    //outerFrame9
    private Bitmap outerFrame9(Bitmap obm) {
    	Bitmap newBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.outerframe9);
        
    	int newWidth = obm.getWidth() * 265 / 198;
    	int newHeight = obm.getHeight() * 379 / 316;
        newBitmap = resizeBitmap(newBitmap, newWidth, newHeight);
        
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(obm, newWidth * 34 / 265, newHeight * 32 / 379, null);
        
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        
        return newBitmap;
    }

    //outerFrame10
    private Bitmap outerFrame10(Bitmap obm) {
    	Bitmap newBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.outerframe10);
    	
    	final int smallW = obm.getWidth() / 20;
        final int smallH = obm.getHeight() / 20;
        
        newBitmap = resizeBitmap(newBitmap, smallW * 22, smallH * 22);
        
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(obm, smallW, smallH, null);
        
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        
        return newBitmap;
    }

    //outerFrame11
    private Bitmap outerFrame11(Bitmap obm) {
    	Bitmap newBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.outerframe11);
        
    	int newWidth = obm.getWidth() * 500 / 454;
    	int newHeight = obm.getHeight() * 800 / 642;
        newBitmap = resizeBitmap(newBitmap, newWidth, newHeight);
        
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(obm, newWidth * 23 / 500, newHeight * 28 / 800, null);
        
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        
        return newBitmap;
    }
    
}


