package com.example.ficherchess.AutoMatedBot;

import com.example.ficherchess.Model;
import com.example.ficherchess.Pieces.*;

import java.util.ArrayList;

public class AutomatedBot {
    private Model model;
    private Model copy;
    boolean opening = true;
    boolean middleGame = false;
    boolean endGame = false;
    int totalMoves = 0;
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

    public void makeMove() {
        if (Piece.check) {
            handleCheckSituation();
        } else if (!attemptCheckmate()) {
            if (!preventOpponentCheckmate()) {
                ArrayList<ArrayList<Piece>> pieces = model.isWhiteTurn() ? model.getWhitePieces() : model.getBlackPieces();
                ArrayList<ArrayList<Piece>> opponentPieces = model.isWhiteTurn() ? model.getBlackPieces() : model.getWhitePieces();
                PieceInfo pInfo = getMostThreatenedPiece(pieces);
                PieceInfo oppPInfo = getMostThreatenedPiece(opponentPieces);
                double score = evaluatePosition(pieces, model) -
                        evaluatePosition(opponentPieces, model);
                if (pInfo.getAction().equals("move")) {
                    makeBestMoveForPiece(pInfo.getPiece());
                } else if (pInfo.getAction().equals("defend")) {
                    if (pInfo.getEvaluation() >= 0) {
                        if (oppPInfo.getAction().equals("move") ||
                                (oppPInfo.getEvaluation() < 0 && oppPInfo.getAction().equals("defend"))) {
                            takeBestWayPossible(oppPInfo.getPiece(), pieces);
                        }
                    } else {
                        defendBestWayPossible(pInfo.getPiece(), pieces);
                    }
                } else {
                    if (oppPInfo.getAction().equals("move") ||
                            (oppPInfo.getEvaluation() < 0 && oppPInfo.getAction().equals("defend"))) {
                        takeBestWayPossible(oppPInfo.getPiece(), pieces);
                    }
                }
                if(opening){
                    makeAnOpeningMove(pieces, score);
                } else if (middleGame){
                    makeAMiddleGameMove(pieces, score, pInfo, oppPInfo);
                } else if (endGame){
                    makeAnEndGameMove(pieces, score);
                }
                makeSafeBestMove();
            }
        }
        totalMoves++;
        if(totalMoves == 12) {
            opening = false;
            middleGame = true;
        }
    }

