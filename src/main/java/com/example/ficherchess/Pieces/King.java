package com.example.ficherchess.Pieces;

public class King extends Piece {

    private boolean hasMoved = false;
    public King(long bitboard, boolean isWhite) {
        super(bitboard, isWhite, 10);
    }

    @Override
    public long possibleMoves(long position) {
        long specificKing = bitboard & position;
        long myPieces = isWhite ? whitePieces : blackPieces;

        // File masks to prevent wraparound
        long notAFile = 0xFEFEFEFEFEFEFEFEL; // Clears column A
        long notHFile = 0x7F7F7F7F7F7F7F7FL; // Clears column H

        // One-square moves in 8 directions
        long up         = (specificKing << 8) & ~myPieces;              // North
        long down       = (specificKing >>> 8) & ~myPieces;             // South
        long left       = (specificKing << 1) & notAFile & ~myPieces;   // West
        long right      = (specificKing >>> 1) & notHFile & ~myPieces;  // East
        long upLeft     = (specificKing << 9) & notAFile & ~myPieces;   // NW
        long upRight    = (specificKing << 7) & notHFile & ~myPieces;   // NE
        long downLeft   = (specificKing >>> 7) & notAFile & ~myPieces;  // SW
        long downRight  = (specificKing >>> 9) & notHFile & ~myPieces;  // SE

        // Combine all valid directions
        return up | down | left | right | upLeft | upRight | downLeft | downRight;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    @Override
    public King clone() {
        return (King) super.clone();
    }
}