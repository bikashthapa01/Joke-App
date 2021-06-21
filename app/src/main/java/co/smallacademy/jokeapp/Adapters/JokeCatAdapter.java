package co.smallacademy.jokeapp.Adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.smallacademy.jokeapp.R;
import co.smallacademy.jokeapp.fragments.Jokes;

public class JokeCatAdapter extends RecyclerView.Adapter<JokeCatAdapter.ViewHolder> {
        List<String> categories;
        int selected_position = 0;

    public JokeCatAdapter(List<String> cats){
        this.categories = cats;
    }

    @NonNull
    @Override
    public JokeCatAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  JokeCatAdapter.ViewHolder holder, int position) {
            holder.catName.setText(categories.get(position));

            holder.card.setBackgroundColor(selected_position == position ? Color.RED:Color.WHITE);
            holder.card.setRadius(10);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView catName;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            catName = itemView.findViewById(R.id.catName);
            card = itemView.findViewById(R.id.mCardView);
        }


        @Override
        public void onClick(View v) {
            // Below line is just like a safety check, because sometimes holder could be null,
            // in that case, getAdapterPosition() will return RecyclerView.NO_POSITION
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            // Updating old as well as new positions
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);

            // Do your another stuff for your onClick
            if(categories.get(selected_position) == "Any"){
                loadFragment(new Jokes(v.getResources().getString(R.string.url)+"Any?amount=10"),v);
                //Log.d("TAG", "onClick: "+v.getResources().getString(R.string.url)+"Any?amount=10");
            }
            if(categories.get(selected_position) == "Programming"){
                loadFragment(new Jokes(v.getResources().getString(R.string.url)+"Programming?amount=10"),v);
            }
            if(categories.get(selected_position) == "Dark"){
                loadFragment(new Jokes(v.getResources().getString(R.string.url)+"Dark?amount=10"),v);
            }

            if(categories.get(selected_position) == "Spooky"){
                loadFragment(new Jokes(v.getResources().getString(R.string.url)+"Spooky?amount=10"),v);
            }

            if(categories.get(selected_position) == "Misc"){
                loadFragment(new Jokes(v.getResources().getString(R.string.url)+"Misc?amount=10"),v);
            }

            if(categories.get(selected_position) == "Pun"){
                loadFragment(new Jokes(v.getResources().getString(R.string.url)+"Programming?amount=10"),v);
            }

            if(categories.get(selected_position) == "Christmas"){
                loadFragment(new Jokes(v.getResources().getString(R.string.url)+"Christmas?amount=10"),v);
            }
            
        }
    }

    public void loadFragment(Fragment fragment,View v){
        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction().replace(R.id.fragment_frame,fragment);
        transaction.commit();
    }

}
