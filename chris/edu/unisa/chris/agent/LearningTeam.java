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
 * LearningTeam.java
 *
 * Created on 28 September 2004, 10:10
 */

package edu.unisa.chris.agent;
import aos.team.Team;
import aos.jack.jak.plan.Plan;
import edu.unisa.chris.observation.*;
import edu.unisa.chris.orientation.*;
import edu.unisa.chris.decision.*;
import edu.unisa.chris.action.*;
import edu.unisa.chris.environment.*;
import edu.unisa.chris.util.*;
import edu.unisa.chris.logging.*;

import java.util.*;
/**
 *
 *  The agent is the entity that interacts with the environment, that receives senations and selects actions.
 *   The agent may or may not learn, may or may not build a model of the environment, etc.
 *   The base class of all agents. Specific agents are instances of subclasses derived from Team.
 *   User defined agent classes (subclasses of Team) will normally provide specialized definitions of the
 *   following three functions.
 *
 * @author  Christos Sioutis
 */
public abstract class LearningTeam extends Team implements CHRISFunctions{
    int idCounter = 0;
    //public DecisionProcess decisionProcess;
    //public Environment environment;
    public Random uniform = new Random();
    public Random gaussian = new Random();
    DebugLog log = new DebugLog();

    public LearningTeam(String name){
        super(name);
        ProgressMessage.messageCHRIS();
    }
    
    public LearningTeam(String name, String logLevels){
        super(name);
        ProgressMessage.messageCHRIS();
        log = new DebugLog(logLevels);
    }
    
    public synchronized int getUniqueId(){
        idCounter++;
        return idCounter;
    }  
    
    // see Random.nextInt 
    public int uniformRandom(int n) {
        return uniform.nextInt(n);
    }
    
    public double uniformDouble(){
        return uniform.nextDouble();
    }
    
    // see Random.nextGaussian 
    public double gaussianRandom(){
        return gaussian.nextGaussian();
    }
    
    public List generateAlertData(Object sensationData) {
        List list = new LinkedList();
        list.add(sensationData);
        return list;
    }
    
    public Object generateInformationData(Object alertData) {
        return alertData;        
    }
    
    public void setLogLevels(String logLevels){
        log.flush();
        log = new DebugLog(logLevels);
    }
    
    public void logInfo(String logEntry, int logLevel, boolean direct){
        log.logInfo(logEntry, logLevel, direct);
    }
    
    public void logError(String logEntry, boolean direct){
        log.logError(logEntry, direct);
    }
    
    public void setDebugLog(DebugLog log) {
        this.log = log;
    }
}
