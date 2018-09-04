# README
## 依赖配置
### 工程（项目根目录）build.gradle文件配置

```
ext {
    routerVersionName = '1.0.0'//该变量名和项目里面gradle里面文件名相对应
}
```
Module模块build.gradle文件配置

```
dependencies {
      annotationProcessor 'com.trc.android:lib-router-processor:' + rootProject.ext.routerVersionName    
	//每个需要扫描注解的模块都需要api 'com.trc.android:lib-router:' + rootProject.ext.routerVersion//最好放在base模块里面
}
```

### 简单使用
一般使用:路由注解可以声明在任意Class上
//实例代码1:声明在Activity上，

```
@RouterUri("taihe://to_login")//声明的URI可以包含多个
public class LoginActivity extends Activity {
//public static void start(Router router)方法可以省略，如果需要处理参数，则需要提供该方法完成参数处理及页面跳转
}
```

#### 示例代码1:声明在Activity上

```
@RouterUri("taihe://to_login")//声明的URI可以包含多个
public class LoginActivity extends Activity {
	public static void start(Router router){//需要处理参数，则需要提供该方法完成参数处理及页面跳转
    	Context context = router.getContext();
		String channel = router.getParams("channelCode");
		Intent intent = new Intent(context, LoginActivity.class);
		intent.put(LoginActivity.INTENT_KEY_CHANNEL, channel);
    	context.startActivity(intent);
 	}
}
```

#### 示例代码2:声明在任意Class上

```
@RouterUri({"taihe://share","trc://share"})//声明的URI可以包含多个
public class ShareRouterHandler{
	
	//路由管理器最终会调用该方法。如果是Activity的无参数跳转，那么该Activity可以省略此方法
	public static void start(Router router){
    	Context context = router.getContext();

		final Router.Callback callback = router.getCallback();
		final WebView webView = (WebView) router.get("webView");
		if (null != callback || null != webView) {
    		ShareApi.share(context, router.toUri(), new ShareCallback() {
       	 	@Override
        		public void onShareEvent(ShareEvent shareEvent) {
            		Bundle bundle = new Bundle();
            		bundle.putSerializable(SHARE_EVENT, shareEvent);
            		if (null != callback) {//如果Router有设置Callback，需要处理回调结果
                		callback.onResult(true, bundle);
            		}
            		if (null != webView) {//如果Router有WebView参数，则需要处理对WebView的结果进行处理，当然也可以通过Callback的地方处理
                		if (shareEvent.eventType == ShareConstants.EventType.CLICK_EVENT) {
                    		webView.loadUrl("javascript:shareSuccess('" + shareEvent.platform + "')");
                		} else if (shareEvent.eventType == ShareConstants.EventType.SHARE_SUCCESS_EVENT) {
                    		webView.loadUrl("javascript:shareCallback('" + shareEvent.platform + "')");
                		} else {
                    		webView.loadUrl("javascript:shareFail()");
                		}
            		}
        		}
    		});
		}
		if (context instanceof Activity) {
    		((Activity) context).overridePendingTransition(0, 0);
		}
 	}
}
```

#### 示例代码3:跳转第三方Activity页面时（比如跳转第三方FaceIdActivity，FaceIdActivity无法编辑）

```
@RouterScheme("tlkjrn")
public class FaceIdUriHandler {
    //配置当前类的注解实现Router路由
    public static void start(Router router) {
        String bundleName = router.getParams("module");
        String page = router.getParams("page");
        Bundle bundle = new Bundle();
        bundle.putString("page", page);
        router.getContext().startActivity(FaceIdActivity y.newIntent(router.getContext(), bundleName, bundle));
    }
}
```

## 配置及初始化
### 在主模块（APP模块）的任意一个类上添加注解如下：
编译扫描到该注解会确定当前编译的是最后一个模块，进而在该模块编译结束后生成AddressList.class

```
	@RouterAppModule
 	public class MainActivity extends Activity {  }
```

初始化Router
主模块新建RouterHelper类，代码如下

```
public class RouterHelper {
    public static HashMap<String, String> redirectMap = new HashMap<>();
    public static RedirectAdapter redirectAdapter = new RedirectAdapter() {
    @Override
 	public Router adapt(Router router) {//该路由转换的实现方案是基于当前APP路由管理后台的方案实现的，如果没有，直接return router;
            String url = router.toUriStr();
            for (String targetUrl : redirectMap.keySet()) {
                if (url.contains(targetUrl)) {
                    return Router.from(router.getContext()).setUri(redirectMap.get(targetUrl));
                }
            }
            return router;
        }
    };

    public static void init(Application application) {
        RouterConfig.getInstance()
                .init(application)
				.addInterceptor(LogInterceptor.class)//路由管理器在处理路由时，会传递给该拦截器进行拦截处理,比如添加一个记录页面跳转的拦截器，该拦截器优先级最高
 				.setRedirectAdapter(redirectAdapter);//路由管理器在处理路由时，会首先使用RedirectAdapter进行路由转换
    	}
}
```

路由跳转
>示例代码1
>
```
Router.from(MainActivity.this).to("trc://login");//直接跳转到登录页面
```
 
>示例代码2
>
```
Router.fromCurrentContext().to("trc://login");//当前代码前后文不方便获取Context的时候，该方法会优先使用当前Activity，如果没有则使用Application
```
 
