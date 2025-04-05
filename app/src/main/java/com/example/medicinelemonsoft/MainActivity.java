package com.example.medicinelemonsoft;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText edSearch;

    ArrayList<MedicineModel> arrayList = new ArrayList<>();
    MedicineSqlite medicineSqlite;

    MedicineAdepter adepter;

   DrawerLayout drawerLayout;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        medicineSqlite = new MedicineSqlite(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        edSearch = findViewById(R.id.edSearch);



     setUpToolbar();



















        loadData("");


        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String key = edSearch.getText().toString();
                loadData(key);






            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });







    }

    public void loadData(String key){

        arrayList.clear();


        Cursor cursor;


        if (key.isEmpty()){

            cursor = medicineSqlite.loadData();
        }else {

            cursor = medicineSqlite.searchData(key);
        }


        if (cursor!=null&&cursor.getCount()>0){


            while (cursor.moveToNext()){


                int id = cursor.getInt(0);
                String brand_name = cursor.getString(3);
                String from = cursor.getString(4);
                String strength = cursor.getString(5);
                String price = cursor.getString(6);


               arrayList.add(new MedicineModel(id,brand_name,from,strength,"Unit price: "+price+" BDT"));

                adepter = new MedicineAdepter(arrayList,this);
                recyclerView.setAdapter(adepter);



            }

            cursor.close();




        }














    }

public void setUpToolbar(){

    // Set up the toolbar
    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    // Set up the drawer layout and toggle
    drawerLayout = findViewById(R.id.drawer);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open, R.string.close);

// Change the overflow icon color
    if (toolbar.getOverflowIcon() != null) {
        toolbar.getOverflowIcon().setTint(getResources().getColor(R.color.menu_icon_color));
    }

   toggle.getDrawerArrowDrawable().setColor(getColor(R.color.white));



    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();



    // Handle navigation item clicks
    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.nav_home) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check this out!");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Here is some content to share.");
                startActivity(Intent.createChooser(shareIntent, "Share via"));
                Toast.makeText(MainActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (id == R.id.nav_profile) {
                Toast.makeText(MainActivity.this, "Profile clicked", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (id == R.id.nav_settings) {
                Toast.makeText(MainActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            }

            drawerLayout.closeDrawers();
            return true;
        }
    });



}

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        }else {

            super.onBackPressed();
        }






    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_about) {
            Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}