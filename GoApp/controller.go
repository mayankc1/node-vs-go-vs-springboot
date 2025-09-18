package main

import (
	"net/http"
	"strings"

	"github.com/gin-gonic/gin"
)

type ShortenUrlRequestBody struct {
	SrcUrl string `json:"srcUrl"`
}

type ShortenUrlResponseBody struct {
	SrcUrl       string `json:"srcUrl"`
	ShortenedUrl string `json:"shortenedUrl"`
}

func HandleRequest(c *gin.Context) {
	var reqBody ShortenUrlRequestBody
	if err := c.BindJSON(&reqBody); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Request is not a valid JSON"})
		return
	}

	srcUrl := reqBody.SrcUrl
	if len(srcUrl) > 250 {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Parameter 'srcUrl' must not be more than 250 characters"})
		return
	}

	if !(strings.HasPrefix(srcUrl, "http://") || strings.HasPrefix(srcUrl, "https://")) {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Parameter 'srcUrl' must start with http:// or https://"})
		return
	}

	shortenedUrl := Shorten(srcUrl)
	if len(shortenedUrl) == 0 {
		c.JSON(http.StatusInternalServerError, gin.H{"error": "Failed to shorten"})
		return
	}

	rspBody := &ShortenUrlResponseBody{
		SrcUrl:       srcUrl,
		ShortenedUrl: shortenedUrl,
	}
	c.JSON(http.StatusOK, rspBody)
}
