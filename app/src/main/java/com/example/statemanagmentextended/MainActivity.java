package com.example.statemanagmentextended;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private CountViewModel countViewModel;
    private TextView textViewCount;
    private EditText editedText;
    private CheckBox checkboxes;
    private Switch switche;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCount = findViewById(R.id.textViewCount);
        Button buttonIncrement = findViewById(R.id.buttonIncrement);
        editedText = findViewById(R.id.editedText);
        checkboxes = findViewById(R.id.checkboxes);
        switche = findViewById(R.id.switche);

        countViewModel = new ViewModelProvider(this).get(CountViewModel.class);
        updateCountText();

        switche.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countViewModel.incrementCount();
                updateCountText();
            }
        });

        checkboxes.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                editedText.setVisibility(View.VISIBLE);
                checkboxes.setText("Opcja zaznaczona");
            } else {
                editedText.setVisibility(View.GONE);
            }
        });

        editedText.setVisibility(View.GONE);
    }
    private void updateCountText() {
        textViewCount.setText("Licznik: " + countViewModel.getCount());
    }
}
