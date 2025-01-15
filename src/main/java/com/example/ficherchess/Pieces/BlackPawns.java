package com.example.ficherchess.Pieces;

public class BlackPawns extends Piece {

    public BlackPawns(long bitboard, long allPieces) {
        super(bitboard, allPieces);
    }
    @Override
    public long possibleMoves(long position) {
        long specificPawn = bitboard & position;

        // Move one step forward
        long oneStepForward = (specificPawn >> 8) & ~allPieces;
        long twoStepsForward = 0;
        if(isPawnOnStartingPosition()) {
            // Move two steps forward from the initial position
            twoStepsForward = ((specificPawn & 0x00FF000000000000L) >> 16) & ~allPieces & ~(allPieces >> 8);
        }
        // Capture diagonally
        long captureLeft = (specificPawn & 0x7F7F7F7F7F7F7F7FL) >> 9 & allPieces;
        long captureRight = (specificPawn & 0xFEFEFEFEFEFEFEFEL) >> 7 & allPieces;

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
    private boolean isPawnOnStartingPosition() {
        return (bitboard & 0x00FF000000000000L) != 0;
    }
}