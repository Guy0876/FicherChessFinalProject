package com.example.ficherchess.AutoMatedBot;

import com.example.ficherchess.Model;
import com.example.ficherchess.Pieces.*;

import java.util.ArrayList;

public class AutomatedBot {
    private Model model;
    private Model copy;
    private boolean opening = true;
    private boolean middleGame = false;
    private boolean endGame = false;
    private int totalMoves = 0;
    private int [] lastMove;
    private MoveSum [] moveSums;
    private int depth = 0;
//    private ArrayList<ArrayList<Piece>> pieces;
//    private ArrayList<ArrayList<Piece>> opponentPieces;
    public AutomatedBot(Model model){
        this.model = model;
    }

//    public void makeMove() {
        //this.copy = model.getCopy();
        // check if king in check if yes get out of the check best possible way

        // check if you can checkmate if yes make it

        // check if opp can checkmate
        // check if you can block checkmate or stop it

        // check if one of your pieces is under attack,
        // if you can take with it and get out of danger do it,
        // if it is under attack by a better piece then check if your piece is defended if yes there is no danger
        // if not defended try to defend if you cant move out the way if you can
        // if you can take the piece that is attacking you and it is a good trade do it
        // if it is a bad trade try to move out of the way or take the piece that attacks
        // if couple pieces are under attack make the best trade

        // check if you can take a piece and if yes check if opp can take back and if it is good for you if yes make the move

        // if opening try to take center and advance pieces to useful positions
        // and make sure every piece is defended Just like in standard chess,
        // you want to mainly focus on controlling the 16 central squares
        // by placing your pawns and developing your pieces to influence these squares.
        // Your goal in the opening should be to activate your pieces
        // as efficiently and as effectively as possible while at the same time
        // controlling the center with your pawns and making sure that your King is safe.
        // If your opponent puts 2 pawns in the center,
        // (also known as a pawn duo), then break it up as soon as possible with one of your pawns.
        // Develop your pieces to active squares where they will influence the center.
        // Develop your Knights behind pawns,
        // particularly central pawns (c, d, e and f pawns).
        // You generally want to avoid blocking your central pawns
        // because you often want to use your central pawns to fight for control of the center.
        // Also, Knights work well behind pawns because the pawn and Knight
        // will coordinate to control the same squares and
        // if the Knight ever moves forward it will have the support of the pawn.
        // You shouldn't always develop your Knights behind pawns
        // but try to do so whenever it's convenient.
        // Get your Knights out of the corner.
        // Knights are very badly placed in the corner
        // because that's where they have the least mobility.
        // So if a Knight starts in a corner
        // it should generally be one of the first pieces you develop.
        // Bishops don't always need to move to be considered developed.
        // Sometimes moving a pawn forward is all it takes to activate a Bishop.
        // Bishops are long range pieces that have the potential
        // to influence the position from their starting squares.
        // It may be necessary to move your Bishops to get castled
        // or to connect your Rooks but if that's not the case then
        // it's often acceptable to leave your Bishops on the back rank,
        // at least in the opening stage of the game.
        // Decrease the activity/mobility of your opponent's pieces.
        // If your opponent has a very active piece then
        // try to either trade one of your pieces for it
        // or restrict it with one or more of your pawns.
        // Don't bring your Queen out early unless you have a good reason.
        // Your Queen will just get attacked by your opponent's pawns and minor pieces.
        // You also run the risk of getting your Queen trapped.
        // Don't move the same piece multiple times in the opening unless you have a good reason.
        // You should give priority to your least active pieces
        // before attempting to improve an already developed piece.
        // Don't move pawns in front of your King unless you have a good reason.
        // Make use of a pawn break as soon as possible.
        // In simple terms,
        // a pawn break is when you offer to exchange one of your pawns
        // for one of your opponent's pawns. Pawn breaks can help you do many things.
        // Some of these things include opening lines for your pieces,
        // gaining control of the position, and putting pressure on your opponent's position.
        // Connect your Rooks when possible.
        // Don't rush. Slowly improve your position. Be patient.

        // opening ends after 12 moves

        // if middle game Pawns are the backbone of your position.
        // Keep an eye on their structure and mobility.
        // They can control key squares and create avenues for your pieces to maneuver.
        // Weaknesses in pawn structure can be exploited by your opponent,
        // so strive to maintain a solid formation
        // while also looking for opportunities to advance and create threats.
        // The center of the board is the most dynamic area
        // where battles for control often take place.
        // By occupying or influencing central squares with your pieces,
        // you exert greater control over the board and limit your opponent's options.
        // Additionally, controlling the center allows
        // for more flexibility in your maneuvers and facilitates piece coordination.
        // As the game progresses into the middle game,
        // it's essential to ensure the safety of your king.
        // Centralize the king by either castling
        // or relocating it to a safer position away from potential threats.
        // A well-protected king provides stability to your position
        // and enables you to focus on executing your strategic plans.
        // The queen is the most powerful piece on the board
        // and should be utilized effectively during the middle game.
        // Look for opportunities to activate your queen,
        // either by positioning it aggressively to
        // attack your opponent's pieces
        // or by using it to control key squares and support your other pieces' actions.
        // Bishops are valuable pieces that excel in open positions.
        // Keep your bishops active and maintain their mobility
        // by avoiding unnecessary pawn moves that could restrict their scope.
        // Utilize their long-range capabilities to control diagonals
        // and exert pressure on your opponent's position.
        // Rooks are most effective when they are connected along open files or ranks.
        // Coordinate your rooks by placing them on files
        // or ranks where they support each other and have
        // the potential to penetrate your opponent's position.
        // Connected rooks can form powerful battery attacks
        // and create threats that are difficult for your opponent to defend against.

        // In the middle game,
        // aim to either capture your opponent's pieces
        // or force them into unfavorable positions.
        // Look for opportunities to initiate exchanges
        // that improve your position or create imbalances in material.
        // Alternatively, if you have the initiative,
        // apply pressure on your opponent's pieces and force them to retreat,
        // allowing you to gain space or launch an attack.
        // Weak squares in your position can become targets for your opponent's pieces
        // or potential entry points for an attack.
        // Identify and shore up any weaknesses in your position
        // by controlling these squares with your pieces or pawns.
        // Conversely, exploit your opponent's weak squares
        // by maneuvering your pieces to occupy or attack them,
        // thereby gaining positional advantages.

        // the middlegame comes to an end when players
        // start exchanging most of the major pieces
        // like queens and rooks or when
        // the kings become more active on the board.