>示例代码3
>
```
代码对1:
View v = (View) Router.from(this).setUri("test://custom_view").transform();//通过URI获取一个对象，该URI匹配的Class必须提供public static RemoteViewDemo transformObject(Router router)方法
代码对2:
@RouterUri("test://custom_view")
public class RemoteViewDemo extends android.support.v7.widget.AppCompatTextView {
    public RemoteViewDemo(Context context) {
        super(context);
        setText("This view can be declared in other module so that we can cut off the dependency of codding level");
    }
    //必须实现此方法，返回类型可为任意类型
    public static RemoteViewDemo transformObject(Router router) {
        return new RemoteViewDemo(router.getContext());
    }
}
```

> 示例代码4
> 
```
Router.from(context).setUri("demo://login").go() 效果等同于 
Router.from(context).to("demo://login") ，建议使用后一种方式
```

>示例代码5：
>
>```
setParams(String key, Object value)设置Query参数,去参数是对应 
router.getParams(String key)方法，该方法只适合传递基本类型的数据
Router.form(context).setParams("goodsId", "2C8DS").setParams("goodsCategory", 1).to("demo://goodsDetail") 
Router.form(context).to("demo://goodsDetail?goodsId=2C8DS&goodsCategory=1") //效果同上，建议使用上一种方式传参
```


>示例代码6：
>
>```
>put(String key, Object value), get(String key)对象参数传递，该参数存放在Router的一个Map里面，主要传递一些诸如Model、Fragment、WebView等对象
比如上面提到的ShareRouterHandler，来处理分享URI的Handler，如果是从WebView分发过来的，不要带上WebView对象，这样在分享成功后，可以拿到WebView对象，调相应的JS方法把分享结果告诉H5示例代码7:设置回调，可以获取跳转后的页面的异步回调结果
Router.from(this).setCallback(new Router.Callback() {
 	@Override public void onResult(boolean succeed, Bundle bundle) {
 	Toast.makeText(MainActivity.this, bundle.getString("name"), Toast.LENGTH_SHORT).show();
 	}
}).to("tlkj://trc.com");
```

### 拦截器配置
就是在跳转到指定页面的时候(准确的说是在执行URI匹配的class的start方式之前)，需要做拦截操作，诸如登录校验、实名认证校验等常用校验操作可使用拦截器做到代码复用、简化的目的。

示例拦截器-登录拦截器

```
public class LoginInterceptor implements Interceptor {
    @Override   
    public void handle(Router router, Chain chain) {    
		   
           if (!UserConfig.isLogined())    //如果未登录则跳转到登录，回来之后检查登录结果，如果登录成功则继续路由下去，否则停止路由     
                 Router.Callback loginCallback = new Router.Callback() {
    				@Override
    				public void onResult(boolean succeed, Bundle bundle) {
        				if (succeed) {
            				chain.proceed(router);//此行代码保证路由能继续被处理，否则路由分发就此终止
        			}else {
            			Toast.makeText(router.getContext(), "登录失败", Toast.LENGTH_SHORT).show();
        			}
    			}
				Router.from(router.getContext()).setCallback(loginCallback).to(Pages.LOGIN);         
           else {         
                chain.proceed(router); //此行代码保证路由能继续被处理，否则路由分发就此终止
          }  
     } 
} 
```

示例拦截器-实名认证拦截器

```
public class RealNameOauthInterceptor implements Interceptor {
    @Override   
    public void handle(Router router, Chain chain) {    
			RealNameApi.getRealNameStatus(new Callback<Boolean>(){
				public void onSuccess(Boolean isRealName){
					if (isRealName)//未登录则跳转到登录页面
						Router.Callback realNameCallback = new Router.Callback() {
    						@Override
    						public void onResult(boolean succeed, Bundle bundle) {
        						if (succeed) {//认证成功，继续执行之前到路由
            						chain.proceed(router);//此行代码保证路由能继续被处理，否则路由分发就此终止
        						}else {//认证失败，放弃路由
            						Toast.makeText(router.getContext(), "登录失败", Toast.LENGTH_SHORT).show();
        						}
    						}          
                 		Router.from(router.getContext()).setCallback(realNameCallback).to("demo://realname_oauth");//跳转到实名认证页面进行认证       
           			else {         
                 		chain.proceed(router); //此行代码保证路由能继续被处理，否则路由分发就此终止
					}
			});
		   
     } 
} 
```

全局拦截器配置（最高优先级）

```
//路由管理器在初始化时候配置，例如打印页面跳转的拦截器
RouterConfig.getInstance().addInterceptor(LogInterceptor.class)
2跳转前配置拦截器配置（次高优先级）
//拦截器也可以在路由跳转配置时设置，例如跳转到充值页面前先要检查是否绑卡
Router.from(context).setInterceptor(BindBankCardInterceptor.class).to("demo://recharge")
```
 
注解拦截器配置 (最低优先级)

```
//比如，想进入充值页面（demo://recharge）页面,首先想到要进行登录检查，然后再进行实名状态检查
@RouterUri("demo://recharge")
@RouterInterceptor({LoginInterceptor.class,RealNameOauthInterceptor.class})//跳转到该页面前先要通过登录拦截器和实名认证拦截器
public class RechargeActivity extends Activity {
}
```

### 混淆配置

```
#Router -end
 -keepattributes *Annotation*
 -keep public class com.trc.android.router.build.**{*;}
 -keeppackagenames com.trc.android.router.annotation.**
 -keep @com.trc.android.router.annotation.** class *
 -keepclassmembers class * {
     public static void start(com.trc.android.router.Router);
 }
 -keepclassmembers class * {
     public static RemoteViewDemo transformObject(com.trc.android.router.Router);
 }
 #Router -end
```