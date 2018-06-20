package com.migu.schedule.info;

/**
 * @author: zhoupengwei
 * @time:2018/6/20-下午6:37
 * @Email: 496946423@qq.com
 * @desc:
 */
public class TaskBean {
    /**
     * 任务id
     */
    int taskId;

    /**
     * 服务id
     */
    int nodeId;
    /**
     * 消耗
     */
    int consumption;

    public TaskBean(int taskId, int nodeId, int consumption) {
        this.taskId = taskId;
        this.nodeId = nodeId;
        this.consumption = consumption;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }
}
