def __notAvailable(board, row, col):
    return True
def nQueen(board, piecesLeft):
    size = len(board)
    for i in range(size-piecesLeft, size):
        for j in range(size-piecesLeft, size):
            if __notAvailable(board, i, j):
                return False
            