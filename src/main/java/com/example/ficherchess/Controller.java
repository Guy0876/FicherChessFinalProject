package com.example.ficherchess;

import com.example.ficherchess.Pieces.King;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private boolean isWhiteMove = true;

    public void initialize() {
        FischerRandomChess fischerRandomChess = new FischerRandomChess();
        presenter = new Presenter(this, fischerRandomChess);

        char[] startingPosition = fischerRandomChess.getStartingPosition();

        // Add white pieces
        for (int i = 0; i < 8; i++) {
            addPiece(getImageName(startingPosition[i], true), 7, i);
            addPiece("white-pawn.png", 6, i);
        }

        // Add black pieces
        for (int i = 0; i < 8; i++) {
            addPiece(getImageName(startingPosition[i], false), 0, i);
            addPiece("black-pawn.png", 1, i);
        }

        selectionHalo = new Circle(25, Color.TRANSPARENT);
        selectionHalo.setStroke(Color.RED);
        selectionHalo.setStrokeWidth(3);
    }

    private String getImageName(char piece, boolean isWhite) {
        String colorPrefix = isWhite ? "white" : "black";
        switch (piece) {
            case 'R':
                return colorPrefix + "-rook.png";
            case 'N':
                return colorPrefix + "-knight.png";
            case 'B':
                return colorPrefix + "-bishop.png";
            case 'Q':
                return colorPrefix + "-queen.png";
            case 'K':
                return colorPrefix + "-king.png";
            default:
                return "";
        }
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
        imageView.setUserData(imagePath); // Store the imagePath in userData

        StackPane stackPane = getNodeByRowColumnIndex(row, col, chessBoard);
        if (stackPane != null) {
            stackPane.getChildren().add(imageView);
        } else {
            System.err.println("No StackPane found at row: " + row + ", col: " + col);
        }
    }

    @FXML
    private void handleSquareClick(MouseEvent event) {
        StackPane square = (StackPane) event.getSource();
        Integer row = GridPane.getRowIndex(square);
        Integer col = GridPane.getColumnIndex(square);

        if (row == null || col == null) {
            return;
        }
        if (selectedPiece != null) {
            // Clicked on an empty square
            if(!isColor()) {
                return;
            }
            presenter.handlePieceMove(selectedRow, selectedCol, row, col);
            removeSelectionHalo();
            removePossibleMoveHighlights();
            selectedPiece = null;
        } else if (square.getChildren().size() > 1 && square.getChildren().get(1) instanceof ImageView) {
            // Clicked on a piece
            selectedPiece = (ImageView) square.getChildren().get(1);
            if (!isColor()) {
                selectedPiece = null;
                return;
            }
            selectedRow = row;
            selectedCol = col;
            presenter.handlePieceSelection(selectedRow, selectedCol);
            addSelectionHalo(selectedRow, selectedCol);
        }
    }

    private boolean isColor(){
        if (isWhiteMove) {
            if (selectedPiece.getUserData().toString().contains("black")) {return false;}
        } else {
            if (selectedPiece.getUserData().toString().contains("white")) {return false;}
        }
        return true;
    }

    private void addSelectionHalo(int row, int col) {
        StackPane square = getNodeByRowColumnIndex(row, col, chessBoard);
        if (square != null) {
            square.getChildren().add(0, selectionHalo);
        }
    }

    private void removeSelectionHalo() {
        StackPane square = getNodeByRowColumnIndex(selectedRow, selectedCol, chessBoard);
        if (square != null) {
            square.getChildren().remove(selectionHalo);
        }
    }

    @Override
    public void movePiece(int oldRow, int oldCol, int newRow, int newCol) {
        System.out.println("movePiece called: " + oldRow + "," + oldCol + " to " + newRow + "," + newCol);
        StackPane oldSquare = getNodeByRowColumnIndex(oldRow, oldCol, chessBoard);
        StackPane newSquare = getNodeByRowColumnIndex(newRow, newCol, chessBoard);
        if (oldSquare != null && newSquare != null) {
            System.out.println("Old square and new square found.");
            ImageView piece = null;
            boolean castle = true;
            boolean white = false;
            for (javafx.scene.Node node : oldSquare.getChildren()) {
                if (node instanceof ImageView) {
                    piece = (ImageView) node;
                    break;
                }
            }
            if (piece != null) {
                System.out.println("Piece found and moved.");
                oldSquare.getChildren().remove(piece);
                String piecePath = (String) piece.getUserData(); // Retrieve the imagePath from userData
                if (piecePath != null && !piecePath.contains("king")) {
                    castle = false;
                }
                // Remove any existing ImageView in the new square
                ImageView existingPiece = null;
                for (javafx.scene.Node node : newSquare.getChildren()) {
                    if (node instanceof ImageView) {
                        existingPiece = (ImageView) node;
                        break;
                    }
                }
                if (existingPiece != null) {
                    newSquare.getChildren().remove(existingPiece);
                    if (castle) {
                        String existingPiecePath = (String) existingPiece.getUserData(); // Retrieve the imagePath from userData
                        if (existingPiecePath != null && !existingPiecePath.contains("rook")) {
                            castle = false;
                        }
                        if (existingPiecePath != null && existingPiecePath.contains("white")) {
                            if (piecePath == null || !piecePath.contains("white")) {
                                castle = false;
                            }
                            white = true;
                        } else {
                            if (piecePath == null || !piecePath.contains("black")) {
                                castle = false;
                            }
                        }
                    }
                }
                else {
                    castle = false;
                    if(piece.getUserData().toString().contains("pawn") && newCol != oldCol){
                        int enPassantRow = isWhiteMove ? newRow + 1 : newRow - 1;
                        StackPane enPassantSquare = getNodeByRowColumnIndex(enPassantRow, newCol, chessBoard);
                        for (javafx.scene.Node node : enPassantSquare.getChildren()) {
                            if (node instanceof ImageView) {
                                existingPiece = (ImageView) node;
                                break;
                            }
                        }
                        enPassantSquare.getChildren().remove(existingPiece);
                    }
                }
                if (!castle) {
                    newSquare.getChildren().add(piece);
                } else {
                    if (white) {
                        if (oldCol < newCol) {
                            StackPane rookSquare = getNodeByRowColumnIndex(7, 5, chessBoard);
                            rookSquare.getChildren().add(existingPiece);
                            StackPane kingSquare = getNodeByRowColumnIndex(7, 6, chessBoard);
                            kingSquare.getChildren().add(piece);
                        } else {
                            StackPane rookSquare = getNodeByRowColumnIndex(7, 3, chessBoard);
                            rookSquare.getChildren().add(existingPiece);
                            StackPane kingSquare = getNodeByRowColumnIndex(7, 2, chessBoard);
                            kingSquare.getChildren().add(piece);
                        }
                    } else {
                        if (oldCol < newCol) {
                            StackPane rookSquare = getNodeByRowColumnIndex(0, 5, chessBoard);
                            rookSquare.getChildren().add(existingPiece);
                            StackPane kingSquare = getNodeByRowColumnIndex(0, 6, chessBoard);
                            kingSquare.getChildren().add(piece);
                        } else {
                            StackPane rookSquare = getNodeByRowColumnIndex(0, 3, chessBoard);
                            rookSquare.getChildren().add(existingPiece);
                            StackPane kingSquare = getNodeByRowColumnIndex(0, 2, chessBoard);
                            kingSquare.getChildren().add(piece);
                        }
                    }
                }
                isWhiteMove = !isWhiteMove;
                removePossibleMoveHighlights();
            } else {
                System.out.println("No piece found in the old square.");
            }
        } else {
            System.out.println("Old square or new square not found.");
        }
    }

    private StackPane getNodeByRowColumnIndex(int row, int col, GridPane gridPane) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            Integer nodeRow = GridPane.getRowIndex(node);
            Integer nodeCol = GridPane.getColumnIndex(node);
            if (nodeRow != null && nodeCol != null && nodeRow == row && nodeCol == col) {
                return (StackPane) node;
            }
        }
        return null;
    }

    @Override
    public void highlightPossibleMoves(long possibleMoves) {
        for (int i = 0; i < 64; i++) {
            long movePosition = 1L << i;
            if ((possibleMoves & movePosition) != 0) {
                int moveRow = i / 8;
                int moveCol = i % 8;
                StackPane square = getNodeByRowColumnIndex(moveRow, moveCol, chessBoard);
                if (square != null) {
                    Circle moveHighlight = new Circle(10, Color.YELLOW);
                    moveHighlight.setOpacity(0.5);
                    square.getChildren().add(moveHighlight);
                }
            }
        }
    }

    private void removePossibleMoveHighlights() {
        for (javafx.scene.Node node : chessBoard.getChildren()) {
            if (node instanceof StackPane) {
                StackPane square = (StackPane) node;
                square.getChildren().removeIf(child -> child instanceof Circle && ((Circle) child).getOpacity() == 0.5);
            }
        }
    }

    @Override
    public void showGameOverScene(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
            // Optionally, exit or reset the game:
            // System.exit(0);
            // or call a restart method
        });
    }
}