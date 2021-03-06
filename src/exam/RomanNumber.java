package exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumber {

	public static void main(String[] args) {
		RomanNumber rn = new RomanNumber();
		System.out.println(rn.intToRoman(3999));
		System.out.println(rn.romanToInt1(rn.intToRoman(394)));

		System.out.println(rn.isPalindrome(12121));

		List<String> results = rn.generateParenthesis(4);
		System.out.println(results.size());
		
		System.out.println(rn.isNumber("0"));
		System.out.println(rn.isNumber("0.1"));
		System.out.println(rn.isNumber(".1e2"));
		System.out.println(rn.isNumber("abc"));
		System.out.println(rn.isNumber("1 a"));
		System.out.println(rn.isNumber("2e10"));
		System.out.println(rn.isNumber("01"));

	}

	@LeetCode(value=20, c="a")
	public boolean isValid(String s) {
		if(s == null || s.length() == 0) return true;

		if(s.length() % 2 != 0) return false;

		char[] cc = new char[s.length()];
		int j = 0;

		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if(j == 0) {
				if(c == '(' || c == '{' || c == '[') {
					cc[j++] = c;
					continue;
				} else {
					return false;
				}
			}
			char h = cc[j-1];
			if(c == ')') {
				if(h == '(') j--;
			} else if (c == '}') {
				if(h == '{') j--;
			} else if(c == ']') {
				if(h == '[') j--;
			} else {
				cc[j++] = c;
			}
		}

		return j == 0;
	}

	public boolean isValid1(String s) {
		Stack<Character> q = new Stack<Character>();
		if(s == null || s.length() == 0) return true;

		if(s.length() % 2 != 0) return false;

		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if(q.isEmpty()) {
				if(c == '(' || c == '{' || c == '[') {
					q.push(c);
					continue;
				} else {
					return false;
				}
			}
			char h = q.peek();
			if(c == ')') {
				if(h == '(') q.pop();
			} else if (c == '}') {
				if(h == '{') q.pop();
			} else if(c == ']') {
				if(h == '[') q.pop();
			} else {
				q.push(c);
			}
		}

		return q.isEmpty();
	}

	public List<String> generateParenthesis(int n) {
		List<String> results = new ArrayList<String>();
		this.go(0, 0, n, "", results);
		//this.ggo(n,  n, "", results);
		return results;
	}

	public void ggo(int l, int r, String s, List<String> results) {
		if(l == 0 && r == 0) {
			System.out.println(s);
			results.add(s);
			return;
		}

		if(l > 0)
			ggo(l-1, r, s + "(", results);

		if(r>0 && l < r)
			ggo(l, r-1, s + ")", results);
	}

	public void go(int l, int r, int t, String s,  List<String> results) {
		if(r == t) {
			System.out.println(s);
			results.add(s);
			return;
		}
		
		if(l<t) {
			go(l+1, r, t, s + "(", results);
		}
		
		if(r<t && r<l) {
			go(l, r+1, t, s + ")", results);
		}
	}

	public boolean isNumber(String str) {
		Pattern pat = Pattern.compile("^\\s*[+-]?(\\d+\\.?|.\\d+|\\d+\\.\\d+)(e[+-]?([1-9]\\d*))?\\s*$");
		
		Matcher m = pat.matcher(str);
		return m.find();
	}
	
	/*
	Determine whether an integer is a palindrome. Do this without extra space.
	 */
	@LeetCode(9)
	public boolean isPalindrome(int x) {
		if(x<0) return false;

		int n = x;
		int r = 0;

		while(n > 0) {
			r = r * 10 + n % 10;
			n = n/10;
		}

		if(r == x) return true;
		return false;
	}

	public int romanToInt(String s) {
		int[] vv = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[] pp = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

		int v = 0;
		int i = 0;
		while(s.length() > 0 && i<pp.length) {
			String ss = pp[i];
			if(s.startsWith(ss)) {
				v += vv[i];

				s = s.substring(ss.length());
			} else {
				i++;
			}
		}

		return v;
	}

	public int romanToInt1(String s) {	
		int[] vv = new int['X'+1];
		vv['M'] = 1000;
		vv['D'] = 500;
		vv['C'] = 100;
		vv['L'] = 50;
		vv['X'] = 10;
		vv['V'] = 5;
		vv['I'] = 1;

		if(s == null || s.length() == 0) return 0;

		char l = s.charAt(0);
		int v = 0;
		for(int i=1; i<s.length(); i++) {
			char c = s.charAt(i);

			if(vv[l] < vv[c]) {
				v -= vv[l];
			} else {
				v += vv[l];
			}

			l = c;
		}

		v += vv[l];

		return v;
	}

	public String intToRoman(int num) {
		if(num > 3999) return "";

		StringBuffer sb = new StringBuffer();
		int[] vv = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[] pp = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int i = 0;
		while(num > 0) {
			if(num >= vv[i]) {
				sb.append(pp[i]);

				num -= vv[i];
			} else {
				i++;
			}
		}

		return sb.toString();
	}
}
