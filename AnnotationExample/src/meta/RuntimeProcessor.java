package meta;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RuntimeProcessor {
	
	public static final List<Annotation> findAnnotations(final Class<? extends Annotation> annotationClass) throws IOException {
		final String workingDirectory = System.getProperty("user.dir") + "\\src\\";
		final Path srcDir = Paths.get(workingDirectory);
		
		final List<Class<?>> allClasses = new ArrayList<Class<?>>();
		final List<Class<?>> topClasses = Files.walk(srcDir, Integer.MAX_VALUE, FileVisitOption.FOLLOW_LINKS)
									.filter(p -> p.getFileName().toString().endsWith(".java"))
									.map(p -> {
										String name = p.toString().replace(workingDirectory, "");
										String fqName = name.replace('\\', '.').substring(0, name.indexOf(".java"));
										try {
											return Class.forName(fqName);
										} catch (Exception e) {
											e.printStackTrace();
											return null;
										}
									})
									.collect(Collectors.toList());
		topClasses.forEach(c -> allClasses.addAll(getNestedClasses(c)));
		
		final List<Method> methods = new ArrayList<Method>();
		allClasses.forEach(c -> methods.addAll(Arrays.asList(c.getDeclaredMethods())));
		allClasses.forEach(System.out::println);
		return null;
	}
	
	private static final List<Class<?>> getNestedClasses(final Class<?> root) {
		final List<Class<?>> accumulated = new ArrayList<Class<?>>();
		accumulated.add(root);
		
		if (root.getDeclaredClasses().length == 0) {
			return accumulated;
		}
		
		final List<Class<?>> innerClasses = Arrays.asList(root.getDeclaredClasses());
		accumulated.addAll(innerClasses);
		
		innerClasses.forEach(c -> accumulated.addAll(getNestedClasses(c)));
		
		return accumulated;
	}
} 
