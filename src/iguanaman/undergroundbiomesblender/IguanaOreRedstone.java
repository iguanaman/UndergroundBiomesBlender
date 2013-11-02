package iguanaman.undergroundbiomesblender;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.common.block.BlockMetadataBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.BlockStone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class IguanaOreRedstone extends BlockRedstoneOre {

	protected Block companionBlock;
	public Icon[] blockIcons;

	public IguanaOreRedstone(int par1, boolean par2, Block companion) {
		super(par1, par2);
		companionBlock = companion;
        this.setTickRandomly(true);
	}

	@SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
    	this.blockIcons = new Icon[UndergroundBiomesBlender.blockNames.size()];
    	this.blockIcon = par1IconRegister.registerIcon(this.getTextureName());
        this.blockIcons[0] = par1IconRegister.registerIcon(this.getTextureName());
        this.blockIcons[1] = par1IconRegister.registerIcon(this.getTextureName());
        for (int i = 2; i < this.blockIcons.length; ++i)
        {
            this.blockIcons[i] = par1IconRegister.registerIcon("undergroundbiomesblender:" + this.getTextureName() + "/" + UndergroundBiomesBlender.blockNames.get(i));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon (int side, int metadata)
    {
    	return this.blockIcons[metadata];
    }

    /**
     * The redstone ore glows.
     */
    @Override
    public void glow(World par1World, int par2, int par3, int par4)
    {
        this.sparkle(par1World, par2, par3, par4);
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (this.blockID == UndergroundBiomesBlender.newRedstone.blockID)
        {
            par1World.setBlock(par2, par3, par4, UndergroundBiomesBlender.newRedstoneGlowing.blockID, meta, 2);
        }
        else if (this.blockID == UndergroundBiomesBlender.newRedstoneCompanion.blockID)
        {
            par1World.setBlock(par2, par3, par4, UndergroundBiomesBlender.newRedstoneGlowingCompanion.blockID, meta, 2);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
	@Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
    	this.updateTick(par1World, par2, par3, par4, par1World.rand);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World par1World, int x, int y, int z, Random par5Random)
    {
        int thisMeta = par1World.getBlockMetadata(x, y, z);
        
        if (this.blockID == UndergroundBiomesBlender.newRedstoneGlowing.blockID)
        {
            par1World.setBlock(x, y, z, UndergroundBiomesBlender.newRedstone.blockID, thisMeta, 2);
        }
        else if (this.blockID == UndergroundBiomesBlender.newRedstoneGlowingCompanion.blockID)
        {
            par1World.setBlock(x, y, z, UndergroundBiomesBlender.newRedstoneCompanion.blockID, thisMeta, 2);
        }
        
    	if (thisMeta == 0)
    	{
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
			        	if (block instanceof BlockStone)
			        	{
				        	//same vanilla stone adjacent
			        		thisMeta = 1;
				        	break;
			        	}
			        	else if (block instanceof BlockMetadataBase)
				        {
				        	//Some stone adjacent
				        	String blockName = ((BlockMetadataBase)block).getBlockTypeName(meta);
				        	if (UndergroundBiomesBlender.blockNames.contains(blockName))
				        	{
				        		thisMeta = UndergroundBiomesBlender.blockNames.indexOf(blockName);
				        		break;
				        	}
				        }
			        	else if (block instanceof IguanaOreCompanion || block instanceof IguanaOreRedstoneCompanion)
			        	{
			        		thisMeta = meta + 14;
			        		break;
			        	}
				        else if ((block instanceof IguanaOre || block instanceof IguanaOreRedstone) && meta > 0)
				        {
				        	//same ore adjacent
			        		thisMeta = meta;
				        	break;
				        }
		        	}
				}
			}

			if (thisMeta != 0)
			{
				if (thisMeta < 16)
					par1World.setBlockMetadataWithNotify(x, y, z, thisMeta, 2);
				else
					par1World.setBlock(x, y, z, companionBlock.blockID, thisMeta - 14, 2);
			}
			else
			{
				par1World.scheduleBlockUpdate(x, y, z, this.blockID, IguanaConfig.tickRate);
			}
		}
	}

}
