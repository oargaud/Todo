package com.ipi.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        // Get reference of widgets from XML layout
        spinner = (Spinner) findViewById(R.id.spiUrgency);

        editText = findViewById(R.id.tvTodoName);

        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);


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
                    String urgency = spinner.getSelectedItem().toString();
                    Intent intent = new Intent();
                    intent.putExtra("MESSAGE",message);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("TODO",new Todo(1,message,urgency));

                    intent.putExtras(bundle);

                    setResult(RESULT_OK,intent);
                    finish();//finishing activity
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


        // Initializing a String Array
        String[] urgency = new String[]
        {
            getString(R.string.liLowUrgency),
            getString(R.string.liMediumUrgency),
            getString(R.string.liHighUrgency)
        };

        final List<String> urgencyList = new ArrayList<>(Arrays.asList(urgency));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item_urgency,urgencyList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item_urgency);
        spinner.setAdapter(spinnerArrayAdapter);


















    }
}
