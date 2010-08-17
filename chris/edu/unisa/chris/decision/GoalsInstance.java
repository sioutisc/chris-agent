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
 * ConsequenceStateInstance.java
 *
 * Created on February 12, 2005, 1:18 AM
 */

package edu.unisa.chris.decision;
import edu.unisa.chris.orientation.*;
import java.util.List;
/**
 *
 * @author  Christos Sioutis
 */
public class GoalsInstance extends StateInstance{
    public List goals;
    /** Creates a new instance of ConsequenceStateInstance */
    public GoalsInstance(List goals) {
        this.goals = goals;
    }
    
    public GoalInfo getGoalInfo(String goalName, String goalParent){
        GoalInfo tmp;
        for(int i=0; i<goals.size(); i++){
            tmp = (GoalInfo) goals.get(i);
            if(goalName.equals(tmp.name) && goalParent.equals(tmp.parent))
                return tmp;
        }
        return null;
    }
    
    public boolean equivalent(StateInstance other) {
        GoalsInstance otherGoals = (GoalsInstance) other;
        GoalInfo g1, g2;
        boolean status = true;
        if(goals.size() != otherGoals.goals.size())
            status = false;
        for(int i=0; i<otherGoals.goals.size() && status == true; i++){
            g1 = (GoalInfo) goals.get(i);
            g2 = (GoalInfo) otherGoals.goals.get(i);            
            if(g1.equals(g2) == false){
                status = false;
            }
        }
        return status;
    }
    
    public int hashCode(){
        int hashcode = 0;
        GoalInfo tmp;
        for(int i=0; i<goals.size(); i++){
            tmp = (GoalInfo) goals.get(i);
            hashcode += tmp.hashCode();
        }
        return hashcode;
    }
    
    public GoalInfo get(String goalName){
        GoalInfo tmp;
        for(int i=0;i<goals.size();i++){
            tmp = (GoalInfo) goals.get(i);
            if(tmp.name.equals(goalName))
                return tmp;
        }
        return null;
    }
    
    public String toString(){
        return "GoalsInstance{"+goals+"}";
    }
}
