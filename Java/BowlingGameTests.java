package Tests;

import bowlinggame.InvalidRollException;
import org.junit.Test;
import org.junit.Assert;

import bowlinggame.BowlingGame;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

public class BowlingGameTests{
  BowlingGame game;

  @Before
  public void createBowlingGame() {
    game = new BowlingGame();
  }
  
  private void rollValues(BowlingGame game, int... values) throws InvalidRollException {
      for(int value : values)
          game.roll(value);
      
  }
  @Test
  public void theGameTotalScoreEqualsFirstRollafterFirstRoll() throws InvalidRollException {

      for(int i = 0; i < 10; i++) {
       BowlingGame game = new BowlingGame();
       game.roll(i);
       assertEquals(i, game.TotalScore());
      }
  }
  
  @Test
  public void theGameSummsValuesThatAreNotStrikesOrSpares() throws InvalidRollException {
    rollValues(game, 2, 3);
            
    assertEquals(5, game.TotalScore());
  }
  
  @Test
  public void theGameDoublesTheFirstValueAfterASpare() throws InvalidRollException { 
    rollValues(game, 5, 5, 6);
    assertEquals(22, game.TotalScore());
  }
  
  @Test
  public void toFivesInAdjcentFramesDoesNotASpareMake() throws InvalidRollException {
    rollValues(game, 3, 5, 5, 3);
    assertEquals(16, game.TotalScore()); 
  }
  
  @Test
  public void gameScoresStrikeWhena10IsRolled() throws InvalidRollException {
    rollValues(game, 10, 5, 3);
    assertEquals(26, game.TotalScore()); 
  }
  
  @Test
  public void gameAllowUserToRollStrikeAfterSpare() throws InvalidRollException {
    rollValues(game, 10, 5, 5,4,5);
    assertEquals(43, game.TotalScore());
  }
  
  @Test(expected=InvalidRollException.class)
  public void gameRejectsRollsIfTheRollIsLessThanZero() throws InvalidRollException {
    rollValues(game, -1); 
  }
  
  @Test(expected=InvalidRollException.class)
  public void gameRejectsRollsIfTheRollIsMoreThan10() throws InvalidRollException {
    rollValues(game, 11); 
  }
  
  @Test(expected=InvalidRollException.class)
  public void gameRejectsRollsIfThesumofAFrameIsMoreThan10() throws InvalidRollException {
    rollValues(game, 5, 7); 
  }
  
  
    
}
