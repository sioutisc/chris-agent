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
 * BanditAction.java
 *
 * Created on February 22, 2005, 12:57 AM
 */

package edu.unisa.chris_examples.bandit2;
import edu.unisa.chris.action.*;
/**
 *
 * @author  Christos Sioutis
 */
public class BanditAction extends Action{
    int selectedNumber;
    /** Creates a new instance of BanditAction */
    public BanditAction(int choiceNumber) {
        super(("Choice"+choiceNumber),"choice");
        selectedNumber = choiceNumber;
    }
    
    public boolean equivalent(Action other) {
        BanditAction otherAction = (BanditAction) other;
        if(selectedNumber == otherAction.selectedNumber)
            return true;
        return false;
    }
    
    public int hashCode(){
        return selectedNumber;
    }
    
    public String toString(){
        return "BanditAction{"+selectedNumber+"}";
    }
    
}
