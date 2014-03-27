package com.pictoaster.www.montage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.pictoaster.www.R;

public class Background extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//»•µÙ±ÍÃ‚¿∏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.free_background); 
        
        
        ImageView img1 = (ImageView) findViewById(R.id.image1);
        ImageView img2 = (ImageView) findViewById(R.id.image2);
        ImageView img3 = (ImageView) findViewById(R.id.image3);
        ImageView img4 = (ImageView) findViewById(R.id.image4);
        ImageView img5 = (ImageView) findViewById(R.id.image5);
        ImageView img6 = (ImageView) findViewById(R.id.image6);
        
        img1.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Background.this, Free.class);
				Bundle bundle = Background.this.getIntent().getExtras();
				bundle.putInt("bg", 1);
				
				//bun1.putString("bm", bundle.getString("bm"));
				//bun1.putInt("download", bundle.getInt("download"));
				intent.putExtras(bundle);
				startActivity(intent);
				System.exit(0);
			}
        });
        
        img2.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Background.this, Free.class);
				Bundle bundle = Background.this.getIntent().getExtras();
				bundle.putInt("bg", 2);
				
				//bun1.putString("bm", bundle.getString("bm"));
				//bun1.putInt("download", bundle.getInt("download"));
				intent.putExtras(bundle);
				startActivity(intent);
				System.exit(0);
			}
        });
        
        img3.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Background.this, Free.class);
				Bundle bundle = Background.this.getIntent().getExtras();
				bundle.putInt("bg", 3);
				
				//bun1.putString("bm", bundle.getString("bm"));
				//bun1.putInt("download", bundle.getInt("download"));
				intent.putExtras(bundle);
				startActivity(intent);	
				System.exit(0);
			}
        });
        
        img4.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Background.this, Free.class);
				Bundle bundle = Background.this.getIntent().getExtras();
				bundle.putInt("bg", 4);
				
				//bun1.putString("bm", bundle.getString("bm"));
				//bun1.putInt("download", bundle.getInt("download"));
				intent.putExtras(bundle);
				startActivity(intent);
				System.exit(0);
			}
        });
        
        img5.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Background.this, Free.class);
				Bundle bundle = Background.this.getIntent().getExtras();
				bundle.putInt("bg", 5);
				
				//bun1.putString("bm", bundle.getString("bm"));
				//bun1.putInt("download", bundle.getInt("download"));
				intent.putExtras(bundle);
				startActivity(intent);	
				System.exit(0);
			}
        });
        
        img6.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Background.this, Free.class);
				Bundle bundle = Background.this.getIntent().getExtras();
				bundle.putInt("bg", 6);
				
				//bun1.putString("bm", bundle.getString("bm"));
				//bun1.putInt("download", bundle.getInt("download"));
				intent.putExtras(bundle);
				startActivity(intent);	
				System.exit(0);
			}
        });
	}
}


	
	
	