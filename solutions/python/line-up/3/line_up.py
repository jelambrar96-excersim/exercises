"""
This module provides functionality for managing file metadata.
It allows for the creation, reading, and updating of file attributes.
"""


def line_up_number(number):
    """Return the ordinal suffix for a given number (st, nd, rd, or th).
    
    Args:
        number: An integer to determine the ordinal suffix for.
        
    Returns:
        A string containing the ordinal suffix ("st", "nd", "rd", or "th").
        
    Examples:
        >>> line_up_number(1)
        'st'
        >>> line_up_number(2)
        'nd'
        >>> line_up_number(3)
        'rd'
        >>> line_up_number(4)
        'th'
        >>> line_up_number(11)
        'th'
        >>> line_up_number(12)
        'th'
        >>> line_up_number(13)
        'th'
        >>> line_up_number(21)
        'st'
        >>> line_up_number(22)
        'nd'
        >>> line_up_number(23)
        'rd'
    """
    mod10_number = number % 10
    mod100_number = number % 100
    if mod10_number == 1 and mod100_number != 11:
        return "st"
    if mod10_number == 2 and mod100_number != 12:
        return "nd"
    if mod10_number == 3 and mod100_number != 13:
        return "rd"
    return "th"


def line_up(name, number):
    """Return a formatted greeting message for a customer in line.
    
    Args:
        name: The name of the customer.
        number: The position of the customer in line.
        
    Returns:
        A formatted string with the customer's name, position, and a thank you message.
        
    Example:
        >>> line_up("Alice", 1)
        'Alice, you are the 1st customer we serve today. Thank you!'
        >>> line_up("Bob", 2)
        'Bob, you are the 2nd customer we serve today. Thank you!'
        >>> line_up("Carol", 23)
        'Carol, you are the 23rd customer we serve today. Thank you!'
    """
    return f"{name}, you are the {number}{line_up_number(number)} customer we serve today. Thank you!"
