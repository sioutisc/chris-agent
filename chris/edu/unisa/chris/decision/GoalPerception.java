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
import edu.unisa.chris.learning.*;

/**
 *
 * @author  Christos Sioutis
 */
public class GoalPerception extends Perception{
    GoalsInstance goalsInstance;
    String choosingGoal;
    /** Creates a new instance of ConsequenceStateInstance */
    public GoalPerception(GoalsInstance goals,String goal) {
        goalsInstance = goals;
        choosingGoal = goal;
    }
    
    public boolean equivalent(Perception other) {
        GoalPerception perception = (GoalPerception) other;
        return (goalsInstance.equals(perception.goalsInstance) && choosingGoal.equals(perception.choosingGoal));
    }
    
    public int hashCode(){
        return goalsInstance.hashCode() + choosingGoal.hashCode();
    }    
}
