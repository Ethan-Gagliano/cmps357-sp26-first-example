User: What implementation changes need to be completed before begining the larger application described in `docs/` ?

GitHub Copilot: Searched codebase for "What implementation changes need to be completed before begining the larger application described in `docs/` ?", 11 results

Before moving on to the larger app in docs, tidy up the Stage 1 pieces:

- Resolve the spec/API mismatch: either add `toPrettyString()` delegating to `toString()` in `Recipe` or update SPEC.md to match the current `toString()` usage (see note in TODO.md).  
- Add Javadoc for the remaining public methods in `Recipe` for clarity.  
- Add a few sanity tests (e.g., in a `test/` folder) covering `totalIngredientCount()`, `scaleToServings(...)`, and `formatAmount(...)` to lock in current behavior.  

With those done, the core recipe model is consistent and ready for the multi-class expansion outlined in STAGES.md and ARCHITECTURE.md.

User: Any other changes you would suggest?

GitHub Copilot: Searched codebase for "Any other changes you would suggest?", 11 results

Additional low-effort improvements before Stage 2:

- Add `toPrettyString()` delegating to `toString()` to close the spec/code gap noted in TODO.md and SPEC.md; keep both documented in Recipe.java.
- Add brief Javadoc to `scaleToServings(...)` and `toString()` in Recipe.java for clarity.
- Move debug prints in `addIngredient` to `System.err` or remove them; add a return boolean to signal rejection (optional).
- Seed a tiny test harness (e.g., JUnit) to lock formatting and scaling behavior for `formatAmount`, `scaleToServings`, and `totalIngredientCount` before refactors.
- Sketch stubs/interfaces for upcoming classes (`service.RecipeBook`, `service.Search`, `service.ShoppingCart`, `io.RecipeJsonStore`, `ui.ConsoleUI`) to align with ARCHITECTURE.md and STAGES.md; keep them minimal (fields, constructor args, method signatures, no logic yet).
- Document expected normalization/aggregation behavior (from DATA_MODEL.md) in Javadoc on the future shopping cart API to avoid drift when implemented.

User: Update the TODO.md accordingly

GitHub Copilot: Read [TODO](TODO.md)

Using "Apply Patch"

 
Updated TODO items to spell out next steps: add `toPrettyString()` and doc alignment, Javadoc for remaining `Recipe` methods, seed a small test harness, tidy `addIngredient` logging/return, and note optional refactors plus stubs for upcoming components. See TODO for details.

Made changes.