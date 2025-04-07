package com.example.ficherchess;

import com.example.ficherchess.Pieces.Piece;

public class Presenter {
    private IView view;
    private Model model;

    public Presenter(IView view, FischerRandomChess fischerRandomChess) {
        this.view = view;
        this.model = new Model(fischerRandomChess);
    }

    public void handlePieceSelection(int row, int col) {
        long possibleMoves = model.setSelectedPiece(row, col);
        view.highlightPossibleMoves(possibleMoves);
    }

    public void handlePieceMove(int oldRow, int oldCol, int newRow, int newCol) {
        if (model.isLegalMove(oldRow, oldCol, newRow, newCol)) {
            view.movePiece(oldRow, oldCol, newRow, newCol);
            if(Piece.check)
                Piece.check = false;
        }
    }
}