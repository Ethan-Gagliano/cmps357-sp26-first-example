import java.util.ArrayList;
import java.util.List;

/**
 * Tests for the RecipeSorter class.
 */
public class RecipeSorterTest {
    public static void main(String[] args) throws Exception {
        testSortByName();
        testSortByNameCaseInsensitive();
        testSortByNameDoesNotMutate();
        testSortByNameNullInput();
        testSortByNameReverse();
        System.out.println("All RecipeSorter tests passed.");
    }

    private static void testSortByName() throws Exception {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Zebra Cake", 8));
        recipes.add(new Recipe("Apple Pie", 6));
        recipes.add(new Recipe("Muffins", 12));
        
        List<Recipe> sorted = RecipeSorter.sortByName(recipes);
        
        assertEquals("sorted size", 3, sorted.size());
        assertEquals("first recipe", "Apple Pie", getRecipeName(sorted.get(0)));
        assertEquals("second recipe", "Muffins", getRecipeName(sorted.get(1)));
        assertEquals("third recipe", "Zebra Cake", getRecipeName(sorted.get(2)));
    }

    private static void testSortByNameCaseInsensitive() throws Exception {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("banana bread", 1));
        recipes.add(new Recipe("Apple Tart", 1));
        recipes.add(new Recipe("Cherry Pie", 1));
        
        List<Recipe> sorted = RecipeSorter.sortByName(recipes);
        
        assertEquals("case-insensitive first", "Apple Tart", getRecipeName(sorted.get(0)));
        assertEquals("case-insensitive second", "banana bread", getRecipeName(sorted.get(1)));
        assertEquals("case-insensitive third", "Cherry Pie", getRecipeName(sorted.get(2)));
    }

    private static void testSortByNameDoesNotMutate() throws Exception {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Zebra", 1));
        recipes.add(new Recipe("Apple", 1));
        
        String originalFirst = getRecipeName(recipes.get(0));
        
        RecipeSorter.sortByName(recipes);
        
        String afterSortFirst = getRecipeName(recipes.get(0));
        assertEquals("original not mutated", originalFirst, afterSortFirst);
    }

    private static void testSortByNameNullInput() {
        List<Recipe> sorted = RecipeSorter.sortByName(null);
        assertNotNull("null input returns empty list", sorted);
        assertEquals("null input returns empty", 0, sorted.size());
    }

    private static void testSortByNameReverse() throws Exception {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Zebra Cake", 8));
        recipes.add(new Recipe("Apple Pie", 6));
        recipes.add(new Recipe("Muffins", 12));
        
        // Test reverse sort (Z-A)
        List<Recipe> sorted = RecipeSorter.sortByName(recipes, true);
        
        assertEquals("reverse sorted size", 3, sorted.size());
        assertEquals("reverse first recipe", "Zebra Cake", getRecipeName(sorted.get(0)));
        assertEquals("reverse second recipe", "Muffins", getRecipeName(sorted.get(1)));
        assertEquals("reverse third recipe", "Apple Pie", getRecipeName(sorted.get(2)));
        
        // Test normal sort (A-Z) with false parameter
        List<Recipe> sortedAsc = RecipeSorter.sortByName(recipes, false);
        assertEquals("ascending first recipe", "Apple Pie", getRecipeName(sortedAsc.get(0)));
        assertEquals("ascending third recipe", "Zebra Cake", getRecipeName(sortedAsc.get(2)));
    }

    // Helper methods
    private static String getRecipeName(Recipe recipe) throws ReflectiveOperationException {
        java.lang.reflect.Field nameField = Recipe.class.getDeclaredField("name");
        nameField.setAccessible(true);
        return (String) nameField.get(recipe);
    }

    private static void assertEquals(String label, String expected, String actual) {
        if (!expected.equals(actual)) {
            fail(label + " expected '" + expected + "' but was '" + actual + "'");
        }
    }

    private static void assertEquals(String label, int expected, int actual) {
        if (expected != actual) {
            fail(label + " expected " + expected + " but was " + actual);
        }
    }

    private static void assertNotNull(String label, Object obj) {
        if (obj == null) {
            fail(label + " expected non-null but was null");
        }
    }

    private static void fail(String message) {
        throw new AssertionError(message);
    }
}
