class Markdown {

    String parse(String markdown) {
        String[] lines = markdown.split("\n");
        StringBuilder sBuilder = new StringBuilder();
        boolean activeList = false;

        for (String line: lines) {

            String theLine = parseHeader(line);

            if (theLine == null) {
                theLine = parseListItem(line);
            }
            if (theLine == null) {
                theLine = parseParagraph(line);
            }
            
            if (!activeList && theLine.matches("(<li>).*") 
                && !theLine.matches("(<h).*") && !theLine.matches("(<p>).*")) {
                activeList = true;
                sBuilder.append("<ul>");
                sBuilder.append(theLine);
            } 
            else if (activeList && !theLine.matches("(<li>).*")) {
                activeList = false;
                sBuilder.append("</ul>");
                sBuilder.append(theLine);
            } 
            else {
                sBuilder.append(theLine);
            }
        }

        if (activeList) {
            sBuilder.append("</ul>");
        }

        return sBuilder.toString();
    }

    private String parseHeader(String markdown) {
        int count = 0;
        int n = markdown.length();
        while (count < n && markdown.charAt(count) == '#') {
            count++;
        }        
        if (count > 6) { return parseParagraph(markdown); }
        if (count == 0) { return null; }
        String label = "h" + Integer.toString(count); 
        return  "<" + label + ">" + markdown.substring(count + 1) + "</" + label + ">";
    }

    private String parseListItem(String markdown) {
        if (markdown.startsWith("*")) {
            String skipAsterisk = markdown.substring(2);
            String listItemString = parseSomeSymbols(skipAsterisk);
            return "<li>" + listItemString + "</li>";
        }
        return null;
    }

    private String parseParagraph(String markdown) {
        return "<p>" + parseSomeSymbols(markdown) + "</p>";
    }

    private String parseSomeSymbols(String markdown) {

        String lookingFor = "__(.+)__";
        String update = "<strong>$1</strong>";
        String workingOn = markdown.replaceAll(lookingFor, update);

        lookingFor = "_(.+)_";
        update = "<em>$1</em>";
        return workingOn.replaceAll(lookingFor, update);
    }
}
