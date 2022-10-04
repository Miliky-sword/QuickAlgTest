package methodtesthandler;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodTestTask {
    /**
     * 输入重定向文件后缀
     */
    private static final String FILE_TAIL = "_in";

    /**
     * 被测试类所在包包名
     */
    private static final String PACKAGE_NAME = "tobetested.";

    private final String klassName;

    private final String funcName;

    private final String redirectInFileName;


    public MethodTestTask(String klassName) {
        this.klassName = klassName;
        this.funcName = "Main";
        this.redirectInFileName = this.klassName + FILE_TAIL;
    }

    public MethodTestTask(String klassName, String funcName) {
        this.klassName = klassName;
        this.funcName = funcName;
        this.redirectInFileName = klassName + "_in";
    }

    public MethodTestTask(String klassName, String funcName, String redirectInFileName) {
        this.klassName = klassName;
        this.funcName = funcName;
        this.redirectInFileName = redirectInFileName;
    }

    public void test() {
        try {
            // 获取指定类并创建实例
            Class<?> testedClass = Class.forName(PACKAGE_NAME + klassName);
            Object obj = testedClass.newInstance();

            // 获取方法及参数类型
            Method entryMethod = null;
            for (Method method : testedClass.getDeclaredMethods()) {
                if (method.getName().equals(funcName)) {
                    entryMethod = method;
                }
            }

            // 执行方法
            if (entryMethod == null) {
                return;
            }
            Object[] params = new Object[2];
            params[0] = new int[]{2, 7, 11, 15};
            params[1] = 9;
            int[] ret = (int[]) entryMethod.invoke(obj, params);
            System.out.println(Arrays.toString(ret));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
