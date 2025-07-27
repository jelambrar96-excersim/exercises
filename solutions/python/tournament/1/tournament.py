import string


class SoccerTeam:

    def __init__(self, name):
        self.name = name
        self.mp = 0
        self.w = 0
        self.d = 0
        self.l = 0
        self.p = 0

    def win(self):
        self.mp += 1
        self.w += 1
        self.p += 3

    def draw(self):
        self.mp += 1
        self.d += 1
        self.p += 1

    def loss(self):
        self.mp += 1
        self.l += 1

    def __str__(self):
        str_mp = str(self.mp).rjust(2)
        str_w = str(self.w).rjust(2)
        str_d = str(self.d).rjust(2)
        str_l = str(self.l).rjust(2)
        str_p = str(self.p).rjust(2)
        return f"{self.name.ljust(30)} | {str_mp} | {str_w} | {str_d} | {str_l} | {str_p}"


def tally(rows):
    teams = {}
    for item in rows:
        p1, p2, r = item.split(';')
        if not p1 in teams:
            teams[p1] = SoccerTeam(p1)
        if not p2 in teams:
            teams[p2] = SoccerTeam(p2)
        if r == 'win':
            teams[p1].win()
            teams[p2].loss()
        if r == 'draw':
            teams[p1].draw()
            teams[p2].draw()
        if r == 'loss':
            teams[p1].loss()
            teams[p2].win()
    team_list = list(teams.values())
    # team_list.sort(reverse=True, key=lambda t1, t2: (t1.name < t2.name) if t1.p == t2.p else (t1.p == t2.p))
    team_list.sort(reverse=False, key=lambda t: (- t.p, t.name))
    return ["Team                           | MP |  W |  D |  L |  P"] + [str(item) for item in team_list ]
        

