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
 * Environment.java
 *
 * Created on October 10, 2004, 12:14 AM
 */

package edu.unisa.chris.environment;
import edu.unisa.chris.observation.*;
import edu.unisa.chris.action.*;
import edu.unisa.chris.decision.Goal;
import edu.unisa.chris.agent.*;
/**
 *  Adopted in Java from the C++ RL Interface described by Richard S. Sutton and Juan Carlos Santamaria
 *  http://www.cs.ualberta.ca/~sutton/RLinterface/RLI-Cplusplus.html
 *
 *  The environment basically defines the problem to be solved. It determines the dynamics of the environment,
 *  the rewards, and the trial terminations.
 *
 *  This is the base class of all environments. Specific environments are instances of subclasses derived from
 *  Environment. User defined environment classes (subclasses of Environment) will normally provide specialized
 *  definitions of the following three functions.
 *
 * @author  Christos Sioutis
 */
public interface Environment {
    
    public void begin();
    
    /**  This function is normally provided by the user for her specialized environment class.
     * It should initialize the instance of the environment, making any needed data-structures.
     * If the environment changes in any way with experience, then this function should reset it
     * to its original, naive condition. The input arguments provide the generic command-line
     * initialization parameters using the standard format (i.e., argc is the number of
     * command-line parameters and argv is the array of pointers to strings). Normally, it is
     * called once when the simulation is first assembled and initialized. The default method does nothing.
     */
    public void init(Object initData, LearningAgent agent);

    /**  This function must be provided by the user for her specialized environment class.
     * It is normally called at the beginning of each new trial. It should perform any needed
     * initialization of the environment to prepare it for beginning a new trial. It should return
     * a pointer to the first sensation of the trial. The environment instance should provide the space
     * in memory where the sensation is stored, which should persist after the function is called.
     * Memory allocation should be done with the new constructor, the simulator takes the responsibility
     * of freeing the memory with delete when the object is no longer needed.  
     */
   // public Sensation startEpisode();
    
    /**  This is the main function for Environment. It must be provided by the user and will be called
     *  once by the simulation instance on each step of the simulation. It causes the environment to
     *  undergo a transition from its current state to a next state dependent on the action pointed by pa.
     *  The function returns the payoff of the state transition in the reference reward and the pointer
     *  to the next sensation in the reference pnext_s. The environment instance should provide the space
     *  in memory where the sensation is stored, which should persist after the function is called. 
     *  Memory allocation should be done with the new constructor, the simulator takes the responsibility
     *  of freeing the memory with delete when the object is no longer needed.
     *
     *  @returns A double value indicating the reward for the action taken
     */
   // public ActionResult performAction(Action action);
    
    /** This function forces an action that is waiting for a result to return. The environment
     * returns the results provided the this method to the task that has carried the action.
     *
     * @returns A boolean indicating whether an applicable action was found and subsequently cancelled.
     */
    //public boolean cancelAction(ActionResult result);
    
    
    /** This function senses the environment, it returns a sensation that is irrelavant to an action
     * There may be multiple sensations while an action is underway. This allows the agent to reason about
     * new sensations even though it has not completed its current action. 
     * 
     * Alternatively the environment class can directly call the Activation.alertAgent() method to alert
     * the agent about new sensation data, which will be handled in a separate decision process than any 
     * action currently taken.
     */
    //public Sensation sense(); 
    
}
