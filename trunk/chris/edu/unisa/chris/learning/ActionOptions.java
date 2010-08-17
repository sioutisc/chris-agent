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
 * ActionOptions.java
 *
 * Created on 30 January 2005, 15:00
 */

package edu.unisa.chris.learning;
import java.util.*;
/**
 * Keeps an ORDERED list of ActionParameters for use by a policy
 * @author  Christos Sioutis
 */
public class ActionOptions {
    private List actionOptions = new LinkedList();
    private double maxValue = 0.0;
    private double minValue = 0.0;
    
    
    public String toString(){
        ActionValuePair tmp;
        StringBuffer sb = new StringBuffer();
        sb.append(System.getProperty("line.separator")+"*** ActionOptions ***");
        sb.append(System.getProperty("line.separator")+"* MaxValue="+maxValue +", MinValue="+minValue);
        for(int i=0; i<actionOptions.size(); i++){
            tmp = get(i);
            sb.append(System.getProperty("line.separator")+"* "+tmp.action+"("+tmp.value+")");
        }
        sb.append(System.getProperty("line.separator")+"*********************");
        return sb.toString();
    }
    
    public void add(ActionValuePair pair){
        int index = 0;
        if(actionOptions.size() == 0)
            actionOptions.add(pair);
        else{
            while(index<actionOptions.size() && pair.value < ((ActionValuePair)actionOptions.get(index)).value)
                index++;            
            actionOptions.add(index,pair);
        }
        maxValue = getFirst().value;
        minValue = getLast().value;
    }
    
    public List getAll(){
        return actionOptions;
    }
    
    public ActionValuePair remove(String actionName){
        for(int i=0; i<size(); i++){
            ActionValuePair tmp = get(i);
            if(actionName.equals(tmp.action.action))
                return (ActionValuePair) actionOptions.remove(i);
        }
        return null;
    }
    
    public ActionValuePair get(int index){
        return (ActionValuePair) actionOptions.get(index);
    }
    
    public ActionValuePair getFirst(){
        return (ActionValuePair) actionOptions.get(0);
    }
    
    public ActionValuePair getLast(){
        return (ActionValuePair) actionOptions.get(actionOptions.size()-1);
    }
    
    public double maxValue(){
        return maxValue;
    }
    
    public double minValue(){
        return minValue;
    }
    
    public int size(){
        return actionOptions.size();
    }
}
