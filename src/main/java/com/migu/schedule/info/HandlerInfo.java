package com.migu.schedule.info;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhoupengwei
 * @time:2018/6/20-下午6:36
 * @Email: 496946423@qq.com
 * @desc:
 */
public class HandlerInfo {
    /**
     * 任务链
     */
    List<TaskBean> taskList = new ArrayList<>();
    /**
     * 总消耗
     */
    int totalConsumption = 0;

    public List<TaskBean> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskBean> taskList) {
        this.taskList = taskList;
    }

    public int getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(int totalConsumption) {
        this.totalConsumption = totalConsumption;
    }
}
