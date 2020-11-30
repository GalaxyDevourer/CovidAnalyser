package models.utils.downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class FileDownloader {
    private String path;
    private String url;

    public FileDownloader(String url, String path) {
        this.path = path;
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void downloadFile (String filename) {
        try {
            URL fileURL = new URL(url + "/" + filename);
            InputStream in = fileURL.openStream();

            OutputStream os = new FileOutputStream( path + "/" + filename);
            byte[] buffer = new byte[4096];

            int n;
            while ( (n = in.read(buffer)) != -1 ){
                os.write(buffer, 0, n);
            }
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
