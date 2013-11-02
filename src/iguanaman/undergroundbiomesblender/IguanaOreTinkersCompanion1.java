package iguanaman.undergroundbiomesblender;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import tconstruct.blocks.TConstructBlock;

public class IguanaOreTinkersCompanion1 extends TConstructBlock {

	public Icon[] blockIcons;
	int dropMeta = 0; 

	public IguanaOreTinkersCompanion1(int id, int dropMeta) {
		super(id, Material.iron, 3f, new String[]{});
        this.setCreativeTab(null);
		this.dropMeta = dropMeta;
	}

    /**
     * Returns the ID of the items to drop on destruction.
     */
	@Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return UndergroundBiomesBlender.newTinkersOre.blockID;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return UndergroundBiomesBlender.newTinkersOre.blockID;
    }

    @Override
    public int damageDropped (int meta)
    {
        return dropMeta;
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

    @SideOnly(Side.CLIENT)
    @Override
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {}

}
