package main.dumb;

import main.UnfinishedClass2;
import meta.Priority;

@Priority(2)
public final class UnfinishedClass3 extends UnfinishedClass2 {
	private int bricks;
	
	public UnfinishedClass3() {
		bricks = getBricks();
	}
	
	@Priority(3)
	public final void eatBrick() {
		bricks--;
	}
	
	@Priority(2)
	private final int getBricks() {
		return (int) System.currentTimeMillis();
	}
}
