package Wasif;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static int cell_size;
    static int Num_Obs;


    public static void main(String[] args) {
        Scanner Input =new Scanner(System.in);

        // Create a grid
        while (true){
            try {
                System.out.print("Enter the size of Gird between 10 to 15 ( width x height ) : ");
                cell_size = Input.nextInt();

                if (cell_size >= 10 && cell_size <=15){
                    break;
                }else {
                    System.out.println("Enter the correct value");
                }
            }catch (InputMismatchException e){
                System.out.println("Don't Enter Alphabet\n");
                Input.nextLine();
            }
        }

        Grid grid = new Grid(cell_size);
        grid.settingGrid();

        // Set obstacles
        Obstacle obstacle= new Obstacle();

        switch (cell_size){
            case 10:
                Num_Obs = 20;
                break;

            case 11:
                Num_Obs = 30;
                break;

            case 12:
                Num_Obs = 40;
                break;

            case 13:
                Num_Obs = 50;
                break;

            case 14:
                Num_Obs =60;
                break;

            case 15:
            Num_Obs = 70;
        }

        obstacle.size_Setting(Num_Obs,cell_size);
        obstacle.Random();
        obstacle.setObstacle(grid.cells);

        // Display the grid
        System.out.println("\nGrid with obstacles  : ");
        grid.Display ();

        // Goal destination
        Robot_State state = new Robot_State(grid.cells,cell_size);
        state.Start();
        state.End();

        // displaying the grid with starting and ending points
        PathFind pathfind = new PathFind(grid.cells,cell_size);
        pathfind.pathImport(state.x-1,state.y-1,state.a-1,state.b-1);
        System.out.println("\nGrid with obstacles, Staring and Ending points : \n");
        grid.Display ();
    }
}


