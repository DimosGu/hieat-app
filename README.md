# Scala、ES 6实战

## 使用Play 2、Slick 3、React.js、Redux、Sass、Bootstrap 4、PostgreSQL、Redis开发

<hr/>

## Install

### Mac OS

    brew install redis postgresql
    launchctl load /usr/local/opt/postgresql/homebrew.mxcl.postgresql.plist
    redis-server /usr/local/etc/redis.conf

### Linux for Ubuntu

**install Nginx**: <a href="http://nginx.org/en/linux_packages.html#stable" target="_blank">http://nginx.org/en/linux_packages.html#stable</a>

**install PostgreSQL**: <a href="https://wiki.postgresql.org/wiki/Apt" target="_blank">https://wiki.postgresql.org/wiki/Apt</a>

### Install Node

    git clone https://github.com/creationix/nvm.git ~/.nvm && cd ~/.nvm && git checkout `git describe --abbrev=0 --tags`
    . ~/.nvm/nvm.sh
    nvm install stable
    echo '. ~/.nvm/nvm.sh' >> ~/.bashrc
    echo 'nvm use stable' >> ~/.bashrc

### Setup PostgreSQL

    postgres=> create user hieatuser nosuperuser replication encrypted password 'hieatpass';
    postgres=> create database hieat_site owner=hieatuser template=template0 encoding='UTF-8' lc_collate='zh_CN.UTF-8' lc_ctype='zh_CN.UTF-8';

### Build

    git clone https://github.com/yangbajing/hieat-app.git
    cd hieat-app/front-end
    npm install && npm run build
    cd ../
    ./sbt
    [hieat] $ testOnly me.hieat.site.data.repo.SchemaTest
    [hieat] $ ~ run

**注意** `[hieat] $ testOnly me.hieat.site.data.repo.SchemaTest` 这一步骤不要忘记，创建数据库。

## Use

访问: <a href="http://localhost:9000/sign/up" target="_blank">http://localhost:9000/sign/up</a> 注册用户
