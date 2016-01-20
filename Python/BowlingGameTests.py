from BowlingGame import BowlingGame

def test_BowlingGameAddsTheFirstRollToTheScore():
  for rollValue in range(0,9):
    game = BowlingGame()
    game.roll(rollValue)
    assert game.totalScore() == rollValue

def test_BowlingGameSumsNewRollToTotal():
    game = BowlingGame()
    game.roll(2)
    game.roll(3)
    assert game.totalScore() == 5

def test_BowlingGameDoublesTheNextRollAfterASpare():
    game = BowlingGame()
    game.roll(5)
    game.roll(5)
    game.roll(6)
    assert game.totalScore() ==  22

def test_BowlingGameDetectsSparesOnlyWhenNotInTheSameFrame():
    game = BowlingGame()
    game.roll(4)
    game.roll(5)
    game.roll(5)
    game.roll(4)
    assert game.totalScore() == 18

def test_bowlingGameOnlyDecetsAStrikeWhenAppropriate():
    game = BowlingGame()
    game.roll(5)
    game.roll(5)
    game.roll(4)
    game.roll(4)
    game.roll(5)
    assert game.totalScore() == 27

def test_bowlingGameIdentifiesAStrike():
    game = BowlingGame()
    game.roll(10)
    game.roll(5)
    game.roll(4)
    assert game.totalScore() == 28
