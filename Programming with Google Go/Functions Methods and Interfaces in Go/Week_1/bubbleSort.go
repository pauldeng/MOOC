package main

/*
Write a Bubble Sort program in Go. The program should prompt the user to type in a sequence of up to 10 integers. The program should print the integers out on one line, in sorted order, from least to greatest. Use your favorite search tool to find a description of how the bubble sort algorithm works.

As part of this program, you should write a function called BubbleSort() which takes a slice of integers as an argument and returns nothing. The BubbleSort() function should modify the slice so that the elements are in sorted order.

A recurring operation in the bubble sort algorithm is the Swap operation which swaps the position of two adjacent elements in the slice. You should write a Swap() function which performs this operation. Your Swap() function should take two arguments, a slice of integers and an index value i which indicates a position in the slice. The Swap() function should return nothing, but it should swap the contents of the slice in position i with the contents in position i+1.
*/

import (
	"fmt"
	"os"
	"strconv"
	"strings"
)

func Swap(inSli []int, i int) {
	inSli[i+1], inSli[i] = inSli[i], inSli[i+1]
}

func BubbleSort(tosort []int) {
	size := len(tosort)
	if size < 2 {
		return
	}
	for i := 0; i < size; i++ {
		for j := size - 1; j >= i+1; j-- {
			if tosort[j] < tosort[j-1] {
				Swap(tosort, j-1)
			}
		}
	}
}

func main() {
	var inputString string
	fmt.Print("Please enter up to 10 comma separated integers: ")
	fmt.Scan(&inputString)

	nameSli := strings.Split(inputString, ",")
	if len(nameSli) > 10 || len(nameSli) < 2 {
		fmt.Println("Cannot read input numbers. Please try again for example: 1,3,2")
		os.Exit(1)
	}

	intSli := make([]int, 0, 10)
	for _, strValue := range nameSli {
		intValue, err := strconv.Atoi(strValue)
		if err != nil {
			fmt.Printf("%s is not a integer\n", strValue)
			os.Exit(1)
		}
		intSli = append(intSli, intValue)
	}

	BubbleSort(intSli)
	fmt.Printf("Sorted integers are: %v", intSli)
}
