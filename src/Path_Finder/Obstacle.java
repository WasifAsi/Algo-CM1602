package Path_Finder;

import java.util.Random;

public class Obstacle {
    private  int [] colum;
    private  int [] Rows ;
    private int ArrSize;
    int Size;

    public void size_Setting(int OB_Size,int size){
        ArrSize = OB_Size;
        this.Size=size;
        colum=new int[ArrSize];
        Rows = new int[ArrSize];
    }


    // Obstacle generating
    public void Random(){
        Random random = new Random();

        for (int i=0; i<ArrSize; i++ ){
            int Rm_C= random.nextInt(0,Size);
            colum[i] = Rm_C;
        }

        for (int j=0; j<ArrSize; j++ ){
            int Rm_R= random.nextInt(0,Size);
            Rows[j] = Rm_R;
        }
    }

    // PUTTING OBSTACLE TO THE GRID
    public void setObstacle(String[][] cells) {
        for (int a =0 ; a< ArrSize ; a++){
            cells[Rows[a]][colum[a]] = " x ";
        }
    }
}
