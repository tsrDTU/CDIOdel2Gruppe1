import java.util.concurrent.ThreadLocalRandom;

public class Die {
    private int faceValue = 0;


    public Die() {
        faceValue = ThreadLocalRandom.current().nextInt(1, 6 + 1);

    }

    public Die( int lowerBound, int upperBound) {
        faceValue = ThreadLocalRandom.current().nextInt(lowerBound, upperBound + 1);

    }

    public int getFaceValue(){
        return faceValue;
    }

    public int getSum(Die d1, Die d2){

      return  d1.getFaceValue()+d2.getFaceValue();
    }


}
