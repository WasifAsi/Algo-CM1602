package New;

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

    public void Random(){
        Random random = new Random();

        for (int i=0; i<ArrSize; i++ ){
            int Rm_C= random.nextInt(1,Size);
            colum[i] = Rm_C;
        }

        for (int j=0; j<ArrSize; j++ ){
            int Rm_R= random.nextInt(1,Size);
            Rows[j] = Rm_R;
        }
    }

    public void setObstacle(String cells[][] ) {
        for (int a =0 ; a< ArrSize ; a++){                  // PUTTING OBSTACLE TO THE GRID
            cells[Rows[a]][colum[a]] = " X ";
        }
    }
}
