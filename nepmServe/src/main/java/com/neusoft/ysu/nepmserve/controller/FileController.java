package com.neusoft.ysu.nepmserve.controller;

import com.neusoft.ysu.nepmserve.util.MyInterceptorConfig;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王郝浠
 * @since 2023-09-04
 */
//@CrossOrigin
//@RestController
//@RequestMapping("/upload")
public class FileController {

//    @Value("${pictureFile.path}")
//    private String picturePath;

//    @Value("${pictureFile.path-mapping}")
//    private String picturePath_mapping;

//    String path=System.getProperty("user.dir")+"/src/main/resources/static/avatar/";
//    String relativePath=new File(path).getCanonicalPath();
//    public FileController() throws IOException {
//    }
    //获得了target/classes/static的相对路径
    String path=this.getClass().getClassLoader().getResource("static/avatar").getPath();
//    1.String path= ClassUtils.getDefaultClassLoader().getResource("").getPath();
//    String filePath=path+"static/avatar/";
//    2.String path= ResourceUtils.getURL("classpath:static").getPath();
//    public FileController() throws FileNotFoundException {
//    }

    //重命名重复的文件
//    public static boolean checkFileExistence(String fileName) {
//        try {
//            // 获取resources/static文件夹的路径
//            File staticFolder = ResourceUtils.getFile("classpath:static/avatar");
//            String staticFolderPath=staticFolder.getAbsolutePath();
//            System.out.println("60:"+staticFolderPath);
//            // 构建文件的完整路径
//            String filePath = staticFolderPath  + File.separator+fileName;
//            System.out.println("62:"+File.separator);
//            System.out.println("62:"+filePath);
//            // 使用Path对象来判断文件是否存在
//            Path path = Paths.get(filePath);
//            System.out.println("65:"+path);//65==62
//            if (Files.exists(path)) {
//                //生成新的文件名
//                String newFileName = fileName.substring(0, fileName.lastIndexOf(".")) + "old.jpg";
//                System.out.println("71:"+newFileName);
//
//                File file = path.toFile();
//                System.out.println("74:"+file);
//                File newFile = new File(staticFolder, newFileName);
//                if (file.renameTo(newFile)) {
//                    System.out.println("成功！ 70:"+newFile);
//                    return true;
//                } else {
//                    System.out.println("文件重命名失败");
//                }
//            } else {
//                System.out.println("文件不存在");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }

    //判断文件名是否已存在
    public static boolean checkFileExistence(String fileName) {
        try {
            // 获取resources/static文件夹的路径
            File staticFolder = ResourceUtils.getFile("classpath:static/avatar");
            String staticFolderPath=staticFolder.getAbsolutePath();
            System.out.println("99:"+staticFolderPath);
            // 构建文件的完整路径
            String filePath = staticFolderPath  + File.separator+fileName;
            System.out.println("102:"+File.separator);
            System.out.println("103:"+filePath);
            // 使用Path对象来判断文件是否存在
            Path path = Paths.get(filePath);
            return Files.exists(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //删除重复的文件
    public static void deleteExistingFile(String fileName) {
        try {
            // 获取resources/static文件夹的路径
            File staticFolder = ResourceUtils.getFile("classpath:static/avatar");
            String staticFolderPath=staticFolder.getAbsolutePath();
            System.out.println("120:"+staticFolderPath);
            // 构建文件的完整路径
            String filePath = staticFolderPath  + File.separator+fileName;
            System.out.println("123:"+File.separator);
            System.out.println("124:"+filePath);
            // 使用Path对象来判断文件是否存在
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                File file = path.toFile();
                if (file.delete()) {
                    System.out.println("文件删除成功");
                } else {
                    System.out.println("文件删除失败");
                }
            } else {
                System.out.println("文件不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/imgggg/{username}")
    public CommonResult uploadImg(MultipartFile file, @PathVariable("username")String username){
        String fileName = file.getOriginalFilename();  // 文件名
        System.out.println("文件名："+fileName);
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        System.out.println("后缀名："+suffixName);
        fileName = username + suffixName; // 新文件名
        System.out.println("新文件名："+fileName);
//        deleteExistingFile(fileName);
            System.out.println("相对路径："+ path);
            File dest = new File(path+'/' + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs(); //mkdirs（）创建此抽象路径指定的目录，包括所有必须但不存在的父目录。
            }
            try {
                //把内存图片写入磁盘中
                file.transferTo(dest);
            } catch (IOException e) {  //需求、设计：数据库、项目结构、某段代码、组件的实现-》演示
                //printStackTrace()将错误信息全部打印至控制台上展示
                e.printStackTrace();
            }
//        String finalName = "http://localhost:18081" + picturePath_mapping + fileName;
            String finalName="http://localhost:18081/avatar/"+fileName;
            System.out.println("最终："+finalName);
            return new CommonResult(finalName);


//        return CommonResult.failed("头像上传失败！");
    }
}
