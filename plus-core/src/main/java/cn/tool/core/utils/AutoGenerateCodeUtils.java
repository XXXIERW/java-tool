package cn.tool.core.utils;

/**
 * <p>
 * mybatis-plus里面生成代码生成service,dao,mapper等；
 * 根据实体类自动生成Dao和Service的模板代码
 * 需遵守相关约定，如实体类必须放在model包下等
 * </p>
 */
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class AutoGenerateCodeUtils {
    public AutoGenerateCodeUtils() {
    }

    public static void generate(String packagePath, Class<?> clazz, String... tableNames) throws Exception {
        String basePath = clazz.getResource("/").getPath();
        String projectPath = basePath.substring(0, basePath.indexOf("target/")) + "src/main/java/";
        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setTemplate((new TemplateConfig()).setXml((String)null).setController((String)null));
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath);
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);
        gc.setBaseResultMap(false);
        gc.setBaseColumnList(false);
        gc.setAuthor("RaveyXie");
        gc.setServiceName("%sDao");
        gc.setServiceImplName("%sDaoImpl");
        gc.setMapperName("%sMapper");
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("sulu");
        dsc.setPassword("sulutest");
        dsc.setUrl("jdbc:mysql://my57.c4op4uxykvfu.ap-southeast-1.rds.amazonaws.com:3306/sulu3");
        mpg.setDataSource(dsc);
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{"t_"});
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tableNames);
        strategy.setSuperEntityClass("cn.biboom.core.orm.mp.base.BaseEntity");
        strategy.setSuperEntityColumns(new String[]{"id"});
        strategy.setSuperServiceClass((String)null);
        strategy.setSuperServiceImplClass((String)null);
        strategy.setSuperMapperClass((String)null);
        mpg.setStrategy(strategy);
        PackageConfig pc = new PackageConfig();
        pc.setParent(packagePath);
        pc.setService("dao");
        pc.setServiceImpl("dao.impl");
        pc.setMapper("mapper");
        pc.setEntity("model");
        pc.setXml("xml");
        mpg.setPackageInfo(pc);
        mpg.execute();
    }

    public static void generate(String packagePath, String dbUserName, String dbPassword, String author, String dbUrl, Class<?> clazz, String... tableNames) throws Exception {
        String basePath = clazz.getResource("/").getPath();
        String projectPath = basePath.substring(0, basePath.indexOf("target/")) + "src/main/java/";
        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setTemplate((new TemplateConfig()).setController((String)null));
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath);
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);
        gc.setBaseResultMap(false);
        gc.setBaseColumnList(false);
        gc.setAuthor(author);
        gc.setServiceName("%sDao");
        gc.setServiceImplName("%sDaoImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(dbUserName);
        dsc.setPassword(dbPassword);
        dsc.setUrl(dbUrl);
        mpg.setDataSource(dsc);
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{"t_"});
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tableNames);
        strategy.setSuperEntityClass("cn.biboom.core.orm.mp.base.BaseEntity");
        strategy.setSuperEntityColumns(new String[]{"id"});
        strategy.setSuperServiceClass((String)null);
        strategy.setSuperServiceImplClass((String)null);
        strategy.setSuperMapperClass((String)null);
        mpg.setStrategy(strategy);
        PackageConfig pc = new PackageConfig();
        pc.setParent(packagePath);
        pc.setService("dao");
        pc.setServiceImpl("dao.impl");
        pc.setMapper("mapper");
        pc.setEntity("model");
        pc.setXml("xml");
        mpg.setPackageInfo(pc);
        mpg.execute();
    }
}
