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

package edu.unisa.chris.decision;
import edu.unisa.chris.orientation.*;
import aos.jack.util.cursor.Change;
import aos.jack.jak.beliefset.*;
import java.util.List;
import edu.unisa.chris.agent.CHRISConstants;
/*
 *
 * @author  Christos Sioutis
 */
public class EitherWaitCursor extends Change {
    Goals goals;
    List initial;
    /** Creates a new instance of EpisodeIdentificationCursor
     * when identifyInitialState = true it matches the initial state
     * when identifyInitialState = false it matches the terminal state */
    public EitherWaitCursor(Goals goalsBeliefSet, List initialGoalInfos, boolean test) {
        super(goalsBeliefSet,test);
        goals = goalsBeliefSet;
        initial = initialGoalInfos;
    }
    
    // if a parallel subgoal has failed then fail the cursor
    public boolean condition(){
            GoalInfo initInfo, finInfo;
        try{
            for(int i=0; i<initial.size(); i++){
                initInfo = (GoalInfo) initial.get(i);
                finInfo = (GoalInfo) goals.getGoalInfo(initInfo.name,initInfo.parent);
                if(finInfo.failureCount > initInfo.failureCount || finInfo.successCount > initInfo.successCount) //check if this subgoal has failed
                    return true;
            }
        }
        catch(BeliefSetException e){
            System.out.println("CHRIS: Decision: ParallelExecutionCursor: Error accessing the beliefset");
        }        
        return false;
    }
}
