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
 * DebugLogLevels.java
 *
 * Created on 6 October 2004, 14:47
 */

package edu.unisa.chris.logging;

/**
 *
 * @author  Christos Sioutis
 */
public interface DebugLevels {
    public final int NUM_LEVELS = 9;
    public final int LOG_AGENT = 0;
    public final int LOG_PLANS = 1;
    public final int LOG_BELIEFS = 2;
    public final int LOG_OBSERVATION = 3;
    public final int LOG_ORIENTATION = 4;
    public final int LOG_DECISION = 5;
    public final int LOG_ACTION = 6;
    public final int LOG_LEARNING = 7;
    public final int LOG_PLANING = 8;
}
