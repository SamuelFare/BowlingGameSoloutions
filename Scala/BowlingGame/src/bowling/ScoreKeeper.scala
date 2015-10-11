package bowling;

class ScoreKeeper(frame_list: List[Frame] = List[Frame](new Frame(0,0))) {
  val current_frame = frame_list.head
  
  def frameScore(): Int = current_frame.sum()
  def spare(): Boolean = current_frame.spare()
  def strike(): Boolean = current_frame.strike()
  
  def TotalScore(): Int = sumAllFramesInList(frame_list)
  
  def sumAllFramesInList(listToSum: List[Frame], currentTotal: Int = 0): Int = { 
    if (listToSum.isEmpty)
      return currentTotal
    else if (!listToSum.tail.isEmpty)
      return sumAllFramesInList(listToSum.tail,currentTotal + getTotalForFrameGivenLastScore(listToSum.tail.head, listToSum.head))  
    else
      return sumAllFramesInList(listToSum.tail,currentTotal + listToSum.head.sum())  
  }
  
  def getTotalForFrameGivenLastScore(previousFrame: Frame, currentFrame: Frame): Int = {  
        if (previousFrame.strike()) currentFrame.sum() * 2
        else if (previousFrame.spare()) currentFrame.firstRoll() + currentFrame.sum()
        else currentFrame.sum()
  }
    
  def roll(roll_value: Int): ScoreKeeper = { 
    if(frame_list.size == 10)
      throw new GameFininhedBeforeRollException()
    if(isSecoundRoll()) { 
      return scoreKeeperWithFrameCompleted(current_frame.firstRoll(), roll_value)
    }
    else if(isStrike(roll_value)) {
       return scoreKeeperWithFrameCompleted(roll_value, 0)
    }
    return scoreKeeperWithNewFrame(roll_value)
  }
  
  def isStrike(roll_value: Int): Boolean = roll_value == 10
  def isSecoundRoll(): Boolean =  current_frame.currentRoll() == 1
  
  def scoreKeeperWithFrameCompleted(roll_one: Int, roll_two: Int): ScoreKeeper =  {
     return new ScoreKeeper(new Frame(roll_one,
         roll_two) :: frame_list.drop(1) )
  }
 
  def scoreKeeperWithNewFrame(roll_one: Int): ScoreKeeper =  {
    new ScoreKeeper(new Frame(roll_one) :: frame_list )  
  }
      
}