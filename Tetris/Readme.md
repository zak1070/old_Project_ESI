# Tetris

## Description
This is an implementation of the Tetris game in C++ language. It adheres to all the required functionalities. Additionally, just like the original Tetris game, it provides the option to display the next brick. A graphical version is available for you to play.

## Known Bug

The sole known bug occurs in the grid display during gameplay. Specifically, the display of the grid borders inconsistently grows and shrinks as the game progresses, without any apparent reason.

## Warning

The  warnings encountered concern some inclusions. The warnings suggest that these inclusions are not necessary, but removing them leads to program malfunctionsxy, we do not know how to solve it.. Additionally, two warnings are known:

- One in `RelatedPositions.cpp` line 135 because there is a comparison between an `int` and a `size_type`.
- Another in `board.cpp` line 81 for the same reason.

The correction for these warnings would then be to use a `static_cast` to ensure a proper comparison between types but it is managed by the compiler, which automatically casts..
