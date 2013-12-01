package iguanaman.undergroundbiomesblender;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import org.modstats.ModstatInfo;
import org.modstats.Modstats;

import tconstruct.blocks.MetalOre;
import tconstruct.items.blocks.MetalOreItemBlock;
import tconstruct.util.config.PHConstruct;

import com.google.common.base.Optional;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid="UndergroundBiomesBlender", name="Underground Biomes Blender", version="1.6.X-1a", 
dependencies = "required-after:UndergroundBiomes;after:TConstruct@")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
@ModstatInfo(prefix="ubblender")
public class UndergroundBiomesBlender {
	
        // The instance of your mod that Forge uses.
        @Instance("UndergroundBiomesBlender")
        public static UndergroundBiomesBlender instance;
        
        public static final List<String> blockNames = Arrays.asList("", "",
                "redGranite", "blackGranite", "rhyolite", "andesite", "gabbro", "basalt", "komatiite", "dacite",
                "gneiss", "eclogite", "marble", "quartzite", "blueschist", "greenschist", "soapstone", "migmatite",
                "limestone", "chalk", "shale", "siltstone", "ligniteBlock", "dolomite", "greywacke", "chert"
            );

        public static Block newCoal;
        public static Block newCoalCompanion;
        public static Block newIron;
        public static Block newIronCompanion;
        public static Block newGold;
        public static Block newGoldCompanion;
        public static Block newDiamond;
        public static Block newDiamondCompanion;
        public static Block newEmerald;
        public static Block newEmeraldCompanion;
        public static Block newLapis;
        public static Block newLapisCompanion;
        public static Block newRedstone;
        public static Block newRedstoneCompanion;
        public static Block newTinkersOre;
        public static Block newTinkersOreCopperCompanion1;
        public static Block newTinkersOreCopperCompanion2;
        public static Block newTinkersOreTinCompanion1;
        public static Block newTinkersOreTinCompanion2;
        public static Block newTinkersOreAluminumCompanion1;
        public static Block newTinkersOreAluminumCompanion2;
        
