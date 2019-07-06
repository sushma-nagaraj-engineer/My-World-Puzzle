# My-World-Puzzle

A grid consisting of letters is to be checked against a dictionary of words to see if the grid contains any of the words.The user can input a value for the rows and columns of the grid and the program will create a grid of random characters. The program will read in a dictionary file and use an algorithm to solve the word puzzle.The program checks each word in the grid for presence in the dictionary.
In this algorithm, if a prefix is not found, the rest of this string treated as "not found". For example, if the string is "apbum", and after checking and finding "a" and "ap" I find that "abp" is not in my dictionary, then there is no point in checking further in this direction.
