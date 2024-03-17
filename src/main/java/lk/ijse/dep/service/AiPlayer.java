package lk.ijse.dep.service;

import java.util.*;

public class AiPlayer extends Player {
    public AiPlayer(Board newBoard) {
        super(newBoard);
    }

    /**
     * Move a piece to the specified column.
     *
     * @param col The column number to move the piece to.
     */
    public void movePiece(int col) {
        // Keep finding a valid column until one is found
        while (true) {
            col = minMax();
            if (board.isLegalMove(col)) {
                break;
            }
        }
        // Update the board and the board UI with the move
        board.updateMove(col, Piece.GREEN);
        board.getBoardUI().update(col, false);

        // Check if there is a winner
        Winner winner = board.findWinner();
        // If the winning piece is empty, check if there are any legal moves left
        if (winner.getWinningPiece() == Piece.EMPTY) {
            boolean fl = board.existLegalMoves();
            if (!fl) {
                // If there is no winner, check if there are any legal moves left
                board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        } else {
            // If there is a winner, notify the UI with the winner
            board.getBoardUI().notifyWinner(winner);
        }
    }


    public int minMax() {
        /**
         * Returns the maximum value from a list of Holder objects.
         *@param holders The list of Holder objects.
         * @return The maximum value from the list.
         */
        List<Holder> set = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            set.add(new Holder(i, 0));
        }

        updateHolderMarks(set,set.size(),Piece.GREEN,100);
        updateHolderMarks(set,6,Piece.BLUE,99);

        return max(set);
    }

    /**
     * Update the marks of the holders in the set based on the moves made on the board.
     *
     * @param set The list of holders.
     * @param length The length of the set.
     * @param piece The current piece being played.
     * @param marks The marks to be added to the holders.
     */
    private void updateHolderMarks(List<Holder> set,int length,Piece piece,int marks){
        for (int i = 0; i < length; i++) {
            // Find the next available spot in the board for the current column.
            int avs = board.findNextAvailableSpot(i);
            if (avs == -1)continue;
            // Update the board with the current move.
            board.updateMove(i, piece);
            Winner winner = board.findWinner();
            if (winner.getWinningPiece() == piece) {
                // Update the mark of the holders in the set for the column of the current move.
                for (Holder holder : set) {
                    if (holder.getCol() == i) {
                        int mark = holder.getMark();
                        mark += marks;
                        holder.setMark(mark);
                    }
                }
            }
            // Undo the current move.
            board.updateMove(i, avs, Piece.EMPTY);
        }
    }
    /**
     * Returns the column with the maximum mark from the list of holders.
     * If the maximum mark is 0, returns a random column between 0 and 5.
     *
     * @param list the list of holders
     * @return the column with the maximum mark or a random column if the maximum mark is 0
     */
    private int max(List<Holder> list){
        int max = Integer.MIN_VALUE;
        int min = -1;
        for (Holder holder : list) {
            if (max<holder.getMark()){
                max=holder.getMark();
                min =holder.getCol();
            }
        }
        if (max==0){
            return new Random().nextInt(6);
        }
        return min;
    }
}
