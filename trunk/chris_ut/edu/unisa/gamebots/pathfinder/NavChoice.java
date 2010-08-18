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
 * PathAction.java
 *
 * Created on 4 April 2005, 14:20
 */

package edu.unisa.gamebots.pathfinder;
import edu.unisa.chris.action.Action;
import edu.unisa.gamebots.utjackinterface.controller.*;
/**
 *
 * @author  Christos Sioutis
 */
public class NavChoice extends Action{
    UtNav choice;
    
    public NavChoice(UtNav nav) {
        super(nav.id,"NavPath");
        choice = nav;
        
    }
    
    public boolean equivalent(Action other){
        NavChoice c = (NavChoice) other;
        if(choice.id.equals(c.choice.id))
            return true;
        return false;
    }
    
    public int hashCode(){
        return action.hashCode();
    }
    
    public String toString(){
        return "NavChoice{"+choice+"}";
    }
}
