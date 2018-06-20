import com.migu.schedule.Schedule;
import com.migu.schedule.info.TaskInfo;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        Schedule schedule = new Schedule();
        System.out.println("init " + schedule.init());
        System.out.println("registerNode " + schedule.registerNode(5));
        System.out.println("registerNode " + schedule.registerNode(4));
        System.out.println("registerNode " + schedule.registerNode(6));
        System.out.println("registerNode " + schedule.registerNode(3));
        System.out.println("registerNode " + schedule.registerNode(-1));
        System.out.println("registerNode " + schedule.registerNode(4));

        System.out.println("unregisterNode " + schedule.unregisterNode(6));
        System.out.println("unregisterNode " + schedule.unregisterNode(-1));
        System.out.println("unregisterNode " + schedule.unregisterNode(1));

        System.out.println("addTask " + schedule.addTask(1,4));
        System.out.println("addTask " + schedule.addTask(2,6));
        System.out.println("addTask " + schedule.addTask(4,3));
        System.out.println("addTask " + schedule.addTask(3,7));
        System.out.println("addTask " + schedule.addTask(-5,4));
        System.out.println("addTask " + schedule.addTask(1,1));

        System.out.println("scheduleTask " + schedule.scheduleTask(1));

        System.out.println("addTask " + schedule.addTask(4,3));
        System.out.println("addTask " + schedule.addTask(7,3));
        System.out.println("addTask " + schedule.addTask(8,4));
        System.out.println("addTask " + schedule.addTask(9,5));


        System.out.println("scheduleTask " + schedule.scheduleTask(1));

        System.out.println("addTask " + schedule.addTask(10,5));

        List<TaskInfo> a = new ArrayList();
        TaskInfo info = new TaskInfo();
        info.setTaskId(4);
        a.add(info);

        info = new TaskInfo();
        info.setTaskId(10);
        a.add(info);

        info = new TaskInfo();
        info.setTaskId(7);
        a.add(info);

        info = new TaskInfo();
        info.setTaskId(8);
        a.add(info);

        System.out.println("scheduleTask " + schedule.queryTaskStatus(a));
    }
}
