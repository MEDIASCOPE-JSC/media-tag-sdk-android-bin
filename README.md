# media-tag-sdk-android-bin

# Mediatag SDK (Android версия)

## Использование

При использовании mavenCentral в файл **build.gradle(app)** необходимо добавить зависимости:
```
    dependencies {
        implementation 'net.mediascope:mediatagsdk:1.0.5*
    }
```
При использовании aar-файла необходимо добавить зависимости:
```groovy
  
    implementation 'com.google.android.gms:play-services-appset:16.0.2'
    implementation 'androidx.preference:preference:1.2.0'

    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava3:2.9.0'

    implementation 'com.google.code.gson:gson:2.9.0'

    implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
    implementation 'io.reactivex.rxjava3:rxjava:3.1.3'

    implementation 'javax.inject:javax.inject:1'

    implementation 'com.google.android.gms:play-services-ads-identifier:18.0.1'
    implementation 'com.huawei.hms:ads-identifier:3.4.28.313'
    implementation 'com.huawei.hms:ads-installreferrer:3.4.28.313'

    implementation "androidx.work:work-runtime:2.7.1"
    implementation 'androidx.work:work-rxjava3:2.7.1'
    implementation "androidx.concurrent:concurrent-futures:1.1.0"



```
Использование библиотеки:
```java
    MediatagConfiguration mediatagConfiguration = new MediatagConfiguration("partner_name", "tms");
    Mediatag.instance(this).activate(mediatagConfiguration);
    
    MediatagEvent event = new MediatagEvent(@NonNull MediatagEvent.ContactTypes type);  //инициализация события с одним обязательным параметром
    Mediatag.instance().addEvent(event); //добавляем событие в очередь для отправки

```

