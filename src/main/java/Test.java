
public class Test {
    public static void main(String[] args) {
        final int rolls = 10000;
        final double expectedValue = rolls/6;
        Die testDie = new Die();
        int[] results = new int[6];
        for (int i = 0; i < rolls; i++) {
            results[testDie.getFaceValue()-1] ++;
            testDie.dice_roll();
        }
        System.out.println("Results for "+ rolls + " rolls:");
        System.out.println("Face value 1 appeared times: " +results[0]);
        System.out.printf("Deviation from expected value in pct: %3.2f\n", ((results[0]/expectedValue)*100-100));
        System.out.println("Face value 2 appeared times: " +results[1]);
        System.out.printf("Deviation from expected value in pct: %3.2f\n", ((results[1]/expectedValue)*100-100));
        System.out.println("Face value 3 appeared times: " +results[2]);
        System.out.printf("Deviation from expected value in pct: %3.2f\n", ((results[2]/expectedValue)*100-100));
        System.out.println("Face value 4 appeared times: " +results[3]);
        System.out.printf("Deviation from expected value in pct: %3.2f\n", ((results[3]/expectedValue)*100-100));
        System.out.println("Face value 5 appeared times: " +results[4]);
        System.out.printf("Deviation from expected value in pct: %3.2f\n",((results[4]/expectedValue)*100-100));
        System.out.println("Face value 6 appeared times: " +results[5]);
        System.out.printf("Deviation from expected value in pct: %3.2f\n", ((results[5]/expectedValue)*100-100));






    }
}
