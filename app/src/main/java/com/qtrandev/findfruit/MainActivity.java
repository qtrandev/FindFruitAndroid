package com.qtrandev.findfruit;

import android.app.LauncherActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img=(ImageView)findViewById(R.id.imageView);
        Drawable myDrawable = getResources().getDrawable(R.drawable.logo);
        img.setImageDrawable(myDrawable);

        Button mapButton = (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mapButtonClicked();
            }
        });

        Button profileButton = (Button) findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                profileButtonClicked();
            }
        });

        Button adventureButton = (Button) findViewById(R.id.adventureButton);
        adventureButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adventureButtonClicked();
            }
        });
    }

    private void mapButtonClicked() {
        Intent i = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(i);
    }

    private void profileButtonClicked() {
        Intent i = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(i);
    }

    private void adventureButtonClicked() {
        Intent i = new Intent(MainActivity.this, AdventureActivity.class);
        startActivity(i);
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
