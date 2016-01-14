package bowlinggame;

public class BowlingGame {
    private int totalScore;
    private int lastRoll = -1;
    
    private boolean lastRollWasSpare = false;
    private boolean lastframeWasStrike = false;
    
    public void roll(int rollValue) throws InvalidRollException {
        assertRollValueIsValid(rollValue);
        updateScore(rollValue);
        ifSpareMarkForNextTime(rollValue);
        ifStrikeMarkForNextTime(rollValue);
        storeRollValueForNextTime(rollValue); 
        
    }

    private void assertRollValueIsValid(int rollValue) throws InvalidRollException {
        if(rollValue < 0 || rollValue > 10 || ((rollValue + lastRoll) > 10))
            throw new InvalidRollException();
    }

    private void ifStrikeMarkForNextTime(int rollValue) {
        if(isAllPinsKnockedDown(rollValue)) {
            lastframeWasStrike = true;
        }
    }

    private static boolean isAllPinsKnockedDown(int rollValue) {
        return rollValue == 10;
    }

    private void storeRollValueForNextTime(int rollValue) {
        if(!isLastRollStartOfFrame() ||isAllPinsKnockedDown(rollValue))
          makeThisRollStartOfFrame();
        else 
           AssignFirstRollToFrame(rollValue); 
    }

    private void AssignFirstRollToFrame(int rollValue) {
        lastRoll = rollValue;
    }

    private void makeThisRollStartOfFrame() {
        lastRoll = -1;
    }

    private boolean isLastRollStartOfFrame() {
        return lastRoll == -1;
    }

    private void updateScore(int rollValue) {
        
        if(lastRollWasSpare) {
            totalScore += rollValue * 2;
            lastRollWasSpare = false;
            
        }
        else if(lastframeWasStrike && !isLastRollStartOfFrame() ) {
            totalScore += addLastRollForSecoundTimeAndTwiceTheCurrentRoll(rollValue) ;
            lastframeWasStrike = false;
        }
        else {
            totalScore += rollValue;
        }
    }

    private int addLastRollForSecoundTimeAndTwiceTheCurrentRoll(int rollValue) {
        return lastRoll + (rollValue * 2);
    }

    private void ifSpareMarkForNextTime(int rollValue) {
        if(isAllPinsKnockedDown(lastRoll + rollValue)) {
            lastRollWasSpare = true;
        }
    }
    
    public int TotalScore() {
      return totalScore;
    }
}
