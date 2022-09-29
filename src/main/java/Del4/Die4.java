package Del4;

import java.util.concurrent.ThreadLocalRandom;

public class Die4 {
    private int faceValue = 0;

    public Die4() {
        faceValue = ThreadLocalRandom.current().nextInt(1, 6 + 1);
    }
    public Die4( int lowerBound, int upperBound) {
        faceValue = ThreadLocalRandom.current().nextInt(lowerBound, upperBound + 1);
    }
    public int getFaceValue(){
        return faceValue;
    }
}
