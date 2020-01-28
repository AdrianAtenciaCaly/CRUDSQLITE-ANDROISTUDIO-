package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class listaCursos extends AppCompatActivity {
    private RecyclerView recyclerViewCursos;
    private  CursoAdaptador cursoAdaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cursos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerViewCursos = findViewById(R.id.reciclerCursos);
        recyclerViewCursos.setLayoutManager(new LinearLayoutManager(this));

        BD bd = new BD(getApplicationContext());
        cursoAdaptador = new CursoAdaptador(bd.mostrarCursos());
        recyclerViewCursos.setAdapter(cursoAdaptador);
    }

    public List<CursoModelo> obetenerCurso(){
        List<CursoModelo> cursoModelos = new ArrayList<>();
        cursoModelos.add(new CursoModelo("Adrian Andres ","Colombia","Ing"));
        cursoModelos.add(new CursoModelo("Vegeta ","MEXICO","ing"));
        return cursoModelos;
    }
}
