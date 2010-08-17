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
 * Schedule.java
 *
 * Created on 4 March 2005, 14:48
 */

package edu.unisa.chris.action;
import java.util.*;
/**
 *
 * @author  Christos Sioutis
 */
public class Scheduler extends Observable{
    private int numQueues;
    private LinkedList[] queues;
    /** Creates a new instance of Schedule */
    public Scheduler(int numberOfQueues) {
        numQueues = numberOfQueues;
        queues = new LinkedList[numQueues];
        for(int i=0; i<numberOfQueues; i++)
            queues[i] = new LinkedList();
    }
    
    public void add(String target, int priority){
        if(priority<0)
            queues[0].add(target);
        else if(priority>=numQueues)
            queues[numQueues-1].add(target);
        else
            queues[priority].add(target);
        setChanged();
        notifyObservers();
    }
    
    
    public boolean remove(String target){
        //observers are notified by the remove(string,priority) method,
        //this method simply calls that method
        for(int i=0; i<numQueues; i++){
            if(remove(target,i))
                return true;
        }
        return false;
    }
    
    public boolean remove(String target, int priority){
        boolean status = queues[priority].remove(target);
        if(status){
            setChanged();
            notifyObservers();            
        }
        return status;
    }
    
    public String getActiveTarget(){
        for(int i=0; i<numQueues; i++){
            if(containsTarget(i))
                return (String) queues[i].getFirst();
        }
        return null;
    }
    
    boolean containsTarget(int queue){
        if(queues[queue].size()>0)
            return true;
        return false;
    }
    
    boolean haveHigherPriorityTargets(int priority){
        for(int i=priority+1; i<numQueues; i++){
            if(containsTarget(i))
                return true;
        }
        return false;
    }
}
