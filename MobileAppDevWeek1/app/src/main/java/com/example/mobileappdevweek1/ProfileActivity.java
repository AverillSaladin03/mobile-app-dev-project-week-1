package com.example.mobileappdevweek1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

import Model.Mahasiswa;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar profile_toolbar;
    private TextView profile_text_nama, profile_text_age, profile_text_address;
    private ImageView profile_image_edit, profile_image_delete;

    private Intent intent;
    private int indexMahasiswa;
    private ArrayList<Mahasiswa> listMahasiswa;
    private Mahasiswa currentMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        initialView();
        listener();


    }

    private void listener() {
        profile_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profile_image_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), AddUserActivity.class);
                intent.putExtra("condition", "edit");
                intent.putExtra("indexMahasiswa", indexMahasiswa);
                intent.putParcelableArrayListExtra("listMahasiswa", listMahasiswa);
                startActivity(intent);
            }
        });

        profile_image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMahasiswa = listMahasiswa.get(indexMahasiswa);
                new AlertDialog.Builder(ProfileActivity.this)
                        .setIcon(R.drawable.ic_action_deleteicon)
                        .setTitle("Delete " + currentMahasiswa.getNama() + "'s Data?")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listMahasiswa.remove(indexMahasiswa);
                                intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });
    }

    private void initialView() {
        profile_toolbar = findViewById(R.id.profile_toolbar);
        profile_text_age = findViewById(R.id.profile_text_age);
        profile_text_nama = findViewById(R.id.profile_text_nama);
        profile_text_address = findViewById(R.id.profile_text_address);
        profile_image_edit = findViewById(R.id.profile_image_edit);
        profile_image_delete = findViewById(R.id.profile_image_delete);

        intent = getIntent();
        indexMahasiswa = intent.getIntExtra("indexMahasiswa", -1);
        listMahasiswa = intent.getParcelableArrayListExtra("listMahasiswa");
        getDataUser();
    }

    private void getDataUser(){
        String nama_temp = listMahasiswa.get(indexMahasiswa).getNama();
        String age_temp = listMahasiswa.get(indexMahasiswa).getAge();
        String address_temp = listMahasiswa.get(indexMahasiswa).getAddress();

        profile_text_nama.setText(nama_temp);
        profile_text_age.setText(age_temp);
        profile_text_address.setText(address_temp);
    }
}