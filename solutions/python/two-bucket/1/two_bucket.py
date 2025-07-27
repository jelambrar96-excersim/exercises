def measure(bucket_one, bucket_two, goal, start_bucket):
    visited = set()
    queue = []
    if start_bucket == "one":
        queue.append((bucket_one, 0, 1))
        invalid = (0, bucket_two)
    else:
        queue.append((0, bucket_two, 1))
        invalid = (bucket_one, 0)
    
    while queue:
        b1, b2, step = queue.pop(0)
        if b1 == goal: return step, 'one', b2
        if b2 == goal: return step, 'two', b1
            
        if (b1, b2) in visited or (b1, b2) == invalid:
            continue
        visited.add((b1, b2))

        # Pouring one bucket into the other bucket until either: a) the first bucket is empty b) the second bucket is full
        queue.append((min(b1 + b2, bucket_one), b2 - (min(b1 + b2, bucket_one) - b1), step + 1))
        queue.append((b1 - (min(b1 + b2, bucket_two) - b2), min(b1 + b2, bucket_two), step + 1))
        
        # Emptying a bucket and doing nothing to the other.
        queue.append((b1, 0, step + 1))
        queue.append((0, b2, step + 1))
    
        # Filling a bucket and doing nothing to the other.
        queue.append((bucket_one, b2, step + 1))
        queue.append((b1, bucket_two, step + 1))
    
    raise ValueError("No Solution Possible")