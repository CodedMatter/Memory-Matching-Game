public class Tile {
    private int x;
    private int y;
    private int hiddenValue;
    private int id;
    private boolean isFlipped;
    private boolean isMatched;

    Tile(int x, int y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
        isFlipped = false;
        isMatched = false;
    }

    public void assignHiddenValue(int value){
        hiddenValue = value;
    }
    public int getHiddenValue() {
        return hiddenValue;
    }

    public boolean isFlipped() {
        return isFlipped;
    }
    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public boolean isMatched() {
        return isMatched;
    }
    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public void printTile(){
        if(isFlipped){
            if(isMatched){
                System.out.print("\u001B[44m" + "[" + hiddenValue + "]" + "\u001B[0m");
            }
            else {
                System.out.print("[" + hiddenValue + "]");
            }

        }
        else{
            System.out.print("[" + " " + "]");
        }
    }
}
