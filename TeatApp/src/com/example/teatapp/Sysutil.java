package com.example.teatapp;

import android.content.Context;
import android.content.Intent;

public class Sysutil {
	public static final int  EXIT_APPLICATION = 0x0001;  
    
    private Context mContext;  
      
    public Sysutil(Context context){  
        this.mContext = context;  
    }  
      
    //��ȫ�˳�Ӧ��  
    public void exit(){  

          
        Intent mIntent = new Intent();  
        mIntent.setClass(mContext, MainActivity.class);  
        //��������flag���ǱȽ� ��Ҫ��  
        mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
        //�����˳�����ָʾ  
        mIntent.putExtra("flag", EXIT_APPLICATION);  
        mContext.startActivity(mIntent);  
    }  
}
