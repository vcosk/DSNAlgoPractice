# https://www.hackerearth.com/practice/basic-programming/recursion/recursion-and-backtracking/practice-problems/algorithm/a-tryst-with-chess/description/
#You are given a 10X10 chessboard with a knight on coordinate (I,J). You have to find the number of blocks on the chessboard that the knight can be at in exactly N moves.


boardSize = 10
possibleLocation = [(i, j)[::-k] for i in (1,-1) for j in (2,-2) for k in (1,-1)]
def kinghtLocation(loc: tuple, movesLeft: int):
    newLocations = []
    returnSet = set()
    x = loc[0]
    y = loc[1]
    if movesLeft > 0 and (x > -1 and x < boardSize) and (y > -1 and y < boardSize):
        s = {(x+dx,y+dy) for dx,dy in possibleLocation}
        returnSet = set(filter(lambda t: t[0]>-1 and t[0]<10 and t[1]>-1 and t[1]<10, s))
        nextMoves = set()
        for newLocation in returnSet:
            nextMoves |= kinghtLocation(newLocation, movesLeft - 1)

        if len(nextMoves) > 0:
            returnSet = nextMoves

    return returnSet



if __name__ == '__main__':
    possibleLocation = [(i,j)[::k] for i in (1, -1) for j in (2, -2) for k in (1,-1)]
    i,j,k = map(int, '3 8 2'.split())
    startLoc = (i - 1, j - 1)
    iterations = k
    result = kinghtLocation(startLoc, iterations)
    print(len(result), result)

    locs = {startLoc}
    for _ in range(iterations):
        t = set()
        for i,j in locs:
            for dx,dy in possibleLocation:
                x,y = i+dx,j+dy
                if 10 > x >= 0 <= y < 10:
                    t.add((x,y))
        locs = t

    print(len(locs), locs)

    print(result.difference(locs))