package com.example.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class TemActivity extends AppCompatActivity {

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {         // Edittext 입력 중 다른 곳 터치하면 타자기 내리기
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tem);


        EditText temtext = findViewById(R.id.Temtext);
        EditText moitext = findViewById(R.id.Moitext);

        ImageButton send = findViewById(R.id.sendmain);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tem = temtext.getText().toString();
                String moi = moitext.getText().toString();
                Intent sendtext = new Intent(TemActivity.this,MainActivity.class);
                if(tem != null && moi != null){
                    sendtext.putExtra("tem",tem);
                    sendtext.putExtra("moi",moi);
                    Toast.makeText(TemActivity.this,"설정 완료",Toast.LENGTH_SHORT).show();
                    startActivity(sendtext);
                }else{
                    Toast.makeText(TemActivity.this,"다시 설정해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });





        ImageButton temmain = findViewById(R.id.temmain);
        temmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TemActivity.this,"취소",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        ImageButton home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(TemActivity.this,MainActivity.class);
                startActivity(home);
            }
        });





    }
}