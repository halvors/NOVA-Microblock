package com.calclavia.microblock.injection.prefab;

import nova.core.block.Block;
import nova.core.component.Component;

import java.util.function.Function;

/**
 * Injects a dummy component that handles all interactions
 * @author Calclavia
 */
public class ForwardInjector<COMPONENT extends Component> extends DefaultInjector<COMPONENT> {

	/**
	 * Container goes to Component
	 */
	public final Function<Block, COMPONENT> constructor;

	public ForwardInjector(Class<COMPONENT> clazz, Function<Block, COMPONENT> constructor) {
		super(clazz);
		this.constructor = constructor;
	}

	@Override
	public boolean injectForward(COMPONENT component, Block contained, Block container) {
		if (!container.has(componentType())) {
			container.add(constructor.apply(container));
			return true;
		}
		return false;
	}
}