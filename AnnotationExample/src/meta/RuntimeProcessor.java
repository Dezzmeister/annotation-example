package meta;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class RuntimeProcessor {
	
	public static final List<Annotation> findAnnotations(final Class<? extends Annotation> annotationClass) throws IOException {
		final String workingDirectory = System.getProperty("user.dir") + "\\src\\";
		final Path srcDir = Paths.get(workingDirectory);
		
		final List<Class<?>> allClasses = Files.walk(srcDir, Integer.MAX_VALUE, FileVisitOption.FOLLOW_LINKS)
									.filter(p -> (!Files.isDirectory(p, LinkOption.NOFOLLOW_LINKS)) && p.getFileName().toString().endsWith(".java"))
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
				
	}
} 
