import java.util.Scanner;

class Sudoku {
    int[][] sudoku = new int[9][9];
    int zeroSpaces = 0;

    void setSudoku() {
        Scanner input = new Scanner(System.in);
        //Initializing sudoku into int array
        System.out.println("Enter the sudoku to be solved :");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = input.nextInt();
                if (sudoku[i][j] == 0)
                    this.zeroSpaces++;
            }
        }
    }

    int[][] getSudoku() {
        return sudoku;
    }

    boolean solve() {

        int row = 9, column = 9;
        boolean isEmpty = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    row = i;
                    column = j;
                    isEmpty = true;
                    break;
                }
            }
            if (isEmpty) {
                break;
            }
        }

        if(!isEmpty)
            return true;

        for (int i = 1; i <= 9; i++) {
            if (checkForNumber(row, column, i)) {
                sudoku[row][column] = i;
                if (solve())
                    return true;
                sudoku[row][column] = 0;
            }
        }
        return false;
    }

    private boolean checkForNumber(int row, int column, int number) {

        //Check for row if number already present in it
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == number) {
                return false;
            }
        }

        //Check for column if number already present in it
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][column] == number) {
                return false;
            }
        }

        //Check for box if number already present in it
        column = column - column % 3;
        row = row - row % 3;
        for (int i = row; i < row + 3; i++) {
            for (int j = column; j < column + 3; j++) {
                if (sudoku[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    void printSudoku() {
        //Printing sudoku
        System.out.println("The sudoku is :");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
}
