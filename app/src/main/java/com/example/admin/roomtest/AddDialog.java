package com.example.admin.roomtest;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by admin on 2017-05-23.
 */

public class AddDialog extends Dialog implements View.OnClickListener{


    private EditText idEdit;
    private EditText pwEdit;
    private Button positiveBtn;
    private Button negativeBtn;
    private DialogBtnClickListener clickListener;
    public AddDialog(@NonNull Context context) {
        super(context);
    }

    public AddDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getWindow() != null){
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.8f;
            getWindow().setAttributes(params);
        }

        setContentView(R.layout.dialog_add);

        idEdit = (EditText)findViewById(R.id.id_edit);
        pwEdit = (EditText)findViewById(R.id.pw_edit);
        positiveBtn = (Button)findViewById(R.id.positive_btn);
        negativeBtn = (Button)findViewById(R.id.negative_btn);
        positiveBtn.setOnClickListener(this);
        negativeBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.positive_btn:
                User user = new User();
                user.setId(checkEmpty(idEdit.getText().toString()));
                user.setPw(checkEmpty(pwEdit.getText().toString()));
                clickListener.positiveClick(user);
                break;
            case R.id.negative_btn:
                clickListener.negativeClick();
                break;
        }
    }
    private String checkEmpty(String text){
        if(text.isEmpty()){
            text = "null";
        }
        return text;
    }
    void setClickListener(DialogBtnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    interface DialogBtnClickListener{
        void positiveClick(User user);
        void negativeClick();
    }

}
