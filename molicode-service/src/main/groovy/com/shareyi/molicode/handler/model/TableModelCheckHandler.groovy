package com.shareyi.molicode.handler.model;

import com.shareyi.molicode.common.chain.handler.SimpleHandler;
import com.shareyi.molicode.common.chain.handler.awares.TableModelHandlerAware;
import com.shareyi.molicode.common.constants.ConfigKeyConstant;
import com.shareyi.molicode.common.enums.DataTypeEnum;
import com.shareyi.molicode.common.utils.ValidateUtils;
import com.shareyi.molicode.common.valid.Validate;
import com.shareyi.molicode.common.vo.page.TableModelPageVo;
import com.shareyi.molicode.context.TableModelContext;
import com.shareyi.molicode.service.conf.AcConfigService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * tableModel生成的前置参数验证器
 * 及配置文件读取
 * @author zhangshibin
 * @since 2018/10/7
 */
@Service
public class TableModelCheckHandler extends SimpleHandler<TableModelContext> implements TableModelHandlerAware{

    @Resource
    AcConfigService acConfigService;

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public boolean shouldHandle(TableModelContext tableModelContext) {
        return true;
    }

    @Override
    public void doHandle(TableModelContext tableModelContext) {
        TableModelPageVo tableModelPageVo = tableModelContext.getTableModelPageVo();
        Validate.notNull(tableModelPageVo, "tableModelPageVo 不能为空");
        ValidateUtils.notEmptyField(tableModelPageVo, "projectKey");
        Map<String,Map<String,String>> configMap = acConfigService.getConfigMapByProjectKey(tableModelPageVo.getProjectKey(), DataTypeEnum.JSON);
        Map<String,String> databaseConfigMap = configMap.get(ConfigKeyConstant.DatabaseConfig.CONFIG_KEY);
        String driverName = MapUtils.getString(databaseConfigMap, ConfigKeyConstant.DatabaseConfig.DRIVER_CLASS);
        String url = MapUtils.getString(databaseConfigMap, ConfigKeyConstant.DatabaseConfig.URL);
        String username = MapUtils.getString(databaseConfigMap, ConfigKeyConstant.DatabaseConfig.USERNAME);
        String password = MapUtils.getString(databaseConfigMap, ConfigKeyConstant.DatabaseConfig.PASSWORD);


        Validate.notEmpty(driverName, "driverName 不能为空");
        Validate.notEmpty(url, "url 不能为空");
        Validate.notEmpty(username, "username 不能为空");
        Validate.notEmpty(password, "password 不能为空");

        ValidateUtils.notEmptyField(tableModelPageVo, ConfigKeyConstant.PathConfig.TABLE_MODEL_DIR);
        ValidateUtils.notEmptyField(tableModelPageVo, "tableName");

        //将查询到的数据库信息设置到context
        tableModelContext.setProjectConfigMap(configMap);
    }
}
