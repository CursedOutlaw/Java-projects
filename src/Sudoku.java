import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Sudoku {
    int[][] solvedSudoku = new int[9][9];
    int[][] unsolvedSudoku = new int[9][9];

    int zeroSpaces = 0;

    //Initializing sudoku into int array.
    void setSudoku() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the sudoku to be solved :");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solvedSudoku[i][j] = input.nextInt();
                if (solvedSudoku[i][j] == 0)
                    this.zeroSpaces++;
            }
        }
    }

    //Returns sudoku
    int[][] getSolvedSudoku() {
        return solvedSudoku;
    }

    //Main code for solving the sudoku.
    boolean solve() {

        // This code gets row and column for any empty slot in the grid.
        int row = 9, column = 9;
        boolean isEmpty = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (solvedSudoku[i][j] == 0) {
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

        //Returns to main code if all sudoku positions are already filled.
        if(!isEmpty)
            return true;

        for (int i = 1; i <= 9; i++) {
            if (checkForNumber(row, column, i)) {
                solvedSudoku[row][column] = i;
                if (solve())
                    return true;
                solvedSudoku[row][column] = 0;
            }
        }
        return false;
    }

    //Code for checking for constraints for number in row, column & subpart of sudoku.
    private boolean checkForNumber(int row, int column, int number) {

        //Checks for number if already present in its row
        for (int i = 0; i < 9; i++) {
            if (solvedSudoku[row][i] == number) {
                return false;
            }
        }

        //Check for number if already present in its column.
        for (int i = 0; i < 9; i++) {
            if (solvedSudoku[i][column] == number) {
                return false;
            }
        }

        //Check for number if already present in the (3*3)box subpart of whole sudoku grid.
        column = column - column % 3;
        row = row - row % 3;
        for (int i = row; i < row + 3; i++) {
            for (int j = column; j < column + 3; j++) {
                if (solvedSudoku[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    //Printing sudoku
    void printSudoku() {
        System.out.println("The sudoku is :");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(solvedSudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Generating a sudoku
    void generateSudoku() {
        int[] randomCell;
        for (int k = 0; k < 3; k++) {
            randomCell = generateCell();
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    solvedSudoku[3*k + i][3*k + j] = randomCell[3 * j + i];
                }
            }
        }
        solve();
    }

    //Generate a (3*3)cell of a sudoku
    int[] generateCell() {
        int[] cell = new int[9];
        Random random = new Random();
        int[] initial = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        ArrayList<Integer> number = new ArrayList<>();
        for (int i = 0; i < 9; i++)
            number.add(initial[i]);

        for (int i = 0; i < 9; i++) {
            int randomValue = random.nextInt(number.size());
            int numberRemoved = number.remove(randomValue);
            cell[i] = numberRemoved;
        }
        return cell;
    }
}
