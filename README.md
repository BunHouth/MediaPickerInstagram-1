# MediaPickerInstagram

[![](https://img.shields.io/badge/platform-android-orange.svg)](https://developer.android.com)
[![](https://img.shields.io/badge/min%20SDK-21-red.svg)](http://developer.android.com/about/dashboards/index.html)
[![](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://github.com/NodensN/MediaPickerInstagram/blob/master/LICENSE)
[![](https://www.bitrise.io/app/a75cb334c78a65a1.svg?token=iegzE5D55LP0tjg3OvaQFA&branch=master)](https://www.bitrise.io/app/a75cb334c78a65a1)

I've not found any MediaPicker component suitable for my needs for Android so I made this one.
It's a Media picker component that aims to replicate the experience provided by Instagram.
The features I plan to add are : Photo taking functionality Video taking functionality.
Hope this helps! Some help is much appreciated, there is much work that could be done. :)

## Screenshots

![picker](https://cloud.githubusercontent.com/assets/10350755/20828571/78467e0e-b878-11e6-9117-dfabb9dc0b90.png)
![editor](https://cloud.githubusercontent.com/assets/10350755/20828570/783bade4-b878-11e6-9c4a-daba7c20dc94.png)
![camera](https://cloud.githubusercontent.com/assets/10350755/20828569/77a3f6ca-b878-11e6-9420-8d5d0d84a9ed.png)

## Work In Progress

- [X] Gallery picker
- [ ] Capture photo
- [ ] Capture video
- [X] Editor photo

## Project Structure
```
AndroidManifest.xml
assets/
  |- fonts/
java/
  |- com/
  |  |- octopepper/
  |  |  |- mediapickerinstagram/
  |  |  |  |- commons/
  |  |  |  |- components/
  |  |  |  |  |- editor/
  |  |  |  |  |- gallery/
  |  |  |  |  |- photo/
  |  |  |  |  |- video/
  |  |  |  |- MainActivity.java
  |  |  |  |- MainApplication.java
res/
  |- anim/
  |- color/
  |- drawable/
  |- layout/
  |- layout-v14/
  |- mipmap-hdpi/
  |- mipmap-mdpi/
  |- mipmap-xhdpi/
  |- mipmap-xxhdpi/
  |- mipmap-xxxhdpi/
  |- values/
  |- values-v21/
  |- values-w820dp/
```

## Git
### Branch
* `master` --> **PRODUCTION**
* `development` --> **DEVELOPMENT BRANCH**

**For any news features create a new branch from development**

### Formatting commit messages
```
$ git commit -am "[... your message ...]"
```
*All commit message line will be cropped at 100 characters*

**Prefixe all your commit messages by one of this type:**
* `feat:`     A new feature
* `fix:`      A bug fix
* `docs:`     Documentation only changes
* `style:`    Changes that do not affect the meaning of the code (white-space, formatting, etc)
* `refactor:` A code change that neither fixes a bug or adds a feature
* `perf:`     A code changes that improves performance
* `test:`     Adding missing tests
* `chore:`    Changes to the build process or auxiliary tools and libraries

## Libraries
### Core
* Butter Knife --> [link](http://jakewharton.github.io/butterknife/)
* Picasso --> [link](http://square.github.io/picasso/)
* Rebound --> [link](http://facebook.github.io/rebound/)
* RxJava --> [link](https://github.com/ReactiveX/RxJava)
* Image processing --> [link](https://github.com/Zomato/AndroidPhotoFilters)

## Naming Conventions
```java
public class MyClass {

    public static final int SOME_CONSTANT = 42;
    public int publicField;
    private static MyClass sSingleton;
    int mPackagePrivate;
    private int mPrivate;
    protected int mProtected;
    boolean isBoolean;
    boolean hasBoolean;

    @BienView({id})
    View mMyView;

    @BindString({id})
    String _myString;

    @BindColor({id})
    int _myColor;

}
```

Code style for Android --> [link](http://source.android.com/source/code-style.html)

## Android Version Support
Android fragmentation analytics --> [Platform Versions](http://developer.android.com/about/dashboards/index.html#Platform)

* Min API 21 --> Lollipop : 5.x.x
* Max API 25 --> Nougat : 7.x.x

## Contributors
[NodensN](https://twitter.com/Guillaume_Mas)
