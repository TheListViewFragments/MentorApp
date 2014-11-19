package com.detroitlabs.mentorapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.view.Menu;
import android.view.MenuItem;
>>>>>>> 5485b41df21dc4d868dd97049116322b677a1895
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.detroitlabs.mentorapp.R;


public class MainActivity extends Activity {
    EditText editText;
    Button button;
<<<<<<< HEAD
=======
    String subreddit = "";

    static final String SUBREDDIT_CHOICE_KEY = "SUBREDDIT_CHOICE_KEY";
>>>>>>> 5485b41df21dc4d868dd97049116322b677a1895

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
=======

        editText = (EditText) findViewById(R.id.search_string_text);
        button = (Button) findViewById(R.id.first_page_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (editText.getText().toString().trim().length() != 0) {
                    subreddit = (editText.getText().toString().trim());

                    Intent launchListViewIntent = new Intent(getApplicationContext(), SubredditListViewActivity.class);
                    launchListViewIntent.putExtra(SUBREDDIT_CHOICE_KEY, subreddit);
                    startActivity(launchListViewIntent);


                }

            }
        });
    }
>>>>>>> 5485b41df21dc4d868dd97049116322b677a1895

        editText = (EditText) findViewById(R.id.search_string_text);
        button = (Button) findViewById(R.id.first_page_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchListViewIntent = new Intent(getApplicationContext(), SubredditListViewActivity.class);
                startActivity(launchListViewIntent);

            }
        });
    }
}
