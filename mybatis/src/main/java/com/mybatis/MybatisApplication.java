package com.mybatis;

import com.mybatis.interepter.MBinterepter;
import com.mybatis.mapper.UserMapper;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sun.misc.ProxyGenerator;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Properties;
@EnableTransactionManagement
@MapperScan("com.mybatis.mapper")
@SpringBootApplication
public class MybatisApplication implements CommandLineRunner {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Autowired
    ApplicationContext applicationContext;

    String path = MybatisApplication.class.getResource(".").getPath();

    public static void main(String[] args)  throws Exception{


        Field[] fields = ProxyGenerator.class.getDeclaredFields();
        for (Field f : fields){
            if ( f.getName().equals("saveGeneratedFiles")) {
                f.setAccessible(true);
                Field modifiers = Field.class.getDeclaredField("modifiers");
                modifiers.setAccessible(true);
                modifiers.setInt(f,  f.getModifiers() & ~Modifier.FINAL);
                f.setBoolean(null,new Boolean(true));
            }
        }
        String path = MybatisApplication.class.getResource("/").getPath() + "proxyA/";
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, path);
        SpringApplication.run(MybatisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Properties properties = new Properties();
        properties.setProperty("nb", "mybaties");
        MBinterepter interceptor = new MBinterepter();
        interceptor.setProperties(properties);
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }

        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        ClassReader cr = new ClassReader(UserMapper.class.getName());
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        cr.accept(cw, Opcodes.ASM5);
        byte[] code = cw.toByteArray();
        FileOutputStream fos = new FileOutputStream(path + userMapper.getClass().getName() + ".class");
        fos.write(code);
        fos.close();

        Object object = Proxy.newProxyInstance(UserMapper.class.getClassLoader(),UserMapper.class.getInterfaces(), new MapperProxy<UserMapper> (null,null,null));
        saveClassFile(object.getClass(), object.getClass().getName());

    }

    private  void saveClassFile(Class clazz,String proxyName) {
        //生成class的字节数组，此处生成的class与proxy.newProxyInstance中生成的class除了代理类的名字不同，其它内容完全一致
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        //URL url = ProxyApplication.class.getResource(".");


        try( FileOutputStream fos = new FileOutputStream(path + proxyName + ".class")) {
            fos.write(classFile);
            fos.flush();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
