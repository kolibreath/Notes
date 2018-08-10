``fromXDelta``
``toXDelata`` 从那个点开始到那个点结束

````


    <?xml version="1.0" encoding="utf-8"?>  
    <set xmlns:android="http://schemas.android.com/apk/res/android" android:interpolator="@android:anim/accelerate_interpolator">  
        <translate android:fromXDelta="0%p" android:toXDelta="-100%p"  
            android:duration="500" />  
    </set>  
````

从屏幕正中间 移动到屏幕最左边直到看不见为止 

如果竖着的位置 

在屏幕最下面看不到的地方是100% 在屏幕最上面是-100%

如果是Activity的动画 可以使用系统中的 Transition api进行操作
