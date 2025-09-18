package main

import gonanoid "github.com/matoous/go-nanoid/v2"

var baseUrl = "http://test.short/"

func Shorten(srcUrl string) string {

	id, _ := gonanoid.New(10)
	shortenedUrl := baseUrl + id
	Save(id, srcUrl)
	return shortenedUrl
}
