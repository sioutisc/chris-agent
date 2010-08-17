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
 * BanditStateInstance.java
 *
 * Created on 4 February 2005, 15:17
 */

package edu.unisa.chris_examples.bandit2;
import edu.unisa.chris.orientation.*;
/**
 *
 * @author  Christos Sioutis
 */
public class BanditStateInstance extends StateInstance{
    public double currentValue;
    /** Creates a new instance of BanditStateInstance */
    public BanditStateInstance(double value) {
        currentValue = value;
    }
    
    public boolean equivalent(StateInstance other) {
        BanditStateInstance state = (BanditStateInstance) other;
        return currentValue == state.currentValue;
    }
    
    public int hashCode(){
        return (int) currentValue*1000;
    }
    
    public String toString(){
        return "BanditStateInstance("+currentValue+")";
    }
}
