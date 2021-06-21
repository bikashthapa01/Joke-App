package co.smallacademy.jokeapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.smallacademy.jokeapp.R;
import co.smallacademy.jokeapp.model.Joke;

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> {
    List<Joke> jokes;

    public JokeAdapter(List<Joke> jokes){
        this.jokes = jokes;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.jokes_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if(jokes.get(position).getType().equals("single")){
                holder.firstLine.setText(jokes.get(position).getJoke());
                holder.secondLine.setText("");
            }else {
                holder.firstLine.setText(jokes.get(position).getSetup());
                holder.secondLine.setText(jokes.get(position).getDelivery());
            }

    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView firstLine,secondLine;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstLine = itemView.findViewById(R.id.firstLine);
            secondLine = itemView.findViewById(R.id.secondLine);
        }
    }
}
