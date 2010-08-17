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



package edu.unisa.chris.util;

/** This class is used to display a message to the user with an indicator showing an "OK" or "FAILED" message. This functionality is useful when showing the a user that different stages in an task have been successful or failed.
 *
 * @author Christos Sioutis
 */
public class ProgressMessage {
    int messageLength = 0;
    int totalSpaces = 70;
    
    public ProgressMessage(){
    }
    
    public ProgressMessage(int totalNumSpaces){
        totalSpaces = totalNumSpaces;
    }
    
    public void beginMessage(String message){
        messageLength = message.length();
        System.out.print(message);
    }
    
    public void completeMessage(boolean result){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<(totalSpaces-messageLength); i++)
            sb.append(" ");
        if (result == true)
            sb.append("[  OK  ]");
        else
            sb.append("[FAILED]");
        System.out.println(sb);
    }
    
    public static void printMessage(int totalSpaces, String message, boolean result){
        StringBuffer sb = new StringBuffer(message);
        for(int i=0; i<(totalSpaces-message.length()); i++)
            sb.append(" ");
        if (result == true)
            sb.append(" [  OK  ]");
        else
            sb.append(" [FAILED]");
        System.out.println(sb);
    }
    
    public static void printMessage(String message, boolean result){
        printMessage(70, message, result);
    }
     
    /** Use this method to print out a message with an "OK" status on the end.
     *
     * @param message the message to display
     */
    public static void messageOk(String message){
        printMessage(message,true);
    }
    
    /** Use this method to print out a message with an "FAILED" status on the end.
     *
     * @param message the message to display
     */
    public static void messageFailed(String message) {
        printMessage(message,false);
    }
    
    public static void messageInfo(String message){
        System.out.println("["+message+"]");
    }
    
    public static void messageCHRIS(){
        System.out.println("[Uses CHRIS Framework, http://code.google.com/p/chris-agent]");
    }
    
    
    /** This main method is used for testing the class's code. It is not used by another application making use of this class.
     *
     * Expected arguments: [0,1] message
     * this refers to:    [OK,FAILED] message
     *
     * @param args command line arguments
     */
    public static void main(String args[]) {
        if (args.length > 0) {
            if(args[0].equals("0"))
                ProgressMessage.messageFailed(args[1]);
            else if(args[0].equals("1"))
                ProgressMessage.messageOk(args[1]);
            else
                System.out.println("Progress Message Test: Usage -  '[0,1] Message' without the square brackets");
        }
    }
    
}
