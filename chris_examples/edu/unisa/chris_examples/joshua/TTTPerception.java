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

package edu.unisa.chris_examples.joshua;
import edu.unisa.chris.learning.*;
/**
 *
 * @author  Christos Sioutis
 */
public class TTTPerception extends Perception{
    public int[] cells;
    public int wins,losses,draws;
    
    
    public TTTPerception(int[] cells, int wins, int losses, int draws) {
        this.cells = cloneCells(cells);
        this.wins = wins;
        this.losses = losses;
	this.draws = draws;
    }
    
    public void transposeCells(int numTimes){
        cells = transpose(cells,numTimes);
    }
    
    public boolean equivalent(Perception other) {
        TTTPerception tttp = (TTTPerception) other;
        int[] otherCells = cloneCells(tttp.cells);
        for(int i=0; i<4; i++){
            if(cellsEqual(cells,otherCells))
                return true;
            otherCells = transpose(otherCells);
        }
        return false;
    }
    
    public static int[] cloneCells(int cells[]){
        int c[]= {cells[0],cells[1],cells[2],cells[3],cells[4],cells[5],cells[6],cells[7],cells[8]}; 
        return c;
    }
    
    public int hashCode(){
        int toReturn=0;
        for(int i=0; i<cells.length; i++){
                toReturn+=cells[i]*i;
        }
        return toReturn;
    }
    
    public static boolean cellsEqual(int[] c1, int[] c2){
        for(int i=0; i<c1.length; i++){
            if(c1[i] != c2[i])
                return false;
        }
        return true;        
    }
    
    public static int[] transpose(int[] cells, int repeatTimes){
        int[] toReturn = cloneCells(cells);
        for(int i=0; i<repeatTimes; i++){
            toReturn = transpose(toReturn);
        }
        return toReturn;
    }
    
    public static int[] transpose(int[] cells){
        int[] toReturn = {cells[6],cells[3],cells[0],cells[7],cells[4],cells[1],cells[8],cells[5],cells[2]};
        return toReturn;
    }
    
    public static int transposeIndex(int index, int turns){
        int tmp = index;
        for(int i=0; i<turns; i++){
            tmp = transposeIndex(tmp);
        }
        return tmp;
    }
    
    public static int transposeIndex(int index){
        switch(index){
            case 0: return 6;
            case 1: return 3;
            case 2: return 0;
            case 3: return 7;
            case 4: return 4;
            case 5: return 1;
            case 6: return 8;
            case 7: return 5;
        }
        return 2;
    }
    
    public static int getNumTranspositions(int[] c1, int[] c2){
        int[] toCheck = c2;
        for(int i=0; i<4; i++){
            if(cellsEqual(c1,toCheck))
                return i;
            toCheck = transpose(toCheck);
        }
        return -1;
    }
    
    public String toString(){
        String toReturn = "TTTPerception{{";
        for(int i=0; i<9; i++){
            toReturn = toReturn + cells[i] +",";
        }
        toReturn = toReturn +"},"+wins+","+losses+"}";
        return toReturn;
    }
    
}
