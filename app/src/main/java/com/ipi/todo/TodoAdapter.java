package com.ipi.todo;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ipi.todo.DAO.TodoDAO;
import com.ipi.todo.pojos.Todo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder>
{

    private List<Todo> todos;

    public class TodoViewHolder extends RecyclerView.ViewHolder
    {

        public TextView tvName;
        public TextView tvUrgency;
        public Button btnDelete;

        public TodoViewHolder(View itemView)
        {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvUrgency = itemView.findViewById(R.id.tvUrgency);
            btnDelete = itemView.findViewById(R.id.btnDel);
        }

    }


    public TodoAdapter(List<Todo> todos)
    {
        this.todos = todos;
    }


    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item,parent,false);

        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position)
    {
        Todo todo = todos.get(position);
        holder.tvName.setText(todo.getName());
        holder.tvUrgency.setText(todo.getUrgency());
        final Integer test = todo.getId();
//        final Context context = getApplicationContext();
        holder.btnDelete.setText("DEL");
        holder.btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("bouton del","id "+ test);

//                TodoDAO todoDAO = new TodoDAO(context);
//                todoDAO.delete(test);



            }
        });

    }

    @Override
    public int getItemCount()
    {
        return todos.size();
    }


}
