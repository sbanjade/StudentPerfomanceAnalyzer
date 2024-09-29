
import java.io.*;
import java.io.File;
import java.util.Scanner;
/**
 * 
 * @author Sachin Banjade
 */

public class SBProjectTwo {

    static void Student_Avg(String[][] grid, double[] student_avg) {
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 1; j < col; j++) {
                student_avg[i] += Integer.parseInt(grid[i][j]);
            }

            student_avg[i] = (student_avg[i] / (col - 1));
        }
    }

    static void Quiz_Avg(String[][] grid, double[] quiz_avg) {
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 1; i < col; i++) {
            int count = 0;
            for (int j = 0; j < row; j++) {
                if (Integer.parseInt(grid[j][i]) != 0) {
                    quiz_avg[i - 1] += Integer.parseInt(grid[j][i]);
                    count++;
                }
            }
            quiz_avg[i - 1] = quiz_avg[i - 1] / count;
        }
    }

    static double highest_Avg(double[] student_avg, int[] index) {
        double highest = Integer.MIN_VALUE;
        for (int i = 0; i < student_avg.length; i++) {
            if (highest < student_avg[i]) {
                highest = student_avg[i];
                index[0] = i;
            }
        }
        return highest;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the file: ");
        String filename = sc.nextLine();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        int noOfData;
        int noofRecords;
        String text = br.readLine();
        String[] ans = text.split(",");
        noOfData = Integer.parseInt(ans[0]);
        noofRecords = Integer.parseInt(ans[1]);
        String line;

        int i = 0;
        String[][] data = new String[noOfData][noofRecords + 1];
        while ((line = br.readLine()) != null) {
            if (i == noOfData) {
                break;
            }
            String[] record = line.split(",");

            for (int j = 0; j <= noofRecords; j++) {
                data[i][j] = record[j];
            }
            i++;
        }
        double student_avg[] = new double[noOfData];
        double quiz_avg[] = new double[noofRecords];
        int[] index = new int[1];
        Student_Avg(data, student_avg);
        Quiz_Avg(data, quiz_avg);
        double highestAvg = highest_Avg(student_avg, index);

        System.out.print("\t\t");
        for (int j = 0; j < noofRecords; j++) {
            System.out.print("Quiz " + "\t");
        }
        System.out.println("Student");

        System.out.print("Student\t\t");
        for (int j = 0; j < noofRecords; j++) {
            System.out.print(j + 1 + "\t");
        }
        System.out.println("Average");

        System.out.print("---------\t");
        for (int j = 0; j < noofRecords; j++) {
            System.out.print("---\t");
        }
        System.out.println("-------");

        for (int j = 0; j < noOfData; j++) {
            for (int k = 0; k < noofRecords + 1; k++) {
                System.out.print(data[j][k] + "\t");
            }
            System.out.printf("%.1f%n", student_avg[j]);
        }
        System.out.print("\t\t");
        for (int j = 0; j < noofRecords; j++) {
            System.out.print("---\t");
        }
        System.out.println();
        System.out.print("Quiz Average\t");
        for (int j = 0; j < noofRecords; j++) {
            System.out.printf("%.1f ", quiz_avg[j]);
            System.out.print("\t");
        }

        System.out.println();
        System.out.println(data[index[0]][0] + " had the highest overall average of " + String.format("%.1f", highestAvg));

        br.close();

    }
}
