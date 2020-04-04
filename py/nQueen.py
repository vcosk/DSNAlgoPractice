# Solution for nQueen problem using recursion and backtracking.
# https://www.hackerearth.com/practice/basic-programming/recursion/recursion-and-backtracking/tutorial/
def attacked(row: int, col: int, board: list):
    
    size = len(board)
    
    # Checking the column
    for i in range(0, size):
        if board[i][col] == 1:
            return True

    for i, j in zip(range(row-1, -1, -1), range(col-1, -1, -1)):
        if board[i][j] == 1:
            return True
    
    for i, j in zip(range(row-1, -1, -1), range(col+1, size)):
        if board[i][j] == 1:
            return True
    
    return False

def nQueen(board: list, queenCount: int):
    if queenCount == 0:
        return True
    
    size = len(board)

    # for row in range(size-queenCount, size):
    row = size-queenCount
    for col in range(0, size):
        if attacked(row, col, board):
            continue
        board[row][col] = 1
        if nQueen(board, queenCount - 1):
            return True

        board[row][col] = 0

    return False


if __name__ == '__main__':
    size = int(input())
    board = [[0]*size for i in range(0,size)]
    # board[1][2] = 1
    # print(attacked(3,3,board))
    nQueen(board, size)
    for row in board:
        output = ''
        for cell in row:
            output += str(cell) + ' '
        print(output.strip())