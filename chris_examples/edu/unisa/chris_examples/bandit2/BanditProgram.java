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
 * BanditProgram.java
 *
 * Created on 29 October 2004, 11:36
 */

package edu.unisa.chris_examples.bandit2;
import java.util.*;
/**
 *
 * @author  Christos Sioutis
 */
public class BanditProgram {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bandit bandit;
        String logLevels = null;
        boolean skillEnabled = false;
        
        int tries=0;
        if(args.length > 0)
            tries = Integer.parseInt(args[0]);
        if(tries == 0)
            tries = 1000;
        
        if(args.length > 1)
            skillEnabled = Boolean.valueOf(args[1]).booleanValue();
        
        if(args.length > 2)
            logLevels = args[2];
    
        bandit = new Bandit(args[0], tries, skillEnabled, logLevels);
        
        
        //TenChoiceTestbed testBed = new TenChoiceTestbed();
        //bandit.test();
        bandit.activateDecisionProcess(new Double(0));
        //bandit.test();
        bandit.findBestAction();
        bandit.printPlots();
        System.exit(0);
        //bandit.executeTrials(10);
        //System.exit(0);
        // TODO code application logic here
    }
}
