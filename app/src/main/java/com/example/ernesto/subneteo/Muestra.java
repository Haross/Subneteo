package com.example.ernesto.subneteo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

public class Muestra extends AppCompatActivity {

    TextView tv_mostrar;
    int clase;
    String tabla="";
    String[] fa;
    int num=0;

    @Override
    protected  void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_muestra);//estamos llamando al archivo xml para enlazarlo

        tv_mostrar = (TextView) findViewById(R.id.impresion);
        // tabla = ""; //Declarar la variablee String para guardar los resultados
        Bundle extras = getIntent().getExtras();
         num = extras.getInt("numero");//recibe el numero de subneteo
        clase = extras.getInt("clase");
        fa = extras.getStringArray("fa");
        System.out.println("ja");
        System.out.println(fa[0]);
        //Muestra sub = new Muestra();
        subnet(this.fa);
        tv_mostrar.setText(tabla);
    }




        public void subnet(String[] fa){
            //System.out.println("Introduzca el numero de redes");
            int numR =num;
            System.out.println("La clase es " +clase);
            switch(clase){
                case 3:	subneteoC(numR,fa);
                    break;
                case 2:
                    subneteoB(numR);
                    break;
                case 1:  subneteoA(numR);
                    break;
            }
        }

        public void subneteoA(int numR){
            int x = 16777216/numR;
            int b = 24 - findbits(numR);
            for(int z=0;z<=255;z++){
                for(int y=0; y<=255;y++){
                    boolean band = true;
                    for(int k=x;k<256;k = k+x){
                        if(band){

                            tabla+=fa[0]+"."+z+"."+y+".0"+"\n";
                            band = false;
                        }

                        tabla+=fa[0]+"."+z+"."+y+"."+k+"\n";
                    }
                }
            }
            tv_mostrar.setText(tabla);
        }

        public void subneteoB(int numR){
            int x = 65536/numR;
            int b = 16 - findbits(numR);
            for(int y=0; y<=255;y++){
                boolean band = true;
                for(int k=x;k<256;k = k+x){
                    if(band){

                        tabla+=fa[0]+"."+fa[1]+"."+k+".0"+"\n";
                        band = false;
                    }
                    tabla+=fa[0]+"."+fa[1]+"."+y+"."+k+"\n";
                }
            }
            tv_mostrar.setText(tabla);
        }
        public void subneteoC(int numR,String[] fa){
            System.out.println("Entrada subneteo c");
            int x = 256/numR;
            int b = 8 - findbits(numR);
            this.tabla+=fa[0]+"."+fa[1]+"."+fa[2]+".0"+"\n";
            for(int k=x;k<256;k = k+x){
                System.out.println("for subneteo c");
                this.tabla+=fa[0]+"."+fa[1]+"."+fa[2]+"."+k+"\n";
            }
            System.out.println("salida subneteo c");
            //tabla="Hola";
            //tv_mostrar.setText(tabla);
        }


        public int findbits(int numR){
            int i=-1;
            int x=1;
            while( x!= 0){
                i++;
                int subredes = (int)Math.pow(2,i);
                if(numR <= subredes){
                    x=0;
                }
            }
            return i;
        }

        /*for (int i = 1; i < 100; i++){
            // eL ciclo se repetira 20 veces
            //cada vez que se repite el ciclo se agrega al string lo que esta en
            // comillas al mismo tiempo que se realiza la operacion
            tabla+="8 X "+i+" = "+8*i+"\n";
        }
        tv_mostrar.setText(tabla);*/
    }

