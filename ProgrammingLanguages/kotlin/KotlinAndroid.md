# Kotlin in Android Dev

- View extension
给View增加扩展属性
`````
val View.ctx: Context
    get() = context
`````

context本身是通过View 的实例iew.getContext()引用 在作为View的扩展函数的时候这个函数在Kt中被映射为context，而且Kt中没有static 的概念，所以引用数值一定是通过实例引用，因此类中的方法都可以使用，非常方便 

- 属性委托
这种写法真的是太秀了
````
object DelegatesExt {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
    fun <T> preference(context: Context, name: String,
            default: T) = Preference(context, name, default)
}

class Preference<T>(private val context: Context, private val name: String,
        private val default: T) {

    //只有第一次调用时才会被初始化
    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    //委托
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = findPreference(name, default)

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun findPreference(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }

        res as T
    }

    @SuppressLint("CommitPrefEdits")
    private fun putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }.apply()
    }
}
````

with 函数中会使用this代替 放入的参数 后面不需要在使用实例引用，代码比较整洁

- 自带异步支持的函数
````
private fun loadForecast() = async(UI) {
        val result = bg { RequestForecastCommand(zipCode).execute() }
        updateUI(result.await())
    }

````

async(UI) 支持在Android MainThread进行处理，开了一个默认的线程池 bg是aynsc的封装，开了一个新的线程池

- 闭包作为参数
````

 class ForecastListAdapter(private val weekForecast: ForecastList,
        private val itemClick: (Forecast) -> Unit)
````
item click 直接传递一个闭包





## the Use of Scoping Function
````
DrawerArrowDrawable(toolbar.ctx).apply { progress = 3000f }
````
作用域函数apply会返回原对象本身并且执行闭包中的函数