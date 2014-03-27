package com.pictoaster.www.retouch.text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pictoaster.www.R;
import com.pictoaster.www.retouch.Retouch;

public class Text extends Activity {
	/** Called when the activity is first created. */

    Context context;
	int x,y,bubb;
	Button btnEditText;
	Button btnBubble;
	ImageView btnConfirm;
	ImageView btnCancel;
    private Button btnColorPicker;
    private TextView tvText;
    private Picker dialog;
    private View view;
    
    String path;
    int download;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {

		context = this;
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.retouch_text);
		Bundle bundle = getIntent().getExtras();
		
		bubb = bundle.getInt("bub");
		path = bundle.getString("bm");
		download = bundle.getInt("download");
		
		final Bitmap bmp1 = BitmapFactory.decodeFile(path);
        final ImageView imgView1 = (ImageView)findViewById(R.id.img);
    	imgView1.setImageBitmap(bmp1);
		
		final TextView result = (TextView) findViewById(R.id.draw);
    	result.setText(bundle.getString("text"));
    	result.setTextSize(22);
    	result.setScaleX(bundle.getFloat("scaleX"));
    	result.setScaleY(bundle.getFloat("scaleY"));
    	
		result.setTextColor(bundle.getInt("color"));
		result.setLeft(bundle.getInt("l"));
		result.setRight(bundle.getInt("r"));
		result.setTop(bundle.getInt("t"));
		result.setBottom(bundle.getInt("b"));
		
		result.setGravity(Gravity.CENTER);
		
		
		/*result.setOnClickListener(new Button.OnClickListener(){
 		   @Override
 		   public void onClick(View v) {
				bubb += 1;
				addBubble((bubb) % 6);
 		   }
 		});*/
						
		tvText = result;
				
		dragText();
		
        changeColor();
        
        animation();
        
        addBubble(bubb);
        	
		btnEditText = (Button)findViewById(R.id.btn_edit_text);
		btnEditText.setOnTouchListener(new Button.OnTouchListener(){
			   @Override
			   public boolean onTouch(View v, MotionEvent event) {
			    if(event.getAction() == MotionEvent.ACTION_DOWN){   
			                    v.setBackgroundColor(Color.LTGRAY);   
			                    Log.i("TestAndroid Button", "MotionEvent.ACTION_DOWN");
			                }   
			                else if(event.getAction() == MotionEvent.ACTION_UP){   
			                    v.setBackgroundColor(Color.TRANSPARENT); 
			                    Log.i("TestAndroid Button", "MotionEvent.ACTION_UP");
			                } 
			    return false;
			   }
		});
		
        btnEditText.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					Intent intent = new Intent(Text.this, EditT.class); 
	        		Bundle bundle1 = new Bundle();
	                bundle1.putString("tText", result.getText().toString());
	                bundle1.putFloat("tScaleX", tvText.getScaleX());
	                bundle1.putFloat("tScaleY", tvText.getScaleY());
	                bundle1.putInt("tColor", tvText.getCurrentTextColor());
	                bundle1.putInt("tl", tvText.getLeft());
	                bundle1.putInt("tr", tvText.getRight());
	                bundle1.putInt("tt", tvText.getTop());
	                bundle1.putInt("tb", tvText.getBottom());
	                bundle1.putInt("tBub", bubb);
	                
