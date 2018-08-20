package com.tme.Util;

import com.tme.Bean.FileBean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileHelper {

    public List<FileBean> getRootFile(){
        String path="F:\\lijiangnan";

         List<FileBean> list = new ArrayList<>();
        File file = new File(path);
        // get the folder list
        File[] files = file.listFiles();
        for(int i =0;i<files.length;i++){

                String name = files[i].getName();
                String url = files[i].getAbsolutePath();
                FileBean fb = new FileBean();
                fb.setType(0);
                fb.setFileUrl(url);
                fb.setFileName(name);
                list.add(fb);

        }
        System.out.println(list);
        return list;
    }

    public List<FileBean> getFiles(){
        String path="F:\\lijiangnan";
        List<FileBean> list = new ArrayList<>();
        File file = new File(path);
        // get the folder list
        File[] files = file.listFiles();
        for(int i =0;i<files.length;i++){
            if(files[i].isDirectory()){
                String name = files[i].getName();   //获取文件的名字
                //System.out.println(files[i].getParent());
                String url = files[i].getAbsolutePath();  //获取绝对路径
                long size = files[i].length();            //获取文件的大小
                System.out.println("文件夹");
                FileBean fb = new FileBean();
                fb.setSuffix("文件夹");
                fb.setSize(size);
                fb.setType(0);                             //设置文件的类型
                fb.setFileUrl(url);
                fb.setFileName(name);
                list.add(fb);
            }else{
                String name = files[i].getName();   //获取文件的名字
                String url = files[i].getAbsolutePath();  //获取绝对路径
                long size = files[i].length();            //获取文件的大小
                String suffix = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1);
                System.out.println("文件的后缀"+suffix);
                FileBean fb = new FileBean();
                fb.setSuffix(suffix);
                fb.setType(0);                             //设置文件的类型
                fb.setFileUrl(url);
                fb.setFileName(name);
                list.add(fb);

            }
        }
        return list;
    }
    //创建文件夹   传入一个路径
    public boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }
    //删除空目录
    private  void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    //删除文件夹
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();//递归删除不为空的目录
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    //查找所有的文本
    public List<File> getDocumentList(String strPath) {
        List<File> filelist = new ArrayList<>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getDocumentList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("doc") || fileName.endsWith("txt")) { // 判断文件名是否以.doc || txt结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                    filelist.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        return filelist;
    }

    //查找所有的视屏
    public List<File> getVideoList(String strPath) {
        List<File> filelist = new ArrayList<>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getVideoList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("mp4") || fileName.endsWith("avi") || fileName.endsWith("flv") || fileName.endsWith("wmv") || fileName.endsWith("mpg")) { // 判断文件名是否以.doc || txt结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                    filelist.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        return filelist;
    }
    public static void main(String [] args){
        String path="F:\\lijiangnan";
        FileHelper fileHelper = new FileHelper();
            //fileHelper.getRootFile();
            fileHelper.getFiles();
            // fileHelper.getDocumentList(path);
       // fileHelper.getVideoList(path);
        }
}
