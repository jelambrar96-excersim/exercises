FILE_TEXT = {
    "iliad.txt": """Achilles sing, O Goddess! Peleus' son;
His wrath pernicious, who ten thousand woes
Caused to Achaia's host, sent many a soul
Illustrious into Ades premature,
And Heroes gave (so stood the will of Jove)
To dogs and to all ravening fowls a prey,
When fierce dispute had separated once
The noble Chief Achilles from the son
Of Atreus, Agamemnon, King of men.\n""",

    "midsummer-night.txt": """I do entreat your grace to pardon me.
I know not by what power I am made bold,
Nor how it may concern my modesty,
In such a presence here to plead my thoughts;
But I beseech your grace that I may know
The worst that may befall me in this case,
If I refuse to wed Demetrius.\n""",

    "paradise-lost.txt": """Of Mans First Disobedience, and the Fruit
Of that Forbidden Tree, whose mortal tast
Brought Death into the World, and all our woe,
With loss of Eden, till one greater Man
Restore us, and regain the blissful Seat,
Sing Heav'nly Muse, that on the secret top
Of Oreb, or of Sinai, didst inspire
That Shepherd, who first taught the chosen Seed\n""",
}


def grep(pattern, flags, files):

    L_FLAG = "-l" in flags
    I_FLAG = "-i" in flags
    N_FLAG = "-n" in flags
    X_FLAG = "-x" in flags
    V_FLAG = "-v" in flags

    MULTFILES = len(files) > 1

    if I_FLAG:
        pattern = pattern.lower()

    out = []
    for k in FILE_TEXT.keys():
        if not k in files:
            continue

        full_text = FILE_TEXT[k]

        if L_FLAG:
            if I_FLAG:
                full_text = full_text.lower()
            if pattern in full_text:
                out.append(k + '\n')
            continue

        text_lines = full_text.splitlines(True)
        for i, line in enumerate(text_lines, start=1):
            
            search_line = line.lower().strip() if I_FLAG else line.strip()
            multifile_text = f'{k}:' if MULTFILES else ''

            if X_FLAG:
                if (search_line == pattern) ^ V_FLAG:
                    number_text = f'{i}:' if N_FLAG else ''
                    out_line = f'{multifile_text}{number_text}{line}'
                    out.append(out_line)

            else:
                if (pattern in search_line) ^ V_FLAG: 
                    number_text = f'{i}:' if N_FLAG else ''
                    out_line = f'{multifile_text}{number_text}{line}'
                    out.append(out_line)

    return "".join(out)
