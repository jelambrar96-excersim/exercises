def leap_year(year):
    """
    Determine whether a given year is a leap year.

    A year is a leap year if it is divisible by 4, except for years 
    that are divisible by 100, unless they are also divisible by 400.

    Parameters:
        year (int): The year to check.

    Returns:
        bool: True if the year is a leap year, False otherwise.
    """
    if year % 400 == 0:
        return True
    if year % 100 == 0:
        return False
    if year % 4 == 0:
        return True
    return False
