

def transform(legacy_data):
    out = {}
    for key, value in legacy_data.items():
        new_dict = { item.lower() : key for item in value }
        out = {**out, **new_dict}
    return out