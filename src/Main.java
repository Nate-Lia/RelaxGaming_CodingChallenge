import java.util.Random;
public class Main {
    private static int totalOneDieAmount;
    private int slotsBet = 10;

    private static Random rand = new Random();
    public static void main(String[] args) {
        OneDiceGame();
    }

    /**
     * Method to create the dice game simulation using 1 die
     */
    public static void OneDiceGame(){
        int diceNumber; // the number the die rolled
        boolean sixRolled = false; // checks if a 6 has been rolled
        for(int i = 0; i < 1000000; i++){
            for(int rollCounter = 0; rollCounter < 4; rollCounter++){
                diceNumber = (int) (Math.random() * 6 + 1);
                diceNumber += 1; // randomize between 1-6

                if(diceNumber == 6 && !sixRolled){
                    sixRolled = true;
                    totalOneDieAmount += 1;
                    System.out.println("Win");
                }

                if(rollCounter == 3 && !sixRolled){
                    totalOneDieAmount -= 1;
                    System.out.println("Lose");
                }
                else if(rollCounter == 3 && sixRolled){
                    sixRolled = false;
                }
            }
        }
        System.out.println("Total Payout: $" + totalOneDieAmount);
    }

    public static void TwoDiceGame(){
        /*int dice1Number; // the number the die rolled
        int dice2Number;
        boolean sixRolled = false; // checks if a 6 has been rolled
        for(int i = 0; i < 1000000; i++){
            for(int rollCounter = 0; rollCounter < 4; rollCounter++){
                diceNumber = (int) (Math.random() * 6 + 1);
                diceNumber += 1; // randomize between 1-6

                if(diceNumber == 6 && !sixRolled){
                    sixRolled = true;
                    totalOneDieAmount += 1;
                    System.out.println("Win");
                }

                if(rollCounter == 3 && !sixRolled){
                    totalOneDieAmount -= 1;
                    System.out.println("Lose");
                }
                else if(rollCounter == 3 && sixRolled){
                    sixRolled = false;
                }
            }
        }
        System.out.println("Total Payout: $" + totalOneDieAmount);*/
    }
}