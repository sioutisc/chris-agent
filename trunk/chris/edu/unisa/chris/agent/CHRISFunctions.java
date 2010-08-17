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
 * LearningAgentAction.java
 *
 * Created on December 13, 2004, 12:53 PM
 */

package edu.unisa.chris.agent;
import edu.unisa.chris.observation.*;
import edu.unisa.chris.orientation.*;
import edu.unisa.chris.decision.*;
import edu.unisa.chris.action.*;
import edu.unisa.chris.logging.*;
import java.util.*;
/**
 *
 * @author  Christos Sioutis
 */
public interface CHRISFunctions extends CHRISConstants{  
    //OBSERVATION FUNCTIONS
    public void activateDecisionProcess(Object rawData);
    public List generateAlertData(Object sensationData);
    public Object generateInformationData(Object alertData);
    
    //ORIENTATION FUNCTIONS
    public void updateWorldState(Object informationValues);
   
    //DECISION FUNCTIONS
    //public double getUsefulness(String goalName);
    //public GoalChoice chooseSubGoal(String goal, List goalChoices, StateInstance state);
    
    //ACTION FUNCTIONS
      public List getActionsAvailable(StateInstance state);

    //UTILITY FUNCTIONS
    public int getUniqueId();
    public int uniformRandom(int max);
    public double gaussianRandom();    
    public double uniformDouble();    
    
    //LOGGING FUNCTIONS
    public void setLogLevels(String logLevels);
    public void setDebugLog(DebugLog log);
    public void logInfo(String logEntry, int chrisSection, boolean direct);
    public void logError(String logEntry, boolean direct);

    public void saveLearning(String fileName);
    public void loadLearning(String fileName);
}
