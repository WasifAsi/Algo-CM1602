package New;

public class Grid {

    private int Size;

    public static String[][] cells;

    public Grid(int Size) {
        this.Size = Size;
        cells = new String[Size][Size];
    }

    public void settingGrid() {
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                cells[i][j] = " - ";
            }
        }
    }

    public void Display() {
        System.out.println("\nGrid with obstacles: ");
        for (int k = 1; k <= Size; k++) {
            if ((10 <= k) && (k <= 15)) {
                System.out.print(" " + k + "");
            } else {
                System.out.print(" " + k + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                System.out.print(cells[i][j]);
            }
            System.out.print(" " + (i + 1));
            System.out.println();
        }
    }
}


