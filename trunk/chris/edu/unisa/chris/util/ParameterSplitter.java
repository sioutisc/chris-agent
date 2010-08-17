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
 * ParameterSplitter.java
 *
 * Created on 28 August 2005, 12:42
 */

package edu.unisa.chris.util;
import java.util.*;
/**
 *
 * @author  Christos Sioutis
 */
public abstract class ParameterSplitter {
    public static Vector split(String args[]){
        Vector toReturn = new Vector();
        
        for(int i=0; i<args.length; i++){
            StringTokenizer st = new StringTokenizer(args[i],"=");
            if(st.countTokens()>0){   
                if(st.countTokens()==1)
                    toReturn.add(new Parameter(st.nextToken()));
                else
                    toReturn.add(new Parameter(st.nextToken(),st.nextToken()));
            }
        }
        return toReturn;
    }
}
