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


    public static void main(String[] args){
        SpinSlots();
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
            System.out.println();
            for(int j = 0; j < 3; j++){
                System.out.print(slotMatrix[i][j] + " ");
                //check if the slot is a winner if the row has 3 of the same symbol
                if(j == 2){
                    if(isWinningCombination(slotMatrix[i][0],  slotMatrix[i][1], slotMatrix[i][2])){
                        System.out.print("Winner! Payout: $" + getPayout(slotMatrix[i][0]));
                    }else if(slotMatrix[i][0].equals("W1") && slotMatrix[i][1].equals("W1") && slotMatrix[i][2].equals("W1")){
                        System.out.print("Winner! Payout: $" + getPayout(slotMatrix[i][0]));
                    }
                    //if the row is not a winner, check if the diagonals are winners
                    else if(i == 1){
                        if(isWinningCombination(slotMatrix[0][0], slotMatrix[1][1], slotMatrix[2][2])){
                            System.out.print("Winner! Payout: $" + getPayout(slotMatrix[0][0]));
                        }else if(slotMatrix[0][0].equals("W1") && slotMatrix[1][1].equals("W1") && slotMatrix[2][2].equals("W1")){
                            System.out.print("Winner! Payout: $" + getPayout(slotMatrix[i][0]));
                        }
                        else if(isWinningCombination(slotMatrix[0][2], slotMatrix[1][1], slotMatrix[2][0])){
                            System.out.print("Winner! Payout: $" + getPayout(slotMatrix[0][2]));
                        }else if(slotMatrix[2][0].equals("W1") && slotMatrix[1][1].equals("W1") && slotMatrix[0][2].equals("W1")){
                            System.out.print("Winner! Payout: $" + getPayout(slotMatrix[i][0]));
                        }
                    }
                }
            }
        }
    }

    /**
     * Check if the symbols form a winning combination
     * @param symbol1
     * @param symbol2
     * @param symbol3
     * @return
     */
    private static boolean isWinningCombination(String symbol1, String symbol2, String symbol3) {
        // Check if the symbols form a winning combination
        return symbol1.equals(symbol2) && (symbol2.equals(symbol3) || symbol3.equals("W1"))
                || symbol1.equals("W1") || symbol2.equals("W1") && symbol2.equals(symbol3);
    }

    /**
     * Get the payout from the symbol
     * @param symbol
     * @return payout
     */
    private static int getPayout(String symbol) {
        // Retrieve the payout for the symbol from the payout table
        return slotPayouts.get(symbol);
    }
}
