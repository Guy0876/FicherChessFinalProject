package com.example.ficherchess;

import com.example.ficherchess.Pieces.*;

import java.util.ArrayList;

public class Model {
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;
    private long possibleMoves;
    private Piece selectedPiece;
    private boolean isWhiteTurn;
    private FischerRandomChess frc;
    private Piece lastMovedPiece;
    private int[] lastMove = new int[4];
    private long enPassantPosition = 0L;

    public Model(FischerRandomChess fischerRandomChess) {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        possibleMoves = 0L;
        isWhiteTurn = true;
        frc = fischerRandomChess;
        initializeBoard();
    }
    public void initializeBoard() {
        whitePieces.add(new WhitePawns(frc.getWhitePawns(), true));
        whitePieces.add(new Knights(frc.getWhiteKnights(), true));
        whitePieces.add(new Bishops(frc.getWhiteBishops(), true));
        whitePieces.add(new Rooks(frc.getWhiteRooks(), true));
        whitePieces.add(new Queen(frc.getWhiteQueen(), true));
        whitePieces.add(new King(frc.getWhiteKing(), true));
        blackPieces.add(new BlackPawns(frc.getBlackPawns(), false));
        blackPieces.add(new Knights(frc.getBlackKnights(), false));
        blackPieces.add(new Bishops(frc.getBlackBishops(), false));
        blackPieces.add(new Rooks(frc.getBlackRooks(), false));
        blackPieces.add(new Queen(frc.getBlackQueen(), false));
        blackPieces.add(new King(frc.getBlackKing(), false));
    }
    public static long indexToBitboard(int row, int col) {
        int index = row * 8 + col;
        if (index < 0 || index > 63) {
            throw new IllegalArgumentException("Index must be between 0 and 63");
        }
        return 1L << index;
    }
    public long setSelectedPiece(int row, int col) {
        long specificPiece = indexToBitboard(row, col);
        possibleMoves = getPossibleMoves(specificPiece);
        return possibleMoves;
    }

    public boolean isLegalMove(int oldRow, int oldCol, int newRow, int newCol) {
        long movePosition = indexToBitboard(newRow, newCol);
        Rooks rooks = (Rooks)(isWhiteTurn ? whitePieces.get(3) : blackPieces.get(3));
        if((rooks.getBitboard() & movePosition) != 0) {
            King king = (King)(isWhiteTurn ? whitePieces.get(5) : blackPieces.get(5));
            long oldPosition = indexToBitboard(oldRow, oldCol);
            if(king.getBitboard() != oldPosition) return false;
            boolean isLeftRook = selectedPiece.isWhite() ? king.getBitboard() > absolute(movePosition) : king.getBitboard() < movePosition;
            if(king.getHasMoved() || (isLeftRook ? rooks.getHasMovedLeft() : rooks.getHasMovedRight())) {
                return false;
            }
            long temp = king.getBitboard();
            while((temp & rooks.getBitboard()) == 0){
                if(isWhiteTurn){
                    if(isLeftRook) temp >>>= 1;
                    else temp <<= 1;
                }
                else {
                    if(isLeftRook) temp <<= 1;
                    else temp >>>= 1;
                }
                if(((temp & Piece.allPieces) & ~rooks.getBitboard()) != 0) return false;
            }
            long pieces = Piece.allPieces & ~king.getBitboard() & ~rooks.getBitboard();
            if(isLeftRook && !isWhiteTurn) {
                if ((pieces & 0x0000000000000060L) != 0) return false;
            }
            else if(isLeftRook && isWhiteTurn) {
                if ((pieces & 0x0C00000000000000L) != 0) return false;
            }
            else if(!isLeftRook && !isWhiteTurn) {
                if ((pieces & 0x000000000000000CL) != 0) return false;
            }
            else if(!isLeftRook && isWhiteTurn) {
                if ((pieces & 0x6000000000000000L) != 0) return false;
            }
            return castle(movePosition, indexToBitboard(oldRow, oldCol), isLeftRook, king, rooks);
        }
        boolean isLegal = (possibleMoves & movePosition) != 0;
        if(isLegal)
            isLegal = updateTurn(oldRow, oldCol, newRow, newCol);
        return isLegal;
    }

    public long absolute(long a){
        if((a & 0x8000000000000000L) != 0) return 0x7888888888888888L;
        return a;
    }

