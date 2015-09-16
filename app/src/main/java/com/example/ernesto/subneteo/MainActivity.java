package com.example.ernesto.subneteo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_ip, et_numero;
    Button btn_calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_ip = (EditText) findViewById(R.id.et_ip);
        et_numero = (EditText) findViewById(R.id.et_numero);

        btn_calcular = (Button) findViewById(R.id.btn_calcular);
    }

    public void creditos(View view){
        Intent a = new Intent(this, Creditos.class);
        startActivity(a);
    }


    public void onClickAceptar(View view){


        String str_ip = et_ip.getText().toString();
        String str_numero = et_numero.getText().toString();
        String fa[];
        int clase =0;
        str_ip = str_ip.replaceAll(" ","");

        if(!str_ip.matches("")&& !str_numero.matches("")){
            fa = str_ip.split("\\.");
            boolean band = true;
            for(int i=0; i < fa.length; i++){
                int num = Integer.parseInt(fa[i]);
                if(i == 0)
                    if(num<128){
                        clase = 1;
                    }else{
                        if(num <192){
                            clase = 2;
                        }else{
                            if(num <224){
                                clase = 3;
                            }else{
                                if(num<240){
                                    clase = 4;
                                }else{
                                    clase = 5;
                                }
                            }
                        }
                    }
                if(num<0 || num>255){
                    band = false;
                }
            }
            if(band){
                System.out.println("clase valor main" +clase);
                Intent i = new Intent(this, Muestra.class);
                i.putExtra("fa", fa);//envia los datos de la IP
                i.putExtra("numero", Integer.parseInt(str_numero));//envia los datos de la cantidad
                i.putExtra("clase", clase);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(),"Debe rellenar los campos", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getApplicationContext(),"Debe rellenar los campos", Toast.LENGTH_SHORT).show();
        }
    }




}
