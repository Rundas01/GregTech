package muramasa.gregtech.loader.multi;

import muramasa.gregtech.data.Materials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.gregtech.data.RecipeMaps.IMPLOSION_COMPRESSING;

public class ImplosionCompressor {
    public static void init() {
        GEM.all().forEach(g -> {
            Item gemExquisite = GEM_EXQUISITE.get(g), gemFlawless = GEM_FLAWLESS.get(g), gemFlawed = GEM_FLAWED.get(g), gemChipped = GEM_CHIPPED.get(g), gem = GEM.get(g);
            IMPLOSION_COMPRESSING.RB().ii(of(gemChipped,3),of(Items.TNT,8)).io(new ItemStack(gemFlawed,1),new ItemStack(DUST_SMALL.get(Materials.DarkAsh),2)).add(g.getId() + "_chipped_implosion_compressing",20,8);
            IMPLOSION_COMPRESSING.RB().ii(of(gemFlawed,3),of(Items.TNT,8)).io(new ItemStack(gem,1),new ItemStack(DUST_SMALL.get(Materials.DarkAsh),2)).add(g.getId() + "_flawed_implosion_compressing",20,8);
            IMPLOSION_COMPRESSING.RB().ii(of(gem,3),of(Items.TNT,8)).io(new ItemStack(gemFlawless,1),new ItemStack(DUST_SMALL.get(Materials.DarkAsh),2)).add(g.getId() + "_implosion_compressing",20,8);
            IMPLOSION_COMPRESSING.RB().ii(of(gemFlawless,3),of(Items.TNT,8)).io(new ItemStack(gemExquisite,1),new ItemStack(DUST_SMALL.get(Materials.DarkAsh),2)).add(g.getId() + "_flawless_implosion_compressing",20,8);
        });
    }
}
