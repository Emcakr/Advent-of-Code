public class Advent16_01_Taxi {
    public static void main(String[] args) {
        System.out.println(Taxi("L3, R2, L5, R1, L1, L2, L2, R1, R5, R1, L1, L2, R2, R4, L4, L3, L3, R5, L1, R3, L5, L2, R4, L5, R4, R2, L2, L1, R1, L3, L3, R2, R1, L4, L1, L1, R4, R5, R1, L2, L1, R188, R4, L3, R54, L4, R4, R74, R2, L4, R185, R1, R3, R5, L2, L3, R1, L1, L3, R3, R2, L3, L4, R1, L3, L5, L2, R2, L1, R2, R1, L4, R5, R4, L5, L5, L4, R5, R4, L5, L3, R4, R1, L5, L4, L3, R5, L5, L2, L4, R4, R4, R2, L1, L3, L2, R5, R4, L5, R1, R2, R5, L2, R4, R5, L2, L3, R3, L4, R3, L2, R1, R4, L5, R1, L5, L3, R4, L2, L2, L5, L5, R5, R2, L5, R1, L3, L2, L2, R3, L3, L4, R2, R3, L1, R2, L5, L3, R4, L4, R4, R3, L3, R1, L3, R5, L5, R1, R5, R3, L1"));
        System.out.println(Taxi2("L3, R2, L5, R1, L1, L2, L2, R1, R5, R1, L1, L2, R2, R4, L4, L3, L3, R5, L1, R3, L5, L2, R4, L5, R4, R2, L2, L1, R1, L3, L3, R2, R1, L4, L1, L1, R4, R5, R1, L2, L1, R188, R4, L3, R54, L4, R4, R74, R2, L4, R185, R1, R3, R5, L2, L3, R1, L1, L3, R3, R2, L3, L4, R1, L3, L5, L2, R2, L1, R2, R1, L4, R5, R4, L5, L5, L4, R5, R4, L5, L3, R4, R1, L5, L4, L3, R5, L5, L2, L4, R4, R4, R2, L1, L3, L2, R5, R4, L5, R1, R2, R5, L2, R4, R5, L2, L3, R3, L4, R3, L2, R1, R4, L5, R1, L5, L3, R4, L2, L2, L5, L5, R5, R2, L5, R1, L3, L2, L2, R3, L3, L4, R2, R3, L1, R2, L5, L3, R4, L4, R4, R3, L3, R1, L3, R5, L5, R1, R5, R3, L1"));
        System.out.println(Taxi("R8, R4, R4, R8"));
        System.out.println(Taxi2("R8, R4, R4, R8"));
    }


    public static int Taxi(String directions){
        String[] instuctions = directions.toUpperCase().split(", ");
        char face = 'N';
        char[]  compass = {'N','E','S','W'};
        int northSouth=0;
        int eastWest=0;

        for (String action: instuctions){
            for (int i=0; i<4; i++){
                if (face==compass[i]){
                    if (action.charAt(0)=='R'){
                        face=compass[(i+1)%4];
                        break;
                    }else{
                        face=compass[(i+3)%4];
                        break;
                    }
                }
            }
            int steps = Integer.parseInt(action.substring(1));
            switch (face){
                case 'N':
                    northSouth+=steps;
                    break;
                case 'E':
                    eastWest+=steps;
                    break;
                case 'S':
                    northSouth-=steps;
                    break;
                case 'W':
                    eastWest-=steps;
                    break;

            }
        }
        return exitTaxi(eastWest,northSouth);
    }


    public static int Taxi2(String directions){
        String[] instuctions = directions.toUpperCase().split(", ");
        char face = 'N';
        char[]  compass = {'N','E','S','W'};
        int northSouth=0;
        int eastWest=0;

        int sumNS=1;
        int sumEW=1;


        for (int i = 0; i < instuctions.length; i++) {
            int steps = Integer.parseInt(instuctions[i].substring(1));
            if (i%2==0){
                sumEW+=steps;
            }else {
                sumNS+=steps;
            }
        }

        boolean[][][] history = new boolean[4][sumNS][sumEW];

        for (String action: instuctions){
            for (int i=0; i<4; i++){
                if (face==compass[i]){
                    if (action.charAt(0)=='R'){
                        face=compass[(i+1)%4];
                        break;
                    }else{
                        face=compass[(i+3)%4];
                        break;
                    }
                }
            }
            int steps = Integer.parseInt(action.substring(1));
            switch (face) {
                case 'N':
                    for (int i = 0; i < steps; i++) {
                        northSouth++;
                        if (beenHere(northSouth,eastWest,history)){
                            return exitTaxi(northSouth,eastWest);
                        }
                    }
                    break;
                case 'E':
                    for (int i = 0; i < steps; i++) {
                        eastWest++;
                        if (beenHere(northSouth,eastWest,history)){
                            return exitTaxi(northSouth,eastWest);
                        }
                    }
                    break;
                case 'S':
                    for (int i = 0; i < steps; i++) {
                        northSouth--;
                        if (beenHere(northSouth,eastWest,history)){
                            return exitTaxi(northSouth,eastWest);
                        }
                    }
                    break;
                case 'W':
                    for (int i = 0; i < steps; i++) {
                        eastWest --;
                        if (beenHere(northSouth,eastWest,history)){
                            return exitTaxi(northSouth,eastWest);
                        }
                    }
                    break;

            }
        }
        return exitTaxi(northSouth,eastWest);
    }

    public static int exitTaxi(int northSouth,int eastWest){
        if (northSouth<0){
            northSouth*=-1;
        }
        if (eastWest<0){
            eastWest*=-1;
        }
        return northSouth+eastWest;
    }
    public static boolean beenHere(int northSouth, int eastWest, boolean[][][] position){
        boolean check;
        if (northSouth>=0&&eastWest>=0){
            check = position[0][northSouth][eastWest];
            position[0][northSouth][eastWest]=true;
            return check;
        } else if (northSouth>=0){
            check = position[1][northSouth][-eastWest];
            position[1][northSouth][-eastWest]=true;
            return check;
        }else if (eastWest>=0){
            check = position[2][-northSouth][eastWest];
            position[2][-northSouth][eastWest]=true;
            return check;
        }else {
            check = position[3][-northSouth][-eastWest];
            position[3][-northSouth][-eastWest]=true;
            return check;
        }
    }
}
