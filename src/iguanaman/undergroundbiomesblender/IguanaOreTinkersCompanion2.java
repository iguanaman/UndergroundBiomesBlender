package iguanaman.undergroundbiomesblender;

import java.util.Arrays;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class IguanaOreTinkersCompanion2 extends IguanaOreTinkersCompanion1 {

    public static final List<String> blockNames = Arrays.asList("", "", "soapstone", "migmatite",
    "limestone", "chalk", "shale", "siltstone", "ligniteBlock", "dolomite", "greywacke", "chert"
    );
    
	public IguanaOreTinkersCompanion2(int id, int dropMeta) {
		super(id, dropMeta); 
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

}
