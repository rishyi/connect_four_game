package lk.ijse.dep.service;

public class
HumanPlayer extends Player {
    public HumanPlayer(Board newBoard) {
        super(newBoard);
    }

    /**
     * Moves a piece to the specified column.
     *
     * @param col The column number to move the piece to.
     */
    @Override
    public void movePiece(int col) {
        // Check if the move is legal
        boolean flag = board.isLegalMove(col);
        if (flag) {
            board.updateMove(col, Piece.BLUE);
            board.getBoardUI().update(col,true);

            // Find the winner after the move
            Winner winner = board.findWinner();
            // If the winning piece is empty, check if there are any legal moves left
            if (winner.getWinningPiece() == Piece.EMPTY) {
                boolean f1 = board.existLegalMoves();
                // If there are no legal moves left, notify the winner as empty
                if (!f1) {
                    board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
                }
            } else {
                // Notify the winner
                board.getBoardUI().notifyWinner(winner);
            }
        }
    }
}
