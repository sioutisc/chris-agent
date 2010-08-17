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
 * BanditPerception.java
 *
 * Created on 1 March 2005, 16:37
 */

package edu.unisa.chris_examples.bandit2;
import edu.unisa.chris.learning.*;
/**
 *
 * @author  Christos Sioutis
 */
public class BanditPerception extends Perception{
    public double value;
    /** Creates a new instance of BanditPerception */
    public BanditPerception(double valueRecieved) {
        value = valueRecieved;
    }
    
    public boolean equivalent(Perception other) {
            return true;
    }
    
    public int hashCode(){
        return (int)(value * 1000);
    }
    
    public String toString(){
        return "BanditPerception{"+value+"}";
    }
    
}
