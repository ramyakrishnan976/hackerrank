from datetime import datetime

def time_delta(t1, t2):
    # Format: Day dd Mon yyyy hh:mm:ss +xxxx
    fmt = '%a %d %b %Y %H:%M:%S %z'
    
    # 1. Parse strings into timezone-aware datetime objects
    dt1 = datetime.strptime(t1, fmt)
    dt2 = datetime.strptime(t2, fmt)
    
    # 2. Subtracting two datetime objects results in a timedelta object
    # 3. .total_seconds() gives the difference in seconds
    diff = abs((dt1 - dt2).total_seconds())
    
    # Return as a string representing an integer
    return str(int(diff))

if __name__ == '__main__':
    t = int(input())
    for _ in range(t):
        t1 = input()
        t2 = input()
        print(time_delta(t1, t2))
