import java.util.List;

/**
 * Demo for Stage 3 functionality - Advanced Searching and Sorting.
 */
public class Stage3Demo {
    public static void main(String[] args) {
        System.out.println("=== Stage 3 Demo: Advanced Searching and Sorting ===\n");
        
        // Create a recipe book
        RecipeBook book = new RecipeBook();
        
        // Create some recipes
        Recipe pasta = new Recipe("Pasta Aglio e Olio", 2);
        pasta.addIngredient("spaghetti (g)", 200);
        pasta.addIngredient("garlic cloves", 3);
        pasta.addIngredient("olive oil (cup)", 0.25);
        
        Recipe pancakes = new Recipe("Pancakes", 4);
        pancakes.addIngredient("flour (cup)", 2);
        pancakes.addIngredient("eggs", 2);
        pancakes.addIngredient("milk (cup)", 1.5);
        
        Recipe chocolateCake = new Recipe("Chocolate Cake", 8);
        chocolateCake.addIngredient("flour (cup)", 2);
        chocolateCake.addIngredient("cocoa powder (cup)", 0.75);
        chocolateCake.addIngredient("sugar (cup)", 2);
        chocolateCake.addIngredient("eggs", 3);
        
        Recipe garlicBread = new Recipe("Garlic Bread", 4);
        garlicBread.addIngredient("bread", 1);
        garlicBread.addIngredient("garlic cloves", 4);
        garlicBread.addIngredient("butter (tbsp)", 4);
        
        // Add recipes to book
        book.addRecipe(pasta);
        book.addRecipe(pancakes);
        book.addRecipe(chocolateCake);
        book.addRecipe(garlicBread);
        
        System.out.println("Added " + book.size() + " recipes to the book\n");
        
        // Test 1: Case-insensitive name search with whitespace trimming
        System.out.println("=== Test 1: Search by name 'cake' (case-insensitive) ===");
        List<Recipe> results = book.searchByName("  cake  ");
        printRecipes(results);
        
        // Test 2: Ingredient-based search
        System.out.println("=== Test 2: Search by ingredient 'garlic' ===");
        results = book.searchByIngredient("garlic");
        printRecipes(results);
        
        // Test 3: Multiple-token query
        System.out.println("=== Test 3: Multi-token search 'garlic oil' ===");
        results = book.searchByTokens("garlic oil");
        printRecipes(results);
        
        // Test 4: Sort by name A-Z
        System.out.println("=== Test 4: All recipes sorted A-Z ===");
        List<Recipe> sorted = RecipeSorter.sortByName(book.getAllRecipes());
        printRecipes(sorted);
        
        // Test 5: Sort by name Z-A
        System.out.println("=== Test 5: All recipes sorted Z-A ===");
        sorted = RecipeSorter.sortByName(book.getAllRecipes(), true);
        printRecipes(sorted);
        
        // Test 6: Verify storage order is unchanged
        System.out.println("=== Test 6: Original insertion order preserved ===");
        printRecipes(book.getAllRecipes());
        
        // Test 7: Complex multi-token search
        System.out.println("=== Test 7: Multi-token search 'flour eggs' ===");
        results = book.searchByTokens("flour eggs");
        printRecipes(results);
    }
    
    private static void printRecipes(List<Recipe> recipes) {
        if (recipes.isEmpty()) {
            System.out.println("(No recipes found)\n");
        } else {
            for (Recipe r : recipes) {
                System.out.println(r.toString());
            }
        }
    }
}
