package com.example.quiz;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private static List<DataClass> list;
    private OnItemClickListener1 clickListener1;
    private OnItemClickListener2 clickListener2;
    RecyclerViewAdapter(List<DataClass> tlist, OnItemClickListener1 cL1, OnItemClickListener2 cL2)
    {
        list = tlist;
        clickListener1 = cL1;
        clickListener2 = cL2;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view;
        if(viewType == R.layout.cardview)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scores, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position == list.size())
            return R.layout.scores;
        else
            return R.layout.cardview;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position)
    {
        if(position < list.size()) {
            DataClass data = list.get(position);
            holder.question.setText(data.question);
            holder.arr[0].setText(data.options[0]);
            holder.arr[1].setText(data.options[1]);
            holder.arr[2].setText(data.options[2]);
            holder.arr[3].setText(data.options[3]);
        }
    }

    @Override
    public int getItemCount()
    {
        return (list.size()+1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        Button checkScore;
        TextView question;
        RadioButton[] arr = new RadioButton[4];
        TextView displayScore;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            checkScore = itemView.findViewById(R.id.checkScore);
            displayScore = itemView.findViewById(R.id.displayScore);

            question = itemView.findViewById(R.id.question);

            arr[0] = itemView.findViewById(R.id.box1);
            arr[1] = itemView.findViewById(R.id.box2);
            arr[2] = itemView.findViewById(R.id.box3);
            arr[3] = itemView.findViewById(R.id.box4);

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    int clickedID = v.getId();
                    for(int i=0; i<4; i++)
                    {
                        if(arr[i].getId() == clickedID)
                        {
                            clickListener1.addToAns(list.get(getAdapterPosition()), (char)(65+i));
                        }
                        else
                            arr[i].setChecked(false);
                    }
                }
            };

            for(int i=0; i<4; i++)
            {
                if(arr[i] != null)
                    arr[i].setOnClickListener(listener);
            }

            if(itemView.findViewById(R.id.checkScore)!=null)
            {
                checkScore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int score = clickListener2.checkAns();
                        displayScore.setText("Score = "+score);
                    }
                });
            }
        }
    }


    public interface OnItemClickListener1
    {
        void addToAns(DataClass data, char ch);
    }

    public interface OnItemClickListener2
    {
        int checkAns();
    }
}
