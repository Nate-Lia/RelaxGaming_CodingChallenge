import java.util.Map;

public class SlotsGameSimulator {
    static String[][] slotMatrix = new String[3][3];
    static String[] slotColumn = {"W1","H1","H2","H3","L1","L2","L3","L4", "B1"};
    static Map<String, Integer> slotPayouts = Map.of(
            "W1", 2000,
            "H1", 800,
            "H2", 400,
            "H3", 80,
            "L1", 60,
            "L2", 20,
            "L3", 16,
            "L4", 12,
            "B1", 0
    );

    static boolean isWinner = false;
    static int bonusCounter = 0;
    static int bet = 10;
    static int payout = 0;
    static int bonusPayout = 0;
    static int totalPayout = 0;
    static int simulationCount = 1000000;

    public static void main(String[] args){
        payout = 0;
        bonusPayout = 0;
        for(int i = 0; i < simulationCount; i++){
            SpinSlots();
        }
        System.out.println("Return to Player:  $" + totalPayout / (simulationCount * bet));
    }

    private static void SpinSlots(){
        int randomReel;
        //spin the slots
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                randomReel = (int) (Math.random() * 9);
                slotMatrix[i][j] = slotColumn[randomReel];
            }
        }

        //print the result in a 3x3 matrix
        for(int i = 0; i < 3; i++){
            //System.out.println();
            for(int j = 0; j < 3; j++){
                //System.out.print(slotMatrix[i][j] + " ");
                //check if the slot is a winner if the row has 3 of the same symbol
                if(j == 2){
                    if(IsWinningCombination(slotMatrix[i][0],  slotMatrix[i][1], slotMatrix[i][2]) && !isWinner){
                        if(slotMatrix[i][0].equals("W1") && !slotMatrix[i][1].equals("W1")){
                            payout += GetPayout(slotMatrix[i][1]);
                        }
                        else if(slotMatrix[i][1].equals("W1") && !slotMatrix[i][2].equals("W1")){
                            payout += GetPayout(slotMatrix[i][2]);
                        }
                        else{
                            payout += GetPayout(slotMatrix[i][0]);
                        }
                    }
                    //if the row is not a winner, check if the diagonals are winners
                    else if(i == 1){
                        if(IsWinningCombination(slotMatrix[0][0], slotMatrix[1][1], slotMatrix[2][2])){
                            if(slotMatrix[0][0].equals("W1") && !slotMatrix[1][1].equals("W1")){
                                payout += GetPayout(slotMatrix[1][1]);
                            }
                            else if(slotMatrix[1][1].equals("W1") && !slotMatrix[2][2].equals("W1")){
                                payout += GetPayout(slotMatrix[2][2]);
                            }
                            else{
                                payout += GetPayout(slotMatrix[0][0]);
                            }

                        }
                        else if(IsWinningCombination(slotMatrix[0][2], slotMatrix[1][1], slotMatrix[2][0])){
                            if(slotMatrix[0][2].equals("W1") && !slotMatrix[1][1].equals("W1")){
                                payout += GetPayout(slotMatrix[1][1]);
                            }
                            else if (slotMatrix[1][1].equals("W1") && !slotMatrix[2][0].equals("W1")){
                                payout += GetPayout(slotMatrix[2][0]);
                            }
                            else{
                                payout += GetPayout(slotMatrix[0][2]);
                            }
                        }
                    }
                }
                //check through slotMatrix and see if it contains at least 3 "B1"s
                if(slotMatrix[i][j].equals("B1") && bonusCounter < 3){
                    bonusCounter++;
                }
            }
        }
        //if bonusCounter >= 3 then bonus game is triggered
        if(bonusCounter >= 3){
            bonusPayout = RandomCoin();
        }
        totalPayout += bonusPayout + payout - bet;
        System.out.println("\n"+"Total Payout: $" + totalPayout);
    }

    /**
     * Check if the symbols form a winning combination
     * @param symbol1
     * @param symbol2
     * @param symbol3
     * @return
     */
    private static boolean IsWinningCombination(String symbol1, String symbol2, String symbol3) {
        // Check if the symbols form a winning combination
        return symbol1.equals(symbol2) && (symbol2.equals(symbol3) || symbol3.equals("W1"))
                || symbol1.equals("W1") && symbol2.equals(symbol3) || symbol1.equals(symbol3) && symbol2.equals("W1")
                || symbol1.equals("W1") && symbol3.equals("W1") || symbol2.equals("W1") && symbol3.equals("W1")
                || symbol1.equals("W1") && symbol2.equals("W1");
    }

    /**
     * Get the payout from the symbol
     * @param symbol
     * @return payout
     */
    private static int GetPayout(String symbol) {
        // Retrieve the payout for the symbol from the payout table
        return slotPayouts.get(symbol);
    }

    /**
     * When bonus game is triggered by getting 3 "B1"s, generate a random amount of coins
     *  A die is rolled as a multiplier
     * @return random amount of coins
     */
    private static int RandomCoin() {
        int totalWeights = 0;
        int dieRoll = (int) (Math.random() * 6) + 1;
        // generate a set random amount of coins using a predetermined weight
       Map<Integer, Integer> coinMap = Map.of(
               10, 100,
                20, 50,
               40, 30,
               50, 20,
               151, 10,
               220, 5,
               260, 4,
               300, 3,
               350, 2
       );
         for(int weight : coinMap.keySet()){
             totalWeights += weight;
         }
        int randomWeight = (int) (Math.random() * totalWeights);
         int cumulativeWeight = 0;
        //check randomWeight if it is less than the weight of the coinMap key, then return the value
        for(int weight : coinMap.keySet()){
            cumulativeWeight += weight;
            if(randomWeight < cumulativeWeight){
                return coinMap.get(weight) * dieRoll * bet;
            }
        }
        //if the randomWeight is greater than the cumulativeWeight, return the last value
        return coinMap.get(totalWeights) * dieRoll *  bet;
    }
}
