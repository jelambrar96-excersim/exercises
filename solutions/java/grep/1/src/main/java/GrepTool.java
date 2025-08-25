import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class GrepTool {

    private boolean showLineNum;
    private boolean caseInsensitive;
    private boolean printFileNameOnly;
    private boolean matchEntireLine;
    private boolean inverted;
    private boolean multipleFilesMode;


    String grep(String pattern, List<String> flags, List<String> files) {
        this.processFlags(flags);
        if (files.size() > 1) { multipleFilesMode = true; }
        return files.stream().map(f -> {
            try {
                return grepOnFile(pattern, f);
            } catch (IOException e) {
                return null;
            }
        })
        .filter(s -> s != null && !s.isEmpty())
        .collect(Collectors.joining("\n"));
    }

    public void processFlags(List<String> flagList) {
        this.showLineNum = flagList.contains("-n");
        this.caseInsensitive = flagList.contains("-i");
        this.printFileNameOnly = flagList.contains("-l");
        this.matchEntireLine = flagList.contains("-x");
        this.inverted = flagList.contains("-v");
    }

    public String grepOnFile(String pattern, String filepath) throws IOException {
        int flag = Pattern.LITERAL | (caseInsensitive ? Pattern.CASE_INSENSITIVE : 0);
        Pattern ptn = Pattern.compile(pattern, flag);
        StringBuilder sbuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        long lineNum = 0;
        while(true) {
            String line = reader.readLine();
            lineNum++;
            if (line == null) { break; }
            if (!match(ptn, line)) { continue; }
            if (printFileNameOnly) {
                sbuilder.append(filepath);
                break;
            }
            if (sbuilder.length() > 0) {
                sbuilder.append("\n");
            }
            sbuilder.append(formattedLine(filepath, lineNum, line));
        }
        reader.close();
        return sbuilder.toString();
    }

    public String formattedLine(String file, long lineNum, String line) {
        StringBuilder sb = new StringBuilder();
        if (this.multipleFilesMode) {
            sb.append(file);
            sb.append(":");
        }
        if (this.showLineNum) {
            sb.append(Long.toString(lineNum));
            sb.append(":");
        }
        sb.append(line);
        return sb.toString();
    }

    public boolean match(Pattern pattern, String toMatch) {
        Matcher matcher = pattern.matcher(toMatch);
        boolean ret = matchEntireLine ? matcher.matches() : matcher.find();
        return ret ^ this.inverted; // this.inverted ? !ret : ret; //  invert the result if -v is set
    }
}