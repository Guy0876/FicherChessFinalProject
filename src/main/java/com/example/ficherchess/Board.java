package com.example.ficherchess;

import com.example.ficherchess.Pieces.*;

public class Board {
    private Piece whiteRooks;
    private Piece whiteKnights;
    private Piece whiteBishops;
    private Piece whiteQueen;
    private Piece whiteKing;
    private Piece whitePawns;
    private Piece blackRooks;
    private Piece blackKnights;
    private Piece blackBishops;
    private Piece blackQueen;
    private Piece blackKing;
    private Piece blackPawns;

    public Board() {
        whiteRooks = new Rooks(0x81L);
        whiteKnights = new Knights(0x42L);
        whiteBishops = new Bishops(0x24L);
        whiteQueen = new Queen(0x8L);
        whiteKing = new King(0x10L);
        whitePawns = new WhitePawns(0xFF00L);
        blackRooks = new Rooks(0x8100000000000000L);
        blackKnights = new Knights(0x4200000000000000L);
        blackBishops = new Bishops(0x2400000000000000L);
        blackQueen = new Queen(0x800000000000000L);
        blackKing = new King(0x100000000000000L);
        blackPawns = new BlackPawns(0xFF000000000000L);
    }

    public Piece getWhiteRooks() {
        return whiteRooks;
    }

    public void setWhiteRooks(Piece whiteRooks) {
        this.whiteRooks = whiteRooks;
    }

    public Piece getWhiteKnights() {
        return whiteKnights;
    }

    public void setWhiteKnights(Piece whiteKnights) {
        this.whiteKnights = whiteKnights;
    }

    public Piece getWhiteBishops() {
        return whiteBishops;
    }

    public void setWhiteBishops(Piece whiteBishops) {
        this.whiteBishops = whiteBishops;
    }

    public Piece getWhiteQueen() {
        return whiteQueen;
    }

    public void setWhiteQueen(Piece whiteQueen) {
        this.whiteQueen = whiteQueen;
    }

    public Piece getWhiteKing() {
        return whiteKing;
    }

    public void setWhiteKing(Piece whiteKing) {
        this.whiteKing = whiteKing;
    }

    public Piece getWhitePawns() {
        return whitePawns;
    }

    public void setWhitePawns(Piece whitePawns) {
        this.whitePawns = whitePawns;
    }

    public Piece getBlackRooks() {
        return blackRooks;
    }

    public void setBlackRooks(Piece blackRooks) {
        this.blackRooks = blackRooks;
    }

    public Piece getBlackKnights() {
        return blackKnights;
    }

    public void setBlackKnights(Piece blackKnights) {
        this.blackKnights = blackKnights;
    }

    public Piece getBlackBishops() {
        return blackBishops;
    }

    public void setBlackBishops(Piece blackBishops) {
        this.blackBishops = blackBishops;
    }

    public Piece getBlackQueen() {
        return blackQueen;
    }

    public void setBlackQueen(Piece blackQueen) {
        this.blackQueen = blackQueen;
    }

    public Piece getBlackKing() {
        return blackKing;
    }

    public void setBlackKing(Piece blackKing) {
        this.blackKing = blackKing;
    }

    public Piece getBlackPawns() {
        return blackPawns;
    }

    public void setBlackPawns(Piece blackPawns) {
        this.blackPawns = blackPawns;
    }
}
