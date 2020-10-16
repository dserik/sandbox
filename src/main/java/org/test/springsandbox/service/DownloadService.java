package org.test.springsandbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.test.springsandbox.exception.SystemException;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class DownloadService {

    //changes to test branch

    private static final String DOWNLOAD_DIR =
            System.getProperty("user.dir") + File.separator +
                    "download" + File.separator;

    void download(String fileUrl, String fileName, String directory) {
        String filePath = DOWNLOAD_DIR + directory + File.separator + fileName;
        String tempFilePath = filePath + ".dat";

        try {
            //noinspection ResultOfMethodCallIgnored
            new File(tempFilePath).getParentFile().mkdirs();
            URLConnection conn = new URL(fileUrl).openConnection();
            conn.connect();

            try (InputStream is = conn.getInputStream();
                 OutputStream outputStream = new FileOutputStream(tempFilePath)) {
                byte[] buffer = new byte[4096];
                int len;

                while ((len = is.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, len);
                }
                Files.move(Paths.get(tempFilePath), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                log.info("downloaded '{}'", filePath);
            }
        } catch (FileNotFoundException ignore) {
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new SystemException(e);
        }
    }
}
