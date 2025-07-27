from datetime import datetime, time, timedelta
from calendar import FRIDAY, SATURDAY, SUNDAY, WEDNESDAY, monthrange


def dt(d, h):
    return datetime(d.year, d.month, d.day, h)


def delivery_date(start, description):
    d = datetime.fromisoformat(start)
    match description:
        case "NOW": d += timedelta(hours=2)
        case "ASAP" if d.hour < 12: d = dt(d, 17)
        case "ASAP": d = dt(d + timedelta(days=1), 13)
        case "EOW" if d.weekday() <= WEDNESDAY: d = dt(d + timedelta(days=FRIDAY - d.weekday()), 17)
        case "EOW": d = dt(d + timedelta(days=SUNDAY - d.weekday()), 20)
        case "Q1": d = q(d, 1)
        case "Q2": d = q(d, 2)
        case "Q3": d = q(d, 3)
        case "Q4": d = q(d, 4)
        case _: # <N>M
            m = int(description[:-1])
            d = datetime(d.year + (d.month >= m), m, 1, 8)
            if d.weekday() >= SATURDAY:
                d += timedelta(days=7 - d.weekday())  # Next Monday
    return d.isoformat()


def q(d, q_idx):
    m = 3 * q_idx
    ans = datetime(d.year, m, monthrange(d.year, m)[1], 8)
    if d >= ans:
        ans = datetime(d.year + 1, m, monthrange(d.year, m)[1], 8)
    if ans.weekday() >= SATURDAY:
        ans -= timedelta(days=ans.weekday() - FRIDAY)  # Prev Friday
    return ans
