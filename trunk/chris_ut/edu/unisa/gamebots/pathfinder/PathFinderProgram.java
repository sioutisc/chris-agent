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



package edu.unisa.gamebots.pathfinder;
import edu.unisa.gamebots.utjackinterface.*;
import java.io.*;
import java.util.*;

/**
 * @author Christos Sioutis
 */


public class PathFinderProgram{    
    public static void main(String[] args) {
        if(args.length < 5){
            System.out.println("Usage: java edu.unisa.gamebots.pathfinder.PathFinderProgram server goals learningData explorationData loglevels");
            System.exit(1);
        }
        PathFinder pathFinder = new PathFinder(args[1],"PathFinder", UtConstants.TEAM_BLUE);    
        pathFinder.setLogLevels(args[4]);
        pathFinder.loadLearning(args[2]);
        pathFinder.loadExplorationData(args[3]);
        pathFinder.connect(args[0]);
        try{Thread.sleep(1000);}catch(Exception e){System.out.println(e);}
        pathFinder.findPath();
        pathFinder.saveLearning(args[2]);
        pathFinder.saveExplorationData(args[3]);
        System.exit(0);
    }
}
