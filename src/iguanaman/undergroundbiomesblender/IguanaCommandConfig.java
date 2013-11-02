package iguanaman.undergroundbiomesblender;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class IguanaCommandConfig extends CommandBase {

	@Override
	public String getCommandName() {
		return "ubblender";
	}
	
    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		boolean worked = false;
		if (astring.length == 2)
		{
			String setting = astring[0];
			String value = astring[1];
			/*
			if (setting.equalsIgnoreCase("damageSlowdown"))
			{
				IguanaConfig.damageSlowdown = Boolean.parseBoolean(value);
				worked = true;
			}
			else if (setting.equalsIgnoreCase("damageSlowdownDifficultyScaling"))
			{
				IguanaConfig.damageSlowdownDifficultyScaling = Boolean.parseBoolean(value);
				worked = true;
			}
			else if (setting.equalsIgnoreCase("blockMovementSpeeds"))
			{
				IguanaConfig.blockMovementSpeeds = Boolean.parseBoolean(value);
				worked = true;
			}
			else if (setting.equalsIgnoreCase("addDebugText"))
			{
				IguanaConfig.addDebugText = Boolean.parseBoolean(value);
				worked = true;
			}
			else if (setting.equalsIgnoreCase("addHudText"))
			{
				IguanaConfig.addHudText = Boolean.parseBoolean(value);
				worked = true;
			}
			else if (setting.equalsIgnoreCase("detailedHudText"))
			{
				IguanaConfig.detailedHudText = Boolean.parseBoolean(value);
				worked = true;
			}
			else if (setting.equalsIgnoreCase("carryWeightDifficultyScaling"))
			{
				IguanaConfig.carryWeightDifficultyScaling = Boolean.parseBoolean(value);
				worked = true;
			}
			else if (setting.equalsIgnoreCase("maxCarryWeight") && isInteger(value))
			{
				IguanaConfig.maxCarryWeight = Integer.parseInt(value);
				worked = true;
			}
			*/
			
			if (setting.equalsIgnoreCase("tickRate") && isInteger(value))
			{
				IguanaConfig.tickRate = Integer.parseInt(value);
				worked = true;
			}
			
			if (worked) notifyAdmins(icommandsender, 0, "Set '" + setting + "' to '" + value + "'", new Object[0]);
		}

		if (!worked) throw new WrongUsageException("/" + getCommandName() + " <settingname> <value>", new Object[0]);
			
	}


    /**
     * Parses an int from the given string with a specified minimum.
     */
    public static int parseIntWithMinMax(ICommandSender par0ICommandSender, String par1Str, int min, int max)
    {
        return parseIntBounded(par0ICommandSender, par1Str, min, max);
    }
    
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        }
        // only got here if we didn't return false
        return true;
    }

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/" + getCommandName() + " <settingname> <value>";
	}

}
