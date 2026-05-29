package com.example.zakatgoldcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etValue;

    Spinner spJenis;

    Button btnCalculate, btnReset;

    TextView tvGoldValue, tvPayable, tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.etWeight);
        etValue = findViewById(R.id.etValue);

        spJenis = findViewById(R.id.spJenis);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        tvGoldValue = findViewById(R.id.tvGoldValue);
        tvPayable = findViewById(R.id.tvPayable);
        tvResult = findViewById(R.id.tvResult);

        String[] jenis = {
                "Select Type",
                "Keep",
                "Wear"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                jenis);

        spJenis.setAdapter(adapter);

        btnCalculate.setOnClickListener(view -> {

            if (etWeight.getText().toString().trim().isEmpty() ||
                    etValue.getText().toString().trim().isEmpty()) {

                Toast.makeText(
                        MainActivity.this,
                        "Please enter all fields before calculation",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            String selectedJenis =
                    spJenis.getSelectedItem().toString();

            if (selectedJenis.equals("Select Type")) {

                Toast.makeText(
                        MainActivity.this,
                        "Please select type",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            double weight = Double.parseDouble(
                    etWeight.getText().toString());

            double value = Double.parseDouble(
                    etValue.getText().toString());

            double uruf;

            if (selectedJenis.equals("Keep")) {

                uruf = 85;

            } else {

                uruf = 200;
            }

            double totalGoldValue =
                    weight * value;

            double zakatPayableGram =
                    weight - uruf;

            if (zakatPayableGram < 0) {

                zakatPayableGram = 0;
            }

            double zakatPayableValue =
                    zakatPayableGram * value;

            double totalZakat =
                    zakatPayableValue * 0.025;

            tvGoldValue.setText(
                    String.format("%.2f", totalGoldValue));

            tvPayable.setText(
                    String.format("%.2f", zakatPayableValue));

            tvResult.setText(
                    String.format("%.2f", totalZakat));
        });

        btnReset.setOnClickListener(view -> {

            etWeight.setText("");
            etValue.setText("");

            spJenis.setSelection(0);

            tvGoldValue.setText("0.00");
            tvPayable.setText("0.00");
            tvResult.setText("0.00");

            Toast.makeText(
                    MainActivity.this,
                    "Form Reset",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_about) {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            AboutActivity.class);

            startActivity(intent);

            return true;
        }

        if (item.getItemId() == R.id.menu_share) {

            Intent shareIntent =
                    new Intent(Intent.ACTION_SEND);

            shareIntent.setType("text/plain");

            shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Zakat Gold Calculator App\n\n" +
                            "GitHub Repository:\n" +
                            "https://github.com/ayunss/ZakatGoldCalculator"
            );

            startActivity(
                    Intent.createChooser(
                            shareIntent,
                            "Share App"));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}