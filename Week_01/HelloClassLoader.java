import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try{
            Class<?> clazz = new HelloClassLoader().findClass("Hello");
            Method method = clazz.getMethod("hello");
            Object obj = method.invoke(clazz.newInstance());
//            System.out.println(obj);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

   @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        byte[] bytes = null;
        File file = new File("C:\\Users\\yuyibing\\IdeaProjects\\test-demo\\src\\main\\java\\com\\example\\demo\\classloader\\Hello.xlass");
        // 读取文件到byte[]
        try(
              FileInputStream fis = new FileInputStream(file);
        ){
            int length = (int)file.length();
            bytes = new byte[length];
            fis.read(bytes);
        }catch(Exception e){
            e.printStackTrace();
        };

        // 字节转换x=255-x
        for(int i = 0; i < bytes.length; i++){
            bytes[i] = (byte)(255 - bytes[i]);
        }

        return defineClass(name, bytes, 0, bytes.length);
    }
}