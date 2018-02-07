package com.zhengpu.iflytekaiui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.zhengpu.iflytekaiui.R;


/**
 * sayid ....
 * Created by wengmf on 2017/12/18.
 */

public class CommonDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TextView tvContext;
    private Button butOk,butCancel;
    private String contet;
    private  onButOKListener onButOKListener;
    private  onButCancelListener onButCancelListener;


    public CommonDialog(@NonNull Context context, String contet) {
        super(context, R.style.dialog);
        this.context = context;
        this.contet=contet;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.commdialog);
        tvContext = (TextView) findViewById(R.id.tv_context);
        tvContext.setText(contet);
        butOk = (Button) findViewById(R.id.but_ok);
        butCancel = (Button) findViewById(R.id.but_cancel);
        butOk.setOnClickListener(this);
        butCancel.setOnClickListener(this);
//        setDialogAttributes((Activity) context, this, 0.5f, 0, Gravity.CENTER);
        setCanceledOnTouchOutside(false);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_ok:
                if(onButOKListener!=null)
                    onButOKListener.onButOKListener();
                break;
            case  R.id.but_cancel:
                if(onButCancelListener!=null)
                    onButCancelListener.onButCancelListener();
                break;
        }
    }

    public interface onButOKListener {
        void onButOKListener();
    }
    public void onButOKListener(onButOKListener onButOKListener) {
        this.onButOKListener = onButOKListener;
    }
    public interface onButCancelListener {
        void onButCancelListener();
    }
    public void onButCancellListener(onButCancelListener onButCancelListener) {
        this.onButCancelListener = onButCancelListener;
    }
}
