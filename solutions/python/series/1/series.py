def slices(series, length):
    if length == 0:
        raise ValueError("slice length cannot be zero")

    if length < 0:
        raise ValueError("slice length cannot be negative")

    len_series = len(series)
    if len_series == 0:
        raise ValueError("series cannot be empty")

    if len(series) < length:
        raise ValueError("slice length cannot be greater than series length")

    return [ series[i:i+length] for i in range(0, len_series - length + 1) ]
