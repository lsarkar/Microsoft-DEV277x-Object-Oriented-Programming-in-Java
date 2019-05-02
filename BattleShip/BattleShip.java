import java.util.Scanner;
import java.util.Random;

// TODO fix bug when entering invalid coords for a 2nd time in a row

public class BattleShip {

    public static int[][] battleShipGrid = new int[10][10];
    public static int playerShipCount;
    public static int MAX_SHIPS = 5;

    public static void main(String[] args) {

        deployComputerShips();

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

    public static int getXGridMax() {
        return battleShipGrid.length -1;
    }

    public static int getYGridMax() {
        return battleShipGrid[0].length -1;
    }

    public static void deployPlayerShips() {
        Scanner input = new Scanner(System.in);

        System.out.println("Deploy your ships");

        for (int i=1; i<=MAX_SHIPS; i++) {


            final int[] coords = getXYCoords(i);
            if (!isCoordsInRange(coords)) {
                getXYCoords(i);
            }
            else {
                battleShipGrid[coords[0]][coords[1]] = 1;
            }
        }

    }

    public static void deployComputerShips() {

        System.out.println("Computer is deploying ships");

        for (int i=1; i<=MAX_SHIPS; i++) {
            // add 0.5 second delay to computer deployment per ship

            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {

            }
            int[] coords = generateRandom();

            while(!isCoordsInRange(coords)) {
                coords = generateRandom();
            }

            // put computer ship in the ocean grid
            battleShipGrid[coords[0]][coords[1]] = 2;

            System.out.println(String.format("%d. ship DEPLOYED" , i));
        }
    }

    public static int[] generateRandom() {
        int[] randXY = new int[2];

        final Random rand = new Random();

        // generates a random integer between 0 and map range
        randXY[0] = rand.nextInt(getXGridMax()+1);
        randXY[1] = rand.nextInt(getYGridMax()+1);

        return randXY;
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

        // check x range
        if (x > xMaxValue || x < 0 || y > yMaxValue || y < 0) {
            System.out.println("X coordinate out of range, must be in range 0 : " + xMaxValue);
            return false;
        }

        // check y range
        if (y > yMaxValue || y < 0) {
            System.out.println("Y coordinate out of range, must be in range 0 : " + yMaxValue);
            return false;
        }

        // check if ship already exists in current space
        if (battleShipGrid[x][y] == 1) {
            System.out.println(String.format("Invalid coordinate. Ship already placed at position %s,%s" , x, y));
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


