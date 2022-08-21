import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Sudoku s1 = new Sudoku();
        s1.setSudoku();
        if(s1.solve())
            s1.printSudoku();
        else
            System.out.println("No solution for given Sudoku.");
    }
}
