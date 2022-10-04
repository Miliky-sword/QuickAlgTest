package common.redirection;

import java.io.File;
import java.util.*;

/**
 * 输入重定向文件解析类
 * 从输入重定向文件中读取字符串，按照case name存储
 *
 * @author parallel_wj
 */
public class ContentParser {

    private Map<String, List<String>> despGroup;

    private Map<String, List<String>> retGroup;

    private String currentCaseName;

    private boolean isParams;

    public ContentParser() {
        this.despGroup = new LinkedHashMap<>();
        this.retGroup = new LinkedHashMap<>();
        this.currentCaseName = null;
        this.isParams = false;
    }

    public static void main(String[] args) {
        ContentParser contentParser = new ContentParser();
        contentParser.loadLines("LeetCode1_in");
        for (Map.Entry<String, List<String>> stringListEntry : contentParser.despGroup.entrySet()) {
            System.out.println("----");
            System.out.println("case  param   " + stringListEntry.getKey());
            for (String s : stringListEntry.getValue()) {
                System.out.println(s);
            }
        }

        for (Map.Entry<String, List<String>> stringListEntry : contentParser.retGroup.entrySet()) {
            System.out.println("****");
            System.out.println("case  ret   " + stringListEntry.getKey());
            for (String s : stringListEntry.getValue()) {
                System.out.println(s);
            }
            System.out.println("----");
        }
    }

    public void loadLines(String fileName) {
        String filePath = getFilePath(fileName);
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                if (line.startsWith("/***") && line.endsWith("***/")) {
                    cmdLineRoute(line);
                    continue;
                }
                if (isParams) {
                    despGroup.get(currentCaseName).add(line);
                    continue;
                }
                retGroup.get(currentCaseName).add(line);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void cmdLineRoute(String line) {
        if (line.contains("case")) {
            String[] strings = line.split(" ");
            if (strings.length < 3) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 2; i < strings.length - 1; i++) {
                stringBuffer.append(strings[i]);
                isParams = true;
            }
            currentCaseName = stringBuffer.toString();
            despGroup.put(currentCaseName, new ArrayList<>());
        }
        if (line.contains("ret")) {
            retGroup.put(currentCaseName, new ArrayList<>());
            isParams = false;
        }
    }

    private String getFilePath(String fileName) {
        String projectRoot = System.getProperty("user.dir");
        return projectRoot + "/src/main/java/input/" + fileName;
    }
}
