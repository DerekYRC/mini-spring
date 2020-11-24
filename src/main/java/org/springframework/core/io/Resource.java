package org.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 资源的抽象接口
 *
 * @author derekyi
 * @date 2020/11/25
 */
public interface Resource {

	boolean exists();

	File getFile() throws IOException;

	InputStream getInputStream() throws IOException;

	URL getURL() throws IOException;
}
