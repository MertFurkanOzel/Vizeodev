package com.example.vizeodev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaRouter2;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class activity2 extends AppCompatActivity
{
    private ArrayList<Kisiler> rehber;
    int is=0,arkadas=0,akraba=0; // shared preferences'da grupların uzunluklarını tutan değişkenler
    private RecyclerView recw;
    private RehberAdapter rehberAdapter;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button button;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Context context=getApplicationContext();
        rb1=(RadioButton) findViewById(R.id.radioButton2);
        rb2=(RadioButton) findViewById(R.id.radioButton3);
        rb3=(RadioButton) findViewById(R.id.radioButton4);
        button=(Button)findViewById(R.id.button);
        Intent ucuncu=new Intent(this,activity3.class);
        rehber=new ArrayList<Kisiler>();
        sharedPreferences=context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
         editor=sharedPreferences.edit();
        Cursor telrehber=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while(telrehber.moveToNext())
            {
                String isim=telrehber.getString(telrehber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String numara=telrehber.getString(telrehber.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                Kisiler nesne=new Kisiler();
                nesne.set_isim(isim);
                nesne.set_numara(numara);
                nesne.set_foto(R.drawable.ic_launcher_asd_foreground);
                rehber.add(nesne);
            }telrehber.close();
        recw=(RecyclerView)findViewById(R.id.Recview);
        rehberAdapter=new RehberAdapter(rehber);
        rehberAdapter.setOnItemClickListener(new RehberAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) { //position kaçıncı iteme tıklanıldığını tutuyor.

                if(rb1.isChecked())
                {
                    Toast.makeText(getApplicationContext(),rehber.get(position).get_isim()+" isimli kişi akrabalara eklendi.",Toast.LENGTH_SHORT).show();
                    editor.putString("akraba_isim"+String.valueOf(arkadas),rehber.get(position).get_isim());
                    editor.putString("akraba_no"+String.valueOf(arkadas),rehber.get(position).get_numara());
                    arkadas++;
                }

                else if(rb2.isChecked())
                {
                    Toast.makeText(getApplicationContext(),rehber.get(position).get_isim()+" isimli kişi arkadaşlara eklendi.",Toast.LENGTH_SHORT).show();
                    editor.putString("arkadas_isim"+String.valueOf(akraba),rehber.get(position).get_isim());
                    editor.putString("arkadas_no"+String.valueOf(akraba),rehber.get(position).get_numara());
                    akraba++;
                }

                else if(rb3.isChecked())
                {
                    Toast.makeText(getApplicationContext(),rehber.get(position).get_isim()+" isimli kişi iş grubuna eklendi.",Toast.LENGTH_SHORT).show();
                    editor.putString("is_isim"+String.valueOf(is),rehber.get(position).get_isim());
                    editor.putString("is_no"+String.valueOf(is),rehber.get(position).get_numara());
                    is++;
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Eklemek İstediğiniz Grubu Seçin.",Toast.LENGTH_SHORT).show();
                }


                return true;
            }

        });

        recw.setAdapter(rehberAdapter);
        recw.setLayoutManager(new LinearLayoutManager(this));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ucuncu);
            }
        });











    }

    @Override
    protected void onPause() {
        super.onPause();
        editor.putInt("Akrabauzunluk",akraba);
        editor.putInt("Arkadasuzunluk",arkadas);
        editor.putInt("Isuzunluk",is);
        editor.commit();

    }
}

