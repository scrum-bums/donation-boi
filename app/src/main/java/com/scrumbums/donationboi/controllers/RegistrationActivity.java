package com.scrumbums.donationboi.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.scrumbums.donationboi.R;
import com.scrumbums.donationboi.model.UserRole;
import com.scrumbums.donationboi.model.entities.User;
import com.scrumbums.donationboi.model.util.AccountValidation;
import com.scrumbums.donationboi.model.util.DatabaseAbstraction;

import io.realm.Realm;

public class RegistrationActivity extends Activity {

    Button regBtn;
    Button cancelBtn;
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
        cancelBtn = findViewById(R.id.button_cancel);
        typeSpinner = findViewById(R.id.field_usertype);
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
                User u;
                if ((u = getUser()) != null) {
                    Realm realm = Realm.getDefaultInstance();
                    if (DatabaseAbstraction.register(getApplicationContext(), u, Realm.getDefaultInstance())) {
                        realm.close();
                        finish();
                    } else {
                        emailField.setError(getString(R.string.error_email_already_registered));
                        emailField.requestFocus();
                    }
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                finish();

            }
        });
    }

    private User getUser() {
        String username = usernameField.getText().toString();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        if (!AccountValidation.isValidEmail(email)) {
            emailField.setError(getString(R.string.error_invalid_email));
            emailField.requestFocus();
            return null;
        }
        if (!AccountValidation.isValidPassword(password)) {
            passwordField.setError(getString(R.string.error_invalid_password));
            passwordField.requestFocus();
            return null;
        }

        UserRole role = UserRole.getRole(typeSpinner.getSelectedItem().toString());
        if (role == null) {
            role = UserRole.USER;
        }

        return new User(username, name, email, password, role);


    }

}
