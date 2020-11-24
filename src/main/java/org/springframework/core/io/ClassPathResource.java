package org.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 类路径下的资源
 *
 * @author derekyi
 * @date 2020/11/25
 */
public class ClassPathResource extends AbstractResource {

	private final String path;

	private ClassLoader classLoader;

	public ClassPathResource(String path) {
		this.path = path;
		this.classLoader = ClassPathResource.class.getClassLoader();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream is = this.classLoader.getResourceAsStream(this.path);
		if (is == null) {
			throw new FileNotFoundException("resource cannot be opened because it does not exist");
		}
		return is;
	}

	@Override
	public URL getURL() throws IOException {
		URL url = this.classLoader.getResource(this.path);
		if (url == null) {
			throw new FileNotFoundException("resource cannot be resolved to URL because it does not exist");
		}
		return url;
	}

	public String getPath() {
		return path;
	}
}
