# SlidingIndicatorBar
![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)
![Api](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)

> 可以弯曲的滑动指示条控件

# Preview

![image](https://github.com/zaaach/SlidingIndicatorBar/raw/master/imgs/preview.jpg)

# Install

**1：** 项目根目录的build.gradle添加如下配置：

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

**2：** app添加依赖：

```groovy
dependencies {
	 implementation 'com.github.zaaach.widget:SlidingIndicatorBar:x.y.z'
}
```

记得把`x.y.z`替换为[![jitpack](https://jitpack.io/v/zaaach/CityPicker.svg)](https://jitpack.io/#zaaach/CityPicker)中的数字

# Usage

`xml`里直接使用

```xml
<com.github.zaaach.widget.SlidingIndicatorBar
        android:layout_width="240dp"
        android:layout_height="wrap_content"
        app:sib_bar_color="@color/purple_200"
        app:sib_bar_height="16dp"
        app:sib_bending_height="56dp"
        app:sib_bending_ratio="1"/>
```

相关自定义属性：

| 属性               | 值                   | 描述           |
| ------------------ | -------------------- | -------------- |
| sib_bar_height     | dimension\|reference | 指示条高度     |
| sib_bar_color      | color\|reference     | 指示条颜色     |
| sib_bending_height | dimension\|reference | 可弯曲最大高度 |
| sib_bending_ratio  | float                | 弯曲比例       |



# About me

掘金：[ https://juejin.im/user/56f3dfe8efa6310055ac719f ](https://juejin.im/user/56f3dfe8efa6310055ac719f)

淘宝店：[ LEON生活馆]( https://shop238932691.taobao.com)

![LEON](https://raw.githubusercontent.com/zaaach/imgbed/master/arts/leon_shop_qrcode.png)

:wink:淘宝店求爆单:wink:
