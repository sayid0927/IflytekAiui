package com.zhengpu.iflytekaiui.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.zhengpu.iflytekaiui.R;


/**
 * Created by Administrator on 2016/10/26.
 */

public class SpeechDialog extends Dialog  {
    private TextView textView;
    private Context context;
    private String s;

    public SpeechDialog(Context context, String s ) {
        super(context, R.style.dialog);
        this.context = context;
        this.s=s;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);//将弹出框设置为全局
        setContentView(R.layout.speechdialog);
        setCanceledOnTouchOutside(false);
        setCancelable(true);//弹出框不可以换返回键取消
        setCanceledOnTouchOutside(true);//失去焦点不会消失
        textView = (TextView)findViewById(R.id.tv_context) ;
        Button button = (Button)findViewById(R.id.but_cancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  dismiss();
            }
        });
        textView.setText(s);

    }

    public void setDialogAttributes(Activity context, final Dialog dialog,
                                    float widthP, float heightP, int gravity) {
        Display d = context.getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
        Point mPoint = new Point();
        d.getSize(mPoint);
        if (heightP != 0)
            p.height = (int) (mPoint.y * heightP);
        if (widthP != 0)
            p.width = (int) (mPoint.x * widthP);
        dialog.getWindow().setAttributes(p);
        dialog.getWindow().setGravity(gravity);
    }
}
