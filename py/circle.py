import math

def circleFrom3Points(x1: int, y1: int, x2: int, y2: int, x3: int, y3: int):
    '''
    Method calculates center and radius of a circle give three points on it's circumference.
    '''
    # Equation of a circle with center points (h,k) (x-h)**2+(y-k)**2=r**2
    # Using equation x**2+y**2+2gx+2fy+c=0
    # h = -g, k = -f, r = math.sqrt(h**2 + k**2 - c)

    A = x1**2 + y1**2
    B = x2**2 + y2**2
    C = x3**2 + y3**2

    P = x1 - x2
    Q = y1 - y2
    R = x1 - x3
    S = y1 - y3

    g = ((A - C) * Q - (A - B) * S) / (2 * (P * S - R * Q))

    f = ((A - C) * P - (A - B) * R) / (2 * (Q * R - S * P))

    h = -g
    k = -f
    c = - (x1**2+y1**2+2*g*x1+2*f*y1)
    r  = math.sqrt(h**2 + k**2 - c)
    return {
        'center' : (h,k),
        'radius' : r
    }

print(circleFrom3Points(1,-6,2,1,5,2))
print(circleFrom3Points(y1=1,x1=1,y3=4,x3=2,y2=3,x2=5))