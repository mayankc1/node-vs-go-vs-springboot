package main

import (
	"context"
	"database/sql"
	"os"
	"time"

	"github.com/uptrace/bun"
	"github.com/uptrace/bun/dialect/pgdialect"
	"github.com/uptrace/bun/driver/pgdriver"
)

type ShortenedUrl struct {
	bun.BaseModel `bun:"table:shortenedurls"`
	Id            string    `bun:"id,pk"`
	SrcUrl        string    `bun:"srcurl"`
	Created       time.Time `bun:"created"`
	LastAccessed  time.Time `bun:"lastaccessed"`
}

var sqldb = sql.OpenDB(pgdriver.NewConnector(
	pgdriver.WithNetwork("tcp"),
	pgdriver.WithAddr("localhost:5432"),
	pgdriver.WithUser(os.Getenv("dbUser")),
	pgdriver.WithPassword(os.Getenv("dbUserPass")),
	pgdriver.WithDatabase(os.Getenv("dbName")),
	pgdriver.WithInsecure(true),
))

var db = bun.NewDB(sqldb, pgdialect.New())

func init() {
	sqldb.SetMaxOpenConns(40)
	sqldb.SetMaxIdleConns(40)
}

func Save(id string, srcUrl string) bool {
	ctx := context.Background()
	shortenedUrl := &ShortenedUrl{
		Id:           id,
		SrcUrl:       srcUrl,
		Created:      time.Now(),
		LastAccessed: time.Now(),
	}
	_, err := db.NewInsert().Model(shortenedUrl).Exec(ctx)
	if err != nil {
		return false
	}
	return true
}
