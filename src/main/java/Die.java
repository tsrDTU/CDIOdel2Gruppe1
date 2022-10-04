import java.util.concurrent.ThreadLocalRandom;

public class Die {
    private int faceValue = 0;


    public Die() {
        faceValue = (int)(Math.random() * 6) + 1;

    }

    public Die(int upperBound) {
        faceValue = (int)(Math.random() * upperBound) + 1;

    }
    public void Die_test (int lowerBound, int upperBound) {
        int temp =(int)(Math.random() * upperBound-lowerBound) + 1;
        faceValue = temp+lowerBound;
 //       System.out.println(temp + lowerBound);
 //       faceValue = upperBound;
    }

    public void die_normal ()
    {
        faceValue = (int)(Math.random() * 6) + 1;
    }


    public int getFaceValue(){
        return faceValue;
    }






}
