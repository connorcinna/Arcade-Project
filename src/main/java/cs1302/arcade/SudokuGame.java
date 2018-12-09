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

public class SudokuGame implements Playable {
    
    private Stage stage;
    private Tile[][] fullGameData;
    private Tile[][][] premadeBoards;
    
    public void play() {

	premadeBoards = makePremadeBoards();
	fullGameData = createGameData();
	HBox row1 = makeRowOne();
	HBox row2 = makeRowTwo();
	HBox row3 = makeRowThree();
	VBox vbox = new VBox();
	vbox.getChildren().addAll(row1, row2, row3);
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
		tiles[i][j] = new Tile(0);
	    } // for
	} // for
	return tiles;
    } // createGameData

    private HBox makeRowOne(){
	HBox hbox = new HBox();
	BoardPiece bp0 = makeBoardPiece(0);
	BoardPiece bp1 = makeBoardPiece(1);
	BoardPiece bp2 = makeBoardPiece(2);
	hbox.getChildren().addAll(bp0, bp1, bp2);
	return hbox;
    } // makeRowOne

    private HBox makeRowTwo(){
	HBox hbox = new HBox();
	BoardPiece bp3 = makeBoardPiece(3);
	BoardPiece bp4 = makeBoardPiece(4);
	BoardPiece bp5 = makeBoardPiece(5);
	hbox.getChildren().addAll(bp3, bp4, bp5);
	return hbox;
    } // makeRowTwo

    private HBox makeRowThree(){
	HBox hbox = new HBox();
	BoardPiece bp6 = makeBoardPiece(6);
	BoardPiece bp7 = makeBoardPiece(7);
	BoardPiece bp8 = makeBoardPiece(8);
	hbox.getChildren().addAll(bp6, bp7, bp8);
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
		tiles[index] = fullGameData[row][col];
		index++;
	    } // for
	} // for
	BoardPiece bp = new BoardPiece(tiles);
	return bp;
    } // makeBoardPiece
    
    private Tile[][][] makePremadeBoards(){
	Tile[][][] premade = new Tile[4][9][9];
	try{
	    File board1 = new File("cs1302/arcade/boards/board1.txt");
	    Scanner scan = new Scanner(board1);
	    Tile t;
	    for(int i = 0; i < 9; i++){
		for(int j = 0; j < 9; j++){
		    t = new Tile(Integer.parseInt(scan.nextLine()));
		    premade[0][i][j] = t;
		} // for
	    } // for
	} catch(Exception e){
	    System.out.println("Something went wrong!");
	} // catch
	return premade;
    } // makePremadeBoards
    
} // SudokuGame
