package edu.maine.castonguay.grateful_today;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AddGratitudeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gratitude);

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_toolbar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_gratitude, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println(item.getItemId());
        switch(item.getItemId()){
            case R.id.action_settings:
                return true;

            case R.id.action_add_gratitude:
                EditText gratitudeField = (EditText)findViewById(R.id.editText);
                String gratitude = gratitudeField.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("GRATITUDE", gratitude);
                setResult(Activity.RESULT_OK, intent);
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
