package com.example.vizeodev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class activity3 extends AppCompatActivity {

    private EditText editText;
    private Button Gonder;
    private TextView kisiler;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioGroup rg;
    private int uzunluk=0;
    SharedPreferences sharedPref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Context context=getApplicationContext();
        SmsManager sms = SmsManager.getDefault();
       sharedPref=context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
        editText=(EditText) findViewById(R.id.editTextTextMultiLine);
        Gonder=(Button) findViewById(R.id.button2);
        kisiler=(TextView) findViewById(R.id.textView5);
        rb1=(RadioButton) findViewById(R.id.radioButton6);
        rb2=(RadioButton) findViewById(R.id.radioButton5);
        rb3=(RadioButton) findViewById(R.id.radioButton);
        rg=(RadioGroup)findViewById(R.id.radioGroup);

        Gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rb1.isChecked())
                {
                    for(int i=0;i<sharedPref.getInt("Akrabauzunluk",0);i++)
                    {
                        sms.sendTextMessage(sharedPref.getString("akraba_no"+i,""),null,editText.getText().toString(),null,null);
                    }
                }

                else if(rb2.isChecked())
                {
                    for(int i=0;i<sharedPref.getInt("Arkadasuzunluk",0);i++)
                    {
                        sms.sendTextMessage(sharedPref.getString("arkadas_no"+i,""),null,editText.getText().toString(),null,null);
                    }
                }

                else if(rb3.isChecked())
                {
                    for(int i=0;i<sharedPref.getInt("Isuzunluk",0);i++)
                    {
                        sms.sendTextMessage(sharedPref.getString("is_no"+i,""),null,editText.getText().toString(),null,null);
                    }
                }


            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                kisiler.setText("");
                if(rb1.isChecked())
                {

                    for(int i=0;i<sharedPref.getInt("Akrabauzunluk",0);i++)
                    {

                        kisiler.setText(kisiler.getText().toString()+sharedPref.getString("akraba_isim"+String.valueOf(i),"")+"\n");

                    }
                }
                else if(rb2.isChecked())
                {
                    for(int i=0;i<sharedPref.getInt("Arkadasuzunluk",0);i++)
                    {
                        kisiler.setText(kisiler.getText().toString()+sharedPref.getString("arkadas_isim"+String.valueOf(i),"")+"\n");

                    }
                }

                else if(rb3.isChecked())
                {
                    for(int i=0;i<sharedPref.getInt("Isuzunluk",0);i++)
                    {
                        kisiler.setText(kisiler.getText().toString()+sharedPref.getString("is_isim"+String.valueOf(i),"")+"\n");

                    }
                }
            }
        });

    }
}