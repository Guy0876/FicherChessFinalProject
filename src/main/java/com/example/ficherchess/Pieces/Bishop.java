package com.example.ficherchess.Pieces;

public class Bishop extends Piece{
    public Bishop(long bitboard, boolean isWhite) {
        super(bitboard, isWhite, 3);
    }

    @Override
    public long possibleMoves(long position){
        long specificBishop = bitboard & position;
        return diagonalMoves(specificBishop);
    }

    @Override
    public Bishop clone() {
        return (Bishop) super.clone();
    }
}