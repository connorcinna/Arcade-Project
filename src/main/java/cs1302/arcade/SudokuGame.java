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

public class SudokuGame implements Playable {
    
    private Stage stage;
    private Tile[][] gameData;
    private Tile[][][] premadeBoards;
    private BoardPiece[] pieces;
    
    public void play() {
	premadeBoards = makePremadeBoards();
	//gameData = createGameData();
	gameData = premadeBoards[0];
	pieces = new BoardPiece[9];
	HBox row1 = makeRowOne();
	Rectangle divider1 = makeHorizRectangle();
	Rectangle divider2 = makeHorizRectangle();
	HBox row2 = makeRowTwo();
	HBox row3 = makeRowThree();
	VBox vbox = new VBox();
	vbox.getChildren().addAll(row1, divider1, row2, divider2, row3);
	stage = new Stage();
	Scene scene = new Scene(vbox, Color.BLACK);
	stage.setScene(scene);
	stage.setTitle("Sudoku!");
	stage.sizeToScene();
	stage.show();	
    } // play

    private Tile[][] createGameData(){
	Tile[][] tiles = new Tile[9][9];
	for(int i = 0; i < 9; i++){
	    for(int j = 0; j < 9; j++){
		tiles[i][j] = new Tile(0, this);
	    } // for
	} // for
	return tiles;
    } // createGameData
    
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
		System.out.println("Issues in BoardPiece solving"); // DEBUGGGGGGGGGGGGGG
		return false;
	    } // if
	} // for
	for(int i = 0; i < gameData.length; i++){
	    if(rowSolved(i) == false){
		System.out.println("Issue in RowSolved"); // DEBUG
		return false;
	    } // if
	    if(columnSolved(i) == false){
		System.out.println("Issue in colSolved"); // DEBUG
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
	    if(oneFound){
		if(tiles[i - 1].getNum() == i){
		    return false;
		} // if
	    } // if
	    if(!oneFound){
		if(tiles[i].getNum() == i){
		    oneFound = true;
		} // if
	    } // if
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
	    if(oneFound){
                if(tiles[i - 1].getNum() == i){
		    return false;
                } // if
	    } // if 
            if(!oneFound){
                if(tiles[i].getNum() == i){
		    oneFound = true;
                } // if
	    } // if   
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
	int[][] intArr = {
	    {7, 3, 5, 6, 1, 4, 8, 9, 2},
	    {8, 4, 2, 9, 7, 3, 5, 6, 1},
	    {9, 6, 1, 2, 8, 5, 3, 7, 4},
	    {2, 8, 6, 3, 4, 9, 1, 5, 7},
	    {4, 1, 3, 8, 5, 7, 9, 2, 6},
	    {5, 7, 9, 1, 2, 6, 4, 3, 8},
	    {1, 5, 7, 4, 9, 2, 6, 8, 3},
	    {6, 9, 4, 7, 3, 8, 2, 1, 5},
	    {0, 2, 8, 5, 6, 1, 7, 4, 9},
	};
	Tile tile;
	for(int i = 0; i < premade[0].length; i++){
	    for(int j = 0; j < premade[0][i].length; j++){
		tile = new Tile(intArr[i][j], this);
		premade[0][i][j] = tile;
	    } // for
	} // for
	
	/*
	  try{
	  File board1 = new File("cs1302/arcade/boards/board1.txt");
	    Scanner scan = new Scanner(board1);
	    Tile t;
	    for(int i = 0; i < 9; i++){
		for(int j = 0; j < 9; j++){
		    t = new Tile(Integer.parseInt(scan.nextLine()), this);
		    premade[0][i][j] = t;
		} // for
	    } // for
	} catch(Exception e){
	    System.out.println("Something went wrong!");
	} // catch
	    */
	return premade;
    } // makePremadeBoards
    
} // SudokuGame
