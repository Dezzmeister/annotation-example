package main;

import java.io.IOException;
import java.net.URISyntaxException;

import meta.Priority;
import meta.RuntimeProcessor;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {
		System.out.println(System.getProperty("user.dir") + "\\src\\");
		final var classes = RuntimeProcessor.findAnnotations(Priority.class);
	}

}
