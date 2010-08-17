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



package edu.unisa.chris_examples.joshua;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import java.util.*;
import edu.unisa.chris.util.*;
import edu.unisa.chris.agent.*;

/**
 * This is a modified version of the TicTacToe applet shipped with
 * Java 2 Software Development Kit. Instead of drawing the board and
 * pieces by way of overriding <code>paint</code>, this uses
 * JEditorPane with an html document. The html document is updated via
 * the HTMLDocument method <code>setOuterHTML</code>.
 *
 * @author Christos Sioutis
 */
public class TicTacToe extends MouseAdapter {
    private static final String circleHTML = "<td><img src=circle2.gif>";
    private static final String emptyMoveHTML = "<td><img src=emptyImage.gif>";
    private static final String crossHTML = "<td><img src=cross2.gif>";
    
    private static final String yourMoveHTML = "<b>Status:</b> Your Move";
    private static final String wonHTML = "<b>Status:</b> I WON!";
    private static final String lostHTML = "<b>Status:</b> You WON!";
    private static final String stalemateHTML = "<b>Status:</b> Stalemate";
    
    /**
     * The squares in order of importance...
     */
    private static final int moves[] = {4, 0, 2, 6, 8, 1, 3, 5, 7};
    
    /**
     * The winning positions.
     */
    private static boolean won[] = new boolean[1 << 9];
    private static final int DONE = (1 << 9) - 1;
    private static final int OK = 0;
    private static final int WIN = 1;
    private static final int LOSE = 2;
    private static final int STALEMATE = 3;
    
    private int circle;
    private int cross;
    private Random uniform = new Random();
    
    private JEditorPane jep;
    private HTMLDocument htmlDoc;
    private JFrame frame;
    
    int gameType = 1;
    Joshua agent;
    boolean compete = false;
    int agentChoice = -1;
    int numPlays,playCount;
    Semaphore mutex = new Semaphore(0);
    String pcSign = circleHTML;
    String agentSign = circleHTML;
    String userSign = crossHTML;
    
    private int wins,draws,losses;
    
    
    public void setTypes(){
        if(gameType == 3){
            pcSign = crossHTML;
        }
    }
    
    public void setAgent(Joshua agent){
        this.agent = agent;
    }
    
    public void agentChoice(int choice){
        agentChoice = choice;
        mutex.up();
    }
    
    public void switchPC(){
        pcSign = crossHTML;
        userSign = circleHTML;
    }
    
    /**
     * Mark all positions with these bits set as winning.
     */
    static void isWon(int pos) {
        for (int i = 0 ; i < DONE ; i++) {
            if ((i & pos) == pos) {
                won[i] = true;
            }
        }
    }
    
    /**
     * Initialize all winning positions.
     */
    static {
        isWon((1 << 0) | (1 << 1) | (1 << 2));
        isWon((1 << 3) | (1 << 4) | (1 << 5));
        isWon((1 << 6) | (1 << 7) | (1 << 8));
        isWon((1 << 0) | (1 << 3) | (1 << 6));
        isWon((1 << 1) | (1 << 4) | (1 << 7));
        isWon((1 << 2) | (1 << 5) | (1 << 8));
        isWon((1 << 0) | (1 << 4) | (1 << 8));
        isWon((1 << 2) | (1 << 4) | (1 << 6));
    }
    
    
    public TicTacToe(int gameType) {
        this.gameType = gameType;
        setTypes();
        new ImageIcon("circle2.gif");
        new ImageIcon("cross2.gif");
        frame = new JFrame("TicTacToe II");
        jep = new JEditorPane();
        jep.setEditable(false);
        jep.setEditorKit(new TicTacToeEditorKit());
        try {
            jep.setPage(getClass().getResource("TicTacToe.html"));
        } catch (java.io.IOException ioe) {}
        htmlDoc = (HTMLDocument)jep.getDocument();
        frame.getContentPane().add(new JScrollPane(jep));
        jep.addMouseListener(this);
        
        frame.pack();
        frame.setSize(new Dimension(600, 800));
        frame.show();
    }
    
