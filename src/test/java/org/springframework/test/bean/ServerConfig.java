package org.springframework.test.bean;
/**
 * @author NingMao
 * @since 2025-07-17
 */
public class ServerConfig {

    private String url;
    private String path;
    private String location;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ServerConfig{" +
                "url='" + url + '\'' +
                ", path='" + path + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
