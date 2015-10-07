package bowling;

class Frame(first_roll: Int, secound_roll: Int) {
  val num_pins_in_frame = 10
  
  def this(first_roll: Int) = this(first_roll, -1)
  def this() = this(-1,-1)
  
  def firstRoll() = first_roll
  def secoundRoll() = secound_roll
  def strike() = first_roll == num_pins_in_frame
  def spare() = (first_roll + secound_roll == num_pins_in_frame) && (first_roll != num_pins_in_frame)
  def sum() = if(secound_roll == -1)  first_roll else first_roll + secound_roll
    
  def empty = first_roll == -1 && secound_roll == -1
  def fristRoll = secound_roll == -1
  def currentRoll(): Int = {  
    if(this.empty)  0
    else if(this.fristRoll) 1   
    else 2
  }
  

  
  
  
}