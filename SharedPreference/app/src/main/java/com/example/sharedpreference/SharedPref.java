package com.example.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sharedpreference.model.User;

public class SharedPref {

    // What's the function of this class :
    // take care of saving and loading data from shared preferences xml.
    // remember, the data is saved in the form of xml.

    private SharedPreferences sharedPreferences;

    public SharedPref(Context context){
        sharedPreferences = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);

    }

    public void save(User user){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", user.getUsername());
        editor.putString("password", user.getPassword());
        // you can use apply() or commit() to saving data to shared preferences xml
        editor.apply();
    }

    public User load(){
        User user = new User();
        user.setUsername(sharedPreferences.getString("username", ""));
        user.setPassword(sharedPreferences.getString("password", ""));
        return user;
    }


}
