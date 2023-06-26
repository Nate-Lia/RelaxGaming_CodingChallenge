
public class DiceGameSimulator {
    private static int totalOneDieAmount;
    private static int totalTwoDieAmount;
    private static float oneDieGameVariance  = 0;
    private static float twoDieGameVariance = 0;
    private static final int simulationCount = 1000000;
    private static int[] oneDieGamePayouts = new int[simulationCount];
    private static  int[] twoDieGamePayouts = new int[simulationCount];

    private int slotsBet = 10;


    public static void main(String[] args) {
        OneDiceGame();
        TwoDiceGame();
        CalculateMean();
        System.out.println("----------------------------------------------");
        CalculateVariance();
        System.out.println("----------------------------------------------");
        CalculateStandardDeviation();
    }

    /**
     * Method to create the dice game simulation using 1 die
     */
    public static void OneDiceGame(){
        int diceNumber; // the number the die rolled
        boolean sixRolled = false; // checks if a 6 has been rolled
        for(int i = 0; i < simulationCount; i++){
            for(int rollCounter = 0; rollCounter < 4; rollCounter++){
                diceNumber = (int) (Math.random() * 6 + 1);

                if(diceNumber == 6 && !sixRolled){
                    sixRolled = true;
                    totalOneDieAmount += 1;
                }

                if(rollCounter == 3 && !sixRolled){
                    totalOneDieAmount -= 1;
                }
                else if(rollCounter == 3 && sixRolled){
                    sixRolled = false;
                }
            }
            oneDieGamePayouts[i] = totalOneDieAmount;
        }
    }

    public static void TwoDiceGame(){
        int dice1Number; // the number of dice 1
        int dice2Number; // the number of dice 2
        boolean doubleSixRolled = false; // checks if a 6 has been rolled by both dice
        for(int i = 0; i < simulationCount; i++) {
            for (int rollCounter = 0; rollCounter < 24; rollCounter++) {
                dice1Number = (int) (Math.random() * 6 + 1);
                dice2Number = (int) (Math.random() * 6 + 1);

                if (dice1Number == 6 && dice2Number == 6 && !doubleSixRolled) {
                    doubleSixRolled = true;
                    totalTwoDieAmount += 1;
                }

                if (rollCounter == 23 && !doubleSixRolled) {
                    totalTwoDieAmount -= 1;
                } else if (rollCounter == 23 && doubleSixRolled) {
                    doubleSixRolled = false;
                }
            }
            twoDieGamePayouts[i] = totalTwoDieAmount;
        }
    }

    public static void CalculateMean(){
        float meanOneDieGame = (float) totalOneDieAmount / simulationCount;
        float meanTwoDiceGame = (float) totalTwoDieAmount / simulationCount;

        System.out.println("One Die Game Mean: $" + meanOneDieGame);
        System.out.println("Two Dice Game Mean: $" + meanTwoDiceGame);
    }

    public static void CalculateVariance(){
        for(int payout : oneDieGamePayouts){
            oneDieGameVariance += Math.pow(payout - totalOneDieAmount, 2);
        }
        for(int payout : twoDieGamePayouts){
            twoDieGameVariance += Math.pow(payout - totalTwoDieAmount, 2);
        }

        oneDieGameVariance /= simulationCount;
        twoDieGameVariance /= simulationCount;

        System.out.println("One Die Game Variance: " + oneDieGameVariance);
        System.out.println("Two Dice Game Variance: " + twoDieGameVariance);
    }

    public static void CalculateStandardDeviation(){
        float standardDeviationOneDieGame = (float) Math.sqrt(oneDieGameVariance / simulationCount);
        float standardDeviationTwoDiceGame = (float) Math.sqrt(twoDieGameVariance / simulationCount);

        System.out.println("One Die Game Standard Deviation: " + standardDeviationOneDieGame);
        System.out.println("Two Dice Game Standard Deviation: " + standardDeviationTwoDiceGame);
    }
}