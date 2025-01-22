package com.example.ficherchess.Pieces;

public class King extends Piece {

    public King(long bitboard) {
        super(bitboard);
    }

    @Override
    public long possibleMoves(long position){
        long specificKing = bitboard & position;
        if(Piece.check){
            return 0L;
        }

        // Move up
        long up = specificKing << 8;

        // Move down
        long down = specificKing >> 8;

        // Move left
        long left = specificKing << 1;

        // Move right
        long right = specificKing >> 1;

        // Move up-right
        long upRight = specificKing << 7;

        // Move up-left
        long upLeft = specificKing << 9;

        // Move down-right
        long downRight = specificKing >> 9;

        //Move down-left
        long downLeft = specificKing >> 7;

        return (up | down | left | right | upRight | upLeft | downRight | downLeft);
    }
}
