package com.oxf1.spider.pipline;

import com.oxf1.spider.TaskConfig;
import com.oxf1.spider.component.MyspiderComponent;
import com.oxf1.spider.data.DataItem;

/**
 * Created by cxu on 2014/11/21.
 */
public abstract class Pipline extends MyspiderComponent{

    public Pipline(TaskConfig taskConfig){
        super(taskConfig);
    }

    /**
     * 保存解析之后的数据
     * @param dataItem 要保存的数据
     */
    public abstract void save(DataItem dataItem);

    public abstract void close();
}
