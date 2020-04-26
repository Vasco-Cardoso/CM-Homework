package com.example.homework1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {


        private static  final int REQUEST_CALL = 1;
        Button um;
        Button dois;
        Button tres;
        Button quatro;
        Button cinco;
        Button seis;
        Button sete;
        Button oito;
        Button nove;
        Button zero;
        Button asterisco;
        Button cardinal;
        Button apagar;
        Button ligar;
        Button sd1;
        Button sd2;
        Button sd3;
        EditText texto;

        public static String sd1_nome = "NOT DEFINED";
        public static String sd1_num = "";
        public static String sd2_nome = "NOT DEFINED";
        public static String sd2_num = "";
        public static String sd3_nome = "NOT DEFINED";
        public static String sd3_num = "";

        public static void setSd1_nome(String sd1_nome) {

            MainActivity.sd1_nome = sd1_nome;
        }

        public static void setSd1_num(String sd1_num) {

            MainActivity.sd1_num = sd1_num;
        }

        public static void setSd2_nome(String sd2_nome) {
            MainActivity.sd2_nome = sd2_nome;
        }

        public static void setSd2_num(String sd2_num) {
            MainActivity.sd2_num = sd2_num;
        }

        public static void setSd3_nome(String sd3_nome) {
            MainActivity.sd3_nome = sd3_nome;
        }

        public static void setSd3_num(String sd3_num) {
            MainActivity.sd3_num = sd3_num;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            um = findViewById(R.id.button);
            dois = findViewById(R.id.button2);
            tres = findViewById(R.id.button3);
            quatro = findViewById(R.id.button4);
            cinco = findViewById(R.id.button5);
            seis = findViewById(R.id.button6);
            sete = findViewById(R.id.button7);
            oito = findViewById(R.id.button8);
            nove = findViewById(R.id.button9);
            zero = findViewById(R.id.button10);
            asterisco = findViewById(R.id.button11);
            cardinal = findViewById(R.id.button12);
            ligar = findViewById(R.id.button13);
            apagar = findViewById(R.id.button14);
            texto = findViewById(R.id.editText);
            sd1 = (Button)findViewById(R.id.button18);
            sd2 = (Button)findViewById(R.id.button19);
            sd3 = (Button)findViewById(R.id.button20);

            sd1.setText(sd1_nome);
            sd2.setText(sd2_nome);
            sd3.setText(sd3_nome);

            ligar.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    ligar();
                }
            });

            sd1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    texto.setText(sd1_num);
                    ligar();
                }
            });

            sd1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent i = new Intent(getApplicationContext(), sdinfo.class);
                    startActivity(i);
                    i.putExtra("numero", sd1_num);
                    i.putExtra("nome", sd1_nome);
                    i.putExtra("id", "1");
                    startActivity(i);
                    return true;
                }
            });

            sd2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    texto.setText(sd2_num);
                    ligar();
                }
            });

            sd2.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent i = new Intent(getApplicationContext(), sdinfo.class);
                    i.putExtra("numero", sd2_num);
                    i.putExtra("nome", sd2_nome);
                    i.putExtra("id", "2");
                    startActivity(i);
                    return true;
                }
            });

            sd3.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    texto.setText(sd3_num);
                    ligar();
                }
            });

            sd3.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent i = new Intent(getApplicationContext(), sdinfo.class);
                    i.putExtra("numero", sd3_num);
                    i.putExtra("nome", sd3_nome);
                    i.putExtra("id", "3");
                    startActivity(i);
                    return true;
                }
            });
        }

        public void um(View v) {

            onBtnClick(um, texto, "1");
        }

        public void dois(View v) {

            onBtnClick(dois, texto, "2");
        }

        public void tres(View v) {

            onBtnClick(tres, texto, "3");
        }

        public void quatro(View v) {

            onBtnClick(quatro, texto, "4");
        }

        public void cinco(View v) {

            onBtnClick(cinco, texto, "5");
        }

        public void seis(View v) {

            onBtnClick(seis, texto, "6");
        }

        public void sete(View v) {

            onBtnClick(sete, texto, "7");
        }

        public void oito(View v) {

            onBtnClick(oito, texto, "8");
        }

        public void nove(View v) {

            onBtnClick(nove, texto, "9");
        }

        public void zero(View v) {

            onBtnClick(zero, texto, "0");
        }

        public void asterisco(View v) {

            onBtnClick(asterisco, texto, "*");
        }

        public void cardinal(View v) {

            onBtnClick(cardinal, texto, "#");
        }

        public void apagar(View v) {

            texto.setText("");
        }

        public void ligar() {

            String num = texto.getText().toString();

            if(num.trim().length() == 9){

                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                }else {

                    String dial = "tel:" + num;
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }

            }else{

                Toast.makeText(MainActivity.this, "Enter correct number", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if(requestCode == REQUEST_CALL){

                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    ligar();
                } else{

                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }

        public void onBtnClick(Button b, EditText in, String num){

            String cache = texto.getText().toString();
            in.setText(cache + num);
        }
    }

