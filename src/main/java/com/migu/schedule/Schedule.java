package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.HandlerInfo;
import com.migu.schedule.info.TaskBean;
import com.migu.schedule.info.TaskInfo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 *类名和方法不能修改
 */
public class Schedule {
    /**
     * 服务器调度map
     */
    Map<Integer, HandlerInfo> serverMap = new TreeMap<>();
    Map<Integer,TaskBean> hangTask = new TreeMap<>();
    Map<Integer,TaskBean> doneTask = new TreeMap<>();


    public int init() {
        serverMap.clear();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        } else if (serverMap.containsKey(nodeId)) {
            return ReturnCodeKeys.E005;
        }

        HandlerInfo info = new HandlerInfo();
        serverMap.put(nodeId, info);
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        } else if (!serverMap.containsKey(nodeId)) {
            return ReturnCodeKeys.E007;
        }

        HandlerInfo info = serverMap.remove(nodeId);
        for(TaskBean task : info.getTaskList()){
            hangTask.put(task.getTaskId(),task);

        }
        return ReturnCodeKeys.E006;
    }


    public int addTask(int taskId, int consumption) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        } else if (hangTask.containsKey(taskId)) {
            return ReturnCodeKeys.E010;
        } else if (doneTask.containsKey(taskId)) {
            return ReturnCodeKeys.E010;
        }

        TaskBean task= new TaskBean(taskId,0,consumption);
        hangTask.put(taskId,task);
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        } else if (!hangTask.containsKey(taskId) && !doneTask.containsKey(taskId)) {
            return ReturnCodeKeys.E012;
        }
        if(hangTask.containsKey(taskId)){
            hangTask.remove(taskId);
        }else if(doneTask.containsKey(taskId)){
            TaskBean info = doneTask.remove(taskId);

            int nodeId = info.getNodeId();
            HandlerInfo hanlder= serverMap.get(nodeId);
            for(TaskBean task:hanlder.getTaskList()){
                if(task.getTaskId()==taskId){
                    hanlder.getTaskList().remove(task);
                    break;
                }
            }
        }
        return ReturnCodeKeys.E011;
    }


    public int scheduleTask(int threshold) {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }

}
