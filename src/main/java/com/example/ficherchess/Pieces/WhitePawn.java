package com.example.ficherchess.Pieces;

public class WhitePawn extends Piece {

    public WhitePawn(long bitboard, boolean isWhite) {
        super(bitboard, isWhite, 1);
    }
    @Override
    public long possibleMoves(long position) {
        long specificPawn = bitboard & position;
        long opponentPieces = isWhite ? blackPieces : whitePieces;

        // Move one step forward
        long oneStepForward = (specificPawn >>> 8) & ~Piece.allPieces;
        long twoStepsForward = getTwoStepsForward(specificPawn);
        // Capture diagonally
        long captureLeft = (specificPawn & 0xFEFEFEFEFEFEFEFEL) >>> 9 & opponentPieces;
        long captureRight = (specificPawn & 0x7F7F7F7F7F7F7F7FL) >>> 7 & opponentPieces;

        return oneStepForward | twoStepsForward | captureLeft | captureRight;
    }

    private long getTwoStepsForward(long specificPawn) {
        long twoStepsForward = 0L;
        if(isPawnOnStartingPosition(specificPawn)) {
            // Move two steps forward from the initial position
            twoStepsForward = ((specificPawn & 0x00FF000000000000L) >>> 16) & ~Piece.allPieces & ~(Piece.allPieces >>> 8);
        }
        return twoStepsForward;
    }
    private boolean isPawnOnStartingPosition(long specificPawn) {
        return (specificPawn & 0x00FF000000000000L) != 0;
    }

    @Override
    public WhitePawn clone() {
        return (WhitePawn) super.clone();
    }

}