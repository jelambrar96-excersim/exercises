DICT_TRADUCTOR = {
    "G": "C",
    "C": "G",
    "T": "A",
    "A": "U"
}

def to_rna(dna_strand):
    return "".join(DICT_TRADUCTOR[item] for item in dna_strand)
