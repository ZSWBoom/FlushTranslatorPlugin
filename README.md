# 手炒翻译插件快速上手

> 服务器开发 朱少威
> 
> 插件开发 张靖昊
>
> 技术支持 刘璞辉

## 简介

手炒翻译插件目前处于开发者预览版，提供了选词翻译以及新建词条的功能，支持 IntelliJ、Android Studio、PyCharm 等 JetBrains 系 IDE。

主要解决以下痛点：

- 命名难以规范
- 新人在老代码中遇到奇怪命名不知意

插件使用 Java 开发，插件本身是基于 IDEA Plugin DevKit，UI 使用了 Swing，服务端使用 Spring。

## 获取插件

如果在内网使用，首先在外网[点击此处](https://github.com/laomao1997/FlushTranslatorPlugin/releases/download/0.2/FlushTranslatorPlugin_BASE.zip)下载插件，之后传输至内网。

如果在外网使用，[点击此处](https://github.com/laomao1997/FlushTranslatorPlugin/releases/download/0.2/FlushTranslatorPlugin_TEST.zip)下载插件。

## 安装到 IDE

首先打开IDE，进入 `File`，选择 `Setting`。

<a href="https://sm.ms/image/ufUo9JxznsaglNh" target="_blank"><img src="https://i.loli.net/2020/07/30/ufUo9JxznsaglNh.jpg" ></a>

进入 `Plugins`，点击**齿轮标志**，选择 `Install Plugin from Disk...`。

<a href="https://sm.ms/image/UgPIHM4GrWYvw2e" target="_blank"><img src="https://i.loli.net/2020/07/30/UgPIHM4GrWYvw2e.jpg" ></a>

之后找到刚刚下载的插件 zip 文件，点击 `OK`，之后重启 IDE。

<a href="https://sm.ms/image/A25JpewuYKPbFLl" target="_blank"><img src="https://i.loli.net/2020/07/30/A25JpewuYKPbFLl.jpg" ></a>

在编辑器中**点击右键**，发现了如下图所示菜单项，安装成功。

<a href="https://sm.ms/image/I3eUGN2rZkb1Bfx" target="_blank"><img src="https://i.loli.net/2020/07/30/I3eUGN2rZkb1Bfx.jpg" ></a>

## 如何使用

### 选词翻译

选中固定的词句，点击右键即可，可以获取英文的中文及解释，或中文的英文命名。

<a href="https://sm.ms/image/2BeKaAsn8TOrm13" target="_blank"><img src="https://i.loli.net/2020/07/30/2BeKaAsn8TOrm13.jpg" ></a>

<a href="https://sm.ms/image/rxZjyECkJgvd9n5" target="_blank"><img src="https://i.loli.net/2020/07/30/rxZjyECkJgvd9n5.jpg" ></a>

### 添加词条

当遇到有的词在词库中找不到词条时，就可以添加新的词条。首先选中一个词，右键点击，选择**新建词条**。

<a href="https://sm.ms/image/GTiNLxR8q1lgdAP" target="_blank"><img src="https://i.loli.net/2020/07/30/GTiNLxR8q1lgdAP.jpg" ></a>

在弹出的新建词条窗口中输入词条的翻译和解释。

<a href="https://sm.ms/image/9blWf7cSANe6Fja" target="_blank"><img src="https://i.loli.net/2020/07/30/9blWf7cSANe6Fja.jpg" ></a>

点击提交按钮后即添加成功。

<a href="https://sm.ms/image/ifdGnBUAVT69aOR" target="_blank"><img src="https://i.loli.net/2020/07/30/ifdGnBUAVT69aOR.jpg" ></a>

添加成功后，就可以获取到这个词的翻译了。

<a href="https://sm.ms/image/PQiyVeCnfkDjsWL" target="_blank"><img src="https://i.loli.net/2020/07/30/PQiyVeCnfkDjsWL.jpg" ></a>

## 考虑优化

- 增加审核新词条功能，新增词条需要审核后才可以添加到词库中
- UI 优化
