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
 * TTTLearningGoal.java
 *
 * Created on 1 March 2005, 16:31
 */

package edu.unisa.chris_examples.joshua;
import edu.unisa.chris.orientation.*;
import edu.unisa.chris.learning.*;
import edu.unisa.chris.agent.*;
import edu.unisa.chris.action.*;
import java.util.*;
/**
 *
 * @author  Christos Sioutis
 */
public class TTTLearningGoal extends LearningGoal{
    /** Creates a new instance of TTTLearningGoal */
    public TTTLearningGoal(double exploration) {
        super("LearnTTT", CHRISConstants.RL_SARSA, CHRISConstants.POLICY_EGREEDY);
        this.exploration = exploration;
        exploration = 0.5;
    }

    public TTTLearningGoal(double exploration, boolean skillControlEnabled) {
        super("LearnTTT", CHRISConstants.RL_SARSA, CHRISConstants.POLICY_EGREEDY);
        this.exploration = exploration;
        this.passiveTarget = "TTTWinPlan";
        learning = CHRISConstants.LEARNING_PASSIVE;
        this.skillControl = skillControlEnabled;
	explorationMaximum = 0.5;
	explorationMinimum = 0;
    }    
    
    public Perception getPerception(edu.unisa.chris.orientation.StateInstance state) {
        TTTStateInstance tttsi = (TTTStateInstance) state;
        return new TTTPerception(tttsi.cells,tttsi.wins,tttsi.losses,tttsi.draws);
    }
    
    /** patched the super method with the wins and losses as they should reflext current values*/
    public Perception getPerception(StateInstance state, StateActionValueFunction sav){
        TTTPerception toReturn;
        TTTPerception perceptionOld = (TTTPerception) super.getPerception(state,sav);
        TTTStateInstance s = (TTTStateInstance) state;
        perceptionOld.wins = s.wins;
        perceptionOld.losses = s.losses;
        
        TTTPerception perceptionNew = (TTTPerception)getPerception(state);
        
        if(TTTPerception.cellsEqual(perceptionOld.cells,perceptionNew.cells))
            return perceptionOld;
        
        if(rotations==0){
            rotations = TTTPerception.getNumTranspositions(perceptionNew.cells,perceptionOld.cells);
            toReturn = new TTTPerception(perceptionOld.cells,perceptionOld.wins,perceptionOld.losses,perceptionOld.draws);
        }
        else
            toReturn = perceptionNew;

        if(rotations>0)
            toReturn.transposeCells(rotations);        
        
        return toReturn;
    }
    
    public double rewardFunction(Perception before, Action actionTaken, Perception after) {
        TTTPerception b = (TTTPerception) before;
        TTTPerception a = (TTTPerception) after;
        if(a.wins > b.wins)
            return 50;
        if(a.losses > b.losses)
            return -50;
        if(a.draws > b.draws)
            return -10;
        return -1;
    }
    
    int wins,losses,draws;
    public int getGoalStatus(edu.unisa.chris.orientation.StateInstance state) {
        TTTStateInstance tttsi = (TTTStateInstance) state;
        int toReturn = CHRISConstants.GOAL_ACTIVE;
        if(!(tttsi.wins == wins && tttsi.losses == losses && tttsi.draws == draws)){
            if(tttsi.wins > wins)
                toReturn = CHRISConstants.GOAL_ACHIEVED;
            else if(tttsi.losses > losses)
                toReturn = CHRISConstants.GOAL_FAILED;
            else
                toReturn = CHRISConstants.GOAL_IMPOSSIBLE;
            wins = tttsi.wins;
            losses = tttsi.losses;
            draws = tttsi.draws;
        }
        if(toReturn != CHRISConstants.GOAL_ACTIVE){
            rotations=0;
        }
        
        int plays = wins+losses+draws;

        if(learning == CHRISConstants.LEARNING_PASSIVE && plays==1000){
		skillControl = false;
		learning = CHRISConstants.LEARNING_ACTIVE;
	}

        if(skillControl == false && learning == CHRISConstants.LEARNING_ACTIVE){
            /*if(plays == 500)
                exploration=0.3;
            else if(plays==1000)
                exploration=0.2;
            else if(plays==1500)
                exploration=0.1;
            else if(plays==3000)
                exploration=0.01;
            else if(plays==4000)
                exploration=0.0; */
            
            if(plays<500){
                double m = -0.2/500;
                double c = 0.5;
                exploration = m * plays + c;
            }

            else if(plays>500 && plays < 1000){
                double m = -0.1/500;
                double c = 0.3-m*500;
                exploration = m * plays + c;
            }
            else if(plays>1000 && plays < 2000){
                double m = -0.1/1000;
                double c = 0.2-m*1000;
                exploration = m * plays + c;
		stepSizeParameter = 0.08;
            }
            else if(plays>2000 && plays < 3000){
                double m = -0.09/1000;
                double c = 0.1-m*2000;
                exploration = m * plays + c;
		stepSizeParameter = 0.05;
            }
            else if(plays>3000 && plays < 4000){
                double m = -0.01/1000;
                double c = 0.01-m*3000;
                exploration = m * plays + c;
		stepSizeParameter = 0.02;
            }
            else {
                exploration = 0;
		stepSizeParameter = 0.0;
            }
         }
        
        return toReturn;
    }
    
    int rotations;    
    public List getActionsAvailable(CHRISFunctions chris, StateInstance state, StateActionValueFunction sav){
        List toReturn = chris.getActionsAvailable(state);
        if(rotations>0)
            for(int i=0; i<toReturn.size();i++){
                TTTPlay p = (TTTPlay) toReturn.get(i);
                p.index = TTTPerception.transposeIndex(p.index,rotations);
            }
        return toReturn;
    }
    
    public Action getAction(Action action){
        TTTPlay p, toReturn;
        if(rotations<=0)
            return action;
        p = (TTTPlay) action;
        toReturn = new TTTPlay(p.type,p.index);
        toReturn.index = TTTPerception.transposeIndex(toReturn.index,4-rotations);
        return toReturn;
    }    
    
}
