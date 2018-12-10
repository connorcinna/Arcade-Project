package cs1302.arcade;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import java.awt.event.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import java.util.Scanner;
import java.io.File;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextArea;

public class SudokuGame implements Playable {
    
    private Stage stage;
    private Tile[][] gameData;
    private Tile[][][] premadeBoards;
    private BoardPiece[] pieces;
    private int level;
    private String information;
    
    public void play() {
	premadeBoards = makePremadeBoards();
	level = 2;
	gameData = createGameData();
	pieces = new BoardPiece[9];
	HBox row1 = makeRowOne();
	Rectangle divider1 = makeHorizRectangle();
	Rectangle divider2 = makeHorizRectangle();
	HBox row2 = makeRowTwo();
	HBox row3 = makeRowThree();
	VBox vbox = new VBox();
	vbox.getChildren().addAll(row1, divider1, row2, divider2, row3);
	TextArea textArea = makeTextArea();
	HBox bigBox = new HBox();
	bigBox.getChildren().addAll(vbox, textArea);
	stage = new Stage();
	Scene scene = new Scene(bigBox, Color.BLACK);
	stage.setScene(scene);
	stage.setTitle("Sudoku!");
	stage.sizeToScene();
	stage.show();	
    } // play

    private Tile[][] createGameData(){
	Tile[][] tiles = new Tile[9][9];
	tiles = pickRandomBoard();
	if(level == 1){
	    tiles = loseSquares(tiles, 3);
	} // if
	else{
	    tiles = loseSquares(tiles, 60);
	} // else
	return tiles;
    } // createGameData
    
    private Tile[][] pickRandomBoard(){
	Tile[][] tiles;
	int boardChoice = (int) (Math.random() * 4);
	tiles = premadeBoards[boardChoice];
	tiles = mixBoard(tiles);
	return tiles;
    } // pickRandomBoard
    
    private Tile[][] loseSquares(Tile[][] tiles, int numLost){
	int randomX;
	int randomY;
	for(int i = 0; i < numLost; i++){
	    randomX = (int) (Math.random() * 9);
	    randomY = (int) (Math.random() * 9);
	    tiles[randomX][randomY].setZero();
	} // for
	return tiles;
    } // loseSquares
    
    private Tile[][] mixBoard(Tile[][] board){
	int iterations = (int) (Math.random() * 4);
	for(int i = 0; i < iterations; i++){
	    board = clockwiseTurn(board);
	} // for
	return board;
    } // mixBoard
    
    private Tile[][] clockwiseTurn(Tile[][] board){
	Tile[][] arr = new Tile[9][9];
	for(int r = 0; r < arr.length; r++){
	    for(int c = 0; c < arr.length; c++){
		arr[c][arr.length - 1 - r] = board[r][c];
	    } // for
	} // for
	return arr;
    } // clockwiseTurn
    
    protected boolean isWon(){
	for(int i = 0; i < gameData.length; i++){
	    for(int j = 0; j < gameData[i].length; j++){
		if(gameData[i][j].getNum() == 0){
		    return false;
		} // if
	    } // for
	} // for
	for(int i = 0; i < pieces.length; i++){
	    if(pieces[i].isSolved() == false){
		return false;
	    } // if
	} // for
	for(int i = 0; i < gameData.length; i++){
	    if(rowSolved(i) == false){
		return false;
	    } // if
	    if(columnSolved(i) == false){
		return false;
	    } // if
	} // for
	return true;
    } // gameWon
    
    private boolean rowSolved(int row){
	Tile[] tiles = gameData[row];
	boolean oneFound = false;
	for(int i = 1; i - 1 < tiles.length; i++){
	    oneFound = false;
	    for(int j = 0; j < tiles.length; j++){
		if(oneFound){
		    if(tiles[j].getNum() == i){
			return false;
		    } // if
		} // if
		if(!oneFound){
		    if(tiles[j].getNum() == i){
			oneFound = true;
		    } // if
		} // if
	    } // for
	    if(!oneFound){
		return false;
	    } // if
	} // for
	return true;
    } // rowSolved

