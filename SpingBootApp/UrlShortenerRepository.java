package com.example.shorten;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shorten.ShortenedUrl;

public interface UrlShortenerRepository extends JpaRepository<ShortenedUrl, String> {

}