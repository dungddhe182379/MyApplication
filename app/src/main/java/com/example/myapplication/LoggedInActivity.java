package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoggedInActivity extends AppCompatActivity {

    private Button buttonLogout;

    private void bindingView() {
        buttonLogout = findViewById(R.id.buttonLogout);
    }

    private void bindingAction() {
        buttonLogout.setOnClickListener(this::onLogoutClicked);

    }

    private void onLogoutClicked(View view) {
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish(); // Close LoggedInActivity and return to MainActivity
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_logged_in);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindingView();
        bindingAction();

    }


}