    private boolean columnSolved(int col){
	Tile[] tiles = new Tile[9];
	for(int i = 0; i < tiles.length; i++){
	    tiles[i] = gameData[i][col];
	} // for
	boolean	oneFound = false;
	for(int i = 1; i - 1 < tiles.length; i++){
            oneFound = false;
	    for(int j = 0; j < tiles.length; j++){
		if(oneFound){
		    if(tiles[j].getNum() == i){
			return false;
		    } // if
		} // if 
		if(!oneFound){
		    if(tiles[j].getNum() == i){
			oneFound = true;
		    } // if
		} // if
	    } // for
            if(!oneFound){
		return false;
            } // if  
	} // for
        return true;	
    } // col
    
    private Rectangle makeHorizRectangle(){
	Rectangle r = new Rectangle();
	r.setHeight(10);
	r.setWidth(695);
	return r;
    } // makeHorizRectangle
    
    private Rectangle makeVertRectangle(){
	Rectangle r = new Rectangle();
	r.setWidth(10);
	r.setHeight(225);
	return r;
    } // makeVertRectangle
    
    private HBox makeRowOne(){
	HBox hbox = new HBox();
	pieces[0] = makeBoardPiece(0);
	Rectangle divider1 = makeVertRectangle();
	pieces[1] = makeBoardPiece(1);
	Rectangle divider2 = makeVertRectangle();
	pieces[2] = makeBoardPiece(2);
	hbox.getChildren().addAll(pieces[0], divider1, pieces[1], divider2, pieces[2]);
	return hbox;
    } // makeRowOne

    private HBox makeRowTwo(){
	HBox hbox = new HBox();
	pieces[3] = makeBoardPiece(3);
	Rectangle divider1 = makeVertRectangle();
	pieces[4] = makeBoardPiece(4);
	Rectangle divider2 = makeVertRectangle();
	pieces[5] = makeBoardPiece(5);
	hbox.getChildren().addAll(pieces[3], divider1, pieces[4], divider2, pieces[5]);
	return hbox;
    } // makeRowTwo

    private HBox makeRowThree(){
	HBox hbox = new HBox();
	pieces[6] = makeBoardPiece(6);
	Rectangle divider1 = makeVertRectangle();
	pieces[7] = makeBoardPiece(7);
	Rectangle divider2 = makeVertRectangle();
	pieces[8] = makeBoardPiece(8);
	hbox.getChildren().addAll(pieces[6], divider1, pieces[7], divider2, pieces[8]);
	return hbox;
    } // makeRowThree
    
    private BoardPiece makeBoardPiece(int num){
	int row, col;
	Tile[] tiles = new Tile[9];
	int index = 0;
	for(int i = 0; i < 3; i++){
	    row = i + (3 * (num / 3));
	    for(int j = 0; j < 3; j++){
		col = j + (3 * (num % 3));
		tiles[index] = gameData[row][col];
		index++;
	    } // for
	} // for
	BoardPiece bp = new BoardPiece(tiles);
	return bp;
    } // makeBoardPiece
    