        // In the endgames, nothing has changed.
        //We should still try to mate the King.
        //And if you ask “But how to do that with limited resources on the board?”,
        // that's a good question! The answer is - we need a Queen!
        //That's why the pawns, especially the passed pawns, become the star of the show!
        // 2. Create a passed pawn
        //The 2nd step. What if we don’t have a passed pawn?
        //And we can’t get a Queen?
        //The answer is we have to create one!
        // Using a pawn majority to create a passed pawn is the main way
        // But what if we don’t have a pawn majority?
        //We should try to win pawns,
        // so we can get a pawn majority,
        // then create a passed pawn, then get a Queen
        // The 2 main ways are:
        // Attacking the weak pawns
        // Entering the opponent’s camp
        // Now, what if we don’t have a passed pawn,
        // we don’t have a pawn majority and we can’t win any pawn?
        // We can’t win them immediately? We’ll do it slowly.
        // In the endgame, the pieces are effective if they target
        // or potentially can target the opponent’s weak pawns!
        // “Activate the King” in the endgames. But now you know why we do this.
        //Mainly for winning pawns!
        //We do not just blindly take the King to the center as many resources advise.
        //As with the other pieces,
        // we try to target the opponent’s weak pawns.
        // And if there are no weak pawns,
        // only then we usually take the king to the center.
        // Winning pawns (often when you win a pawn, the adjacent pawn(s) become weak!)
        //Exchanging pawns
        //Exchanging pieces
        //Provoking
        // As we’ve learned, we’ll often try to win pawns by entering the opponent’s camp.
        // And we can easily do this by using weak squares.
        // And another great way of using weak squares is to make them outposts for our pieces,
        // where they can become most effective!
        // We need a Queen, for that we need a passed pawn,
        // for a passed pawn we often need a pawn majority,
        // to achieve a pawn majority we need to win pawns.
        //And activating the pieces, the King,
        // and using the weak pawns and weak squares are paths to remember!
        // Attack the King directly
        //Directly mating the King in the endgame
        // will not often happen because of limited resources on the board.
        // But even in this phase of the game, there will be chances to do it.

        // if not everything make a useless move not putting anything in danger
//    }

    public int [] makeBestMove() {
        Model tempModel = model;
        boolean tempopening = opening;
        boolean tempmiddleGame = middleGame;
        boolean tempendGame = endGame;
        int temptotalMoves = totalMoves;
        long allPieces = Piece.allPieces;
        long whitePieces = Piece.whitePieces;
        long blackPieces = Piece.blackPieces;
        boolean check = Piece.check;
        moveSums = new MoveSum[5];
        for(int i = 0; i < 5; i++) {
            this.model = tempModel.getCopy();
            this.opening = tempopening;
            this.middleGame = tempmiddleGame;
            this.endGame = tempendGame;
            this.totalMoves = temptotalMoves;
            boolean whiteTurn = model.isWhiteTurn();
            makeMove();
            moveSums[i] = new MoveSum(lastMove, 0);
            depth++;
            makeMove();
            makeMove();
            ArrayList<ArrayList<Piece>> pieces = whiteTurn ? model.getWhitePieces() : model.getBlackPieces();
            ArrayList<ArrayList<Piece>> opponentPieces = whiteTurn ? model.getBlackPieces() : model.getWhitePieces();
            double score = evaluatePosition(pieces, model) -
                    evaluatePosition(opponentPieces, model);
            moveSums[i].setScore(score);
            depth = 0;
            restoreStatics(allPieces, whitePieces, blackPieces, check);
        }
        int [] bestMove = moveSums[0].getMove();
        double maxScore = moveSums[0].getScore();
        for(int i = 1; i < 5; i++) {
            if(moveSums[i].getScore() > maxScore) {
                bestMove = moveSums[i].getMove();
                maxScore = moveSums[i].getScore();
            }
        }
        this.model = tempModel;
        this.opening = tempopening;
        this.middleGame = tempmiddleGame;
        this.endGame = tempendGame;
        this.totalMoves = temptotalMoves;
        if(maxScore < -10){
            makeSafeBestMove();
        }
        else {
            model.setSelectedPiece(bestMove[0], bestMove[1]);
            model.updateTurn(bestMove[0], bestMove[1], bestMove[2], bestMove[3]);
            lastMove = new int[]{bestMove[0], bestMove[1], bestMove[2], bestMove[3]};
        }
        totalMoves++;
        if(totalMoves == 12) {
            opening = false;
            middleGame = true;
        }
        return lastMove;
    }

    private void restoreStatics(long allPieces, long whitePieces, long blackPieces, boolean check) {
        Piece.allPieces = allPieces;
        Piece.whitePieces = whitePieces;
        Piece.blackPieces = blackPieces;
        Piece.check = check;
    }

    private void makeMove() {
        System.out.println("Entering makeMove()");
        if (Piece.check) {
            System.out.println("King is in check. Handling check situation.");
            handleCheckSituation();
        } else if (!attemptCheckmate()) {
            System.out.println("No checkmate attempt possible. Preventing opponent checkmate.");
            if (!preventOpponentCheckmate()) {
                ArrayList<ArrayList<Piece>> pieces = model.isWhiteTurn() ? model.getWhitePieces() : model.getBlackPieces();
                ArrayList<ArrayList<Piece>> opponentPieces = model.isWhiteTurn() ? model.getBlackPieces() : model.getWhitePieces();
                PieceInfo pInfo = getMostThreatenedPiece(pieces);
                PieceInfo oppPInfo = getMostThreatenedPiece(opponentPieces);
                double score = evaluatePosition(pieces, model) - evaluatePosition(opponentPieces, model);
                System.out.println("Score: " + score + ", Most threatened piece: " + pInfo.getPiece() + ", Opponent's most threatened piece: " + oppPInfo.getPiece());

                if (pInfo.getAction().equals("move")) {
                    System.out.println("Making the best move for the most threatened piece.");
                    makeBestMoveForPiece(pInfo.getPiece());
                } else if (pInfo.getAction().equals("defend")) {
                    System.out.println("Defending the most threatened piece.");
                    if (oppPInfo.getAction().equals("move") ||
                            (oppPInfo.getEvaluation() < pInfo.getEvaluation() && oppPInfo.getAction().equals("defend"))) {
                        System.out.println("Taking the best way possible to handle opponent's piece.");
                        takeBestWayPossible(oppPInfo.getPiece(), pieces);
                    } else {
                        System.out.println("Defending the best way possible.");
                        defendBestWayPossible(pInfo.getPiece(), pieces);
                    }
                } else {
                    System.out.println("No immediate threats. Proceeding with strategic moves.");
                    if (oppPInfo.getAction().equals("move") ||
                            (oppPInfo.getEvaluation() < 0 && oppPInfo.getAction().equals("defend"))) {
                        System.out.println("Taking the best way possible to handle opponent's piece.");
                        takeBestWayPossible(oppPInfo.getPiece(), pieces);
                    } else if (opening) {
                        System.out.println("Making an opening move.");
                        makeAnOpeningMove(pieces, score);
                    } else if (middleGame) {
                        System.out.println("Making a middle game move.");
                        makeAMiddleGameMove(pieces, score, pInfo, oppPInfo);
                    } else if (endGame) {
                        System.out.println("Making an end game move.");
                        makeAnEndGameMove(pieces, score);
                    }
                }
            }
        }
        totalMoves++;
        if (totalMoves == 12) {
            System.out.println("Transitioning from opening to middle game.");
            opening = false;
            middleGame = true;
        }
        System.out.println("Exiting makeMove() with lastMove: " + lastMove[0] + ", " + lastMove[1] + ", " + lastMove[2] + ", " + lastMove[3]);
    }

