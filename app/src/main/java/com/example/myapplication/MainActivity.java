package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    private ActivityResultLauncher<Intent> loggedInActivityLauncher;

    private void bindingView() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        loggedInActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), this::onActivityResultCallBack);
    }



    private void onActivityResultCallBack(ActivityResult activityResult) {
        if (activityResult.getResultCode() == RESULT_OK) {
            editTextEmail.setText("");
            editTextPassword.setText("");
            Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
        }
    }

    private void bindingAction() {
        buttonLogin.setOnClickListener(this::onLoginClick);
    }

    private void onLoginClick(View view) {
        String email = editTextEmail.getText().toString().toLowerCase().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.equals("admin") && password.equals("123456")) {
            Toast.makeText(this, "login successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoggedInActivity.class);
            loggedInActivityLauncher.launch(intent);
        } else {
            Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // You can keep or remove EdgeToEdge based on your preference
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindingView();
        bindingAction();
    }
}
