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
 * Tree.java
 *
 * Created on 4 May 2005, 16:06
 */

package edu.unisa.chris.util;
import java.util.*;
import edu.unisa.chris.agent.*;
/**
 *
 * @author  Christos Sioutis
 */
public class GoalTree {
    public Node root = new Node("_Agent_",9,0);
    /** Creates a new instance of Tree */
    public GoalTree() {
    }
    
    class Node{
        public String name;
        public int priority;
        public int type;
        public List children = new LinkedList();
        
        public Node(String goalName, int goalPriority, int goalType){
            name = goalName;
            priority = goalPriority;
            type = goalType;
        }
        
        public void childUp(String childName){
            Node child = getChildNode(childName);
            if(child != null){
                int index = children.indexOf(child);
                if(index>0){
                    swapChildren(index-1,index);
                }
            }
        }
        
        public void childDown(String childName){
            Node child = getChildNode(childName);
            if(child != null){
                int index = children.indexOf(child);
                if(index<children.size()-1){
                    swapChildren(index,index+1);
                }
            }
        }
        
        public void swapChildren(int i, int j){
            Node ci = (Node) children.get(i);
            children.set(i,children.get(j));
            children.set(j,ci);
        }
        
        public void edit(String goalName, int goalPriority, int goalType){
            name = goalName;
            priority = goalPriority;
            type = goalType;
        }
        
        public void addChild(String childName, int childPriority, int childType){
            if(!hasChild(childName))
                children.add(new Node(childName, childPriority, childType));
        }
        
        public void addChild(Node child){
            if(!hasChild(child.name))
                children.add(child);
        }
        
        public void editChild(String childName, String newName, int newPriority, int newType){
            Node child = getChildNode(childName);
            if(child != null){
                child.name = newName;
                child.priority = newPriority;
                child.type = newType;
            }
        }
        
        public void removeChild(String childName){
            Node child = getChildNode(childName);
            if(child != null)
                children.remove(child);
        }
        
        public Node getChildNode(String name){
            for(int i=0; i<children.size(); i++){
                Node child = (Node) children.get(i);
                if(child.name.equals(name))
                    return child;
            }
            return null;
        }
        
        public boolean hasChild(String name){
            for(int i=0; i<children.size(); i++){
                Node child = (Node) children.get(i);
                if(child.name.equals(name))
                    return true;
            }
            return false;
        }
        
        public String toString(){
            return name + " ("+ priority + ") ("+typeToString(type)+")";
        }
        
        public String typeToString(int type){
            switch(type){
                case 0: return "Procedural";
                case 1: return "Parallel";
                case 2: return "Optional";
                case 3: return "Resident";
                case 4: return "Either";
                case 5: return "IfElse";
            }
            return "UNKNOWN";
        }
        
        public String toString(String parent, int index){
            StringBuffer sb = new StringBuffer();
            String newline = System.getProperty("line.separator");
            if(name!="_Agent_"){
                sb.append("      <Goals__Tuple"+newline);
                sb.append("         :name \""+name+"\""+newline);
                sb.append("         :parent \""+parent+"\""+newline);
                sb.append("         :type "+type+newline);
                sb.append("         :active :false"+newline);
                sb.append("         :priority "+priority+newline);
                sb.append("         :successCount 0"+newline);
                sb.append("         :failureCount 0"+newline);
                sb.append("         :proceduralIndex "+index+newline);
                sb.append("      >"+newline);
            }
            for(int i=0; i<children.size(); i++)
                sb.append(((Node)children.get(i)).toString(this.name,i));
            return sb.toString();
        }
        
        public String toGraph(){
            StringBuffer sb = new StringBuffer();
            String newline = System.getProperty("line.separator");
            String quote = "\"";
            String pindex = "";
            String nodeName = quote+name+"\\n("+priority+")"+quote;
            if(children.size()==0)
                sb.append("   "+nodeName+" [style=filled, fillcolor="+getColour(type)+"]"+newline); 
            else    
                sb.append("   "+nodeName+" [style=filled, color="+getColour(type)+"]"+newline); 
            for(int i=0; i<children.size(); i++){
                Node child = (Node)children.get(i);
                pindex="";
                if(child.type==CHRISConstants.SUBGOAL_PROCEDURAL || child.type==CHRISConstants.SUBGOAL_OPTIONAL || child.type==CHRISConstants.SUBGOAL_IFELSE) 
                    pindex=",label="+i;
                String childName = quote+child.name+"\\n("+child.priority+")"+quote;
                sb.append("   "+nodeName+" -> " +childName+" [arrowhead="+getArrow(child.type)+pindex+"]"+ ";"+newline);
            }
            
            for(int i=0; i<children.size(); i++)
                sb.append(((Node)children.get(i)).toGraph());
            return sb.toString();
        }
        
