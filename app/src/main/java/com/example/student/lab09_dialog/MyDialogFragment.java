package com.example.student.lab09_dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class MyDialogFragment extends DialogFragment {

    private EditText m_et_username;

    //無參數建構子是必須的
    public MyDialogFragment() {
            //Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 透過 inflater，讀取 fragment.xml 資源來生成 View

        // 取得 inflater
        LayoutInflater inflater = getActivity().getLayoutInflater() ;

        // 從 fragment.xml 取得自訂畫面
        //    inflate( resource , viewGroup )
        View view = inflater.inflate(R.layout.fragment_my_dialog,null);

        // 初始化 edit_view
        m_et_username = (EditText)view.findViewById(R.id.et_username);

        // 建立 AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view) //設定自訂 view
                .setPositiveButton("Sign in",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create(); // 返回所建立的 Dialog
    }
}
