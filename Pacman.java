
import java.util.Scanner;
public class Pacman {
    /** Sebs */
    private String[][] grid = new String[20][20];
    private String pacman = "C";
    private String ghost = "O";
    private int coins = 0;
    private int row= 9;
    private int col= 9;
    private int ghostrow = 14;
    private int ghostcol = 9;

//========= GRID ==========//
    /**This method populates the grid*/
    private void buildGrid() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = ".";
            }
        }
        createBoundary();
        createGate();
        createWalls();
        grid[row][col] = pacman;
        grid[ghostrow][ghostcol] = ghost;
 
    }
    /**This method Displays the grid*/
    private  void printGrid(){
        for(int row = 0; row<grid.length;row++){
            for(int col = 0;col<grid[row].length;col++){
                System.out.print(grid[row][col]+" ");
            }
            System.out.println();
        }
    }

//=========== PACMAN ==============//
    private void pacmanRight(){
        grid[row][col]=" ";
        col++;

        if(pacmanAtGate()){
            row = 9;
            col= 0 ;
        }
        else if(pacmanAtBoundary()){
            col--;
        }
        else if(grid[row][col] == "."){
            coins++;
        }
        grid[row][col]=pacman;

    }
    private void pacmanLeft(){
        grid[row][col] =" ";
        col--;
        if(pacmanAtGate()){
            row = 9;
            col= 19;
        }
        else if(pacmanAtBoundary()){
            col++;
        }
        else if(grid[row][col] == "."){
            coins++;
        }
        grid[row][col] = pacman;
    }
    private void pacmanUp(){
        grid[row][col] = " ";
        row--;
        if(pacmanAtGate()){
            row = 19;
            col= 9 ;
        }
        else if(pacmanAtBoundary()){
            row++;
        }

        else if(grid[row][col] == "."){
            coins++;
        }

        grid[row][col] = pacman;
    }
    private void pacmanDown(){
        grid[row][col]=" ";
        row++;
        if(pacmanAtGate()){
            row =0;
            col=9;
        }
        else if(pacmanAtBoundary()){
            row--;
        }
        else if(grid[row][col] == "."){
            coins++;
        }
        grid[row][col] = pacman;
    }
    private boolean pacmanAtBoundary(){
        if(grid[row][col].equals("X"))
            return true;
        return false;
    }

    //============ GHOST =========//
    private void ghostUp(){
        grid[ghostrow][ghostcol] = ".";
            ghostrow--;
        if(ghostAtGate()){
            ghostrow = 19;
            ghostcol= 9 ;
        }
        else if(ghostAtBoundary()){
            ghostrow++;
        }
        else if(grid[ghostrow][ghostcol].equals(" ")){
            grid[ghostrow][ghostcol] = " ";

        }
        grid[ghostrow][ghostcol] = ghost;

    }
    private void ghostDown(){
        grid[ghostrow][ghostcol] = ".";

            ghostrow++;
        if(ghostAtGate()){
            ghostrow = 0;
            ghostcol= 9 ;
        }
        else if(ghostAtBoundary()) {
            ghostrow--;
        }
        else if(grid[ghostrow][ghostcol].equals(" ")){
            grid[ghostrow][ghostcol] = " ";

        }
        grid[ghostrow][ghostcol] = ghost;

    }
    private void ghostLeft(){
        grid[ghostrow][ghostcol] = ".";

            ghostcol--;

        if(ghostAtGate()){
            ghostrow = 9;
            ghostcol= 19 ;
        }
        else if(ghostAtBoundary()){
            ghostcol++;
        }
        else if(grid[ghostrow][ghostcol].equals(" ")){
            grid[ghostrow][ghostcol] = " ";

        }
        grid[ghostrow][ghostcol] = ghost;

    }
    private void ghostRight(){
        grid[ghostrow][ghostcol] = ".";
            ghostcol++;
        if(ghostAtGate()){
            ghostrow = 9;
            ghostcol= 0 ;
        }
        else if(ghostAtBoundary()){
            ghostcol--;
        }
        else if(grid[ghostrow][ghostcol].equals(" ")){
            grid[ghostrow][ghostcol] = " ";

        }
        grid[ghostrow][ghostcol] = ghost;

    }
    private boolean ghostAtBoundary(){
        if(grid[ghostrow][ghostcol].equals("X")|| grid[ghostcol][ghostrow].equals("X"))
            return true;
        return false;
    }
    private boolean ghostAtGate(){
        if(ghostrow == 0 && ghostcol == 9){
            return true;
        }
        if(ghostrow == 9 && ghostcol == 0){
            return true;
        }
        if(ghostrow == 9 && ghostcol == 19){
            return true;
        }
        if(ghostrow == 19 && ghostcol == 9){
            return true;
        }
        return false;
    }
    private boolean ghostEatsPacman(){
        if(grid[row][col].equals(ghost) ){
            return true;
        }
        return false;
    }

    //========= WALLS ==========//
    /**This method creates the walls around the grid*/
    private void createBoundary(){
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col<grid[row].length;col++){
                grid[0][col]="X";
                grid[row][0]="X";
            }
        }
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col<grid[row].length;col++){
                grid[19][col]="X";
                grid[row][19]="X";
            }
        }
    }
    /**This method creates all walls inside the Grid*/
    private void createWalls(){

        for(int i = 0; i < 4 ;i++){
            grid[2+i][4] = "X";

            grid[5][4+i] = "X";

            grid[16][8-i] = "X";

            grid[16-i][4] = "X";

            grid[16-i][16] = "X";

            grid[16][16-i] = "X";

            grid[2][12+i] = "X";

            grid[2+i][16] = "X";
        }
    }

    //========= GATES =========//
    /**This method is checks if Pacman is at a Gate*/
    private boolean pacmanAtGate(){
        if(row == 0 && col == 9){
            return true;
        }
        if(row == 9 && col == 0){
            return true;
        }
        if(row == 9 && col == 19){
            return true;
        }
        if(row == 19 && col == 9){
            return true;
        }
        return false;
    }
    /**This method creates the teleportation gates*/
    private void createGate(){
        grid[0][9]=" ";
        grid[9][0]=" ";
        grid[9][19]=" ";
        grid[19][9]=" ";
    }

   //========= GAME ==========//
    /**This method is called in the main to RUN the Pacman Game*/
    public void playPacman() {
        Scanner input = new Scanner(System.in);
        buildGrid();

        while (true) {
            System.out.println("Coins: "+coins);
            printGrid();
            System.out.println("Use keys WASD to move pacman");
            String key = input.nextLine();
            key.toLowerCase();
            if (key.equals("a")){
                pacmanLeft();
                ghostLeft();
                if(ghostEatsPacman()){
                    System.out.println("---------------------------");
                    System.out.println("GAME OVER ! ");
                    System.out.println("---------------------------");
                    playPacman();

                }

            } else if (key.equals("s")) {
                pacmanDown();
                ghostDown();
                if(ghostEatsPacman()){
                    System.out.println("---------------------------");
                    System.out.println("GAME OVER ! ");
                    System.out.println("---------------------------");
                    playPacman();

                }
            } else if (key.equals("d")) {
                pacmanRight();
                ghostRight();
                if(ghostEatsPacman()){
                    System.out.println("---------------------------");
                    System.out.println("GAME OVER ! ");
                    System.out.println("---------------------------");
                    playPacman();

                }
            } else if (key.equals("w")) {
                pacmanUp();
                ghostUp();
                if(ghostEatsPacman()){
                    System.out.println("---------------------------");
                    System.out.println("GAME OVER ! ");
                    System.out.println("---------------------------");
                    playPacman();

                }

            }
        }


    }

}
