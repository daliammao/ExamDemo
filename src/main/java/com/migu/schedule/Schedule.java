package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.HandlerInfo;
import com.migu.schedule.info.TaskBean;
import com.migu.schedule.info.TaskInfo;

import java.util.*;

/*
 *类名和方法不能修改
 */
public class Schedule {
    /**
     * 服务器调度map
     */
    TreeMap<Integer, HandlerInfo> serverMap = new TreeMap<>();
    Map<Integer, TaskBean> hangTask = new HashMap<>();
    Map<Integer, TaskBean> doneTask = new HashMap<>();


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
        info.setNodeId(nodeId);
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
        for (TaskBean task : info.getTaskList()) {
            hangTask.put(task.getTaskId(), task);
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

        TaskBean task = new TaskBean(taskId, 0, consumption);
        hangTask.put(taskId, task);
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        } else if (!hangTask.containsKey(taskId) && !doneTask.containsKey(taskId)) {
            return ReturnCodeKeys.E012;
        }
        if (hangTask.containsKey(taskId)) {
            hangTask.remove(taskId);
        } else if (doneTask.containsKey(taskId)) {
            TaskBean info = doneTask.remove(taskId);

            int nodeId = info.getNodeId();
            HandlerInfo hanlder = serverMap.get(nodeId);
            for (TaskBean task : hanlder.getTaskList()) {
                if (task.getTaskId() == taskId) {
                    hanlder.getTaskList().remove(task);
                    break;
                }
            }
        }
        return ReturnCodeKeys.E011;
    }


    public int scheduleTask(int threshold) {
        if (threshold <= 0) {
            return ReturnCodeKeys.E002;
        }

        if(serverMap.size()==0){
            return ReturnCodeKeys.E014;
        }

        List<TaskBean> tasks = new ArrayList<>(hangTask.values());
        tasks.sort((o1, o2) -> o2.getConsumption() - o1.getConsumption());

        TreeMap<Integer, HandlerInfo> sortMap = new TreeMap<>();
        for(HandlerInfo handler :serverMap.values()){
            sortMap.put(handler.getTotalConsumption(),handler);
        }

        for (TaskBean task :tasks){
            int minK = sortMap.firstKey();
            HandlerInfo minV = sortMap.remove(minK);

            task.setNodeId(minV.getNodeId());
            minV.getTaskList().add(task);
            minK = minV.getTotalConsumption() + task.getConsumption();
            minV.setTotalConsumption(minK);

            sortMap.put(minK,minV);
            hangTask.remove(task.getTaskId());
            doneTask.put(task.getTaskId(),task);
        }

        serverMap.clear();
        for(HandlerInfo handler :sortMap.values()){
            serverMap.put(handler.getNodeId(),handler);
        }

        return ReturnCodeKeys.E013;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        if(tasks == null){
            return ReturnCodeKeys.E016;
        }

        return ReturnCodeKeys.E015;
    }

}
