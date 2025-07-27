from collections import deque


SCALE_BASE_SHARP = ["C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"]
SCALE_BASE_BEMOL = ["C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"]

SHARP_TONICS = [ "C", "G",  "D",  "A",  "E",  "B",  "F#", "a", "e", "b", "f#", "c#", "g#", "d#"]
FLAT_TONICS  = ["F",  "Bb", "Eb", "Ab", "Db", "Gb", "d",  "g", "c", "f", "bb", "eb"]

INTERVALS_DICT = {
    "m": 1,
    "M": 2,
    "A": 3
}

def get_pitches(tonic: str):
    if tonic in SHARP_TONICS:
        return SCALE_BASE_SHARP
    elif tonic in FLAT_TONICS:
        return SCALE_BASE_BEMOL
    return None

class Scale:
    def __init__(self, tonic):
        self.tonic = tonic.upper()
        if self.tonic == "BB":
            self.tonic = "Bb"
        elif len(self.tonic) > 1:
            self.tonic = self.tonic.replace("B", "b")
        self.scale = get_pitches(tonic)

    def chromatic(self):
        d = deque(self.scale)
        d.rotate(-self.scale.index(self.tonic))
        return list(d)

    def interval(self, intervals):
        index_tonic = self.scale.index(self.tonic)
        out = [self.tonic]
        for item in intervals:
            step = INTERVALS_DICT[item]
            index_tonic += step
            index_tonic %= 12
            out.append(self.scale[index_tonic])
        return out


if __name__ == '__main__':
    print(Scale("bb").interval("MmMMmMM"))