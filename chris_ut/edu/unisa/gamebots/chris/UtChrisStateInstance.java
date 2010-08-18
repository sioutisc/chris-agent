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
 * UtChrisStateInstance.java
 *
 * Created on March 29, 2005, 11:35 PM
 */

package edu.unisa.gamebots.chris;
import edu.unisa.gamebots.utjackinterface.*;
import edu.unisa.gamebots.utjackinterface.controller.*;
import edu.unisa.gamebots.utjackinterface.caps.sync.*;
import edu.unisa.gamebots.utjackinterface.caps.async.*;
import edu.unisa.chris.orientation.*;
import java.util.*;
/**
 *
 * @author  Christos Sioutis
 */
public class UtChrisStateInstance extends StateInstance{
    /** The decoded SLF message if recieved, null if not recieved
     */
    public UtSelf self;
    /** A list of the decoded PLR messages if recieved, null if not recieved
     */
    public UtGame game;
    /** A list of the decoded PLR messages if recieved, null if not recieved
     */
    public List players;
    /** A list of the decoded NAV messages if recieved, null if not recieved
     */
    public List navs;
    /** A list of the decoded MOV messages if recieved, null if not recieved
     */
    public List movs;
    /** A list of the decoded DOM messages if recieved, null if not recieved
     */
    public List doms;
    /** A list of the decoded FLG messages if recieved, null if not recieved
     */
    public List flags;
    /** A list of the decoded NAV messages if recieved, null if not recieved
     */
    public List invs;
    /** Creates a new instance of UtChrisStateInstance */
    
    public UtChrisStateInstance(UtSyncInfo sync){
        self = sync.self;
        game = sync.game;
        players = sync.players;
        navs = sync.navs;
        movs = sync.movs;
        doms = sync.doms;
        flags = sync.flags;
        invs = sync.invs;
    }
    
    public UtChrisStateInstance(UtSelf self, UtGame game, List players, List navs, List movs, List doms, List flag, List invs) {
        this.self = self;
        this.game = game;
        this.players = players;
        this.navs = navs;
        this.movs = movs;
        this.doms = doms;
        this.flags = flags;
        this.invs = invs;
    }
    
    public boolean equivalent(StateInstance other) {
        UtChrisStateInstance otherInstance = (UtChrisStateInstance) other;
        if(self.equals(otherInstance.self) && game.equals(otherInstance.game) && players.equals(otherInstance.players) &&
        navs.equals(otherInstance.navs) && movs.equals(otherInstance.movs) && doms.equals(otherInstance.doms) &&
        flags.equals(otherInstance.flags) && invs.equals(otherInstance.invs))
            return true;
        return false;
        
    }
    
    public int hashCode(){
        return self.hashCode() + game.hashCode() + players.hashCode() + navs.hashCode() + movs.hashCode() + doms.hashCode() + flags.hashCode() + invs.hashCode();
    }
    
    
    /* Borrowed from Navs beliefset */
    public UtNav closeToWhat(UtCoordinate currentLocation) {
        UtNav closest = getClosestNav(currentLocation);
        if(closest!=null && currentLocation.closeTo(closest.location))
            return closest;
        return null;
    }
    
    
    public UtNav getClosestNav(UtCoordinate currentLocation) {
        return(closest(navs,currentLocation));
    }
    
    private UtNav closest(List navs, UtCoordinate currentLocation){
        double distance, tmpDis;
        UtNav closest, tmpNav;
        
        if(navs.size()==0)
            return null;
        closest = (UtNav) navs.get(0);
        distance = currentLocation.distanceFrom(closest.location);
        for(int i=0; i<navs.size(); i++){
            tmpNav = (UtNav) navs.get(i);
            tmpDis = currentLocation.distanceFrom(tmpNav.location);
            if(tmpDis<distance){
                distance = tmpDis;
                closest = tmpNav;
            }
        }
        return closest;
    }
    
    public List getReachableNavs(boolean reachable){
        List list = new LinkedList();
        for(int i=0; i<navs.size(); i++){
            UtNav tmp = (UtNav) navs.get(i);
            if(tmp.reachable == reachable)
                list.add(tmp);
        }
        return list;
    }
}
