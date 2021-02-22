package cn.tool.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

/**
 * 文件处理通用工具  对文件进行内容的获取
 */
public class FileUtil {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * 读取文件内容并返回，文件不存在时返回null；文件为空时，返回空字符串；否则返回文件内容，每行之间用System.getProperty("line.separator")分隔
     * @param file
     * @return
     * @throws IOException
     */
    public static String getFileContent(File file) throws IOException {
        if(!file.exists()) {
            return null;
        }
        StringBuilder fileContent = new StringBuilder();
        fileFunction(file, line -> {
            fileContent.append(line).append(LINE_SEPARATOR);
            return true;
        });
        return fileContent.toString();
    }

    /**
     * 处理文件的每一行，当function返回false时，不再继续处理文件
     * @param file
     * @param function
     * @throws IOException
     */
    public static void fileFunction(File file, Function<String, Boolean> function) throws IOException{
        try(FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Boolean result = function.apply(line);
                if(Objects.equals(result, false)) {
                    break;
                }
            }
        }
    }
}
