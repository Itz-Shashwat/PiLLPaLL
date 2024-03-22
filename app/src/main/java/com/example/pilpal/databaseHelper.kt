import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "UserInfo.db"
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${DatabaseContract.UserInfo.TABLE_NAME} (" +
                    "${DatabaseContract.UserInfo._ID} INTEGER PRIMARY KEY," +
                    "${DatabaseContract.UserInfo.COLUMN_NAME_NAME} TEXT," +
                    "${DatabaseContract.UserInfo.COLUMN_NAME_AGE} INTEGER," +
                    "${DatabaseContract.UserInfo.COLUMN_NAME_GENDER} TEXT," +
                    "${DatabaseContract.UserInfo.COLUMN_NAME_TABLET1} TEXT," +
                    "${DatabaseContract.UserInfo.COLUMN_NAME_TABLET2} TEXT," +
                    "${DatabaseContract.UserInfo.COLUMN_NAME_TABLET3} TEXT," +
                    "${DatabaseContract.UserInfo.COLUMN_NAME_CONDITION} TEXT)"
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DatabaseContract.UserInfo.TABLE_NAME}"
    }
}