    private boolean castle(long movePosition, long oldPosition, boolean isLeftRook, King king, Rooks rooks) {
        long oldRooks = rooks.getBitboard();
        long oldKing = king.getBitboard();
        long oldAllPieces = Piece.allPieces;
        long oldWhitePieces = Piece.whitePieces;
        long oldBlackPieces = Piece.blackPieces;
        long kingPosition = oldPosition;
        long rookPosition = movePosition;
        long newKingPosition, newRookPosition;
        if(isWhiteTurn) {
            newKingPosition = isLeftRook ? indexToBitboard(7, 2): indexToBitboard(7, 6);
            newRookPosition = isLeftRook ? indexToBitboard(7, 3): indexToBitboard(7, 5);
        }
        else {
            newKingPosition = isLeftRook ? indexToBitboard(0, 6): indexToBitboard(0, 2);
            newRookPosition = isLeftRook ? indexToBitboard(0, 5): indexToBitboard(0, 3);
        }
        king.setBitboard((king.getBitboard() & ~kingPosition) | newKingPosition);
        rooks.setBitboard((rooks.getBitboard() & ~rookPosition) | newRookPosition);
        Piece.allPieces = (Piece.allPieces & ~kingPosition & ~rookPosition) | newKingPosition | newRookPosition;
        if(isWhiteTurn) {
            Piece.whitePieces = (Piece.whitePieces & ~kingPosition & ~rookPosition) | newKingPosition | newRookPosition;
        } else {
            Piece.blackPieces = (Piece.blackPieces & ~kingPosition & ~rookPosition) | newKingPosition | newRookPosition;
        }
        if(isKingInCheck(isWhiteTurn) || isRookThreatened(isWhiteTurn, newRookPosition)) {
            king.setBitboard(oldKing);
            rooks.setBitboard(oldRooks);
            Piece.allPieces = oldAllPieces;
            Piece.whitePieces = oldWhitePieces;
            Piece.blackPieces = oldBlackPieces;
            return false;
        }
        if(isLeftRook) rooks.setHasMovedLeft(true);
        else rooks.setHasMovedRight(true);
        king.setHasMoved(true);
        isWhiteTurn = !isWhiteTurn;
        possibleMoves = 0L;
        selectedPiece = null;

        return true;
    }

