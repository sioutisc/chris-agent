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
 * TTTPlay.java
 *
 * Created on 7 April 2005, 17:25
 */

package edu.unisa.chris_examples.joshua;
import edu.unisa.chris.action.Action;
/**
 *
 * @author  Christos Sioutis
 */
public class TTTPlay extends Action{
    public int type;
    public int index;
    
    /** Creates a new instance of TTTPlay */
    public TTTPlay(int type, int index) {
        super("{"+type+","+index+"}","TTTPlay");
        this.type = type;
        this.index = index;
    }
    
    public boolean equivalent(Action other) {
        TTTPlay a = (TTTPlay) other;
        if(type == a.type && index == a.index)
            return true;
        return false;
    }

    public int hashCode(){
        return (type+50) * index ;
    }
    
    public String toString(){
        return "TTTPlay{"+type+","+index+"}";
    }
}
