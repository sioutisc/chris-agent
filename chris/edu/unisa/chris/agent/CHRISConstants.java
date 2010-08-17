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
 * LACoreConstants.java
 *
 * Created on 22 February 2005, 16:04
 */

package edu.unisa.chris.agent;
import edu.unisa.chris.logging.*;
/**
 *
 * @author  Christos Sioutis
 */
public interface CHRISConstants extends DebugLevels{
    
    //DECISION STAGE CONSTANTS
    //Subgoal type
    public static final int SUBGOAL_PROCEDURAL = 0;
    public static final int SUBGOAL_PARALLEL = 1;
    public static final int SUBGOAL_OPTIONAL = 2;
    public static final int SUBGOAL_RESIDENT = 3;
    public static final int SUBGOAL_EITHER = 4;
    public static final int SUBGOAL_IFELSE = 5;
    public static final int SUBGOAL_ALL = 6;
    //Subgoal Execution Results
    public static final int SUBGOAL_EXECUTION_UNKNOWN = 5;
    public static final int SUBGOAL_EXECUTION_SUCCESS = 6;
    public static final int SUBGOAL_EXECUTION_FAILED = 7;
    public static final int SUBGOAL_EXECUTION_NONE = 8;    
    
    //LEARNING STAGE CONSTANTS
    //Learning type
    public static final int LEARNING_ACTIVE = 10;
    public static final int LEARNING_PASSIVE = 11;

    //Algorithm type
    public static final int RL_SARSA = 20;
    public static final int RL_QLEARNING = 21;
    //Policy type
    public static final int POLICY_EGREEDY = 30;
    public static final int POLICY_SOFTMAX = 31;
    //Goal Status used for setting the scheduler
    public static final int GOAL_ACTIVE = 40;
    public static final int GOAL_IMPOSSIBLE = 41;
    public static final int GOAL_ACHIEVED = 42;
    public static final int GOAL_FAILED = 43;
    
    //Stages
    public static final int STAGE_OBSERVATION = 50;
    public static final int STAGE_ORIENTATION = 51;
    public static final int STAGE_DECISION = 52;
    public static final int STAGE_ACTION = 53;
    public static final int STAGE_LEARNING = 54;
}
