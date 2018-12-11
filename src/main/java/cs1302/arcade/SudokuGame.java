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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/** 
 * An application that displays an interactable
 * grid that is clickable for the game of Sudoku
 * to be played. There are 16 puzzles included,
 * where the difficulty is based on the number of
 * blank spaces. The difficulty is easy for the
 * first puzzle, but quickly increases. The numbers
 * are adjusted through a mouse click.
 *
 * @author Matthew Gebara, Connor Cummings
 */
public class SudokuGame implements Playable {
    
    private Stage stage;
    private Scene scene;
    private Tile[][] gameData;
    private Tile[][][] premadeBoards;
    private BoardPiece[] pieces;
    private int level;
    private String information;

    /**
     * Manages the GUI for the Sudoku Game.
     * The default level is level 1.
     */
    public void play(){
	play(1);
    } // play

    /**
     * Manages the GUI for the Sudoku Gane,
     * where the level of gameplay is taken
     * in as a parameter.
     *
     * @param lvl What level this sudoku board will be.
     */
    public void play(int lvl) {
	premadeBoards = makePremadeBoards();
	level = lvl;
	gameData = createGameData();
	pieces = new BoardPiece[9];
	HBox row1 = makeRowOne();
	// These dividers go between the rows
	Rectangle divider1 = makeHorizRectangle();
	Rectangle divider2 = makeHorizRectangle();
	HBox row2 = makeRowTwo();
	HBox row3 = makeRowThree();
	// This VBox holds the rows and dividers
	VBox vbox = new VBox();
	vbox.getChildren().addAll(row1, divider1, row2, divider2, row3);
	TextArea textArea = makeTextArea();
	// This HBox allows for the TextField with information to be displayed
	// alongside the Sudoku Gamne
	HBox bigBox = new HBox();
	bigBox.getChildren().addAll(vbox, textArea);
	scene = new Scene(bigBox, Color.BLACK);
	stage = new Stage();
	stage.setScene(scene);
	stage.setTitle("Sudoku!");
	stage.sizeToScene();
	stage.show();
    } // play

    /**
     * Creates the game data used when creating and managing
     * the Sudoku board.
     *
     * @return Array holding game data in the form of Tile objects.
     */
    private Tile[][] createGameData(){
	Tile[][] tiles = new Tile[9][9];
	tiles = pickRandomBoard(); // This picks a random Sudoku puzzle for the user to solve
	if(level == 1){
	    tiles = loseSquares(tiles, 3); // If the level is 1, the difficulty is very low
	} // if
	else{
	    tiles = loseSquares(tiles, 60); // Beyond level 1, the difficulty is normal
	} // else
	return tiles;
    } // createGameData

    /**
     * Returns a random game board from the premade boards.
     *
     * @return Array holding random game board information.
     */
    private Tile[][] pickRandomBoard(){
	Tile[][] tiles;
	// This number will be which board is selected from the premadeBoards array
	int boardChoice = (int) (Math.random() * 4); 
	tiles = premadeBoards[boardChoice];
	tiles = mixBoard(tiles);
	return tiles;
    } // pickRandomBoard

    /**
     * Takes in game board and returns a version of the game board
     * with a specified number of positions turned into blank spots
     * that the player can change the number of. 
     *
     * @param tiles Game board to take spots away from.
     * @param numLost The number of spaces to take away from the game board.
     * @return Game board with missing spots.
     */
    private Tile[][] loseSquares(Tile[][] tiles, int numLost){
	int randomX;
	int randomY;
	// loop iterates as many times as there are tiles that need to be lost
	for(int i = 0; i < numLost; i++){
	    randomX = (int) (Math.random() * 9);
	    randomY = (int) (Math.random() * 9);
	    tiles[randomX][randomY].setZero();
	} // for
	return tiles;
    } // loseSquares

    /**
     * Rotates a game board clockwise a random number of times
     * to increase the number of playable game boards.
     *
     * @param board The game board to be rotated.
     * @return The rotated game board.
     */
    private Tile[][] mixBoard(Tile[][] board){
	// Defines how many times the board should be rotated clockwise
	int iterations = (int) (Math.random() * 4);
	for(int i = 0; i < iterations; i++){
	    board = clockwiseTurn(board);
	} // for
	return board;
    } // mixBoard

    /**
     * Method to rotate a game in the clockwise direction
     * once.
     *
     * @param board The game board to be rotated
     * @return The rotated game board
     */ 
    private Tile[][] clockwiseTurn(Tile[][] board){
	// This temp array will be filled in clockwise to the original board, then returned
	Tile[][] arr = new Tile[9][9];
	for(int r = 0; r < arr.length; r++){
	    for(int c = 0; c < arr.length; c++){
		arr[c][arr.length - 1 - r] = board[r][c]; 
	    } // for
	} // for
	return arr;
    } // clockwiseTurn

