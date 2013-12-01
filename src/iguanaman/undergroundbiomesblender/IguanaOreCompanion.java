package iguanaman.undergroundbiomesblender;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class IguanaOreCompanion extends IguanaOre {

	protected int companionID;
    public static final List<String> blockNames = Arrays.asList("", "", "soapstone", "migmatite",
    "limestone", "chalk", "shale", "siltstone", "ligniteBlock", "dolomite", "greywacke", "chert"
    );
	
	public IguanaOreCompanion(int par1, int companion) {
		super(par1, null);
        this.setCreativeTab(null);
        companionID = companion;
        this.setTickRandomly(false);
	}

	@SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
    	this.blockIcons = new Icon[blockNames.size()];
    	this.blockIcon = par1IconRegister.registerIcon(this.getTextureName());
        this.blockIcons[0] = par1IconRegister.registerIcon(this.getTextureName());
        this.blockIcons[1] = par1IconRegister.registerIcon(this.getTextureName());
        for (int i = 2; i < this.blockIcons.length; ++i)
        {
            this.blockIcons[i] = par1IconRegister.registerIcon("undergroundbiomesblender:" + this.getTextureName() + "/" + blockNames.get(i));
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
	@Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {}

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World par1World, int x, int y, int z, Random par5Random) {}
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
	@Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID == UndergroundBiomesBlender.newCoalCompanion.blockID ? Item.coal.itemID : (this.blockID == UndergroundBiomesBlender.newDiamondCompanion.blockID ? Item.diamond.itemID : (this.blockID == UndergroundBiomesBlender.newLapisCompanion.blockID ? Item.dyePowder.itemID : (this.blockID == UndergroundBiomesBlender.newEmeraldCompanion.blockID ? Item.emerald.itemID : companionID)));
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
	@Override
    public int quantityDropped(Random par1Random)
    {
        return this.blockID == UndergroundBiomesBlender.newLapisCompanion.blockID ? 4 + par1Random.nextInt(5) : 1;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
	@Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

        if (this.idDropped(par5, par1World.rand, par7) != this.blockID)
        {
            int j1 = 0;

            if (this.blockID == UndergroundBiomesBlender.newCoalCompanion.blockID)
            {
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 0, 2);
            }
            else if (this.blockID == UndergroundBiomesBlender.newDiamondCompanion.blockID)
            {
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 3, 7);
            }
            else if (this.blockID == UndergroundBiomesBlender.newEmeraldCompanion.blockID)
            {
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 3, 7);
            }
            else if (this.blockID == UndergroundBiomesBlender.newLapisCompanion.blockID)
            {
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);
            }

            this.dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
	@Override
    public int damageDropped(int par1)
    {
        return this.blockID == UndergroundBiomesBlender.newLapisCompanion.blockID ? 4 : 0;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return companionID;
    }
    
    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    @Override
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(companionID, 1, 0);
    }

}
