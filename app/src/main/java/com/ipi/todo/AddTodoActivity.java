package com.ipi.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ipi.todo.DAO.TodoDAO;
import com.ipi.todo.pojos.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddTodoActivity extends AppCompatActivity
{


    private Button btnAdd;
    private Button btnCancel;
    private EditText editText;
    private  Spinner spinner;

    private String TAG = "monTag";

    private List<String> urgencyList = new ArrayList<>();

    private ArrayAdapter<String> spinnerArrayAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG,"onCreate() add called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        spinner = findViewById(R.id.spiUrgency);
        editText = findViewById(R.id.tvTodoName);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        initUrgencyList();

        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String message = editText.getText().toString();
                if (message.length()<3)
                {

                    Toast toast = Toast.makeText(getApplicationContext(),"la description de la tache doit contenir au moins 3 caractères",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP,0,200);
                    toast.show();

                }
                else
                {
                    Intent resultIntent = new Intent();
//                    Bundle bundle = new Bundle();

                    String urgency = spinner.getSelectedItem().toString();

//                    bundle.putSerializable("TODO",new Todo(1,message,urgency));

//                    resultIntent.putExtras(bundle);

                    setResult(RESULT_OK,resultIntent);


                    TodoDAO todoDAO = new TodoDAO(getApplicationContext());
                    Todo todo = new Todo();
                    todo.setName(message);
                    todo.setUrgency(urgency);
                    todoDAO.add(todo);






                    finish();
                }

            }

        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent resultIntent = new Intent();
                setResult(RESULT_CANCELED,resultIntent);
                finish();
            }
        });



        spinner.setAdapter(spinnerArrayAdapter);

        editText.setImeOptions(EditorInfo.IME_ACTION_DONE);

    }



    @Override
    public boolean onSupportNavigateUp()
    {
        Log.d(TAG,"onSupportNavigateUp () add called");

        finish();

        return true;
    }


    public void initUrgencyList()
    {
        urgencyList.add(getString(R.string.liLowUrgency));
        urgencyList.add(getString(R.string.liMediumUrgency));
        urgencyList.add(getString(R.string.liHighUrgency));

        spinnerArrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item_urgency,urgencyList);

    }

}
