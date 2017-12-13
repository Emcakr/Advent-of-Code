public class PacketScanner {
    private int [][] layers;

    PacketScanner(String input){

        input = input.replaceAll(":","");
        String[] lines = input.split("\n");


        layers = new int[lines.length][2];
        for (int i = 0; i < lines.length; i++) {
            String[] numbersInLine = lines[i].split(" ");
            layers[i][0] = Integer.parseInt(numbersInLine[0]);
            layers[i][1] = Integer.parseInt(numbersInLine[1]);
        }

    }




    private int stateWhenReached(int depth,int range){

        int possibleStates = (range-1)*2;

        int state = depth%possibleStates;

        if (state<=range){
            return state;
        }else{
            return 2*range-state;
        }

    }

    private boolean isCaught(int delay){


        for (int i = 0; i < layers.length; i++) {
            if (stateWhenReached(layers[i][0]+delay,layers[i][1])==0){
                return true;
            }
        }

        return false;
    }

    int findOpening(){
        int i = 0;
        while (isCaught(i)){
            i++;
        }

        return i;
    }

    int severity(){
        int severity=0;
        for (int i = 0; i < layers.length; i++) {
            int depth = layers[i][0];
            int range = layers[i][1];

            if (stateWhenReached(depth,range)==0){
                severity+=depth*range;
            }
        }

        return severity;

    }

    public static void main(String[] args) {
        PacketScanner scanner = new PacketScanner("0: 4\n" +
                "1: 2\n" +
                "2: 3\n" +
                "4: 4\n" +
                "6: 6\n" +
                "8: 5\n" +
                "10: 6\n" +
                "12: 6\n" +
                "14: 6\n" +
                "16: 8\n" +
                "18: 8\n" +
                "20: 9\n" +
                "22: 12\n" +
                "24: 8\n" +
                "26: 8\n" +
                "28: 8\n" +
                "30: 12\n" +
                "32: 12\n" +
                "34: 8\n" +
                "36: 12\n" +
                "38: 10\n" +
                "40: 12\n" +
                "42: 12\n" +
                "44: 10\n" +
                "46: 12\n" +
                "48: 14\n" +
                "50: 12\n" +
                "52: 14\n" +
                "54: 14\n" +
                "56: 12\n" +
                "58: 14\n" +
                "60: 12\n" +
                "62: 14\n" +
                "64: 18\n" +
                "66: 14\n" +
                "68: 14\n" +
                "72: 14\n" +
                "76: 14\n" +
                "82: 14\n" +
                "86: 14\n" +
                "88: 18\n" +
                "90: 14\n" +
                "92: 17");


        System.out.println(scanner.severity());
        System.out.println(scanner.findOpening());
    }
}
