package net.palermod.tutorialMod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.palermod.tutorialMod.item.ModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class MagicBlock extends Block {
    private static final  Map<Item, Item> Converter_Map =
            Map.of(
                    Items.IRON_BLOCK, Items.GOLD_BLOCK,
                    Items.IRON_INGOT, Items.GOLD_INGOT,
                    Items.IRON_NUGGET, Items.GOLD_NUGGET,
                    Items.DIAMOND, Items.EMERALD,
                    Items.COAL, Items.LAPIS_LAZULI,
                    Items.STONE, Items.COBBLESTONE,
                    Items.POTATO, Items.CARROT
            );

    public MagicBlock(Properties properties) {
        super(properties);
    }

    // You can add custom behavior for the magic block here if needed

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        pLevel.playSound(pPlayer,pPos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(pEntity instanceof ItemEntity itemEntity) {
            if(Converter_Map.containsKey(itemEntity.getItem().getItem())) {
                // Play a sound when an item entity steps on the block
                itemEntity.setItem(new ItemStack(Converter_Map.get(itemEntity.getItem().getItem()), itemEntity.getItem().getCount()));
                pLevel.playSound(null,pPos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.tutorialmod.magic_block.tooltip"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
