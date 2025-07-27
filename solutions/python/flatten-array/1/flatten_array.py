from collections.abc import Iterable

def flatten(iterable, skip_none=True):
    out = []
    for item in iterable:
        if isinstance(item, Iterable):
            if len(item) > 0:
                out.extend(flatten(item))
        else:
            out.append(item)
    if skip_none:
        return [ item for item in out if item is not None ]
    return out
        
        
