import java.util.*;

public class SgfParsing {
    private String input;
    private int pos;

    public SgfNode parse(String input) throws SgfParsingException {
        if (input == null || input.isEmpty()) {
            throw new SgfParsingException("tree missing");
        }
        this.input = input;
        this.pos = 0;

        if (peek() != '(') {
            throw new SgfParsingException("tree missing");
        }
        return parseTree();
    }

    private SgfNode parseTree() throws SgfParsingException {
        expect('(');
        if (peek() == ')') {
            throw new SgfParsingException("tree with no nodes");
        }
        SgfNode root = parseNode();
        expect(')');
        return root;
    }

    private SgfNode parseNode() throws SgfParsingException {
        expect(';'); // every node starts with ;

        Map<String, List<String>> properties = new HashMap<>();
        while (peek() != '(' && peek() != ')' && peek() != ';') {
            properties.putAll(parseProperty());
        }

        SgfNode head = new SgfNode(properties);
        SgfNode current = head;

        // Handle siblings (; means another node in the same sequence)
        while (peek() == ';') {
            // pos++; // consume ';'
            SgfNode child = parseNode();
            current.appendChild(child);
            current = child; // move down branch
        }

        // Handle variations (subtrees in parentheses)
        while (peek() == '(') {
            SgfNode variation = parseTree();
            current.appendChild(variation);
        }

        return head;
    }

    private Map<String, List<String>> parseProperty() throws SgfParsingException {
        String key = parsePropIdent();
        if (peek() != '[') {
            throw new SgfParsingException("properties without delimiter");
        }

        List<String> values = new ArrayList<>();
        while (peek() == '[') {
            values.add(parsePropValue());
        }
        return Map.of(key, values);
    }

    private String parsePropIdent() throws SgfParsingException {
        int start = pos;
        while (Character.isUpperCase(peek())) {
            pos++;
        }
        if (pos == start) {
            throw new SgfParsingException("property must be in uppercase");
        }
        return input.substring(start, pos);
    }

    private String parsePropValue() throws SgfParsingException {
        expect('[');
        StringBuilder sb = new StringBuilder();
        while (peek() != ']') {
            char c = next();
            if (c == '\\') {
                if (pos >= input.length()) {
                    throw new SgfParsingException("unexpected end of input");
                }
                char next = next();
                if (next == '\n') {
                    // line continuation: skip both
                } else if (next == '\t' || next == '\r' || next == ' ') {
                    sb.append(' ');
                } else {
                    sb.append(next);
                }
            } else if (c == '\t' || c == '\r') {
                sb.append(' ');
            } else {
                sb.append(c);
            }
        }
        expect(']');
        return sb.toString();
    }

    // --- helpers ---

    private char peek() throws SgfParsingException {
        if (pos >= input.length()) {
            throw new SgfParsingException("unexpected end of input");
        }
        return input.charAt(pos);
    }

    private char next() throws SgfParsingException {
        if (pos >= input.length()) {
            throw new SgfParsingException("unexpected end of input");
        }
        return input.charAt(pos++);
    }

    private void expect(char c) throws SgfParsingException {
        char p = peek();
        if (p != c) {
            throw new SgfParsingException(
                "expected '" + c + "' at pos " + pos 
                + " but current value is '" + p + "'.");
        }
        pos++;
    }
}
