import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {

    public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tagQueue) {

        Stack<HtmlTag> tagStack = new Stack<>();

        while (tagQueue.size() > 0) {
            if (tagQueue.peek().isSelfClosing()) {
                tagQueue.remove();
                continue;
            }
            if (tagQueue.peek() != null && tagQueue.peek().isOpenTag()) {
                tagStack.push(tagQueue.remove());
                continue;
            }
            if (tagStack.size() == 0) {
                return null;
            }
            if (!tagStack.peek().matches(tagQueue.peek())) {
                break;
            }
            if (tagStack.peek().matches(tagQueue.peek())) {
                tagQueue.remove();
                tagStack.pop();
            }
        }

        return tagStack;
    }
}

