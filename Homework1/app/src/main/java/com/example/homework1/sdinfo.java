package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sdinfo extends AppCompatActivity {

    Button save, back;
    EditText num, name;

    Bundle extras = getIntent().getExtras();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdinfo);

        back.findViewById(R.id.button17);
        num.findViewById(R.id.editText3);
        name.findViewById(R.id.editText2);
        save.findViewById(R.id.button16);



        num.setText(extras.getString("numero"));
        name.setText(extras.getString("nome"));
    }


    public void back(View v){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    public void save(View v){

        String id = extras.getString("id");

        if (id == "1"){

            MainActivity.setSd1_nome(name.getText().toString());
            MainActivity.setSd1_num(num.getText().toString());
        }else if (id == "2"){

            MainActivity.setSd2_nome(name.getText().toString());
            MainActivity.setSd2_num(num.getText().toString());
        }else{

            MainActivity.setSd3_nome(name.getText().toString());
            MainActivity.setSd3_num(num.getText().toString());
        }

        Toast.makeText(sdinfo.this, "Info updated", Toast.LENGTH_SHORT).show();
    }

}
