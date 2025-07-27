from datetime import datetime, timedelta

class Clock:
    def __init__(self, hour, minute):
        self._datetime = datetime(year=1970, month=1, day=1)
        self._datetime += timedelta(hours=hour, minutes=minute)

    def __repr__(self):
        return f"Clock({self._datetime.hour}, {self._datetime.minute})"

    def __str__(self):
        return self._datetime.strftime("%H:%M")

    def __eq__(self, other):
        return str(other) == self.__str__()

    def __add__(self, minutes):
        return Clock(self._datetime.hour, self._datetime.minute + minutes)

    def __sub__(self, minutes):
        return Clock(self._datetime.hour, self._datetime.minute - minutes)
