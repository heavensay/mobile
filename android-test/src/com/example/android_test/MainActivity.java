package com.example.android_test;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        final TextView tv = new TextView(this);
        tv.setText(" i love u ! ohohohoh");
        
        
        Button button = new Button(this);
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setText(" button is be clicked! ");
				
			}
		});
        
      //这个利用的是activity_main.xml文件
        setContentView(R.layout.activity_main);
        
//        setContentView(tv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * 点击事件，联动
     * @param view
     */
    public void showContent(View view){
    	Intent intent = new Intent(this,DisplayMessageActivity.class); 
    	TextView textView = (TextView)findViewById(R.id.textView1);
    	String message = textView.getText().toString();
    	intent.putExtra("click-message", message);
    	startActivity(intent);
//    	textView.setText(" the content is changed by clicking event !");
    }
    
}