        private String getArrow(int type){
            switch(type){
                case 0: return "normal";
                case 1: return "crow";
                case 2: return "diamond";
                case 3: return "dot";
                case 4: return "inv";
                case 5: return "tee";
            }
            return "normal";
        }
        
        private String getColour(int type){
            switch(type){
                case 0: return "khaki";
                case 1: return "sandybrown";
                case 2: return "skyblue";
                case 3: return "seagreen3";
                case 4: return "slateblue1";
                case 5: return "tomato";
            }
            return "gray";
        }
        
    }
    
    /** recursive search of the tree for a node */
    public Node findNode(String childName, String parentName, Node current){
        if(current.name.equals(parentName))
            for(int i=0; i<current.children.size(); i++){
                Node child = (Node)current.children.get(i);
                if(child.name.equals(childName))
                    return child;
            }
        else{
            for(int i=0; i<current.children.size(); i++){
                Node child = findNode(childName,parentName,(Node)current.children.get(i));
                if(child != null)
                    return child;
            }
        }
        return null;
    }
    
    /**recursive search for all nodes that have the given name*/
    public List findNodes(String name, Node current){
        List list = new LinkedList();
        if(current.name.equals(name)){
            list.add(current);
        }
        for(int i=0; i<current.children.size(); i++){
            list.addAll(findNodes(name,(Node)current.children.get(i)));
        }
        return list;
    }
    
    public void remove(String name, String parent, String grandParent){
        if(parent.equals("_Agent_")){
            root.removeChild(name);
        }
        else{
            Node parentNode = findNode(parent,grandParent,root);
            parentNode.removeChild(name);
        }
    }
    
    public void up(String name, String parent, String grandParent){
        if(parent.equals("_Agent_")){
            root.childUp(name);
        }
        else{
            Node parentNode = findNode(parent,grandParent,root);
            parentNode.childUp(name);
        }
    }
    
    public void down(String name, String parent, String grandParent){
        if(parent.equals("_Agent_")){
            root.childDown(name);
        }
        else{
            Node parentNode = findNode(parent,grandParent,root);
            parentNode.childDown(name);
        }
    }
    
    public void edit(String name, String parent, String grandParent, String newName, int newPriority, int newType){
        if(parent.equals("_Agent_")){
            root.editChild(name, newName, newPriority, newType);
        }
        else{
            Node parentNode = findNode(parent,grandParent,root);
            parentNode.editChild(name, newName, newPriority, newType);
        }
    }
    
    public void add(String name, String parent, int priority, int type){
        if(parent.equals("_Agent_")){
            root.addChild(name, priority, type);
        }
        else{
            Node node = new Node(name,priority,type);
            List parents = findNodes(parent,root);
            for(int i=0; i<parents.size(); i++){
                Node parentNode = (Node)parents.get(i);
                parentNode.addChild(node);
            }
        }
    }
    
    public List getChildren(String goal, String parent){
        if(goal.equals("_Agent_")){
            return root.children;
        }
        List list = new LinkedList();
        Node node = findNode(goal,parent,root);
        if(node!=null)
            list.addAll(node.children);
        return list;
    }
    
    
    private List getGoals(Node current){
        List list = new LinkedList();
        list.add(current.name);
        for(int i=0; i<current.children.size(); i++){
            list.addAll(getGoals((Node)current.children.get(i)));
        }
        return list;
    }
    
    public List getAllGoals(){
        return getGoals(root);
    }
    
    public String toString(){
        StringBuffer sb = new StringBuffer();
        String newline = System.getProperty("line.separator");
        sb.append("<TupleTable"+newline);
        sb.append("   :tuples ("+newline);
        sb.append(root.toString("_Agent_",0));
        sb.append("   )"+newline);
        sb.append(">");
        return sb.toString();
    }
    
    public void parse(String data){
        StringTokenizer st = new StringTokenizer(data,"< >\"");
        String token = null;
        String name = null;
        String parent = null;
        int type = 0;
        int priority = 0;
        while(st.hasMoreTokens()){
            token = st.nextToken();
            if(token.equals(":name"))
                name = st.nextToken();
            else if(token.equals(":parent"))
                parent = st.nextToken();
            else if(token.equals(":type"))
                type = Integer.parseInt(st.nextToken());
            else if(token.equals(":priority")){
                priority = Integer.parseInt(st.nextToken());
                this.add(name, parent, priority, type);
            }
        }
    }
    
    public String toGraph(){
        String newline = System.getProperty("line.separator");
        StringBuffer sb = new StringBuffer("digraph G {"+newline);
        sb.append(root.toGraph());
        sb.append("}");
        return sb.toString();
    }
    
}
