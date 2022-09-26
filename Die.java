import java.util.concurrent.ThreadLocalRandom;

public class Die {
    int faceValue = 0;
    int id = 0;

    public Die(int id) {
        faceValue = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        id = this.id;
    }

    public Die(int id, int lowerBound, int upperBound) {
        faceValue = ThreadLocalRandom.current().nextInt(lowerBound, upperBound + 1);
        id = this.id;
    }

}
