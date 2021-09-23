package edu.natu.systemuser;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class MyCodeGeneration {
    public static void main(String[] args) {
        // 设置模块名称，以及要映射生成的表名(*代表所有,多表用;隔开)，作者名称
        String moduleName = setModuleName();
        String tableName = setTableName();
        String author = setAuthor();

        AutoGenerator autoGenerator = new AutoGenerator();
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String oPath = System.getProperty("user.dir");//得到当前项目的路径
        gc.setOutputDir(oPath + "/src/main/java/");   //生成文件输出根目录
        gc.setOpen(false);//生成完成后不弹出文件框
        gc.setFileOverride(false);  //文件覆盖
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor(author);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setServiceName("%sDao");
        gc.setServiceImplName("%sDaoImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        autoGenerator.setGlobalConfig(gc);

        TemplateConfig templateConfig = new TemplateConfig();
        autoGenerator.setTemplate(templateConfig);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);   //设置数据库类型，我是mysql
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://112.74.163.100:3306/systemuser");  //指定数据库
        autoGenerator.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityLombokModel(true);
        strategy.setTablePrefix("t_");
        strategy.setNaming(NamingStrategy.underline_to_camel);      // 表名生成策略
        if (!Objects.equals("*", tableName)) {
            strategy.setInclude(tableName.split(";"));
        }
        autoGenerator.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent("edu.natu.systemuser.business");
        pc.setService("dao");
        pc.setServiceImpl("dao");
        pc.setMapper("mapper");
        pc.setEntity("model");
        pc.setXml("mapper");
        pc.setController("controller");
        autoGenerator.setPackageInfo(pc);
        // 执行生成
        autoGenerator.execute();
    }

    private static String setModuleName() {
        String moduleName = "";
        System.out.println(("请输入模块名称（必须由小写字母和下划线组成）："));
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            moduleName = scanner.next();
            if (!moduleName.matches("[a-z_]*")) {
                System.out.println(("模块名称不符合要求：（必须由小写字母和下划线组成）！"));
                System.exit(-1);
            }
        }
        if (ifModuleExist(moduleName)) {
            System.out.println(("模块名称已存在，请不要否重复创建模块"));
            System.exit(-1);
        }
        return moduleName;
    }

    private static String setTableName() {
        String tableName = "";
        System.out.println(("请输入表的名称："));
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            tableName = scanner.next();
            if (!tableName.matches("^[a-zA-Z_*,;]{1,64}$")) {
                System.out.println(("表的名称名称不符合以下要求：^[a-zA-Z_]{1,64}$\"）！"));
                System.exit(-1);
            }
        }
        return tableName;
    }

    private static String setAuthor() {
        String author = "";
        System.out.println(("请输入作者名称（您的大名）："));
        Scanner scanner2 = new Scanner(System.in);
        if (scanner2.hasNext()) {
            author = scanner2.next();
            if (!author.matches("^[a-zA-Z0-9_\\u4e00-\\u9fa5]{1,64}$")) {
                System.out.println(("作者名称不符合以下要求：（^[a-zA-Z0-9_\\u4e00-\\u9fa5]{1,64}$\"）！"));
                System.exit(-1);
            }
        }
        return author;
    }

    private static boolean ifModuleExist(String moduleName) {
        boolean ifExist = false;
        File[] files = new File(System.getProperty("user.dir") + "/src/main/java/edu/natu/systemuser/business").listFiles();
        for (File e : files) {
            if(e.isDirectory() &&  moduleName.equals(e.getName())) {
                ifExist = true;
                break;
            }
        }
        return ifExist;
    }
}
