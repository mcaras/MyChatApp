package com.marlon1300.mychatapp;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends ListActivity {

    private Firebase mFirebasedRef;
    private EditText editText;
    private Button button;
    private FirebaseListAdapter firebaseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        mFirebasedRef = new Firebase("https://vivid-heat-3175.firebaseio.com");
            button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.edittext);
        firebaseListAdapter = new FirebaseListAdapter<ChatMesage>(mFirebasedRef, ChatMesage.class, R.layout.message_layout, this) {
            @Override
            protected void populateView(View v, ChatMesage model) {
                ((TextView)v.findViewById(R.id.name)).setText(model.getName());
                ((TextView)v.findViewById(R.id.name)).setText(model.getMessage());
            }
        };
        setListAdapter(firebaseListAdapter);
    }

    public void onclickbutton(View view ){
        String message = editText.getText().toString();

        Map<String,String> map = new HashMap<>();
        map.put("name", "test");
        map.put("message", message);
        mFirebasedRef.push().setValue(map);
        editText.setText("");


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
