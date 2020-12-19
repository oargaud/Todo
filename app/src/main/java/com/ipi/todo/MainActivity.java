package com.ipi.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ipi.todo.pojos.Todo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{


    public static final String KEY_TODO = "todo";


    private TextView listeTodos;

    private List<Todo> todoList = new ArrayList();





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState!=null)
        {
            Bundle bundle = savedInstanceState.getBundle("SAVE");

            todoList =(List<Todo>)bundle.getSerializable("LIST");
        }

        listeTodos = findViewById(R.id.tvListeTodos);

        afficherListe();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        Bundle bundle = new Bundle();
        bundle.putSerializable("LIST",(Serializable)todoList);

        outState.putBundle("SAVE",bundle);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        // sert a effectuer des action en fonction des item selectionne dans le menu
        switch (item.getItemId())
        {
            case R.id.addTodo:
            {

                // cree l'objet intent qui permet de lancer une activity
                Intent intent = new Intent(getApplicationContext(), AddTodoActivity.class);

                // demarre la nouvelle activite
                startActivityForResult(intent, 2);

                return true;
            }
            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }

    }


    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode == 2)
        {
            if(resultCode == RESULT_OK)
            {
                Todo todo = (Todo)data.getExtras().getSerializable("TODO");
                todoList.add(todo);

                afficherListe();
            }
            else if (resultCode == RESULT_CANCELED)
            {

            }


        }
    }



    public void afficherListe()
    {
        String s = "";
        for (Todo t : todoList)
        {
            s += "\n" + t.getName() +"   "+ t.getUrgency() + "\n";
        }
        listeTodos.setText(s);
    }


}
