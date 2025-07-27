VLOOK_PROTEIN = {
    "AUG": "Methionine",
    "UGG": "Tryptophan",
    "UUU": "Phenylalanine",
    "UUC": "Phenylalanine",
    "UUA": "Leucine",
    "UUG": "Leucine",
    "UAU": "Tyrosine",
    "UAC": "Tyrosine",
    "UGU": "Cysteine",
    "UGC": "Cysteine",
    "UAA": "STOP",
    "UAG": "STOP",
    "UGA": "STOP",
    "UCU": "Serine",
    "UCC": "Serine",
    "UCA": "Serine",
    "UCG": "Serine",
}


def proteins(strand):
    if len(strand) % 3 != 0:
        raise ValueError()
    list_strand = [ strand[i:i+3] for i in range(0, len(strand), 3) ]
    out = []
    for item in list_strand:
        value = VLOOK_PROTEIN[item]
        if value == "STOP":
            break
        out.append(value)
    return out
        
    
