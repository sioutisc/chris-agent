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
 * UtChrisInterface.java
 *
 * Created on March 29, 2005, 11:15 PM
 */

package edu.unisa.gamebots.chris;
import edu.unisa.gamebots.utjackinterface.*;
import edu.unisa.gamebots.utjackinterface.controller.*;
import edu.unisa.gamebots.utjackinterface.caps.sync.*;
import edu.unisa.gamebots.utjackinterface.caps.async.*;
import edu.unisa.chris.orientation.*;
import edu.unisa.chris.util.*;
/**
 *
 * @author  Christos Sioutis
 */
public class UtChrisState extends UtJackInterface implements State{
    CurrentStateInstance currentStateInstance;
    Semaphore syncMutex = new Semaphore(1);
    
    /** Creates a new instance of UtChrisInterface */
    public UtChrisState(CurrentStateInstance currentStateInstance, Self self, Game game, Players players, Navs navs, Movs movs, Doms doms, Flags flags, Invs invs, AsyncMsgs async) {
        super(self,game,players,navs,movs,doms,flags,invs,async);
        this.currentStateInstance = currentStateInstance;
    }
    
    
    public void stateChange(){
        try{
            currentStateInstance.add(getInstance());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void processSyncMessage(UtSyncInfo sync){
        syncMutex.down();
        super.processSyncMessage(sync);
        stateChange();
        syncMutex.up();
    }

    public StateInstance getInstance(){
        try{
            return new UtChrisStateInstance(new UtSyncInfo(self.getInfo(),game.getInfo(),players.getInfo(),navs.getInfo(),movs.getInfo(),doms.getInfo(),flags.getInfo(),invs.getInfo()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
