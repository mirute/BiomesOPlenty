package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.WorldGenAutumn;
import biomesoplenty.worldgen.WorldGenAutumn2;
import biomesoplenty.worldgen.WorldGenDeadTree2;
import biomesoplenty.worldgen.WorldGenMaple;
import biomesoplenty.worldgen.WorldGenTaiga10;
import biomesoplenty.worldgen.WorldGenTaiga5;

public class BiomeGenSeasonalSpruceForest extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenSeasonalSpruceForest(int par1)
	{
		super(par1);
		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 20;
		customBiomeDecorator.grassPerChunk = 8;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.toadstoolsPerChunk = 4;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 1);
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenAutumn2(false) : (par1Random.nextInt(3) == 0 ? new WorldGenAutumn(false) : (par1Random.nextInt(2) == 0 ? new WorldGenTaiga5(false) : (par1Random.nextInt(4) == 0 ? new WorldGenTaiga10(false) : (par1Random.nextInt(3) == 0 ? new WorldGenMaple(false) : (par1Random.nextInt(5) == 0 ? new WorldGenDeadTree2(false) : (par1Random.nextInt(6) == 0 ? worldGeneratorBigTree : worldGeneratorTrees))))));
	}
	
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = 3 + par2Random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16);
			int var8 = par2Random.nextInt(28) + 4;
			int var9 = par4 + par2Random.nextInt(16);
			int var10 = par1World.getBlockId(var7, var8, var9);

			if (var10 == Block.stone.blockID)
			{
				par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
			}
		}
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeFoliageColor()
	{
		return 11781186;
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 12502092;
		//return 12502595;
	}
}
