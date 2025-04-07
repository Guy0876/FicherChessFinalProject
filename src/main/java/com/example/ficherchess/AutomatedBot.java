package com.example.ficherchess;

public class AutomatedBot {
    private Model model;
    public AutomatedBot(Model model){
        this.model = model;
    }

    public void makeMove() {
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
        //Attacking the weak pawns
        //Entering the opponent’s camp
        // Now, what if we don’t have a passed pawn,
        // we don’t have a pawn majority and we can’t win any pawn?
        //We can’t win them immediately? We’ll do it slowly.
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
    }
}
