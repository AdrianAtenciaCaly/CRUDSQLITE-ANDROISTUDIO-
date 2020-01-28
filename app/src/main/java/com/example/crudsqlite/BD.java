package com.example.crudsqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BD extends SQLiteOpenHelper {

    private  static  final String NOMBRE_BD = "cursos_sqlite.bd";
    private  static  final int VERSION_BD = 1;
    private  static  final String TABLA_CURSOS = "CREATE TABLE CURSOS (CODIGO TEXT PRIMARY KEY, CURSO TEXT, CARRERA TEXT)";

    public BD (Context contex){
        super(contex, NOMBRE_BD, null, VERSION_BD);

    }
//ESTE METODO SE EJECUTA AUTOMATICAMENTE Y CREARA LAS TABLAS QUE CONFROMAN LAS BD
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_CURSOS);

    }
    // METODO SE EJECUTA AUTOMATICAMENTE UNICAMENTE CUANDO SEA NECESARIO UNA ACTUALIZACION EN LA ESTRUCTURA DE BD O UNA CONVERSION DE DATOS
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTSC" + TABLA_CURSOS);
        db.execSQL(TABLA_CURSOS);
    }

    public void agregarCursos(String codigo, String curso, String carrera ){
        SQLiteDatabase database = getWritableDatabase();
        if (database!=null){
            database.execSQL("INSERT INTO CURSOS VALUES ('"+codigo+"','"+curso+"','"+carrera+"')");
            database.close();
        }
    }

    public List<CursoModelo> mostrarCursos(){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM CURSOS",null);
        List<CursoModelo> cursos = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                cursos.add(new CursoModelo(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return cursos;
    }

    public void buscarCursos(CursoModelo cursoModelo, String codigo){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT *FROM CURSOS WHERE CODIGO ='"+codigo+"'",null);

        if (cursor.moveToFirst()){
            do {

                cursoModelo.setCurso(cursor.getString(1));
                cursoModelo.setCarrera(cursor.getString(2));
            }while (cursor.moveToNext());
        }else{

        }

    }


    public void actalizarCurso(String codigo, String curso, String carrera ){
        SQLiteDatabase database = getWritableDatabase();
        if (database!=null){
            database.execSQL("UPDATE CURSOS SET  CURSO = '"+curso+"',CARRERA='"+carrera+"' WHERE CODIGO ='"+codigo+"'" );
            database.close();
        }
    }
    public void eliminarCurso(String codigo){
        SQLiteDatabase database = getWritableDatabase();
        if (database!=null){
            database.execSQL("DELETE FROM CURSOS WHERE CODIGO ='"+codigo+"'" );
            database.close();
        }
    }
}
