package com.calclavia.microblock.injection.component;

import com.calclavia.microblock.micro.MicroblockContainer;
import nova.core.block.Block;
import nova.core.component.renderer.ItemRenderer;

/**
 * @author Calclavia
 */
public class ContainerItemRenderer extends ItemRenderer {

	public ContainerItemRenderer(Block provider) {
		if (provider.has(MicroblockContainer.class)) {
			setOnRender(model -> provider
					.get(MicroblockContainer.class)
					.microblocks(ItemRenderer.class)
					.forEach(renderer -> renderer.onRender.accept(model))
			);
		}
	}

	public ContainerItemRenderer(Block provider, Block contained) {
		setOnRender(contained.get(ItemRenderer.class).onRender::accept);
	}
}