    public boolean isRookThreatened(boolean isWhite, long rookPosition){
        ArrayList<Piece> opponentPieces = isWhite ? blackPieces : whitePieces;
        for (Piece piece : opponentPieces) {
            if(piece instanceof Rooks || piece instanceof Bishops) {
                long temp = piece.getBitboard();
                for (int i = 0; i < 64; i++) {
                    long movePosition = 1L << i;
                    if ((temp & movePosition) != 0) {
                        long possibleMoves = piece.possibleMoves(movePosition);
                        if ((possibleMoves & rookPosition) != 0) {
                            return true;
                        }
                    }
                }
            }
            else if ((piece.possibleMoves(piece.getBitboard()) & rookPosition) != 0) {
                return true;
            }
        }
        return false;
    }
    private boolean updateTurn(int oldRow, int oldCol, int newRow, int newCol) {
        ArrayList<Piece> opponentPieces = isWhiteTurn ? blackPieces : whitePieces;
        long oldPosition = indexToBitboard(oldRow, oldCol);
        long newPosition = indexToBitboard(newRow, newCol);

        // Update the piece's position
        selectedPiece.setBitboard((selectedPiece.getBitboard() & ~oldPosition) | newPosition);
        if((enPassantPosition & newPosition) != 0 && (selectedPiece instanceof WhitePawns || selectedPiece instanceof BlackPawns)) {
            if(isWhiteTurn) {
                enPassantPosition = indexToBitboard(newRow + 1, newCol);
                blackPieces.get(0).setBitboard(blackPieces.get(0).getBitboard() & ~enPassantPosition);
                Piece.blackPieces &= ~enPassantPosition;
                Piece.allPieces &= ~enPassantPosition;
            }
            else {
                enPassantPosition = indexToBitboard(newRow - 1, newCol);
                whitePieces.get(0).setBitboard(whitePieces.get(0).getBitboard() & ~enPassantPosition);
                Piece.whitePieces &= ~enPassantPosition;
                Piece.allPieces &= ~enPassantPosition;
            }
        }
        Piece.allPieces &= ~oldPosition;
        Piece.allPieces |= newPosition;
        if (isWhiteTurn) {
            Piece.whitePieces &= ~oldPosition;
            Piece.whitePieces |= newPosition;
        } else {
            Piece.blackPieces &= ~oldPosition;
            Piece.blackPieces |= newPosition;
        }

        // Save the last moved piece
        lastMovedPiece = selectedPiece;
        lastMove[0] = oldRow;
        lastMove[1] = oldCol;
        lastMove[2] = newRow;
        lastMove[3] = newCol;

        // Update the state of the king and rooks if necessary
        King king = (King) (isWhiteTurn ? whitePieces.get(5) : blackPieces.get(5));
        Rooks rooks = (Rooks) (isWhiteTurn ? whitePieces.get(3) : blackPieces.get(3));
        if (selectedPiece instanceof King && !king.getHasMoved()) {
            king.setHasMoved(true);
        } else if (selectedPiece instanceof Rooks && !king.getHasMoved() && (!rooks.getHasMovedLeft() || !rooks.getHasMovedRight())) {
            long kingPosition = findKingPosition(!selectedPiece.isWhite());
            boolean isLeftRook = selectedPiece.isWhite() ? kingPosition > oldPosition : kingPosition < oldPosition;
            if (isLeftRook) rooks.setHasMovedLeft(true);
            else rooks.setHasMovedRight(true);
        }
        // Remove the captured opponent piece if any
        for (Piece opponentPiece : opponentPieces) {
            if ((opponentPiece.getBitboard() & newPosition) != 0) {
                opponentPiece.setBitboard(opponentPiece.getBitboard() & ~newPosition);
                if (isWhiteTurn) {
                    Piece.blackPieces &= ~newPosition;
                } else {
                    Piece.whitePieces &= ~newPosition;
                }
                break;
            }
        }

        // Toggle the turn and reset the state
        isWhiteTurn = !isWhiteTurn;
        possibleMoves = 0L;
        selectedPiece = null;
        enPassantPosition = 0L;

        // Check if the move puts the opponent's king in check
        if (isKingInCheck(!isWhiteTurn)) {
            System.out.println((isWhiteTurn ? "Black" : "White") + " king is in check!");
            Piece.check = true;

            if (isCheckmate(isWhiteTurn)) {
                System.out.println((isWhiteTurn ? "Black" : "White") + " won the game !!!");
            }
        }
        return true;
    }
    public long getPossibleMoves(long specificPiece) {
        long moves = 0L;
        //Update the selected piece
        if (isWhiteTurn) {
            for (Piece piece : whitePieces) {
                if ((piece.getBitboard() & specificPiece) != 0) {
                    selectedPiece = piece;
                    moves = piece.possibleMoves(specificPiece);
                }
            }
        } else {
            for (Piece piece : blackPieces) {
                if ((piece.getBitboard() & specificPiece) != 0) {
                    selectedPiece = piece;
                    moves = piece.possibleMoves(specificPiece);
                }
            }
        }

        // Handle en passant capture
        if (selectedPiece instanceof BlackPawns && lastMovedPiece instanceof WhitePawns ||
                selectedPiece instanceof WhitePawns && lastMovedPiece instanceof BlackPawns) {
            int oldRow = lastMove[0];
            int oldCol = lastMove[1];
            int newRow = lastMove[2];
            int newCol = lastMove[3];
            if (Math.abs(newRow - oldRow) == 2) {
                int enPassantRow = isWhiteTurn ? newRow - 1 : newRow + 1;
                int enPassantCol = newCol;
                long enPassantPosition = indexToBitboard(enPassantRow, enPassantCol);
                int currentPawnRow = isWhiteTurn ? 3 : 4;
                int currentPawnCol = (int) (Math.log(selectedPiece.getBitboard() & specificPiece) / Math.log(2)) % 8;
                if ((selectedPiece.getBitboard() & specificPiece) == indexToBitboard(currentPawnRow, currentPawnCol) &&
                        (currentPawnCol == enPassantCol - 1 || currentPawnCol == enPassantCol + 1)) {
                    moves |= enPassantPosition;
                }
            }
        }
        //Filter moves that resolve check
        if (Piece.check) {
            moves = filterMovesThatResolveCheck(selectedPiece, moves, specificPiece);
        }

        // Remove moves that put the king in check
        for (int i = 0; i < 64; i++) {
            long movePosition = 1L << i;
            if ((moves & movePosition) != 0) {
                long originalPosition = selectedPiece.getBitboard();
                long originalAllPieces = Piece.allPieces;
                long originalWhitePieces = Piece.whitePieces;
                long originalBlackPieces = Piece.blackPieces;
                selectedPiece.setBitboard(movePosition | (selectedPiece.getBitboard() & ~specificPiece));
                Piece.allPieces = (Piece.allPieces & ~specificPiece) | movePosition;
                if (selectedPiece.isWhite()) {
                    Piece.whitePieces = (Piece.whitePieces & ~specificPiece) | movePosition;
                } else {
                    Piece.blackPieces = (Piece.blackPieces & ~specificPiece) | movePosition;
                }
                if (isKingInCheck(!selectedPiece.isWhite())) {
                    moves &= ~movePosition;
                }
                selectedPiece.setBitboard(originalPosition);
                Piece.allPieces = originalAllPieces;
                if (selectedPiece.isWhite()) {
                    Piece.whitePieces = originalWhitePieces;
                } else {
                    Piece.blackPieces = originalBlackPieces;
                }
            }
        }

        return moves;
    }

    public long findKingPosition(boolean isWhite) {
        ArrayList<Piece> pieces = isWhite ? blackPieces : whitePieces;
        for (Piece piece : pieces) {
            if (piece instanceof King) {
                return piece.getBitboard();
            }
        }
        return 0L;
    }

