def proverb(*input_iterable, qualifier=None):
    len_iterable = len(input_iterable)
    if len_iterable == 0:
        return []
    out = []
    for i in range(1, len_iterable):
        text = f"For want of a {input_iterable[i-1]} the {input_iterable[i]} was lost."
        out.append(text)
    qualifier_text = ("" if qualifier is None else f"{qualifier} ") + input_iterable[0]
    out.append(f"And all for the want of a {qualifier_text}.")
    return out