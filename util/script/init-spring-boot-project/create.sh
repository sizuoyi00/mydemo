#! /bin/sh -

basePath="/Users/guest1/Desktop/jf180/szy/demo"
projectName="demo"
projectNamePlaceHolder="project-name"

if [[ -d $basePath ]]; then
	echo "project workspace path is exit, please change to another one: "$basePath
	exit 1
fi

mkdir -p $basePath
mkdir $basePath/h5
mkdir -p $basePath/api/src/main/java/cn/sy/"$projectName"/api; mkdir -p $basePath/api/src/main/resources; mkdir -p $basePath/api/src/test/java; mkdir -p $basePath/api/src/test/resources
mkdir -p $basePath/service/src/main/java/cn/sy/"$projectName"/service; mkdir -p $basePath/service/src/main/resources; mkdir -p $basePath/service/src/main/mybatis-generator; mkdir -p $basePath/service/src/test/java; mkdir -p $basePath/service/src/test/resources
mkdir -p $basePath/service/src/main/resources/dao/mapper/$projectName; mkdir -p $basePath/service/src/test/resources/dao/mapper/$projectName
mkdir -p $basePath/bmg/src/main/java/cn/sy/"$projectName"/bmg; mkdir -p $basePath/bmg/src/main/resources; mkdir -p $basePath/bmg/src/test/java; mkdir -p $basePath/bmg/src/test/resources

echo "project workspace path is: "$basePath

cp source/gitignore $basePath/.gitignore
sed "s/$projectNamePlaceHolder/$projectName/g" source/pom.xml > $basePath/pom.xml

sed "s/$projectNamePlaceHolder/$projectName/g" source/pom-api > $basePath/api/pom.xml
sed "s/$projectNamePlaceHolder/$projectName/g" source/Application > $basePath/api/src/main/java/cn/sy/"$projectName"/Application.java

sed "s/$projectNamePlaceHolder/$projectName/g" source/pom-service > $basePath/service/pom.xml
sed "s/$projectNamePlaceHolder/$projectName/g" source/generator.example > $basePath/service/src/main/mybatis-generator/$projectName"GeneratorConfig.xml"
sed "s/$projectNamePlaceHolder/$projectName/g" source/application.properties > $basePath/service/src/main/resources/application.properties
sed "s/$projectNamePlaceHolder/$projectName/g" source/application-dev.properties > $basePath/service/src/main/resources/application-dev.properties
cp source/application-prod.properties $basePath/service/src/main/resources/application-prod.properties
sed "s/$projectNamePlaceHolder/$projectName/g" source/logback-spring.xml > $basePath/service/src/main/resources/logback-spring.xml

sed "s/$projectNamePlaceHolder/$projectName/g" source/pom-bmg > $basePath/bmg/pom.xml
sed "s/$projectNamePlaceHolder/$projectName/g" source/Application > $basePath/bmg/src/main/java/cn/sy/"$projectName"/Application.java
sed "s/$projectNamePlaceHolder/$projectName/g" source/logback-spring-service.xml > $basePath/bmg/src/main/resources/logback-spring.xml

echo "create successfully"
