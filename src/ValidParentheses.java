import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
	public boolean isValid(String s) {
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
		     char ch = s.charAt(i);
			 if (ch == '(' || ch == '[' || ch == '{') {
				 stack.push(s.charAt(i));
			 }
			 else if (stack.isEmpty()) return false;
			 else {
			     if (map.get(ch) != stack.pop()) return false;
			 }
		}
		return stack.isEmpty();
	}
  
  
 /* public boolean isValidParentheses(String s) {
        if (s == null || s.length() == 0)   return true;
        Stack<Character> stack = new Stack<Character>();
        
        for (int i = 0; i < s.length(); i++) {
            if (stack.empty())  stack.push(s.charAt(i));
            else if (s.charAt(i) - stack.peek() == 1 || s.charAt(i) - stack.peek() == 2)    stack.pop();
            else    stack.push(s.charAt(i));
        }
        
        return stack.empty();
    }*/
}
