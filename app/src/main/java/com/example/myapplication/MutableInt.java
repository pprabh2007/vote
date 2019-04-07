package com.example.myapplication;

public class MutableInt {
    private int value;
    protected void setValue(int value)
    {
        this.value=value;
    }
    protected int getValue()
    {
        return this.value;
    }
}
