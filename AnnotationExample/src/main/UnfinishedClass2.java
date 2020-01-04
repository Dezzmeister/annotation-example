package main;

import meta.Priority;

public abstract class UnfinishedClass2 {
	
	@Priority(1)
	public final void weird() {
		UnfinishedClass1.trash();
	}
}
