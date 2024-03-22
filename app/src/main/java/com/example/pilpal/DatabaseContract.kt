import android.provider.BaseColumns

object DatabaseContract {
    object UserInfo : BaseColumns {
        const val TABLE_NAME = "user_info"
        const val _ID = "_id"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_AGE = "age"
        const val COLUMN_NAME_GENDER = "gender"
        const val COLUMN_NAME_TABLET1 = "tablet1"
        const val COLUMN_NAME_TABLET2 = "tablet2"
        const val COLUMN_NAME_TABLET3 = "tablet3"
        const val COLUMN_NAME_CONDITION = "condition"
    }
}