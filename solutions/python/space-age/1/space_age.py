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
        
    def on_mercury(self):
        return round(self.earth_years / SpaceAge.VLOOK_DICT["mercury"],2)

    def on_venus(self):
        return round(self.earth_years / SpaceAge.VLOOK_DICT["venus"],2)

    def on_earth(self):
        return round(self.earth_years / SpaceAge.VLOOK_DICT["earth"],2)

    def on_mars(self):
        return round(self.earth_years / SpaceAge.VLOOK_DICT["mars"],2)

    def on_jupiter(self):
        return round(self.earth_years / SpaceAge.VLOOK_DICT["jupiter"],2)

    def on_saturn(self):
        return round(self.earth_years / SpaceAge.VLOOK_DICT["saturn"],2)

    def on_uranus(self):
        return round(self.earth_years / SpaceAge.VLOOK_DICT["uranus"],2)

    def on_neptune(self):
        return round(self.earth_years / SpaceAge.VLOOK_DICT["neptune"],2)
