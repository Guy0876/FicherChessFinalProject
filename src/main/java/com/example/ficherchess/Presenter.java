package com.example.ficherchess;

import com.example.ficherchess.AutoMatedBot.AutomatedBot;
import com.example.ficherchess.Pieces.Piece;

public class Presenter {
    private IView view;
    private Model model;
    private AutomatedBot automatedBot;

    public Presenter(IView view, FischerRandomChess fischerRandomChess) {
        this.view = view;
        this.model = new Model(fischerRandomChess);
        this.automatedBot = new AutomatedBot(model);
    }

    public void handlePieceSelection(int row, int col) {
        long possibleMoves = model.setSelectedPiece(row, col);
        view.highlightPossibleMoves(possibleMoves);
    }

    public void handlePieceMove(int oldRow, int oldCol, int newRow, int newCol) {
        if (model.isLegalMove(oldRow, oldCol, newRow, newCol)) {
            view.movePiece(oldRow, oldCol, newRow, newCol);
            if(checkGameState())
                return; // Check for checkmate or stalemate after the player's move
            int[] move = automatedBot.makeBestMove();
            view.movePiece(move[0], move[1], move[2], move[3]);
            checkGameState(); // Check for checkmate or stalemate after the bot's move
        }
    }

    private boolean checkGameState() {
        boolean isWhiteTurn = model.isWhiteTurn();
        if (model.isCheckmate(isWhiteTurn)) {
            if (Piece.check) {
                view.showGameOverScene(isWhiteTurn ? "Black wins by checkmate!" : "White wins by checkmate!");
            } else { // Stalemate condition
                view.showGameOverScene("Game ends in stalemate!");
            }
            return true;
        }
        return false;
    }
}