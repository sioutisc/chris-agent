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

===========================================================
CHRIS: Cognitive Hybrid Reasoning Intelligent Agent System
===========================================================

CHRIS provides a reasoning and learning framework for JACK agents.

You need a JACK license to build this code. 
See http://www.agent-software.com.au for more info.

Here are the URLs for relevant projects being used by CHRIS itself or the examples.

Using the ant JACK build task
http://code.google.com/p/simplejt

Used by pathfinder
http://code.google.com/p/utji
http://utbot.sourceforge.net
http://gamebots.sourceforge.net

To run
=======

* Ensure that your classpath has an entry to jack.jar
* Add the includes jar files in the CLASSPATH.

Run the CHRIS examples

The 10 armed bandit RL problem
> java edu.unisa.chris_examples.bandit.BanditProgram numberOfTries
> java edu.unisa.chris_examples.bandit.BanditProgram numberOfTries

The above generate a data which can be ploted. The plots look very similar
to the ones in chapter 2 of the book http://webdocs.cs.ualberta.ca/~sutton/book/the-book.html

Joshua, the TicTacToe player, inspired from the movie War Games, also described in the aforementioned book
a) Let the agent learn how to play, say 10000 plays
> java edu.unisa.chris_examples.joshua.TicTacToe game=3 plays=10000
The above will generate a TTTLearning.data file
The agent is forced to explore 10% of the time
> java edu.unisa.chris_examples.joshua.TicTacToe game=3 plays=100 skill=true
The agent should be a fairly good player by now
The above uses TTTLearning.data and enables skill control.
The agent automatically switches between active and passive learning based on the
rewards of the actions it is getting.
> java edu.unisa.chris_examples.joshua.TicTacToe game=2 plays=10
See how you fare against joshua now

Pathfinder, learns the quickest path between the blue and red flags in a UT CTF game
> java edu.unisa.gamebots.pathfinder.PathFinderProgram gamebotsServer PathFinder_goals.data learning.data exploration.data 111111111
The last argument is the CHRIS loglevels, 
when it is all ones it enables all logging so you can see what is happening across all CHRIS stages
replace it with zeros to suppress logging

