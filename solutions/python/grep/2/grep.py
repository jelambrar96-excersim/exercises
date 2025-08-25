import os
import re

def grep(pattern, flags, files):

    L_FLAG = "-l" in flags # show filenames only
    I_FLAG = "-i" in flags # ignore case
    N_FLAG = "-n" in flags # show line numbers
    X_FLAG = "-x" in flags # match whole lines only
    V_FLAG = "-v" in flags # invert match

    MULTFILES = len(files) > 1

    repattern = re.compile(
        f"^{pattern}$" if X_FLAG else pattern,
        flags=re.IGNORECASE if I_FLAG else 0,
    )

    out = []
    for k in files:
        with open(k, 'r', encoding='utf-8') as f:
            for i, line in enumerate(f.readlines(), start=1):
                
                line = line.rstrip("\n")
                match_line = repattern.search(line)
                match_flag = bool(match_line)
                match_flag ^= V_FLAG

                if not match_flag:
                    continue

                if L_FLAG:
                    out.append(k)
                    break

                outline = (f"{k}:" if MULTFILES else "") \
                        + (f"{i}:" if N_FLAG else "") + line
                out.append(outline)

    return "\n".join(out) + ("\n" if out else "")
