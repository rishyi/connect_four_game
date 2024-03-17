package lk.ijse.dep.service;

public class BoardImpl implements Board{

    private Piece[][] pieces=new Piece[NUM_OF_COLS][NUM_OF_ROWS];
    private  BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        // Initialize the pieces array with Piece.EMPTY values
        for (int cols = 0; cols < pieces.length; cols++) {
            for (int row = 0; row < pieces[cols].length; row++) {
                pieces[cols][row] = Piece.EMPTY;
            }
        }
        this.boardUI = boardUI;
    }

    @Override
    public BoardUI getBoardUI() {

        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        // Iterate over each row in the column
        for (int i = 0; i < pieces[col].length; i++) {
            // Check if the spot is empty
            if (pieces[col][i] == Piece.EMPTY) {
                return i;
            }
        }
        // No available spot found
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        // Checks if there is an available spot in the given column
        return findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existLegalMoves() {
        // Iterate through each piece
        for (int i = 0; i < pieces.length; i++) {
            // Check if the current piece has a legal move
            if (isLegalMove(i)) {
                // Return true if a legal move is found
                return true;
            }
        }
        // Return false if no legal moves are found
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        // Find the next available spot in the column
        int index = findNextAvailableSpot(col);
        pieces[col][index] = move;
        // Update the move in the pieces array

    }

    @Override
    public void updateMove(int col, int raw ,Piece move) {
        pieces[col][raw] = move;
    }

    @Override
    public Winner findWinner() {
        // Check for horizontal wins
        for (int row = 0; row < NUM_OF_ROWS; row++) {
            for (int cols = 0; cols < 3; cols++) {
                Piece piece = pieces[cols][row];
                if (piece != Piece.EMPTY && piece==pieces[cols+1][row] && piece==pieces[cols+2][row] && piece==pieces[cols+3][row]){
                    return new Winner(piece,cols,cols+3,row,row);
                }
            }
        }
        // Check for vertical wins
        for (int cols = 0; cols < NUM_OF_COLS; cols++) {
            for (int row = 0; row < 2; row++) {
                Piece piece = pieces[cols][row];
                if (piece != Piece.EMPTY && piece == pieces[cols][row+1] && piece == pieces[cols][row+2] && piece == pieces[cols][row+3]){
                    return new Winner(piece,cols,cols,row,row+3);
                }
            }
        }
        // No winner found
        return new Winner(Piece.EMPTY,-1,-1,-1,-1);
    }
}