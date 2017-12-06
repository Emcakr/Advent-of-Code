import java.util.ArrayList;
import java.util.Arrays;

public class Advent17_06_MemoryReallocation {
    public static void main(String[] args) {
        String input = "4\t1\t15\t12\t0\t9\t9\t5\t5\t8\t7\t3\t14\t5\t12\t3";

        String[] separated = input.split("\t");
        int[] array = new int[separated.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(separated[i]);
        }

        ArrayList<int[]> stateChecker = new ArrayList<>();
        stateChecker.add(array);

        boolean exit=false;
        int i = 0;
        int j = 0;

        while (!exit){

            int[] newArray = reallocate(stateChecker.get(stateChecker.size()-1));
            stateChecker.add(newArray);
            i++;
            for (j = 0; j < stateChecker.size()-2 ; j++) {
                if (Arrays.equals(stateChecker.get(i),stateChecker.get(j))) {
                    exit = true;
                    break;
                }
            }
        }
        System.out.printf("the pattern repeats after %d cycles\n",i);
        System.out.printf("the infinite loop is %d cycles long\n",i-j);
    }

    public static int[] reallocate(int[] oldArray){
        int[] newArray = new int[oldArray.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i]=oldArray[i];
        }

        int startPosition = highestNumberInArray(newArray);
        int blocks = newArray[startPosition];
        newArray[startPosition] = 0;
        int i = 0;

        while (blocks>0){
            i++;
            blocks--;
            newArray[(startPosition+i)%newArray.length]++;
        }

        return newArray;
    }

    public static int highestNumberInArray(int[] array){
        int highest = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[highest]) {
                highest = i;

            }
        }
        return highest;
    }
}
