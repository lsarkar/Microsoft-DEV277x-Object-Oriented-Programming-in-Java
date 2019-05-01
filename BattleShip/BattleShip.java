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

        createOceanMap();

    }

    public static void printRow() {
        System.out.print("   ");
        for (int row = 0; row<battleShipGrid[0].length; row++) {
            System.out.print(row);
        }
        System.out.println();
    }

    public static void deployPlayerShips() {
        int maxShips = 5;
        Scanner input = new Scanner(System.in);

        System.out.println("Deploy your ships");

        for (int i=1; i<=maxShips; i++) {

            final int[] coords = getXYCoords(i);
            if (!isCoordsInRange(coords)) {
                getXYCoords(i);
            }
            else {
                battleShipGrid[coords[0]][coords[1]] = 1;
            }
        }

    }

    public static int[] getXYCoords(int shipNumber) {

        int[] shipPos = new int[2];

        Scanner input = new Scanner(System.in);

        System.out.print(String.format("Enter X coordinate for your %d. ship: ", shipNumber));
        shipPos[0] = input.nextInt();


        System.out.print(String.format("Enter Y coordinate for your %d. ship: ", shipNumber));
        shipPos[1] = input.nextInt();

        return shipPos;
    }

    public static boolean isCoordsInRange(int[] coords) {

        int xMaxValue = battleShipGrid.length -1;
        int yMaxValue = battleShipGrid[0].length -1;

        int x = coords[0];
        int y = coords[1];

        if (x > xMaxValue || x < 0 || y > yMaxValue || y < 0) {
            System.out.println("X coordinate out of range, must be in range 0 : " + xMaxValue);
            return false;
        }

        if (y > yMaxValue || y < 0) {
            System.out.println("Y coordinate out of range, must be in range 0 : " + yMaxValue);
            return false;
        }

        return true;
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

                    // if a battleship is present, display it in the ocean map as "@"
                    if (battleShipGrid[row][col] == 1) {
                        System.out.print("@");
                    }
                    else {
                        System.out.print(" ");
                    }
                }
            }

            System.out.println();

        }

        printRow();
    }

}


