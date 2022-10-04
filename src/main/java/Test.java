
public class Test {
    public static void main(String[] args) {
        final int rolls = 10000;
        final double expectedValue = rolls/6;
        Die testDie = new Die();
        int[] results = new int[6];
        for (int i = 0; i < rolls; i++) {
            results[testDie.getFaceValue()-1] ++;
            testDie = new Die();
        }
        System.out.println("Results:");
        System.out.println("1: " +results[0]);
        System.out.println("1: " + ((results[0]/expectedValue)*100-100));
        System.out.println("2: " +results[1]);
        System.out.println("2: " +((results[1]/expectedValue)*100-100));
        System.out.println("3: " +results[2]);
        System.out.println("3: " +((results[2]/expectedValue)*100-100));
        System.out.println("4: " +results[3]);
        System.out.println("4: " +((results[3]/expectedValue)*100-100));
        System.out.println("5: " +results[4]);
        System.out.println("5: " +((results[4]/expectedValue)*100-100));
        System.out.println("6: " +results[5]);
        System.out.println("6: " +((results[5]/expectedValue)*100-100));






    }
}
