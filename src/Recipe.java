
    // src/Recipe.java
import java.util.ArrayList;

public class Recipe {
    private final String name;
    private int servings;

    // Parallel lists to keep Day-1 Java simple (no extra classes required).
    private final ArrayList<String> ingredientNames = new ArrayList<>();
    private final ArrayList<Double> ingredientAmounts = new ArrayList<>();

    /**
     * Create a new Recipe with the given name and number of servings.
     *
     * @param name the recipe name; must be non-null and not blank
     * @param servings the number of servings; must be positive
     * @throws IllegalArgumentException if {@code name} is null/blank or {@code servings} <= 0
     */
    public Recipe(String name, int servings) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must be non-empty");
        }
        if (servings <= 0) {
            throw new IllegalArgumentException("servings must be positive");
        }
        this.name = name;
        this.servings = servings;
    }



    /**
     * Adds an ingredient and its amount to the recipe.
     *
     * <p>The ingredient is only added if both inputs are valid:
     * <ul>
     *   <li>{@code ingredientName} must be non-null and not blank</li>
     *   <li>{@code amount} must be greater than 0</li>
     * </ul>
     *
     * <p>If either input is invalid, this method leaves the recipe unchanged
     * and prints a debug message to standard output.
     *
     * @param ingredientName the name of the ingredient
     * @param amount the amount of the ingredient
     */
   public void addIngredient(String ingredientName, double amount) {
        // TODO:
        // - Validate ingredientName non-null and non-blank
        // - Validate amount > 0
        // - Add to ingredientNames and ingredientAmounts in matching positions
        // - done below
        if (ingredientName == null || ingredientName.trim().isEmpty()) {
            // Invalid ingredient name: ignore the entry (tests expect this behaviour).
            System.out.println("Ignoring invalid ingredient name: '" + ingredientName + "'");
            return;
        }
        if (amount <= 0) {
            // Invalid amount: ignore the entry.
            System.out.println("Ignoring invalid ingredient amount: " + amount);
            return;
        }

        ingredientNames.add(ingredientName);
        ingredientAmounts.add(amount);
}


    /**
     * Return the number of ingredient entries in this recipe.
     *
     * @return the count of ingredients added to this recipe
     */
    public int totalIngredientCount() {
        // TODO: return number of ingredient entries
        return ingredientNames.size();
    }

     
    /**
     * Scale all ingredient amounts to match a new number of servings.
     *
     * <p>The method multiplies each ingredient amount by the factor
     * {@code (double)newServings / servings} and updates the stored
     * number of servings.
     *
     * @param newServings the desired number of servings; must be positive
     * @throws IllegalArgumentException if {@code newServings} <= 0
     */
    public void scaleToServings(int newServings) {
        // TODO:
        // - If newServings <= 0 throw IllegalArgumentException
        // - Compute factor = (double) newServings / servings
        // - Multiply each ingredient amount by factor
        // - Update servings

        if (newServings <= 0) {
            throw new IllegalArgumentException("Servings must be greater than 0");
        }

        double factor = (double) newServings / servings;

        for (int i = 0; i < ingredientAmounts.size(); i++) {
            double scaled = ingredientAmounts.get(i) * factor;
            ingredientAmounts.set(i, scaled);
        }

        servings = newServings;
    }
     
    /**
     * Return a human-readable representation of the recipe.
     *
     * <p>Format:
     * <pre>
     * &lt;name&gt; (serves &lt;servings&gt;)
     * - &lt;amount&gt; &lt;ingredient&gt;
     * - ...
     * </pre>
     * Amounts are formatted using {@link #formatAmount(double)}.
     *
     * @return the formatted recipe string
     */
    @Override
    public String toString() {
        // TODO:
        // Return:
        // <name> (serves <servings>)
        // - <amount> <ingredient>
        // ...
        //
        // Use formatAmount(double) helper for printing amounts.

        StringBuilder sb = new StringBuilder();

        sb.append(name).append(" (serves ").append(servings).append(")\n");

        for (int i = 0; i < ingredientNames.size(); i++) {
            sb.append("- ")
              .append(formatAmount(ingredientAmounts.get(i)))
              .append(" ")
              .append(ingredientNames.get(i))
              .append("\n");
        }

        return sb.toString();
    }

    

    /**
     * Format an amount for display.
     *
     * <p>If the value is an integer (e.g. {@code 200.0}) it is formatted
     * without a decimal point. Otherwise the value is rounded to two
     * decimal places and trailing zeros are trimmed (for example
     * {@code 0.625} -> {@code "0.63"}).
     *
     * @param x the amount to format
     * @return a compact string representation of the amount
     */
    private String formatAmount(double x) {
        // Spec:
        // - If x is an integer value, print without decimals.
        // - Else print with up to 2 decimals, trimming trailing zeros.
        //
        // Examples:
        // 200.0 -> "200"
        // 7.5 -> "7.5"
        // 0.625 -> "0.63"
        // 1.333 -> "1.33"
        //
        // TODO: implement.

        if (x == Math.floor(x)) {
            return String.valueOf((long) x);
        }

        double rounded = Math.round(x * 100.0) / 100.0;

        String s = String.valueOf(rounded);

        if (s.contains(".")) {
            while (s.endsWith("0")) {
                s = s.substring(0, s.length() - 1);
            }
            if (s.endsWith(".")) {
                s = s.substring(0, s.length() - 1);
            }
        }

        return s;
    }

}
