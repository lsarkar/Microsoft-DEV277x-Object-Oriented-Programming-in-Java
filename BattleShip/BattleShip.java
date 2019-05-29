import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class BattleShip {

    public static final char SHIP_PLAYER = '@';
    public static final int SHIP_COMPUTER = 2;
    public static char[][] ocean = new char[10][10];
    public static int MAX_SHIPS = 5;
    public static int playerLives;
    public static int computerLives;
    public static boolean gameOver = false;

    public static void main(String[] args) {

        System.out.println("**** Welcome to Battle Ships game ****\n\nRight now, the sea is empty\n");

        playerLives = MAX_SHIPS;
        computerLives = MAX_SHIPS;

        createOceanMap();
        System.out.println("\n");
        deployPlayerShips();

        createOceanMap();
        deployComputerShips();

        battleSequence();
    }

    public static void printRow() {
        System.out.print("   ");
        for (int row = 0; row< ocean[0].length; row++) {
            System.out.print(row);
        }
        System.out.println();
    }

    public static int getXGridMax() {
        return ocean.length -1;
    }

    public static int getYGridMax() {
        return ocean[0].length -1;
    }

    public static void deployPlayerShips() {

        System.out.println("Deploy your ships");

        for (int i=1; i<=MAX_SHIPS; i++) {

            int[] coords = getXYCoords(i);
            while (!checkGridRange(coords, true)) {
                coords = getXYCoords(i);
            }

            ocean[coords[0]][coords[1]] = SHIP_PLAYER;
        }
    }

    public static void deployComputerShips() {

        System.out.println("Computer is deploying ships");

        for (int i=1; i<=MAX_SHIPS; i++) {
            // add 0.2 second delay to computer deployment per ship

            try {
                Thread.sleep(200);
            }
            catch (InterruptedException e) {

            }
            int[] coords = generateRandom();

            while(!deployCheckForComputer(coords)) {
                coords = generateRandom();
            }

            // put computer ship in the ocean grid
            ocean[coords[0]][coords[1]] = SHIP_COMPUTER;
            System.out.println(String.format("%d. ship DEPLOYED" , i));
        }
    }

    public static void battleSequence() {
        while (!gameOver) {
            ArrayList<int[]> computerGuesses = new ArrayList<>();
            playersTurn();
            computersTurn(computerGuesses);
            createOceanMap();
            printGameStatus();
        }

    }

    public static void playersTurn() {
        int[] shipPos = new int[2];

        shipPos[0] = getXGridMax();
        shipPos[1] = getYGridMax();

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("YOUR TURN");

            System.out.print(String.format("Enter X coordinate: "));
            shipPos[0] = input.nextInt();

            System.out.print(String.format("Enter Y coordinate: "));
            shipPos[1] = input.nextInt();
        }
        while(!checkGridRange(shipPos, true));

        int[] turnHits = validateHit(shipPos, "player", null);
        updateLives(turnHits);

    }

    public static void updateLives(int[] hits) {

        int playerHits = hits[0];
        int computerHits = hits[1];

        playerLives-=playerHits;
        computerLives-=computerHits;

        if (playerLives == 0) {
            gameOver = true;
        }
        else if (computerLives == 0) {
            gameOver = true;
        }
    }

    public static void computersTurn(ArrayList<int[]> computerGuesses) {
        int[] shipPos = new int[2];

        shipPos[0] = getXGridMax();
        shipPos[1] = getYGridMax();

        System.out.println("COMPUTERS TURN");

        do {
            shipPos = generateRandom();
        }
        while(!checkGridRange(shipPos, false));

        int[] hits = validateHit(shipPos, "computer", computerGuesses);
        updateLives(hits);
    }

    public static int[] validateHit(int[] coord, String turn, ArrayList<int[]> computerGuess) {

        int mapValue = ocean[coord[0]][coord[1]];

        // times a player is hit 1st element, computer hits 2nd element
        int[] hits = new int[2];

        if (turn.toLowerCase() == "player") {

            switch (mapValue) {

                // handle players turn

                case SHIP_COMPUTER:
                    System.out.println("Boom! You sunk the ship!");
                    ocean[coord[0]][coord[1]] = '!';
                    hits[1] = 1;
                    break;
                case SHIP_PLAYER:
                    System.out.println("Oh no, you sunk your own ship :(");
                    ocean[coord[0]][coord[1]] = 'x';
                    hits[0] = 1;
                    break;
                default:
                    System.out.println("Sorry, you missed");
                    ocean[coord[0]][coord[1]] = '-';
            }
        }

        // handle computers turn
        else {

            // store computer guesses so that guesses are not repeated
            computerGuess.add(coord);

            switch (mapValue) {

                case SHIP_COMPUTER:
                    System.out.println("The Computer sunk one of its own ships");
                    ocean[coord[0]][coord[1]] = '!';
                    hits[1] = 1;
                    break;
                case SHIP_PLAYER:
                    System.out.println("The Computer sunk one of your ships!");
                    ocean[coord[0]][coord[1]] = 'x';
                    hits[0] = 1;
                    break;
                default:
                    // don't need to print to the ocean map if the computer misses
                    System.out.println("Computer missed");
                    break;
            }

        }

        return hits;

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

    public static boolean checkGridRange(int[] coords, boolean printMessage) {
        int xMaxValue = getXGridMax();
        int yMaxValue = getYGridMax();

        int x = coords[0];
        int y = coords[1];

        // check x and y range
        if (x > xMaxValue || x < 0 || y > yMaxValue || y < 0) {

            if (printMessage) {
                System.out.println("Coordinates out of range!");
            }
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean deployCheckForComputer(int[] coords) {

        if (!checkGridRange(coords, false)) {
            return false;
        }

        if (!isSpaceTakenByPlayerShip(coords[0], coords[1])) {
            return false;
        }

        return true;
    }

    /*
        Check if grid space is occupied by a deployed ship
    */
    public static boolean isSpaceTakenByPlayerShip(int x, int y) {

        // check if ship already exists in current space
        if (ocean[x][y] == SHIP_PLAYER) {
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean isCoordsInRange(int[] coords) {

        int xMaxValue = getXGridMax();
        int yMaxValue = getYGridMax();

        int x = coords[0];
        int y = coords[1];

        // check x and y range
        if (x > xMaxValue || x < 0 || y > yMaxValue || y < 0) {
            System.out.println("X coordinate out of range, must be in range 0 : " + xMaxValue);
            return false;
        }

        // check if ship already exists in current space
        if (ocean[x][y] == 1) {
            System.out.println(String.format("Invalid coordinate. Ship already placed at position %s,%s" , x, y));
            return false;
        }

        return true;
    }

    public static void printGameStatus() {
        System.out.println();
        System.out.println(String.format("Your ships %d | Computer Ships %d" , playerLives, computerLives));

        if (playerLives == 0) {
            System.out.println("You lose the game!");
        }
        else if (computerLives == 0) {
            System.out.println("Hooray, you win the game!");
        }

        System.out.println("-----------------------------");
    }

    public static void createOceanMap() {

        printRow();

        for (int row = 0; row < ocean.length; row++) {

            System.out.print("\n" + row + " |");

            for (int col = 0; col < ocean[row].length; col++) {

                if (ocean[row][col] == 0)
                    System.out.print(" ");
                else
                    System.out.print(ocean[row][col]);

            }
            System.out.print("| " + row);
        }

        System.out.println();

        printRow();
    }
}