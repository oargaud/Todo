package com.ipi.todo.pojos;

import java.io.Serializable;

public class Todo implements Serializable
{

    private Integer id;

    private String name;

    private String urgency;


    public Todo()
    {
    }


    public Todo(Integer id, String name, String urgency)
    {
        this.id = id;
        this.name = name;
        this.urgency = urgency;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrgency()
    {
        return urgency;
    }

    public void setUrgency(String urgency)
    {
        this.urgency = urgency;
    }




}
