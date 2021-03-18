package com.haminhtu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAdd, btnSearch;
    EditText edUsername, edEmail, edPhone;
    RadioGroup rgGender;
    AppDatabase db;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edUsername = findViewById(R.id.edUsername);
        edEmail = findViewById(R.id.edEmail);
        edPhone = findViewById(R.id.edPhone);
        btnAdd = findViewById(R.id.add);
        rgGender = findViewById(R.id.grGender);
        btnSearch = findViewById(R.id.search);
        btnAdd.setOnClickListener(this::onClick);
        btnSearch.setOnClickListener(this::onClick);

        db = AppDatabase.getAppDatabase(this);

    }
    private void insertCustomer(){
        CustomerEntity bm = new CustomerEntity();

        bm.username = edUsername.getText().toString();
        bm.email = edEmail.getText().toString();
        bm.phone_number = edPhone.getText().toString();
        db.bookmarkDao().insertCustomer(bm);

    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                if (!validate()) {
                    return;
                }
                edPhone.setText("");
                insertCustomer();
                break;
            case R.id.search:
                goSearch();
                break;
            case R.id.grGender:
                int selectedId = rgGender.getCheckedRadioButtonId();

                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(this, selectedId, Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }

    private void goSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    private void CheckValidate() {
        if (edUsername.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter Customer Name", Toast.LENGTH_LONG).show();
            return;
        }

        if (edEmail.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter Email Address", Toast.LENGTH_LONG).show();
            return;
        }

        if (edPhone.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter Phone Number", Toast.LENGTH_LONG).show();
            return;
        }

        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select Gender", Toast.LENGTH_LONG).show();
            return;
        }
    }

    private boolean validate() {
        String mes = null;
        if (edUsername.getText().toString().trim().isEmpty()) {
            mes = "Chưa nhập username";
        }
        if (edEmail.getText().toString().isEmpty()) {
            mes = "Chưa nhập Email";
        }
        if (edPhone.getText().toString().trim().isEmpty()) {
            mes = "Chưa nhập số điện thoại";
        }
        if (rgGender.getCheckedRadioButtonId() == -1) {
            mes = "Bạn chưa chọn giới tính";
        }
        if (mes != null) {
            Toast.makeText(this, mes, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