    public boolean isKingInCheck(boolean isWhite) {
        long kingPosition = findKingPosition(isWhite);
        ArrayList<Piece> opponentPieces = isWhite ? whitePieces : blackPieces;
        for (Piece piece : opponentPieces) {
            if(piece instanceof Rooks || piece instanceof Bishops) {
                long temp = piece.getBitboard();
                for (int i = 0; i < 64; i++) {
                    long movePosition = 1L << i;
                    if ((temp & movePosition) != 0) {
                        long possibleMoves = piece.possibleMoves(movePosition);
                        if ((possibleMoves & kingPosition) != 0) {
                            return true;
                        }
                    }
                }
            }
            else if ((piece.possibleMoves(piece.getBitboard()) & kingPosition) != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean doesMoveResolveCheck(Piece piece, long movePosition, long specificPiece) {
        long originalPosition = piece.getBitboard();
        long originalAllPieces = Piece.allPieces;
        long originalWhitePieces = Piece.whitePieces;
        long originalBlackPieces = Piece.blackPieces;

        // Save the state of the opponent piece that might be captured
        Piece capturedPiece = null;
        ArrayList<Piece> opponentPieces = piece.isWhite() ? blackPieces : whitePieces;
        for (Piece opponentPiece : opponentPieces) {
            if ((opponentPiece.getBitboard() & movePosition) != 0) {
                capturedPiece = opponentPiece;
                break;
            }
        }

        // Make the move
        piece.setBitboard(movePosition | (piece.getBitboard() & ~specificPiece));
        Piece.allPieces = (Piece.allPieces & ~specificPiece) | movePosition;
        if (piece.isWhite()) {
            Piece.whitePieces = (Piece.whitePieces & ~specificPiece) | movePosition;
        } else {
            Piece.blackPieces = (Piece.blackPieces & ~specificPiece) | movePosition;
        }
        if (capturedPiece != null) {
            capturedPiece.setBitboard(capturedPiece.getBitboard() & ~movePosition);
            if (piece.isWhite()) {
                Piece.blackPieces &= ~movePosition;
            } else {
                Piece.whitePieces &= ~movePosition;
            }
        }

        boolean isInCheck = isKingInCheck(!piece.isWhite());

        // Revert the move
        piece.setBitboard(originalPosition);
        Piece.allPieces = originalAllPieces;
        if (piece.isWhite()) {
            Piece.whitePieces = originalWhitePieces;
        } else {
            Piece.blackPieces = originalBlackPieces;
        }
        if (capturedPiece != null) {
            capturedPiece.setBitboard(capturedPiece.getBitboard() | movePosition);
            if (piece.isWhite()) {
                Piece.blackPieces |= movePosition;
            } else {
                Piece.whitePieces |= movePosition;
            }
        }

        return !isInCheck;
    }

    public long filterMovesThatResolveCheck(Piece piece, long moves, long specificPiece) {

        long validMoves = 0L;
        for (int i = 0; i < 64; i++) {
            long movePosition = 1L << i;
            if ((moves & movePosition) != 0 && doesMoveResolveCheck(piece, movePosition, specificPiece)) {
                validMoves |= movePosition;
            }
        }
        return validMoves;
    }

    public boolean isCheckmate(boolean isWhite) {
        ArrayList<Piece> pieces = isWhite ? whitePieces : blackPieces;
        long validMoves = 0L;
        for (Piece piece : pieces) {
            long temp = piece.getBitboard();
            for(int i = 0; i < 64; i++) {
                long movePosition = 1L << i;
                if((temp & movePosition) != 0) {
                    long possibleMoves = piece.possibleMoves(movePosition);
                    validMoves = filterMovesThatResolveCheck(piece, possibleMoves, movePosition);
                    if (possibleMoves != 0) {
                        return false;
                    }
                }
            }

        }
        if(validMoves != 0)
            return false;
        return true;
    }

    // get copy of model that i can change without it doing anything to the model
    public Model getCopy() {
        Model copy = new Model(frc);
        copy.whitePieces = new ArrayList<>();
        for (Piece piece : whitePieces) {
            copy.whitePieces.add(piece.clone());
        }
        copy.blackPieces = new ArrayList<>();
        for (Piece piece : blackPieces) {
            copy.blackPieces.add(piece.clone());
        }
        copy.possibleMoves = possibleMoves;
        copy.selectedPiece = selectedPiece != null ? selectedPiece.clone() : null;
        copy.isWhiteTurn = isWhiteTurn;
        copy.frc = frc;
        copy.lastMovedPiece = lastMovedPiece != null ? lastMovedPiece.clone() : null;
        copy.lastMove = lastMove.clone();
        copy.enPassantPosition = enPassantPosition;
        return copy;
    }

}