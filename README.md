# BulgarianSimulator

This is implementing a program to model the game Bulgarian Solitaire. This program will run in the console window.

# How it works?
  - The game starts with 45 cards. (They need not be playing cards. Unmarked index cards work just as well.) Randomly divide them into some number of piles of random size. For example, you might start with piles of size 20, 5, 1, 9, and 10. In each round you take one card from each pile, forming a new pile with these cards. For example, the starting configuration would be transformed into piles of size 19, 4, 8, 9, and 5. The solitaire is over when the piles have size 1, 2, 3, 4, 5, 6, 7, 8, and 9, in some order. (It can be shown that you always end up with such a configuration.

  - In the normal mode of operation your program will produce a random starting configuration and print it. It then keeps applying the solitaire step and printing the results, and stops when the final configuration is reached.

# How to Use?

  - Just clone the files on a single folder.
  - Run the files ,under the same package, on a java IDE