    /**
     * Compute the best move for circle.
     * @return the square to take
     */
    private int bestMove(int circle, int cross) {
        int bestmove = -1;
        
        //return a random choice if the board it empty
        if(circle == 0 && cross == 0){
            return uniform.nextInt(9);
        }
        
        
        loop:
            for (int i = 0 ; i < 9 ; i++) {
                int mw = moves[i];
                if (((circle & (1 << mw)) == 0) && ((cross & (1 << mw)) == 0)) {
                    int pw = circle | (1 << mw);
                    if (won[pw]) {
                        // circle wins, take it!
                        return mw;
                    }
                    for (int mb = 0 ; mb < 9 ; mb++) {
                        if (((pw & (1 << mb)) == 0) && ((cross & (1 << mb)) == 0)) {
                            int pb = cross | (1 << mb);
                            if (won[pb]) {
                                // cross wins, take another
                                continue loop;
                            }
                        }
                    }
                    // Neither circle nor cross can win in one move, this will do.
                    if (bestmove == -1) {
                        bestmove = mw;
                    }
                }
            }
            if (bestmove != -1) {
                return bestmove;
            }
            
            // No move is totally satisfactory, try the first one that is open
            for (int i = 0 ; i < 9 ; i++) {
                int mw = moves[i];
                if (((circle & (1 << mw)) == 0) && ((cross & (1 << mw)) == 0)) {
                    return mw;
                }
            }
            
            // No more moves
            return -1;
    }
    
    /**
     * User move. This will invoke <code>doYourMove</code> to handle
     * updating the board if the move is legal.
     *
     * @return true if legal
     */
    private boolean yourMove(int m) {
        if ((m < 0) || (m > 8)) {
            return false;
        }
        if (((cross | circle) & (1 << m)) != 0) {
            return false;
        }
        doYourMove(m);
        return true;
    }
    
    /**
     * Computer move, this will invoke <code></code> if
     * the move is legal.
     *
     * @return true if legal
     */
    private boolean agentMove(){
        int best = -1;
        if ((cross | circle) == DONE) {
            return false;
        }
        int[] board = {circle,cross};
        agent.activateDecisionProcess(board);
        mutex.down();
        best = agentChoice;
        doAgentMove(best);
        return true;
    }
    
    private boolean myMove() {
        int best = -1;
        if ((cross | circle) == DONE) {
            return false;
        }
        if(pcSign.equals(circleHTML))
            best = bestMove(circle, cross);
        else
            best = bestMove(cross, circle);
        doMyMove(best);
        return true;
    }
    
    /**
     * Figure what the status of the game is.
     */
    private int status() {
        int toReturn = OK;
        if (won[circle]) {
            toReturn = WIN;
        }
        if (won[cross]) {
            toReturn = LOSE;
        }
        if ((cross | circle) == DONE) {
            toReturn = STALEMATE;
        }
        return toReturn;
    }
    
    /**
     * Resets the board to an empty state.
     */
    public void newGame() {
        for (int counter = 0; counter < 9; counter++) {
            toggleBoardElementHTML(getBoardElement(counter), emptyMoveHTML);
        }
        toggleStatus(yourMoveHTML);
        cross = circle = 0;
    }
    
