package com.example.ficherchess;

public interface IView {
    void movePiece(int oldRow, int oldCol, int newRow, int newCol);
    void highlightPossibleMoves(long possibleMoves);
    void showGameOverScene(String message);
}
