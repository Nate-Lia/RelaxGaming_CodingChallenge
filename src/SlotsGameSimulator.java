public class SlotsGameSimulator {
    static String[][] SlotMatrix = new String[3][3];
    static String[] SlotColumn = {"W1","H1","H2","H3","L1","L2","L3","L4", "B1"};
    public static void main(String[] args){
        SpinSlots();
    }

    private static void SpinSlots(){
        int randomReel;
        //spin the slots
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                randomReel = (int) (Math.random() * 9);
                SlotMatrix[i][j] = SlotColumn[randomReel];
            }
        }

        //print the result in a 3x3 matrix
        for(int i = 0; i < 3; i++){
            System.out.println();
            for(int j = 0; j < 3; j++){
                System.out.print(SlotMatrix[i][j] + " ");
                //check if the slot is a winner if the row has 3 of the same symbol
                if(j == 2){
                    if(SlotMatrix[i][0].equals(SlotMatrix[i][1]) && SlotMatrix[i][1].equals(SlotMatrix[i][2])){
                        System.out.print("Winner!");
                    }
                    //if the row is not a winner, check if the diagonals are winners
                    else if(i == 1){
                        if(SlotMatrix[0][0].equals(SlotMatrix[1][1]) && SlotMatrix[1][1].equals(SlotMatrix[2][2])){
                            System.out.print("Winner!");
                        }
                        else if(SlotMatrix[0][2].equals(SlotMatrix[1][1]) && SlotMatrix[1][1].equals(SlotMatrix[2][0])){
                            System.out.print("Winner!");
                        }
                    }
                }
            }
        }
    }
}