	                bundle1.putString("bm", path);                
	                bundle1.putInt("download", download);
	                intent.putExtras(bundle1);
	                startActivity(intent);
	                //finish();
				
				
			}             
        });
        
        btnBubble = (Button)findViewById(R.id.btn_bubble);
        
        btnBubble.setOnTouchListener(new Button.OnTouchListener(){
			   @Override
			   public boolean onTouch(View v, MotionEvent event) {
			    if(event.getAction() == MotionEvent.ACTION_DOWN){   
			                    v.setBackgroundColor(Color.LTGRAY);   
			                    Log.i("TestAndroid Button", "MotionEvent.ACTION_DOWN");
			                }   
			                else if(event.getAction() == MotionEvent.ACTION_UP){   
			                    v.setBackgroundColor(Color.TRANSPARENT); 
			                    Log.i("TestAndroid Button", "MotionEvent.ACTION_UP");
			                } 
			    return false;
			   }
		});
        
        btnBubble.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(result.getText() == null) {
					Intent intent = new Intent(Text.this, NoText.class);
					startActivity(intent);
				}
				
				else {
					Intent intent = new Intent(Text.this, Bubble.class);
					Bundle bundle = new Bundle();
					
	                bundle.putString("bm", path);
	               
	                bundle.putInt("download", download);
	                bundle.putInt("tBub", bubb);
	                bundle.putString("tText", result.getText().toString());
	                bundle.putFloat("tScaleX", tvText.getScaleX());
	                bundle.putFloat("tScaleY", tvText.getScaleY());
	                bundle.putInt("tColor", tvText.getCurrentTextColor());
	                bundle.putInt("tl", tvText.getLeft());
	                bundle.putInt("tr", tvText.getRight());
	                bundle.putInt("tt", tvText.getTop());
	                bundle.putInt("tb", tvText.getBottom());
	                intent.putExtras(bundle);
					startActivity(intent);
				}
				
			}
        });
        
        ImageView ok = (ImageView) findViewById(R.id.text_check);
		ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String extStorage = Environment.getExternalStorageDirectory().toString();
				
				File tem_file = new File(extStorage, "Retouch_tem.JPEG");
			    if(tem_file.exists())
			    {
			    	boolean deleted = tem_file.delete();
			    }
			    
			    File file = new File(extStorage, "Retouch_tem.JPEG");
			    
			    view = (RelativeLayout)findViewById(R.id.confirm);
				view.setDrawingCacheEnabled(true);
				view.buildDrawingCache();
				
				Bitmap modified = view.getDrawingCache();
			     
			    try {
			     OutputStream outStream = new FileOutputStream(file);
			     modified.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
			     outStream.flush();
			        outStream.close();
			        /*Toast.makeText(Text.this, 
			          extStorage+"/Retouch_tem.JPEG", 
			          Toast.LENGTH_LONG).show();*/
			    } catch (FileNotFoundException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Text.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    } catch (IOException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			     Toast.makeText(Text.this, 
			          e.toString(), 
			          Toast.LENGTH_LONG).show();
			    }
			    
			    Intent intent = new Intent(Text.this, Retouch.class);
			    Bundle bundle = Text.this.getIntent().getExtras();
				download = bundle.getInt("download");
				intent.putExtras(bundle);
				if(modified.isRecycled()==false) {
					modified.recycle();
					System.gc();
				}
				startActivity(intent);
				System.exit(0);
			}
		});
		
		ImageView cancel = (ImageView) findViewById(R.id.text_error);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Text.this, Retouch.class);
				Bundle bundle = Text.this.getIntent().getExtras();
				download = bundle.getInt("download");
				intent.putExtras(bundle);
				result.setText(null);
				startActivity(intent);
				System.gc();
				System.exit(0);
			}
		});
    }
	
	private void dragText() {
		tvText.setClickable(true);
		tvText.setOnTouchListener(new OnTouchListener() {
			int[] temp = new int[] { 0, 0 };

			public boolean onTouch(View v, MotionEvent event) {

				int eventaction = event.getAction();

				x = (int) event.getRawX();
				y = (int) event.getRawY();

				switch (eventaction) {

				case MotionEvent.ACTION_DOWN: // touch down so check if the
					temp[0] = (int) event.getX();
					temp[1] = y - v.getTop();
					break;

				case MotionEvent.ACTION_MOVE: // touch drag with the ball
					v.layout(x - temp[0], y - temp[1], x + v.getWidth()
							- temp[0], y - temp[1] + v.getHeight());
//					v.postInvalidate();
					break;

				case MotionEvent.ACTION_UP:
					break;
				} 

				return false;
			}

		});
	}
	
	private void addBubble(int b) {
		Resources resources = this.getBaseContext().getResources();
		switch(b) {
			case 0:  
				tvText.setBackgroundColor(Color.TRANSPARENT);
	        break;
	        
			case 1:  
		        Drawable dra = resources.getDrawable(R.drawable.bub1);  
		        tvText.setBackgroundDrawable(dra);
		        break;
		        
			case 2:  
		        dra = resources.getDrawable(R.drawable.bub2);  
		        tvText.setBackgroundDrawable(dra);
		        break;
		        
			case 3:  
		        dra = resources.getDrawable(R.drawable.bub3);  
		        tvText.setBackgroundDrawable(dra);
		        break;
		        
			case 4:  
		        dra = resources.getDrawable(R.drawable.bub4);  
		        tvText.setBackgroundDrawable(dra);
		        break;
		        
			case 5:  
		        dra = resources.getDrawable(R.drawable.bub5);  
		        tvText.setBackgroundDrawable(dra);
		        break;
			case 6:  
		        dra = resources.getDrawable(R.drawable.bub6);  
		        tvText.setBackgroundDrawable(dra);
		        break;
			case 7:  
		        dra = resources.getDrawable(R.drawable.bub7);  
		        tvText.setBackgroundDrawable(dra);
		        break;
			case 8:  
		        dra = resources.getDrawable(R.drawable.bub8);  
		        tvText.setBackgroundDrawable(dra);
		        break;
			case 9:  
		        dra = resources.getDrawable(R.drawable.bub9);  
		        tvText.setBackgroundDrawable(dra);
		        break;
			case 10:  
		        dra = resources.getDrawable(R.drawable.bub10);  
		        tvText.setBackgroundDrawable(dra);
		        break;
		}
	}
	
	private void animation() {
		// TODO Auto-generated method stub
		//final TextView rotate = (TextView) findViewById(R.id.draw);
		SeekBar seekBar;
		
		seekBar = (SeekBar) findViewById(R.id.size);
        seekBar.setMax(21);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                // prevent seeking on app creation
            	TextView tv = (TextView) findViewById(R.id.sca);
            	
            	tv.setText(progress/10f + "");
            	tvText.setScaleX((float)progress/10f);
            	tvText.setScaleY((float)progress/10f);
            }
        });
       
        seekBar = (SeekBar) findViewById(R.id.scaleX);
        seekBar.setMax(40);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                tvText.setScaleX((float)progress/10f);
            }
        });
        seekBar = (SeekBar) findViewById(R.id.scaleY);
        seekBar.setMax(40);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                tvText.setScaleY((float)progress/10f);
            }
        });
        /*seekBar = (SeekBar) findViewById(R.id.rotationX);
        seekBar.setMax(360);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                // prevent seeking on app creation
                rotate.setRotationX((float)progress);
            }
        });
        seekBar = (SeekBar) findViewById(R.id.rotationY);
        seekBar.setMax(360);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                // prevent seeking on app creation
                rotate.setRotationY((float)progress);
            }
        });*/
        seekBar = (SeekBar) findViewById(R.id.rotationZ);
        seekBar.setMax(361);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                // prevent seeking on app creation
                tvText.setRotation((float)progress);
            }
        });
	}
	
	private void changeColor() {  
        btnColorPicker = (Button) findViewById(R.id.btn_color_picker);
        btnColorPicker.setOnTouchListener(new Button.OnTouchListener(){
			   @Override
			   public boolean onTouch(View v, MotionEvent event) {
			    if(event.getAction() == MotionEvent.ACTION_DOWN){   
			                    v.setBackgroundColor(Color.LTGRAY);   
			                    Log.i("TestAndroid Button", "MotionEvent.ACTION_DOWN");
			                }   
			                else if(event.getAction() == MotionEvent.ACTION_UP){   
			                    v.setBackgroundColor(Color.TRANSPARENT); 
			                    Log.i("TestAndroid Button", "MotionEvent.ACTION_UP");
			                } 
			    return false;
			   }
		});
        btnColorPicker.setOnClickListener(new View.OnClickListener() {  
              
            @Override  
            public void onClick(View v) { 
            	if(tvText.getText() == null) {
					Intent intent = new Intent(Text.this, NoText.class);
					startActivity(intent);
				}
            	
            	else {
            		dialog = new Picker(context, tvText.getTextColors().getDefaultColor(),   
                            getResources().getString(R.string.btn_color_picker),   
                            new Picker.OnColorChangedListener() {  
                          
                        @Override  
                        public void colorChanged(int color) {  
                            tvText.setTextColor(color);  
                        }  
                    });  
                    dialog.show();  
            	}
                
            }  
        });  
    }
}