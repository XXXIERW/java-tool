package cn.tool.core.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * <p>
 * mybatis-plus里面生成代码生成service,dao,mapper等；
 * 根据实体类自动生成Dao和Service的模板代码
 * 需遵守相关约定，如实体类必须放在model包下等
 * </p>
 */
public class AutoGenerateCodeUtils {

    /**
     * 进行自动生成 mybatis结构 mapper,service,model
     * @param packagePath 包路径，根据项目修改
     * @param clazz resource path
     */
    public static void generate(String packagePath, Class<?> clazz) throws Exception {
        generateDao(packagePath, clazz);
        generateService(packagePath, clazz);
    }

    /**
     *
     * @param packagePath 包路径，根据项目修改
     * @param clazz resource path
     */
    private static void generateDao(String packagePath, Class<?> clazz) throws Exception {

        // 包名
        String entityPack = packagePath + ".model";
        String daoPack = packagePath + ".dao";
        String daoImplPack = packagePath + ".dao.impl";

        // 文件路径
        String basePath = clazz.getResource("/").getPath();
        String projectPath = basePath.substring(0, basePath.indexOf("target/")) + "src/main/java/" + packagePath.replaceAll("\\.", "/");
        String entityPath = projectPath + "/model/";
        String daoPath = projectPath + "/dao/";
        String daoImplPath = projectPath + "/dao/impl/";

        File dir = new File(entityPath);
        File[] fs = dir.listFiles();
        if (fs == null || fs.length == 0) return;
        for (File f : fs) {
            if (!f.getName().endsWith(".java")) continue;
            String fname = f.getName();
            fname = fname.substring(0, fname.lastIndexOf("."));
            // Dao 是否存在
            File daoFile = new File(daoPath + fname + "Dao.java");
            if (daoFile.exists()) continue;
            // DaoImpl 是否存在
            File daoImplFile = new File(daoImplPath + fname + "DaoImpl.java");
            // 文件夹不存在则先创建
            if (!daoImplFile.getParentFile().exists()) {
                boolean daoSuccess = daoImplFile.getParentFile().mkdirs();
                if (!daoSuccess) System.out.println("创建Dao/Impl文件夹失败，请检查磁盘容量、权限等");
            }
            String claName = entityPack + "." + fname;
            Class<?> cla = Class.forName(claName);
            if (cla.getAnnotation(Entity.class) == null) continue;
            Field[] fields = cla.getSuperclass().getDeclaredFields();
            Field idField = null;
            for (Field fl : fields) {
                if (fl.getAnnotation(Id.class) != null) {
                    idField = fl;
                    break;
                }
            }
            Class<?> idtype = idField == null ? Long.class : idField.getType();

            PrintWriter pw = new PrintWriter(new FileWriter(daoFile));
            pw.println("package " + daoPack + ";\n");
            pw.println("/**");
            pw.println(" * 自动生成Dao接口类");
            pw.println(" */");
            pw.println("public interface " + fname + "Dao extends cn.taqu.core.orm.base.NimbleDao<" + idtype.getName() + ", " + cla.getName() + "> {");
            pw.println("\n}");
            pw.flush();
            pw.close();
            System.out.println("已生成" + cla.getName() + "Dao");

            pw = new PrintWriter(new FileWriter(daoImplFile));
            pw.println("package " + daoImplPack + ";\n");
            pw.println("import org.springframework.stereotype.Repository;\n");
            pw.println("/**");
            pw.println(" * 自动生成Dao接口实现类");
            pw.println(" */");
            pw.println("@Repository");
            pw.println("public class " + fname + "DaoImpl extends cn.taqu.core.orm.base.NimbleDaoImpl<" + idtype.getName() + ", " + cla.getName() + "> implements " + daoPack + "." + fname + "Dao {");
            pw.println("\n}");
            pw.flush();
            pw.close();
            System.out.println("已生成" + cla.getName() + "DaoImpl");

            System.out.println("===================================================================");
        }
    }

    /**
     *
     * @param packagePath 包路径，根据项目修改
     * @param clazz resource path
     */
    private static void generateService(String packagePath, Class<?> clazz) throws Exception {

        // 包名
        String entityPack = packagePath + ".model";
        String servicePack = packagePath + ".service";

        // 文件路径
        String basePath = clazz.getResource("/").getPath();
        String projectPath = basePath.substring(0, basePath.indexOf("target/")) + "src/main/java/" + packagePath.replaceAll("\\.", "/");
        String entityPath = projectPath + "/model/";
        String servicePath = projectPath + "/service/";

        File dir = new File(entityPath);
        File[] fs = dir.listFiles();
        if (fs == null || fs.length == 0) return;
        for (File f : fs) {
            if (!f.getName().endsWith(".java")) continue;
            String fname = f.getName();
            fname = fname.substring(0, fname.lastIndexOf("."));
            // Service 是否存在
            File serviceFile = new File(servicePath + fname + "Service.java");
            if (serviceFile.exists()) continue;
            // 文件夹不存在则先创建
            if (!serviceFile.getParentFile().exists()) {
                boolean serviceSuccess = serviceFile.getParentFile().mkdirs();
                if (!serviceSuccess) System.out.println("创建Service文件夹失败，请检查磁盘容量、权限等");
            }
            String claName = entityPack + "." + fname;
            Class<?> cla = Class.forName(claName);
            if (cla.getAnnotation(Entity.class) == null) continue;
            Field[] fields = cla.getSuperclass().getDeclaredFields();
            Field idField = null;
            for (Field fl : fields) {
                if (fl.getAnnotation(Id.class) != null) {
                    idField = fl;
                    break;
                }
            }
            Class<?> idtype = idField == null ? Long.class : idField.getType();

            PrintWriter pw = new PrintWriter(new FileWriter(serviceFile));
            pw.println("package " + servicePack + ";\n");
            pw.println("import org.springframework.stereotype.Service;");
            pw.println("import org.springframework.transaction.annotation.Transactional;\n");
            pw.println("/**");
            pw.println(" * 自动生成Service类");
            pw.println(" */");
            pw.println("@Service");
            pw.println("@Transactional");
            pw.println("public class " + fname + "Service extends cn.taqu.core.orm.base.BaseServiceImpl<" + idtype.getName() + ", " + cla.getName() + "> {");
            pw.println("\n}");
            pw.flush();
            pw.close();
            System.out.println("已生成" + cla.getName() + "Service");

            System.out.println("===================================================================");
        }
    }


}