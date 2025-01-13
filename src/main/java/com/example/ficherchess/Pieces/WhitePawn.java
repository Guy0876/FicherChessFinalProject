package com.example.ficherchess.Pieces;

public class WhitePawn extends Piece {

    public WhitePawn(long bitboard) {
        super(bitboard);
    }

    @Override
    public long possibleMoves(long position) {
        // Example logic for white pawn movement
        // Move one step forward
        long oneStepForward = bitboard << 8;
        // Move two steps forward from the initial position
        long twoStepsForward = (bitboard & 0x000000000000FF00L) << 16;
        // Capture diagonally
        long captureLeft = (bitboard & 0xFEFEFEFEFEFEFEFEL) << 7;
        long captureRight = (bitboard & 0x7F7F7F7F7F7F7F7FL) << 9;

        return oneStepForward | twoStepsForward | captureLeft | captureRight;
    }

    private boolean isPromotion() {
        return (bitboard & 0xFF000000000000FFL) != 0;
    }

    private boolean isEnPassant() {
        // Check if the pawn can perform en passant capture
        return false; // Placeholder logic
    }

    private boolean isPawnDoubleStep() {
        // Check if the pawn has moved two steps forward from the initial position
        return false; // Placeholder logic
    }

    private boolean isPawnCapture() {
        // Check if the pawn is capturing an opponent's piece
        return false; // Placeholder logic
    }
    private boolean isPawnOnStrartingPosition() {
        return (bitboard & 0x0000000000000FF00L) != 0;
    }
}
