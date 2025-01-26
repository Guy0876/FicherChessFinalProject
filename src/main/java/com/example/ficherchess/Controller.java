package com.example.ficherchess;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Controller implements IView {
    @FXML
    private GridPane chessBoard;

    private ImageView selectedPiece;
    private int selectedRow;
    private int selectedCol;
    private Presenter presenter;

    public void initialize() {
        presenter = new Presenter(this);

        // Add white pieces
        addPiece("white-rook.png", 0, 0);
        addPiece("white-knight.png", 0, 1);
        addPiece("white-bishop.png", 0, 2);
        addPiece("white-queen.png", 0, 3);
        addPiece("white-king.png", 0, 4);
        addPiece("white-bishop.png", 0, 5);
        addPiece("white-knight.png", 0, 6);
        addPiece("white-rook.png", 0, 7);
        for (int i = 0; i < 8; i++) {
            addPiece("white-pawn.png", 1, i);
        }

        // Add black pieces
        addPiece("black-rook.png", 7, 0);
        addPiece("black-knight.png", 7, 1);
        addPiece("black-bishop.png", 7, 2);
        addPiece("black-queen.png", 7, 3);
        addPiece("black-king.png", 7, 4);
        addPiece("black-bishop.png", 7, 5);
        addPiece("black-knight.png", 7, 6);
        addPiece("black-rook.png", 7, 7);
        for (int i = 0; i < 8; i++) {
            addPiece("black-pawn.png", 6, i);
        }
    }

    private void addPiece(String imageName, int row, int col) {
        Image pieceImage = new Image(getClass().getResourceAsStream("/pieces/" + imageName));
        ImageView imageView = new ImageView(pieceImage);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setOnMouseClicked(this::handlePieceClick);
        chessBoard.add(imageView, col, row);
    }

    private void handlePieceClick(MouseEvent event) {
        selectedPiece = (ImageView) event.getSource();
        selectedRow = GridPane.getRowIndex(selectedPiece);
        selectedCol = GridPane.getColumnIndex(selectedPiece);
        if (selectedPiece != null) {
            presenter.handlePieceSelection(selectedRow, selectedCol);
        }
    }

    @FXML
    private void handleSquareClick(MouseEvent event) {
        if (selectedPiece != null) {
            StackPane square = (StackPane) event.getSource();
            int newRow = GridPane.getRowIndex(square);
            int newCol = GridPane.getColumnIndex(square);

            presenter.handlePieceMove(selectedRow, selectedCol, newRow, newCol);

            selectedPiece = null;
        }
    }

    @Override
    public void movePiece(int oldRow, int oldCol, int newRow, int newCol) {
        ImageView piece = (ImageView) getNodeByRowColumnIndex(oldRow, oldCol, chessBoard);
        if (piece != null) {
            chessBoard.getChildren().remove(piece);
            chessBoard.add(piece, newCol, newRow);
        }
    }

    private ImageView getNodeByRowColumnIndex(int row, int col, GridPane gridPane) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return (ImageView) node;
            }
        }
        return null;
    }
}
