package com.example.unitconverter;

import java.util.Arrays;
import java.util.List;

public class ConverterEngine {

    public static final String CATEGORY_LENGTH = "Length";
    public static final String CATEGORY_WEIGHT = "Weight / Mass";
    public static final String CATEGORY_TEMPERATURE = "Temperature";
    public static final String CATEGORY_VOLUME = "Volume";

    public static final List<String> CATEGORIES = Arrays.asList(
            CATEGORY_LENGTH,
            CATEGORY_WEIGHT,
            CATEGORY_TEMPERATURE,
            CATEGORY_VOLUME
    );

    // Units lists
    private static final List<String> LENGTH_UNITS = Arrays.asList(
            "Millimeter (mm)",
            "Centimeter (cm)",
            "Meter (m)",
            "Kilometer (km)",
            "Inch (in)",
            "Foot (ft)",
            "Yard (yd)",
            "Mile (mi)"
    );

    private static final List<String> WEIGHT_UNITS = Arrays.asList(
            "Milligram (mg)",
            "Gram (g)",
            "Kilogram (kg)",
            "Ounce (oz)",
            "Pound (lb)"
    );

    private static final List<String> TEMPERATURE_UNITS = Arrays.asList(
            "Celsius (°C)",
            "Fahrenheit (°F)",
            "Kelvin (K)"
    );

    private static final List<String> VOLUME_UNITS = Arrays.asList(
            "Milliliter (ml)",
            "Liter (l)",
            "Gallon (gal)",
            "Cup (cup)"
    );

    public static List<String> getUnitsForCategory(String category) {
        if (category == null) return LENGTH_UNITS;
        switch (category) {
            case CATEGORY_WEIGHT:
                return WEIGHT_UNITS;
            case CATEGORY_TEMPERATURE:
                return TEMPERATURE_UNITS;
            case CATEGORY_VOLUME:
                return VOLUME_UNITS;
            case CATEGORY_LENGTH:
            default:
                return LENGTH_UNITS;
        }
    }

    /**
     * Converts a value from one unit to another.
     */
    public static double convert(double value, String fromUnit, String toUnit, String category) {
        if (fromUnit.equals(toUnit)) {
            return value;
        }

        switch (category) {
            case CATEGORY_LENGTH:
                return convertLength(value, fromUnit, toUnit);
            case CATEGORY_WEIGHT:
                return convertWeight(value, fromUnit, toUnit);
            case CATEGORY_TEMPERATURE:
                return convertTemperature(value, fromUnit, toUnit);
            case CATEGORY_VOLUME:
                return convertVolume(value, fromUnit, toUnit);
            default:
                return value;
        }
    }

    // Convert length (Base unit: Meter)
    private static double convertLength(double value, String fromUnit, String toUnit) {
        double meters = 0;
        
        // Convert to Meter
        switch (fromUnit) {
            case "Millimeter (mm)": meters = value * 0.001; break;
            case "Centimeter (cm)": meters = value * 0.01; break;
            case "Meter (m)":       meters = value; break;
            case "Kilometer (km)":  meters = value * 1000.0; break;
            case "Inch (in)":       meters = value * 0.0254; break;
            case "Foot (ft)":       meters = value * 0.3048; break;
            case "Yard (yd)":       meters = value * 0.9144; break;
            case "Mile (mi)":       meters = value * 1609.344; break;
            default:                meters = value; break;
        }

        // Convert from Meter to target unit
        switch (toUnit) {
            case "Millimeter (mm)": return meters / 0.001;
            case "Centimeter (cm)": return meters / 0.01;
            case "Meter (m)":       return meters;
            case "Kilometer (km)":  return meters / 1000.0;
            case "Inch (in)":       return meters / 0.0254;
            case "Foot (ft)":       return meters / 0.3048;
            case "Yard (yd)":       return meters / 0.9144;
            case "Mile (mi)":       return meters / 1609.344;
            default:                return meters;
        }
    }

    // Convert weight (Base unit: Gram)
    private static double convertWeight(double value, String fromUnit, String toUnit) {
        double grams = 0;

        // Convert to Gram
        switch (fromUnit) {
            case "Milligram (mg)": grams = value * 0.001; break;
            case "Gram (g)":       grams = value; break;
            case "Kilogram (kg)":  grams = value * 1000.0; break;
            case "Ounce (oz)":     grams = value * 28.349523125; break;
            case "Pound (lb)":     grams = value * 453.59237; break;
            default:               grams = value; break;
        }

        // Convert from Gram to target unit
        switch (toUnit) {
            case "Milligram (mg)": return grams / 0.001;
            case "Gram (g)":       return grams;
            case "Kilogram (kg)":  return grams / 1000.0;
            case "Ounce (oz)":     return grams / 28.349523125;
            case "Pound (lb)":     return grams / 453.59237;
            default:               return grams;
        }
    }

    // Convert temperature
    private static double convertTemperature(double value, String fromUnit, String toUnit) {
        double kelvin = 0;

        // Convert to Kelvin
        switch (fromUnit) {
            case "Celsius (°C)":    kelvin = value + 273.15; break;
            case "Fahrenheit (°F)": kelvin = (value - 32.0) * (5.0 / 9.0) + 273.15; break;
            case "Kelvin (K)":      kelvin = value; break;
            default:                kelvin = value; break;
        }

        // Convert from Kelvin to target unit
        switch (toUnit) {
            case "Celsius (°C)":    return kelvin - 273.15;
            case "Fahrenheit (°F)": return (kelvin - 273.15) * (9.0 / 5.0) + 32.0;
            case "Kelvin (K)":      return kelvin;
            default:                return kelvin;
        }
    }

    // Convert volume (Base unit: Liter)
    private static double convertVolume(double value, String fromUnit, String toUnit) {
        double liters = 0;

        // Convert to Liter
        switch (fromUnit) {
            case "Milliliter (ml)": liters = value * 0.001; break;
            case "Liter (l)":       liters = value; break;
            case "Gallon (gal)":    liters = value * 3.785411784; break;
            case "Cup (cup)":       liters = value * 0.2365882365; break;
            default:                liters = value; break;
        }

        // Convert from Liter to target unit
        switch (toUnit) {
            case "Milliliter (ml)": return liters / 0.001;
            case "Liter (l)":       return liters;
            case "Gallon (gal)":    return liters / 3.785411784;
            case "Cup (cup)":       return liters / 0.2365882365;
            default:                return liters;
        }
    }
}