    private boolean chechIfMoveHasBeenMade(int [] move) {
        for (int i = 0; i < moveSums.length; i++) {
            if(moveSums[i] == null) {
                continue;
            }
            if(moveSums[i].getMove()[0] == move[0] && moveSums[i].getMove()[1] == move[1] &&
                    moveSums[i].getMove()[2] == move[2] && moveSums[i].getMove()[3] == move[3]) {
                return true;
            }
        }
        return false;
    }

    private void makeSafeBestMove() {
        System.out.println("Making a safe best move.");
        ArrayList<ArrayList<Piece>> pieces = model.isWhiteTurn() ? model.getWhitePieces() : model.getBlackPieces();
        double maxPos = -Double.MAX_VALUE;
        int [] maxRowCol = {-1, -1};
        int [] pieceRowCol = {-1, -1};
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                long moves = getAvailableMoves(piece.getBitboard(), model);
                if(moves != 0L) {
                    for(int i = 0; i < 64; i++) {
                        long move = 1L << i;
                        if ((moves & move) != 0) {
                            double pos = simulateMoveAndEvaluate(piece, move, false);
                            int [] movePosition = movePositionToRowCol(move);
                            int [] position = movePositionToRowCol(piece.getBitboard());
                            if(chechIfMoveHasBeenMade(new int[]{position[0], position[1], movePosition[0], movePosition[1]}) && depth == 0) {continue;}
                            if (pos > maxPos) {
                                maxPos = pos;
                                maxRowCol = movePosition;
                                pieceRowCol = position;
                            }
                        }
                    }
                }
            }
        }
        if (pieceRowCol[0] == -1 || maxRowCol[0] == -1) {
            System.out.println("No valid moves found. Exiting makeSafeBestMove.");
            return;
        }
        model.setSelectedPiece(pieceRowCol[0], pieceRowCol[1]);
        lastMove = new int[]{pieceRowCol[0], pieceRowCol[1], maxRowCol[0], maxRowCol[1]};
        model.updateTurn(pieceRowCol[0], pieceRowCol[1], maxRowCol[0], maxRowCol[1]);
    }

    private void makeAnEndGameMove(ArrayList<ArrayList<Piece>> pieces, double score) {
        System.out.println("Making an end game move.");
        if(score >= 8) {
            // i need to implement more checkmating techniques

        } else if (score >= 5) {
            if(attacKing(pieces, score)) return;
        }

        // Step 1: Identify passed pawns
        Piece passedPawn = findPassedPawn(pieces);
        if (passedPawn != null) {
            if(promotePawn(passedPawn))
                return;
        }

        // Step 2: Create a passed pawn
        ArrayList<ArrayList<Piece>> opponentPieces = model.isWhiteTurn() ? model.getBlackPieces() : model.getWhitePieces();
        ArrayList<Piece> weakPawns = findWeakPawns(opponentPieces);
        if (!weakPawns.isEmpty()) {
            for (Piece weakPawn : weakPawns) {
                if (attackBestWayPossible(weakPawn, pieces)) {
                    return;
                } else {
                    if(makeBestMoveInvolvingKing(pieces)) {
                        return;
                    }
                }
            }
        }

        // Step 3: Attack weak opponent pieces
        ArrayList<Piece> weakPieces = getWeakOppPieces();
        if(!weakPieces.isEmpty()) {
            for (int i = weakPieces.size() - 1; i >= 0; i--) {
                Piece p = weakPieces.get(i);
                if (attackBestWayPossible(p, pieces)) return;
            }
        }

        // If no move is found, make a safe best move
        makeSafeBestMove();
    }

    private Piece findPassedPawn(ArrayList<ArrayList<Piece>> pieces) {
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                if (piece instanceof WhitePawn || piece instanceof BlackPawn) {
                    if (isPassedPawn(piece, pieces)) {
                        return piece;
                    }
                }
            }
        }
        return null;
    }

    private boolean isPassedPawn(Piece p, ArrayList<ArrayList<Piece>> pieces) {
        long pawnPosition = p.getBitboard();
        boolean isWhite = p.isWhite();

        // Masks for file boundaries
        long notAFile = 0xFEFEFEFEFEFEFEFEL; // Exclude A-file
        long notHFile = 0x7F7F7F7F7F7F7F7FL; // Exclude H-file

        // Get opponent pawns
        ArrayList<Piece> ownPawns = pieces.get(0);
        long opponentPawns = 0L;
        for (Piece pawn : ownPawns) {
            opponentPawns |= pawn.getBitboard();
        }

        // Calculate the pawn's file and adjacent files
        long pawnFile = pawnPosition;
        long leftFile = (pawnPosition << 1) & notAFile;
        long rightFile = (pawnPosition >> 1) & notHFile;

        // Combine the pawn's file and adjacent files
        long relevantFiles = pawnFile | leftFile | rightFile;

        // Determine the ranks ahead of the pawn
        long ranksAhead = isWhite ? (relevantFiles << 8) | (relevantFiles << 16) | (relevantFiles << 24) | (relevantFiles << 32) | (relevantFiles << 40)
                : (relevantFiles >> 8) | (relevantFiles >> 16) | (relevantFiles >> 24) | (relevantFiles >> 32) | (relevantFiles >> 40);
        // Check if there are any opponent pawns in the ranks ahead
        return (ranksAhead & opponentPawns) == 0;
    }

    private boolean promotePawn(Piece pawn) {
        long pawnPosition = pawn.getBitboard();
        int[] pawnRowCol = movePositionToRowCol(pawnPosition);

        // Determine the direction of movement based on the pawn's color
        int direction = pawn.isWhite() ? -1 : 1;

        int promotionRow = pawn.isWhite() ? 0 : 7; // Promotion row for white is rank 0, for black is rank 7
        // Move the pawn step by step toward the promotion row
        int[] nextRowCol = {pawnRowCol[0] + direction, pawnRowCol[1]};
        if(chechIfMoveHasBeenMade(new int[]{pawnRowCol[0], pawnRowCol[1], nextRowCol[0], nextRowCol[1]}) && depth == 0) {return false;}
        model.setSelectedPiece(pawnRowCol[0], pawnRowCol[1]);
        lastMove = new int[]{pawnRowCol[0], pawnRowCol[1], pawnRowCol[0] + direction, pawnRowCol[1]};
        model.updateTurn(pawnRowCol[0], pawnRowCol[1], pawnRowCol[0] + direction, pawnRowCol[1]);
        pawnRowCol = nextRowCol;
        if(pawnRowCol[0] == promotionRow) {
            // Replace the pawn with a Queen (or another piece)
            Piece promotedPiece = new Queen(1L << (promotionRow * 8 + pawnRowCol[1]), pawn.isWhite());
            model.replacePiece(pawn, promotedPiece);
        }
        return true;
    }

    private boolean makeBestMoveInvolvingKing(ArrayList<ArrayList<Piece>> pieces) {
        System.out.println("Making the best move involving the King.");
        ArrayList<Piece> weakOpponentPawns = findWeakPawns(pieces.get(0).get(0).isWhite() ? model.getBlackPieces() : model.getWhitePieces()); // Get weak opponent pawns
        double maxScore = -Double.MAX_VALUE;
        int[] bestMoveStart = {-1, -1};
        int[] bestMoveEnd = {-1, -1};

        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                long moves = getAvailableMoves(piece.getBitboard(), model);
                int[] pieceRowCol = movePositionToRowCol(piece.getBitboard());

                for (int i = 0; i < 64; i++) {
                    long move = 1L << i;
                    if ((moves & move) != 0) {
                        int[] moveRowCol = movePositionToRowCol(move);
                        if(chechIfMoveHasBeenMade(new int[]{pieceRowCol[0], pieceRowCol[1], moveRowCol[0], moveRowCol[1]}) && depth == 0) {continue;}
                        // Calculate the distance to the nearest weak opponent pawn
                        double minDistanceToWeakPawn = Double.MAX_VALUE;
                        // Calculate the minimum distance to any weak opponent pawn
                        for (Piece weakPawn : weakOpponentPawns) {
                            int[] weakPawnRowCol = movePositionToRowCol(weakPawn.getBitboard());
                            double distance = calculateDistance(moveRowCol, weakPawnRowCol);
                            minDistanceToWeakPawn = Math.min(minDistanceToWeakPawn, distance);
                        }

                        double positionScore = simulateMoveAndEvaluate(piece, move, false);

                        // Combine position score and proximity to the weak pawns
                        double combinedScore = positionScore - minDistanceToWeakPawn;
                        if (combinedScore > maxScore) {
                            maxScore = combinedScore;
                            bestMoveStart = pieceRowCol;
                            bestMoveEnd = moveRowCol;
                        }
                    }
                }
            }
        }

        if (bestMoveStart[0] != -1 && bestMoveEnd[0] != -1) {
            model.setSelectedPiece(bestMoveStart[0], bestMoveStart[1]);
            lastMove = new int[]{bestMoveStart[0], bestMoveStart[1], bestMoveEnd[0], bestMoveEnd[1]};
            model.updateTurn(bestMoveStart[0], bestMoveStart[1], bestMoveEnd[0], bestMoveEnd[1]);
            return true;
        }
        return false;
    }

    private double calculateDistance(int[] pos1, int[] pos2) {
        return Math.sqrt(Math.pow(pos1[0] - pos2[0], 2) + Math.pow(pos1[1] - pos2[1], 2));
    }

    private ArrayList<Piece> findWeakPawns(ArrayList<ArrayList<Piece>> opponentPieces) {
        ArrayList<Piece> weakPawns = new ArrayList<>();
        for (ArrayList<Piece> pieceList : opponentPieces) {
            for (Piece piece : pieceList) {
                if (piece instanceof WhitePawn || piece instanceof BlackPawn) {
                    if (isWeakPawn(piece, opponentPieces)) {
                        weakPawns.add(piece);
                    }
                }
            }
        }
        return weakPawns;
    }

    private boolean isWeakPawn(Piece pawn, ArrayList<ArrayList<Piece>> pieces) {
        long pawnPosition = pawn.getBitboard();
        boolean isWhite = pawn.isWhite();

        // Masks for file boundaries
        long notAFile = 0xFEFEFEFEFEFEFEFEL; // Exclude A-file
        long notHFile = 0x7F7F7F7F7F7F7F7FL; // Exclude H-file

        // Get adjacent files
        long leftFile = (pawnPosition << 1) & notAFile;
        long rightFile = (pawnPosition >> 1) & notHFile;

        // Get all pawns of the same color
        ArrayList<Piece> ownPawns = pieces.get(0);
        long opponentPawns = 0L;
        for (Piece p : ownPawns) {
            opponentPawns |= p.getBitboard();
        }

        // Check ranks below the pawn
        long ranksBelow = isWhite
                ? (leftFile >> 8) | (rightFile >> 8) // White pawns move down
                : (leftFile << 8) | (rightFile << 8); // Black pawns move up

        // If no pawns are in the adjacent files below, the pawn is weak
        return (ranksBelow & opponentPawns) == 0;
    }



    private ArrayList<Piece> getWeakOppPieces() {
        ArrayList<Piece> weakPieces = new ArrayList<>();
        ArrayList<ArrayList<Piece>> opponentPieces = model.isWhiteTurn() ? model.getBlackPieces() : model.getWhitePieces();
        for (ArrayList<Piece> pieceList : opponentPieces) {
            for (Piece piece : pieceList) {
                if(evaluateThreat(piece, opponentPieces, model).getEvaluation() < 0) {
                    weakPieces.add(piece);
                }
            }
        }
        return weakPieces;
    }

    private boolean attacKing(ArrayList<ArrayList<Piece>> pieces, double score) {
        System.out.println("Attacking the King.");
        double maxPos = -Double.MAX_VALUE;
        int [] max = {-1, -1}, poStart = {-1, -1};
        King king = (King)pieces.get(5).get(0);
        long kingPos = king.getBitboard();
        long kingSafteyPos = getKingSurroundingBits(kingPos);
        for(ArrayList<Piece> pieceList : pieces) {
            for (Piece p : pieceList) {
                long moves = getAvailableMoves(p.getBitboard(), model);
                if((moves & kingSafteyPos) != 0) {
                    moves &= kingSafteyPos;
                    if(Long.bitCount(moves) > 1) {
                        for (int i = 0; i < 64; i++) {
                            long move = 1L << i;
                            if ((moves & move) != 0) {
                                double s = simulateMoveAndEvaluate(p, move, false);
                                int [] movePosition = movePositionToRowCol(move), position = movePositionToRowCol(p.getBitboard());
                                if(chechIfMoveHasBeenMade(new int[]{position[0], position[1], movePosition[0], movePosition[1]}) && depth == 0) {continue;}
                                if (s > maxPos) {
                                    maxPos = s;
                                    max = movePosition;
                                    poStart = position;
                                }
                            }
                        }
                    }
                    else {
                        double s = simulateMoveAndEvaluate(p, moves, false);
                        int [] movePosition = movePositionToRowCol(moves), position = movePositionToRowCol(p.getBitboard());
                        if(chechIfMoveHasBeenMade(new int[]{position[0], position[1], movePosition[0], movePosition[1]}) && depth == 0) {continue;}
                        if (s > maxPos) {
                            maxPos = s;
                            max = movePosition;
                            poStart = position;
                        }
                    }
                }
            }
        }
        if(max[0] != -1 && max[1] != -1 && maxPos > score) {
            model.setSelectedPiece(poStart[0], poStart[1]);
            lastMove = new int[]{poStart[0], poStart[1], max[0], max[1]};
            model.updateTurn(poStart[0], poStart[1], max[0], max[1]);
            return true;
        }
        return false;
    }

    private long getKingSurroundingBits(long kingPosition) {
        long notAFile = 0xFEFEFEFEFEFEFEFEL; // Mask to exclude the A-file
        long notHFile = 0x7F7F7F7F7F7F7F7FL; // Mask to exclude the H-file

        long left = (kingPosition << 1) & notAFile;
        long right = (kingPosition >> 1) & notHFile;
        long up = kingPosition << 8;
        long down = kingPosition >> 8;

        long upLeft = (up << 1) & notAFile;
        long upRight = (up >> 1) & notHFile;
        long downLeft = (down << 1) & notAFile;
        long downRight = (down >> 1) & notHFile;

        return left | right | up | down | upLeft | upRight | downLeft | downRight;
    }

    private void makeAMiddleGameMove(ArrayList<ArrayList<Piece>> pieces, double score, PieceInfo piece, PieceInfo oppPiece) {
        System.out.println("Making a middle game move.");
        double maxPos = -Double.MAX_VALUE;
        int [] max = {-1, -1}, poStart = {-1, -1};
        King king = (King)pieces.get(5).get(0);
        long kingPos = king.getBitboard();
        poStart = movePositionToRowCol(kingPos);
        double evaluation = pieces.get(0).get(0).isWhite() ? Finals.WhitePiecePosition[0][poStart[0]][poStart[1]] : Finals.BlackPiecePosition[0][poStart[0]][poStart[1]];
        if(evaluation > 2) {
            ArrayList<Piece> rooks = pieces.get(3);
            for(Piece r : rooks) {
                double s = simulateMoveAndEvaluate(king, r.getBitboard(), true);
                int [] movePosition = movePositionToRowCol(r.getBitboard());
                if(chechIfMoveHasBeenMade(new int[]{poStart[0], poStart[1], movePosition[0], movePosition[1]}) && depth == 0) {continue;}
                if(s > maxPos) {
                    maxPos = s;
                    max = movePosition;
                }
            }
            if(score != -Double.MAX_VALUE) {
                if(max[0] != -1 && max[1] != -1 && maxPos > score) {
                    model.setSelectedPiece(poStart[0], poStart[1]);
                    lastMove = new int[]{poStart[0], poStart[1], max[0], max[1]};
                    model.updateTurn(poStart[0], poStart[1], max[0], max[1]);
                    return;
                }
            }
        }
        if(score >= 4)
            attacKing(pieces, score);
        else if(score > 0){
            attackBestWayPossible(oppPiece.getPiece(), pieces);
        } else {
            defendBestWayPossible(piece.getPiece(), pieces);
        }
    }

    private boolean attackBestWayPossible(Piece piece, ArrayList<ArrayList<Piece>> pieces) {
        System.out.println("Attacking the best way possible.");
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece p : pieceList) {
                long moves = getAvailableMoves(p.getBitboard(), model);
                for(int i = 0; i < 64; i++) {
                    long move = 1L << i;
                    if ((moves & move) != 0) {
                        long allPieces = Piece.allPieces;
                        long whitePieces = Piece.whitePieces;
                        long blackPieces = Piece.blackPieces;
                        boolean check = Piece.check;
                        simulateMoveAndEvaluateNoRevert(p, move, false);
                        long attacks = getAvailableMoves(move, copy);
                        restoreStatics(allPieces, whitePieces, blackPieces, check);
                        if((attacks & piece.getBitboard()) != 0) {
                            int[] selectedRowCol = movePositionToRowCol(p.getBitboard());
                            int[] moveRowCol = movePositionToRowCol(move);
                            if(chechIfMoveHasBeenMade(new int[]{selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]}) && depth == 0) {continue;}
                            model.setSelectedPiece(selectedRowCol[0], selectedRowCol[1]);
                            lastMove = new int[]{selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]};
                            model.updateTurn(selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void makeAnOpeningMove(ArrayList<ArrayList<Piece>> pieces, double score) {
        System.out.println("Making an opening move.");
        int [] max = {-1, -1}, poStart = {-1, -1};
        double maxPos = -Double.MAX_VALUE;
        for(ArrayList<Piece> pieceList : pieces) {
            for(Piece p : pieceList) {
                 if(p instanceof Knight){
                        long moves = getAvailableMoves(p.getBitboard(), model);
                        for(int i = 0; i < 64; i++) {
                            long move = 1L << i;
                            if ((moves & move) != 0) {
                                int [] movePosition = movePositionToRowCol(move), position = movePositionToRowCol(p.getBitboard());
                                if(chechIfMoveHasBeenMade(new int[]{position[0], position[1], movePosition[0], movePosition[1]}) && depth == 0) {continue;}
                                double pos = simulateMoveAndEvaluate(p, move, false);
                                if( pos > maxPos) {
                                    maxPos = pos;
                                    max = movePosition;
                                    poStart = position;
                                }
                            }
                        }
                 } else if ((p instanceof WhitePawn || p instanceof BlackPawn) && (p.getBitboard() & (0x3C00L | 0x003C000000000000L)) != 0) {
                        long moves = getAvailableMoves(p.getBitboard(), model);
                        for(int i = 0; i < 64; i++) {
                            long move = 1L << i;
                            if ((moves & move) != 0) {
                                int [] movePosition = movePositionToRowCol(move), position = movePositionToRowCol(p.getBitboard());
                                if((chechIfMoveHasBeenMade(new int[]{position[0], position[1], movePosition[0], movePosition[1]})) && depth == 0) {continue;}
                                double pos = simulateMoveAndEvaluate(p, move, false) + 0.05;
                                if( pos > maxPos) {
                                    maxPos = pos;
                                    max = movePosition;
                                    poStart = position;
                                }
                            }
                        }
                 } else if (p instanceof Bishop) {
                     long moves = getAvailableMoves(p.getBitboard(), model);
                        for(int i = 0; i < 64; i++) {
                            long move = 1L << i;
                            if ((moves & move) != 0) {
                                int [] movePosition = movePositionToRowCol(move), position = movePositionToRowCol(p.getBitboard());
                                if(chechIfMoveHasBeenMade(new int[]{position[0], position[1], movePosition[0], movePosition[1]}) && depth == 0) {continue;}
                                double pos = simulateMoveAndEvaluate(p, move, false);
                                if( pos > maxPos) {
                                    maxPos = pos;
                                    max = movePosition;
                                    poStart = position;
                                }
                            }
                        }
                 }
            }
        }
        if (poStart[0] == -1 || poStart[1] == -1 || max[0] == -1 || max[1] == -1) {
            System.out.println("Fallback — makeSafeBestMove()");
            makeSafeBestMove();
            return;
        }
        model.setSelectedPiece(poStart[0], poStart[1]);
        lastMove = new int[]{poStart[0], poStart[1], max[0], max[1]};
        model.updateTurn(poStart[0], poStart[1], max[0], max[1]);
    }

    private void makeBestMoveForPiece(Piece p) {
        System.out.println("Making the best move for a piece.");
        ArrayList<ArrayList<Piece>> pieces = model.isWhiteTurn() ? model.getWhitePieces() : model.getBlackPieces();
        double maxPos = -Double.MAX_VALUE;
        int [] maxRowCol = {-1, -1};
        int [] pieceRowCol = movePositionToRowCol(p.getBitboard());
        long moves = getAvailableMoves(p.getBitboard(), model);
        model.setSelectedPiece(pieceRowCol[0], pieceRowCol[1]);
        for(int i = 0; i < 64; i++) {
            long move = 1L << i;
            if ((moves & move) != 0) {
                int [] movePosition = movePositionToRowCol(move);
                if(chechIfMoveHasBeenMade(new int[]{pieceRowCol[0], pieceRowCol[1], movePosition[0], movePosition[1]}) && depth == 0) {continue;}
                double pos = simulateMoveAndEvaluate(p, move, false);
                if (pos > maxPos) {
                    maxPos = pos;
                    maxRowCol = movePosition;
                }
            }
        }
        if(maxRowCol[0] == -1 || maxRowCol[1] == -1) {
            System.out.println("Fallback — makeSafeBestMove()");
            makeSafeBestMove();
            return;
        }
        lastMove = new int[]{pieceRowCol[0], pieceRowCol[1], maxRowCol[0], maxRowCol[1]};
        model.updateTurn(pieceRowCol[0], pieceRowCol[1], maxRowCol[0], maxRowCol[1]);
    }

    private void defendBestWayPossible(Piece piece, ArrayList<ArrayList<Piece>> pieces) {
        System.out.println("Defending the best way possible.");
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece p : pieceList) {
                if(p.getBitboard() == piece.getBitboard()) {
                    continue;
                }
                long moves = getAvailableMoves(p.getBitboard(), model);
                for(int i = 0; i < 64; i++) {
                    long move = 1L << i;
                    if ((moves & move) != 0) {
                        long allPieces = Piece.allPieces;
                        long whitePieces = Piece.whitePieces;
                        long blackPieces = Piece.blackPieces;
                        boolean check = Piece.check;
                        simulateMoveAndEvaluateNoRevert(p, move, false);
                        if (p.isWhite()) {
                            Piece.blackPieces |= Piece.whitePieces;
                            Piece.whitePieces = 0;

                        } else {
                            Piece.whitePieces |= Piece.blackPieces;
                            Piece.blackPieces = 0;
                        }
                        long defences = getAvailableMoves(move, copy);
                        restoreStatics(allPieces, whitePieces, blackPieces, check);
                        if((defences & piece.getBitboard()) != 0) {
                            int[] selectedRowCol = movePositionToRowCol(p.getBitboard());
                            int[] moveRowCol = movePositionToRowCol(move);
                            if(chechIfMoveHasBeenMade(new int[]{selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]}) && depth == 0) {continue;}
                            model.setSelectedPiece(selectedRowCol[0], selectedRowCol[1]);
                            lastMove = new int[]{selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]};
                            model.updateTurn(selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]);
                            return;
                        }
                    }
                }
            }
        }
        makeSafeBestMove();
    }



    private boolean takeBestWayPossible(Piece piece, ArrayList<ArrayList<Piece>> pieces) {
        System.out.println("Taking the best way possible.");
        for(ArrayList<Piece> pieceList : pieces) {
            for(Piece p : pieceList) {
                long moves = getAvailableMoves(p.getBitboard(), model);
                if((moves & piece.getBitboard()) != 0) {
                    for(int i = 0; i < 64; i++) {
                        long move = 1L << i;
                        if((move & piece.getBitboard()) != 0) {
                            int[] selectedRowCol = movePositionToRowCol(p.getBitboard());
                            int[] moveRowCol = movePositionToRowCol(move);
                            if(chechIfMoveHasBeenMade(new int[]{selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]}) && depth == 0) {continue;}
                            model.setSelectedPiece(selectedRowCol[0], selectedRowCol[1]);
                            lastMove = new int[]{selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]};
                            model.updateTurn(selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void handleCheckSituation() {
        System.out.println("Handling check situation.");
        // Iterate through all pieces to find moves that get out of check
        ArrayList<ArrayList<Piece>> pieces = model.isWhiteTurn() ? model.getWhitePieces() : model.getBlackPieces();
        double maxPos = -Double.MAX_VALUE;
        int [] maxRowCol = {-1, -1};
        int [] pieceRowCol = {-1, -1};
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                long moves = getAvailableMoves(piece.getBitboard(), model);
                int [] position = movePositionToRowCol(piece.getBitboard());
                if(moves != 0) {
                    for(int i = 0; i < 64; i++) {
                        long move = 1L << i;
                        if ((moves & move) != 0) {
                            int [] movePosition = movePositionToRowCol(move);
                            if(chechIfMoveHasBeenMade(new int[]{position[0], position[1], movePosition[0], movePosition[1]}) && depth == 0) {continue;}
                            double pos = simulateMoveAndEvaluate(piece, move, false);
                            if (pos > maxPos) {
                                maxPos = pos;
                                maxRowCol = movePosition;
                                pieceRowCol = position;
                            }
                        }
                    }
                }
            }
        }
        if(maxRowCol[0] == -1 || maxRowCol[1] == -1) {
            System.out.println("Fallback — makeSafeBestMove()");
            makeSafeBestMove();
            return;
        }
        model.setSelectedPiece(pieceRowCol[0], pieceRowCol[1]);
        lastMove = new int[]{pieceRowCol[0], pieceRowCol[1], maxRowCol[0], maxRowCol[1]};
        model.updateTurn(pieceRowCol[0], pieceRowCol[1], maxRowCol[0], maxRowCol[1]);
    }

    private boolean attemptCheckmate() {
        System.out.println("Attempting checkmate.");
        // Iterate through all pieces to find a move that results in checkmate
        ArrayList<ArrayList<Piece>> pieces = model.isWhiteTurn() ? model.getWhitePieces() : model.getBlackPieces();
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                long moves = getAvailableMoves(piece.getBitboard(), model);
                for(int i = 0; i < 64; i++) {
                    long move = 1L << i;
                    if ((moves & move) != 0) {
                        if (simulateMoveAndEvaluate(piece, move, false) == Double.MAX_VALUE) {
                            int[] selectedRowCol = movePositionToRowCol(piece.getBitboard());
                            model.setSelectedPiece(selectedRowCol[0], selectedRowCol[1]);
                            int[] moveRowCol = movePositionToRowCol(move);
                            if(chechIfMoveHasBeenMade(new int[]{selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]}) && depth == 0) {continue;}
                            lastMove = new int[]{selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]};
                            model.updateTurn(selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean preventOpponentCheckmate() {
        System.out.println("Preventing opponent checkmate.");
        // Iterate through all pieces to block or prevent opponent's checkmate
        ArrayList<ArrayList<Piece>> oppPieces = model.isWhiteTurn() ? model.getBlackPieces() : model.getWhitePieces();
        for (ArrayList<Piece> pieceList : oppPieces) {
            for (Piece piece : pieceList) {
                long moves = getAvailableMoves(piece.getBitboard(), model);
                for (int i = 0; i < 64; i++) {
                    long move = 1L << i;
                    if ((moves & move) != 0) {
                        if(simulateMoveAndEvaluate(piece, move, false) == -Double.MAX_VALUE){
                            if(!preventCheckMate(move)){
                                if(!takeBestWayPossible(piece, (oppPieces == model.getWhitePieces()) ? model.getBlackPieces() : model.getWhitePieces())) {
                                    makeSafeBestMove();
                                }
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean preventCheckMate(long move) {
        System.out.println("Preventing checkmate.");
        // Iterate through all pieces to block or prevent opponent's checkmate
        ArrayList<ArrayList<Piece>> pieces = model.isWhiteTurn() ? model.getWhitePieces() : model.getBlackPieces();
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                long moves = getAvailableMoves(piece.getBitboard(), model);
                for (int i = 0; i < 64; i++) {
                    long movePos = 1L << i;
                    if ((moves & movePos) != 0) {
                        simulateMoveAndEvaluate(piece, movePos, false);
                        long possibleMoves = getAvailableMoves(movePos, copy);
                        if((possibleMoves & move) != 0){
                            int [] selectedRowCol = movePositionToRowCol(piece.getBitboard());
                            model.setSelectedPiece(selectedRowCol[0], selectedRowCol[1]);
                            int [] moveRowCol = movePositionToRowCol(movePos);
                            if(chechIfMoveHasBeenMade(new int[]{selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]}) && depth == 0) {continue;}
                            lastMove = new int[]{selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]};
                            model.updateTurn(selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    private long getAvailableMoves(long pos, Model model) {
        this.copy = model.getCopy();
        int[]rowCol = movePositionToRowCol(pos);
        copy.setSelectedPiece(rowCol[0], rowCol[1]);
        return copy.getPossibleMoves();
    }

    private double simulateMoveAndEvaluate(Piece piece, long movePosition, boolean isCastle) {
        long allPieces = Piece.allPieces;
        long whitePieces = Piece.whitePieces;
        long blackPieces = Piece.blackPieces;
        boolean check = Piece.check;
        this.copy = model.getCopy();
        int[] selectedRowCol = movePositionToRowCol(piece.getBitboard());
        copy.setSelectedPiece(selectedRowCol[0], selectedRowCol[1]);
        int[] moveRowCol = movePositionToRowCol(movePosition);
        if(isCastle){
            copy.isLegalMove(selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]);
        }else {
            copy.updateTurn(selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]);
        }
        if (copy.isCheckmate(!piece.isWhite())) return Double.MAX_VALUE;
        // Evaluate the position based on material, center control, and king safety
        // Queen = 9, rook = 5, knight = bishop = 3, pawn = 1,
        // + to the position of the piece from PiecePosition[x][row][col]
        // if piece threatened -0.2 * weight
        // if piece defended +0.2 * weight
        // if checkmate 0
        double score = evaluatePosition(piece.isWhite() ? copy.getWhitePieces() : copy.getBlackPieces(), copy) -
                evaluatePosition(piece.isWhite() ? copy.getBlackPieces() : copy.getWhitePieces(), copy);
        Piece.allPieces = allPieces;
        Piece.whitePieces = whitePieces;
        Piece.blackPieces = blackPieces;
        Piece.check = check;
        return score;
    }

    private void simulateMoveAndEvaluateNoRevert(Piece piece, long movePosition, boolean isCastle) {
        this.copy = model.getCopy();
        int[] selectedRowCol = movePositionToRowCol(piece.getBitboard());
        copy.setSelectedPiece(selectedRowCol[0], selectedRowCol[1]);
        int[] moveRowCol = movePositionToRowCol(movePosition);
        if(isCastle){
            copy.isLegalMove(selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]);
        }else {
            copy.updateTurn(selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]);
        }
    }

    private int piecePosIndex(Piece p){
        if (p instanceof Queen) {
            return 1;
        } else if (p instanceof Rook) {
            return 2;
        } else if (p instanceof Knight) {
            return 4;
        } else if (p instanceof Bishop) {
            return 3;
        } else if (p instanceof WhitePawn || p instanceof BlackPawn) {
            return 5;
        } else if (p instanceof King) {
            return 0;
        }
        return -1;
    }

    public double evaluatePiece(Piece p, int i, ArrayList<ArrayList<Piece>> pieces) {
        int [] piecePos = movePositionToRowCol(p.getBitboard());
        double score = p.getWeight();
        if(p.isWhite()) {
            score += Finals.WhitePiecePosition[i][piecePos[0]][piecePos[1]];
        }
        else {
            score += Finals.BlackPiecePosition[i][piecePos[0]][piecePos[1]];
        }
        return score;
    }

    public int[] movePositionToRowCol(long movePosition) {
        int index = Long.numberOfTrailingZeros(movePosition); // Get the index of the set bit (0-63)
        int row = index / 8; // Calculate the row (0-7)
        int col = index % 8; // Calculate the column (0-7)
        return new int[]{row, col}; // Return as an array [row, col]
    }

    // Enhanced evaluatePosition with improved heuristics
    private double evaluatePosition(ArrayList<ArrayList<Piece>> pieces, Model model) {
        double score = 0;
        if (model.isCheckmate(!pieces.get(0).get(0).isWhite())) return Double.MAX_VALUE;

        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece p : pieceList) {
                score += evaluatePiece(p, piecePosIndex(p), pieces);
                score += evaluateMobility(p, model);
                score += evaluateCenterControl(p, model);
                score += evaluateActivity(p);
                if (p instanceof WhitePawn || p instanceof BlackPawn) {
                    score += evaluatePawnStructure(p, pieces);
                }
                PieceInfo threatInfo = evaluateThreat(p, pieces, model);
                if(threatInfo.getAction().equals("defend")) {
                    score += threatInfo.getEvaluation() * 4;
                } else if(threatInfo.getAction().equals("move")) {
                    score += threatInfo.getEvaluation() * 6;
                } else if(threatInfo.getAction().equals("nothing")) {
                    score += threatInfo.getEvaluation() * 0.1;
                }
            }
        }
        King king = (King) pieces.get(5).get(0);
        return (score + getKingSaftey(king.getBitboard(), pieces)) * 0.1;
    }

    private double evaluateMobility(Piece p, Model model) {
        long moves = getAvailableMoves(p.getBitboard(), model);
        return 0.05 * Long.bitCount(moves); // reward pieces with more options
    }

    private double evaluateCenterControl(Piece p, Model model) {
        long center = 0x0000001818000000L; // squares d4, e4, d5, e5
        long control = p.possibleMoves(p.getBitboard()) & center;
        return 0.2 * Long.bitCount(control);
    }

    private double evaluateActivity(Piece p) {
        int[] pos = movePositionToRowCol(p.getBitboard());
        boolean isBackRank = p.isWhite() ? pos[0] == 7 : pos[0] == 0;
        return isBackRank ? -0.1 * p.getWeight() : 0.1 * p.getWeight();
    }

    private double evaluatePawnStructure(Piece p, ArrayList<ArrayList<Piece>> pieces) {
        long fileMask = 0x0101010101010101L;
        long position = p.getBitboard();
        int col = movePositionToRowCol(position)[1];
        long colMask = fileMask << col;

        long pawnBitboard = 0;
        for (Piece pawn : pieces.get(0)) {
            pawnBitboard |= pawn.getBitboard();
        }

        boolean isDoubled = Long.bitCount(pawnBitboard & colMask) > 1;
        boolean isIsolated = true;
        if (col > 0) isIsolated &= (pawnBitboard & (fileMask << (col - 1))) == 0;
        if (col < 7) isIsolated &= (pawnBitboard & (fileMask << (col + 1))) == 0;

        double penalty = 0.0;
        if (isDoubled) penalty -= 0.2;
        if (isIsolated) penalty -= 0.3;

        return penalty;
    }

    private PieceInfo evaluateThreat(Piece p, ArrayList<ArrayList<Piece>> allies, Model model) {
        ArrayList<ArrayList<Piece>> opponentPieces = p.isWhite() ? model.getBlackPieces() : model.getWhitePieces();
        long pos = p.getBitboard();

        ArrayList<Piece> opponentThreats = new ArrayList<>();

        for (ArrayList<Piece> group : opponentPieces) {
            for (Piece opp : group) {
                if ((opp.possibleMoves(opp.getBitboard()) & pos) != 0) {
                    opponentThreats.add(opp);
                }
            }
        }

        // Temporarily merge both sides to detect friendly targeting
        long originalWhite = Piece.whitePieces;
        long originalBlack = Piece.blackPieces;

        if (p.isWhite()) {
            Piece.blackPieces |= Piece.whitePieces;
            Piece.whitePieces = 0;

        } else {
            Piece.whitePieces |= Piece.blackPieces;
            Piece.blackPieces = 0;
        }

        ArrayList<Piece> alliesDefenses = new ArrayList<>();
        for (ArrayList<Piece> group : allies) {
            for (Piece ally : group) {
                if ((ally.possibleMoves(ally.getBitboard()) & pos) != 0) {
                    alliesDefenses.add(ally);
                }
            }
        }

        // Restore original state
        Piece.whitePieces = originalWhite;
        Piece.blackPieces = originalBlack;

        if(opponentThreats.isEmpty()) {
            double eval = 0;
            while(!alliesDefenses.isEmpty()) {
                Piece ally = alliesDefenses.remove(0);
                eval += ally.getWeight();
            }
            return new PieceInfo(p, "nothing", eval);
        }
        String action;
        double eval = p.getWeight() * -1;
        while(!alliesDefenses.isEmpty()) {
            Piece opp = opponentThreats.remove(0);
            eval += opp.getWeight();
            Piece ally = alliesDefenses.remove(0);
            if(opponentThreats.isEmpty()) {
                break;
            }
            eval -= ally.getWeight();
        }

        if (eval == 0) {
            action = "nothing";
        } else if (eval <= -2) {
            action = "move";
        } else if (eval < 0) {
            action = "defend";
        } else if (eval < 2) {
            action = "nothing";
        } else {
            action = "nothing";
        }

        return new PieceInfo(p, action, eval);
    }

    private PieceInfo getMostThreatenedPiece(ArrayList<ArrayList<Piece>> pieces) {
        System.out.println("Getting the most threatened piece.");
        PieceInfo mostThreatenedPiece = new PieceInfo(null, "nothing", Double.MAX_VALUE);
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                PieceInfo threatInfo = evaluateThreat(piece, pieces, model);
                if(threatInfo.getAction().equals(mostThreatenedPiece.getAction())){
                    if(threatInfo.getEvaluation() < mostThreatenedPiece.getEvaluation()) {
                        mostThreatenedPiece = threatInfo;
                    }
                } else if (threatInfo.getAction().equals("move")) {
                    mostThreatenedPiece = threatInfo;
                } else if (threatInfo.getAction().equals("defend") && !mostThreatenedPiece.getAction().equals("move")) {
                    mostThreatenedPiece = threatInfo;
                }
            }
        }
        return mostThreatenedPiece;
    }

    public double getKingSaftey(long kingPosition, ArrayList<ArrayList<Piece>> pieces) {
        double score = 0;

        // Masks for file boundaries
        long notAFile = 0xFEFEFEFEFEFEFEFEL; // Exclude A-file
        long notHFile = 0x7F7F7F7F7F7F7F7FL; // Exclude H-file
        long ownPieces = pieces.get(0).get(0).isWhite() ? Piece.whitePieces : Piece.blackPieces;

        // Get all pawns of the king's color
        ArrayList<Piece> ownPawns = pieces.get(0);
        long pawnsBitBoard = 0L;
        for (Piece pawn : ownPawns) {
            pawnsBitBoard |= pawn.getBitboard();
        }

        // Calculate the king's file and adjacent files
        long kingFile = kingPosition;
        long leftFile = (kingPosition << 1) & notAFile;
        long rightFile = (kingPosition >> 1) & notHFile;

        // Extend the files across two ranks above the king
        long rank1 = ((kingFile | leftFile | rightFile) << 8) & 0xFFFFFFFFFFFFFF00L;
        long rank2 = ((kingFile | leftFile | rightFile) << 16) & 0xFFFFFFFFFFFF0000L;

        // Check each file individually for at least one pawn
        long kingFileMask = (0x0101010101010101L << Long.numberOfTrailingZeros(kingFile)) & (rank1 | rank2);
        long leftFileMask = ((0x0101010101010101L << Long.numberOfTrailingZeros(leftFile)) & notAFile) & (rank1 | rank2);
        long rightFileMask = ((0x0101010101010101L << Long.numberOfTrailingZeros(rightFile)) & notHFile) & (rank1 | rank2);

        if ((kingFileMask & ownPieces) == 0) score -= 1.0;
        else score += 0.5;// No pawn in king's file
        if ((leftFileMask & ownPieces) == 0) score -= 1.0;
        else score += 0.5;// No pawn in left file
        if ((rightFileMask & ownPieces) == 0) score -= 1.0;
        else score += 0.5;// No pawn in right file

        // Add 0.5 for each square occupied by a pawn in these files
        int pawnsInFiles = Long.bitCount((rank1 | rank2) & pawnsBitBoard);
        score += 0.5 * pawnsInFiles;

        return score * 0.2;
    }


}

