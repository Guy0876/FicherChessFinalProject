package com.example.ficherchess.Pieces;

public abstract class Piece {
    protected long bitboard;
    public static long allPieces;
    protected static boolean check;

    public Piece(long bitboard) {
        this.bitboard = bitboard;
        Piece.allPieces |= bitboard;
        Piece.check = false;
    }

    public long getAllPieces() {
        return allPieces;
    }

    public void setAllPieces(long allPieces) {
        Piece.allPieces |= allPieces;
    }
    public void setCheck() {
        Piece.check = true;
    }
    public long getBitboard() {
        return bitboard;
    }

    public void setBitboard(long bitboard) {
        this.bitboard = bitboard;
    }

    public abstract long possibleMoves(long position);


    protected long horizontalMoves(long specificPiece) {
        long moves = 0L;
        long temp = specificPiece;

        // Move left
        while ((temp & 0x7F7F7F7F7F7F7F7FL) != 0) {
            temp <<= 1;
            moves |= temp;
            if ((temp & allPieces) != 0) break;
        }

        temp = specificPiece;

        // Move right
        while ((temp & 0xFEFEFEFEFEFEFEFEL) != 0) {
            temp >>= 1;
            moves |= temp;
            if ((temp & allPieces) != 0) break;
        }

        return moves & ~allPieces;
    }

    protected long verticalMoves(long specificPiece) {
        long moves = 0L;
        long temp = specificPiece;

        // Move up
        while ((temp & 0x00FFFFFFFFFFFFFFL) != 0) {
            temp <<= 8;
            moves |= temp;
            if ((temp & allPieces) != 0) break;
        }

        temp = specificPiece;

        // Move down
        while ((temp & 0xFFFFFFFFFFFFFF00L) != 0) {
            temp >>= 8;
            moves |= temp;
            if ((temp & allPieces) != 0) break;
        }

        return moves & ~allPieces;
    }

    protected long diagonalMoves(long specificPiece) {
        long Moves = 0L;
        long temp = specificPiece;

        // Move up-right
        while((temp & 0x00FFFFFFFFFFFFFFL) != 0 && (temp & 0x0101010101010101L) == 0){
            temp <<= 7;
            Moves |= temp;
            if((temp & Piece.allPieces) != 0) break;
        }

        temp = specificPiece;

        // Move up-left
        while((temp & 0x00FFFFFFFFFFFFFFL) != 0 && (temp & 0x8080808080808080L) == 0){
            temp <<= 9;
            Moves |= temp;
            if((temp & Piece.allPieces) != 0) break;
        }

        temp = specificPiece;

        // Move down-right
        while((temp & 0xFFFFFFFFFFFFFF00L) != 0 && (temp & 0x0101010101010101L) == 0){
            temp >>= 9;
            Moves |= temp;
            if((temp & Piece.allPieces) != 0) break;
        }

        temp = specificPiece;

        // Move down-left
        while((temp & 0xFFFFFFFFFFFFFF00L) != 0 && (temp & 0x8080808080808080L) == 0){
            temp >>= 7;
            Moves |= temp;
            if((temp & Piece.allPieces) != 0) break;
        }

        return Moves;
    }
}
