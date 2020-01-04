package main;

import meta.Priority;

@Priority(0)
public final class UnfinishedClass1 {
	
	@Priority(1)
	public final UnfinishedClass2 doSomething() {
		return null;
	}
	
	@Priority(3)
	public final int doSomethingElse() {
		return -1;
	}
	
	public static final void trash() {
		System.out.println("splish splash our data's trash");
	}
}
