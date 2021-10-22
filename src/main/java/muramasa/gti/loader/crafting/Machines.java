package muramasa.gti.loader.crafting;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.SubTag;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.util.TagUtils;
import muramasa.gti.GregTech;
import muramasa.gti.Ref;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.data.GregTechData;
import muramasa.gti.data.TierMaps;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gti.data.GregTechData.*;
import static muramasa.gti.data.Machines.*;
import static muramasa.gti.data.TierMaps.TIER_CIRCUITS;
import static muramasa.gti.data.TierMaps.WIRE_GETTER;

public class Machines {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        Arrays.stream(Tier.getAllElectric()).forEach(tier -> {
            Item motor = GregTech.get(ItemBasic.class, "motor_"+tier.getId());
            if (motor == null) return;
            Item piston = GregTech.get(ItemBasic.class, "piston_"+tier.getId());
            if (piston == null) return;
            Item arm = GregTech.get(ItemBasic.class, "robot_arm_"+tier.getId());
            if (arm == null) return;
            Item conveyor = GregTech.get(ItemCover.class, "conveyor_"+tier.getId());
            if (conveyor == null) return;
            Item pump = GregTech.get(ItemCover.class, "pump_"+tier.getId());
            if (pump == null) return;
            Item casing = Item.BLOCK_TO_ITEM.get(GregTech.get(BlockCasing.class, "casing_"+tier.getId()));
            if (casing == null) return;
            Item circuit = TIER_CIRCUITS.getOrDefault(tier, CircuitBasic);
            Item cable = TierMaps.TIER_CABLES.get(tier);
            if (cable == null) return;
            Item glass = Items.GLASS;
            Item rotor = TierMaps.TIER_ROTORS.get(tier);
            Object diamond = Items.DIAMOND;

            add(PULVERIZER, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('M', motor).put('C', circuit).put('L', cable).put('H', casing).put('D', Items.DIAMOND).build(), "PMD", "LLH", "CCL"));

            add(ALLOY_SMELTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('L', TierMaps.WIRE_GETTER.apply(PipeSize.SMALL, tier)).put('H', casing).put('C', circuit).put('G', cable).build(), "CLC", "LHL", "GLG"));

            add(COMPRESSOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.of('C', circuit, 'P', piston, 'L', cable, 'H', casing), "LCL", "PHP", "LCL"));

            add(CUTTER, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('M', motor).put('C', circuit).put('L', cable).put('H', casing).put('D', DiamondSawBlade).put('V', conveyor).put('G', glass).build(), "LCG", "VHD", "CLM"));

            add(FURNACE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.of('C', circuit, 'L', cable, 'H', casing, 'W', TierMaps.WIRE_GETTER.apply(PipeSize.TINY, tier)), "CWC", "CHC", "LWL"));

            add(EXTRACTOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('M', pump).put('G', glass).put('C', circuit).put('L', cable).put('H', casing).build(), "GCG", "PHM", "LCL"));

            add(EXTRUDER, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('I', AntimatterAPI.get(FluidPipe.class,"fluid_"+ TierMaps.TIER_PIPE_MATERIAL.get(tier).getId()).getBlockItem(PipeSize.NORMAL)).put('W', WIRE_GETTER.apply(PipeSize.SMALL, tier)).put('C', circuit).put('H', casing).build(), "WWC", "PHI", "WWC"));

            add(LATHE, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('M', motor).put('C', circuit).put('L', cable).put('H', casing).put('D', diamond).build(), "LCL", "MHD", "CLP"));

            add(ELECTROLYZER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.of(
                            'C', circuit,
                            'W', WIRE_SILVER.getBlockItem(PipeSize.VTINY),
                            'L', cable,
                            'H', casing,
                            'G', glass
                    ), "WGW", "WHW", "CLC"));

            add(CHEMICAL_BATH, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', circuit)
                            .put('W', conveyor)
                            .put('P', pump)
                            .put('L', cable)
                            .put('H', casing)
                            .put('G', glass)
                    .build(), "WGL", "PGW", "CHC"));

            add(SIFTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', circuit)
                            .put('P', piston)
                            .put('L', cable)
                            .put('H', casing)
                            .put('F', ItemFilter)
                            .build(), "LFL", "PHP", "CFC"));

            add(BENDER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.of(
                            'P', piston,
                            'M', motor,
                            'C', circuit,
                            'L', cable,
                            'H', casing
                    ), "PLP", "CHC", "MLM"));

            add(WIRE_MILL, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.of(
                            'M', motor,
                            'C', circuit,
                            'L', cable,
                            'H', casing
                    ), "MLM", "CHC", "MLM"));

            add(ASSEMBLER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.of(
                            'R', arm,
                            'O', conveyor,
                            'C', circuit,
                            'L', cable,
                            'H', casing
                    ), "RCR", "OHO", "LCL"));

            add(CENTRIFUGE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.of(
                            'M', motor,
                            'C', circuit,
                            'L', cable,
                            'H', casing
                    ), "CMC", "LHL", "CHC"));

            add(MIXER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.of(
                            'M', motor,
                            'R', rotor,
                            'G', glass,
                            'C', circuit,
                            'H', casing
                    ), "GRG", "GMG", "CHC"));

            add(STEAM_GENERATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('L', cable)
                            .put('H', casing)
                            .put('R', rotor)
                            .put('C', circuit)
                            .put('P',  AntimatterAPI.get(FluidPipe.class,"fluid_"+ TierMaps.TIER_PIPE_MATERIAL.get(tier).getId()).getBlockItem(PipeSize.NORMAL))
                            .build(), "PCP", "RHR", "MLM"));

            add(BLAST_FURNACE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', cable)
                            .put('H', CASING_HEAT_PROOF)
                            .put('C', circuit)
                            .put('F', Items.FURNACE)
                            .build(), "FFF", "CHC", "LCL"));
        });
    }

    private static <T extends Machine<T>> void add(T machine, Tier tier, BiConsumer<T, Item> callback) {
        Item item = machine.getItem(tier);
        if (item != null) callback.accept(machine, item);
    }
}
