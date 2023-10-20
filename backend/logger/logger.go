package logger

import (
	"fmt"
	"time"
)

func MongoInfo(message string) {
	formattedTime := time.Now().Format("2006/01/02 - 15:04:05")
	fmt.Printf("[MONGO] %s | %s", formattedTime, message)
}
