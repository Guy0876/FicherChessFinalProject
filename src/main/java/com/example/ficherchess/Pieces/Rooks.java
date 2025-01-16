package com.example.ficherchess.Pieces;

public class Rooks extends Piece {

    public Rooks(long bitboard) {
        super(bitboard);
    }

    @Override
    public long possibleMoves(long position) {
        long specificRook = bitboard & position;
        long moves = 0L;

        // Horizontal moves
        long horizontalMoves = 0L;
        long temp = specificRook;

        // Move left
        while ((temp & 0x7F7F7F7F7F7F7F7FL) != 0) {
            temp <<= 1;
            horizontalMoves |= temp;
            if ((temp & Piece.allPieces) != 0) break;
        }

        temp = specificRook;

        // Move right
        while ((temp & 0xFEFEFEFEFEFEFEFEL) != 0) {
            temp >>= 1;
            horizontalMoves |= temp;
            if ((temp & Piece.allPieces) != 0) break;
        }

        moves |= horizontalMoves & ~Piece.allPieces;

        // Vertical moves
        long verticalMoves = 0L;
        temp = specificRook;

        // Move up
        while ((temp & 0x00FFFFFFFFFFFFFFL) != 0) {
            temp <<= 8;
            verticalMoves |= temp;
            if ((temp & Piece.allPieces) != 0) break;
        }

        temp = specificRook;

        // Move down
        while ((temp & 0xFFFFFFFFFFFFFF00L) != 0) {
            temp >>= 8;
            verticalMoves |= temp;
            if ((temp & Piece.allPieces) != 0) break;
        }

        moves |= verticalMoves & ~Piece.allPieces;

        return moves;
    }


}
