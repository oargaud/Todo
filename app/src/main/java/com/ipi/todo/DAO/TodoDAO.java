package com.ipi.todo.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.ipi.todo.MainActivity;
import com.ipi.todo.base.TodoDBHelper;
import com.ipi.todo.pojos.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoDAO extends DAO
{


    List<Todo> todos = new ArrayList<>();


    public TodoDAO(Context context)
    {
        super(new TodoDBHelper(context));
    }


    public Todo find(Long id)
    {

        Todo todo = null;

        Cursor cursor = db.rawQuery
        (
        "SELECT * FROM " + TodoDBHelper.TODO_TABLE_NAME +
            " WHERE " + TodoDBHelper.TODO_KEY_COLUMN_INDEX + " = ? ",
            new String[] {String.valueOf(id)}
        );


        if (cursor != null && cursor.moveToFirst())
        {
            todo = new Todo();
            todo.setId(Integer.parseInt(cursor.getString(0)));
            todo.setName(cursor.getString(1));
            todo.setUrgency(cursor.getString(2));

            cursor.close();
        }


        return todo;
    }


    public List<Todo> list()
    {
        open();

        Cursor cursor = db.rawQuery
                (
                        "SELECT * FROM " + TodoDBHelper.TODO_TABLE_NAME,
                        null
                );

        if(cursor.moveToFirst())
        {
            while(!cursor.isAfterLast())
            {
                Todo todo = new Todo();
                todo.setId(cursor.getInt(TodoDBHelper.TODO_KEY_COLUMN_INDEX));
                todo.setName(cursor.getString(TodoDBHelper.TODO_NAME_COLUMN_INDEX));
                todo.setUrgency(cursor.getString(TodoDBHelper.TODO_URGENCY_COLUMN_INDEX));

                todos.add(todo);

                cursor.moveToNext();
            }


        }


        cursor.close();

        return todos;
    }


    public void add(Todo todo)
    {
        open();

        ContentValues value = new ContentValues();

        value.put(TodoDBHelper.TODO_NAME, todo.getName());
        value.put(TodoDBHelper.TODO_URGENCY, todo.getUrgency());

        db.insert(TodoDBHelper.TODO_TABLE_NAME, null,value);




    }


    public void delete(Todo todo)
    {
        Integer id = todo.getId();

        db.execSQL(" DELETE FROM " + TodoDBHelper.TODO_TABLE_NAME +
                " WHERE " + TodoDBHelper.TODO_KEY + " = "+ id );

    }

    public void delete(Integer id)
    {
        open();

        db.execSQL(" DELETE FROM " + TodoDBHelper.TODO_TABLE_NAME +
                " WHERE " + TodoDBHelper.TODO_KEY + " = "+ id );


    }



}
