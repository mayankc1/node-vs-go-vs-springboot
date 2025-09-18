package com.example.shorten;

import java.time.LocalDateTime;
import com.aventrix.jnanoid.jnanoid.*;
import com.example.shorten.ShortenedUrl;
import com.example.shorten.UrlShortenerRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UrlShortenerService {

    final String baseUrl = "http://test.short/";

    @Autowired
    UrlShortenerRepository shortenerRepository;

    public String shorten(String srcUrl) {
        if(srcUrl.isEmpty()) {
            return "";
        }

        String urlId = NanoIdUtils.randomNanoId(
            NanoIdUtils.DEFAULT_NUMBER_GENERATOR,
            NanoIdUtils.DEFAULT_ALPHABET,
            10
        );

        String shortUrl = baseUrl + urlId;
        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setId(urlId);
        shortenedUrl.setSrcUrl(srcUrl);
        shortenedUrl.setCreated(LocalDateTime.now());
        shortenedUrl.setLastAccessed(LocalDateTime.now());
        ShortenedUrl savedData = shortenerRepository.save(shortenedUrl);
        return savedData.getId().isEmpty() ? "" : shortUrl;

    }

}