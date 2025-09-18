package com.example.shorten;

public class UrlShortenerResponse {
    private String srcUrl;
    private String shortenedUrl;

    public UrlShortenerResponse() {
        
    }

    public UrlShortenerResponse(String srcUrl, String shortenedUrl) {
        this.srcUrl = srcUrl;
        this.shortenedUrl = shortenedUrl;
    }

    public String getSrcUrl() {
        return this.srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public String getShortenedUrl() {
        return this.shortenedUrl;
    }

    public void setShortenedUrl(String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }
}