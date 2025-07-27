class SpaceAge:

    VLOOK_DICT = {
        "mercury": 0.2408467,
        "venus": 0.61519726,
        "earth": 1.0,
        "mars": 1.880815,
        "jupiter": 11.862615,
        "saturn": 29.447498,
        "uranus": 84.016846,
        "neptune": 164.79132
    }
    
    def __init__(self, seconds):
        self.earth_years = seconds / 31557600
        for key, value in SpaceAge.VLOOK_DICT.items():
            setattr(self, f"on_{key}", lambda value=value: round(self.earth_years / value, 2))
