package com.example.ficherchess;

import com.example.ficherchess.Pieces.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Model {
    private ArrayList<ArrayList<Piece>> whitePieces;
    private ArrayList<ArrayList<Piece>> blackPieces;
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
        // Initialize white pieces
        ArrayList<Piece> whitePawns = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            long bitboard = 1L << (48 + i); // Place pawns on the second rank (48 to 55)
            whitePawns.add(new WhitePawn(bitboard, true));
        }
        whitePieces.add(whitePawns);

        ArrayList<Piece> whiteKnights = new ArrayList<>();
        long whiteKnightsBitboard = frc.getWhiteKnights();
        for (int i = 0; i < 64; i++) {
            long movePosition = 1L << i;
            if ((movePosition & whiteKnightsBitboard) != 0) {
                whiteKnights.add(new Knight(movePosition, true));
            }
        }
        whitePieces.add(whiteKnights);

        ArrayList<Piece> whiteBishops = new ArrayList<>();
        long whiteBishopsBitboard = frc.getWhiteBishops();
        for (int i = 0; i < 64; i++) {
            long movePosition = 1L << i;
            if ((movePosition & whiteBishopsBitboard) != 0) {
                whiteBishops.add(new Bishop(movePosition, true));
            }
        }
        whitePieces.add(whiteBishops);

        ArrayList<Piece> whiteRooks = new ArrayList<>();
        long whiteRooksBitboard = frc.getWhiteRooks();
        for (int i = 0; i < 64; i++) {
            long movePosition = 1L << i;
            if ((movePosition & whiteRooksBitboard) != 0) {
                whiteRooks.add(new Rook(movePosition, true));
            }
        }
        whitePieces.add(whiteRooks);

        ArrayList<Piece> whiteQueens = new ArrayList<>();
        whiteQueens.add(new Queen(frc.getWhiteQueen(), true));
        whitePieces.add(whiteQueens);

        ArrayList<Piece> whiteKing = new ArrayList<>();
        whiteKing.add(new King(frc.getWhiteKing(), true));
        whitePieces.add(whiteKing);

        // Initialize black pieces
        ArrayList<Piece> blackPawns = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            long bitboard = 1L << (8 + i); // Place pawns on the seventh rank (8 to 15)
            blackPawns.add(new BlackPawn(bitboard, false));
        }
        blackPieces.add(blackPawns);

        ArrayList<Piece> blackKnights = new ArrayList<>();
        long blackKnightsBitboard = frc.getBlackKnights();
        for (int i = 0; i < 64; i++) {
            long movePosition = 1L << i;
            if ((movePosition & blackKnightsBitboard) != 0) {
                blackKnights.add(new Knight(movePosition, false));
            }
        }
        blackPieces.add(blackKnights);

        ArrayList<Piece> blackBishops = new ArrayList<>();
        long blackBishopsBitboard = frc.getBlackBishops();
        for (int i = 0; i < 64; i++) {
            long movePosition = 1L << i;
            if ((movePosition & blackBishopsBitboard) != 0) {
                blackBishops.add(new Bishop(movePosition, false));
            }
        }
        blackPieces.add(blackBishops);

        ArrayList<Piece> blackRooks = new ArrayList<>();
        long blackRooksBitboard = frc.getBlackRooks();
        for (int i = 0; i < 64; i++) {
            long movePosition = 1L << i;
            if ((movePosition & blackRooksBitboard) != 0) {
                blackRooks.add(new Rook(movePosition, false));
            }
        }
        blackPieces.add(blackRooks);

        ArrayList<Piece> blackQueens = new ArrayList<>();
        blackQueens.add(new Queen(frc.getBlackQueen(), false));
        blackPieces.add(blackQueens);

        ArrayList<Piece> blackKing = new ArrayList<>();
        blackKing.add(new King(frc.getBlackKing(), false));
        blackPieces.add(blackKing);
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
        ArrayList<Piece> rookList = isWhiteTurn ? whitePieces.get(3) : blackPieces.get(3);
        for (Piece piece : rookList) {
            Rook rook = (Rook) piece;
            if ((rook.getBitboard() & movePosition) != 0) {
                long oldPosition = indexToBitboard(oldRow, oldCol);
                ArrayList<Piece> kingList = isWhiteTurn ? whitePieces.get(5) : blackPieces.get(5);
                King king = (King) kingList.get(0);
                if (king.getBitboard() != oldPosition) return false;
                boolean isLeftRook = selectedPiece.isWhite() ? king.getBitboard() > absolute(movePosition) : king.getBitboard() < movePosition;
                if (king.getHasMoved() || (isLeftRook ? rook.getHasMovedLeft() : rook.getHasMovedRight())) {
                    return false;
                }
                long temp = king.getBitboard();
                while ((temp & rook.getBitboard()) == 0) {
                    if (isWhiteTurn) {
                        if (isLeftRook) temp >>>= 1;
                        else temp <<= 1;
                    } else {
                        if (isLeftRook) temp <<= 1;
                        else temp >>>= 1;
                    }
                    if (((temp & Piece.allPieces) & ~rook.getBitboard()) != 0) return false;
                }
                long pieces = Piece.allPieces & ~king.getBitboard() & ~rook.getBitboard();
                if (isLeftRook && !isWhiteTurn) {
                    if ((pieces & 0x0000000000000060L) != 0) return false;
                } else if (isLeftRook && isWhiteTurn) {
                    if ((pieces & 0x0C00000000000000L) != 0) return false;
                } else if (!isLeftRook && !isWhiteTurn) {
                    if ((pieces & 0x000000000000000CL) != 0) return false;
                } else if (!isLeftRook && isWhiteTurn) {
                    if ((pieces & 0x6000000000000000L) != 0) return false;
                }
                return castle(movePosition, indexToBitboard(oldRow, oldCol), isLeftRook, king, rook);
            }
        }
        boolean isLegal = (possibleMoves & movePosition) != 0;
        if (isLegal)
            isLegal = updateTurn(oldRow, oldCol, newRow, newCol);
        return isLegal;
    }

    public long absolute(long a) {
        if ((a & 0x8000000000000000L) != 0) return 0x7888888888888888L;
        return a;
    }

    private boolean castle(long movePosition, long oldPosition, boolean isLeftRook, King king, Rook rook) {
        long oldRooks = rook.getBitboard();
        long oldKing = king.getBitboard();
        long oldAllPieces = Piece.allPieces;
        long oldWhitePieces = Piece.whitePieces;
        long oldBlackPieces = Piece.blackPieces;
        long kingPosition = oldPosition;
        long rookPosition = movePosition;
        long newKingPosition, newRookPosition;
        if (isWhiteTurn) {
            newKingPosition = isLeftRook ? indexToBitboard(7, 2) : indexToBitboard(7, 6);
            newRookPosition = isLeftRook ? indexToBitboard(7, 3) : indexToBitboard(7, 5);
        } else {
            newKingPosition = isLeftRook ? indexToBitboard(0, 6) : indexToBitboard(0, 2);
            newRookPosition = isLeftRook ? indexToBitboard(0, 5) : indexToBitboard(0, 3);
        }
        king.setBitboard((king.getBitboard() & ~kingPosition) | newKingPosition);
        rook.setBitboard((rook.getBitboard() & ~rookPosition) | newRookPosition);
        Piece.allPieces = (Piece.allPieces & ~kingPosition & ~rookPosition) | newKingPosition | newRookPosition;
        if (isWhiteTurn) {
            Piece.whitePieces = (Piece.whitePieces & ~kingPosition & ~rookPosition) | newKingPosition | newRookPosition;
        } else {
            Piece.blackPieces = (Piece.blackPieces & ~kingPosition & ~rookPosition) | newKingPosition | newRookPosition;
        }
        if (isKingInCheck(isWhiteTurn) || isRookThreatened(isWhiteTurn, newRookPosition)) {
            king.setBitboard(oldKing);
            rook.setBitboard(oldRooks);
            Piece.allPieces = oldAllPieces;
            Piece.whitePieces = oldWhitePieces;
            Piece.blackPieces = oldBlackPieces;
            return false;
        }
        if (isLeftRook) rook.setHasMovedLeft(true);
        else rook.setHasMovedRight(true);
        king.setHasMoved(true);
        isWhiteTurn = !isWhiteTurn;
        possibleMoves = 0L;
        selectedPiece = null;

        return true;
    }

    public boolean isRookThreatened(boolean isWhite, long rookPosition) {
        ArrayList<ArrayList<Piece>> opponentPieces = isWhite ? blackPieces : whitePieces;
        for (ArrayList<Piece> pieceList : opponentPieces) {
            for (Piece piece : pieceList) {
                if ((piece.possibleMoves(piece.getBitboard()) & rookPosition) != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean updateTurn(int oldRow, int oldCol, int newRow, int newCol) {
        ArrayList<ArrayList<Piece>> opponentPieces = isWhiteTurn ? blackPieces : whitePieces;
        long oldPosition = indexToBitboard(oldRow, oldCol);
        long newPosition = indexToBitboard(newRow, newCol);

        // Update the piece's position
        selectedPiece.setBitboard((selectedPiece.getBitboard() & ~oldPosition) | newPosition);
        if ((enPassantPosition & newPosition) != 0 && (selectedPiece instanceof WhitePawn || selectedPiece instanceof BlackPawn)) {
            if (isWhiteTurn) {
                enPassantPosition = indexToBitboard(newRow + 1, newCol);
                for (Piece pawn : blackPieces.get(0)) { // Iterate through black pawns
                    if ((pawn.getBitboard() & enPassantPosition) != 0) {
                        pawn.setBitboard(pawn.getBitboard() & ~enPassantPosition);
                        break;
                    }
                }
                Piece.blackPieces &= ~enPassantPosition;
                Piece.allPieces &= ~enPassantPosition;
            } else {
                enPassantPosition = indexToBitboard(newRow - 1, newCol);
                for (Piece pawn : whitePieces.get(0)) { // Iterate through white pawns
                    if ((pawn.getBitboard() & enPassantPosition) != 0) {
                        pawn.setBitboard(pawn.getBitboard() & ~enPassantPosition);
                        break;
                    }
                }
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
        ArrayList<Piece> kingList = isWhiteTurn ? whitePieces.get(5) : blackPieces.get(5);
        ArrayList<Piece> rookList = isWhiteTurn ? whitePieces.get(3) : blackPieces.get(3);
        King king = (King) kingList.get(0); // Assuming there is only one king in the list
        Rook rook = null;
        // Find the specific rook being moved
        for (Piece piece : rookList) {
            if (piece instanceof Rook && piece.getBitboard() == selectedPiece.getBitboard()) {
                rook = (Rook) piece;
                break;
            }
        }
        if (selectedPiece instanceof King && !king.getHasMoved()) {
            king.setHasMoved(true);
        } else if (selectedPiece instanceof Rook && !king.getHasMoved() && (!rook.getHasMovedLeft() || !rook.getHasMovedRight())) {
            long kingPosition = findKingPosition(selectedPiece.isWhite());
            boolean isLeftRook = selectedPiece.isWhite() ? kingPosition > oldPosition : kingPosition < oldPosition;
            if (isLeftRook) rook.setHasMovedLeft(true);
            else rook.setHasMovedRight(true);
        }
        // Remove the captured opponent piece if any
        for (ArrayList<Piece> pieceList : opponentPieces) {
            for (Iterator<Piece> iterator = pieceList.iterator(); iterator.hasNext(); ) {
                Piece opponentPiece = iterator.next();
                if ((opponentPiece.getBitboard() & newPosition) != 0) {
                    iterator.remove(); // Safely remove the piece
                    if (isWhiteTurn) {
                        Piece.blackPieces &= ~newPosition;
                    } else {
                        Piece.whitePieces &= ~newPosition;
                    }
                    break;
                }
            }
        }

        // Toggle the turn and reset the state
        isWhiteTurn = !isWhiteTurn;
        possibleMoves = 0L;
        selectedPiece = null;
        enPassantPosition = 0L;

        // Check if the move puts the opponent's king in check
        if (isKingInCheck(isWhiteTurn)) {
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
            for (ArrayList<Piece> pieceList : whitePieces) {
                for (Piece piece : pieceList) {
                    if ((piece.getBitboard() & specificPiece) != 0) {
                        selectedPiece = piece;
                        moves = piece.possibleMoves(specificPiece);
                        break;
                    }
                }
            }
        } else {
            for (ArrayList<Piece> pieceList : blackPieces) {
                for (Piece piece : pieceList) {
                    if ((piece.getBitboard() & specificPiece) != 0) {
                        selectedPiece = piece;
                        moves = piece.possibleMoves(specificPiece);
                        break;
                    }
                }
            }
        }

        // Handle en passant capture
        if (selectedPiece instanceof BlackPawn && lastMovedPiece instanceof WhitePawn ||
                selectedPiece instanceof WhitePawn && lastMovedPiece instanceof BlackPawn) {
            int oldRow = lastMove[0];
            int oldCol = lastMove[1];
            int newRow = lastMove[2];
            int newCol = lastMove[3];
            if (Math.abs(newRow - oldRow) == 2) {
                int enPassantRow = isWhiteTurn ? newRow - 1 : newRow + 1;
                int enPassantCol = newCol;
                long enPassantPosition = indexToBitboard(enPassantRow, enPassantCol);
                int currentPawnRow = isWhiteTurn ? 3 : 4;
                int currentPawnCol = (int) (Math.log(selectedPiece.getBitboard()) / Math.log(2)) % 8;
                if (selectedPiece.getBitboard() == indexToBitboard(currentPawnRow, currentPawnCol) &&
                        ((currentPawnCol == enPassantCol - 1) || (currentPawnCol == enPassantCol + 1))) {
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
                if (isKingInCheck(selectedPiece.isWhite())) {
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
        ArrayList<ArrayList<Piece>> pieces = isWhite ? whitePieces : blackPieces;
        ArrayList<Piece> kingList = pieces.get(5);
        King king = (King)kingList.get(0);
        return king.getBitboard();
    }

    public boolean isKingInCheck(boolean isWhite) {
        long kingPosition = findKingPosition(isWhite);
        ArrayList<ArrayList<Piece>> opponentPieces = isWhite ? blackPieces : whitePieces;
        for (ArrayList<Piece> pieceList : opponentPieces) {
            for (Piece piece : pieceList) {
                if ((piece.possibleMoves(piece.getBitboard()) & kingPosition) != 0) {
                    return true;
                }
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
        ArrayList<ArrayList<Piece>> opponentPieces = piece.isWhite() ? blackPieces : whitePieces;
        for (ArrayList<Piece> pieceList : opponentPieces) {
            for (Piece opponentPiece : pieceList) {
                if ((opponentPiece.getBitboard()) != 0) {
                    capturedPiece = opponentPiece;
                    break;
                }
            }
            if (capturedPiece != null) break;
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

        boolean isInCheck = isKingInCheck(piece.isWhite());

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
        ArrayList<ArrayList<Piece>> pieces = isWhite ? whitePieces : blackPieces;
        long validMoves = 0L;

        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                long possibleMoves = piece.possibleMoves(piece.getBitboard());
                validMoves = filterMovesThatResolveCheck(piece, possibleMoves, piece.getBitboard());
                if (validMoves != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // get copy of model that i can change without it doing anything to the model
    public Model getCopy() {
        Model copy = new Model(frc);
        copy.whitePieces = new ArrayList<>();
        for (ArrayList<Piece> pieceList : whitePieces) {
            ArrayList<Piece> clonedList = new ArrayList<>();
            for (Piece piece : pieceList) {
                clonedList.add(piece.clone());
            }
            copy.whitePieces.add(clonedList);
        }
        copy.blackPieces = new ArrayList<>();
        for (ArrayList<Piece> pieceList : blackPieces) {
            ArrayList<Piece> clonedList = new ArrayList<>();
            for (Piece piece : pieceList) {
                clonedList.add(piece.clone());
            }
            copy.blackPieces.add(clonedList);
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

    public ArrayList<ArrayList<Piece>> getWhitePieces() {
        return whitePieces;
    }

    public ArrayList<ArrayList<Piece>> getBlackPieces() {
        return blackPieces;
    }

    public long getPossibleMoves() {
        return possibleMoves;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public FischerRandomChess getFrc() {
        return frc;
    }

    public Piece getLastMovedPiece() {
        return lastMovedPiece;
    }

    public int[] getLastMove() {
        return lastMove;
    }

    public long getEnPassantPosition() {
        return enPassantPosition;
    }

}

