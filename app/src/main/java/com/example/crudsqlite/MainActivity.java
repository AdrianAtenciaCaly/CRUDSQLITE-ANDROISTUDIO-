package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtCodigo,edtCurso,edtCarrera;
    Button btnAgregar, btnEditar,btnEliminar,btnLista,btnBuscar,btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtCodigo =  findViewById(R.id.editTCodigo);
        edtCurso =  findViewById(R.id.editTCurso);
        edtCarrera = findViewById(R.id.editTCarrera);
        btnAgregar =  findViewById(R.id.buttonAgregar);
        btnEditar = findViewById(R.id.buttonEditar);
        btnEliminar =  findViewById(R.id.buttonEliminar);
        btnLista =  findViewById(R.id.buttonListar);
        btnBuscar =  findViewById(R.id.buttonBuscar);
        btnLimpiar =  findViewById(R.id.buttonLimpiar);

        final BD bd = new BD(getApplicationContext());

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(edtCodigo.getText().toString().trim().equalsIgnoreCase("") || edtCarrera.getText().toString().trim().equalsIgnoreCase("") || edtCurso.getText().toString().trim().equalsIgnoreCase("") ){
                   Toast.makeText(getApplicationContext(),"FALTAN DATOS",Toast.LENGTH_SHORT).show();

                   AlertDialog.Builder alerta=new AlertDialog.Builder(MainActivity.this);
                   alerta.setTitle("Alerta");
                   alerta.setMessage("...esta enterado");
                   alerta.setPositiveButton("Aceptar",null);
                   alerta.create();
                   alerta.show();
               }else{

                   AlertDialog.Builder alerta=new AlertDialog.Builder(MainActivity.this);
                   alerta.setTitle("Realmente desea guardar estos datos?");
                   alerta.setMessage("Codigo: "+edtCodigo.getText()+"\n"+"Curso: "+edtCurso.getText()+"\n"+"Carrera: "+edtCarrera.getText());
                   alerta.setCancelable(false);
                   alerta.setPositiveButton("Si",new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialogo,int id) {
                           bd.agregarCursos(edtCodigo.getText().toString().trim(),edtCurso.getText().toString().trim(),edtCarrera.getText().toString().trim());

                           Toast.makeText(getApplicationContext(),"DATOS AGREGADOS CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                           limpiarCampos();
                   }
                   });
                   alerta.setNegativeButton("No",new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialogo,int id) {

                   }
                   });
                   alerta.create();
                   alerta.show();
               }
           }
        });

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrarListaCursos = new Intent( getApplicationContext(),listaCursos.class);
           startActivity(mostrarListaCursos);
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(edtCodigo.getText().toString().trim().equalsIgnoreCase("")){
                   edtCodigo.setError("Ingrese codigo a buscar");
               }else {
                    CursoModelo cursoModelo = new CursoModelo();
                    bd.buscarCursos(cursoModelo, edtCodigo.getText().toString().trim());
                    edtCurso.setText(cursoModelo.getCurso());
                    edtCarrera.setText(cursoModelo.getCarrera());
               }

            }
        });
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.actalizarCurso(edtCodigo.getText().toString().trim(),edtCurso.getText().toString().trim(),edtCarrera.getText().toString().trim());
                Toast.makeText(getApplicationContext(),"DATOS MODIFICADOS CORRECTAMENTE",Toast.LENGTH_SHORT).show();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.eliminarCurso(edtCodigo.getText().toString().trim());
                Toast.makeText(getApplicationContext(),"DATOS ELIMINADOS CORRECTAMENTE",Toast.LENGTH_SHORT).show();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();


            }
        });

    }

    public void limpiarCampos(){

        edtCodigo.setText("");
        edtCurso.setText("");
        edtCarrera.setText("");
    }


}
