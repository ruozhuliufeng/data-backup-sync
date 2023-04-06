package tech.msop.data.task;

import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

public class DataBackupTask {


    /**
     * 导出 sql 并返回相关信息
     */
//    @SneakyThrows
//    private String exportSql(String time) {
//        String path = new File((ResourceUtils.getURL("classpath:").getPath())).getAbsolutePath();
//        String filePath = path + File.separator + LedgerConstant.UPLOAD_PATH_PREFIX_STATIC + File.separator + LedgerConstant.UPLOAD_PATH_PREFIX_UPLOAD_FILE + File.separator;
//        String host = getHost();
//        String dataBaseName = getDataBaseName();
//        String fileName = time + "-" + dataBaseName + ".sql";
//        // 指定导出的 sql 存放的文件夹
//        File saveFile = new File(filePath);
//        if (!saveFile.exists()) {
//            saveFile.mkdirs();
//        }
//        StringBuilder sb = new StringBuilder();
//        // 拼接备份命令
//        sb.append("mysqldump").append(" --opt")
//                .append(" -h ").append(host).append(" --user=")
//                .append(properties.getUsername())
//                .append(" --password=")
//                .append(properties.getPassword())
//                .append(" --result-file=")
//                .append(filePath + fileName)
//                .append(" --default-character-set=utf8mb4 ")
//                .append(dataBaseName);
//        try {
//            Process exec = Runtime.getRuntime().exec(sb.toString());
//            if (exec.waitFor() == 0) {
//                log.info("数据库备份成功，保存路径：" + filePath);
//            } else {
//                log.info("process.waitFor:{}", exec.waitFor());
//                return null;
//            }
//        } catch (IOException e) {
//            log.error("备份 数据库 出现 IO异常 ", e);
//            return null;
//        } catch (InterruptedException e) {
//            log.error("备份 数据库 出现 线程中断异常 ", e);
//            return null;
//        } catch (Exception e) {
//            log.error("备份 数据库 出现 其他异常 ", e);
//            return null;
//        }
//        return filePath + fileName;
//    }
}
