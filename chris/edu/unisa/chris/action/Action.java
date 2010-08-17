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
 * Action.java
 *
 * Created on 8 October 2004, 16:58
 */

package edu.unisa.chris.action;
import edu.unisa.chris.decision.*;
import aos.jack.jak.beliefset.Immutable;
/**
 *
 * @author  Christos Sioutis
 */
public abstract class Action implements Immutable, java.io.Serializable{
    public String action;
    public String category;
    /** Creates a new instance of ActionParameters */
    public Action(String actionName,String actionCategory) {
        action = actionName;
        category = actionCategory;
    }
    
/** Compares this instance to another instance. Should not need to change this method,
    just override the equivalent method as required. */    
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        Action tmp = (Action) obj;
        return equivalent(tmp);
    }
    
    /** ActionParameters are equivalent if and only if
        they contain the same class members and the members have the same values*/
    public abstract boolean equivalent(Action other);
    
    /** returns a unique integer value depending on the contents state
     *   implementations need to override this method for the beliefsets
     *   to work properly
     */    
    public abstract int hashCode();
}