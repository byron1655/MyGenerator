package com.witvillage.tools.comment;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3SimpleImpl;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

public class MyBatis3Simple extends IntrospectedTableMyBatis3SimpleImpl {

    @Override
    protected void calculateXmlAttributes() {
        super.calculateXmlAttributes();

        // 主键查询
        setSelectByPrimaryKeyStatementId("findById");

        // 查询全部
        setSelectAllStatementId("listAll");

        // 主键更新
        setUpdateByPrimaryKeyStatementId("updateById");

        // 主键删除
        setDeleteByPrimaryKeyStatementId("deleteById");
    }

//    @Override
//    protected void calculateJavaClientAttributes() {
//        super.calculateJavaClientAttributes();

//
//        StringBuffer sb = new StringBuffer();
//        sb.append(this.calculateJavaClientInterfacePackage());
//        sb.append('.');
//
//
////        sb.append(this.fullyQualifiedTable.getDomainObjectName());
//        sb.append(this.generateDomainName());
//        sb.append("Dao");
//
//        String mapperName = sb.toString();
//        super.setMyBatis3JavaMapperType(mapperName);
//    }
//
//    private String generateDomainName() {
//        String newDomainName = this.fullyQualifiedTable.getIntrospectedTableName();
//        int index = newDomainName.lastIndexOf("_");
//        if (index > 0) {
//            newDomainName = newDomainName.substring(index + 1);
//        } else {
//            newDomainName = this.fullyQualifiedTable.getDomainObjectName();
//        }
//        return newDomainName;
//    }
}
