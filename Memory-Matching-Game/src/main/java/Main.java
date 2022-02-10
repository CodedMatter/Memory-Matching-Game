import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main program = new Main();
        Scanner input = new Scanner(System.in);

        // create and set up the grid
        Grid grid = new Grid(4,4);
        grid.hideValuesRandomlyInTiles(grid.getTileInGrid());

        // print out instructions
        program.printInstructions();

        while(grid.numberOfMatchesLeft > 0){
            grid.printGrid();
            // ask player to select tiles
            program.askPlayerToSelectTiles(input,grid);

            // check if the two tiles selected match
            if(grid.doFlippedTileMatch()){
                grid.matchFound();
            }
            else{
                grid.matchNotFound(input);
            }
        }
        System.out.println("Nice you won!!");



    }

    private void askPlayerToSelectTiles(Scanner input,Grid grid) {
        boolean isValidTile = false;
        String responseString;
        String[] responseInArray = new String[0];

        while (!isValidTile){
            System.out.println("Select the position of the tile you want to select. " +
                    "EX:(" + "\u001B[34m" + "0" + "\u001B[0m" + "\u001B[31m" +  " 0" + "\u001B[0m" + ")");
            System.out.print("First tile selected: ");
            responseString = input.nextLine().trim();
            responseInArray = responseString.split(" ");
            int x = 0;
            int y = 0;
           if(responseInArray.length == 2){
               x = Integer.parseInt(responseInArray[0]);
               y = Integer.parseInt(responseInArray[1]);
           }else{
               System.out.println("Wrong input. Should only be 2 numbers seperated by a space.");
               continue;
           }

           if(grid.doesTileExist(x,y)){
               grid.selectFirstTile(x,y);
               isValidTile = true;
               grid.flipTile(grid.getFirstTileSelected());
           }
           else{
               System.out.println("Try to select a valid tile");
               isValidTile = false;
           }
        }
        grid.printGrid();
        isValidTile = false;
        responseInArray = new String[0];
        while (!isValidTile){
            System.out.print("Select the second tile you want to flip over: ");
            responseString = input.nextLine().trim();
            responseInArray = responseString.split(" ");
            int x = 0;
            int y = 0;
            if(responseInArray.length == 2){
                x = Integer.parseInt(responseInArray[0]);
                y = Integer.parseInt(responseInArray[1]);
            }else{
                System.out.println("Wrong input. Should only be 2 numbers seperated by a space.");
                continue;
            }

            if(grid.doesTileExist(x,y)){
                grid.selectSecondTile(x,y);
                if(grid.getSecondTilSelected() != grid.getFirstTileSelected()){
                    isValidTile = true;
                    grid.flipTile(grid.getSecondTilSelected());
                }else{
                    System.out.println("Cant select the same tile that is flipped.");
                    isValidTile = false;
                }
            }
            else{
                System.out.println("Try to select a valid tile");
                isValidTile = false;
            }
        }
    }

    private void printInstructions() {
        System.out.println("Select 2 tiles to flip over. " +
                "If they match they stay flipped over. If they " +
                "dont match they flip back. You win by flipping all " +
                "the card over.");
    }
}
