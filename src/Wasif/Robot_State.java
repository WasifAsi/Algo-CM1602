package Wasif;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Robot_State {
    private String cells [][];
    private int cell_size;
    int x;
    int y;
    int a;
    int b;

    public Robot_State(String cells[][],int cell_size){
        this.cells = cells;
        this.cell_size =cell_size ;
    }
    Scanner Input =new Scanner(System.in);

    public  void Start() {

        System.out.println("\nStarting Point : ");

        while (true) {
            try {
                System.out.print("Ender the Row: ");
                x = Input.nextInt();

                if ((0 < x) && (x <= cell_size)) {
                    break;
                } else {
                    System.out.println("Ender the correct Row\n");
                }
            }catch (InputMismatchException e) {
                System.out.println("Don't Enter Alphabet\n");
                Input.nextLine();
            }
        }

        while (true) {
            try{
                System.out.print("Ender the Colum : ");
                y = Input.nextInt();
                if ((0 < y) && (y <= cell_size)) {
                    break;
                } else {
                    System.out.println("\nEnder the correct Colum\n");
                }
            }catch(InputMismatchException e) {
                System.out.println("Don't Enter Alphabet\n");
                Input.nextLine();
            }
        }
        Start_setting();
    }

    public  void End() {

        System.out.println("\nEnding  Point : ");

        while (true) {
            try{
                System.out.print("Ender the Row : ");
                a = Input.nextInt();
                if ((0 < a) && (a <= cell_size)) {
                    break;
                } else {
                    System.out.println("\nEnder the correct Row\n");
                }
            }catch(InputMismatchException e) {
                System.out.println("Don't Enter Alphabet\n");
                Input.nextLine();
            }
        }

        while (true) {
            try{
                System.out.print("Ender the Colum : ");
                b = Input.nextInt();
                if ((0 < b) && (b <= cell_size)) {
                    break;
                } else {
                    System.out.println("\nEnder the correct Colum\n");
                }
            }catch (InputMismatchException e) {
                System.out.println("Don't Enter Alphabet\n");
                Input.nextLine();
            }
        }
        End_setting();
    }

    public void Start_setting(){
        // Cell Setting
        if (cells[x-1][y-1] == " x "){
            System.out.println("\nThere was a Obstacle in that points");
            Start();
        }else {
            cells[x-1][y-1] = " S ";
            System.out.print("Row : "+x );
            System.out.print(" , Colum "+y);
        }
        System.out.println();
    }

        // Cell Setting
     public void End_setting(){
            // Cell Setting
            if (cells[a-1][b-1] == " x "){
                System.out.println("\nThere was a Obstacle in that points");
                End();
            }else if(cells[a-1][b-1] == " S "){
                System.out.println("\nThere was a Start in that points");
                End();

            }else{
                cells[a-1][b-1] = " E ";
                System.out.print("Row : "+a );
                System.out.print(" , Colum "+b);
            }
         System.out.println();
     }
}
