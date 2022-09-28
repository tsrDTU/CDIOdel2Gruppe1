
public class Test {
    public static void main(String[] args) {
        Die testDie = new Die();
        int[] results = new int[6];
        for (int i = 0; i < 100000; i++) {
            results[testDie.getFaceValue()-1] ++;
            testDie = new Die();
        }
        System.out.println("Results:");
        System.out.println("1:"+results[0]);
        System.out.println("2:"+results[1]);
        System.out.println("3:"+results[2]);
        System.out.println("4:"+results[3]);
        System.out.println("5:"+results[4]);
        System.out.println("6:"+results[5]);
    }
}