    /**
     * Determines whether the game board is in a position
     * that satisfies the game requirements, and thus the
     * game is won. 
     *
     * @return True if the game requirements are satisfied
     */
    protected boolean isWon(){
	// This first checks if the board is empty or not, as if it is, it is not solved
	for(int i = 0; i < gameData.length; i++){
	    for(int j = 0; j < gameData[i].length; j++){
		if(gameData[i][j].getNum() == 0){
		    return false;
		} // if
	    } // for
	} // for
	// This checks if each box in the grid has one of each piece
	for(int i = 0; i < pieces.length; i++){
	    if(pieces[i].isSolved() == false){
		return false;
	    } // if
	} // for
	// This checks if each row and column have one of each number
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

    /**
     * Method that handles when the game is won by giving 
     * an alert to the player that they have beaten the puzzle,
     * then a new level of puzzle is presented.
     */
    protected void winState(){
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Victory!");
	alert.setHeaderText("You win!");
	alert.setContentText("You did it! Ready for another?");
	alert.showAndWait();
	stage.close(); // Closes the current stage
	this.play(level + 1); // Opens a new stage with a new game board
    } // winState

    /**
     * Checks if a given row number meets the qualifications
     * of being considered solved.
     *
     * @param row The row to be checked for whether it is solved.
     * @return boolean with whether the row is solved or not.
     */
    private boolean rowSolved(int row){
	Tile[] tiles = gameData[row]; // Stores the row to be checked
	boolean oneFound = false; // If this is not true, row is not solved
	for(int i = 1; i - 1 < tiles.length; i++){
	    oneFound = false;
	    for(int j = 0; j < tiles.length; j++){
		if(oneFound){ // if one is already found, there cannot be another
		    if(tiles[j].getNum() == i){
			return false; // If this is the case, already know the answer
		    } // if
		} // if
		if(!oneFound){
		    if(tiles[j].getNum() == i){
			oneFound = true; // This checks for the first instance of a number
		    } // if
		} // if
	    } // for
	    if(!oneFound){
		return false; // This would mean the check failed
	    } // if
	} // for
	return true; // If the loop ended without failing, then the row is solved
    } // rowSolved

    /**
     * Checks if a given column number meets the qualifications
     * of being considered solved.
     *
     * @param col The column to be checked for whether it is solved.
     * @return boolean with whether the col is solved or not.
     */
    private boolean columnSolved(int col){
	Tile[] tiles = new Tile[9]; // Stored the column to be checked
	for(int i = 0; i < tiles.length; i++){
	    tiles[i] = gameData[i][col];
	} // for
	boolean	oneFound = false; // If this is not true, column is not solved
	for(int i = 1; i - 1 < tiles.length; i++){
            oneFound = false;
	    for(int j = 0; j < tiles.length; j++){
		if(oneFound){ // if one is already found, there cannot be another
		    if(tiles[j].getNum() == i){
			return false; // if this is the case, already know the answer 
		    } // if
		} // if 
		if(!oneFound){
		    if(tiles[j].getNum() == i){
			oneFound = true; // this checks for the first instance of a number
		    } // if
		} // if
	    } // for
            if(!oneFound){
		return false; // This would mean the check failed
            } // if  
	} // for
        return true; // If the loop ended without failing, then the row is solved
    } // col

    /**
     * Makes a horizontal rectangle to be displayed
     * to visually seperate the different boxes in the
     * grid.
     *
     * @return Rectangle node to be added to the GUI.
     */
    private Rectangle makeHorizRectangle(){
	Rectangle r = new Rectangle();
	r.setHeight(10);
	r.setWidth(695);
	return r;
    } // makeHorizRectangle

    /**
     * Makes a vertical rectangle to be displayed
     * to visually seperate the different boxes in the
     * grid.
     *
     * @return Rectangle node to be added to the GUI.
     */
    private Rectangle makeVertRectangle(){
	Rectangle r = new Rectangle();
	r.setWidth(10);
	r.setHeight(225);
	return r;
    } // makeVertRectangle

    /**
     * Creates the first row of Boxes and Vertical
     * Rectangles in the GUI.
     *
     * @return HBox storing the first row of nodes in the GUI.
     */
    private HBox makeRowOne(){
	HBox hbox = new HBox(); // Stores the nodes of the first row
	pieces[0] = makeBoardPiece(0);
	Rectangle divider1 = makeVertRectangle();
	pieces[1] = makeBoardPiece(1);
	Rectangle divider2 = makeVertRectangle();
	pieces[2] = makeBoardPiece(2);
	hbox.getChildren().addAll(pieces[0], divider1, pieces[1], divider2, pieces[2]);
	return hbox;
    } // makeRowOne

    /**
     * Creates the second row of Boxes and Vertical
     * Rectangles in the GUI.
     *
     * @return HBox storing the second row of nodes in the GUI.
     */
    private HBox makeRowTwo(){
	HBox hbox = new HBox(); // Stores the nodes of the second row
	pieces[3] = makeBoardPiece(3);
	Rectangle divider1 = makeVertRectangle();
	pieces[4] = makeBoardPiece(4);
	Rectangle divider2 = makeVertRectangle();
	pieces[5] = makeBoardPiece(5);
	hbox.getChildren().addAll(pieces[3], divider1, pieces[4], divider2, pieces[5]);
	return hbox;
    } // makeRowTwo

    /**
     * Creates the third row of Boxes and Vertical
     * Rectangles in the GUI.
     *
     * @return HBox storing the third row of nodes in the GUI.
     */
    private HBox makeRowThree(){
	HBox hbox = new HBox(); // Stores the nodes of the third row
	pieces[6] = makeBoardPiece(6);
	Rectangle divider1 = makeVertRectangle();
	pieces[7] = makeBoardPiece(7);
	Rectangle divider2 = makeVertRectangle();
	pieces[8] = makeBoardPiece(8);
	hbox.getChildren().addAll(pieces[6], divider1, pieces[7], divider2, pieces[8]);
	return hbox;
    } // makeRowThree

    /**
     * Creates a BoardPiece node that represents a box
     * displayed in the grid. The number of BoardPiece is
     * determined by its location, where the top left is position 0,
     * then the position its right is position 1, where the piece
     * below position 0 is position 3. This pattern holds until the
     * bottom right piece, which is position 8.
     *
     * @param num The number of the BoardPiece to be created.
     * @return The BoardPiece to be added to the GUI.
     */
    private BoardPiece makeBoardPiece(int num){
	int row, col;
	Tile[] tiles = new Tile[9];
	int index = 0;
	for(int i = 0; i < 3; i++){
	    row = i + (3 * (num / 3)); // Allows for any board piece to be made in one loop
	    for(int j = 0; j < 3; j++){
		col = j + (3 * (num % 3));
		tiles[index] = gameData[row][col];
		index++;
	    } // for
	} // for
	BoardPiece bp = new BoardPiece(tiles);
	return bp;
    } // makeBoardPiece

    /**
     * Returns a storage array of the basic premade Sudoku puzzles before
     * they are modified and added to the game data.
     *
     * @return Array that stores possible premade game data.
     */
    private Tile[][][] makePremadeBoards(){
	Tile[][][] premade = new Tile[4][9][9];
	// int arrays used for readability
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
	// int arrays must be converted to Tile arrays 
	return intsToTiles(ints, ints2, ints3, ints4);
    } // makePremadeBoards

    /**
     * Converts a series of four integer array representations of game data
     * into an array of Tile object that can be displayed in the GUI.
     *
     * @param i The first integer array to be converted into a Tile array.
     * @param i2 The second integer array to be converted into a Tile array.
     * @param i3 The third integer array to be converted into a Tile array.
     * @param i4 The fourth integer array to be converted into a Tile array.
     * @return An array holding the four Tile arrays created from the integer arrays
     */
    private Tile[][][] intsToTiles(int[][] i, int[][] i2, int[][] i3, int[][] i4){
	Tile[][][] tiles = new Tile[4][9][9];
	for(int row = 0; row < tiles[0].length; row++){
	    for(int col = 0; col < tiles[0][row].length; col++){
		// A new tile object is made for each object in the integer array
		tiles[0][row][col] = new Tile(i[row][col], this);
	    } // for
	} // for
	for(int row = 0; row < tiles[1].length; row++){
	    for(int col = 0; col < tiles[1][row].length; col++){
		// A new tile object is made for each object in the integer array 
		tiles[1][row][col] = new Tile(i2[row][col], this);
	    } // for
	} // for
	for(int row = 0; row < tiles[2].length; row++){
	    for(int col = 0; col < tiles[2][row].length; col++){
		// A new tile object is made for each object in the integer array 
		tiles[2][row][col] = new Tile(i3[row][col], this);
	    } // for
	} // for
	for(int row = 0; row < tiles[3].length; row++){
	    for(int col = 0; col < tiles[3][row].length; col++){
		// A new tile object is made for each object in the integer array 
		tiles[3][row][col] = new Tile(i4[row][col], this);
	    } // for
	} // for
	return tiles;
    } // intsToTiles

    /**
     * Returns a TextArea object that displays information to the user
     * about the Sudoku Game, including how to play, the controls, score,
     * and similar information.
     *
     * @return Textfield with information for the user to best use the application.
     */
    private TextArea makeTextArea(){
	makeInformation();
	information += "Score: " + (level - 1); // Starts at level 1, but the score is 0.
	TextArea textArea = new TextArea(this.information);
	textArea.setEditable(false);
	return textArea;
    } // makeTextField

    /**
     * Defines the information String that is used within the TextArea object that
     * displays information to the end user about how to best use this Sudoku Game
     * Application such as how to play, the controls, and similar information.
     */
    private void makeInformation(){
	information = "Welcome to Sudoku!\n\n\n";
	information += "Controls:\n\n";
	information += "To control the game, click on a number that does not have the symbol";
	information += " '*' \naround it!\n\n";
	information += "These numbers are given to you, and cannot be changed.\n\n";
	information += "Initially, the numbers you can edit will be the  '-' symbol,";
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
	information += "displayed upon vitory, and a new puzzle will appear.\n\n";
	information += "One point is gained upon completing a puzzle.\n\n";
	information += "NOTE: The first level is very easy. The levels after the first are \n";
	information += "the difficultly more commonly expected when playing Sudoku.\n\n\n";
    } // makeInformation
    
} // SudokuGame
