class BowlingGame:
  def roll(self, rollValue):
      self.__updateScore(rollValue)
      frameScore = rollValue + self.__lastRoll
      self.__saveRollForNextTime(rollValue)
      self.__markifSpareForNextTime(frameScore)
      self.__markIfStrikeForNextTime(rollValue)


  def __markIfStrikeForNextTime(self, rollValue):
      if rollValue == 10 and self.__isSecoundRollInFrame():
        self.__isStrike = True
        self.__resetRollForNewFrame()



  def totalScore(self):
      return self.__totalScore

  def __updateScore(self, rollValue):
      self.__addRollValueToScore(rollValue)
      self.__addBonusIfSpare(rollValue)
      self.__addBonusIfStrike(rollValue)


  def __addRollValueToScore(self, rollValue):
      self.__totalScore = self.__totalScore + rollValue

  def __addBonusIfSpare(self, rollValue):
      if self.__isSpare:
        self.__totalScore = self.__totalScore + rollValue

  def __addBonusIfStrike(self, rollValue):
      if self.__isStrike and self.__isSecoundRollInFrame():
        self.__totalScore =  self.__totalScore + rollValue + self.__lastRoll
        self.__isStrike == False

  def __markifSpareForNextTime(self, frameScore):
      if(frameScore == 10):
        self.__isSpare = True
      else:
        self.__isSpare = False

  def __saveRollForNextTime(self, rollValue):
        if self.__isSecoundRollInFrame():
          self.__resetRollForNewFrame()
        else:
          self.__lastRoll = rollValue

  def __isSecoundRollInFrame(self):
      return self.__lastRoll >= 0

  def __resetRollForNewFrame(self):
      self.__lastRoll = -1

  def __setRollValue(self):
      self.__lastRoll = rollValue

  __totalScore = 0
  __lastRoll = -1
  __isSpare = False
  __isStrike = False
