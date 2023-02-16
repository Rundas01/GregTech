package muramasa.gregtech.loader.machines;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.RecipeMaps.HAMMERING;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;

public class HammerLoader {
    public static void init() {
        PLATE.all().forEach(plate -> {
            if (!plate.has(INGOT)) return;
            HAMMERING.RB().ii(INGOT.getMaterialIngredient(plate, 3)).io(PLATE.get(plate, 2)).add("plate_" + plate.getId(),plate.getMass()*2, 13);
        });
        GEM.all().forEach(g -> {
            Item gemExquisite = GEM_EXQUISITE.get(g), gemFlawless = GEM_FLAWLESS.get(g), gem = GEM.get(g), gemFlawed = GEM_FLAWED.get(g), gemChipped = GEM_CHIPPED.get(g);
            HAMMERING.RB().ii(of(gemExquisite)).io(new ItemStack(gemFlawless,2)).add(g.getId() + "_exquisite_hammering",g.getMass()*2, 12);
            HAMMERING.RB().ii(of(gemFlawless)).io(new ItemStack(gem,2)).add(g.getId() + "_flawless_hammering",g.getMass()*2, 12);
            HAMMERING.RB().ii(of(gem)).io(new ItemStack(gemFlawed,2)).add(g.getId() + "_hammering",g.getMass()*2, 12);
            HAMMERING.RB().ii(of(gemFlawed)).io(new ItemStack(gemChipped,2)).add(g.getId() + "_flawed_hammering",g.getMass()*2, 12);
        });
    }
}
