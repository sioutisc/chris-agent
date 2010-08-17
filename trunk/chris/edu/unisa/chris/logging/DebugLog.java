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
 * DebugLog.java
 *
 * Created on 6 October 2004, 13:58
 */

package edu.unisa.chris.logging;
import java.util.*;

/**
 *
 * @author  Christos Sioutis
 */
public class DebugLog {
    boolean logLevels[] = new boolean[DebugLevels.NUM_LEVELS];
    StringBuffer logBuffer = new StringBuffer();
    
    /** Creates a debug log whereby all logging is disabled by default */
    public DebugLog(){
        
    }
    
    /** Creates a debug log and populates its LogLevels according to information provided by the String parameter.
     *  A specific loglevel becomes true when there is a '1' in the  corresponding index of the string
     *  The true loglevel index enables logging for a specific part of the system as defined in DebugLogLevels.
     *
     *  For example, the following input strings have the following effects:
     *  "1" -  enables all logging functions
     *  "10" - enables logging that happens withing JACK agent reasoning methods
     *  "10000000" - enables logging for all components within the orientation stage
     */
    public DebugLog(String strLogLevels){
        for(int i=0; i < strLogLevels.length() && i < logLevels.length; i++){
            if(strLogLevels.charAt(i) == '1')
                logLevels[i] = true;
        }
    }
    
    /** Appends some information as a line in log buffer, the log itself however is not written */
    public synchronized void logInfo(String logEntry, int logLevel, boolean direct){
        if(debugEnabled(logLevel)){
            if(direct)
                writeToLog(logEntry);    
            else
                logBuffer.append(logEntry+System.getProperty("line.separator"));
        }
            
    }

    /** Appends an entry as an error message to the log buffer, the log itself however is not written */
    public synchronized void logError(String logEntry, boolean direct){
        if(direct)
            writeToLog("ERROR: "+logEntry);
        else
            logBuffer.append("ERROR: "+logEntry+System.getProperty("line.separator"));
    }
    /** writes the log all lines that exist in the buffer */
    public synchronized void flush(){
        writeToLog(logBuffer.toString());
        logBuffer.delete(0,logBuffer.length());
    }
   
    public boolean debugEnabled(int debugLevelIndex){
        if(debugLevelIndex < 0 || debugLevelIndex >= DebugLevels.NUM_LEVELS)
            return false;
        return logLevels[debugLevelIndex];
    }
    
    /** Writes a string to the log. Need to override this method in order to define how the log should be written. It writes it to the standard output by default */
    public void writeToLog(String logEntry){
        System.out.println(logEntry);
    }
}
