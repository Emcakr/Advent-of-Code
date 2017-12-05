public class Advent17_03_TaxiMemory {
    public static void main(String[] args) {
        int number = 277678;
        System.out.println(Advent16_01_Taxi.exitTaxi(taxiNorthSouthSpiral(number),taxiEastWestSpiral(number)));

        System.out.println(secondTry(number));
    }


    public static int secondTry(int goal){
        int size = (int)Math.sqrt(goal)+10;

        int[][] grid = new int[size*2][size*2];

        int x = 0;
        int y = 0;

        boolean xUp=true;
        boolean yUp=true;
        boolean horisontal=true;

        int stepsWalked=0;
        int stepsToWalk=1;

        int nextnumber=1;

        while (nextnumber<goal){
            grid[size+x][size+y]=nextnumber;

            stepsWalked++;
            if(horisontal){
                if (yUp){
                    y++;
                }else{
                    y--;
                }
                if (stepsToWalk==stepsWalked){
                    stepsWalked=0;
                    horisontal=false;
                    yUp=!yUp;
                }
            }else {
                if (xUp){
                    x++;
                }else{
                    x--;
                }
                if(stepsToWalk==stepsWalked){
                    stepsToWalk++;
                    stepsWalked=0;
                    horisontal=true;
                    xUp=!xUp;
                }
            }

            nextnumber=grid[size+x-1][size+y-1]
                    +grid[size+x-1][size+y]
                    +grid[size+x-1][size+y+1]
                    +grid[size+x][size+y-1]
                    +grid[size+x][size+y+1]
                    +grid[size+x+1][size+y-1]
                    +grid[size+x+1][size+y]
                    +grid[size+x+1][size+y+1];

        }



        return nextnumber;
    }












    public static int firstLargerSumOfAdjacent(int higherThan){
        int numb = (int)Math.sqrt((higherThan+1)*2);
        int[][][][] grid = new int[2][2][numb][numb];

        int nextNumber = 1;
        int x = 0;
        int y = 0;

         int[][][] one = grid[0];
         int[][]  two = one[0];
         int[] three;
         int xPosNeg=1;
         int yPosNeg=1;

         boolean xUp=true;
         boolean yUp=true;

        boolean horisontal=true;
         int stepsWalked=0;
         int stepsToWalk=1;

        while (nextNumber<higherThan){

            System.out.println(nextNumber);
            two[x*xPosNeg][y*yPosNeg]=nextNumber;



            //moving

            stepsWalked++;
            if(horisontal){
                if(yUp){
                    y++;
                }else {
                    y--;
                }
                if (stepsToWalk==stepsWalked){
                    stepsWalked=0;
                    horisontal=false;
                    yUp=!yUp;
                }
            }else{
                if(xUp){
                    x++;
                }else {
                    x--;
                }
                if(stepsToWalk==stepsWalked){
                    stepsWalked=0;
                    stepsToWalk++;
                    horisontal=true;
                    xUp=!xUp;
                }
            }


            if (x>=0){
                one=grid[0];
                xPosNeg=1;
            }else{
                one=grid[1];
                xPosNeg=-1;
            }
            if (y>=0){
                two=one[0];
                yPosNeg=1;
            }else{
                two=one[0];
                yPosNeg=-1;
            }


            if (x!=0&&y!=0){
                nextNumber=
                        two[x*xPosNeg-1][y*yPosNeg-1]
                        +two[x*xPosNeg-1][y*yPosNeg]
                        +two[x*xPosNeg-1][y*yPosNeg+1]
                        +two[x*xPosNeg][y*yPosNeg-1]
                        +two[x*xPosNeg][y*yPosNeg+1]
                        +two[x*xPosNeg+1][y*yPosNeg-1]
                        +two[x*xPosNeg+1][y*yPosNeg]
                        +two[x*xPosNeg+1][y*yPosNeg+1];
            }else if (x==0&&y==0){
                nextNumber=1;
            }else if(x==0){
                nextNumber=
                        grid[1][0][1][y*yPosNeg-1]
                        +grid[1][0][1][y*yPosNeg]
                        +grid[1][0][1][y*yPosNeg+1]
                        +two[x*xPosNeg][y*yPosNeg-1]
                        +two[x*xPosNeg][y*yPosNeg+1]
                        +two[x*xPosNeg+1][y*yPosNeg-1]
                        +two[x*xPosNeg+1][y*yPosNeg]
                        +two[x*xPosNeg+1][y*yPosNeg+1];
            }else{
                nextNumber=
                        grid[0][1][x*xPosNeg-1][1]
                        +grid[0][1][x*xPosNeg][1]
                        +grid[0][1][x*xPosNeg+1][1]
                        +two[x*xPosNeg-1][y*yPosNeg]
                        +two[x*xPosNeg+1][y*yPosNeg]
                        +two[x*xPosNeg-1][y*yPosNeg+1]
                        +two[x*xPosNeg][y*yPosNeg+1]
                        +two[x*xPosNeg+1][y*yPosNeg+1];
            }

        }


        return nextNumber;


    }


    public static int taxiNorthSouthSpiral(int stepsToGo){
        boolean countUp = true;
        boolean vertical = false;

        int stepsSinceLastTurn = 0;
        int stepsPerTurn=1;

        int altitude=0;

        for (int i = 1; i < stepsToGo; i++) {
            stepsSinceLastTurn++;
            if (vertical){
                if(countUp){
                    altitude++;
                }else{
                    altitude--;
                }
                if (stepsSinceLastTurn==stepsPerTurn){
                    stepsSinceLastTurn=0;
                    vertical=false;
                    stepsPerTurn++;
                    countUp=!countUp;
                }
            }else{
                if (stepsSinceLastTurn==stepsPerTurn) {
                    stepsSinceLastTurn = 0;
                    vertical = true;
                }
            }
        }

        return altitude;
    }
    public static int taxiEastWestSpiral(int stepsToGo){
        boolean countUp = true;
        boolean horizontal = true;

        int stepsSinceLastTurn = 0;
        int stepsPerTurn=1;

        int longitude=0;

        for (int i = 1; i < stepsToGo; i++) {
            stepsSinceLastTurn++;
            if (horizontal){
                if(countUp){
                    longitude++;
                }else{
                    longitude--;
                }
                if (stepsSinceLastTurn==stepsPerTurn){
                    stepsSinceLastTurn=0;
                    horizontal=false;

                    countUp=!countUp;
                }
            }else{
                if (stepsSinceLastTurn==stepsPerTurn) {
                    stepsSinceLastTurn = 0;
                    stepsPerTurn++;
                    horizontal = true;
                }
            }
        }

        return longitude;
    }
}
