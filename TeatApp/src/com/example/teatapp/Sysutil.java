package com.example.teatapp;

import android.content.Context;
import android.content.Intent;

public class Sysutil {
	public static final int  EXIT_APPLICATION = 0x0001;  
    
    private Context mContext;  
      
    public Sysutil(Context context){  
        this.mContext = context;  
    }  
      
    //完全退出应用  
    public void exit(){  

          
        Intent mIntent = new Intent();  
        mIntent.setClass(mContext, MainActivity.class);  
        //这里设置flag还是比较 重要的  
        mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
        //发出退出程序指示  
        mIntent.putExtra("flag", EXIT_APPLICATION);  
        mContext.startActivity(mIntent);  
    }  
}
