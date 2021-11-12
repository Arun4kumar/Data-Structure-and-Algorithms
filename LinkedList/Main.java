package LinkedList;

import java.awt.List;
import java.util.*;

class Main {

    // Node

    static class Node implements Comparable<Node> {
        int data;
        Node down;
        Node next;

        Node(int data) {
            this.data = data;
        }

        @Override
        public

                int compareTo(Node other) {

            return this.data - other.data;
        }
        @Override 
        public String toString(){
            return Integer.toString(data);
        }
    }

    static Node head = null;
    static Node tail = null;

    private static void add(int data) {
        if (head == null) {
            head = new Node(data);
            tail = head;
        } else {
            
            tail.next = new Node(data);
            tail = tail.next;
        }
    }

    private static void printer(Node head) {
        if(head==null){
            return ;
        }
        Node current = head;
        while (current.next != null) {
            System.out.print(current.data + "->");
            current = current.next;
        }
        System.out.println(current.data);
    }

    public static void main(String args[]) {
        add(1);
        add(1);
        add(0);
        add(0);
        add(2);
        add(2);
        
                
        printer(sort012(head));

    }

    private static void deleteMiddle(Node head) {
        Node temp = null;
        Node current = head;
        Node fast = head;
        while (fast != null) {
            temp = current;
            current = current.next;
            if (fast.next != null) {
                fast = fast.next.next;
            }
        }

         
    }

