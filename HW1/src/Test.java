/**
 * Created by Daltin-Desk on 1/30/2017.
 */
public class Test {
    public static void main(String[] args) {
        int A = 5;
        int B = 1;
        int C = 2;
        for(int i = 0; i < 5; i++) {
            A += 2;
            for(int j = 0; j < 2; j++) {
                B += 1;
                for(int k = 0; k < 3; k++) {
                    C += 3;
                }
            }
        }
        System.out.printf("A=%d\n", A);
        System.out.printf("B=%d\n", B);
        System.out.printf("C=%d\n", C);
    }
}
