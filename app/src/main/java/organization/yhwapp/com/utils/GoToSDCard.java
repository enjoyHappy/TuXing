package organization.yhwapp.com.utils;

import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GoToSDCard {
	private String SDPATH = null;

	public String getSDPATH() {
		return SDPATH;
	}

	public GoToSDCard() {
		// 得到当前外部存储设备的目录
		// SDCARD
		SDPATH = Environment.getExternalStorageDirectory() + "";
		System.out.println("SDPATH=" + SDPATH);
	}

	/*
	 * 在SD卡上创建文件
	 */
	public File CreatSDFile(String fileNmae) {
		File file = new File(SDPATH + fileNmae);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	/*
	 * 在SD卡上创建目录
	 */
	public File creatSDDir(String dirName) {
		File dir = new File(SDPATH + dirName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	/*
	 * 判断SD卡上的文件夹是否存在
	 */
	public boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		return file.exists();
	}

	/*
	 * 将一个InputSteam里面的数据写入到SD卡中
	 */
	public File write2SDFromInput(String path, String fileName,
			InputStream input) {
		System.out.println(path + fileName);
		File file = null;
		File folder = null;
		FileOutputStream output = null;
		try {
			// 创建目录
			folder = creatSDDir(path);
			System.out.println("folder=" + folder);
			// 创建文件
			file = CreatSDFile(path + fileName);

			System.out.println("file=" + file);

			output = new FileOutputStream(file);

			byte[] b = getByte(input);
			output.write(b);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	private byte[] getByte(InputStream inputStream) throws Exception {
		byte[] b = new byte[1024];
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int len = -1;
		while ((len = inputStream.read(b)) != -1) {
			byteArrayOutputStream.write(b, 0, len);
		}
		byteArrayOutputStream.close();
		inputStream.close();
		return byteArrayOutputStream.toByteArray();
	}
}