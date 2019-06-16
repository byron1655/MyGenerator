package com.witvillage.tools.comment;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author byron
 */
public class CommentGenerator extends EmptyCommentGenerator {

    private Properties properties;
    private boolean suppressDate;
    private boolean suppressAllComments;

    public CommentGenerator() {
        properties = new Properties();
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        // 获取自定义的 properties
        this.properties.putAll(properties);
        this.suppressDate = false;
        this.suppressAllComments = false;
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        //生成的是 JavaModel 和 JavaModelExample 文件
        if (compilationUnit instanceof TopLevelClass) {
            //这里可以修改  JavaModel 和 JavaModelExample 文件
                /*TopLevelClass topLevelClass = (TopLevelClass)compilationUnit;
                String shortName = compilationUnit.getType().getShortName();
                topLevelClass.addAnnotation("@Resource");
                topLevelClass.addImportedType("javax.annotation.Resource");*/
        }
        // mapper
        if (compilationUnit instanceof Interface) {
            JavaElement javaElement = (JavaElement) compilationUnit;
            String shortName = compilationUnit.getType().getShortName();
            if (shortName != null && shortName.endsWith("Mapper")) {
                addJavaDoc(javaElement, null);
            }
        }
        if (compilationUnit instanceof InnerClass) {
            JavaElement javaElement = (JavaElement) compilationUnit;
            String shortName = compilationUnit.getType().getShortName();
            if (shortName != null && shortName.endsWith("SqlProvider")) {
                addJavaDoc(javaElement, null);
            }
            //只给JavaModel添加注解就行了，Example不需要
//            anInterface.addAnnotation("@Resource");
//            anInterface.addImportedType(new FullyQualifiedJavaType("javax.annotation.Resource"));
        }
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (this.suppressAllComments) {
            return;
        }
        addJavaDoc(innerClass, null);
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (this.suppressAllComments) {
            return;
        }
        addJavaDoc(innerClass, null);
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addJavaDoc(topLevelClass, introspectedTable);
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 获取列注释
        String remarks = introspectedColumn.getRemarks();
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + remarks);
        field.addJavaDocLine(" */");
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        // 方法注释
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * " + method.getName());
        for (Parameter param : method.getParameters()) {
            method.addJavaDocLine(" * @param " + param.getType().getShortName() + " " + param.getName());
        }
        method.addJavaDocLine(" * @return " + method.getReturnType().getShortName());
        method.addJavaDocLine(" */");
    }

    private void addJavaDoc(JavaElement elementClass, IntrospectedTable introspectedTable) {
        String author = properties.getProperty("author");
        String dateFormat = properties.getProperty("dateFormat", "yyyy-MM-dd");
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        if (introspectedTable != null) {
            // 获取表注释
            String remarks = introspectedTable.getRemarks();
            elementClass.addJavaDocLine("/**");
            elementClass.addJavaDocLine(" * " + remarks);
            elementClass.addJavaDocLine(" * @author " + author);
            elementClass.addJavaDocLine(" * @date   " + dateFormatter.format(new Date()));
            elementClass.addJavaDocLine(" */");
        } else {
            elementClass.addJavaDocLine("/**");
            elementClass.addJavaDocLine(" * @author " + author);
            elementClass.addJavaDocLine(" * @date   " + dateFormatter.format(new Date()));
            elementClass.addJavaDocLine(" */");
        }

    }
}