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
 * GoalResult.java
 *
 * Created on November 30, 2004, 7:14 AM
 */

package edu.unisa.chris.decision;
import edu.unisa.chris.learning.*;
import edu.unisa.chris.action.*;
/**
 *
 * @author  Christos Sioutis
 */
public class GoalChoice extends Action{
    public String parent;
    
    
    public GoalChoice(String goalName){
        super(goalName,"Goals");
    }
    
    public GoalChoice(String goalName, String parentName){
        super(goalName,"Goals");        
        parent = parentName;
    }
    
    public boolean equivalent(Action other) {
        GoalChoice otherChoice = (GoalChoice) other;
        return (action.equals(otherChoice.action) && category.equals(otherChoice.category) &&
                parent.equals(otherChoice.parent));
    }
    
    public int hashCode(){
        return action.hashCode() + category.hashCode() + parent.hashCode();
    }
    
    public String toString(){
        return "GoalChoice{"+action+","+category+","+parent+"}";
    }
    
}
