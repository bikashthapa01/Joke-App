package co.smallacademy.jokeapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.smallacademy.jokeapp.Adapters.JokeAdapter;
import co.smallacademy.jokeapp.R;
import co.smallacademy.jokeapp.model.Joke;


public class Jokes extends Fragment {
    public static final String TAG = "TAG";
    String jokesUrl;
    RecyclerView jokesList;
    JokeAdapter adapter;
    List<Joke> jokes;

    public Jokes(String Url){
        this.jokesUrl = Url;
        jokes = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_jokes, container, false);
        jokesList = v.findViewById(R.id.jokesList);
        adapter = new JokeAdapter(jokes);
        jokesList.setLayoutManager(new LinearLayoutManager(getContext()));
        jokesList.setAdapter(adapter);
        getJokes(jokesUrl);
        return v ;
    }

    public void getJokes(String url){
        // fetch jokes and populate the data
        Log.d(TAG, "getJokes: " + url);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d(TAG, "onResponse: " + response);
                try {
                    JSONArray jokesArray = response.getJSONArray("jokes");
                    for(int i = 0; i < Integer.parseInt(response.getString("amount")); i++){
                        //Log.d(TAG, "onResponse: " + jokesArray.getJSONObject(i));
                        JSONObject jokeData = jokesArray.getJSONObject(i);
                        Joke j = new Joke();
                        j.setType(jokeData.getString("type"));
                        if(jokeData.getString("type").equals("single")){
                            j.setJoke(jokeData.getString("joke"));
                        }else {
                            j.setSetup(jokeData.getString("setup"));
                            j.setDelivery(jokeData.getString("delivery"));
                        }

                        jokes.add(j);
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getLocalizedMessage());
            }
        });

        queue.add(objectRequest);
    }
}