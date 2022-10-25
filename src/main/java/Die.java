public class Die
{
    private int faceValue = 0, numberOfSides = 6;



    public Die()
    {
        faceValue = (int)(Math.random() * numberOfSides) + 1;

    }

    public Die(int upperBound)
    {
        faceValue = (int)(Math.random() * upperBound) + 1;

    }
    public void Die_test (int lowerBound, int upperBound)
    {
        int temp =(int)(Math.random() * upperBound-lowerBound) + 1;
        faceValue = temp+lowerBound;
 //       System.out.println(temp + lowerBound);
 //       faceValue = upperBound;
    }

    public void dice_roll ()
    {
        faceValue = (int)(Math.random() * numberOfSides) + 1;
    }


    public int getFaceValue(){
        return faceValue;
    }


    public void setNumberOfSides(int newNumberOfSides)
    {
        numberOfSides=newNumberOfSides;
    }

}
