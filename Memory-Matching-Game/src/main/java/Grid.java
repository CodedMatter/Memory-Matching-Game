import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Grid {
    private int xMax;
    private int yMax;
    private Tile[][] tileInGrid;
    private List<Integer> possibleValues;
    private Random random = new Random();
    private Tile firstTileSelected;
    private Tile secondTileSelected;
    int numberOfMatchesLeft;

    public Grid(int xMax, int yMax){
        this.xMax = xMax;
        this.yMax = yMax;
        tileInGrid = new Tile[xMax][yMax];
        fillGrid(tileInGrid);
        possibleValues = new ArrayList<>();
        numberOfMatchesLeft = ((xMax*yMax)/2);
        for (int i = 0; i < (xMax*yMax)/2; i++) {
            // values are numbers for now. change later to something else
            possibleValues.add(i);
            possibleValues.add(i);
        }
    }

    public Tile[][] getTileInGrid() {
        return tileInGrid;
    }
    public Tile getTile(int x, int y){
        return getTileInGrid()[x][y];
    }
    public Tile getFirstTileSelected() {
        return firstTileSelected;
    }
    public Tile getSecondTilSelected() {
        return secondTileSelected;
    }

    public void flipTile(Tile tileToFlip){
        tileToFlip.setFlipped(true);
    }

    private void fillGrid(Tile[][] tileInGrid) {
        int tileID = 1;
        for (int x = 0; x < xMax; x++) {
            for (int y = 0; y < yMax; y++) {
                Tile newTile = new Tile(x,y,tileID);
                tileInGrid[x][y] = newTile;
                tileID++;
            }
        }
    }

    public void hideValuesRandomlyInTiles(Tile[][] tileInGrid){
        for (Tile[] tiles : tileInGrid){
            for (Tile tile : tiles){
                tile.assignHiddenValue(getRandomValue());
            }
        }
    }

    private int getRandomValue() {
        int valueToReturn;
        valueToReturn = possibleValues.get(random.nextInt(possibleValues.size()));
        possibleValues.remove((Integer) valueToReturn);
        return valueToReturn;
    }

    public void printGrid(){
        for (int x = 0; x <= xMax; x++) {
            if(x == xMax){
                // column print index
                for (int i = 0; i <= yMax; i++) {
                    if(i == 0){
                        System.out.print("  ");
                    }
                    else
                    {
                        System.out.print("\u001B[31m" + " " + (i-1) + " " + "\u001B[0m");
                    }
                }
            }
            else{
                // row print index
                System.out.print("\u001B[34m"+ x +" " + "\u001B[0m");
                for (int y = 0; y <yMax; y++) {
                    tileInGrid[x][y].printTile();
                }
            }
            System.out.println();
        }
    }

    public boolean doesTileExist(int x, int y){
        try{
            Tile tile= getTileInGrid()[x][y];
            if(tile != null){
                if(tile.isMatched()){
                    System.out.println("Cant select a tile already matched.");
                    return false;
                }
                System.out.println("X:" + x + "Y:" + y + " can find tile");
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            System.out.println("Error: X:" + x + "Y:" + y + " Tile being search is out of bounds");
            return false;
        }
    }
    public void selectFirstTile(int x, int y){
        firstTileSelected = getTile(x,y);
    }
    public void selectSecondTile(int x, int y){
        secondTileSelected = getTile(x,y);
    }

    public boolean doFlippedTileMatch(){
        if(getFirstTileSelected().getHiddenValue() == getSecondTilSelected().getHiddenValue()){
            return true;
        }
        else{
            return false;
        }
    }

    public void flipSelectedTilesBack(){
        getFirstTileSelected().setFlipped(false);
        firstTileSelected = null;
        getSecondTilSelected().setFlipped(false);
        secondTileSelected = null;
    }

    public void matchFound(){
        getFirstTileSelected().setMatched(true);
        getSecondTilSelected().setMatched(true);
        firstTileSelected = null;
        secondTileSelected = null;
        numberOfMatchesLeft--;
        printGrid();
        System.out.println("Match was Found Good Job");
    }



    public void matchNotFound(Scanner input) {
        printGrid();
        System.out.println("Wasn't a match");
        System.out.print("Press enter to flip tiles back and guess again: ");
        input.nextLine();
        flipSelectedTilesBack();
    }
}
