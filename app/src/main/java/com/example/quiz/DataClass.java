package com.example.quiz;

public class DataClass
{
    String question;
    String[] options = new String[4];
    char ans;

    DataClass(String tq, String[] to, char tans)
    {
        question = tq;
        for(int i=0; i<4; i++)
        {
            options[i] = to[i];
        }
        ans = tans;
    }
}
