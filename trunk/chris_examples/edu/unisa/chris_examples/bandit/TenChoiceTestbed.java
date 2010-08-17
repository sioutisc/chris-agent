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
 * TenChoiceTestbed.java
 *
 * Created on October 12, 2004, 12:34 AM
 */

package edu.unisa.chris_examples.bandit;
import edu.unisa.chris.environment.Environment;
import edu.unisa.chris.observation.*;
import edu.unisa.chris.action.*;
//import edu.unisa.chris_examples.bandit.choicemakingcap.*;
import java.util.*;
import edu.unisa.chris.agent.*;
/**
 *
 * @author  Christos Sioutis
 */
public class TenChoiceTestbed implements Environment{
    LearningAgent learningAgent;
    double qStar[] = new double[10];
    int optimalChoice;
    Random uniform = new Random();
    Random gaussian = new Random();
    
    double rewards[];
    int choiceHistory[];
    double percentOptimal[];
    int play = 0;
  
    int numPlays;
    
    /** Creates a new instance of TenChoiceTestbed */
    public TenChoiceTestbed() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("TestBed: Setting qStar={");
        for(int i=0; i< 10; i++) {
            qStar[i] = uniform.nextDouble() *2;
            if(i==0)
                sb.append(((double)((int)(qStar[i]*100)))/100);
            else
                sb.append(" ,"+((double)((int)(qStar[i]*100)))/100);
        }
        
        optimalChoice = getOptimalChoice();
        sb.append("}, Optimal Choice="+optimalChoice);        

        System.out.println(sb);
    }
    
    
    
    private int getOptimalChoice() {
        double max = 0.0;
        int choise = 0;
        
        for( int i=0; i<10; i++) {
            if(qStar[i] > max) {
                max = qStar[i];
                choise = i;
            }
        }
        return(choise);
    }
    
    
    public void step(int choice){
        choiceHistory[play] = choice;
        
        double currentReward = qStar[choice] + Math.abs(gaussian.nextGaussian());
        rewards[play] = currentReward;

        play++;
        
        learningAgent.activateDecisionProcess(new Double(currentReward));
    }
    
    
    
    private void calculateArrays() {
        double totalOptimalChoices = 0.0;
        //System.err.println("\nCalculating averages");
        for(int i=0; i<numPlays; i++){
            if(choiceHistory[i] == optimalChoice){
                totalOptimalChoices += 1.0;
            }
            
            percentOptimal[i] = totalOptimalChoices / numPlays * 100;    
        }
    }
    
    
    public void begin() {
        //this function is only applicable in real-time systems when the environment sends back continous updates
        //in that case this function begins the updates
    }
    
    public void init(Object initData, LearningAgent agent) {
       numPlays = ((Integer)initData).intValue();
       learningAgent = agent;
       
       rewards = new double[numPlays+1];
       choiceHistory = new int[numPlays+1];
       percentOptimal = new double[numPlays+1];
    }
    
    public String getPlots(){
        calculateArrays();
        StringBuffer sb = new StringBuffer();
        sb.append(System.getProperty("line.separator")+"JACK Bandit plots"+System.getProperty("line.separator"));
        sb.append("Play\tRewards\tPercentOptimal"+System.getProperty("line.separator"));
        for(int i=0; i<numPlays; i++){
            if((i+1)%100 == 0)
                sb.append((i+1) + "\t" + (((double)((int)(rewards[i]*100)))/100) + "\t" +  (((double)((int)(percentOptimal[i]*100)))/100)+System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}