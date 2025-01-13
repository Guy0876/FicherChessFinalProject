package com.example.ficherchess.Pieces;

public class BlackPawn extends Piece {

    public BlackPawn(long bitboard) {
        super(bitboard);
    }
    @Override
    public long possibleMoves(long position) {
// Ensure only the specific pawn is considered
        long specificPawn = bitboard & position;

        // Example logic for black pawn movement
        // Move one step forward
        long oneStepForward = specificPawn >> 8;
        // Move two steps forward from the initial position
        long twoStepsForward = (specificPawn & 0x00FF000000000000L) >> 16;
        // Capture diagonally
        long captureLeft = (specificPawn & 0x7F7F7F7F7F7F7F7FL) >> 9;
        long captureRight = (specificPawn & 0xFEFEFEFEFEFEFEFEL) >> 7;

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
        return (bitboard & 0x00FF000000000000L) != 0;
    }
}