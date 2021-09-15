package com.example.mobileappdevweek1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import Model.Mahasiswa;

public class AddUserActivity extends AppCompatActivity {

    private TextInputLayout addUser_textInput_nama, addUser_textInput_age, addUser_textInput_address;
    private Button addUser_button_save;
    private Toolbar addUser_toolbar;

    private String nama, age, address;
    private Mahasiswa newMahasiswa, changeMahasiswa;
    private ArrayList <Mahasiswa> listMahasiswa;

    private LoadingActivity loading;
    private Intent intent;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            nama = addUser_textInput_nama.getEditText().getText().toString();
            age = addUser_textInput_age.getEditText().getText().toString();
            address = addUser_textInput_address.getEditText().getText().toString();

            if (!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(address)){
                addUser_button_save.setEnabled(true);
            }else {
                addUser_button_save.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private String condition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        getSupportActionBar().hide();

        initialView();
        toolbarBack();
        condition = intent.getStringExtra("condition");
        if (condition.equalsIgnoreCase("add")) {
            addUser_toolbar.setTitle("Add User");
            addUserProcess();
        }else if (condition.equalsIgnoreCase("edit")){
            addUser_toolbar.setTitle("Edit User");
            editUserProcess();
        }
    }

    private void addUserProcess(){
        addUser_textInput_nama.getEditText().addTextChangedListener(textWatcher);
        addUser_textInput_age.getEditText().addTextChangedListener(textWatcher);
        addUser_textInput_address.getEditText().addTextChangedListener(textWatcher);

        addUser_button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = addUser_textInput_nama.getEditText().getText().toString();
                age = addUser_textInput_age.getEditText().getText().toString();
                address = addUser_textInput_address.getEditText().getText().toString();
                newMahasiswa = new Mahasiswa(nama, age, address);
                listMahasiswa.add(newMahasiswa);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putParcelableArrayListExtra("listMahasiswa", listMahasiswa);
                startActivity(intent);

                loading.startLoading();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading.dismiss();
                        finish();
                    }
                }, 1500);
            }
        });
    }

    private void editUserProcess() {
        int indexMahasiswa = intent.getIntExtra("indexMahasiswa", -1);
        listMahasiswa = intent.getParcelableArrayListExtra("listMahasiswa");
        Toast.makeText(getApplicationContext(), String.valueOf(indexMahasiswa), Toast.LENGTH_SHORT).show();
        addUser_textInput_nama.getEditText().setText(listMahasiswa.get(indexMahasiswa).getNama());
        addUser_textInput_age.getEditText().setText(listMahasiswa.get(indexMahasiswa).getAge());
        addUser_textInput_address.getEditText().setText(listMahasiswa.get(indexMahasiswa).getAddress());

        addUser_button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = addUser_textInput_nama.getEditText().getText().toString();
                age = addUser_textInput_age.getEditText().getText().toString();
                address = addUser_textInput_address.getEditText().getText().toString();
                changeMahasiswa = new Mahasiswa(nama, age, address);
                listMahasiswa.set(indexMahasiswa, changeMahasiswa);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putParcelableArrayListExtra("listMahasiswa", listMahasiswa);
                startActivity(intent);

                loading.startLoading();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading.dismiss();
                        finish();
                    }
                }, 1500);
            }
        });
    }

    private void toolbarBack() {
        addUser_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initialView() {
        addUser_textInput_nama = findViewById(R.id.addUser_textInput_nama);
        addUser_textInput_age = findViewById(R.id.addUser_textInput_age);
        addUser_textInput_address = findViewById(R.id.addUser_textInput_address);
        addUser_button_save = findViewById(R.id.addUser_button_save);
        addUser_toolbar = findViewById(R.id.addUser_toolbar);

        loading = new LoadingActivity(AddUserActivity.this);
        intent = getIntent();
        if (intent.getParcelableArrayListExtra("listMahasiswa") == null){
            listMahasiswa = new ArrayList<>();
        } else {
            listMahasiswa = intent.getParcelableArrayListExtra("listMahasiswa");
        }
    }

}