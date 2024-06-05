import processing.core.PApplet;

public class Automata extends PApplet {

    int numItems = 60;
    int numGeneration = 0;
    int[] currGen;
    int[] nextGen;
    float cellWidth, cellHeight;

    int[] rule = {0, 1, 1, 0, 1, 1, 0, 1};

    public void settings(){
        size(1600, 800);
    }

    public static void main(String[] args) {
        PApplet.main("Automata");
    }

    public void setup(){
        cellWidth = width / (float) numItems;
        cellHeight = cellWidth;

        currGen = new int[numItems];
        nextGen = new int[numItems];

        currGen[currGen.length/2] = 1;
        //randomValues();
    }

    public void draw(){
        //background(255);
        displayGeneration(numGeneration*cellHeight);
        calculateNextGeneration();
        swapArrays();
        numGeneration++;

        if(numGeneration>40){
            numGeneration = 0;
            resetGeneration();
            currGen[currGen.length/2] = 1;
            newRule();
        }
    }

    void displayCell(float x, float y, int v, int h){
        stroke(0);
        if(v==0){
            fill(255);
        }
        else {
            fill(0);
        }
        rect(x, y, h, h);
    }

    void displayGeneration(float y){
        for(int i=0; i<currGen.length; i++){
            int value = currGen[i];
            float x = i*cellWidth;
            displayCell(x, y, value, (int)cellWidth);
        }
    }

    void randomValues(){
        for(int i=0; i<currGen.length; i++){
            currGen[i] = (int) random(0, 2);
        }
    }

    void calculateNextGeneration(){
        for(int i=0; i<nextGen.length; i++){
            nextGen[i] = calculateCellValue(i);
        }
    }

    void swapArrays(){
        for(int i=0; i<currGen.length; i++){
            currGen[i] = nextGen[i];
        }
    }

    void resetGeneration(){
        for(int i=0; i<currGen.length; i++){
            currGen[i] = 0;
        }
    }

    int calculateCellValue(int n){
        int l, c, r;

        if(n>0){ l = currGen[n-1]; }
        else {   l = currGen[currGen.length-1]; }

        if(n<currGen.length-1){ r = currGen[n+1]; }
        else {   r = currGen[0]; }

        c = currGen[n];

        int b =4*l + 2*c + r;
        return rule[b];
    }

    void newRule(){
        for(int i=0; i<rule.length; i++){
            rule[i] = (int)random(2);
        }
    }
}