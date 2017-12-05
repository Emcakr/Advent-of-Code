import javafx.beans.binding.When;

import java.util.ArrayList;
import java.util.Scanner;

public class Advent17_02_CorruptionChecksum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        int checksum = 0;
        double checksum2 = 0;
        String input = "3751\t3769\t2769\t2039\t2794\t240\t3579\t1228\t4291\t220\t324\t3960\t211\t1346\t237\t1586\n" +
                "550\t589\t538\t110\t167\t567\t99\t203\t524\t288\t500\t111\t118\t185\t505\t74\n" +
                "2127\t1904\t199\t221\t1201\t250\t1119\t377\t1633\t1801\t2011\t1794\t394\t238\t206\t680\n" +
                "435\t1703\t1385\t1461\t213\t1211\t192\t1553\t1580\t197\t571\t195\t326\t1491\t869\t1282\n" +
                "109\t104\t3033\t120\t652\t2752\t1822\t2518\t1289\t1053\t1397\t951\t3015\t3016\t125\t1782\n" +
                "2025\t1920\t1891\t99\t1057\t1909\t2237\t106\t97\t920\t603\t1841\t2150\t1980\t1970\t88\n" +
                "1870\t170\t167\t176\t306\t1909\t1825\t1709\t168\t1400\t359\t817\t1678\t1718\t1594\t1552\n" +
                "98\t81\t216\t677\t572\t295\t38\t574\t403\t74\t91\t534\t662\t588\t511\t51\n" +
                "453\t1153\t666\t695\t63\t69\t68\t58\t524\t1088\t75\t1117\t1192\t1232\t1046\t443\n" +
                "3893\t441\t1825\t3730\t3660\t115\t4503\t4105\t3495\t4092\t48\t3852\t132\t156\t150\t4229\n" +
                "867\t44\t571\t40\t884\t922\t418\t328\t901\t845\t42\t860\t932\t53\t432\t569\n" +
                "905\t717\t162\t4536\t4219\t179\t990\t374\t4409\t4821\t393\t4181\t4054\t4958\t186\t193\n" +
                "2610\t2936\t218\t2552\t3281\t761\t204\t3433\t3699\t2727\t3065\t3624\t193\t926\t1866\t236\n" +
                "2602\t216\t495\t3733\t183\t4688\t2893\t4042\t3066\t3810\t189\t4392\t3900\t4321\t2814\t159\n" +
                "166\t136\t80\t185\t135\t78\t177\t123\t82\t150\t121\t145\t115\t63\t68\t24\n" +
                "214\t221\t265\t766\t959\t1038\t226\t1188\t1122\t117\t458\t1105\t1285\t1017\t274\t281";


         String[] lines = input.split("\n");

         String[][] numbers = new String[lines.length][];


        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = lines[i].split("\t");
        }


        for (int i = 0; i < lines.length; i++) {
            checksum+=Method(lines[i]);
            checksum2+=Method2(numbers[i]);
        }

        System.out.println(checksum);
        System.out.println(checksum2);

    }

    private static double Method(String puzzle) {
        String[] text = puzzle.split("\t");
        int high = Integer.parseInt(text[0]);
        int  low = Integer.parseInt(text[0]);

        for (int i = 1; i < text.length; i++) {
            int check = Integer.parseInt(text[i]);
            if (check<low){
                low=check;
            }else if (check>high){
                high=check;
            }
        }

        return high-low;

    }

    private static double Method2(String[] puzzle) {
        int a=0, b=1;

        int numbers[] = new int[puzzle.length];
        for (int i = 0; i < puzzle.length; i++) {
            numbers[i]=Integer.parseInt(puzzle[i]);
        }

        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle.length; j++) {
                if (numbers[i] == numbers[j]){
                    continue;
                }
                if (numbers[i]%numbers[j]==0){
                    a=numbers[i];
                    b=numbers[j];
                    break;
                }
            }
        }

        return a/b;

    }
}
