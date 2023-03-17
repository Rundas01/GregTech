package muramasa.gregtech.loader.multi;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.RecipeMaps.COMPLEX_ASSEMBLING;

public class AssemblyLine {
    public static void init() {
        COMPLEX_ASSEMBLING.RB().ii(of(Items.STONE,1)).io(new ItemStack(Items.DIAMOND,1)).add("test",20,32);
    }
}
