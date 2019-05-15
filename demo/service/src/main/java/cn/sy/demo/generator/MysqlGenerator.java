package cn.sy.demo.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * mysql 代码生成器
 * @author guests
 */
public class MysqlGenerator {

    /**
     * 项目名称
     */
    private static String productName = "demo";

    /**
     * 表名
     */
    private static String tableName = "jwt_user";


    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/"+productName+"/service/src/main/java");
        gc.setAuthor("guests");
        gc.setOpen(false);
        //service命名方式，默认IService
        gc.setServiceName("%sService");
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setFileOverride(true);
        //主键id类型
        gc.setIdType(IdType.AUTO);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://47.254.81.173:3306/tarot?useUnicode=true&useSSL=false&characterEncoding=utf8");
//        dsc.setDriverName("com.mysql.jdbc.Driver");
//        dsc.setUsername("tarot");
//        dsc.setPassword("gwOLtfC0t&WN0oZU");

        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/sy_demo?characterEncoding=utf8&useSSL=false&serverTimezone=GMT");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Jifen004");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setParent("cn.sy."+productName);

        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setEntity("model");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 这里修改xml配置文件位置
                return projectPath + "/"+productName+"/service/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //是否为lombok模型
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //表名
        strategy.setInclude(tableName);
        strategy.setEntityTableFieldAnnotationEnable(true);
        //自定义基础的Entity类-即公共基础model，公共字段
//        strategy.setSuperEntityColumns("id");
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //移出数据库中is_xxx,在映射实体的时候则会去掉is
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        //乐观锁字段名称
        strategy.setVersionFieldName("version");

        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
