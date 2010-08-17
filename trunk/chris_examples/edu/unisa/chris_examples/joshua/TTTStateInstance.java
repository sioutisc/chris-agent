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
 * TTTStateInstance.java
 *
 * Created on 7 April 2005, 16:20
 */

package edu.unisa.chris_examples.joshua;
import edu.unisa.chris.orientation.*;
import java.util.*;
/**
 *
 * @author  Christos Sioutis
 */
public class TTTStateInstance extends StateInstance{
    public int[] cells;
    public int wins,losses,draws;
    /** Creates a new instance of TTTStateInstance */
    public TTTStateInstance(int[] cells, int wins, int losses, int draws) {
        this.cells = cells;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }
    
    public boolean equivalent(StateInstance other) {
        TTTStateInstance s = (TTTStateInstance) other;
        if(s.wins != wins || s.losses != losses || s.draws != draws)
            return false;
        if(cells == null)
            return false;
        else
            for(int i=0; i<cells.length; i++)
                if(cells[i] == 2 && s.cells[i] != 2)
                    return false;
        return true;
    }
    
    public int hashCode(){
        int toReturn = 0;
        for(int i=0; i<cells.length; i++)
            toReturn += cells[i];
        return toReturn;
    }
    
    public String toString(){
        StringBuffer toReturn = new StringBuffer("TTTStateInstance{");
        for(int i=0; i<cells.length; i++)
            toReturn.append(cells[i]+",");
        toReturn.append("}");
        return toReturn.toString();
    }
}
