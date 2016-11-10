package organization.yhwapp.com.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 缓存数据库helper
 *
 */
public class DBHelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "tb_cache";
	public static final String DATABASE_NAME = "tuxing_db";

	public static final int version = 1;

	private static DBHelper instance;

	private DBHelper(Context context) {
		super(context, DATABASE_NAME, null, version);
	}
	
	public static DBHelper getInstance(Context context) {
		if (instance == null) {
			instance = new DBHelper(context.getApplicationContext());
		}
		return instance;
	}

	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// 只用创建一个表
		String sql = " create table if not exists " + TABLE_NAME + "(id text primary key , " + "data text )  ";
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	// 插入数据
	public void insertData(String key, String data) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues value = new ContentValues();
		value.put("id", key);
		value.put("data", data);
		db.insert(TABLE_NAME, null, value);
		close(db, null);
	}

	// 更新数据
	public void updateData(String key, String data) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues value = new ContentValues();
		value.put("data", data);
		db.update(TABLE_NAME, value, "id = ?", new String[] { key });
		close(db, null);
	}

	// 查找数据
	public String getData(String key) {

		SQLiteDatabase db = getReadableDatabase();
		String sql = "select * from " + TABLE_NAME + " where id = ?";
		Cursor cursor = db.rawQuery(sql, new String[] { key });
		String str = null;
		while (cursor.moveToNext()) {
			str = cursor.getString(1);
		}
		close(db, cursor);
		return str;
	}

	// 保存或者更新数据 保证不一直插入数据导致数据库过于庞大
	public void saveOrUpdate(String key, String data) {
		String str = getData(key);
		if (data.equals(str)) {
			// 如果相等那么就不操作
			return;
		} else {
			if (str != null) {
				updateData(key, data);
			} else {
				insertData(key, data);
			}
		}
	}

	private void close(SQLiteDatabase db, Cursor cursor) {
		if (db != null) {
			db.close();
		}
		if (cursor != null) {
			cursor.close();
		}
	}
}
