package main

import (
	"fmt"
	"sort"
	"strconv"
)

func main() {
	sli := make([]int, 0, 3)

	for true {
		fmt.Print("Please enter a integer or 'X' to exit: ")
		var input string
		fmt.Scan(&input)
		if input == "X" {
			fmt.Println("Bye")
			break
		} else {
			inputNumber, err := strconv.Atoi(input)
			if err != nil {
				fmt.Printf("Cannot accept: %s\n", input)
			} else {
				sli = append(sli, inputNumber)
			}
		}
		sort.Ints(sli)
		fmt.Printf("Sorted slice: %v\n", sli)
	}
}
