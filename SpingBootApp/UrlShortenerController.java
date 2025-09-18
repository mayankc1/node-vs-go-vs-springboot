package com.example.shorten;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import javax.print.attribute.standard.MediaTray;

import com.example.shorten.UrlShortenerRequest;
import com.example.shorten.UrlShortenerResponse;
import com.example.shorten.UrlShortenerError;
import com.example.shorten.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class UrlShortenerController {

    @Autowired
    UrlShortenerService shortenerService;

    @PostMapping(
        value="/shorten",
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity handleRequest(@RequestBody UrlShortenerRequest shortenRequest) {
        if(shortenRequest.getSrcUrl() == null) {
            return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new UrlShortenerError("Parameter 'srcUrl' is missing")); 
        }

        String srcUrl = shortenRequest.getSrcUrl();
        if(srcUrl.length()>250) {
            return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new UrlShortenerError("Parameter 'srcUrl' must not be more than 250 characters"));
        }

        if(!(srcUrl.startsWith("http://") || srcUrl.startsWith("https://"))) {
            return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new UrlShortenerError("Parameter 'srcUrl' must start with http:// or https://"));
        }

        String shortenedUrl = shortenerService.shorten(srcUrl);
        if(shortenedUrl.isEmpty()) {
            return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new UrlShortenerError("Failed to shorten"));
        }

        UrlShortenerResponse resp = new UrlShortenerResponse(srcUrl, shortenedUrl);
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(resp);
    }
}

