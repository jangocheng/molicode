package com.shareyi.molicode.context;

import com.shareyi.molicode.common.vo.code.TableModelVo;
import com.shareyi.molicode.common.vo.page.TableModelPageVo;

import java.util.Map;

/**
 * 表模型上下文
 *
 * @author zhangshibin
 * @date 2018/10/7
 */
public class TableModelContext {

    /**
     * 页面入参
     */
    TableModelPageVo tableModelPageVo;
    /**
     * project 关联的配置文件map
     */
    private Map<String, Map<String, String>> projectConfigMap;
    /**
     * 表模型VO
     */
    private TableModelVo tableModelVo;

    /**
     * 输出目录
     */
    private String outputPath;

    public TableModelPageVo getTableModelPageVo() {
        return tableModelPageVo;
    }

    public void setTableModelPageVo(TableModelPageVo tableModelPageVo) {
        this.tableModelPageVo = tableModelPageVo;
    }

    public void setProjectConfigMap(Map<String, Map<String, String>> projectConfigMap) {
        this.projectConfigMap = projectConfigMap;
    }

    public Map<String, Map<String, String>> getProjectConfigMap() {
        return projectConfigMap;
    }

    public TableModelVo getTableModelVo() {
        return tableModelVo;
    }

    public void setTableModelVo(TableModelVo tableModelVo) {
        this.tableModelVo = tableModelVo;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * 创建tableModel 上下文
     * @param pageVo
     * @return
     */
    public static TableModelContext create(TableModelPageVo pageVo) {
        TableModelContext context = new TableModelContext();
        context.tableModelPageVo = pageVo;
        return context;
    }
}
