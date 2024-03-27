package New;

import java.util.Scanner;

public class Main {
    static int cell_size;
    static int Num_Obs;


    public static void main(String[] args) {
        Scanner Input =new Scanner(System.in);

        // Create a grid
        while (true) {
            System.out.print("Ender the size of Gird between 10 to 15 (width x height ) : ");
            cell_size = Input.nextInt();

            if ((10 <= cell_size) && (cell_size <= 15)) {
                break;
            } else {
                System.out.println("\nEnder the Correct value ");
            }
        }

        Grid grid = new Grid(cell_size);
        grid.settingGrid();

        // Set obstacles
        Obstacle obstacle= new Obstacle();

        switch (cell_size){
            case 10:
                Num_Obs = 15;
                break;

            case 11:
                Num_Obs = 20;
                break;

            case 12:
                Num_Obs = 25;
                break;

            case 13:
                Num_Obs = 30;
                break;

            case 14:
                Num_Obs = 35;
                break;

            case 15:
            Num_Obs = 40;
        }
        obstacle.size_Setting(Num_Obs,cell_size);
        obstacle.Random();
        obstacle.setObstacle(grid.cells);

        // Display the grid
        grid.Display ();

        // Goal destination
        Robot_State state = new Robot_State(grid.cells,cell_size);
        state.Start();
        state.End();
        grid.Display ();



    }
}


