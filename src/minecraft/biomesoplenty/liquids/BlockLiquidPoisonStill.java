package biomesoplenty.liquids;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Potions;
import biomesoplenty.configuration.BOPPotions;

public class BlockLiquidPoisonStill extends BlockStationary 
{
    public BlockLiquidPoisonStill(int id) 
    {
        super(id, Material.water);
        
        this.blockHardness = 100F;
        this.setLightOpacity(3);
        this.disableStats();
    }
    
    @Override
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
    	return 16777215;
    }
    
    @Override
    public void onBlockAdded(World par1World, int x, int y, int z)
    {
        this.setSpringWaterNotStationary(par1World, x, y, z);;
    }
    
    public void setSpringWaterNotStationary(World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        par1World.setBlock(par2, par3, par4, this.blockID - 1, l, 2);
        par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID - 1, this.tickRate(par1World));
    }

    
    @Override
    public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity par5Entity)
    {
        int meta = par1World.getBlockMetadata(x, y, z);
        
        if (par5Entity instanceof EntityLiving) 
        {
        	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
        	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 100));
        }  
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) 
	{
		this.theIcon = new Icon[]{iconRegister.registerIcon("BiomesOPlenty:liquid_poison_still"), iconRegister.registerIcon("BiomesOPlenty:liquid_poison_flowing")};
	}
}