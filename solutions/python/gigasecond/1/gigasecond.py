from datetime import timedelta

GIGA = 1_000_000_000

def add(moment, delta=GIGA):
    return moment + timedelta(seconds=delta)
