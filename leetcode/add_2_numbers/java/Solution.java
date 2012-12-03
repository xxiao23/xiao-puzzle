public class Solution {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ListNode resultHead = null;
        ListNode currentResult, prevResult = null;
        
        ListNode a1 = l1;
        ListNode a2 = l2;        
        boolean hasCarryOver = false;
        
        while (a1 != null && a2 != null) {
        	int val1 = a1.val;
        	int val2 = a2.val;
        	int carryOver = hasCarryOver ? 1 : 0; 
        	int sum = 0;
        	
        	if (val1 + val2 + carryOver >= 10) {
        		hasCarryOver = true;
        		sum = val1 + val2 + carryOver - 10;
        	} else {
        		hasCarryOver = false;
        		sum = val1 + val2 + carryOver;
        	}
        	
        	currentResult = new ListNode(sum);

        	if (resultHead == null) {
        		resultHead = currentResult;
        	} else {
        		prevResult.next = currentResult;
        	}
        	
        	prevResult = currentResult;
        	a1 = a1.next;
        	a2 = a2.next;
        	
        }
        
        
        while (a1 != null) {
        	int val1 = a1.val;
        	int carryOver = hasCarryOver ? 1 : 0;
        	int sum = 0;
        	if (val1 + carryOver >= 10) {
        		hasCarryOver = true;
        		sum = val1 + carryOver - 10;
        	} else {
        		hasCarryOver = false;
        		sum = val1 + carryOver;
        	}
        	currentResult = new ListNode(sum);
        	prevResult.next = currentResult;
        	prevResult = currentResult;
        	a1 = a1.next;
        }
        
        while (a2 != null) {
        	int val2 = a2.val;
        	int carryOver = hasCarryOver ? 1 : 0;
        	int sum = 0;
        	if (val2 + carryOver >= 10) {
        		hasCarryOver = true;
        		sum = val2 + carryOver - 10;
        	} else {
        		hasCarryOver = false;
        		sum = val2 + carryOver;
        	}
        	currentResult = new ListNode(sum);
        	prevResult.next = currentResult;
        	prevResult = currentResult;
        	a2 = a2.next;
        }
        
        if (hasCarryOver) {
        	currentResult = new ListNode(1);
        	prevResult.next = currentResult;
        }
        
        return resultHead;
    }
}
