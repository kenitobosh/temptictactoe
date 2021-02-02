package com.example;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Takes in and evaluates a string representing a tic tac toe board.
 */
public class TicTacToeBoard {


  char[][] testBoard;
  String originalBoard;
  char lastEntry;

  /**
   * This method should load a string into your TicTacToeBoard class.
   * @param board The string representing the board
   */
  public TicTacToeBoard(String board) {
    if (board.length() < 9) {
      throw new IllegalArgumentException();
    }

    board = cleanBoard(board);

    testBoard = new char[][]{
            {board.charAt(0),board.charAt(1),board.charAt(2)},
            {board.charAt(3),board.charAt(4),board.charAt(5)},
            {board.charAt(6),board.charAt(7),board.charAt(8)}
    };

    originalBoard = board;
  }


  char isWinner = 'N';

  /**
   * Checks the state of the board (unreachable, no winner, X wins, or O wins)
   * @return an enum value corresponding to the board evaluation
   */
  public Evaluation evaluate() {

    int numberOfX = countNumberOfAppearance('X');
    int numberOfO = countNumberOfAppearance('O');

    if (numberOfX != numberOfO + 1 && numberOfX != numberOfO) {
      return Evaluation.UnreachableState;
    }

    /* Checks to see if there has been any winners */
    checkWinnerByRow();
    checkWinnerByColumn();
    checkWinnerByDiagonal();

    if (isWinner == 'X') {
      return Evaluation.Xwins;
    } else if (isWinner == 'O') {
      return Evaluation.Owins;
    }

    return Evaluation.NoWinner;
  }

  /**
   * Evaluates if there is any winners that is won by filling a row.
   */
  public void checkWinnerByRow() {
    for (char[] chars : testBoard) {
      if (chars[0] == chars[1] && chars[0] == chars[2]) {
        if (chars[0] == 'X') {
          isWinner = 'X';
        } else if (chars[0] == 'O') {
          isWinner = 'O';
        }
      }
    }
  }

  /**
   * Evaluates if there is any winners that is won by filling a column.
   */
  public void checkWinnerByColumn() {
    if (testBoard[0][0] == testBoard[1][0] && testBoard[0][0] == testBoard[2][0]) {
      System.out.println(testBoard[0][0] == 'X' || testBoard[0][0] == 'O');
      if (testBoard[0][0] == 'X' || testBoard[0][0] == 'O') {
        System.out.println("B1");
        isWinner = testBoard[1][0];
      }
    }

    if (testBoard[0][1] == testBoard[1][1] && testBoard[0][1] == testBoard[2][1]) {
      System.out.println(testBoard[0][1] == 'X' || testBoard[0][1] == 'O');
      if (testBoard[0][1] == 'X' || testBoard[0][1] == 'O') {
        System.out.println("B2");
        isWinner = testBoard[0][1];
      }
    }

    if (testBoard[0][2] == testBoard[1][2] && testBoard[0][2] == testBoard[2][2]) {
      System.out.println(testBoard[0][2] == 'X' || testBoard[0][2] == 'O');
      if (testBoard[0][2] == 'X' || testBoard[0][2] == 'O') {
        System.out.println("B3");
        isWinner = testBoard[0][2];
      }
    }
  }

  /**
   * Evaluates if there is any winners that is won by filling a diagonal.
   */
  public void checkWinnerByDiagonal() {
    if (testBoard[0][2] == testBoard[1][1] && testBoard[0][2] == testBoard[2][0]) {
      isWinner = testBoard[0][2];
    }
    System.out.println(testBoard[0][0] == testBoard[1][1] && testBoard[0][0] == testBoard[2][2]);
    if (testBoard[0][0] == testBoard[1][1] && testBoard[0][0] == testBoard[2][2]) {
      isWinner = testBoard[0][0];
    }
  }

  /**
   * Checks to see if there is an unreachable amount of turns or order of turns played.
   * @param character
   * @return
   */
  public int countNumberOfAppearance(char character) {
    int count = 0;

    for (int i = 0; i < originalBoard.length(); i++) {
      if (originalBoard.charAt(i) == character) {
        count++;
      }
    }

    return count;
  }

  /**
   * Replace all random and different types of Characters to only 'X', 'O', or ' '.
   * @param board
   * @return board
   */
  public String cleanBoard(String board) {
    for (int i = 0; i < board.length(); i++) {
      char toChange = board.charAt(i);

      if (Character.toLowerCase(toChange) == 'x') {
        board = board.replace(toChange, 'X');
      } else if (Character.toLowerCase(toChange) == 'o') {
        board = board.replace(toChange, 'O');
      } else {
        board = board.replace(toChange, ' ');
      }
    }
    return board;
  }


}