    private Tile[][][] makePremadeBoards(){
	Tile[][][] premade = new Tile[4][9][9];
	int[][] ints = {
	    {7, 3, 5, 6, 1, 4, 8, 9, 2},
	    {8, 4, 2, 9, 7, 3, 5, 6, 1},
	    {9, 6, 1, 2, 8, 5, 3, 7, 4},
	    {2, 8, 6, 3, 4, 9, 1, 5, 7},
	    {4, 1, 3, 8, 5, 7, 9, 2, 6},
	    {5, 7, 9, 1, 2, 6, 4, 3, 8},
	    {1, 5, 7, 4, 9, 2, 6, 8, 3},
	    {6, 9, 4, 7, 3, 8, 2, 1, 5},
	    {3, 2, 8, 5, 6, 1, 7, 4, 9},
	};
	int[][] ints2 = {
	    {2, 9, 5, 7, 4, 3, 8, 6, 1},
	    {4, 3, 1, 8, 6, 5, 9, 2, 7},
	    {8, 7, 6, 1, 9, 2, 5, 4, 3},
	    {3, 8, 7, 4, 5, 9, 2, 1, 6},
	    {6, 1, 2, 3, 8, 7, 4, 9, 5},
	    {5, 4, 9, 2, 1, 6, 7, 3, 8},
	    {7, 6, 3, 5, 2, 4, 1, 8, 9},
	    {9, 2, 8, 6, 7, 1, 3, 5, 4},
	    {1, 5, 4, 9, 3, 8, 6, 7, 2},
	};
	int[][] ints3 = {
	    {1, 5, 4, 8, 7, 3, 2, 9, 6},
	    {3, 8, 6, 5, 9, 2, 7, 1, 4},
	    {7, 2, 9, 6, 4, 1, 8, 3, 5},
	    {8, 6, 3, 7, 2, 5, 1, 4, 9},
	    {9, 7, 5, 3, 1, 4, 6, 2, 8},
	    {4, 1, 2, 9, 6, 8, 3, 5, 7},
	    {6, 3, 1, 4, 5, 7, 9, 8, 2},
	    {5, 9, 8, 2, 3, 6, 4, 7, 1},
	    {2, 4, 7, 1, 8, 9, 5, 6, 3},
	    };
	int[][] ints4 = {
	    {5, 6, 3, 2, 1, 9, 8, 4, 7},
	    {7, 1, 8, 4, 5, 3, 9, 2, 6},
	    {2, 9, 4, 6, 7, 8, 3, 1, 5},
	    {1, 2, 5, 7, 9, 6, 4, 3, 8},
	    {6, 8, 7, 3, 4, 2, 1, 5, 9},
	    {3, 4, 9, 1, 8, 5, 7, 6, 2},
	    {4, 5, 1, 8, 2, 7, 6, 9, 3},
	    {9, 7, 6, 5, 3, 1, 2, 8, 4},
	    {8, 3, 2, 9, 6, 4, 5, 7, 1},
	};
	return intsToTiles(ints, ints2, ints3, ints4);
    } // makePremadeBoards
    
    private Tile[][][] intsToTiles(int[][] i, int[][] i2, int[][] i3, int[][] i4){
	Tile[][][] tiles = new Tile[4][9][9];
	for(int row = 0; row < tiles[0].length; row++){
	    for(int col = 0; col < tiles[0][row].length; col++){
		tiles[0][row][col] = new Tile(i[row][col], this);
	    } // for
	} // for
	for(int row = 0; row < tiles[1].length; row++){
	    for(int col = 0; col < tiles[1][row].length; col++){
		tiles[1][row][col] = new Tile(i2[row][col], this);
	    } // for
	} // for
	for(int row = 0; row < tiles[2].length; row++){
	    for(int col = 0; col < tiles[2][row].length; col++){
		tiles[2][row][col] = new Tile(i3[row][col], this);
	    } // for
	} // for
	for(int row = 0; row < tiles[3].length; row++){
	    for(int col = 0; col < tiles[3][row].length; col++){
		tiles[3][row][col] = new Tile(i4[row][col], this);
	    } // for
	} // for
	return tiles;
    } // intsToTiles

    private TextArea makeTextArea(){
	makeInformation();
	information += "Score: " + (level - 1);
	TextArea textArea = new TextArea(this.information);
	textArea.setEditable(false);
	return textArea;
    } // makeTextField
    
    private void makeInformation(){
	information = "Welcome to Sudoku!\n\n\n";
	information += "Controls:\n\n";
	information += "To control the game, click on a number that does not have the symbol";
	information += " '-' \naround it!\n\n";
	information += "These numbers are given to you, and cannot be changed.\n\n";
	information += "Initially, the numbers you can edit will look like a - symbol,";
	information += " but these can be\ntranformed into a 1 by clicking on them!\n\n";
	information += "To go to a number you passed, click the number multiple times";
	information += " until the\nnumber loops around from 9 to 1!\n\n\n\n";
	information += "How To Play:\n\n";
	information += "The goal of Sudoku is to create a grid that fits the following\n";
	information += "conditions:\n\n";
	information += "1.) Each individual square must only have one of each number\n";
	information += "     from 1 to 9. The individual squares are noted by the bold\n";
	information += "     lines surrounding them.\n\n";
	information += "2.) Each column must only have one of each number from 1 to 9.\n\n";
	information += "3.) Each row must only have one of number from 1 to 9.\n\n";
	information += "When these conditions are met, you have won! An alert will be\n";
	information += "displayed upon vitory, and a new level will appear.\n\n\n\n";
	information += "NOTE: The first level is very easy. The levels after the first are \n";
	information += "the difficultly more commonly expected when playing Sudoku.\n\n\n";
    } // makeInformation
    
} // SudokuGame
