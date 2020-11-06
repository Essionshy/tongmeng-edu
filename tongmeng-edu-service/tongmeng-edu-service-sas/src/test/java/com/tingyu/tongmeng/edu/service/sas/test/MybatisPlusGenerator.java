package com.tingyu.tongmeng.edu.service.sas.test;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Author essionshy
 * @Create 2020/10/25 17:31
 * @Version qianlong-edu
 */
public class MybatisPlusGenerator {

    private static final String homePath = "D:\\Development\\WorkSpace\\IdeaProjects\\tongmeng-edu\\tongmeng-edu-service\\tongmeng-edu-service-sas\\src\\main\\java";

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();


        //配置GlobalConfig
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(homePath);//获取生成文件路径
        globalConfig.setAuthor("1218817610@qq.com");//设置生成java文件作者信息
        globalConfig.setServiceName("%sService");//缺省时，默认生成IxxxService
        globalConfig.setOpen(false);
        globalConfig.setIdType(IdType.ID_WORKER_STR);//ID主键策略
        globalConfig.setDateType(DateType.ONLY_DATE);//定义生成实体类中日期类型为Date还是DateTime
        globalConfig.setSwagger2(true);//开启swagger2模式


        mpg.setGlobalConfig(globalConfig);
        //配置DataSourceConfig
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://192.168.0.105:3306/tongmeng_edu?serverTimezone=GMT&useUnicode=true&useSSL=false&characterEncoding=utf8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root-xz");
        mpg.setDataSource(dataSourceConfig);


        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.tingyu.tongmeng.edu.service");
        pc.setModuleName("sas");
        pc.setEntity("entity");
        pc.setMapper("dao");
        pc.setController("controller");
        pc.setService("service");
        pc.setXml("mapper");

        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        // strategy.setSuperEntityColumns("id");
        //scanner("表名，多个英文逗号分割").split(",")

        //“edu_chapter”，“”，“”，“”，“”，“”

        strategy.setInclude("sys_statistics_daily");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("sys_");//生成实体时，去掉表前缀
        mpg.setStrategy(strategy);
        //  mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        //mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();

    }

}
