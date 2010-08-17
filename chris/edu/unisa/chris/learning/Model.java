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
 * Model.java
 *
 * Created on September 22, 2004, 4:48 PM
 */

package edu.unisa.chris.learning;
import edu.unisa.chris.learning.*;
import edu.unisa.chris.decision.*;
import java.util.*;


/**
 * The model is the agent's understanding of the dynamics of the environment. Using the model the agent can predict what will happen if it takes a particular action.
 * @author  Christos Sioutis
 */
public abstract class Model {
    /** Returns the expected next state for a given action taken in a specific state */
    //public abstract Perception getNextInstance(Perception initialState, Transition action);
    /** Returns the expected next state for a given action taken in a specific state, if more than one transition is enabled, the transition is picked randomly */
    //public abstract Perception getNextInstance(Perception initialState);
    
    /** Returns a Perception extracted from State with parameters only applicable to the specified Goal */
    //public abstract Perception getCurrentInstance(Goal goal);
    
    /** Predicts what changes will happen if a particular goal is chosen  */
    //public abstract PerceptionChange predictChange(Goal goal);
    //public abstract double predictReward(Goal goal);
    //public abstract Double getTransitionProbability(Object initialState, Object resultingState);
    //public abstract Set getStates();
    //public abstract double getValue(Object state);
}
