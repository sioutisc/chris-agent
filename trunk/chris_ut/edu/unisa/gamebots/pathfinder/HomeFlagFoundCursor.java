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
 * EpisodeIdentificationCursor.java
 *
 * Created on 30 January 2005, 11:42
 */

package edu.unisa.gamebots.pathfinder;
import aos.jack.util.cursor.Change;
import aos.jack.jak.beliefset.*;
import edu.unisa.chris.action.*;
import edu.unisa.chris.agent.CHRISConstants;
import edu.unisa.gamebots.utjackinterface.*;
import edu.unisa.gamebots.utjackinterface.controller.*;
import edu.unisa.gamebots.utjackinterface.caps.sync.*;
import java.util.*;
/**
 *
 * @author  Christos Sioutis
 */
public class HomeFlagFoundCursor extends Change {
    Self self;
    Flags flags;
    Actions actions;
    public HomeFlagFoundCursor(Actions actions, Self self, Flags flags, boolean test) {
        super(actions,test);
        this.actions = actions;
        this.self = self;
        this.flags = flags;
    }
    
    // if a parallel subgoal has failed then fail the cursor
    public boolean condition(){
        try{
            if(actions.activeAction() == null){
                UtSelf selfInfo = self.getInfo();
                List flagList = flags.getInfo();
                for(int i=0; i<flagList.size(); i++){
                    UtFlag flagInfo = (UtFlag) flagList.get(i);
                    if(flagInfo.team == UtConstants.TEAM_BLUE)
                        if(selfInfo.location.closeTo(flagInfo.location))
                            return true;
                }
            }
        }
        catch(BeliefSetException e){
            System.out.println("CHRIS: Decision: EitherExecutionCursor: Error accessing the beliefset");
        }        
        return false;
    }
}
