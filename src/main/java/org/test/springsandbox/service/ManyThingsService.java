package org.test.springsandbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.test.springsandbox.exception.SystemException;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManyThingsService {

    private final DownloadService downloadService;

    private static final String AUDIO_BASE_URL = "http://study.aitech.ac.jp/tat/";


    /**
     * @param pageUrl url like {@code http://www.manythings.org/audio/sentences/281.html}
     */
    public void downloadAll(String pageUrl, String article) {
        try {
            Document doc = Jsoup.connect(pageUrl).get();
            Elements links = doc.select("dt");
            String dirName = (article == null)
                    ? pageUrl.replaceAll("[^\\d]+(\\d+).+", "$1")
                    : article;

            for (Element link : links) {
                String script = link.getElementsByTag("script").first().data();
                String id = script.replaceAll("l\\((\\d+),\\d+\\)", "$1");
                downloadService.download(AUDIO_BASE_URL + id + ".mp3" , link.text() + ".mp3", dirName);
            }

            log.info("Complete");
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new SystemException(e);
        }



    }
}
