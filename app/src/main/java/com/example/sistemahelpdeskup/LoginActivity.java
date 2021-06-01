package com.example.sistemahelpdeskup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText et_nombre;
    EditText et_clave;
    ImageView iv_logo;
    TextView tv_olvidaste;
     Button btnbuton;

    String usuario, password, rol;

   int num_aleatorio = (int) (Math.random() * 10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_nombre =  findViewById(R.id.txt_nombre);
        et_clave = findViewById(R.id.txt_clave);
        iv_logo =  findViewById(R.id.imageView_Logo);
        tv_olvidaste = findViewById(R.id.textView_Olvidaste);
        btnbuton = findViewById(R.id.button_1 );

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        int id;
        if (num_aleatorio == 1 || num_aleatorio == 9) {
            id = getResources().getIdentifier("universidad", "drawable", getPackageName());
            iv_logo.setImageResource(id);
        } else if (num_aleatorio == 2 || num_aleatorio == 8) {
            id = getResources().getIdentifier("unipaci" , "drawable", getPackageName());
            iv_logo.setImageResource(id);

        }
        btnbuton.setOnClickListener(v -> {
            usuario=et_nombre.getText().toString();
            password=et_clave.getText().toString();

            //cambiar con la ip de su pc
            //SI puerto es 80 solo poner la ip SINO colocar el puerto asignado
            if(!usuario.isEmpty() && !password.isEmpty()){
                validarUsuario("http://179.6.213.4:85/HelpDeskPacifico/autentificacion.php"); //Llamamos al método y colocamos la url de nuestro servicio
            }else{
                Toast.makeText(LoginActivity.this, "No se permite campos vacíos", Toast.LENGTH_SHORT).show();
            }

        });




    }


    private void validarUsuario(String URL){
        //Se ejecutará en caso se procese la petición
        // Generará error en caso no se procese la petición
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, response -> {
            if(!response.isEmpty()){ //Validamos que los datos ingresados sean correctos

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show()){
            //Parámetros que el servicio solicita para devolvernos una respuesta
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>(); //Creamos una instancia 'parametros'
                //Ingresamos los datos a enviar
                parametros.put("usuario", usuario);
                parametros.put("password", password);

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    /*
        @Override
        public void onClick(View v) {
            Intent intent = null;
             switch (v.getId()) {
                case R.id.button_1:
                intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("titulo", "INGRESAR");
                startActivity(intent);
                break;

        }
             }
    */



// mi parte




    







}