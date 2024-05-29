package com.haha.xiuxian.worldgen;

import com.haha.xiuxian.XiuXian;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;

import javax.annotation.Nonnull;
import java.util.Random;

public class InitialHouse extends WorldGenerator implements IStructure, IWorldGenerator {

    public static final InitialHouse initialHouse = new InitialHouse("initial");
    public static String structureName;


    public InitialHouse(String name) {
        structureName = name;
    }


    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    }

    @Override
    public boolean generate(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos position) {
        generateStructure(worldIn);
        return true;
    }

    public static void generateStructure(World world) {
        MinecraftServer minecraftServer = world.getMinecraftServer();
        TemplateManager manager = worldserver.getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation(XiuXian.MODID, structureName);
        Template template = manager.get(minecraftServer, location);

        if (template != null) {
            if (!world.playerEntities.isEmpty() & !world.isRemote) {
                int x = (int) world.playerEntities.get(0).posX;
                int z = (int) world.playerEntities.get(0).posZ;
                BlockPos templatePos = new BlockPos(x, calculateGenerationHeight(world, x, z), z);
                PlacementSettings settings = new PlacementSettings();
                template.addBlocksToWorldChunk(world, templatePos, settings.setReplacedBlock(Blocks.AIR));
            }
        }
    }

    private static int calculateGenerationHeight(World world, int x, int z) {
        int y = world.getHeight();
        boolean foundGround = false;

        while (!foundGround && y-- >= 0) {
            Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.STONE;
        }
        return y;
    }
}
