
import calendar
from datetime import datetime

# subclassing the built-in ValueError to create MeetupDayException
class MeetupDayException(ValueError):
    """Exception raised when the Meetup weekday and count do not result in a valid date.

    message: explanation of the error.

    """
    def __init__(self, message=None):
        self.message = message if message is not None else "That day does not exist."


WEEKDAY_TABLE = {
    'Monday': 0,
    'Tuesday': 1,
    'Wednesday': 2,
    'Thursday': 3,
    'Friday': 4,
    'Saturday': 5,
    'Sunday': 6
}

INDEX_TABLE = {
    'first': 0,
    'second': 1,
    'third': 2,
    'fourth': 3,
    'fifth': 4,
}


def meetup(year, month, week, day_of_week):
    
    weekday = WEEKDAY_TABLE[day_of_week]
    list_dates = [ datetime(year, month, day) for day in range(1, calendar.monthrange(year, month)[1] + 1)]
    list_dates = [ dt for dt in list_dates if dt.weekday() == weekday]

    if week == "teenth":
        filtered_list = [ dt for dt in list_dates if dt.day in range(13, 20) ]
        if len(filtered_list) == 0:
            raise MeetupDayException("That day does not exist.")
        return filtered_list[0].date()

    if week == "last":
        return list_dates[-1].date()

    ind_date = INDEX_TABLE[week]
    if len(list_dates) <= ind_date:
        raise MeetupDayException("That day does not exist.")

    return list_dates[ind_date].date()
