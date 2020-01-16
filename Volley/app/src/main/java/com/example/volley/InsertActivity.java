package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class InsertActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etContent;
    Button btnInsert;
    LinearLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        btnInsert = findViewById(R.id.btn_insert);
        loading = findViewById(R.id.loading);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String content = etContent.getText().toString().trim();

                insert(title, content);
            }
        });
    }

    private void insert(String title, String content){
        // the data should be serialize to JSONObject
        // you can't send a model or anything except JSONObject
        JSONObject param = new JSONObject();
        try{
            param.put("title", title);
            param.put("content", content);
        }
        catch (Exception e){

        }


        // to perform some request to api webservice, we can use 2 request type :
        // 1. JsonArrayRequest : is used if the return type is an JSON ARRAY
        // 2. JsonObjectRequest : is used if the return type is an JSON Object

        // both of the type have the same parameter (there are 5 parameters):
        // 1. Method (GET, POST, PUT, DELETE, etc.)
        // 2. URL (in string)
        // 3. JsonRequest (parameter that sent to the api, in JSONObject)
        // 4. Callback Success Response
        // 5. Callback Error Response

        // show loading
        showLoading(true);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://raderking-gg.herokuapp.com/note",
                param,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        showLoading(false);
                        Toast.makeText(InsertActivity.this, "Insert Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showLoading(false);
                    }
                }
        );

        // don't forget to add the request to queue here
        RequestQueue queue = Volley.newRequestQueue(InsertActivity.this);
        queue.add(request);
    }

    private void showLoading(boolean isShow){
        loading.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
}
