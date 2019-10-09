package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MysqlUtil {

	public static void backup (String mysqlPath, String backupfile) throws IOException{
		String commandFormat = "\"%s/bin/mysqldump.exe\" -u%s -p%s    -hlocalhost   -P%d %s -r \"%s\"";
		
		String command = String.format(commandFormat, mysqlPath, DBUtil.getLoginName(), DBUtil.getPassword(), 
				DBUtil.getPort(), DBUtil.getDatabase() ,backupfile);
		
		Runtime.getRuntime().exec(command);
	}
	
	public static void recover(String mysqlPath, String recoverfile) {
		try {
			String commandFormat = "\"%s/bin/mysql.exe\" -u%s -p%s   %s";
			String command = String.format(commandFormat, mysqlPath, DBUtil.getLoginName(), DBUtil.getPassword());
			
			Process p = Runtime.getRuntime().exec(command);
			OutputStream out = p.getOutputStream();
			
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverfile), "utf8"));
			String inStr;
			while((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
			writer.write(sb.toString());
			writer.flush();
			out.close();
			br.close();
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
