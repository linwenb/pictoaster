package com.pictoaster.www.retouch.tone;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.pictoaster.www.R;

public class ToneMethod {
	
	//---------------------------------------------------
	//We define the data that we need in the view section
	//---------------------------------------------------
	private TextView mSaturation;
	private SeekBar mSaturationBar;
	
	private TextView mLightness;
	private SeekBar mLightnessBar;
	
	private TextView mHue;
	private SeekBar mHueBar;
	
	private LinearLayout mParent;
	
	private static final int MAX_VALUE = 255;
	private static final int MIDDLE_VALUE = 127;
	
	private float mDensity;
	private static final int TEXT_WIDTH = 50;
	
	private ArrayList<SeekBar> mSeekBar = new ArrayList<SeekBar>();
	
	//----------------------------------------------------
	//We define the data that we need in the process section
	//----------------------------------------------------
	private ColorMatrix mSaturationMatrix;
	private ColorMatrix mLightnessMatrix;
	private ColorMatrix mHueMatrix;
	private ColorMatrix mAllMatrix;
	
	public static final int FLAG_SATURATION = 0;
	public static final int FLAG_LIGHTNESS = 1;
	public static final int FLAG_HUE = 2;
	
	private float mSaturationValue = 0F;
	private float mLightnessValue = 1F;
	private float mHueValue = 0F;
	
	//---------------------------------------------------
	//Define the constructor for the toneMethod
	//---------------------------------------------------
	public ToneMethod(Context context)
	{
		init(context);
	}
	
	//---------------------------------------------------
	//Define the init method to initialize the toneMethod
	//--------------------------------------------------
	private void init(Context context)
	{
		//------------------------------------------------
		//initialize view object
		//------------------------------------------------
		
		//Set the value and gravity for 3 TextView
		mDensity = context.getResources().getDisplayMetrics().density;
		mSaturation = new TextView(context);
		mSaturation.setText(R.string.saturation);
		mSaturation.setTextSize(10);
		mSaturation.setTypeface(null, Typeface.BOLD);
		mSaturation.setGravity(Gravity.CENTER);
		
		mLightness = new TextView(context);
		mLightness.setText(R.string.lightness);
		mLightness.setTextSize(10);
		mLightness.setTypeface(null, Typeface.BOLD);
		mLightness.setGravity(Gravity.CENTER);
		
		mHue = new TextView(context);
		mHue.setText(R.string.hue);
		mHue.setTextSize(10);
		mHue.setTypeface(null, Typeface.BOLD);
		mHue.setGravity(Gravity.CENTER);
		
		//Initialize the 3 SeekBar and add them to the mSeekBar list
		mSaturationBar = new SeekBar(context);
		mLightnessBar = new SeekBar(context);
		mHueBar = new SeekBar(context);
		
		mSeekBar.add(mSaturationBar);
		mSeekBar.add(mLightnessBar);
		mSeekBar.add(mHueBar);
		
		//set the max_value, initial value and tag for the 3 SeekBar
		for(int i = 0;i<mSeekBar.size();i++)
		{
			SeekBar tem = mSeekBar.get(i);
			tem.setMax(MAX_VALUE);
			
			if(i == mSeekBar.size()-1)
				tem.setProgress(0);
			else
				tem.setProgress(MIDDLE_VALUE);
			
			tem.setTag(i);
		}
		
		//Initialize the 3 LinearLayout and add corresponding TextView and SeekBar into them
		//Then add them to parent LinearLayout
		LinearLayout.LayoutParams txtLayoutParams = new LinearLayout.LayoutParams((int)(TEXT_WIDTH*mDensity), LinearLayout.LayoutParams.FILL_PARENT);
		LinearLayout.LayoutParams seekLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		
		LinearLayout saturation = new LinearLayout(context);
		saturation.setOrientation(LinearLayout.HORIZONTAL);
		saturation.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
		saturation.addView(mSaturation, txtLayoutParams);
		saturation.addView(mSaturationBar, seekLayoutParams);
		
		LinearLayout lightness = new LinearLayout(context);
		lightness.setOrientation(LinearLayout.HORIZONTAL);
		lightness.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
		lightness.addView(mLightness, txtLayoutParams);
		lightness.addView(mLightnessBar, seekLayoutParams);
		
		LinearLayout hue = new LinearLayout(context);
		hue.setOrientation(LinearLayout.HORIZONTAL);
		hue.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
		hue.addView(mHue, txtLayoutParams);
		hue.addView(mHueBar, seekLayoutParams);
		
		mParent = new LinearLayout(context);
		mParent.setOrientation(LinearLayout.VERTICAL);
		mParent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		mParent.addView(saturation);
		mParent.addView(lightness);
		mParent.addView(hue);
	}
	
	//--------------------------------------------
	//Return the parent View
	//--------------------------------------------
	public View getParentView()
	{
		return mParent;
	}
	
	//---------------------------------------------
	//Return SeekBar list
	//---------------------------------------------
	public ArrayList<SeekBar> getSeekBar()
	{
		return mSeekBar;
	}
	
	//----------------------------------------------
	//Set the value of saturation, lightness and hue
	//----------------------------------------------
	public void setSaturation(int saturation)
	{
		mSaturationValue = saturation * 1.0F / MIDDLE_VALUE;
	}
	
	public void setLightness(int lightness)
	{
		mLightnessValue = lightness * 1.0F / MIDDLE_VALUE;
	}
	
	public void setHue(int hue)
	{
		mHueValue = 180* hue * 1.0F / MIDDLE_VALUE;
	}
	
	public Bitmap handleImage(Bitmap bm, int flag)
	{
		//Create a bitmap and initialize the canvas and paint
		Bitmap bmp = Bitmap.createBitmap(bm.getWidth(),bm.getHeight(),Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bmp);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		
		//Clean the old data in colormatrix
		if(mSaturationMatrix == null)
		{
			mSaturationMatrix = new ColorMatrix();
		}
		if(mLightnessMatrix == null)
		{
			mLightnessMatrix = new ColorMatrix();
		}
		if(mHueMatrix == null)
		{
			mHueMatrix = new ColorMatrix();
		}
		if(mAllMatrix == null)
		{
			mAllMatrix = new ColorMatrix();
		}
		
		//According to flag value, we set different value in the 3 colorMatrix
		switch(flag)
		{
		case FLAG_SATURATION:
			mSaturationMatrix.reset();
			mSaturationMatrix.setSaturation(mSaturationValue);
			break;
		case FLAG_LIGHTNESS:
			mLightnessMatrix.reset();
			mLightnessMatrix.setScale(mLightnessValue, mLightnessValue, mLightnessValue, 1);
			break;
		case FLAG_HUE:
			mHueMatrix.reset();
			mHueMatrix.setRotate(0, mHueValue);
			mHueMatrix.setRotate(1, mHueValue);
			mHueMatrix.setRotate(2, mHueValue);
			break;
		}
		
		//Combine the three color matrix to the mAllMatrix
		mAllMatrix.reset();
		mAllMatrix.postConcat(mSaturationMatrix);
		mAllMatrix.postConcat(mLightnessMatrix);
		mAllMatrix.postConcat(mHueMatrix);
		
		//
		paint.setColorFilter(new ColorMatrixColorFilter(mAllMatrix));
		canvas.drawBitmap(bm, 0, 0, paint);
		return bmp;
	}
}
