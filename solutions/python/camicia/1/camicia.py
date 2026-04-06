def simulate_game(player_a, player_b):
    # Convert decks to lists for mutability
    deck_a = list(player_a)
    deck_b = list(player_b)
    pile = []
    
    # Game state variables
    turn = 'A'  # Who is currently playing
    penalty_value = 0  # Number of cards to pay for penalty
    penalty_owner = None  # Who receives the pile if penalty is paid ('A' or 'B')
    
    cards_played = 0
    tricks = 0
    
    # History for loop detection
    # State key: "normalized_deck_a|normalized_deck_b|turn"
    # Normalization replaces number cards (2-10) with 'N' to ignore their values
    history = set()

    face_values = {'J': 1, 'Q': 2, 'K': 3, 'A': 4}
    
    def is_face(card):
        return card in face_values
        
    def normalize(deck):
        return "".join([c if is_face(c) else 'N' for c in deck])

    # Add initial state
    history.add(f"{normalize(deck_a)}|{normalize(deck_b)}|{turn}")

    while True:
        # Determine active deck
        active_deck = deck_a if turn == 'A' else deck_b
        other_deck = deck_b if turn == 'A' else deck_a
        
        # Check if active player can play
        if not active_deck:
            # Player ran out of cards and cannot play.
            # Opponent collects the pile and wins.
            if pile:
                other_deck.extend(pile)
                pile.clear()
                tricks += 1
            
            # Game ends
            return {"status": "finished", "cards": cards_played, "tricks": tricks}

        # Play card
        card = active_deck.pop(0)
        pile.append(card)
        cards_played += 1
        
        if is_face(card):
            # Face card played
            # Start/Interrupt penalty phase
            penalty_value = face_values[card]
            penalty_owner = turn
            
            # Switch turn to opponent to pay penalty
            turn = 'B' if turn == 'A' else 'A'
        else:
            # Number card played
            if penalty_value > 0:
                # Currently paying a penalty
                penalty_value -= 1
                
                if penalty_value == 0:
                    # Penalty paid successfully without revealing a face card
                    # Penalty owner collects the pile
                    winner_deck = deck_a if penalty_owner == 'A' else deck_b
                    winner_deck.extend(pile)
                    pile.clear()
                    tricks += 1
                    
                    # Check win condition immediately after collecting
                    if not deck_a or not deck_b:
                        return {"status": "finished", "cards": cards_played, "tricks": tricks}

                    # Winner starts next round
                    turn = penalty_owner
                    penalty_owner = None
                    
                    # Check for loop at start of new round
                    state = f"{normalize(deck_a)}|{normalize(deck_b)}|{turn}"
                    if state in history:
                        return {"status": "loop", "cards": cards_played, "tricks": tricks}
                    history.add(state)
                # Else: continue paying, turn remains with current player
            else:
                # Normal play, no penalty active
                # Switch turn
                turn = 'B' if turn == 'A' else 'A'