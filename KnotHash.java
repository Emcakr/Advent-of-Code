public class KnotHash {
    private int[] numbers;
    private int[] steps;
    private int skipSize = 0;
    private int position = 0;


    KnotHash(String input){
        int[] extra = {17, 31, 73, 47, 23};
        steps = new int[input.length() + extra.length];

        for (int i = 0; i < steps.length; i++) {
            if (i < input.length()){
                steps[i] = (int)input.charAt(i);
            } else {
                steps[i] = extra[i-input.length()];
            }
        }

        numbers = new int [256];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
    }

    KnotHash(int... input){
        steps = input;

        numbers = new int [256];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
    }

    void runOperations(){
        for (int step : steps) {
            if (step <= numbers.length) {
                twist(step);
            }
            position += step + skipSize;
            skipSize++;
        }
    }

    void runOperations(int times){
        for (int i = 0; i < times; i++) {
            runOperations();
        }
    }

    void twist(int length){
        int[] toTwist = new int[length];
        for (int i = 0; i < length; i++) {
            toTwist[i] = numbers[(position + length - i - 1) % numbers.length];
        }

        for (int i = 0; i < toTwist.length; i++) {
            numbers[(position + i) % numbers.length] = toTwist[i];
        }
    }

    int multiplyFirstAndLast(){
        return numbers[0]*numbers[1];
    }

    int[] getDenseHash(){
        int sixteen = 16;
        int[] denseHash = new int[numbers.length/sixteen];

        for (int i = 0; i < denseHash.length; i++) {
            int value = numbers[i * sixteen];
            for (int j = 1; j < sixteen; j++) {
                value = value ^ numbers[i * sixteen + j];
            }
            denseHash[i] = value;
        }
        return denseHash;
    }

    static void printHex(int[] numbers){
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i]>15) {
                System.out.print(Integer.toHexString(numbers[i]));
            } else {
                System.out.print("0" + Integer.toHexString(numbers[i]));
            }

        }
        System.out.println();
    }

    public static void main(String[] args) {
        KnotHash knitter = new KnotHash(183,0,31,146,254,240,223,150,2,206,161,1,255,232,199,88);
        KnotHash knitter2 = new KnotHash("183,0,31,146,254,240,223,150,2,206,161,1,255,232,199,88");

        knitter.runOperations();
        knitter2.runOperations(64);



        System.out.println(knitter.multiplyFirstAndLast());
        printHex(knitter2.getDenseHash());

        System.out.printf("\nTest cases: \n");

        testPart2("");
        testPart2("AoC 2017");
        testPart2("1,2,3");
        testPart2("1,2,4");
    }

    static void testPart2(String input){
        KnotHash test = new KnotHash(input);

        test.runOperations(64);

        printHex(test.getDenseHash());
    }

}
