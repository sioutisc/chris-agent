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
 * Goalnfo.java
 *
 * Created on February 18, 2005, 11:29 AM
 */

package edu.unisa.chris.decision;
import edu.unisa.chris.learning.*;

/**
 *
 * @author  Christos Sioutis
 */
public class GoalInfo extends Perception{
    public String name;
    public String parent;
    public int type;
    public boolean active;
    public int priority;
    public int successCount;
    public int failureCount;
    public int proceduralIndex;
    
    /** Creates a new instance of Goalnfo */
    public GoalInfo(String goalName, String parentName, int goalType, boolean goalActive, int goalPriority, int goalSuccessCount, int goalFailureCount, int proceduralIndexNumber){
        name = goalName;
        parent = parentName;
        active = goalActive;
        priority = goalPriority;
        successCount = goalSuccessCount;
        failureCount = goalFailureCount;
        type = goalType;
        proceduralIndex = proceduralIndexNumber;
    }
    
    public GoalInfo(String goalName, String parentName, int goalType, int goalPriority, int proceduralIndexNumber){
        name = goalName;
        parent = parentName;
        active = false;
        priority = goalPriority;
        successCount = 0;
        failureCount = 0;
        type = goalType;
        proceduralIndex = proceduralIndexNumber;        
    }
    
    
    public boolean equivalent(Perception other) {
        if(other instanceof GoalInfo){
            GoalInfo g = (GoalInfo) other;
            if(name.equals(g.name) && parent.equals(g.parent) && type == g.type && active==g.active &&  priority == g.priority &&
               successCount == g.successCount && failureCount == g.failureCount && proceduralIndex == g.proceduralIndex)
            return true;
        }
        return false;
    }
    
    public int hashCode(){
        int toReturn = name.hashCode() + parent.hashCode() + 10*type + priority + successCount + failureCount +proceduralIndex;
        if(active)
            toReturn += 103; //some random integer that will make a difference in the total count
        return toReturn;
    }
    
    public String toString(){
        return "GoalInfo{"+name+","+parent+","+type+","+active+","+priority+","+successCount+","+failureCount+","+proceduralIndex+"}";
    }
    
}
