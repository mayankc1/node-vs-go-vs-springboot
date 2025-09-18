package com.example.shorten;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "shortenedurls")
public class ShortenedUrl {
  @Id
  private String id;

  private String srcurl;

  private LocalDateTime created;

  private LocalDateTime lastaccessed;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSrcUrl() {
    return srcurl;
  }

  public void setSrcUrl(String srcUrl) {
    this.srcurl = srcUrl;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public LocalDateTime getLastAccessed() {
    return lastaccessed;
  }

  public void setLastAccessed(LocalDateTime lastAccessed) {
    this.lastaccessed = lastAccessed;
  }
}