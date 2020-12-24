package com.ipi.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ipi.todo.DAO.TodoDAO;
import com.ipi.todo.base.TodoDBHelper;
import com.ipi.todo.pojos.Todo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{


//    public static final String KEY_TODO = "todo";

    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;

    private TextView listeTodos;

    private List<Todo> todoList = new ArrayList();

    private String TAG = "monTag";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG,"onCreate() main called");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_main);







//        if (savedInstanceState!=null)
//        {
//            Bundle bundle = savedInstanceState.getBundle("SAVE");
//
//            todoList =(List<Todo>)bundle.getSerializable("LIST");
//        }

        listeTodos = findViewById(R.id.tvListeTodos);


        afficherListe();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        Log.d(TAG,"onSaveInstanceState() main called");
        super.onSaveInstanceState(outState);

        Bundle bundle = new Bundle();
        bundle.putSerializable("LIST",(Serializable)todoList);

        outState.putBundle("SAVE",bundle);



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.d(TAG,"onCreateOptionsMenu() main called");

        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        Log.d(TAG,"onOptionsItemSelected() main called");

        switch (item.getItemId())
        {
            case R.id.addTodo:
            {

                Intent intent = new Intent(getApplicationContext(), AddTodoActivity.class);

                startActivityForResult(intent, 2);

                return true;
            }
            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d(TAG,"onActivityResult() main called");
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2)
        {
            if(resultCode == RESULT_OK)
            {
//                Todo todo = (Todo)data.getExtras().getSerializable("TODO");
//                todoList.add(todo);

                afficherListe();
            }
            else if (resultCode == RESULT_CANCELED)
            {

            }

        }
    }



    public void afficherListe()
    {

        TodoDAO todoDAO = new TodoDAO(this);
        todoList = todoDAO.list();

        recyclerView = findViewById(R.id.rvTodo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        todoAdapter = new TodoAdapter(todoList);
        recyclerView.setAdapter(todoAdapter);








//
//        for (Todo todo : todoList)
//        {
//            Log.d("Request",todo.getName());
//        }
//
//
//
//        String s = "";
//        for (Todo t : todoList)
//        {
//            s += t.getName() +"\n       niveau d'urgence : "+ t.getUrgency() + "\n";
//        }
//        listeTodos.setText(s);
    }




public class TodoAsyncTasks extends AsyncTask<String,String,List<Todo>>
{
    @Override
    protected List<Todo> doInBackground(String... strings)
    {
        TodoDAO todoDAO = new TodoDAO(getApplicationContext());
        List<Todo> responseTodo = new ArrayList<>();

        try
        {
            responseTodo = todoDAO.list();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return responseTodo;
    }


    @Override
    protected void onPostExecute(List<Todo> responseTodo)
    {
        StringBuilder sb = new StringBuilder();

        for(Todo todo : responseTodo)
        {
            sb.append(todo.getName() + " // " + todo.getUrgency() + "\n");
        }

        //tvTodos.setText(sb.toString());
    }
}


}
