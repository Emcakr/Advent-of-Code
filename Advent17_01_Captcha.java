import java.util.Scanner;

public class Advent17_01_Captcha {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(Captcha(input));
        System.out.println(Captcha2(input));
    }


    public static int Captcha(String puzzle){
        int sum=0;
        for (int i = 0; i < puzzle.length(); i++) {
            if (puzzle.charAt(i)==puzzle.charAt((i+1)%puzzle.length())){
                if (puzzle.charAt(i)<'0'||puzzle.charAt(i)>'9') {
                    return -1;
                }
                sum+=puzzle.charAt(i)-'0';
            }
        }
        return sum;
    }
    public static int Captcha2(String puzzle){
        int sum=0;
        int space = puzzle.length()/2;
        for (int i = 0; i < puzzle.length(); i++) {
            if (puzzle.charAt(i)==puzzle.charAt((i+space)%puzzle.length())){
                if (puzzle.charAt(i)<'0'||puzzle.charAt(i)>'9') {
                    return -1;
                }
                sum+=puzzle.charAt(i)-'0';
            }
        }
        return sum;
    }
}
