package bowling;
import org.scalatest._
import ScoreKeeper._


class BowlingGameTests extends FlatSpec with Matchers  {
  "the ScoreKeeper" should "have a value after the first roll equall to the first roll" in {
    
    val keeper = new ScoreKeeper()
    keeper.roll(5).TotalScore() should equal (5)
    keeper.roll(7).TotalScore() should equal (7)
  }
  
  "the ScoreKeeper" should "sum values less than 10 for one roll" in {
    val keeper = new ScoreKeeper()
    val updatedKeeper = keeper.roll(4)
    updatedKeeper.roll(5).TotalScore() should equal (9)
  }
  
  "the score keeper" should "mark spare if ten rolled in duo" in {
    val keeper = new ScoreKeeper()
    val updatedKeeper = keeper.roll(5)
    val spareKeeper = updatedKeeper.roll(5)
    spareKeeper.spare() should equal (true) 
  }
  
   "the score keeper" should "not have a spare after just one roll" in {
    val keeper = new ScoreKeeper()
    val updatedKeeper = keeper.roll(5)
    updatedKeeper.spare() should equal (false) 
  }
   
   "the score keeper" should "identify spares several roles in" in {
      val keeper = new ScoreKeeper()
      val updatedKeeper = keeper.roll(6)
      val spareKeeper = updatedKeeper.roll(4)
      val nextRollKeeper = spareKeeper.roll(5)
      val nextSpareKeeper = nextRollKeeper.roll(5)
      nextSpareKeeper.spare() should equal (true)
   
   }
   
   "the score keeper" should "identify strikes" in {
      val strikeKeeper = new ScoreKeeper().roll(10)
      strikeKeeper.strike() should equal (true)
   }
   
   "the score keeper" should "move to next frame after a strike" in {
     val strikeKeeper = new ScoreKeeper().roll(10)
     val nextFrameKeeper = strikeKeeper.roll(5)
     nextFrameKeeper.frameScore() should equal (5)
   
   }
   
   "the scoreKeeper" should "score sucessive frames with values under 10 sucessfully" in {
      val keeper = new ScoreKeeper()
      val updatedKeeper = keeper.roll(4)
      val spareKeeper = updatedKeeper.roll(3)
      val nextRollKeeper = spareKeeper.roll(2)
      val nextSpareKeeper = nextRollKeeper.roll(1)
      nextSpareKeeper.TotalScore should equal (10)
   
   }
   
   "the scoreKeeper" should "double the value of a frame after a strike" in {
      val keeper = new ScoreKeeper()
      val updatedKeeper = keeper.roll(10)
      val spareKeeper = updatedKeeper.roll(3)
      val nextRollKeeper = spareKeeper.roll(2)
      
      nextRollKeeper.TotalScore() should equal (20)
   }
   
   "the scoreKeeper" should "double the first value of the next frame" in {
      val keeper = new ScoreKeeper()
      val updatedKeeper = keeper.roll(7)
      val spareKeeper = updatedKeeper.roll(3)
      val nextRollKeeper = spareKeeper.roll(2)
      val finalRollKeeper = nextRollKeeper.roll(3)
      finalRollKeeper.TotalScore() should equal (17)
   }
   
   "the scoreKeeper" should "allow 3 rolls on the final frame" in {
       val keeper = rollXFrames(9)
       val finalRolls = keeper.roll(5).roll(5).roll(5)
       finalRolls.TotalScore should equal (38) 
   }
   
   "the scoreKeeper" should "fail after more than 11 frames" {
      val keeper = rollXFrames(9)
      val finalRolls = keeper.roll(5).roll(5).roll(5)
      an [GameFininhedBeforeRollException] should be thrownBy finalRolls.roll(5) 
   }
   
   
   def rollXFrames(x: Int, keeper: ScoreKeeper = new ScoreKeeper()): ScoreKeeper  = {
      val newkeeper = keeper.roll(1).roll(1)
      if(x == 0)
         return keeper
      else 
        return rollXFrames(x-1, newkeeper)     
   }
   
   
}