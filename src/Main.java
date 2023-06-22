
public class Main {
    private static int totalOneDieAmount;
    private static int totalTwoDieAmount;
    private int slotsBet = 10;


    public static void main(String[] args) {
        OneDiceGame();
        System.out.println("----------------------------------------------");
        TwoDiceGame();
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
                }

                if(rollCounter == 3 && !sixRolled){
                    totalOneDieAmount -= 1;
                }
                else if(rollCounter == 3 && sixRolled){
                    sixRolled = false;
                }
            }
        }
        System.out.println("One Dice Game Total Payout: $" + totalOneDieAmount);
    }

    public static void TwoDiceGame(){
        int dice1Number; // the number of dice 1
        int dice2Number; // the number of dice 2
        boolean doubleSixRolled = false; // checks if a 6 has been rolled by both dice
        for(int i = 0; i < 1000000; i++){
            for(int rollCounter = 0; rollCounter < 24; rollCounter++){
                dice1Number = (int) (Math.random() * 6 + 1);
                dice1Number += 1; // randomize between 1-6
                dice2Number = (int) (Math.random() * 6 + 1);
                dice2Number += 1; // randomize between 1-6

                if(dice1Number == 6 && dice2Number==6  && !doubleSixRolled){
                    doubleSixRolled = true;
                    totalTwoDieAmount += 1;
                }

                if(rollCounter == 3 && !doubleSixRolled){
                    totalTwoDieAmount -= 1;
                }
                else if(rollCounter == 3 && doubleSixRolled){
                    doubleSixRolled = false;
                }
            }
        }
        System.out.println("Two Dice Game Total Payout: $" + totalTwoDieAmount);
    }
}