package com.example.ficherchess;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class Controller implements IView {
    @FXML
    private GridPane chessBoard;

    private ImageView selectedPiece;
    private int selectedRow;
    private int selectedCol;
    private Presenter presenter;
    private Circle selectionHalo;

    public void initialize() {
        presenter = new Presenter(this);

        // Add white pieces
        addPiece("white-rook.png", 7, 0);
        addPiece("white-knight.png", 7, 1);
        addPiece("white-bishop.png", 7, 2);
        addPiece("white-queen.png", 7, 3);
        addPiece("white-king.png", 7, 4);
        addPiece("white-bishop.png", 7, 5);
        addPiece("white-knight.png", 7, 6);
        addPiece("white-rook.png", 7, 7);
        for (int i = 0; i < 8; i++) {
            addPiece("white-pawn.png", 6, i);
        }

        // Add black pieces
        addPiece("black-rook.png", 0, 0);
        addPiece("black-knight.png", 0, 1);
        addPiece("black-bishop.png", 0, 2);
        addPiece("black-queen.png", 0, 3);
        addPiece("black-king.png", 0, 4);
        addPiece("black-bishop.png", 0, 5);
        addPiece("black-knight.png", 0, 6);
        addPiece("black-rook.png", 0, 7);
        for (int i = 0; i < 8; i++) {
            addPiece("black-pawn.png", 1, i);
        }

        selectionHalo = new Circle(25, Color.TRANSPARENT);
        selectionHalo.setStroke(Color.RED);
        selectionHalo.setStrokeWidth(3);
    }

    private void addPiece(String imageName, int row, int col) {
        String imagePath = "/pieces/" + imageName;
        Image pieceImage = new Image(getClass().getResourceAsStream(imagePath));
        if (pieceImage.isError()) {
            System.err.println("Error loading image: " + imagePath);
            return;
        }
        ImageView imageView = new ImageView(pieceImage);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(imageView);
        stackPane.setOnMouseClicked(this::handleSquareClick);
        chessBoard.add(stackPane, col, row);
    }

    @FXML
    private void handleSquareClick(MouseEvent event) {
        StackPane square = (StackPane) event.getSource();
        int row = GridPane.getRowIndex(square);
        int col = GridPane.getColumnIndex(square);

        if (square.getChildren().size() > 0 && square.getChildren().get(0) instanceof ImageView) {
            // Clicked on a piece
            selectedPiece = (ImageView) square.getChildren().get(0);
            selectedRow = row;
            selectedCol = col;
            presenter.handlePieceSelection(selectedRow, selectedCol);
            addSelectionHalo(selectedRow, selectedCol);
        } else if (selectedPiece != null) {
            // Clicked on an empty square
            presenter.handlePieceMove(selectedRow, selectedCol, row, col);
            removeSelectionHalo();
            selectedPiece = null;
        }
    }

    private void addSelectionHalo(int row, int col) {
        StackPane square = (StackPane) getNodeByRowColumnIndex(row, col, chessBoard);
        if (square != null) {
            square.getChildren().add(0, selectionHalo);
        }
    }

    private void removeSelectionHalo() {
        StackPane square = (StackPane) getNodeByRowColumnIndex(selectedRow, selectedCol, chessBoard);
        if (square != null) {
            square.getChildren().remove(selectionHalo);
        }
    }

    @Override
    public void movePiece(int oldRow, int oldCol, int newRow, int newCol) {
        StackPane oldSquare = getNodeByRowColumnIndex(oldRow, oldCol, chessBoard);
        StackPane newSquare = getNodeByRowColumnIndex(newRow, newCol, chessBoard);
        if (oldSquare != null && newSquare != null) {
            ImageView piece = (ImageView) oldSquare.getChildren().get(0);
            oldSquare.getChildren().remove(piece);
            newSquare.getChildren().add(piece);
        }
    }

    private StackPane getNodeByRowColumnIndex(int row, int col, GridPane gridPane) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return (StackPane) node;
            }
        }
        return null;
    }
}
