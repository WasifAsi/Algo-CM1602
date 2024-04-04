package Path_Finder;

public class Grid {

    int Size;

    public static String[][] cells;

    public Grid(int Size) {
        this.Size = Size;
        cells = new String[Size][Size];
    }

    // Grid setup
    public void settingGrid() {
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                cells[i][j] = " - ";
            }
        }
    }
    // printing the Grid
    public void Display() {


        // column integrator printing with suitable space
        for (int k = 1; k <= Size; k++) {
            if ((10 <= k) && (k <= 15)) {
                System.out.print(" " + k + "");
            } else {
                System.out.print(" " + k + " ");
            }
        }
        System.out.println();

        // Printing Row integrator with 2D array
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                System.out.print(cells[i][j]);
            }
            System.out.print(" " + (i + 1));
            System.out.println();
        }
    }
}


