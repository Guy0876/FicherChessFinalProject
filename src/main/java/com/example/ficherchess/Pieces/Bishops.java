package com.example.ficherchess.Pieces;

public class Bishops extends Piece{
    public Bishops(long bitboard) {
        super(bitboard);
    }

    @Override
    public long possibleMoves(long position){
        long Moves = 0L;
        long specificBishop = bitboard & position;
        long temp = specificBishop;

        // Move up-right
        while((temp & 0x00FFFFFFFFFFFFFFL) != 0 && (temp & 0x0101010101010101L) == 0){
            temp <<= 7;
            Moves |= temp;
            if((temp & Piece.allPieces) != 0) break;
        }

        temp = specificBishop;

        // Move up-left
        while((temp & 0x00FFFFFFFFFFFFFFL) != 0 && (temp & 0x8080808080808080L) == 0){
            temp <<= 9;
            Moves |= temp;
            if((temp & Piece.allPieces) != 0) break;
        }

        temp = specificBishop;

        // Move down-right
        while((temp & 0xFFFFFFFFFFFFFF00L) != 0 && (temp & 0x0101010101010101L) == 0){
            temp >>= 9;
            Moves |= temp;
            if((temp & Piece.allPieces) != 0) break;
        }

        temp = specificBishop;

        // Move down-left
        while((temp & 0xFFFFFFFFFFFFFF00L) != 0 && (temp & 0x8080808080808080L) == 0){
            temp >>= 7;
            Moves |= temp;
            if((temp & Piece.allPieces) != 0) break;
        }

        return Moves;
    }
}
