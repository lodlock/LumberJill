# LumberJill

LumberJill is a logging Utility library for Android. Whenever LumberJill is used to call a log it will return:

* originating 
  * file name
  * class
  * method
  * line number
* calling method
  * file name
  * class
  * method
  * line number
  
In order to use this library you need to provide a class that implements LumberJillCallbacks. 

I recommend creating a class which both extends and implements adding for easier and faster access.

```
public class L extends LumberJill implements LumberJillCallbacks {

    @Override
    public boolean isDebugMode() {
        return BuildConfig.DEBUG;
    }

    @Override
    public int getDefaultDepth() {
        return 5;
    }

    @Override
    public String objectToString(Object object) {
        String message = null;
        if (object instanceof View) {
            message = "View :"+((View)object).getId();
        }
        return message;
    }
}
```

Extend any variant of Application class and in the onCreate pass the class which implements LumberJillCallbacks to LumberJill.init.

```
public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LumberJill.init(new L());
    }
}
```
  
###Methods
All log methods return filenames, classes, methods, and lines numbers.

Methods could be called directly `LumberJill.d(Object)` or via an extended class such as `L.d(Object)`.
 
Methods | Description
------ | ------------
v(Object) | Log to verbose 
d(Object) | Log to debug
i(Object) | Log to info
w(Object) | Log to warn
e(Object) | Log to error
wtf(Object) | Log to assert
getException(Throwable) | Returns the message of the exception along with cause message if it exists
initTrace() | Returns a string representation of the entire stack trace starting at the point this method is called
printExceptionTraceCauses(Throwable) | recursively prints all exception causes until there are none left

###Using an extended class such as provided example L
  
From here you can log anything you want throughout your app:
  
```
//String
L.d("logged")
```


You can also log arrays, multidimensional primitive arrays, and build your own logging logic for object types.

```
//Object arrays
L.d(new String[]{"item one", "item two"});

//Primitive multidimensional int array
L.d(new int[][]{new int[]{0,1,2}, new int[]{3,4,5}});
```

