package main

import (
	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.New()

	r.POST("/shorten", HandleRequest)

	r.Run(":3000")
}
