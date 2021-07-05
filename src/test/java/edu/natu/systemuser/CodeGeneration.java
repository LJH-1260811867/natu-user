package edu.natu.systemuser;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

public class CodeGeneration {
    @Test
    public void CodeGenerator() {
        AutoGenerator autoGenerator = new AutoGenerator();
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String oPath = System.getProperty("user.dir");//得到当前项目的路径
        gc.setOutputDir(oPath + "/src/main/java");   //生成文件输出根目录
        gc.setOpen(false);//生成完成后不弹出文件框
        gc.setFileOverride(false);  //文件覆盖
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("ljh");// 作者

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setServiceName("%sDao");
        gc.setServiceImplName("%sDaoImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        autoGenerator.setGlobalConfig(gc);

        TemplateConfig templateConfig = new TemplateConfig();

        //控制 不生成 controller
        templateConfig.setController(null);
        autoGenerator.setTemplate(templateConfig);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);   //设置数据库类型，我是mysql
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("systemuserdb_123");
        dsc.setUrl("jdbc:mysql://112.74.163.100:3306/systemuserdb");  //指定数据库
        autoGenerator.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);      // 表名生成策略
//        strategy.setInclude("*");
        autoGenerator.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("edu.natu.systemuser");
        pc.setService("dao");
        pc.setServiceImpl("dao.impl");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setXml("mapper.xml");
        autoGenerator.setPackageInfo(pc);
        // 执行生成
        autoGenerator.execute();
    }
}
