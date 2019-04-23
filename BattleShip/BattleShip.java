public class BattleShipGame {

    public static int[][] battleShipGrid = new int[10][10];

    public static void main(String[] args) {

        System.out.println("**** Welcome to Battle Ships game ****\n\nRight now, the sea is empty\n");
        createGameGrid();
    }

    public static void printRow() {
        System.out.print("   ");
        for (int row = 0; row<battleShipGrid[0].length; row++) {
            System.out.print(row);
        }
        System.out.println();
    }

    public static void createGameGrid() {

        printRow();

        for (int row = 0; row < battleShipGrid.length; row++) {

            for (int col = 0; col < battleShipGrid[row].length; col++) {

                if (col == 0) {
                    System.out.print(row + " | ");
                } else if (col == battleShipGrid[row].length - 1) {
                    System.out.print(" | " + row);
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();

        }

        printRow();
    }

}


