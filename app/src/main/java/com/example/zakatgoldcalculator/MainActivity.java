package com.example.zakatgoldcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etValue;
    RadioGroup radioGroup;
    RadioButton rbKeep, rbWear;
    Button btnCalculate;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.etWeight);
        etValue = findViewById(R.id.etValue);
        radioGroup = findViewById(R.id.radioGroup);
        rbKeep = findViewById(R.id.rbKeep);
        rbWear = findViewById(R.id.rbWear);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(view -> {

            if(etWeight.getText().toString().isEmpty() ||
                    etValue.getText().toString().isEmpty()) {

                Toast.makeText(this,
                        "Please enter all inputs",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            double weight =
                    Double.parseDouble(etWeight.getText().toString());

            double value =
                    Double.parseDouble(etValue.getText().toString());

            double uruf;

            if(rbKeep.isChecked()) {
                uruf = 85;
            }
            else {
                uruf = 200;
            }

            double totalGoldValue = weight * value;

            double zakatPayableGram = weight - uruf;

            if(zakatPayableGram < 0) {
                zakatPayableGram = 0;
            }

            double zakatPayableValue =
                    zakatPayableGram * value;

            double totalZakat =
                    zakatPayableValue * 0.025;

            String result =
                    "Total Gold Value: RM " + totalGoldValue +
                            "\nZakat Payable Value: RM " + zakatPayableValue +
                            "\nTotal Zakat: RM " + totalZakat;

            tvResult.setText(result);

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menu_about) {

            Intent intent =
                    new Intent(MainActivity.this,
                            AboutActivity.class);

            startActivity(intent);

        }
        else if(item.getItemId() == R.id.menu_share) {

            Intent shareIntent = new Intent(Intent.ACTION_SEND);

            shareIntent.setType("text/plain");

            shareIntent.putExtra(Intent.EXTRA_TEXT,
                    "Download my app: https://github.com/yourusername/zakatgoldcalculator");

            startActivity(Intent.createChooser(
                    shareIntent,
                    "Share App"));

        }

        return true;
    }
}