    /**
     * Invoked when the mouse is released. This will attempt to create a new
     * move for the user based on where the mouse is released.
     */
    public void mouseReleased(MouseEvent me) {
        if (me.getClickCount() == 1) {
            int position;
            if (/*status() != OK ||*/
            (position = getBoardPosition(me.getX(), me.getY())) == -1 ||
            !yourMove(position)) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
    
    
    public void compete(){
        System.out.println("Play\tWins\tLosses\tDraws");
        compete = true;
        while(true){
            if(!checkFinished()){
                agentMove();
                try{Thread.sleep(10);}catch(Exception e){}
                if(!checkFinished()){
                    myMove();
                    try{Thread.sleep(10);}catch(Exception e){}
		}
                else{
                    checkLastAndExit();
                    try{Thread.sleep(50);}catch(Exception e){}
                    newGame();
                    try{Thread.sleep(50);}catch(Exception e){}
            	}
            }
            else{
                checkLastAndExit();
                try{Thread.sleep(50);}catch(Exception e){}
                newGame();
                try{Thread.sleep(50);}catch(Exception e){}
            }
        }
    }
    
    /**
     * Invoked to do a move for the user. The html document is updated
     * as a result of the user moving to position <code>position</code>.
     * If no one has won, <code>myMove</code> is invoked to handle the
     * computers choice.
     */
    private void doYourMove(int position) {
        if(userSign.equals(crossHTML))
            cross |= 1 << position;
        else
            circle |= 1 << position;
        
        // Update the position
        Element e = getBoardElement(position);
        toggleBoardElementHTML(e, userSign);
        
        if(!checkFinished()){
            if(agent!=null)
                agentMove();
            else
                myMove();
            
            if(checkFinished())
                checkLastAndExit();
        }
        else{
            checkLastAndExit();
        }
    }
    
    public boolean checkFinished(){
        switch (status()) {
            case WIN:
                won();
                break;
            case LOSE:
                lost();
                break;
            case STALEMATE:
                statelmate();
                break;
            default:
                return false;
        }
        return true;
    }
    
    public void checkLastAndExit(){
        numPlays--;
        playCount++;
        if(numPlays % 100 == 0)
            System.out.println(playCount+"\t"+wins+"\t"+losses+"\t"+draws);
        
        if(numPlays<=0){
            if(agent!=null)
                agent.saveLearning("TTTLearning.data");
            System.exit(1);
        }
    }
    
    private void doAgentMove(int position){
        Element e = getBoardElement(position);
        toggleBoardElementHTML(e, agentSign);
        if(agentSign.equals(circleHTML))
            circle |= 1 << position;
        else
            cross |= 1 << position;
    }
    
    /**
     * Updates the board in response to the computer choosing position
     * <code>position</code>.
     */
    private void doMyMove(int position) {
        Element e = getBoardElement(position);
        toggleBoardElementHTML(e, pcSign);
        if(pcSign.equals(circleHTML))
            circle |= 1 << position;
        else
            cross |= 1 << position;
    }
    
    /**
     * Invoked when the computer wins.
     */
    private void won() {
        if(compete==false)
            toggleStatus(wonHTML);
        else
            toggleStatus(lostHTML);
        
        if(agent != null){
            agent.activateDecisionProcess("won");
        }
        wins++;
    }
    
    /**
     * Inovked when the computer loses.
     */
    private void lost() {
        if(compete==false)
            toggleStatus(lostHTML);
        else
            toggleStatus(wonHTML);
        
        if(agent != null){
            agent.activateDecisionProcess("lost");
        }
        losses++;
    }
    
    /**
     * Invoked when the game has resulted in a stalemate.
     */
    private void statelmate() {
        toggleStatus(stalemateHTML);
        if(agent != null){
            agent.activateDecisionProcess("draw");
        }
        draws++;
    }
    
    /**
     * Togglets the status of game to show the passed in html string.
     */
    private void toggleStatus(String newHTML) {
        Element e = htmlDoc.getElement("status");
        try {
            htmlDoc.setInnerHTML(e, newHTML);
        }
        catch (BadLocationException ble) {
            System.out.println("BLE: " + ble);
        }
        catch (IOException ioe) {
            System.out.println("IOE: " + ioe);
        }
    }
    
    /**
     * Returns the element representing position <code>position</code>.
     */
    private Element getBoardElement(int position) {
        return htmlDoc.getElement(Integer.toString(position));
    }
    
    /**
     * Resets the html contents of the Element <code>e</code> to
     * the html string <code>html</code>.
     */
    private void toggleBoardElementHTML(Element e, String html) {
        try {
            Object id = e.getAttributes().getAttribute(HTML.Attribute.ID);
            int insertIndex = html.indexOf(">");
            html = html.substring(0, insertIndex) + " id=" + id +
            html.substring(insertIndex);
            htmlDoc.setOuterHTML(e, html);
        }
        catch (BadLocationException ble) {
            System.out.println("BLE: " + ble);
        }
        catch (java.io.IOException ioe) {
            System.out.println("IOE: " + ioe);
        }
    }
    
    /**
     * Returns the board position for the element at location <code>x</code>,
     * <code>y</code>. This will return -1 if the passed in location does not
     * represent a spot on the board.
     */
    private int getBoardPosition(int x, int y) {
        // Determine the offset for the passed in x, y location
        Position.Bias bias[] = new Position.Bias[1];
        int offset = jep.getUI().viewToModel(jep, new Point(x, y), bias);
        // A backward bias typically (at least in normal left to right text)
        // indicates an end of line condition. The passed in point was
        // beyond the visible region of the line. In which case the backward
        // bias indicates the location is at the end offset of the character
        // element. Since we will be using getCharacterElement followed by
        // a check of the bounds we subtract one from the offset so that
        // getCharacterElement returns the Element representing the end of
        // line and NOT the next line.
        if (offset > 0 && bias[0] == Position.Bias.Backward) {
            offset--;
        }
        
        // Get the character Element at that location, and find the
        // corresponding TD cell.
        Element e = htmlDoc.getCharacterElement(offset);
        while (e != null && e.getAttributes().getAttribute
        (StyleConstants.NameAttribute) != HTML.Tag.TD) {
            e = e.getParentElement();
        }
        
        if (e != null) {
            // Check that the location is really inside the table cell.
            Rectangle bounds;
            try {
                bounds = jep.getUI().modelToView(jep, e.getStartOffset(),
                Position.Bias.Forward);
                bounds = bounds.union(jep.getUI().modelToView
                (jep, e.getEndOffset(),
                Position.Bias.Backward));
                
                if (bounds.contains(x, y)) {
                    // found it
                    Object boardLocation = e.getAttributes().getAttribute
                    (HTML.Attribute.ID);
                    if (boardLocation != null) {
                        try {
                            return Integer.parseInt((String)boardLocation);
                        } catch (NumberFormatException nfe) {
                        }
                    }
                }
            } catch (BadLocationException ble) {
            }
        }
        return -1;
    }
    
    
    /**
     * A subclass of HTMLEditorKit that returns a different ViewFactory.
     */
    private class TicTacToeEditorKit extends HTMLEditorKit {
        public ViewFactory getViewFactory() {
            return new TicTacToeFactory();
        }
    }
    
    
    /**
     * A subclass of the HTMLFactory that will create a
     * <code>TicTacToeFormView</code> for <code>INPUT</code> Elements.
     */
    private class TicTacToeFactory extends HTMLEditorKit.HTMLFactory {
        public View create(Element e) {
            Object o = e.getAttributes().getAttribute
            (StyleConstants.NameAttribute);
            if (o == HTML.Tag.INPUT) {
                return new TicTacToeFormView(e);
            }
            return super.create(e);
        }
    }
    
    
    /**
     * A subclass of <code>FormView</code> that invokes <code>newGame</code>
     * when the action is performed (the user clicks on the button).
     */
    private class TicTacToeFormView extends FormView {
        TicTacToeFormView(Element e) {
            super(e);
        }
        
        public void actionPerformed(ActionEvent ae) {
            newGame();
        }
    }
    
    
    public static void main(String[] args) {
        //  SwingUtilities.invokeLater(new Runnable() {
        //     public void run() {
        TicTacToe ttt;
        Joshua agent;
        int gameType = 1;
        int plays = 1;
        double exploration = 0.1;
        String logLevels = "00000000";
        int learningType = CHRISConstants.LEARNING_ACTIVE;
        boolean skillControl = false;
        String data = "TTTLearning.data";
        
        if(args.length==0){
            System.out.println("USAGE (default values shown)");
            System.out.println("=====");
            System.out.println("java edu.unisa.chris_examples.joshua game=1 plays=1 [expl=0.1] [log=00000000] [learn=active] [skill=false] [data=TTTLearning.data]");
            System.out.println("===================="+System.getProperty("line.separator"));
            System.out.println("TicTacToe game types");
            System.out.println("1. You Vs Game");
            System.out.println("2. You Vs Joshua");
            System.out.println("3. Joshua Vs Game"+System.getProperty("line.separator"));
            System.out.println("plays = number of games to play against joshua");
            System.out.println("expl = Fractional percentage of how often to explore vs exploit learning");
            System.out.println("learn = active/passive, sets the learning type to use");
            System.out.println("skill = true/false, enables skill control for passive learning");
        }
        
        StringTokenizer st;
        String argType;
        for(int i=0; i<args.length; i++){
            try{
                st = new StringTokenizer(args[i],"=");
                argType = st.nextToken();
                
                if(argType.equals("game")){
                    gameType = Integer.parseInt(st.nextToken());
                }
                else if(argType.equals("plays")){
                    plays = Integer.parseInt(st.nextToken());
                }
                else if(argType.equals("expl")){
                    exploration = Double.parseDouble(st.nextToken());
                }
                else if(argType.equals("log")){
                    logLevels = st.nextToken();
                }
                else if(argType.equals("learn")){
                    String tmp = st.nextToken();
                    if(tmp.equals("passive"))
                        learningType = CHRISConstants.LEARNING_PASSIVE;
                }
                else if(argType.equals("skill")){
                    skillControl = Boolean.valueOf(st.nextToken()).booleanValue();
                }
                else if(argType.equals("data")){
                    logLevels = st.nextToken();
                }
            }
            catch(Exception e){
                System.out.println("Invalid argument: "+args[i]);
                System.out.println(e);
                System.exit(1);
            }
        }
        
        ttt = new TicTacToe(gameType);
        ttt.numPlays = plays;

	if(gameType>1){

	        if(learningType==CHRISConstants.LEARNING_ACTIVE)
	            agent = new Joshua(ttt,logLevels,1,exploration);
        	else
            	agent = new Joshua(ttt,logLevels,1,exploration,skillControl);
        
        	agent.loadLearning(data);
        	ttt.setAgent(agent);
        }

        if(gameType==3){
            ttt.compete();
        }
    }
}
