package iguanaman.undergroundbiomesblender;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class IguanaOreRedstoneCompanion extends IguanaOreRedstone {

	protected int companionID;
	public IguanaOreRedstoneCompanion(int par1, boolean par2, int companion) {
		super(par1, par2, null);
        this.setCreativeTab(null);
        companionID = companion;
	}
	
    public static final List<String> blockNames = Arrays.asList("", "", "soapstone", "migmatite",
    "limestone", "chalk", "shale", "siltstone", "ligniteBlock", "dolomite", "greywacke", "chert"
    );

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
