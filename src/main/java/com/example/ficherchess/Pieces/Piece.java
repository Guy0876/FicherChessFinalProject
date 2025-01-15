package com.example.ficherchess.Pieces;

public abstract class Piece {
    protected long bitboard;
    protected long allPieces;

    public Piece(long bitboard, long allPieces) {
        this.bitboard = bitboard;
        this.allPieces = allPieces;
    }

    public long getAllPieces() {
        return allPieces;
    }

    public void setAllPieces(long allPieces) {
        this.allPieces = allPieces;
    }
    public long getBitboard() {
        return bitboard;
    }

    public void setBitboard(long bitboard) {
        this.bitboard = bitboard;
    }

    public abstract long possibleMoves(long position);
}
