package edu.hzuapps.androidlabs.soft1714080902128;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Soft1714080902128ShowActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902128_showactivity);
        Button button =(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902128ShowActivity.this,Soft1714080902128Activity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                String inputText =editText.getText().toString();
                Toast.makeText(Soft1714080902128ShowActivity.this,inputText,
                        Toast.LENGTH_SHORT).show();
                break;
            default: break;
        }
    }
}
