"""
two_fer.py
"""

def two_fer(name: str="you") -> str:
    """
    Returns a string that includes the name passed in, or "you" if no name is given.
    The string says "One for [name], one for me."
    """
    return f"One for {name}, one for me."