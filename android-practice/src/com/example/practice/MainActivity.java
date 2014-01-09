package com.example.practice;

import com.example.practice.R;
import com.example.practice.remote.HttpUtils;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// 声明一个Handler对象
	private Handler handler = new Handler();
	
	//
	EditText editText = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
 /**
  * 	不走配置UI，走编程
	        final TextView tv = new TextView(this);
	        tv.setText(" town girl ! ohohohoh");
	        
	        Button button = new Button(this);
	        button.setText("i am a button");
	        button.setOnClickListener(new OnClickListener() {
	        	
				@Override
				public void onClick(View v) {
					((Button)v).setText(" button is be clicked! ");
				}
			});
	      setContentView(button);
       *
       */
        
      //这个利用的是activity_main.xml文件
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText1);

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
    	
    	String text1 = textView.getText().toString();
    	String text2 = editText.getText().toString();
    	intent.putExtra("click-message", text2);
    	startActivity(intent);
//    	textView.setText(" the content is changed by clicking event !");
    }
    
	/**
	 * 点击事件
	 * 获取远程数据
	 * @param view
	 */
	public void getRemoteData(View view) {
		 HttpUtils.postRequest(handler,this,"http://113.105.92.52:7201/investws/product/queryPctActivity?guid=",null);
//		new AlertDialog.Builder(this).setTitle("弹出框").setTitle(result)
//				.setNegativeButton("确定", null).show();
	}
	
	/**
	 * 
	 */
	public void callPhone(View view){
		 //取得输入的电话号码串 
        String inputStr = editText.getText().toString(); 
        //如果输入不为空创建打电话的Intent 
        if(inputStr.trim().length()!=0) 
        { 
         Intent phoneIntent = new Intent("android.intent.action.CALL", 
             Uri.parse("tel:" + inputStr)); 
         //启动 
         startActivity(phoneIntent);    
        } 
        //否则Toast提示一下 
        else{ 
         Toast.makeText(this, "不能输入为空", Toast.LENGTH_LONG).show(); 
        } 
	}
	
    
}
