package com.example.ficherchess.Pieces;

public class Knights extends Piece {
    public Knights(long bitboard, long allPieces) {
        super(bitboard, allPieces);
    }

    @Override
    public long possibleMoves(long position) {
        long specificKnight = bitboard & position;

        // Calculate all possible knight moves
        long move1 = (specificKnight & 0xFEFEFEFEFEFEFEFEL) << 15;
        long move2 = (specificKnight & 0xFCFCFCFCFCFCFCFCL) << 6;
        long move3 = (specificKnight & 0x7F7F7F7F7F7F7F7FL) << 17;
        long move4 = (specificKnight & 0x3F3F3F3F3F3F3F3FL) << 10;
        long move5 = (specificKnight & 0xFEFEFEFEFEFEFEFEL) >> 17;
        long move6 = (specificKnight & 0xFCFCFCFCFCFCFCFCL) >> 10;
        long move7 = (specificKnight & 0x7F7F7F7F7F7F7F7FL) >> 15;
        long move8 = (specificKnight & 0x3F3F3F3F3F3F3F3FL) >> 6;

        // Combine all possible moves
        return move1 | move2 | move3 | move4 | move5 | move6 | move7 | move8;
    }
}