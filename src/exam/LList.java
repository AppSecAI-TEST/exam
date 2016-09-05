package exam;

public class LList {

	public static void main(String[] args) {
		ListNode head = new ListNode(10);
		ListNode tail = new ListNode('x');
		head.next = tail;
		tail.next = head;
		
		head = ListNode.create("1234567890");
		//head.next.next.next.next.next = head.next.next;
		LList l = new LList();
		//System.out.println(l.hasCycle(head));
		
		tail.next = head.next.next;
		//System.out.println(l.getIntersectionNodex(head,  tail));
		//System.out.println(l.getIntersectionNode(head,  tail));
		
		MinStack minStack = new MinStack();
		for(int i=10; i>0; i--) {
			minStack.push(i);
			if(i%3==0) minStack.pop();
			System.out.println("=" + minStack.getMin());
		}
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin()); //   --> Returns -3.
		minStack.pop();
		System.out.println(minStack.top()); //     --> Returns 0.
		System.out.println(minStack.getMin());
		
	}

	public boolean hasCycle(ListNode head) {
		if(head == null) return false;
		
		ListNode p = head;
		ListNode q = head.next;
		
		while(p != q && q != null && q.next != null) {
			p = p.next;
			q = q.next.next;
		}
		
		if(q == null || q.next == null)
			return false;
		return true;
	}
	
	public ListNode getIntersectionNodex(ListNode headA, ListNode headB) {
        if(headA == null && headB == null) return null;
        
        ListNode pA = headA;
        ListNode pB = headB;
        
        int la=0;
        int lb=0;
        
        while(pA!=null) {la++; pA = pA.next;}
        while(pB!=null) {lb++; pB = pB.next;}
        
        pA = headA;
        pB=headB;
        if(lb>la) {
            int c = lb-la;
            while(c>0) {
                pB = pB.next;
                c--;
            }
            while(la>0) {
                if(pA == pB) return pA;
                pB = pB.next;
                pA = pA.next;
            }
        } else {
            int c = la - lb;
            while(c>0) {pA = pA.next;c--;}
            while(lb>0) {
                if(pA == pB) return pA;
                pB = pB.next;
                pA = pA.next;
            }
        }
        return null;
    }
	
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null && headB == null) return null;
        
        ListNode pA = headA;
        ListNode pB = headB;
        
        while(pA!=null && pB!=null) {pA = pA.next; pB=pB.next;}
        if(pA != null) {
        	pB = headB;
        	while(pA!=null && pB!=null) {
        		pA = pA.next; pB=pB.next;
        	}
        	
        	if(pB!=null) {
        		pA = headA;
        	}
        	
        	while(pA!=null && pB!=null) {
        		if(pA == pB) return pA;
        		pA = pA.next; pB=pB.next;
        	}
        } else if(pB != null) {
        	pA = headA;
        	while(pA!=null && pB!=null) {
        		pA = pA.next; pB=pB.next;
        	}
        	
        	if(pA!=null) {
        		pB = headB;
        	}
        	
        	while(pA!=null && pB!=null) {
        		if(pA == pB) return pA;
        		pA = pA.next; pB=pB.next;
        	}
        }
        
        return null;
    }
}

class MinStack {
	class NNode {
		NNode next;
		int val;
		int min;
		
		NNode(int v){val = v; min = v;}
	}
	
	private NNode top;

    /** initialize your data structure here. */
    public MinStack() {
        top = new NNode(0);
    }
    
    public void push(int x) {
        NNode node = new NNode(x);
        
        node.next = top.next;
        top.next = node;
        
        if(node.next != null) {
	        if(node.next.min < x) {
	        	node.min = node.next.min;
	        }
        }
    }
    
    public void pop() {
        top.next = top.next.next;
    }
    
    public int top() {
        return top.next.val;
    }
    
    public int getMin() {
        return top.next.min;
    }
}

class MinStackx {
	class NNode {
		NNode left, right;
		NNode next;
		int val;
		
		NNode(int v){val = v;}
	}
	
	private NNode top, root;

    /** initialize your data structure here. */
    public MinStackx() {
        top = new NNode(0);
        root =new NNode(0);
    }
    
    public void push(int x) {
        NNode node = new NNode(x);
        
        node.next = top.next;
        top.next = node;
        
        NNode p = root;
        NNode q = root.left;
        while(q != null && q.val < x) {
        	p = q;
        	q = q.left;
        }
        p.left = node;
        node.right = p;
        node.left = q;
        if(q!=null) q.right = node;
    }
    
    public void pop() {
        //int v = top.right.val;
        
        NNode p = top.next.right;
        NNode q = top.next.left;
        top.next = top.next.next;
        
        if(p != null) {
        	p.left = q;
        }
        if(q != null) {
        	q.right = p;
        }
    }
    
    public int top() {
        return top.next.val;
    }
    
    public int getMin() {
        return root.left.val;
    }
}
