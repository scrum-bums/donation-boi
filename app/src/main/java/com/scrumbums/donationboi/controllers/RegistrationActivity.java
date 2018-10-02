package com.scrumbums.donationboi.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.AbstractUser;
import com.scrumbums.donationboi.model.Administrator;
import com.scrumbums.donationboi.model.Employee;
import com.scrumbums.donationboi.model.Manager;
import com.scrumbums.donationboi.model.User;

public class RegistrationActivity extends Activity {

    Button regBtn;
    Button canBtn;
    Spinner typeSpinner;
    EditText usernameField;
    EditText nameField;
    EditText passwordField;
    EditText emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regBtn = findViewById(R.id.button_register);
        canBtn = findViewById(R.id.button_cancel);
        typeSpinner = (Spinner) findViewById(R.id.field_usertype);
        usernameField = findViewById(R.id.field_username);
        nameField = findViewById(R.id.field_name);
        emailField = findViewById(R.id.field_email);
        passwordField = findViewById(R.id.field_password);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{
            "User",
            "Employee",
            "Manager",
            "Administrator"
        });
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbstractUser u;
                if ((u = getUser()) != null) {
                    LoginActivity.USERS.add(u);
                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                }
            }
        });

        canBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }

    private AbstractUser getUser() {
        String username = usernameField.getText().toString();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        if (password.length() < 4 || ! email.matches(AbstractUser.EMAILREGEX)) {
            return null;
        }
        switch ((String) typeSpinner.getSelectedItem()) {
            case "User":
                return new User(username, name, email, password);
            case "Employee":
                return new Employee(username, name, email, password);
            case "Manager":
                return new Manager(username, name, email, password);
            case "Administrator":
                return new Administrator(username, name, email, password);
            default:
                return new User(username, name, email, password);
        }
    }

}
