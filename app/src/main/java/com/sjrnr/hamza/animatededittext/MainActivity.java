package com.sjrnr.hamza.animatededittext;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Runnable runnableCodeDelete;
    Runnable runnableCodeType;
    public int x =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




        final EditText editText = (EditText) findViewById(R.id.editText);


        Button stealFocus= (Button) findViewById(R.id.button);
        stealFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.clearFocus();
            }
        });



        final String[] places = {"Lagos", "Ibadan", "Abu Dhabi", "Amsterdam", "London"};
        editText.setHint(places[0]);

        // Create the Handler object (on the main thread by default)
        final Handler handler = new Handler();
        // Define the code block to be executed
        runnableCodeDelete = new Runnable() {
            //int x = 0;

            @Override
            public void run() {
                // Do something here on the main thread
                //Log.d("Handlers", "Called on main thread");

                //x = (x + 1) % 5;
                String hint = editText.getHint().toString();
                int hintLength = hint.length();
                if (hintLength > 0) {
                    hint = hint.substring(0, hintLength - 1);
                    editText.setHint(hint);
                } else {
                    x = (x + 1) % 5;
                    hint = nextPlace(x);
                    editText.setHint(hint);
                }
                //editText.setHint();
                // Repeat this the same runnable code block again another 2 seconds
                handler.postDelayed(this, 400);
            }

            public String nextPlace(int x) {
                return places[x];
            }

        };

        runnableCodeType = new Runnable() {
            int x = 0;

            @Override
            public void run() {
                // Do something here on the main thread
                //Log.d("Handlers", "Called on main thread");

                //x = (x + 1) % 5;
                String hint = editText.getHint().toString();
                int hintLength = hint.length();
                if (hintLength > 0) {
                    hint = hint.substring(0, hintLength - 1);
                    editText.setHint(hint);
                } else {
                    x = (x + 1) % 5;
                    hint = nextPlace(x);
                    editText.setHint(hint);
                }
                //editText.setHint();
                // Repeat this the same runnable code block again another 2 seconds
                handler.postDelayed(this, 400);
            }

            public String nextPlace(int x) {
                return places[x];
            }

        };

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (b) {
                    Log.d("focus", "yep");
                    startAnimation(handler, editText, false);
                } else {
                    Log.d("focus", "lost");
                    startAnimation(handler, editText, true);
                }
            }
        });

        startAnimation(handler, editText, true);
    }

    public void startAnimation(Handler handler, final EditText editText, boolean status){



        if (status) {
            // Start the initial runnable task by posting through the handler
            handler.post(runnableCodeDelete);
        }
        else{
            handler.removeCallbacks(runnableCodeDelete);
            handler.removeMessages(0);
            editText.setHint("");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