        public static boolean COG = false;
        
       
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="iguanaman.undergroundbiomesblender.ClientProxy", serverSide="iguanaman.undergroundbiomesblender.CommonProxy")
        public static CommonProxy proxy;

        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {

        	IguanaConfig.Init(event.getSuggestedConfigurationFile());
        	
        	if (Loader.isModLoaded("CustomOreGenRevived")) COG = true;
        	
        	Block.blocksList[16] = null;
        	newCoalCompanion = (new IguanaOreCompanion(IguanaConfig.coalCompanionId, 16)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreCoalCompanion").setTextureName("coal_ore");
        	newCoal = (new IguanaOre(16, newCoalCompanion)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreCoal").setTextureName("coal_ore");
            GameRegistry.registerBlock(newCoalCompanion, "newCoalCompanion");
            GameRegistry.registerBlock(newCoal, "newCoal");
            OreDictionary.registerOre("oreCoal", new ItemStack(newCoalCompanion, 1, OreDictionary.WILDCARD_VALUE));
            OreDictionary.registerOre("oreCoal", new ItemStack(newCoal, 1, OreDictionary.WILDCARD_VALUE));
            MinecraftForge.setBlockHarvestLevel(newCoalCompanion, "pickaxe", 0);
            MinecraftForge.setBlockHarvestLevel(newCoal, "pickaxe", 0);
            LanguageRegistry.addName(newCoalCompanion, "Coal Ore");
            
        	Block.blocksList[56] = null;
        	newDiamondCompanion = (new IguanaOreCompanion(IguanaConfig.diamondCompanionId, 56)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreDiamondCompanion").setTextureName("diamond_ore");
        	newDiamond = (new IguanaOre(56, newDiamondCompanion)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreDiamond").setTextureName("diamond_ore");
            GameRegistry.registerBlock(newDiamondCompanion, "newDiamondCompanion");
            GameRegistry.registerBlock(newDiamond, "newDiamond");
            OreDictionary.registerOre("oreDiamond", new ItemStack(newDiamondCompanion, 1, OreDictionary.WILDCARD_VALUE));
            OreDictionary.registerOre("oreDiamond", new ItemStack(newDiamond, 1, OreDictionary.WILDCARD_VALUE));
            MinecraftForge.setBlockHarvestLevel(newDiamondCompanion, "pickaxe", 2);
            MinecraftForge.setBlockHarvestLevel(newDiamond, "pickaxe", 2);
            LanguageRegistry.addName(newDiamondCompanion, "Diamond Ore");
        	
        	Block.blocksList[14] = null;
        	newGoldCompanion = (new IguanaOreCompanion(IguanaConfig.goldCompanionId, 14)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreGoldCompanion").setTextureName("gold_ore");
        	newGold = (new IguanaOre(14, newGoldCompanion)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreGold").setTextureName("gold_ore");
            GameRegistry.registerBlock(newGoldCompanion, "newGoldCompanion");
            GameRegistry.registerBlock(newGold, "newGold");
            OreDictionary.registerOre("oreGold", new ItemStack(newGoldCompanion, 1, OreDictionary.WILDCARD_VALUE));
            OreDictionary.registerOre("oreGold", new ItemStack(newGold, 1, OreDictionary.WILDCARD_VALUE));
            MinecraftForge.setBlockHarvestLevel(newGoldCompanion, "pickaxe", 2);
            MinecraftForge.setBlockHarvestLevel(newGold, "pickaxe", 2);
            LanguageRegistry.addName(newGoldCompanion, "Gold Ore");
            
        	Block.blocksList[15] = null;
        	newIronCompanion = (new IguanaOreCompanion(IguanaConfig.ironCompanionId, 15)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreIronCompanion").setTextureName("iron_ore");
        	newIron = (new IguanaOre(15, newIronCompanion)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreIron").setTextureName("iron_ore");
            GameRegistry.registerBlock(newIronCompanion, "newIronCompanion");
            GameRegistry.registerBlock(newIron, "newIron");
            OreDictionary.registerOre("oreIron", new ItemStack(newIronCompanion, 1, OreDictionary.WILDCARD_VALUE));
            OreDictionary.registerOre("oreIron", new ItemStack(newIron, 1, OreDictionary.WILDCARD_VALUE));
            MinecraftForge.setBlockHarvestLevel(newIronCompanion, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(newIron, "pickaxe", 1);
            LanguageRegistry.addName(newIronCompanion, "Iron Ore");
        	
        	Block.blocksList[129] = null;
        	newEmeraldCompanion = (new IguanaOreCompanion(IguanaConfig.emeraldCompanionId, 129)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreEmeraldCompanion").setTextureName("emerald_ore");
        	newEmerald = (new IguanaOre(129, newEmeraldCompanion)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreEmerald").setTextureName("emerald_ore");
            GameRegistry.registerBlock(newEmeraldCompanion, "newEmeraldCompanion");
            GameRegistry.registerBlock(newEmerald, "newEmerald");
            OreDictionary.registerOre("oreEmerald", new ItemStack(newEmeraldCompanion, 1, OreDictionary.WILDCARD_VALUE));
            OreDictionary.registerOre("oreEmerald", new ItemStack(newEmerald, 1, OreDictionary.WILDCARD_VALUE));
            MinecraftForge.setBlockHarvestLevel(newEmeraldCompanion, "pickaxe", 2);
            MinecraftForge.setBlockHarvestLevel(newEmerald, "pickaxe", 2);
            LanguageRegistry.addName(newEmeraldCompanion, "Emerald Ore");
        	
        	Block.blocksList[21] = null;
        	newLapisCompanion = (new IguanaOreCompanion(IguanaConfig.lapisCompanionId, 21)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreLapisCompanion").setTextureName("lapis_ore");
        	newLapis = (new IguanaOre(21, newLapisCompanion)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreLapis").setTextureName("lapis_ore");
            GameRegistry.registerBlock(newLapisCompanion, "newLapisCompanion");
            GameRegistry.registerBlock(newLapis, "newLapis");
            OreDictionary.registerOre("oreLapis", new ItemStack(newLapisCompanion, 1, OreDictionary.WILDCARD_VALUE));
            OreDictionary.registerOre("oreLapis", new ItemStack(newLapis, 1, OreDictionary.WILDCARD_VALUE));
            MinecraftForge.setBlockHarvestLevel(newLapisCompanion, "pickaxe", 1);
            MinecraftForge.setBlockHarvestLevel(newLapis, "pickaxe", 1);
            LanguageRegistry.addName(newLapisCompanion, "Lapis Ore");
        	
        	Block.blocksList[73] = null;
        	newRedstoneCompanion = (new IguanaOreRedstoneCompanion(IguanaConfig.redstoneCompanionId, false, 73)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreRedstoneCompanion").setTextureName("redstone_ore").setCreativeTab(CreativeTabs.tabBlock);
        	newRedstone = (new IguanaOreRedstone(73, false, newRedstoneCompanion)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreRedstone").setTextureName("redstone_ore").setCreativeTab(CreativeTabs.tabBlock);
            GameRegistry.registerBlock(newRedstoneCompanion, "newRedstoneCompanion");
            GameRegistry.registerBlock(newRedstone, "newRedstone");
            OreDictionary.registerOre("oreRedstone", new ItemStack(newRedstoneCompanion, 1, OreDictionary.WILDCARD_VALUE));
            OreDictionary.registerOre("oreRedstone", new ItemStack(newRedstone, 1, OreDictionary.WILDCARD_VALUE));
            MinecraftForge.setBlockHarvestLevel(newRedstoneCompanion, "pickaxe", 2);
            MinecraftForge.setBlockHarvestLevel(newRedstone, "pickaxe", 2);
            LanguageRegistry.addName(newRedstoneCompanion, "Redstone Ore");
            
            if (Loader.isModLoaded("TConstruct"))
            {
            	Block.blocksList[PHConstruct.oreSlag] = null;
	            String[] oreTypes = new String[] { "nether_slag", "nether_cobalt", "nether_ardite", "ore_copper", "ore_tin", "ore_aluminum", "ore_slag" };

	            newTinkersOreCopperCompanion1 = new IguanaOreTinkersCompanion1(IguanaConfig.tinkersCopperCompanion1Id, 3).setUnlocalizedName("ore_copperCompanion1").setTextureName("ore_copper");
	            newTinkersOreCopperCompanion2 = new IguanaOreTinkersCompanion2(IguanaConfig.tinkersCopperCompanion2Id, 3).setUnlocalizedName("ore_copperCompanion2").setTextureName("ore_copper");
	            newTinkersOreTinCompanion1 = new IguanaOreTinkersCompanion1(IguanaConfig.tinkersTinCompanion1Id, 4).setUnlocalizedName("ore_tinCompanion1").setTextureName("ore_tin");
	            newTinkersOreTinCompanion2 = new IguanaOreTinkersCompanion2(IguanaConfig.tinkersTinCompanion2Id, 4).setUnlocalizedName("ore_tinCompanion2").setTextureName("ore_tin");
	            newTinkersOreAluminumCompanion1 = new IguanaOreTinkersCompanion1(IguanaConfig.tinkersAluminumCompanion1Id, 5).setUnlocalizedName("ore_aluminumCompanion1").setTextureName("ore_aluminum");
	            newTinkersOreAluminumCompanion2 = new IguanaOreTinkersCompanion2(IguanaConfig.tinkersAluminumCompanion2Id, 5).setUnlocalizedName("ore_aluminumCompanion2").setTextureName("ore_aluminum");
	            GameRegistry.registerBlock(newTinkersOreCopperCompanion1, "newTinkersOreCopperCompanion1");
	            GameRegistry.registerBlock(newTinkersOreCopperCompanion2, "newTinkersOreCopperCompanion2");
	            GameRegistry.registerBlock(newTinkersOreTinCompanion1, "newTinkersOreTinCompanion1");
	            GameRegistry.registerBlock(newTinkersOreTinCompanion2, "newTinkersOreTinCompanion2");
	            GameRegistry.registerBlock(newTinkersOreAluminumCompanion1, "newTinkersOreAluminumCompanion1");
	            GameRegistry.registerBlock(newTinkersOreAluminumCompanion2, "newTinkersOreAluminumCompanion2");
	            MinecraftForge.setBlockHarvestLevel(newTinkersOreCopperCompanion1, "pickaxe", 1);
	            MinecraftForge.setBlockHarvestLevel(newTinkersOreCopperCompanion2, "pickaxe", 1);
	            MinecraftForge.setBlockHarvestLevel(newTinkersOreTinCompanion1, "pickaxe", 1);
	            MinecraftForge.setBlockHarvestLevel(newTinkersOreTinCompanion2, "pickaxe", 1);
	            MinecraftForge.setBlockHarvestLevel(newTinkersOreAluminumCompanion1, "pickaxe", 1);
	            MinecraftForge.setBlockHarvestLevel(newTinkersOreAluminumCompanion2, "pickaxe", 1);
	            OreDictionary.registerOre("oreCopper", new ItemStack(newTinkersOreCopperCompanion1, 1, OreDictionary.WILDCARD_VALUE));
	            OreDictionary.registerOre("oreCopper", new ItemStack(newTinkersOreCopperCompanion2, 1, OreDictionary.WILDCARD_VALUE));
	            OreDictionary.registerOre("oreTin", new ItemStack(newTinkersOreTinCompanion1, 1, OreDictionary.WILDCARD_VALUE));
	            OreDictionary.registerOre("oreTin", new ItemStack(newTinkersOreTinCompanion2, 1, OreDictionary.WILDCARD_VALUE));
	            OreDictionary.registerOre("oreAluminum", new ItemStack(newTinkersOreAluminumCompanion1, 1, OreDictionary.WILDCARD_VALUE));
	            OreDictionary.registerOre("oreAluminum", new ItemStack(newTinkersOreAluminumCompanion2, 1, OreDictionary.WILDCARD_VALUE));
	            LanguageRegistry.addName(newTinkersOreCopperCompanion1, "Copper Ore");
	            LanguageRegistry.addName(newTinkersOreCopperCompanion2, "Copper Ore");
	            LanguageRegistry.addName(newTinkersOreTinCompanion1, "Tin Ore");
	            LanguageRegistry.addName(newTinkersOreTinCompanion2, "Tin Ore");
	            LanguageRegistry.addName(newTinkersOreAluminumCompanion1, "Aluminum Ore");
	            LanguageRegistry.addName(newTinkersOreAluminumCompanion2, "Aluminum Ore");
	            
	            newTinkersOre = new IguanaOreTinkers(PHConstruct.oreSlag, Material.iron, 10.0F, oreTypes, 
	            		newTinkersOreAluminumCompanion1, newTinkersOreAluminumCompanion2, 
	            		newTinkersOreCopperCompanion1, newTinkersOreCopperCompanion2, 
	            		newTinkersOreTinCompanion1, newTinkersOreTinCompanion2).setUnlocalizedName("tconstruct.stoneore");
	            GameRegistry.registerBlock(newTinkersOre, MetalOreItemBlock.class, "SearedBrick");
	            MinecraftForge.setBlockHarvestLevel(newTinkersOre, 1, "pickaxe", 4);
	            MinecraftForge.setBlockHarvestLevel(newTinkersOre, 2, "pickaxe", 4);
	            MinecraftForge.setBlockHarvestLevel(newTinkersOre, 3, "pickaxe", 1);
	            MinecraftForge.setBlockHarvestLevel(newTinkersOre, 4, "pickaxe", 1);
	            MinecraftForge.setBlockHarvestLevel(newTinkersOre, 5, "pickaxe", 1);
	            OreDictionary.registerOre("oreCobalt", new ItemStack(newTinkersOre, 1, 1));
	            OreDictionary.registerOre("oreArdite", new ItemStack(newTinkersOre, 1, 2));
	            OreDictionary.registerOre("oreCopper", new ItemStack(newTinkersOre, 1, 3));
	            OreDictionary.registerOre("oreTin", new ItemStack(newTinkersOre, 1, 4));
	            OreDictionary.registerOre("oreNaturalAluminum", new ItemStack(newTinkersOre, 1, 5));
            }
        }
       
        @EventHandler
        public void load(FMLInitializationEvent event) {
                Modstats.instance().getReporter().registerMod(this);
        }

        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
        }
        
        @EventHandler
		public void serverStarting(FMLServerStartingEvent event)
		{
			ICommandManager commandManager = ModLoader.getMinecraftServerInstance().getCommandManager();
			ServerCommandManager serverCommandManager = ((ServerCommandManager) commandManager);
			serverCommandManager.registerCommand(new IguanaCommandConfig());
		}

}