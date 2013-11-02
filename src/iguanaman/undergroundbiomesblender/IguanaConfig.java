package iguanaman.undergroundbiomesblender;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class IguanaConfig {

    public static int coalCompanionId;
    public static int goldCompanionId;
    public static int ironCompanionId;
    public static int diamondCompanionId;
    public static int emeraldCompanionId;
    public static int lapisCompanionId;
    public static int redstoneCompanionId;
    public static int redstoneGlowingCompanionId;
    public static int tinkersCopperCompanion1Id;
    public static int tinkersCopperCompanion2Id;
    public static int tinkersTinCompanion1Id;
    public static int tinkersTinCompanion2Id;
    public static int tinkersAluminumCompanion1Id;
    public static int tinkersAluminumCompanion2Id;
    
    public static int tickRate;
    
	public static void Init(File file)
	{
        Configuration config = new Configuration(file);
        config.load();
        
        // items
        Property coalCompanionIdProperty = config.getBlock("coalCompanionId", 2560);
        coalCompanionIdProperty.comment = "Block ID for the coal ore companion (less than 4096)";
        coalCompanionId = coalCompanionIdProperty.getInt(2560);
        
        Property goldCompanionIdProperty = config.getBlock("goldCompanionId", 2561);
        goldCompanionIdProperty.comment = "Block ID for the gold ore companion (less than 4096)";
        goldCompanionId = goldCompanionIdProperty.getInt(2561);
        
        Property ironCompanionIdProperty = config.getBlock("ironCompanionId", 2562);
        ironCompanionIdProperty.comment = "Block ID for the iron ore companion (less than 4096)";
        ironCompanionId = ironCompanionIdProperty.getInt(2562);
        
        Property diamondCompanionIdProperty = config.getBlock("diamondCompanionId", 2563);
        diamondCompanionIdProperty.comment = "Block ID for the diamond ore companion (less than 4096)";
        diamondCompanionId = diamondCompanionIdProperty.getInt(2563);
        
        Property emeraldCompanionIdProperty = config.getBlock("emeraldCompanionId", 2564);
        emeraldCompanionIdProperty.comment = "Block ID for the emerald ore companion (less than 4096)";
        emeraldCompanionId = emeraldCompanionIdProperty.getInt(2564);
        
        Property lapisCompanionIdProperty = config.getBlock("lapisCompanionId", 2565);
        lapisCompanionIdProperty.comment = "Block ID for the lapis ore companion (less than 4096)";
        lapisCompanionId = lapisCompanionIdProperty.getInt(2565);
        
        Property redstoneCompanionIdProperty = config.getBlock("redstoneCompanionId", 2566);
        redstoneCompanionIdProperty.comment = "Block ID for the redstone ore companion (less than 4096)";
        redstoneCompanionId = redstoneCompanionIdProperty.getInt(2566);
        
        Property redstoneGlowingCompanionIdProperty = config.getBlock("redstoneGlowingCompanionId", 2567);
        redstoneGlowingCompanionIdProperty.comment = "Block ID for the glowing redstone ore companion (less than 4096)";
        redstoneGlowingCompanionId = redstoneGlowingCompanionIdProperty.getInt(2567);
        
        // TINKERS
        
        Property tinkersCopperCompanion1IdProperty = config.getBlock("tinkersCopperCompanion1Id", 2568);
        tinkersCopperCompanion1IdProperty.comment = "Block ID for the first copper ore companion (less than 4096) (Only used if Tinkers Construct is present)";
        tinkersCopperCompanion1Id = tinkersCopperCompanion1IdProperty.getInt(2568);
        
        Property tinkersCopperCompanion2IdProperty = config.getBlock("tinkersCopperCompanion2Id", 2569);
        tinkersCopperCompanion2IdProperty.comment = "Block ID for the second copper ore companion (less than 4096) (Only used if Tinkers Construct is present)";
        tinkersCopperCompanion2Id = tinkersCopperCompanion2IdProperty.getInt(2569);
        
        Property tinkersTinCompanion1IdProperty = config.getBlock("tinkersTinCompanion1Id", 2570);
        tinkersTinCompanion1IdProperty.comment = "Block ID for the first Tin ore companion (less than 4096) (Only used if Tinkers Construct is present)";
        tinkersTinCompanion1Id = tinkersTinCompanion1IdProperty.getInt(2570);
        
        Property tinkersTinCompanion2IdProperty = config.getBlock("tinkersTinCompanion2Id", 2571);
        tinkersTinCompanion2IdProperty.comment = "Block ID for the second Tin ore companion (less than 4096) (Only used if Tinkers Construct is present)";
        tinkersTinCompanion2Id = tinkersTinCompanion2IdProperty.getInt(2571);
        
        Property tinkersAluminumCompanion1IdProperty = config.getBlock("tinkersAluminumCompanion1Id", 2572);
        tinkersAluminumCompanion1IdProperty.comment = "Block ID for the first Aluminum ore companion (less than 4096) (Only used if Tinkers Construct is present)";
        tinkersAluminumCompanion1Id = tinkersAluminumCompanion1IdProperty.getInt(2572);
        
        Property tinkersAluminumCompanion2IdProperty = config.getBlock("tinkersAluminumCompanion2Id", 2573);
        tinkersAluminumCompanion2IdProperty.comment = "Block ID for the second Aluminum ore companion (less than 4096) (Only used if Tinkers Construct is present)";
        tinkersAluminumCompanion2Id = tinkersAluminumCompanion2IdProperty.getInt(2573);

		ConfigCategory modifiersCategory = config.getCategory("modifiers");
        
        Property tickRateProperty = config.get("modifiers", "tickRate", 20);
        tickRateProperty.comment = "How often ore blocks update while they look for adjacent stone blocks (lower = possibly laggier but textures update faster)";
        tickRate = tickRateProperty.getInt(20);
        if (tickRate < 1) {
        	tickRate = 1;
        	tickRateProperty.set(tickRate);
        	}
        
        config.save();
	}
}
