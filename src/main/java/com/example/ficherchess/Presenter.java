package com.example.ficherchess;

public class Presenter {
    private IView view;

    public Presenter(IView view) {
        this.view = view;
    }

    public void handlePieceSelection(int row, int col) {
        // Handle piece selection logic
    }

    public void handlePieceMove(int oldRow, int oldCol, int newRow, int newCol) {
        // Handle piece move logic
        view.movePiece(oldRow, oldCol, newRow, newCol);
    }
}
