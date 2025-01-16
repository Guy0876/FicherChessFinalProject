package com.example.ficherchess.Pieces;

public abstract class Piece {
    protected long bitboard;
    protected static long allPieces;

    public Piece(long bitboard) {
        this.bitboard = bitboard;
        Piece.allPieces |= bitboard;
    }

    public long getAllPieces() {
        return allPieces;
    }

    public void setAllPieces(long allPieces) {
        Piece.allPieces |= allPieces;
    }
    public long getBitboard() {
        return bitboard;
    }

    public void setBitboard(long bitboard) {
        this.bitboard = bitboard;
    }

    public abstract long possibleMoves(long position);
}
