package com.example.unitconverter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.unitconverter.databinding.ActivityMainBinding;
import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DecimalFormat decimalFormat = new DecimalFormat("0.######");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupCategorySelection();
        setupInputListener();
        setupSwapButton();
        
        // Initial conversion calculation
        calculateConversion();
    }

    private void setupCategorySelection() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this, 
                android.R.layout.simple_dropdown_item_1line, 
                ConverterEngine.CATEGORIES
        );
        binding.categoryAutocomplete.setAdapter(categoryAdapter);
        binding.categoryAutocomplete.setText(ConverterEngine.CATEGORY_LENGTH, false);

        // Update units dropdown based on default category
        updateUnitDropdowns(ConverterEngine.CATEGORY_LENGTH);

        // Category change listener
        binding.categoryAutocomplete.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCategory = ConverterEngine.CATEGORIES.get(position);
            updateUnitDropdowns(selectedCategory);
            calculateConversion();
        });
    }

    private void updateUnitDropdowns(String category) {
        List<String> units = ConverterEngine.getUnitsForCategory(category);
        
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(
                this, 
                android.R.layout.simple_dropdown_item_1line, 
                units
        );
        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(
                this, 
                android.R.layout.simple_dropdown_item_1line, 
                units
        );

        binding.fromUnitAutocomplete.setAdapter(fromAdapter);
        binding.toUnitAutocomplete.setAdapter(toAdapter);

        // Set default units (e.g. Centimeter to Meter for Length, Gram to Kilogram for Weight, etc.)
        if (units.size() >= 2) {
            // For Length: Centimeter (index 1) to Meter (index 2)
            // For others: index 0 to index 1
            if (category.equals(ConverterEngine.CATEGORY_LENGTH)) {
                binding.fromUnitAutocomplete.setText(units.get(1), false);
                binding.toUnitAutocomplete.setText(units.get(2), false);
            } else {
                binding.fromUnitAutocomplete.setText(units.get(0), false);
                binding.toUnitAutocomplete.setText(units.get(1), false);
            }
        }

        // Add listeners to dropdown items
        binding.fromUnitAutocomplete.setOnItemClickListener((parent, view, position, id) -> calculateConversion());
        binding.toUnitAutocomplete.setOnItemClickListener((parent, view, position, id) -> calculateConversion());
    }

    private void setupInputListener() {
        binding.inputValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateConversion();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupSwapButton() {
        binding.swapButton.setOnClickListener(v -> {
            String fromUnit = binding.fromUnitAutocomplete.getText().toString();
            String toUnit = binding.toUnitAutocomplete.getText().toString();

            // Dynamic rotation animation for the icon
            binding.swapButton.animate().rotationBy(180f).setDuration(250).start();

            binding.fromUnitAutocomplete.setText(toUnit, false);
            binding.toUnitAutocomplete.setText(fromUnit, false);

            calculateConversion();
        });
    }

    private void calculateConversion() {
        String inputStr = binding.inputValue.getText().toString().trim();
        if (inputStr.isEmpty()) {
            binding.resultValue.setText("0.00");
            binding.resultUnitLabel.setText("");
            binding.resultFormula.setText("Enter a value to see result");
            return;
        }

        try {
            double inputValue = Double.parseDouble(inputStr);
            String fromUnit = binding.fromUnitAutocomplete.getText().toString();
            String toUnit = binding.toUnitAutocomplete.getText().toString();
            String category = binding.categoryAutocomplete.getText().toString();

            double result = ConverterEngine.convert(inputValue, fromUnit, toUnit, category);

            // Format numbers to prevent scientific notation and show up to 6 decimal places
            String formattedResult = decimalFormat.format(result);
            String formattedInput = decimalFormat.format(inputValue);

            binding.resultValue.setText(formattedResult);
            binding.resultUnitLabel.setText(toUnit);

            String fromSymbol = getUnitSymbol(fromUnit);
            String toSymbol = getUnitSymbol(toUnit);
            binding.resultFormula.setText(String.format("%s %s = %s %s", formattedInput, fromSymbol, formattedResult, toSymbol));

        } catch (NumberFormatException e) {
            binding.resultValue.setText("Error");
            binding.resultUnitLabel.setText("");
            binding.resultFormula.setText("Invalid number format");
        } catch (Exception e) {
            binding.resultValue.setText("Error");
            binding.resultUnitLabel.setText("");
            binding.resultFormula.setText(e.getMessage());
        }
    }

    private String getUnitSymbol(String unitName) {
        int start = unitName.indexOf('(');
        int end = unitName.indexOf(')');
        if (start != -1 && end != -1) {
            return unitName.substring(start + 1, end);
        }
        return unitName;
    }
}
