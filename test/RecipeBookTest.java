import java.util.List;

/**
 * Tests for the RecipeBook class.
 */
public class RecipeBookTest {
    public static void main(String[] args) {
        testAddRecipe();
        testRemoveRecipe();
        testGetAllRecipes();
        testSize();
        testSearchByName();
        testSearchByNameWithWhitespace();
        testSearchByIngredient();
        testSearchByTokens();
        System.out.println("All RecipeBook tests passed.");
    }

    private static void testAddRecipe() {
        RecipeBook book = new RecipeBook();
        Recipe r1 = new Recipe("Pancakes", 4);
        
        assertEquals("empty book size", 0, book.size());
        
        book.addRecipe(r1);
        assertEquals("size after add", 1, book.size());
        
        try {
            book.addRecipe(null);
            fail("addRecipe should throw on null");
        } catch (IllegalArgumentException expected) {
            // expected
        }
    }

    private static void testRemoveRecipe() {
        RecipeBook book = new RecipeBook();
        Recipe r1 = new Recipe("Soup", 2);
        Recipe r2 = new Recipe("Salad", 1);
        
        book.addRecipe(r1);
        book.addRecipe(r2);
        
        boolean removed = book.removeRecipe("Soup");
        assertTrue("removeRecipe returns true", removed);
        assertEquals("size after remove", 1, book.size());
        
        boolean removed2 = book.removeRecipe("Nonexistent");
        assertFalse("removeRecipe returns false for missing", removed2);
        assertEquals("size unchanged", 1, book.size());
        
        boolean removed3 = book.removeRecipe(null);
        assertFalse("removeRecipe handles null", removed3);
    }

    private static void testGetAllRecipes() {
        RecipeBook book = new RecipeBook();
        Recipe r1 = new Recipe("Recipe1", 1);
        Recipe r2 = new Recipe("Recipe2", 2);
        
        book.addRecipe(r1);
        book.addRecipe(r2);
        
        List<Recipe> all = book.getAllRecipes();
        assertEquals("getAllRecipes size", 2, all.size());
        
        // Verify it's a copy
        all.clear();
        assertEquals("original book unchanged", 2, book.size());
    }

    private static void testSize() {
        RecipeBook book = new RecipeBook();
        assertEquals("initial size", 0, book.size());
        
        book.addRecipe(new Recipe("A", 1));
        assertEquals("size 1", 1, book.size());
        
        book.addRecipe(new Recipe("B", 1));
        assertEquals("size 2", 2, book.size());
        
        book.removeRecipe("A");
        assertEquals("size after remove", 1, book.size());
    }

    private static void testSearchByName() {
        RecipeBook book = new RecipeBook();
        book.addRecipe(new Recipe("Chocolate Cake", 8));
        book.addRecipe(new Recipe("Vanilla Cake", 8));
        book.addRecipe(new Recipe("Brownie", 12));
        
        List<Recipe> results = book.searchByName("cake");
        assertEquals("case-insensitive search", 2, results.size());
        
        List<Recipe> results2 = book.searchByName("Chocolate");
        assertEquals("partial match", 1, results2.size());
        
        List<Recipe> results3 = book.searchByName("Pizza");
        assertEquals("no match", 0, results3.size());
        
        List<Recipe> results4 = book.searchByName("");
        assertEquals("empty query", 0, results4.size());
        
        List<Recipe> results5 = book.searchByName(null);
        assertEquals("null query", 0, results5.size());
    }

    private static void testSearchByNameWithWhitespace() {
        RecipeBook book = new RecipeBook();
        book.addRecipe(new Recipe("Chocolate Cake", 8));
        
        List<Recipe> results = book.searchByName("  cake  ");
        assertEquals("whitespace trimmed", 1, results.size());
        
        List<Recipe> results2 = book.searchByName("   ");
        assertEquals("whitespace-only query", 0, results2.size());
    }

    private static void testSearchByIngredient() {
        RecipeBook book = new RecipeBook();
        
        Recipe pasta = new Recipe("Pasta", 2);
        pasta.addIngredient("spaghetti", 200);
        pasta.addIngredient("garlic", 3);
        
        Recipe cake = new Recipe("Cake", 8);
        cake.addIngredient("flour", 2);
        cake.addIngredient("eggs", 3);
        
        book.addRecipe(pasta);
        book.addRecipe(cake);
        
        List<Recipe> results = book.searchByIngredient("garlic");
        assertEquals("ingredient search", 1, results.size());
        
        List<Recipe> results2 = book.searchByIngredient("GARLIC");
        assertEquals("case-insensitive ingredient", 1, results2.size());
        
        List<Recipe> results3 = book.searchByIngredient("gg");
        assertEquals("partial ingredient match", 1, results3.size());
        
        List<Recipe> results4 = book.searchByIngredient("   ");
        assertEquals("empty ingredient query", 0, results4.size());
    }

    private static void testSearchByTokens() {
        RecipeBook book = new RecipeBook();
        
        Recipe pasta = new Recipe("Pasta Aglio e Olio", 2);
        pasta.addIngredient("spaghetti", 200);
        pasta.addIngredient("garlic", 3);
        pasta.addIngredient("olive oil", 0.25);
        
        Recipe bread = new Recipe("Garlic Bread", 4);
        bread.addIngredient("bread", 1);
        bread.addIngredient("garlic", 4);
        bread.addIngredient("butter", 4);
        
        Recipe cake = new Recipe("Cake", 8);
        cake.addIngredient("flour", 2);
        cake.addIngredient("eggs", 3);
        
        book.addRecipe(pasta);
        book.addRecipe(bread);
        book.addRecipe(cake);
        
        // Search for "garlic oil" should only match pasta (has both)
        List<Recipe> results = book.searchByTokens("garlic oil");
        assertEquals("multi-token search", 1, results.size());
        
        // Search for "garlic" should match both pasta and bread
        List<Recipe> results2 = book.searchByTokens("garlic");
        assertEquals("single token search", 2, results2.size());
        
        // Search for "flour eggs" should match cake
        List<Recipe> results3 = book.searchByTokens("flour eggs");
        assertEquals("ingredient-only tokens", 1, results3.size());
        
        // Search for "bread garlic" should match bread (in name and ingredient)
        List<Recipe> results4 = book.searchByTokens("bread garlic");
        assertEquals("name and ingredient tokens", 1, results4.size());
        
        List<Recipe> results5 = book.searchByTokens("   ");
        assertEquals("empty token query", 0, results5.size());
    }

    // Helper methods
    private static void assertEquals(String label, int expected, int actual) {
        if (expected != actual) {
            fail(label + " expected " + expected + " but was " + actual);
        }
    }

    private static void assertTrue(String label, boolean condition) {
        if (!condition) {
            fail(label + " expected true but was false");
        }
    }

    private static void assertFalse(String label, boolean condition) {
        if (condition) {
            fail(label + " expected false but was true");
        }
    }

    private static void fail(String message) {
        throw new AssertionError(message);
    }
}