    private void makeSafeBestMove() {
        ArrayList<ArrayList<Piece>> pieces = model.isWhiteTurn() ? model.getWhitePieces() : model.getBlackPieces();
        double maxPos = 0;
        int [] maxRowCol = {-1, -1};
        int [] pieceRowCol = {-1, -1};
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                long moves = getAvailableMoves(piece.getBitboard(), model);
                pieceRowCol = movePositionToRowCol(piece.getBitboard());
                if(moves != 0) {
                    for(int i = 0; i < 64; i++) {
                        long move = 1L << i;
                        if ((moves & move) != 0) {
                            double pos = simulateMoveAndEvaluate(piece, move);
                            if (pos > maxPos) {
                                maxPos = pos;
                                maxRowCol = movePositionToRowCol(move);
                                model.setSelectedPiece(pieceRowCol[0], pieceRowCol[1]);
                            }
                        }
                    }
                }
            }
        }
        model.updateTurn(pieceRowCol[0], pieceRowCol[1], maxRowCol[0], maxRowCol[1]);
    }

    private void makeAnEndGameMove(ArrayList<ArrayList<Piece>> pieces, double score) {

    }

    private void makeAMiddleGameMove(ArrayList<ArrayList<Piece>> pieces, double score, PieceInfo piece, PieceInfo oppPiece) {
        double maxPos = Double.MIN_VALUE;
        int [] max = {-1, -1}, poStart = {-1, -1};
        King king = (King)pieces.get(5).get(0);
        long kingPos = king.getBitboard();
        poStart = movePositionToRowCol(kingPos);
        double evaluation = pieces.get(0).get(0).isWhite() ? Finals.WhitePiecePosition[0][poStart[0]][poStart[1]] : Finals.BlackPiecePosition[0][poStart[0]][poStart[1]];
        if(evaluation > 2) {
            ArrayList<Piece> rooks = pieces.get(3);
            for(Piece r : rooks) {
                double s = simulateMoveAndEvaluate(king, r.getBitboard());
                if(s > maxPos) {
                    maxPos = s;
                    max = movePositionToRowCol(r.getBitboard());
                }
            }
        }
        if(score > 0){
            attackBestWayPossible(oppPiece.getPiece(), pieces);
        } else {
            defendBestWayPossible(piece.getPiece(), pieces);
        }
    }

    private void attackBestWayPossible(Piece piece, ArrayList<ArrayList<Piece>> pieces) {
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece p : pieceList) {
                long moves = getAvailableMoves(p.getBitboard(), model);
                for(int i = 0; i < 64; i++) {
                    long move = 1L << i;
                    if ((moves & move) != 0) {
                        simulateMoveAndEvaluate(p, move);
                        long attacks = getAvailableMoves(move, copy);
                        if((attacks & piece.getBitboard()) != 0) {
                            int[] selectedRowCol = movePositionToRowCol(piece.getBitboard());
                            model.setSelectedPiece(selectedRowCol[0], selectedRowCol[1]);
                            int[] moveRowCol = movePositionToRowCol(move);
                            model.updateTurn(selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]);
                        }
                    }
                }
            }
        }
        makeSafeBestMove();
    }

    private void makeAnOpeningMove(ArrayList<ArrayList<Piece>> pieces, double score) {
        int [] max = {-1, -1}, poStart = {-1, -1};
        double maxPos = Double.MIN_VALUE;
        for(ArrayList<Piece> pieceList : pieces) {
            for(Piece p : pieceList) {
                 if(p instanceof Knight){
                        long moves = getAvailableMoves(p.getBitboard(), model);
                        for(int i = 0; i < 64; i++) {
                            long move = 1L << i;
                            if ((moves & move) != 0) {
                                double pos = simulateMoveAndEvaluate(p, move);
                                if( pos > maxPos) {
                                    maxPos = pos;
                                    max = movePositionToRowCol(move);
                                    poStart = movePositionToRowCol(p.getBitboard());
                                }
                            }
                        }
                 } else if ((p instanceof WhitePawn || p instanceof BlackPawn) && (p.getBitboard() & (0x3C00L | 0x003C0000000000000L)) != 0) {
                        long moves = getAvailableMoves(p.getBitboard(), model);
                        for(int i = 0; i < 64; i++) {
                            long move = 1L << i;
                            if ((moves & move) != 0) {
                                double pos = simulateMoveAndEvaluate(p, move) + 0.5;
                                if( pos > maxPos) {
                                    maxPos = pos;
                                    max = movePositionToRowCol(move);
                                    poStart = movePositionToRowCol(p.getBitboard());
                                }
                            }
                        }
                 } else if (p instanceof Bishop) {
                     long moves = getAvailableMoves(p.getBitboard(), model);
                        for(int i = 0; i < 64; i++) {
                            long move = 1L << i;
                            if ((moves & move) != 0) {
                                double pos = simulateMoveAndEvaluate(p, move);
                                if( pos > maxPos) {
                                    maxPos = pos;
                                    max = movePositionToRowCol(move);
                                    poStart = movePositionToRowCol(p.getBitboard());
                                }
                            }
                        }
                 }
            }
        }
        model.setSelectedPiece(poStart[0], poStart[1]);
        model.updateTurn(poStart[0], poStart[1], max[0], max[1]);
    }

    private void makeBestMoveForPiece(Piece p) {
        ArrayList<ArrayList<Piece>> pieces = model.isWhiteTurn() ? model.getWhitePieces() : model.getBlackPieces();
        double maxPos = Double.MIN_VALUE;
        int [] maxRowCol = {-1, -1};
        int [] pieceRowCol = {-1, -1};
        long moves = getAvailableMoves(p.getBitboard(), model);
        pieceRowCol = movePositionToRowCol(p.getBitboard());
        model.setSelectedPiece(pieceRowCol[0], pieceRowCol[1]);
        for(int i = 0; i < 64; i++) {
            long move = 1L << i;
            if ((moves & move) != 0) {
                double pos = simulateMoveAndEvaluate(p, move);
                if (pos > maxPos) {
                    maxPos = pos;
                    maxRowCol = movePositionToRowCol(move);
                }
            }
        }
        model.updateTurn(pieceRowCol[0], pieceRowCol[1], maxRowCol[0], maxRowCol[1]);
    }

    private void defendBestWayPossible(Piece piece, ArrayList<ArrayList<Piece>> pieces) {
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece p : pieceList) {
                long moves = getAvailableMoves(p.getBitboard(), model);
                for(int i = 0; i < 64; i++) {
                    long move = 1L << i;
                    if ((moves & move) != 0) {
                        simulateMoveAndEvaluate(p, move);
                        long opponentPiecesBitBoard = model.isWhiteTurn() ? Piece.blackPieces : Piece.whitePieces;
                        if(model.isWhiteTurn()) {
                            Piece.blackPieces = Piece.whitePieces;
                        }
                        else {
                            Piece.whitePieces = Piece.blackPieces;
                        }
                        long defences = getAvailableMoves(move, copy);
                        if((defences & piece.getBitboard()) != 0) {
                            if(model.isWhiteTurn()) {
                                Piece.blackPieces = opponentPiecesBitBoard;
                            }
                            else {
                                Piece.whitePieces = opponentPiecesBitBoard;
                            }
                            int[] selectedRowCol = movePositionToRowCol(piece.getBitboard());
                            model.setSelectedPiece(selectedRowCol[0], selectedRowCol[1]);
                            int[] moveRowCol = movePositionToRowCol(move);
                            model.updateTurn(selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]);
                        } else {
                            if (model.isWhiteTurn()) {
                                Piece.blackPieces = opponentPiecesBitBoard;
                            } else {
                                Piece.whitePieces = opponentPiecesBitBoard;
                            }
                        }
                    }
                }
            }
        }
        makeSafeBestMove();
    }



    private boolean takeBestWayPossible(Piece piece, ArrayList<ArrayList<Piece>> pieces) {
        for(ArrayList<Piece> pieceList : pieces) {
            for(Piece p : pieceList) {
                long moves = getAvailableMoves(p.getBitboard(), model);
                if((moves & piece.getBitboard()) != 0) {
                    for(int i = 0; i < 64; i++) {
                        long move = 1L << i;
                        if((move & piece.getBitboard()) != 0) {
                            int[] selectedRowCol = movePositionToRowCol(piece.getBitboard());
                            model.setSelectedPiece(selectedRowCol[0], selectedRowCol[1]);
                            int[] moveRowCol = movePositionToRowCol(move);
                            model.updateTurn(selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private PieceInfo getMostThreatenedPiece(ArrayList<ArrayList<Piece>> pieces) {
        PieceInfo mostThreatenedPiece = new PieceInfo(null, "", Double.MIN_VALUE);
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                PieceInfo threatInfo = evaluateThreat(piece, pieces);
                if(threatInfo.getAction().equals(mostThreatenedPiece.getAction())){
                    if(threatInfo.getEvaluation() > mostThreatenedPiece.getEvaluation()) {
                        mostThreatenedPiece = threatInfo;
                    }
                } else if (threatInfo.getAction().equals("move")) {
                    mostThreatenedPiece = threatInfo;
                } else if (threatInfo.getAction().equals("defend")) {
                    mostThreatenedPiece = threatInfo;
                }
            }
        }
        return mostThreatenedPiece;
    }

    private PieceInfo evaluateThreat(Piece p, ArrayList<ArrayList<Piece>> pieces) {
        PieceInfo pInfo = new PieceInfo(p, "", 0);
        ArrayList<ArrayList<Piece>> opponentPieces = pieces.get(0).get(0).isWhite() ? model.getBlackPieces() : model.getWhitePieces();
        ArrayList<Piece> attackers = new ArrayList<Piece>();
        for (ArrayList<Piece> pieceList : opponentPieces) {
            for (Piece piece : pieceList) {
                long attacks = getAvailableMoves(piece.getBitboard(), model);
                if ((attacks & p.getBitboard()) != 0) {
                    attackers.add(piece);
                }
            }
        }
        ArrayList<Piece> defenders = new ArrayList<Piece>();
        long opponentPiecesBitBoard = model.isWhiteTurn() ? Piece.blackPieces : Piece.whitePieces;
        if(model.isWhiteTurn()) {
            Piece.blackPieces = Piece.whitePieces;
        }
        else {
            Piece.whitePieces = Piece.blackPieces;
        }
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                long defences = getAvailableMoves(piece.getBitboard(), model);
                if ((defences & p.getBitboard()) != 0) {
                    defenders.add(piece);
                }
            }
        }
        if(model.isWhiteTurn()) {
            Piece.blackPieces = opponentPiecesBitBoard;
        }
        else {
            Piece.whitePieces = opponentPiecesBitBoard;
        }
        for(Piece piece : attackers) {
            pInfo.setEvaluation(pInfo.getEvaluation() + piece.getWeight());
        }
        for(Piece piece : defenders) {
            pInfo.setEvaluation(pInfo.getEvaluation() - piece.getWeight());
        }
        if(attackers.size() == defenders.size()) {
            if(pInfo.getEvaluation() > 0) {
                pInfo.setAction("nothing");
            }
            else if(pInfo.getEvaluation() < 0) {
                pInfo.setAction("defend");
            }
            else {
                int [] rowCol = movePositionToRowCol(p.getBitboard());
                double [][][] piecePos = model.isWhiteTurn() ? Finals.WhitePiecePosition : Finals.BlackPiecePosition;
                if(piecePos[piecePosIndex(p)][rowCol[0]][rowCol[1]] > 0) {
                    pInfo.setAction("defend");
                }
                else {
                    pInfo.setAction("nothing");
                }
            }
        }
        else if(attackers.size() < defenders.size()) {
            pInfo.setAction("nothing");
        }
        else if(attackers.size() > defenders.size()) {
            if(pInfo.getEvaluation() < 0 && pInfo.getEvaluation() > -3) {
                pInfo.setAction("defend");
            } else if(pInfo.getEvaluation() < -3) {
                pInfo.setAction("move");
            } else {
                pInfo.setAction("nothing");
            }
        }
        return pInfo;
    }

    private void handleCheckSituation() {
        // Iterate through all pieces to find moves that get out of check
        ArrayList<ArrayList<Piece>> pieces = model.isWhiteTurn() ? model.getWhitePieces() : model.getBlackPieces();
        double maxPos = 0;
        int [] maxRowCol = {-1, -1};
        int [] pieceRowCol = {-1, -1};
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                long moves = getAvailableMoves(piece.getBitboard(), model);
                pieceRowCol = movePositionToRowCol(piece.getBitboard());
                if(moves != 0) {
                    for(int i = 0; i < 64; i++) {
                        long move = 1L << i;
                        if ((moves & move) != 0) {
                            double pos = simulateMoveAndEvaluate(piece, move);
                            if (pos > maxPos) {
                                maxPos = pos;
                                maxRowCol = movePositionToRowCol(move);
                                model.setSelectedPiece(pieceRowCol[0], pieceRowCol[1]);
                            }
                        }
                    }
                }
            }
        }
        model.updateTurn(pieceRowCol[0], pieceRowCol[1], maxRowCol[0], maxRowCol[1]);
    }

    private boolean attemptCheckmate() {
        // Iterate through all pieces to find a move that results in checkmate
        ArrayList<ArrayList<Piece>> pieces = model.isWhiteTurn() ? model.getWhitePieces() : model.getBlackPieces();
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                long moves = getAvailableMoves(piece.getBitboard(), model);
                for(int i = 0; i < 64; i++) {
                    long move = 1L << i;
                    if ((moves & move) != 0) {
                        if (simulateMoveAndEvaluate(piece, move) == Double.MAX_VALUE) {
                            int[] selectedRowCol = movePositionToRowCol(piece.getBitboard());
                            model.setSelectedPiece(selectedRowCol[0], selectedRowCol[1]);
                            int[] moveRowCol = movePositionToRowCol(move);
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
        // Iterate through all pieces to block or prevent opponent's checkmate
        ArrayList<ArrayList<Piece>> oppPieces = model.isWhiteTurn() ? model.getBlackPieces() : model.getWhitePieces();
        for (ArrayList<Piece> pieceList : oppPieces) {
            for (Piece piece : pieceList) {
                long moves = getAvailableMoves(piece.getBitboard(), model);
                for (int i = 0; i < 64; i++) {
                    long move = 1L << i;
                    if ((moves & move) != 0) {
                        if(simulateMoveAndEvaluate(piece, move) == Double.MIN_VALUE){
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
        // Iterate through all pieces to block or prevent opponent's checkmate
        ArrayList<ArrayList<Piece>> pieces = model.isWhiteTurn() ? model.getWhitePieces() : model.getBlackPieces();
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                long moves = getAvailableMoves(piece.getBitboard(), model);
                for (int i = 0; i < 64; i++) {
                    long movePos = 1L << i;
                    if ((moves & movePos) != 0) {
                        simulateMoveAndEvaluate(piece, movePos);
                        long possibleMoves = getAvailableMoves(movePos, copy);
                        if((possibleMoves & move) != 0){
                            int [] selectedRowCol = movePositionToRowCol(piece.getBitboard());
                            model.setSelectedPiece(selectedRowCol[0], selectedRowCol[1]);
                            int [] moveRowCol = movePositionToRowCol(movePos);
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
        long allPieces = Piece.allPieces;
        long whitePieces = Piece.whitePieces;
        long blackPieces = Piece.blackPieces;
        boolean check = Piece.check;
        this.copy = model.getCopy();
        int[]rowCol = movePositionToRowCol(pos);
        copy.setSelectedPiece(rowCol[0], rowCol[1]);
        return copy.getPossibleMoves();
    }

    private double simulateMoveAndEvaluate(Piece piece, long movePosition) {
        long allPieces = Piece.allPieces;
        long whitePieces = Piece.whitePieces;
        long blackPieces = Piece.blackPieces;
        boolean check = Piece.check;
        this.copy = model.getCopy();
        int[] selectedRowCol = movePositionToRowCol(piece.getBitboard());
        copy.setSelectedPiece(selectedRowCol[0], selectedRowCol[1]);
        int[] moveRowCol = movePositionToRowCol(movePosition);
        copy.updateTurn(selectedRowCol[0], selectedRowCol[1], moveRowCol[0], moveRowCol[1]);
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
        PieceInfo threatInfo = evaluateThreat(p, pieces);
        if(threatInfo.getAction().equals("defend")) {
            score += 0.2 * p.getWeight();
        }
        else if(threatInfo.getAction().equals("move")) {
            score += threatInfo.getEvaluation();
        }
        else if(threatInfo.getAction().equals("nothing")) {
            score += 0.5 * p.getWeight();
        }
        return score;
    }

    public int[] movePositionToRowCol(long movePosition) {
        int index = Long.numberOfTrailingZeros(movePosition); // Get the index of the set bit (0-63)
        int row = index / 8; // Calculate the row (0-7)
        int col = index % 8; // Calculate the column (0-7)
        return new int[]{row, col}; // Return as an array [row, col]
    }

    private double evaluatePosition(ArrayList<ArrayList<Piece>> pieces, Model model) {
        double score = 0;
        if(model.isCheckmate(pieces.get(0).get(0).isWhite())){return Double.MIN_VALUE;}
        else if (!model.isCheckmate(pieces.get(0).get(0).isWhite())){return Double.MAX_VALUE;}
        for (ArrayList<Piece> pieceList : pieces) {
            for (Piece p : pieceList) {
                score += evaluatePiece(p, piecePosIndex(p), pieces);
            }
        }
        King king = (King) pieces.get(5).get(0);
        return score + getKingSaftey(king.getBitboard(), pieces);
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

        return score;
    }
}

