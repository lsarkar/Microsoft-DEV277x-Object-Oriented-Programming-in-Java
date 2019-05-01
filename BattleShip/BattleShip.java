import java.util.Scanner;

public class BattleShip {

    public static int[][] battleShipGrid = new int[10][10];
    public static int playerShipCount;

    public static void main(String[] args) {
        System.out.println("**** Welcome to Battle Ships game ****\n\nRight now, the sea is empty\n");
        createOceanMap();
        playerShipCount = 1;
        System.out.println("\n");
        System.out.println("Deploy your ships:");
        deployPlayerShips();

    }

    public static void printRow() {
        System.out.print("   ");
        for (int row = 0; row<battleShipGrid[0].length; row++) {
            System.out.print(row);
        }
        System.out.println();
    }

    public static void deployPlayerShips() {
        Scanner input = new Scanner(System.in);

        while(playerShipCount <= 5) {

            System.out.print(String.format("Enter X coordinate for your %d. ship: ", playerShipCount));
            int x = input.nextInt();
            if (!isXCoordInRange(x)) {
                deployPlayerShips();
            }

            System.out.print(String.format("Enter Y coordinate for your %d. ship: ", playerShipCount));
            int y = input.nextInt();
            if (!isYCoordInRange(y)) {
                deployPlayerShips();
            }

            playerShipCount++;
        }
    }

    public static boolean isXCoordInRange(int x) {

        int xMaxValue = battleShipGrid.length -1;

        if (x > xMaxValue || x < 0) {
            System.out.println("X coordinate out of range, must be in range 0 : " + xMaxValue);
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean isYCoordInRange(int y) {

        int yMaxValue = battleShipGrid[0].length -1;

        if (y > yMaxValue || y < 0) {
            System.out.println("Y coordinate out of range, must be in range 0 : " + yMaxValue);
            return false;
        }
        else {
            return true;
        }
    }

    public static void createOceanMap() {

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


