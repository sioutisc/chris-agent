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
 * LearningGoal.java
 *
 * Created on January 9, 2005, 12:57 AM
 */

package edu.unisa.chris.learning;
import edu.unisa.chris.orientation.*;
import aos.jack.jak.beliefset.Immutable;
import edu.unisa.chris.learning.*;
import edu.unisa.chris.action.*;
import edu.unisa.chris.agent.*;
import java.util.List;
/**
 *
 * @author  Christos Sioutis
 */
public abstract class LearningGoal{
    public String name;
    public int algorithm;
    public int policy;
    public int learning;
    public double equiprobability = 1; //temperature
    public double stepSizeParameter = 0.1; //alpha
    public double discountFactor = 0.9; //gamma
    public boolean variableStepSizeParameter = false; //alpha=1/k where k=num trials
    
    public double exploration = 0.1;

    //SKILL CONTROL VARIABLES
    public boolean skillControl = false;
    public boolean learningTypeControl = true;
    public boolean explorationControl = true;
    public boolean explorationChangeExponential = true;
    public boolean stepSizeControl = false;
    public boolean stepSizeChangeExponential = true;
    public double vfMaxChange = 0.0;
    public double confidence = 0.0;
    public double activeThreshold = 0.8;
    public double passiveThreshold = 0.4;
    public double explorationMinimum = 0.0;
    public double explorationMaximum = 1.0;
    public double stepSizeMinimum = 0;
    public double stepSizeMaximum = 0.1;
    public String passiveTarget;
    
    //STATE GENERALISATION VARIABLES
    public StateInstance currentStateInstance = null;
    public boolean replaceDuplicatePerception = false;
    
    //APPLICABLE ONLY FOR ACTIVE ALGORITHMS
    public boolean waitForActionFinish = true;
    public boolean waitForStateInstanceUpdate = false;
    //defines the ordering of waiting 
    //when true order is Action then State waiting
    //when false order is State then Action waiting
    public boolean waitOrderActionState = true; 
    
    public LearningGoal(String goalName, int learningAlgorithm, int policyType){
        name = goalName;
        algorithm = learningAlgorithm;
        policy = policyType;
        learning = CHRISConstants.LEARNING_ACTIVE;
    }

    public LearningGoal(String goalName, int learningAlgorithm, int policyType, int learningType){
        name = goalName;
        algorithm = learningAlgorithm;
        policy = policyType;
        learning = learningType;
    }    
    
    /** generates a numerical reward depending on the action taken in a particular perception */
    public abstract double rewardFunction(Perception before, Action actionTaken, Perception after);
    
    /** returns a Perception that is relavant to this LearningGoal from the current StateInstance
     * Note that Perception is an abstract class, it is assumed that the programmer will
     * define different Perceptions for each LearningGoal. This is because different LearningGoals
     * require different information from the current state
     * NOTE: I may consider abolishing Perception all together and simply using StateInstances for all
     * learning, this would work fine for small applications, however larger applications would
     * have trouble capturing useful state changes for particular learning goal. We'll see */
    public abstract Perception getPerception(StateInstance state);

    public Perception getPerception(StateInstance state, StateActionValueFunction sav){
        currentStateInstance = state;
        Perception newPerception = getPerception(state);
        
        if(replaceDuplicatePerception==true)
            return newPerception;
        
        try{
            Perception oldPerception = sav.getPerception(this.name,newPerception);
            if(oldPerception!=null){
                if(!oldPerception.equals(newPerception))
                    System.out.println("ERROR State contamination: "+oldPerception+","+newPerception);
                return oldPerception;
            }
        }
        catch(Exception e){e.printStackTrace();}
        return newPerception;
    }
    
    /** Returns the status of the learningGoal using ther stateinstance*/
    public abstract int getGoalStatus(StateInstance state);
    
    /** Returns actionAvailable as provided by the agent.
        This method may be overriden the customize the actions being passed into the learning algorithms.
        This would be useful when performing major generalisations to the state space */
    public List getActionsAvailable(CHRISFunctions chris, StateInstance state, StateActionValueFunction sav){
        return chris.getActionsAvailable(state);
    }
    
    /** Returns an action based on the action provided as the parameter.
        This function is useful when actions are majorly transformed by overriding getActionsAvailable() method such
        that they are not directly useable by the agent and need to be re-transformed such that the agent can use them.
        See Joshua for an example of these two methods in use. */
    public Action getAction(Action action){
        return action;
    }

    public String toString(){
        return name;
    }
    
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        LearningGoal tmp = (LearningGoal) obj;
        return equivalent(tmp);
    }
    
    public boolean equivalent(LearningGoal other){
        return name.equals(other.name);
    }

    public int hashCode(){
        return name.hashCode();
    }
}
