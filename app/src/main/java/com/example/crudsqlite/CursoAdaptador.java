package com.example.crudsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CursoAdaptador extends RecyclerView.Adapter<CursoAdaptador.ViewHolder> {

    public static class  ViewHolder extends  RecyclerView.ViewHolder{
        private TextView codigo, curso,carrera;


        public ViewHolder(View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.tvCodigo);
            curso = itemView.findViewById(R.id.tvCurso);
            carrera = itemView.findViewById(R.id.tvCarrera);
        }
    }
    public List<CursoModelo> cursoList;

    public  CursoAdaptador (List<CursoModelo> cursoList ){
        this.cursoList= cursoList;

    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cursos,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.codigo.setText(cursoList.get(position).getCodigo());
        holder.curso.setText(cursoList.get(position).getCurso());
        holder.carrera.setText(cursoList.get(position).getCarrera());


    }

    @Override
    public int getItemCount() {
        return cursoList.size();
    }


}
