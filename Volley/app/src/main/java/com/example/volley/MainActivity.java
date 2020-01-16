package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tvNoteList;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNoteList = findViewById(R.id.tv_list_note);
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData(){
        // the function to get data
        // to perform some request to api webservice, we can use 2 request type :
        // 1. JsonArrayRequest : is used if the return type is an JSON ARRAY
        // 2. JsonObjectRequest : is used if the return type is an JSON Object

        // both of the type have the same parameter (there are 5 parameters):
        // 1. Method (GET, POST, PUT, DELETE, etc.)
        // 2. URL (in string)
        // 3. JsonRequest (parameter that sent to the api, in JSONObject)
        // 4. Callback Success Response
        // 5. Callback Error Response

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://raderking-gg.herokuapp.com/notes",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Success
                        try{
                            // data is set to the string, and then we display the string
                            // in barbaric way :)
                            String listNote = "";
                            for(int i=0; i<response.length(); i++){
                                // get the object based on the index
                                JSONObject object = response.getJSONObject(i);

                                // get the value from the property in current object
                                String id = object.getString("_id");
                                String title = object.getString("title");
                                String content = object.getString("content");

                                // append the listNote string with the data from api
                                // once again, in barbaric way.
                                listNote += id + "\n" + title + "\n" + content + "\n\n";
                            }

                            // set the text to view
                            tvNoteList.setText(listNote);
                        }
                        catch (Exception e){

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // please add some code to handle
                        // either you want to show a toast or something :)
                    }
                }
        );

        // don't forget to add the request to queue here
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
