def two_fer(name: str=None) -> str:
    if name is None:
        name = "you"
    return f"One for {name}, one for me."