    private static Node removeDup(Node node) {
        Node current = node;
        while (current.next != null) {
            if (current.next.data == current.data) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return node;
    }

    private static Node detectCycle(Node node) {
        Set<Node> set = new HashSet<>();
        Node slow = node;

        while (slow != null) {
            if (set.contains(slow.next)) {
                return slow;
            }
            set.add(slow);
            slow = slow.next;
        }
        return null;
    }

    private static Node removeCycle(Node node) {
        detectCycle(node).next = null;
        return node;
    }

    private static int removeLastKElement(Node node, int k) {
        if (node == null) {
            return 1;
        }
        int data = removeLastKElement(node.next, k);
        if (data == k + 1) {
            node.next = node.next.next;
        }
        return data + 1;
    }
    private static Node bringInFront(Node node){
        if(node.next==null){
            return node;
        }
        Node next = bringInFront(node.next);
        Node temp = next.next;
        next.next = node;
        node.next = temp;
        
        return next;
    }
    private static boolean isPalendrome(Node node) {
        Node main = node;
        Node fast = node.next;
        Node slow = node;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            }
        }
        Node rev = slow.next;
        Node prev = null;
        while (rev != null) {
            Node next = rev.next;
            rev.next = prev;
            prev = rev;
            rev = next;
        }
        while (prev != null && main != null) {
            if (prev.data != main.data) {
                return false;
            }
            prev = prev.next;
            main = main.next;
        }
        return true;
    }
    private static Node reverseK(Node node, int k) {
        Node updated = null;
        Node cur = node;
        int m = k;
        Node rev = node;
        Node prev = null;
        Node tailer = node;
        int n = k;
        
        if (node == null) {
            return null;
        }
        
       while (cur != null && m >1) {            
            cur = cur.next;
            m--;
        }
        
            updated = reverseK(cur.next, k);
      
        
        while (rev != null && n > 0) {
            n--;
            Node next = rev.next;
            rev.next = prev;
            prev = rev;
            rev = next;
        }
        if (updated != null) {            
            tailer.next = updated;
        }
        return prev;
    }

    private static Node reverseAlterK(Node node, int k) {
        if (node == null) {
            return null;
        }

        Node cur = node;
        int m = k * 2;
        while (cur != null && m > 1) {
            cur = cur.next;
            m--;
        }

        Node rev = node;
        Node prev = null;
        Node tailer = node;
        int n = k;
        while (rev != null && n > 0) {
            n--;
            Node next = rev.next;
            rev.next = prev;
            prev = rev;
            rev = next;
        }

        Node updated = null;
        if (cur != null) {
            System.out.println(cur.data);
            updated = reverseAlterK(cur.next, k);
        }

        tailer.next = rev;
        if (updated != null) {

            cur.next = updated;
        }
        return prev;
    }

    private static int removeLastK(Node node, int k) {
        if (node == null) {
            return 0;
        }
        int what = removeLastK(node.next, k);
        if (what == 1) {
            node.next = node.next.next;
            return 2;
        }
        if (node.data == k && what == 0) {
            return 1;
        }
        return what;

    }

    private static int size(Node node) {
        Node cur = node;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        return count;
    }

    private static Node rotate(Node node, int k) {
        Node current = node;
        int size = size(node);
        int m = size - k;
        Node temp = node;

        while (m > 1) {
            m--;
            temp = temp.next;
        }
        Node next = temp.next;
        while (next.next != null) {
            next = next.next;
        }
        next.next = current;
        Node ans = temp.next;
        temp.next = null;
        return ans;
    }

    private static Node addAltPos(Node list1, Node list2) {
        Node ours = list1;
        Node others = list2;
        while (ours != null && others != null) {
            Node next = ours.next;
            Node othernext = others.next;
            ours.next = others;
            others.next = next;
            ours = next;
            others = othernext;
        }
        if (others != null) {
            ours.next = others;
        }
        return list1;
    }

    static boolean isused = false;

    private static int addK(Node node, int k) {
        if (node == null) {
            return 0;
        }
        int carry = addK(node.next, k);
        int val = node.data + carry;
        if (!isused) {
            val += k;
            isused = true;
        }
        carry = val / 10;
        if (val > 9) {
            val = val % 10;
        }

        node.data = val;
        return carry;
    }

    private static Node falatern(Node node) {
        Queue<Node> que = new PriorityQueue<>();

        while (node != null) {
            que.add(node);
            node = node.next;
        }

        Node ans = new Node(-1);
        Node head = ans;
        while (que.size() != 0) {
            Node curr = que.remove();
            if (curr.down != null) {
                if (curr.next != null) {
                    curr.down.next = curr.next;
                }

                que.add(curr.down);

            }
            curr.next = null;
            curr.down = null;
            ans.next = curr;
            ans = curr;
        }

        return head.next;

    }
    private static Node start = null;
    private static Node test = null;
    private static Node recursiveRev(Node node){
        if(node==null){
            return null;
        }
        else if(node.next==null){
            test = node;
            start = node;
            return node;
        } 
        recursiveRev(node.next);
      node.next = null;
       
        test.next = node;
        test = test.next;
        return test;
    }
    
    private static Node merge(Node list1,Node list2){
        Node ans = new Node(-1);
        Node cur = ans;
        while(list1!=null && list2!=null){
            if(list1.data<list2.data){
                cur.next = list1;
                list1=list1.next;
            }
            else{
                cur.next = list2;
                list2=list2.next;
            }
            cur=cur.next;
        }
        if(list1!=null){
            cur.next = list1;
        }
        if(list2!=null){
            cur.next = list2;
        }
        return ans.next;
    }
    private static Node middle(Node node){
        if(node==null){
            return null;
        }
        Node slow = node;
        Node fast = node;
        while(fast.next!=null&& fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    private static Node mergeSort(Node node){
        if(node==null){
            return null;
        }
        else if(node.next==null){
            return node;
        }
        
        Node mid = middle(node);
        Node next = mid.next;
        mid.next = null;
       
        Node left = mergeSort(node);
        Node right = mergeSort(next);
        Node merged = merge(left,right);
        return merged;
    }
    private static Node sort012(Node node){
        if(node==null){
            return null;
        }
        Node list0 = null;
        Node list1 = null;
        Node list2 = null;
        Node list0t = null;
        Node list1t = null;
        Node list2t = null;
        Node cur = node;
        while(cur!=null){
            if(cur.data==0){
                if(list0==null){
                    list0 = cur;
                    list0t = cur;
                }
                else{
                    list0t.next = cur;
                    list0t = list0t.next;
                }
            }
            else if(cur.data ==1){
                if(list1==null){
                    list1 = cur;
                    list1t = cur;
                }
                else{
                    list1t.next = cur;
                    list1t = list1t.next;
                }
            }
            else{
                 if(list2==null){
                    list2 = cur;
                    list2t = cur;
                }
                else{
                    list2t.next = cur;
                    list2t = list2t.next;
                }
            }
            cur = cur.next;
        }
        if(list0==null){
           if(list1==null){
               return list2;
           }
           else{
               list1t.next = list2;
               return list1;
           }
        }
        else if(list1 == null){
            if(list0 == null){
                return list2;
            }
            else{
                list0t.next = list2;
                return list0;
            }
        }
        else{
            list1t.next = list2;
            list0t.next = list1;
            return list0;
        }
      
    }
 
}






