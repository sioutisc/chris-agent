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
 * BanditLearningGoal.java
 *
 * Created on 1 March 2005, 16:31
 */

package edu.unisa.chris_examples.bandit;
import edu.unisa.chris.learning.*;
import edu.unisa.chris.agent.*;
import edu.unisa.chris.action.*;
/**
 *
 * @author  Christos Sioutis
 */
public class BanditLearningGoal extends LearningGoal{
    int count, numTries;
    /** Creates a new instance of BanditLearningGoal */
    public BanditLearningGoal(int numberOfTries) {
        super("LearnBestAction", CHRISConstants.RL_QLEARNING, CHRISConstants.POLICY_EGREEDY);
        numTries = numberOfTries;
    }
    
    public Perception getPerception(edu.unisa.chris.orientation.StateInstance state) {
        BanditStateInstance bs = (BanditStateInstance) state;
        return new BanditPerception(bs.currentValue);
    }
    
    public double rewardFunction(Perception before, Action actionTaken, Perception after) {
        /*BanditPerception bp = (BanditPerception) after;
          return bp.value;*/
        /*perceptions are no longer different for the bandit because of generalisation code
         *therefore reward cannot be calculated using perception, now uses currentStateInstance instead
         *CurrentStateInstance is automatically updated by the getPerception method */

        BanditStateInstance s = (BanditStateInstance) currentStateInstance;
        return s.currentValue;
    }
    
    public int getGoalStatus(edu.unisa.chris.orientation.StateInstance state) {
        return CHRISConstants.GOAL_ACHIEVED;
    }
    
}
