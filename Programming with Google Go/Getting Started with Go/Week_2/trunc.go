package main

import (
	"errors"
	"fmt"
	"strconv"
)

func main() {
	var enteredNumber float32
	var truncNumber int32
	fmt.Print("Please enter a floating point number: ")

	readSize, err := fmt.Scan(&enteredNumber)
	if err != nil {
		fmt.Println(err.Error())
		return
	} else if readSize < 1 {
		err = errors.New("Cannot read input")
		return
	}

	truncNumber = int32(enteredNumber)
	fmt.Print("Output truncated integer: " + strconv.Itoa(int(truncNumber)))
}
