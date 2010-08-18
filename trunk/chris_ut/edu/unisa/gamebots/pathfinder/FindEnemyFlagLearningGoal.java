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

package edu.unisa.gamebots.pathfinder;
import edu.unisa.chris.learning.*;
import edu.unisa.chris.agent.*;
import edu.unisa.chris.action.*;
import edu.unisa.chris.decision.*;
import edu.unisa.gamebots.chris.*;
import edu.unisa.gamebots.utjackinterface.*;
import edu.unisa.gamebots.utjackinterface.controller.*;
import java.util.*;
/**
 *
 * @author  Christos Sioutis
 */
public class FindEnemyFlagLearningGoal extends LearningGoal{
    UtChrisStateInstance currentState;
    /** Creates a new instance of BanditLearningGoal */
    public FindEnemyFlagLearningGoal() {
        super("FindEnemyFlagLearningGoal",CHRISConstants.RL_QLEARNING,CHRISConstants.POLICY_EGREEDY);
    }
    
    public Perception getPerception(edu.unisa.chris.orientation.StateInstance state) {
        currentState = (UtChrisStateInstance) state;
        return new Location(currentState.self.location,closestNav(currentState.self.location,currentState.navs));
    }
    
    public UtNav closestNav(UtCoordinate location, List navs){
        UtNav closest =(UtNav) navs.get(0);
        double distance = location.distanceFrom(((UtNav)navs.get(0)).location);
        for(int i=0; i< navs.size(); i++){
            UtNav tmp = (UtNav)navs.get(i);
            if(location.distanceFrom(tmp.location)<distance){
                closest = tmp;
                distance = location.distanceFrom(tmp.location);
            }
        }
        return closest;
    }
    
    public double rewardFunction(Perception before, Action actionTaken, Perception after) {
        Location b = (Location) before;
        Location a = (Location) after;
        UtSelf selfInfo = currentState.self;
        List flagList = currentState.flags;
        for(int i=0; i<flagList.size(); i++){
            UtFlag flagInfo = (UtFlag) flagList.get(i);
            if(flagInfo.team == UtConstants.TEAM_RED)
                if(selfInfo.location.closeTo(flagInfo.location)){
                    //System.out.println("reward=10");
                    return 10;
                }
        }
        if(a.location.y < b.location.y){
            //System.out.println("reward=-2");
            return -2;
        }
        //System.out.println("reward=-1");
        return -1;
    }
    
    public int getGoalStatus(edu.unisa.chris.orientation.StateInstance state) {
        return CHRISConstants.GOAL_ACHIEVED;
    }
}