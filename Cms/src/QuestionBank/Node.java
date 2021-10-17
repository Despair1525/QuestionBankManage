/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionBank;

/**
 *
 * @author smileymask
 */
public class Node {
    Problem data;
    Node left;
    Node right;
    char color;
    Node parent;

    public Node(Problem data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.color = 'R';
        this.parent = null;
    }
    
   
}

