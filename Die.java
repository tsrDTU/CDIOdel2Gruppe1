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

    public void rollDie(Die d1){
        d1.faceValue = ThreadLocalRandom.current().nextInt(1, 6 + 1);

    }
    public void rollDie(Die d1, Die d2){
        d1.faceValue = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        d2.faceValue = ThreadLocalRandom.current().nextInt(1, 6 + 1);
    }

}
