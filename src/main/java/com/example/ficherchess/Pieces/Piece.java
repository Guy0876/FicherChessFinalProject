package com.example.ficherchess.Pieces;

public abstract class Piece {
    protected long bitboard;

    public Piece(long bitboard) {
        this.bitboard = bitboard;
    }

    public long getBitboard() {
        return bitboard;
    }

    public void setBitboard(long bitboard) {
        this.bitboard = bitboard;
    }

    public abstract long possibleMoves(long position);
}
