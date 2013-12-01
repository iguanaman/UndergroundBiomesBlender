package iguanaman.undergroundbiomesblender;

import java.util.Random;

import cpw.mods.fml.common.FMLLog;

import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import tconstruct.blocks.MetalOre;

public class IguanaOreTinkers extends MetalOre {

	protected Block aluminumCompanionBlock1;
	protected Block aluminumCompanionBlock2;
	protected Block copperCompanionBlock1;
	protected Block copperCompanionBlock2;
	protected Block tinCompanionBlock1;
	protected Block tinCompanionBlock2;
	
	public IguanaOreTinkers(int id, Material material, float hardness, String[] tex, 
			Block aluminumCompanion1, Block aluminumCompanion2,
			Block copperCompanion1, Block copperCompanion2,
			Block tinCompanion1, Block tinCompanion2) {
		super(id, material, hardness, tex);
		aluminumCompanionBlock1 = aluminumCompanion1;
		aluminumCompanionBlock2 = aluminumCompanion2;
		copperCompanionBlock1 = copperCompanion1;
		copperCompanionBlock2 = copperCompanion2;
		tinCompanionBlock1 = tinCompanion1;
		tinCompanionBlock2 = tinCompanion2;
        if (UndergroundBiomesBlender.COG) this.setTickRandomly(true);
	}

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
	@Override
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
		if (UndergroundBiomesBlender.COG) this.updateTick(par1World, par2, par3, par4, par1World.rand);
		return super.onBlockPlaced(par1World, par2, par3, par4, par5, par6, par7, par8, par9);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
	@Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
		if (!UndergroundBiomesBlender.COG) this.updateTick(par1World, par2, par3, par4, par1World.rand);
    }


    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World par1World, int x, int y, int z, Random par5Random)
    {
        int thisMeta = par1World.getBlockMetadata(x, y, z);

		if (thisMeta > 2)
		{
			int newMeta = 0;
			for (int coord = 0; coord < 3; ++coord)
			{
				for (int offset = -1; offset <= 1; offset += 2)
				{
					int newx = x;
					int newy = y;
					int newz = z;
	
					if (coord == 0) newx += offset;
					else if (coord == 1) newy += offset;
					else if (coord == 2) newz += offset;
					
					int bID = par1World.getBlockId(newx, newy, newz);
			        int meta = par1World.getBlockMetadata(newx, newy, newz);
			        Block block = Block.blocksList[bID];
			        if (block != null)
			        {
			        	if (block instanceof BlockMetadataBase)
				        {
				        	//Some stone adjacent
				        	String blockName = ((BlockMetadataBase)block).getBlockTypeName(meta);
				        	if (UndergroundBiomesBlender.blockNames.contains(blockName))
				        	{
				        		newMeta = UndergroundBiomesBlender.blockNames.indexOf(blockName);
				        		break;
				        	}
				        }
			        	else if (block instanceof IguanaOreCompanion || block instanceof IguanaOreRedstoneCompanion || 
				        		block instanceof IguanaOreTinkersCompanion2)
			        	{
			        		newMeta = meta + 14;
			        		break;
			        	}
				        else if ((block instanceof IguanaOre || block instanceof IguanaOreRedstone || 
				        		block instanceof IguanaOreTinkersCompanion1) && meta > 0)
				        {
			        		newMeta = meta;
				        	break;
				        }
		        	}
				}
			}
			
			if (newMeta > 0)
			{
		    	//FMLLog.warning("ore " + thisMeta + " changed to " + newMeta);

				Block companionBlock1 = null;
				Block companionBlock2 = null;
				
				switch (thisMeta)
				{
				case 3: companionBlock1 = copperCompanionBlock1; companionBlock2 = copperCompanionBlock2; break;
				case 4: companionBlock1 = tinCompanionBlock1; companionBlock2 = tinCompanionBlock2; break;
				case 5: companionBlock1 = aluminumCompanionBlock1; companionBlock2 = aluminumCompanionBlock2; break;
				}
				
				if (newMeta < 16)
					par1World.setBlock(x, y, z, companionBlock1.blockID, newMeta, 2);
				else
					par1World.setBlock(x, y, z, companionBlock2.blockID, newMeta - 14, 2);
			}
			else
			{
				par1World.scheduleBlockUpdate(x, y, z, this.blockID, IguanaConfig.tickRate);
			}
		}
	}
}
