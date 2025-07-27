"""Functions to automate Conda airlines ticketing system."""

LETTERS = ('A', 'B', 'C', 'D')

def generate_seat_letters(number):
    """Generate a series of letters for airline seats.

    :param number: int - total number of seat letters to be generated.
    :return: generator - generator that yields seat letters.

    Seat letters are generated from A to D.
    After D it should start again with A.

    Example: A, B, C, D

    """
    ind = 0
    for i in range(number):
        yield LETTERS[ind]
        ind = (ind + 1) % len(LETTERS)


def generate_seats(number, skip_13=True):
    """Generate a series of identifiers for airline seats.

    :param number: int - total number of seats to be generated.
    :return: generator - generator that yields seat numbers.

    A seat number consists of the row number and the seat letter.

    There is no row 13.
    Each row has 4 seats.

    Seats should be sorted from low to high.

    Example: 3C, 3D, 4A, 4B

    """
    for i, letter in enumerate(generate_seat_letters(number)):
        row = i // len(LETTERS) + 1
        if row >= 13 and skip_13:
            row += 1
        yield f"{row}{letter}"


def assign_seats(passengers):
    """Assign seats to passengers.

    :param passengers: list[str] - a list of strings containing names of passengers.
    :return: dict - with the names of the passengers as keys and seat numbers as values.

    Example output: {"Adele": "1A", "Björk": "1B"}

    """
    return dict((item, seat) for item, seat in zip(passengers, generate_seats(len(passengers))))


def generate_codes(seat_numbers, flight_id):
    """Generate codes for a ticket.

    :param seat_numbers: list[str] - list of seat numbers.
    :param flight_id: str - string containing the flight identifier.
    :return: generator - generator that yields 12 character long ticket codes.

    """
    for item in seat_numbers:
        yield (item + flight_id)[::-1].zfill(12)[::-1]
