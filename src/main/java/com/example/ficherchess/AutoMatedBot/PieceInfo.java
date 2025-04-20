package com.example.ficherchess.AutoMatedBot;

import com.example.ficherchess.Pieces.Piece;

public class PieceInfo {
    private Piece piece;
    private String action;
    private double evaluation;

    public PieceInfo(Piece piece, String action, double evaluation) {
        this.piece = piece;
        this.action = action;
        this.evaluation = evaluation;
    }

    public Piece getPiece() {
        return piece;
    }

    public String getAction() {
        return action;
    }

    public double getEvaluation() {
        return evaluation;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }
}
