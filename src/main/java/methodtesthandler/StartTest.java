package methodtesthandler;

public class StartTest {
    public static void main(String[] args) {
        new StartTest().startTask();
    }

    public void startTask() {
        MethodTestTask methodTestTask = new MethodTestTask("LeetCode1", "twoSum");
        methodTestTask.test();
    }
}
