package exams.n0;

import java.util.Arrays;
import java.util.Stack;

/*
 Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) 
 parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */
public class N32 {

	public static void main(String[] args) {
		System.out.println(new N32().longestValidParentheses(")()()(())"));
	}
	

	public int longestValidParentheses(String s) {
		if(s == null || s.length() == 0) return 0;
		
        int len = s.length();
        char[] str = s.toCharArray();
        int[] dp = new int[len+1];
        dp[0] = 0;
        int max = 0;
        
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<len; i++) {
        	char c = str[i];
        	if (c == ')') {
        		if (stack.size() == 0) {
        			dp[i+1] = 0;
        		} else {
        			int l = stack.pop();
        			dp[i+1] = dp[l] + (i-l) + 1;
        			if (dp[i+1] > max)
        				max = dp[i+1];
        		}
        	} else {
        		dp[i+1] = 0;
        		stack.push(i);
        	}
        }
        System.out.println(Arrays.toString(dp));
        
        return max;
    }
}
