import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class BattleShip {

    public static final int SHIP_PLAYER = 1;
    public static final int SHIP_COMPUTER = 2;
    public static char[][] battleShipGrid = new char[10][10];
    public static int MAX_SHIPS = 5;
    public static int playerLives = MAX_SHIPS;
    public static int computerLives = MAX_SHIPS;
    public static boolean gameOver = false;

    public static void main(String[] args) {

        System.out.println("**** Welcome to Battle Ships game ****\n\nRight now, the sea is empty\n");

        createOceanMap();
        System.out.println("\n");
        deployPlayerShips();

        createOceanMap();
        deployComputerShips();

        battleSequence(gameOver);
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

        System.out.println("Deploy your ships");

        for (int i=1; i<=MAX_SHIPS; i++) {

            final int[] coords = getXYCoords(i);
            if (!isCoordsInRange(coords)) {
                getXYCoords(i);
            }
            else {
                battleShipGrid[coords[0]][coords[1]] = SHIP_PLAYER;
            }
        }

    }

    public static void deployComputerShips() {

        System.out.println("Computer is deploying ships");

        for (int i=1; i<=MAX_SHIPS; i++) {
            // add 0.5 second delay to computer deployment per ship

            try {
                Thread.sleep(200);
            }
            catch (InterruptedException e) {

            }
            int[] coords = generateRandom();

            while(!deployCheck(coords)) {
                coords = generateRandom();
            }

            // put computer ship in the ocean grid
            battleShipGrid[coords[0]][coords[1]] = SHIP_COMPUTER;
            System.out.println(String.format("%d. ship DEPLOYED" , i));
        }
    }

    public static void battleSequence(boolean gameOver) {
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
        while(!checkGridRange(shipPos));
            //{System.out.println("Coordinates out of range");
        //}

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
        while(!checkGridRange(shipPos));

        int[] hits = validateHit(shipPos, "computer", computerGuesses);
        updateLives(hits);
    }

    public static int[] validateHit(int[] coord, String turn, ArrayList<int[]> computerGuess) {

        int mapValue = battleShipGrid[coord[0]][coord[1]];

        // player hits 1st element, computer hits 2nd element
        int[] hits = new int[2];

        if (turn.toLowerCase() == "player") {

            switch (mapValue) {

                // handle players turn

                case SHIP_COMPUTER:
                    System.out.println("Boom! You sunk the ship!");
                    battleShipGrid[coord[0]][coord[1]] = '!';
                    hits[1] = 1;
                    break;
                case SHIP_PLAYER:
                    System.out.println("Oh no, you sunk your own ship :(");
                    battleShipGrid[coord[0]][coord[1]] = 'x';
                    hits[0] = 1;
                    break;
                default:
                    System.out.println("Sorry, you missed");
                    battleShipGrid[coord[0]][coord[1]] = '-';
            }
        }

        // handle computers turn
        else {

            // store computer guesses so that guesses are not repeated
            computerGuess.add(coord);

            switch (mapValue) {

                case SHIP_COMPUTER:
                    System.out.println("The Computer sunk one of its own ships");
                    battleShipGrid[coord[0]][coord[1]] = '!';
                    hits[0] = 1;
                    break;
                case SHIP_PLAYER:
                    System.out.println("The Computer sunk one of your ships!");
                    battleShipGrid[coord[0]][coord[1]] = 'x';
                    hits[1] = 1;
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

    public static boolean checkGridRange(int[] coords) {
        int xMaxValue = getXGridMax();
        int yMaxValue = getYGridMax();

        int x = coords[0];
        int y = coords[1];

        // check x and y range
        if (x > xMaxValue || x < 0 || y > yMaxValue || y < 0) {
            //System.out.println("X coordinate out of range, must be in range 0 : " + xMaxValue);
            return false;
        }
        else {
            return true;
        }
    }


    public static boolean deployCheck(int[] coords) {

        if (!checkGridRange(coords)) {
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
        if (battleShipGrid[x][y] == SHIP_PLAYER) {
            //System.out.println(String.format("Invalid coordinate. Ship already placed at position %s,%s" , x, y));
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
        if (battleShipGrid[x][y] == 1) {
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

        for (int row = 0; row < battleShipGrid.length; row++) {

            for (int col = 0; col < battleShipGrid[row].length; col++) {

                if (col == 0) {
                    System.out.print(row + " | ");
                }
                else if (col == battleShipGrid[row].length - 1) {
                    System.out.print(" | " + row);
                }
                else {

                    // if a battleship is present, display it in the ocean map as "@"

                    if (battleShipGrid[row][col] == SHIP_PLAYER) {
                        // display players ship with special symbol
                        System.out.print("@");
                    }
                    else if (battleShipGrid[row][col] == 'x') {
                        System.out.print("x");
                    }
                    else if (battleShipGrid[row][col] == '!') {
                        System.out.print("!");
                    }
                    else if (battleShipGrid[row][col] == '-') {
                        System.out.print("-");
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


