package com.example.quiz;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuizList extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener1, RecyclerViewAdapter.OnItemClickListener2 {
    static List<DataClass> list = new ArrayList<>();
    static char ans[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list, this::addToAns, this::checkAns);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    public void setList()
    {
        ans = new char[4];
        list.add(new DataClass("What is a group of crows called?", new String[]{"A murder", "An embarrassment", "A flock", "A gang"}, 'A'));
        list.add(new DataClass("How many time zones are there in Russia?", new String[]{"5", "12", "7", "11"}, 'D'));
        list.add(new DataClass("Book: The Song of Achilles; Author:?", new String[]{"J.R.R. Tolkien", "J.K. Rowling", "Madeline Miller", "Agatha Christie"}, 'C'));
        list.add(new DataClass("Which is the most common training command taught to dogs?", new String[]{"Stay", "Sit", "Heel", "Down"}, 'B'));
    }

    public void addToAns(DataClass data, char ch)
    {
        int position = list.indexOf(data);
        ans[position] = ch;
    }

    public int checkAns()
    {
        int score = 0;
        for(int i=0; i<4; i++)
        {
            if(ans[i] == list.get(i).ans)
            {
                score += 1;
            }
        }
        return score;
    }
}
