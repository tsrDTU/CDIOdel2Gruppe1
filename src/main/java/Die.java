import java.util.concurrent.ThreadLocalRandom;

public class Die {
    private int faceValue = 0, prevFaceValue = 0;


    public Die() {
        prevFaceValue = faceValue;
        faceValue = ThreadLocalRandom.current().nextInt(1, 6 + 1);

    }

    public Die( int lowerBound, int upperBound) {
        prevFaceValue = faceValue;
        faceValue = ThreadLocalRandom.current().nextInt(lowerBound, upperBound + 1);

    }

    public int getFaceValue(){return faceValue; }





}
