package muramasa.gregtech.loader.machines;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.RecipeMaps.CANNING;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.*;

public class CanningLoader {

    static int MULTIPLE_SMALL = 4;
    static int MULTIPLE_MEDIUM = 16;
    static int MULTIPLE_LARGE = 32;

    public static void init() {
        CANNING.RB().ii(of(BatteryHullSmall.getDefaultInstance()), of(DUST.get(Sodium, MULTIPLE_SMALL))).io(BatterySmallSodium.getDefaultInstance()).add("battery_small_sodium",40, 16);
        CANNING.RB().ii(of(BatteryHullSmall.getDefaultInstance()), of(DUST.get(Cadmium, MULTIPLE_SMALL))).io(BatterySmallCadmium.getDefaultInstance()).add("battery_small_cadmium",50, 16);
        CANNING.RB().ii(of(BatteryHullSmall.getDefaultInstance()), of(DUST.get(Lithium, MULTIPLE_SMALL))).io(BatterySmallLithium.getDefaultInstance()).add("battery_small_lithium",60, 16);

        CANNING.RB().ii(of(BatteryHullMedium.getDefaultInstance()), of(DUST.get(Sodium, MULTIPLE_MEDIUM))).io(BatteryMediumSodium.getDefaultInstance()).add("battery_medium",40, 64);
        CANNING.RB().ii(of(BatteryHullMedium.getDefaultInstance()), of(DUST.get(Cadmium, MULTIPLE_MEDIUM))).io(BatteryMediumCadmium.getDefaultInstance()).add("battery_medium",50, 64);
        CANNING.RB().ii(of(BatteryHullMedium.getDefaultInstance()), of(DUST.get(Lithium, MULTIPLE_MEDIUM))).io(BatteryMediumLithium.getDefaultInstance()).add("battery_medium",60, 64);

        CANNING.RB().ii(of(BatteryHullLarge.getDefaultInstance()), of(DUST.get(Sodium, MULTIPLE_LARGE))).io(BatteryLargeSodium.getDefaultInstance()).add("battery_large_sodium",40, 256);
        CANNING.RB().ii(of(BatteryHullLarge.getDefaultInstance()), of(DUST.get(Cadmium, MULTIPLE_LARGE))).io(BatteryLargeCadmium.getDefaultInstance()).add("battery_large_cadmium",50, 256);
        CANNING.RB().ii(of(BatteryHullLarge.getDefaultInstance()), of(DUST.get(Lithium, MULTIPLE_LARGE))).io(BatteryLargeLithium.getDefaultInstance()).add("battery_large_lithium",60, 256);
    }
}
