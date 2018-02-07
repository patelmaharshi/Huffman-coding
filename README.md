# Huffman
Simple huffman code implementation in java without using built-in data structure classes.

## Huffman Algorithm

Huffman invented a simple algorithm for constructing such trees given the set of characters and their frequencies. The corresponding code is called the Huffman code, and is shown to be the optimal prefix code. Let us assume that we have a set C of n characters and that each character c in C has the frequency f[c].
The algorithm constructs the tree in a bottom-up way. It starts by creating a single-node tree for each character in C. At each step, it takes two trees with the smallest frequencies and merges them by making them children of a new root. This root is then labeled with the sum of the frequencies of its two children. This process is repeated |C|-1 times, after which we are left with only a single tree.
A priority queue Q, keyed on the frequencies f, is used to identify the two least-frequent trees at each step.
```
Huffman(C)
  n = |C|
  Q = C
  for i = 1 to n - 1
    z = new Node()
    z.left = Q.extractMin()
    z.right = Q.extractMin()
    z.f = z.left.f + z.right.f
    Q.insert(z)
done
```
## Program Description
This program Encodes(compresses) a file using Huffman encoding. It implements the following data structures:
* A TreeNode class to represent a node in the Huffman tree.
* A sorted array to store elements of TreeNode class with the frequency of TreeNode as the key.

The program workes as following:

1. Read input file and calculate the count of all the characters assuming that the file only contains characters from `a' to `z' in lowercase, replace all other characters with #.
2. Create a TreeNode corresponding to each character and add it to sorted array.
3. Create the Huffman tree using the algorithm described above.
4. For each leaf in the resulting tree (corresponding to a character), calculate its codeword as described above, as a String of 0s and 1s.
5. Calculate the percentage compression that Huffman encoding achieves over the original file.

The program takes a single argument, which is the name of the input file, or two arguments which are name of input and output files.

The program output:

1. The Huffman code of each character as a sequence of 0s and 1s.
2. Output file contains encoded data.
3. Percentage compression achieved by this code.

Use input.txt file to test the program.

## Compile Program:
``` javac MainClass.java Huffman.java TreeNode.java ```
## Run Program:
``` java huffman.MainClass [inputfile] [outputfile] ``` 
