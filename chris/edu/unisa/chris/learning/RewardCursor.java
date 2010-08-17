/* ********************************************************************* *
 *                                                                       *
 *   =============================================================       *
 *   Copyright 2002-2010,                                                *
 *   Christos Sioutis <christos.sioutis@gmail.com>                       *
 *   =============================================================       *
 *   This software was developed during my PhD studies at:               *
 *                                                                       *
 *   Knowledge Based Intelligent Engineering Systems Centre (KES)        *
 *   School of Electrical and Information Engineering                    *
 *   University of South Australia                                       *
 *   =============================================================       *
 *                                                                       *
 *   This file is part of CHRIS.                                         *
 *                                                                       *
 *   CHRIS is free software: you can redistribute it and/or              *
 *   modify it under the terms of the GNU Lesser General Public Licence  *
 *   as published by the Free Software Foundation, either version 3 of   *
 *   the License, or (at your option) any later version.                 *
 *                                                                       *
 *   CHRIS is distributed in the hope that it will be useful,            *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the       *
 *   GNU Lesser General Public License for more details.                 *
 *                                                                       *
 *   You should have received a copy of the GNU Lesser General Public    *
 *   License along with CHRIS.                                           *
 *   If not, see <http://www.gnu.org/licenses/>.                         *
 *                                                                       *
 * ********************************************************************* */



/*
 * EpisodeIdentificationCursor.java
 *
 * Created on 30 January 2005, 11:42
 */

package edu.unisa.chris.learning;
import edu.unisa.chris.orientation.*;
import edu.unisa.chris.action.*;
import aos.jack.util.cursor.Change;
import aos.jack.jak.beliefset.*;
/*
 *
 * @author  Christos Sioutis
 */
public class RewardCursor extends Change {
    String goal;
    Action action;
    Rewards rewards;
    boolean initialState;
    /** Creates a new instance of EpisodeIdentificationCursor
     * when identifyInitialState = true it matches the initial state
     * when identifyInitialState = false it matches the terminal state */
    public RewardCursor(String learningGoal, Action actionTaken, Rewards rewardsRecieved, boolean test,boolean removePrevious) {
        super(rewardsRecieved,test);
        goal = learningGoal;
        action = actionTaken;
        rewards = rewardsRecieved;
        if(removePrevious)
            removePreviousReward();
    }
    
    public void removePreviousReward(){
        try{
            rewards.removePreviousReward(goal,action);
        }
        catch(BeliefSetException e){
            System.out.println("CHRIS: Learning: RewardsCursor: Error removing old rewards");
        }
    }
    
    public boolean condition(){
        try{
            return rewards.hasReward(goal,action);
        }
        catch(BeliefSetException e){
            System.out.println("CHRIS: Learning: StateIdentificationCursor: Error accessing the beliefset");
        }
        return false;
    }
}
