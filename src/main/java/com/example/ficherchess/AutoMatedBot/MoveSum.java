package com.example.ficherchess.AutoMatedBot;

public class MoveSum {
    private int [] move;
    private double score;

    public MoveSum(int [] move, double score) {
        this.move = move;
        this.score = score;
    }

    public int[] getMove() {
        return move;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
