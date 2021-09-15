package com.example.mobileappdevweek1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Model.Mahasiswa;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton main_floatButt_addUsers;
    RecyclerView main_recyclerView_listUser;
    TextView main_text_noData;

    ArrayList<Mahasiswa> listMahasiswa;
    Mahasiswa varMahasiswa;
    RVAdapter rvAdapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initialView();
        setRecyclerView();
        onClickListener();
    }

    private void initialView() {
        main_floatButt_addUsers = findViewById(R.id.main_floatButt_addUsers);
        main_recyclerView_listUser = findViewById(R.id.main_recyclerView_listUser);
        main_text_noData = findViewById(R.id.main_text_noData);
        intent = getIntent();
        varMahasiswa = new Mahasiswa();
        getNewUserData();
        rvAdapter = new RVAdapter(listMahasiswa);
    }

    private void setRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        main_recyclerView_listUser.setLayoutManager(manager);
        main_recyclerView_listUser.setAdapter(rvAdapter);
    }

    private void onClickListener(){
        main_floatButt_addUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddUserActivity.class);
                intent.putParcelableArrayListExtra("listMahasiswa", listMahasiswa);
                intent.putExtra("condition", "add");
                startActivity(intent);
            }
        });
    }

    private void getNewUserData(){
        listMahasiswa = intent.getParcelableArrayListExtra("listMahasiswa");

        if (listMahasiswa == null){
            main_text_noData.setVisibility(View.VISIBLE);
            main_recyclerView_listUser.setVisibility(View.GONE);
            listMahasiswa = new ArrayList<>();
        } else {
            main_text_noData.setVisibility(View.GONE);
            main_recyclerView_listUser.setVisibility(View.VISIBLE);
        }
    }
}