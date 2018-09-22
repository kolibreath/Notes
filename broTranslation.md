Bro offers you a set of callback methods, the usage of which is similiar with the ones you are familiar with in ``Application``.

Eg: the ``onCreate()`` method of ``IBroModule`` will be called after the ``onCreate()``of ``Application``


# Usage 

## LifeCycle Listeners

In your service module:

````
// implements IBroModule and annotates with  @BroModule to expose the module to the outside world
@BroModule("DataModule")
public class DataModule implements IBroModule {

    @Override
    public void onCreate() {
        Log.e("DataModule", "onCreate");
    }
}
```` 

## Trigger Callback Listeners

- Bro needs to be initialized in Application'``onCreate()`` method, as ``onCreate()`` will be triggered in ``init(...)`` block of Bro.  

## Best Practice

to be continued!


# 全局跳转

Using Activity as the carrier,  Native Page， Web Page, Weex Page will be generalized as unified Uris.

## Usage

### Initialization

When initializing Bro, pass the implementations of ``IActivityFinder``as the parameter.

````
List<IActivityFinder> finders = new ArrayList<>();
finders(new AnnoPageFinder());
finders(new PackageManagerPageFinder());

BroConfig config = new BroConfig.Builder()
                    .setLogEnable(true)
                    .setActivityFinders(finders)
                    .build();

````

Bro iterates the list of finders in the initialzation process when liiking for the target Activity, and the process will not proceed when the return value of  one of ``PageFinder``s is not null.

### Declaration of Pages that need to be exposed

Two approaches are supported by default so far. ``Finder`` can be extended for wider range of support.

- Annocation : ``@BroActivity(String uri)`` Passing  ``uri`` as parameter for the annotation for the Activity needing to be exposed.

````
@BroActivity("broapp://settings")
public class SettingsActivity extends AppCompatActivity {
    ...
}
````

- Manifest : compatiable with Android native router support.
````
<intent-filter>
    <category android:name="android.intent.category.DEFAULT" />
    <data
        android:host="home"
        android:scheme="broapp" />
</intent-filter>
````

### Start Another Activity

````
Bundle bundle = new Bundle();
bundle.putString("bundleparam", "123");
Bro.startActivityFrom(context)
        .withExtras(bundle)
        .toUri(Uri.parse("broapp://home?urlparam=233"));

// 更多 API，以及如何获取跳转结果
// ActivityRudder rudder = Bro.startPageFrom(this) // 当前 Context
//        .withExtras(bundle) // 携带参数
//        .withFlags(flags) // 携带 Flags
//        .withCategory(category) // 添加 category
//        .forResult(resultCode) // 用于 onActivityResult 的请求参数  
//        .justForCheck() // 若调用该 API，则不会触发最终的 startActivity，通常用来配合检查目标页面是否存在（而不想跳转）
//        .toUri(Uri.parse("broapp://home?urlparam=233"));   // 目标 Uri
//
// rudder.isIntentValidate(); // 目标 Activity 是否被找到了
// rudder.isIntercepted(); // 跳转过程中是否被拦截了
// rudder.getIntent(); // 获得跳转所用的 Intent
// rudder.getBuilder(); // 获得页面跳转的参数构造器
````

## Best Practice

## It's recommended to use annotation to declare ``Page``

It's remmended to use ``@BroActivity`` to exporse an ``Activity`` ,though Bro gives you ``IActivityFinded`` and ``setFinders`` as customized options. The reasons are listed as follows:

- Declarations in Manifest often come with logic that need specical treat. For example, if the third-party sdk comes along with an ``intent-filter`` for http while another ``intent-filter`` has already been declared in you app, some treatment will applied to match and distinguish ( eg: category).

- Manifest declarations don't supported customization for BroProperties, which means some customized attributes cannot be intercepted ( but this question will be solved in future)

- Manifest declaration may relatively expose some unnessary information.


As a matter of fact, ``AnnoActivityFinder`` seems be able to take care of everything, customized ``Finder`` is not nessary. Taking into account the actual situation, the remaining navigation login and exsited bus design often use manifest as container, in order to make users migrate seamlessly to Bro, these interfaces come into being.



## Bus for Fragments and Services

In the early versions of Bro, such methods like ``getFragment()`` and ``startSerivce()`` once existed. However, these methods cannot apply very often:

- Most of Android engineers prefer to replace fragment with transparent Activity or Dialog after breaking down modules in some scenes, such as implementing the update dialog or display a film ticket, in this way, the implementation is more independent and easier ( data interaction can be realized through methods like ``onActivityResult()``)

- There are not so many Services in an App, let along fewer scene to expose them as usual Services are exsited as a long-term task running in background or starts when App and module starts.


It's not difficult to realized that, with BroApi offered by Bro, the above scenes can be implemented with apis in BroApi. 
See the BroApi documentation and the Sample project for details.




































