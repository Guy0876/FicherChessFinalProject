package com.example.ficherchess;

import com.example.ficherchess.Pieces.*;

import java.util.ArrayList;

public class Model {
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;
    private long possibleMoves;
    private Piece selectedPiece;
    private boolean isWhiteTurn;
    public Model() {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        possibleMoves = 0L;
        isWhiteTurn = true;
        initializeBoard();
    }
    public void initializeBoard() {
        whitePieces.add(new WhitePawns(0xFF000000000000L));
        whitePieces.add(new Knights(0x4200000000000000L));
        whitePieces.add(new Bishops(0x2400000000000000L));
        whitePieces.add(new Rooks(0x8100000000000000L));
        whitePieces.add(new Queen(0x800000000000000L));
        whitePieces.add(new King(0x1000000000000000L));
        blackPieces.add(new BlackPawns(0xFF00L));
        blackPieces.add(new Knights(0x42L));
        blackPieces.add(new Bishops(0x24L));
        blackPieces.add(new Rooks(0x81L));
        blackPieces.add(new Queen(0x8L));
        blackPieces.add(new King(0x10L));
    }
    public static long indexToBitboard(int row, int col) {
        int index = row * 8 + col;
        if (index < 0 || index > 63) {
            throw new IllegalArgumentException("Index must be between 0 and 63");
        }
        return 1L << index;
    }
    public void setSelectedPiece(int row, int col) {
        long specificPiece = indexToBitboard(row, col);
        possibleMoves = getPossibleMoves(specificPiece);
    }

    public boolean isLegalMove(int oldRow, int oldCol, int newRow, int newCol) {
        long movePosition = indexToBitboard(newRow, newCol);
        boolean isLegal = (possibleMoves & movePosition) != 0;
        if(isLegal) updateTurn(oldRow, oldCol, newRow, newCol);
        return isLegal;
    }

    private void updateTurn(int oldRow, int oldCol, int newRow, int newCol) {
        ArrayList<Piece> opponentPieces = isWhiteTurn ? blackPieces : whitePieces;
        isWhiteTurn = !isWhiteTurn;
        long oldPosition = indexToBitboard(oldRow, oldCol);
        long newPosition = indexToBitboard(newRow, newCol);
        selectedPiece.setBitboard((selectedPiece.getBitboard() & ~oldPosition) | newPosition);
        Piece.allPieces &= ~oldPosition;
        Piece.allPieces |= newPosition;
        for (Piece opponentPiece : opponentPieces) {
            if ((opponentPiece.getBitboard() & newPosition) != 0) {
                opponentPiece.setBitboard(opponentPiece.getBitboard() & ~newPosition); // Remove the captured piece
                break;
            }
        }
    }

    public long getPossibleMoves(long specificPiece) {
        if(isWhiteTurn) {
            for (Piece piece : whitePieces) {
                if ((piece.getBitboard() & specificPiece) != 0) {
                    selectedPiece = piece;
                    return piece.possibleMoves(specificPiece);
                }
            }
        } else {
            for (Piece piece : blackPieces) {
                if ((piece.getBitboard() & specificPiece) != 0) {
                    selectedPiece = piece;
                    return piece.possibleMoves(specificPiece);
                }
            }
        }
        return 0L;
    }
}
