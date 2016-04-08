package com.example.student.lab09_dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements DialogInterface.OnClickListener {

    private TextView m_tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        m_tv_message = (TextView) findViewById(R.id.tv_message);
    }


    //按下 主畫面按鈕
    public void clickAlertDialog(View view) {
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                        //將此按鈕 onClick 事件委託給當前的 Activity(居中協調)
                .setPositiveButton("我知道", this)
                .show();
    }

    //按下 對話框(Dialog) 我知道 按鈕
    @Override
    public void onClick(DialogInterface dialog, int which) {
        m_tv_message.setText("我知道");
    }

    //按下 主畫面按鈕2
    public void clickAlertDialogYesNo(View view) {
        AlertDialogYesNoLinsener linsener = new AlertDialogYesNoLinsener();
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                .setPositiveButton("謝謝", linsener)
                .setNegativeButton("狗腿", linsener)
                .show();
    }


    private class AlertDialogYesNoLinsener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    m_tv_message.setText("謝謝");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    m_tv_message.setText("狗腿");
                    break;
            }
        }
    }

    //按下 主畫面按鈕3
    public void clickAlertDialogYesNoCancel(View view) {
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                .setPositiveButton("謝謝讚美", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        m_tv_message.setText("謝謝讚美");
                }
                })
                .setNegativeButton("太狗腿了吧",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        m_tv_message.setText("太狗腿了吧");
                    }
                })
                .setNeutralButton("不知道該說什麼",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("不知道該說什麼");
                    }
                })
                .show();
    }

    //按下 主畫面按鈕4
    public void clickAlertDialogItems(View view) {
        final String[] response = getResources().getStringArray(R.array.response);
        new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar)
                .setTitle("你好帥")
                .setItems(response, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText(response[which]);
                    }
                })
                .show();
    }

    //按下 主畫面按鈕5
    public void clickAlertDialogMultiChoice(View view) {
        final String[] response = getResources().getStringArray(R.array.response);
        final boolean[ ] selected = new boolean[response.length];

        new AlertDialog.Builder(this)
                .setTitle("你好帥")
                .setMultiChoiceItems(response,selected,new
                DialogInterface.OnMultiChoiceClickListener(){
                    //每次勾選或取消勾選時執行
                    //which      這是點選了哪個項目
                    //isChecked  所點選的項目是否打勾
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selected[which] = isChecked ;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder result = new StringBuilder();
                        // 走訪 selected 陣列，將有勾選的項目字串加入 result 字串裡
                        for (int i = 0; i < selected.length; i++) {
                            if (selected[i]) {
                                result.append(response[i]).append("\n");
                            }
                        }
                        m_tv_message.setText(result);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("無語");
                    }
                })
                .show();
    }

    //按下 主畫面按鈕6
    private int mChoice ; //欄位
    public void clickAlertDialogSingleChoice(View view) {
        final String[] response = getResources().getStringArray(R.array.response);
        new AlertDialog.Builder(this)
                .setTitle("你好帥")
                .setSingleChoiceItems(response,0,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mChoice = which; //此 onClick 執行完，which 會消失，所以要記錄在欄位裡
                    }
                })
                .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText(response[mChoice]);
                    }
                })
                .setNegativeButton("CANCEL",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("無語");
                    }
                })
                .show();
    }

    //按下 主畫面按鈕7
    public void clickAlertDialogMyDialogFragment(View view) {
        // 建立自訂的 Dialog
        DialogFragment dialog = new MyDialogFragment();
        // 顯示 Dialog
        dialog.show(getSupportFragmentManager(),"MyDialogFragment");
    }
}